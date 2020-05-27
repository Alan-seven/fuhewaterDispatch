package waterDispatch.model;
import waterDispatch.*;

import java.util.*;

public class WaterDistribution {
     /**
     * 水量分配计算
     */
     public void distributionCalculation(double errorThreshold,List<List<Double>>levels,List<River>rivers){
          River FHRiver=rivers.get(0);
          River XJRiver=rivers.get(1);
          River LSRiver=rivers.get(2);
          int timelength=FHRiver.getNodes().get(0).getTimeData().size();//调度时段数
          for (int i = 0; i <timelength ; i++) {
               //先计算支流
               this.tributaryCalculation(XJRiver,i,errorThreshold);
               this.tributaryCalculation(LSRiver,i,errorThreshold);
              //两个电站初末水位
               List<Double>levelStartlist=new ArrayList<>();
               List<Double>levelEndlist=new ArrayList<>();
               levelStartlist.add(0,levels.get(0).get(i));
               levelStartlist.add(1,levels.get(1).get(i));
               levelEndlist.add(0,levels.get(0).get(i+1));
               levelEndlist.add(1,levels.get(1).get(i+1));
               //两个电站
               List<HydroStation> hydroStation=new ArrayList<>();
               hydroStation.add(0,(HydroStation)FHRiver.getNodes().get(1));
               hydroStation.add(1,(HydroStation)FHRiver.getNodes().get(3));
               //再计算干流
               for (int j = 0; j <FHRiver.getNodes().size(); j++) {
                   //需要参与计算的河流
                   List<River>river=new ArrayList<>();
                   double inflowFrequency=FHRiver.getNodes().get(j).getTimeData().get(i).getInflowFrequency();
                   TimeBean data=FHRiver.getNodes().get(j).getTimeData().get(i);
                   //该节点以上的河流
                   List<Node>node=new ArrayList<>();
                   River riverTemp=new River();
                   for (int k = 0; k <=j ; k++) {
                       node.add(FHRiver.getNodes().get(k));
                   }
                   this.trunkStreamCalculation(node,i,levelStartlist,levelEndlist,hydroStation);
                   //判断需要重新参与计算的河流
                   riverTemp.setNodes(node);
                   if(j<2){
                       river.add(0,riverTemp);
                   }
                   else if(2<=j&&j<5){
                       river.add(0,riverTemp);
                       river.add(1,XJRiver);
                   }
                   else{
                       river.add(0,riverTemp);
                       river.add(1,XJRiver);
                       river.add(2,LSRiver);
                   }
                   //河道生态流量约束
                   if(inflowFrequency>=95){
                       if(data.getOutflowTotal()<data.getMinInstreamingEcologicalFlow()*2/3){
                           this.trunkStreamHydropeniaCalculation(i,river,levelStartlist,levelEndlist,hydroStation,data.getMinInstreamingEcologicalFlow()*2/3,errorThreshold);
                       }
                   }
                   else{
                       if(data.getOutflowTotal()<data.getMinInstreamingEcologicalFlow()){
                           this.trunkStreamHydropeniaCalculation(i,river,levelStartlist,levelEndlist,hydroStation,data.getMinInstreamingEcologicalFlow(),errorThreshold);
                       }
                   }
               }
              levels.get(0).set(i+1,levelEndlist.get(0));
              levels.get(1).set(i+1,levelEndlist.get(1));
          }
     }


