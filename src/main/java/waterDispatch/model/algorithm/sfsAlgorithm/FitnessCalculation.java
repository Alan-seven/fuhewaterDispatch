package waterDispatch.model.algorithm.sfsAlgorithm;

import waterDispatch.ConfluenceNode;
import waterDispatch.River;
import waterDispatch.VirtualNode;
import waterDispatch.model.WaterDistribution;
import java.util.List;

public class FitnessCalculation {
    /**
     * 适应度值计算
     */
    public double getFitness(double errorThreshold,List<List<Double>>levels,List<River>rivers){
        double fitness;
        int i=levels.get(0).size()-1;
        WaterDistribution waterDistribution=new WaterDistribution();
        waterDistribution.distributionCalculation(errorThreshold,levels,rivers);
//        this.show(rivers);
        double RequireWater=this.getRequireWater(rivers,i);
        double RequireWaterPlan=this.getRequireWaterPlan(rivers,i);
        fitness=1-RequireWaterPlan/RequireWater;
        this.rebuildRequireWaterPlan(rivers,i);
        return fitness;
    }

    /**
     * 需水量计算
     */
    public double getRequireWater(List<River>rivers,int t){
        double requireWater=0;
        for (int l = 0; l <t ; l++) {
            for (int i = 0; i <rivers.size() ; i++) {
                for (int j = 0; j <rivers.get(i).getNodes().size(); j++) {
                    if(rivers.get(i).getNodes().get(j).getType()==1||rivers.get(i).getNodes().get(j).getType()==4){
                        requireWater=requireWater+rivers.get(i).getNodes().get(j).getTimeData().get(l).getWaterRequirement();
                    }
                    if(rivers.get(i).getNodes().get(j).getType()==3){
                        VirtualNode virtualNode =(VirtualNode)rivers.get(i).getNodes().get(j);
                        ConfluenceNode JXConfluenceNode=virtualNode.getJXConfluenceNode();
                        ConfluenceNode LCConfluenceNode=virtualNode.getLCConfluenceNode();
                        requireWater=requireWater+JXConfluenceNode.getTimeData().get(l).getWaterRequirement()+LCConfluenceNode.getTimeData().get(l).getWaterRequirement();
                    }
                }
            }
        }
        return requireWater;
    }



    /**
     * 计划需水量计算
     */
    public double getRequireWaterPlan(List<River>rivers,int t){
        double requireWaterPlan=0;
        for (int l = 0; l <t ; l++) {
            for (int i = 0; i <rivers.size() ; i++) {
                for (int j = 0; j <rivers.get(i).getNodes().size(); j++) {
                    if(rivers.get(i).getNodes().get(j).getType()==1||rivers.get(i).getNodes().get(j).getType()==4){
                        requireWaterPlan=requireWaterPlan+rivers.get(i).getNodes().get(j).getTimeData().get(l).getWaterRequirementPlan();
                    }
                    if(rivers.get(i).getNodes().get(j).getType()==3){
                        VirtualNode virtualNode =(VirtualNode)rivers.get(i).getNodes().get(j);
                        ConfluenceNode JXConfluenceNode=virtualNode.getJXConfluenceNode();
                        ConfluenceNode LCConfluenceNode=virtualNode.getLCConfluenceNode();
                        requireWaterPlan=requireWaterPlan+JXConfluenceNode.getTimeData().get(l).getWaterRequirementPlan()+LCConfluenceNode.getTimeData().get(l).getWaterRequirementPlan();
                    }
                }
            }
        }
        return requireWaterPlan;
    }

    /**
     * 需水量计算
     */
    public void rebuildRequireWaterPlan(List<River>rivers,int t){
        for (int l = 0; l <t ; l++) {
            for (int i = 0; i <rivers.size() ; i++) {
                for (int j = 0; j <rivers.get(i).getNodes().size(); j++) {
                    if(rivers.get(i).getNodes().get(j).getType()==1||rivers.get(i).getNodes().get(j).getType()==4){
                        rivers.get(i).getNodes().get(j).getTimeData().get(l).setWaterRequirementPlan(rivers.get(i).getNodes().get(j).getTimeData().get(l).getWaterRequirement());
                    }
                    if(rivers.get(i).getNodes().get(j).getType()==3){
                        VirtualNode virtualNode =(VirtualNode)rivers.get(i).getNodes().get(j);
                        ConfluenceNode JXConfluenceNode=virtualNode.getJXConfluenceNode();
                        ConfluenceNode LCConfluenceNode=virtualNode.getLCConfluenceNode();
                        JXConfluenceNode.getTimeData().get(l).setWaterRequirementPlan(JXConfluenceNode.getTimeData().get(l).getWaterRequirement());
                        LCConfluenceNode.getTimeData().get(l).setWaterRequirementPlan(LCConfluenceNode.getTimeData().get(l).getWaterRequirement());
                    }
                }
            }
        }
    }


    public void show(List<River>rivers){
        for (int i = 0; i <rivers.size() ; i++) {
            System.out.println("第"+i+"条河流");
            for (int j = 0; j <rivers.get(i).getNodes().size() ; j++) {
                for (int k = 0; k<rivers.get(i).getNodes().get(j).getTimeData().size() ; k++) {
                    if(rivers.get(i).getNodes().get(j).getType()!=3){
                        System.out.println(rivers.get(i).getNodes().get(j).getTimeData().get(k).getInflowTotal());
                        System.out.println(rivers.get(i).getNodes().get(j).getTimeData().get(k).getConsumptionTotal());
                        System.out.println(rivers.get(i).getNodes().get(j).getTimeData().get(k).getOutflowTotal());
                        System.out.println(rivers.get(i).getNodes().get(j).getTimeData().get(k).getWaterRequirement());
                        System.out.println(rivers.get(i).getNodes().get(j).getTimeData().get(k).getWaterRequirementPlan());
                    }
                    else{
                        VirtualNode virtualNode =(VirtualNode)rivers.get(i).getNodes().get(j);
                        ConfluenceNode JXConfluenceNode=virtualNode.getJXConfluenceNode();
                        ConfluenceNode LCConfluenceNode=virtualNode.getLCConfluenceNode();
                        System.out.println(JXConfluenceNode.getTimeData().get(k).getInflowTotal());
                        System.out.println(JXConfluenceNode.getTimeData().get(k).getConsumptionTotal());
                        System.out.println(JXConfluenceNode.getTimeData().get(k).getOutflowTotal());
                        System.out.println(JXConfluenceNode.getTimeData().get(k).getWaterRequirement());
                        System.out.println(JXConfluenceNode.getTimeData().get(k).getWaterRequirementPlan());

                        System.out.println(LCConfluenceNode.getTimeData().get(k).getInflowTotal());
                        System.out.println(LCConfluenceNode.getTimeData().get(k).getConsumptionTotal());
                        System.out.println(LCConfluenceNode.getTimeData().get(k).getOutflowTotal());
                        System.out.println(LCConfluenceNode.getTimeData().get(k).getWaterRequirement());
                        System.out.println(LCConfluenceNode.getTimeData().get(k).getWaterRequirementPlan());
                    }
                }
            }
        }
    }
}