    /**
     * * 区县的水量分配计算
     */
    public Map<Integer,Double> districtCalculation(TimeBean data){
        Map<Integer,Double>result=new HashMap<>();
        double inflowTotal=data.getUpstreamInflowTotal()+data.getSelfProducedInflowTotal();
        double consumptionTotal=data.getWaterRequirementPlan();
        double outflowTotal=inflowTotal-consumptionTotal+data.getWaterRequirementPlan()*(
                (data.getAgriculturalWaterRatio()*data.getAgriculturalWaterWeight()*data.getAgriculturalWaterReturnRatio()
                +data.getDomesticWaterRatio()*data.getDomesticWaterWeight()*data.getDomesticWaterReturnRatio()
                +data.getForestryWaterRatio()*data.getForestryWaterWeight()*data.getForestryWaterReturnRatio()
                +data.getIndustrialWaterRatio()*data.getIndustrialWaterWeight()*data.getIndustrialWaterReturnRatio())/
                (data.getAgriculturalWaterRatio()*data.getAgriculturalWaterWeight()
                +data.getDomesticWaterRatio()*data.getDomesticWaterWeight()
                +data.getForestryWaterRatio()*data.getForestryWaterWeight()
                +data.getIndustrialWaterRatio()*data.getIndustrialWaterWeight()));
        result.put(0,inflowTotal);
        result.put(1,consumptionTotal);
        result.put(2,outflowTotal);
        return result;
    }
    /**
     * 水库调度计算(还需单位转化)
     */
    public Map<Integer,Double> hydrostationCalculation(TimeBean data,double levelStart, double levelEnd,HydroStation hydroStation){
        Map<Integer,Double>result=new HashMap<>();
        double inflowTotal=data.getUpstreamInflowTotal()+data.getSelfProducedInflowTotal();
        double capatityStart=hydroStation.getCurve().getCapacityByLevel(levelStart);
        double capatityEnd=hydroStation.getCurve().getCapacityByLevel(levelEnd);
        //此处需要单位转化（单位定为亿m3）
        double outflowTotal=inflowTotal+(capatityStart-capatityEnd)/100;
        result.put(0,inflowTotal);
        result.put(1,outflowTotal);
        return result;
    }

    /**
     * * 虚拟节点计算
     */
    public List<Map<Integer, Double>> virtualNodeCalculation(VirtualNode virtualNode,int i){
        List<Map<Integer,Double>>result=new ArrayList<>();
        Map<Integer, Double>resultAll=new HashMap<>();//总虚拟点
        Map<Integer, Double>result1=new HashMap<>();//临川县
        Map<Integer, Double>result2=new HashMap<>();//金溪县
        result.add(0,resultAll);
        result.add(1,result1);
        result.add(2,result2);
        List<TimeBean>data=virtualNode.getTimeData();
        TimeBean data1=virtualNode.getLCConfluenceNode().getTimeData().get(i);
        TimeBean data2=virtualNode.getJXConfluenceNode().getTimeData().get(i);
        double inflowTotal=data.get(i).getUpstreamInflowTotal1()+data.get(i).getUpstreamInflowTotal2()+data1.getSelfProducedInflowTotal()+data2.getSelfProducedInflowTotal();
        double consumptionTotal1=data1.getWaterRequirementPlan();
        double consumptionTotal2=data2.getWaterRequirementPlan();
        double outflowTotal1=inflowTotal*data.get(i).getWeight1()-consumptionTotal1
                +data1.getWaterRequirementPlan()*(
                (data1.getAgriculturalWaterRatio()*data1.getAgriculturalWaterWeight()*data1.getAgriculturalWaterReturnRatio()
                        +data1.getDomesticWaterRatio()*data1.getDomesticWaterWeight()*data1.getDomesticWaterReturnRatio()
                        +data1.getForestryWaterRatio()*data1.getForestryWaterWeight()*data1.getForestryWaterReturnRatio()
                        +data1.getIndustrialWaterRatio()*data1.getIndustrialWaterWeight()*data1.getIndustrialWaterReturnRatio())/
                        (data1.getAgriculturalWaterRatio()*data1.getAgriculturalWaterWeight()
                                +data1.getDomesticWaterRatio()*data1.getDomesticWaterWeight()
                                +data1.getForestryWaterRatio()*data1.getForestryWaterWeight()
                                +data1.getIndustrialWaterRatio()*data1.getIndustrialWaterWeight()));
        double outflowTotal2=inflowTotal*data.get(i).getWeight2()-consumptionTotal2
                +data2.getWaterRequirementPlan()*(
                (data2.getAgriculturalWaterRatio()*data2.getAgriculturalWaterWeight()*data2.getAgriculturalWaterReturnRatio()
                +data2.getDomesticWaterRatio()*data2.getDomesticWaterWeight()*data2.getDomesticWaterReturnRatio()
                +data2.getForestryWaterRatio()*data2.getForestryWaterWeight()*data2.getForestryWaterReturnRatio()
                +data2.getIndustrialWaterRatio()*data2.getIndustrialWaterWeight()*data2.getIndustrialWaterReturnRatio())/
                (data2.getAgriculturalWaterRatio()*data2.getAgriculturalWaterWeight()
                +data2.getDomesticWaterRatio()*data2.getDomesticWaterWeight()
                 +data2.getForestryWaterRatio()*data2.getForestryWaterWeight()
                 +data2.getIndustrialWaterRatio()*data2.getIndustrialWaterWeight()));
        double outflowTotal=outflowTotal1+outflowTotal2;
        resultAll.put(0,inflowTotal);
        resultAll.put(1,outflowTotal);
        result1.put(0,consumptionTotal1);
        result1.put(1,outflowTotal1);
        result2.put(0,consumptionTotal2);
        result2.put(1,outflowTotal2);
        result.set(0, resultAll);
        result.set(1, result1);
        result.set(2, result2);
        return result;
    }

    /**
     * * 汇流节点计算
     */
    public Map<Integer,Double> confluenceNodeCalculation(TimeBean data){
        Map<Integer,Double>result=new HashMap<>();
        double inflowTotal=data.getUpstreamInflowTotal1()+data.getUpstreamInflowTotal2()+data.getSelfProducedInflowTotal();
        double consumptionTotal=data.getWaterRequirementPlan();
        double outflowTotal=inflowTotal-consumptionTotal+data.getWaterRequirementPlan()*(
                (data.getAgriculturalWaterRatio()*data.getAgriculturalWaterWeight()*data.getAgriculturalWaterReturnRatio()
                +data.getDomesticWaterRatio()*data.getDomesticWaterWeight()*data.getDomesticWaterReturnRatio()
                +data.getForestryWaterRatio()*data.getForestryWaterWeight()*data.getForestryWaterReturnRatio()
                +data.getIndustrialWaterRatio()*data.getIndustrialWaterWeight()*data.getIndustrialWaterReturnRatio())/
                (data.getAgriculturalWaterRatio()*data.getAgriculturalWaterWeight()
                +data.getDomesticWaterRatio()*data.getDomesticWaterWeight()
                +data.getForestryWaterRatio()*data.getForestryWaterWeight()
                +data.getIndustrialWaterRatio()*data.getIndustrialWaterWeight()));
        result.put(0,inflowTotal);
        result.put(1,consumptionTotal);
        result.put(2,outflowTotal);
        return result;
    }

    /**
     * * 支流计算
     */
    public void tributaryCalculation(River river ,int i,double errorThreshold){
        for (int j = 0; j <river.getNodes().size(); j++) {
            TimeBean data=river.getNodes().get(j).getTimeData().get(i);
            double inflowFrequency=river.getNodes().get(j).getTimeData().get(i).getInflowFrequency();
            double inflowTotal=this.districtCalculation(data).get(0);
            data.setInflowTotal(inflowTotal);
            if(inflowTotal<data.getWaterRequirementPlan()){
                data.setWaterRequirementPlan(inflowTotal);
            }
            double consumptionTotal=this.districtCalculation(data).get(1);
            data.setConsumptionTotal(consumptionTotal);
            double outflowTotal=this.districtCalculation(data).get(2);
            data.setOutflowTotal(outflowTotal);
            //上游下泄为下游上游来水
            if(j==river.getNodes().size()-1){
                river.getNodes().get(j).getDownNode().getTimeData().get(i).
                    setUpstreamInflowTotal1(outflowTotal);
            }
            else{
                river.getNodes().get(j).getDownNode().getTimeData().get(i).
                        setUpstreamInflowTotal(outflowTotal);
            }
            List<Node>upNode=new ArrayList<>();
            for (int k = 0; k <=j ; k++) {
                upNode.add(river.getNodes().get(k));
            }
            if(inflowFrequency>=95){
                if(outflowTotal<data.getMinInstreamingEcologicalFlow()*2/3){
                    this.tributaryHydropeniaCalculation(i,upNode,data.getMinInstreamingEcologicalFlow()*2/3,errorThreshold);
                }
            }
            else{
                if(outflowTotal<data.getMinInstreamingEcologicalFlow()){
                    this.tributaryHydropeniaCalculation(i,upNode,data.getMinInstreamingEcologicalFlow(),errorThreshold);
                }
            }
        }
    }
    /**
     * * 干流直接计算方法
     */
    public void trunkStreamCalculation(List<Node>nodes ,int i,List<Double> levelStartlist, List<Double> levelEndlist, List<HydroStation> hydroStation){
            for (int j = 0; j <nodes.size(); j++){
                TimeBean data=nodes.get(j).getTimeData().get(i);
                if(nodes.get(j).getType()==1){
                    double inflowTotal=this.districtCalculation(data).get(0);
                    data.setInflowTotal(inflowTotal);
                    if(inflowTotal<data.getWaterRequirementPlan()){
                        data.setWaterRequirementPlan(inflowTotal);
                    }
                    double consumptionTotal=this.districtCalculation(data).get(1);
                    data.setConsumptionTotal(consumptionTotal);
                    double outflowTotal=this.districtCalculation(data).get(2);
                    data.setOutflowTotal(outflowTotal);
                    if(nodes.get(j).getName()=="ZXX"){
                        nodes.get(j).getDownNode().getTimeData().get(i).
                                setUpstreamInflowTotal2(outflowTotal);
                    }
                    else{
                        if(nodes.get(j).getName()!="GFPY"){
                            nodes.get(j).getDownNode().getTimeData().get(i).
                                    setUpstreamInflowTotal(outflowTotal);
                        }
                    }
                }
                if(nodes.get(j).getType()==2){
                    HydroStation station=new HydroStation(nodes.get(0).getTimeData().size());
                    double levelStart=0;
                    double levelEnd=0;
                    if(nodes.get(j).getName()=="HMSK"){
                        station=hydroStation.get(0);
                        levelStart=levelStartlist.get(0);
                        levelEnd=levelEndlist.get(0);
                    }
                    if(nodes.get(j).getName()=="LFSK"){
                        station=hydroStation.get(1);
                        levelStart=levelStartlist.get(1);
                        levelEnd=levelEndlist.get(1);
                    }
                    double inflowTotal=this.hydrostationCalculation(data,levelStart,levelEnd,station).get(0);
                    data.setInflowTotal(inflowTotal);
                    double outflowTotal=this.hydrostationCalculation(data,levelStart,levelEnd,station).get(1);
                    data.setOutflowTotal(outflowTotal);
                    //上游下泄为下游上游来水
                    if(nodes.get(j).getName()=="HMSK"){
                        nodes.get(j).getDownNode().getTimeData().get(i).
                                setUpstreamInflowTotal2(outflowTotal);
                    }
                    else{
                        nodes.get(j).getDownNode().getTimeData().get(i).
                            setUpstreamInflowTotal(outflowTotal);
                    }
                }
                if(nodes.get(j).getType()==3){
                    VirtualNode virtualNode=(VirtualNode)nodes.get(j);
                    double inflowTotal=this.virtualNodeCalculation(virtualNode,i).get(0).get(0);
                    data.setInflowTotal(inflowTotal);
                    if(inflowTotal<data.getWaterRequirementPlan()){
                        data.setWaterRequirementPlan(inflowTotal);
                    }
                    double outflowTotal=this.virtualNodeCalculation(virtualNode,i).get(0).get(1);
                    data.setOutflowTotal(outflowTotal);
                    double consumptionTotal1=this.virtualNodeCalculation(virtualNode,i).get(1).get(0);
                    virtualNode.getLCConfluenceNode().getTimeData().get(i).setConsumptionTotal(consumptionTotal1);
                    double outflowTotal1=this.virtualNodeCalculation(virtualNode,i).get(1).get(1);
                    virtualNode.getLCConfluenceNode().getTimeData().get(i).setOutflowTotal(outflowTotal1);
                    double consumptionTotal2=this.virtualNodeCalculation(virtualNode,i).get(2).get(0);
                    virtualNode.getJXConfluenceNode().getTimeData().get(i).setConsumptionTotal(consumptionTotal2);
                    double outflowTotal2=this.virtualNodeCalculation(virtualNode,i).get(2).get(1);
                    virtualNode.getJXConfluenceNode().getTimeData().get(i).setOutflowTotal(outflowTotal2);
                    nodes.get(j).getDownNode().getTimeData().get(i).
                            setUpstreamInflowTotal(outflowTotal);
                }
                if(nodes.get(j).getType()==4){
                    double inflowTotal=this.confluenceNodeCalculation(data).get(0);
                    data.setInflowTotal(inflowTotal);
                    if(inflowTotal<data.getWaterRequirementPlan()){
                        data.setWaterRequirementPlan(inflowTotal);
                    }
                    double consumptionTotal=this.confluenceNodeCalculation(data).get(1);
                    data.setConsumptionTotal(consumptionTotal);
                    double outflowTotal=this.confluenceNodeCalculation(data).get(2);
                    data.setOutflowTotal(outflowTotal);
                    nodes.get(j).getDownNode().getTimeData().get(i).
                            setUpstreamInflowTotal(outflowTotal);
                }
            }

    }

    /**
     * 支流缺水计算方法（该节点上游均衡缺水率）
     */
    public void tributaryHydropeniaCalculation(int i,List<Node>upNode,double minInstreamingEcologicalFlow,double errorThreshold){
        int num=upNode.size();
        double satisfactionRateMax=1;//设置初始满足率最大值
        double satisfactionRateMin=0;//设置初始满足率最小值
        double satisfactionRate;//初始满足率
        do{
            satisfactionRate=(satisfactionRateMax+satisfactionRateMin)/2;
            for (int j = 0; j <num; j++){
                TimeBean data=upNode.get(j).getTimeData().get(i);
                data.setWaterRequirementPlan(data.getWaterRequirement()*satisfactionRate);//调整需水率
                double inflowTotal=this.districtCalculation(data).get(0);
                if(inflowTotal<data.getWaterRequirementPlan()){
                    data.setWaterRequirementPlan(inflowTotal);
                }
                double consumptionTotal=this.districtCalculation(data).get(1);
                double outflowTotal=this.districtCalculation(data).get(2);
                data.setInflowTotal(inflowTotal);
                data.setConsumptionTotal(consumptionTotal);
                data.setOutflowTotal(outflowTotal);
                if(j==num-1){
                    upNode.get(j).getDownNode().getTimeData().get(i).
                            setUpstreamInflowTotal1(outflowTotal);
                }
                else{
                    upNode.get(j).getDownNode().getTimeData().get(i).
                            setUpstreamInflowTotal(outflowTotal);
                }
            }
            //调整满足率机制
            if(upNode.get(num-1).getTimeData().get(i).getOutflowTotal()>minInstreamingEcologicalFlow){
                satisfactionRateMin=satisfactionRate;
            }
            else{
                satisfactionRateMax=satisfactionRate;
            }
        }while(Math.abs(upNode.get(num-1).getTimeData().get(i).getOutflowTotal()-minInstreamingEcologicalFlow)>errorThreshold);
    }

    /**
     * 干流缺水计算方法（还需计算节点上面的支流）
     */
    public void trunkStreamHydropeniaCalculation(int i,List<River>rivers,List<Double> levelStartlist, List<Double> levelEndlist,List<HydroStation> hydroStation,double minInstreamingEcologicalFlow,double errorThreshold){
        if(rivers.get(0).getNodes().get(rivers.get(0).getNodes().size()-1).getName()=="HMSK"){
            TimeBean data=rivers.get(0).getNodes().get(rivers.get(0).getNodes().size()-1).getTimeData().get(i);
            double inflowTotal=data.getUpstreamInflowTotal()+data.getSelfProducedInflowTotal();
            double capatityStart=hydroStation.get(0).getCurve().getCapacityByLevel(levelStartlist.get(0));
            double levelEnd=hydroStation.get(0).getCurve().getLevelByCapacity((inflowTotal-minInstreamingEcologicalFlow)*100+capatityStart);
            levelEndlist.set(0,levelEnd);
        }
        else if(rivers.get(0).getNodes().get(rivers.get(0).getNodes().size()-1).getName()=="LFSK"){
            TimeBean data=rivers.get(0).getNodes().get(rivers.get(0).getNodes().size()-1).getTimeData().get(i);
            double inflowTotal=data.getUpstreamInflowTotal()+data.getSelfProducedInflowTotal();
            double capatityStart=hydroStation.get(1).getCurve().getCapacityByLevel(levelStartlist.get(1));
            double levelEnd=hydroStation.get(1).getCurve().getLevelByCapacity((inflowTotal-minInstreamingEcologicalFlow)*100+capatityStart);
            levelEndlist.set(1,levelEnd);
        }
        else{
            int num=rivers.get(0).getNodes().size();//干流节点数
            double satisfactionRateMax=1;//设置初始满足率最大值
            double satisfactionRateMin=0;//设置初始满足率最小值
            double satisfactionRate;//初始满足率
            do {
                satisfactionRate=(satisfactionRateMax+satisfactionRateMin)/2;
                //先计算支流
                for (int k = 1; k <rivers.size() ; k++) {
                    for (int j = 0; j <rivers.get(k).getNodes().size(); j++){
                        TimeBean data=rivers.get(k).getNodes().get(j).getTimeData().get(i);
                        data.setWaterRequirementPlan(data.getWaterRequirement()*satisfactionRate);//调整需水率
                        double inflowTotal=this.districtCalculation(data).get(0);
                        if(inflowTotal<data.getWaterRequirementPlan()){
                            data.setWaterRequirementPlan(inflowTotal);
                        }
                        double consumptionTotal=this.districtCalculation(data).get(1);
                        double outflowTotal=this.districtCalculation(data).get(2);
                        data.setInflowTotal(inflowTotal);
                        data.setConsumptionTotal(consumptionTotal);
                        data.setOutflowTotal(outflowTotal);
                        if(j==rivers.get(k).getNodes().size()-1){
                            rivers.get(k).getNodes().get(j).getDownNode().getTimeData().get(i).
                                    setUpstreamInflowTotal1(outflowTotal);
                        }
                        else{
                            rivers.get(k).getNodes().get(j).getDownNode().getTimeData().get(i).
                                    setUpstreamInflowTotal(outflowTotal);
                        }
                    }
                }
                //干流中需水量改变的县
                for (int j = 0; j <num ; j++){
                    TimeBean data=rivers.get(0).getNodes().get(j).getTimeData().get(i);
                    if(rivers.get(0).getNodes().get(j).getType()==1){
                        data.setWaterRequirementPlan(data.getWaterRequirement()*satisfactionRate);
                    }
                }
                this.trunkStreamCalculation(rivers.get(0).getNodes(),i,levelStartlist,levelEndlist,hydroStation);
                //调整满足率机制
                if(rivers.get(0).getNodes().get(num-1).getTimeData().get(i).getOutflowTotal()>minInstreamingEcologicalFlow){
                    satisfactionRateMin=satisfactionRate;
                }
                else{
                    satisfactionRateMax=satisfactionRate;
                }
            }while(Math.abs(rivers.get(0).getNodes().get(num-1).getTimeData().get(i).getOutflowTotal()-minInstreamingEcologicalFlow)>errorThreshold);
        }

    }
}
