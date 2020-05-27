package waterDispatch.model;

import util.ExcelTool;
import waterDispatch.ConfluenceNode;
import waterDispatch.River;
import waterDispatch.TimeBean;
import waterDispatch.VirtualNode;
import waterDispatch.entity.InputEntity;
import waterDispatch.entity.ResultEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputLoad {

    public void resultSaving(List<River>rivers, ResultEntity resultEntity, InputEntity inputEntity){
        int timeLength=inputEntity.getTimeLength();
        List<Double[][]> dataList = new ArrayList<>();
        for (int i = 0; i <14 ; i++) {
            Double[][]data=new Double[100][timeLength];
            dataList.add(data);
        }
        Double[]HMSKlevel=new Double[timeLength+1];
        Double[]LFSKlevel=new Double[timeLength+1];


        for (int i = 0; i < rivers.get(1).getNodes().size();i++){
            for (int j = 0; j <timeLength; j++) {
                TimeBean timeBean=rivers.get(1).getNodes().get(i).getTimeData().get(j);

                dataList.get(i)[1][j]=timeBean.getInflowTotal();
                dataList.get(i)[2][j]=timeBean.getConsumptionTotal();
                dataList.get(i)[3][j]=timeBean.getWaterRequirement();
                dataList.get(i)[4][j]=timeBean.getConsumptionTotal()/
                        (timeBean.getAgriculturalWaterRatio()*timeBean.getAgriculturalWaterWeight()
                                +timeBean.getDomesticWaterRatio()*timeBean.getDomesticWaterWeight()
                                +timeBean.getForestryWaterRatio()*timeBean.getForestryWaterWeight()
                                +timeBean.getIndustrialWaterRatio()*timeBean.getIndustrialWaterWeight());
                dataList.get(i)[5][j]=timeBean.getOutflowTotal();
                dataList.get(i)[6][j]=(double)dataList.get(i)[4][j]/timeBean.getWaterRequirement();
                dataList.get(i)[7][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[8][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[9][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[10][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[11][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i)[12][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i)[13][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i)[14][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirementPlan();
            }
        }
        for (int i = 0; i < rivers.get(2).getNodes().size();i++){
            for (int j = 0; j <timeLength; j++) {
                TimeBean timeBean=rivers.get(2).getNodes().get(i).getTimeData().get(j);

                dataList.get(i+2)[1][j]=timeBean.getInflowTotal();
                dataList.get(i+2)[2][j]=timeBean.getConsumptionTotal();
                dataList.get(i+2)[3][j]=timeBean.getWaterRequirement();
                dataList.get(i+2)[4][j]=timeBean.getConsumptionTotal()/
                        (timeBean.getAgriculturalWaterRatio()*timeBean.getAgriculturalWaterWeight()
                                +timeBean.getDomesticWaterRatio()*timeBean.getDomesticWaterWeight()
                                +timeBean.getForestryWaterRatio()*timeBean.getForestryWaterWeight()
                                +timeBean.getIndustrialWaterRatio()*timeBean.getIndustrialWaterWeight());
                dataList.get(i+2)[5][j]=timeBean.getOutflowTotal();
                dataList.get(i+2)[6][j]=(double)dataList.get(i+2)[4][j]/timeBean.getWaterRequirement();
                dataList.get(i+2)[7][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[8][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[9][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[10][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[11][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i+2)[12][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i+2)[13][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i+2)[14][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirementPlan();
            }
        }
        for (int j = 0; j <timeLength; j++) {

            dataList.get(5)[1][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getInflowTotal();
            dataList.get(5)[2][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getConsumptionTotal();
            dataList.get(5)[3][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirement();
            dataList.get(5)[4][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(0).getTimeData().get(j).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getAgriculturalWaterWeight()
                            +rivers.get(0).getNodes().get(0).getTimeData().get(j).getDomesticWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getDomesticWaterWeight()
                            +rivers.get(0).getNodes().get(0).getTimeData().get(j).getForestryWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getForestryWaterWeight()
                            +rivers.get(0).getNodes().get(0).getTimeData().get(j).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getIndustrialWaterWeight());
            dataList.get(5)[5][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getOutflowTotal();
            dataList.get(5)[6][j]=(double)dataList.get(5)[4][j]/rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirement();
            dataList.get(5)[7][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirement();
            dataList.get(5)[8][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirement();
            dataList.get(5)[9][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirement();
            dataList.get(5)[10][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirement();
            dataList.get(5)[11][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(5)[12][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(5)[13][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(5)[14][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j).getWaterRequirementPlan();



            dataList.get(6)[1][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getInflowTotal();
            dataList.get(6)[2][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getConsumptionTotal();
            dataList.get(6)[3][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirement();
            dataList.get(6)[4][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(2).getTimeData().get(j).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getAgriculturalWaterWeight()
                            +rivers.get(0).getNodes().get(2).getTimeData().get(j).getDomesticWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getDomesticWaterWeight()
                            +rivers.get(0).getNodes().get(2).getTimeData().get(j).getForestryWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getForestryWaterWeight()
                            +rivers.get(0).getNodes().get(2).getTimeData().get(j).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getIndustrialWaterWeight());
            dataList.get(6)[5][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getOutflowTotal();
            dataList.get(6)[6][j]=(double)dataList.get(6)[4][j]/rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirement();
            dataList.get(6)[7][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirement();
            dataList.get(6)[8][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirement();
            dataList.get(6)[9][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirement();
            dataList.get(6)[10][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirement();
            dataList.get(6)[11][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(6)[12][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(6)[13][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(6)[14][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j).getWaterRequirementPlan();


            dataList.get(7)[1][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getInflowTotal();
            dataList.get(7)[2][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getConsumptionTotal();
            dataList.get(7)[3][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirement();
            dataList.get(7)[4][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(4).getTimeData().get(j).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getAgriculturalWaterWeight()
                            +rivers.get(0).getNodes().get(4).getTimeData().get(j).getDomesticWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getDomesticWaterWeight()
                            +rivers.get(0).getNodes().get(4).getTimeData().get(j).getForestryWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getForestryWaterWeight()
                            +rivers.get(0).getNodes().get(4).getTimeData().get(j).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getIndustrialWaterWeight());
            dataList.get(7)[5][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getOutflowTotal();
            dataList.get(7)[6][j]=(double)dataList.get(7)[4][j]/rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirement();
            dataList.get(7)[7][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirement();
            dataList.get(7)[8][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirement();
            dataList.get(7)[9][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirement();
            dataList.get(7)[10][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirement();
            dataList.get(7)[11][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(7)[12][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(7)[13][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(7)[14][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j).getWaterRequirementPlan();

            VirtualNode virtualNode=(VirtualNode) rivers.get(0).getNodes().get(5);
            ConfluenceNode JXConfluenceNode=virtualNode.getJXConfluenceNode();
            ConfluenceNode LCConfluenceNode=virtualNode.getLCConfluenceNode();

            dataList.get(8)[1][j]=JXConfluenceNode.getTimeData().get(j).getInflowTotal();
            dataList.get(8)[2][j]=JXConfluenceNode.getTimeData().get(j).getConsumptionTotal();
            dataList.get(8)[3][j]=JXConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(8)[4][j]= JXConfluenceNode.getTimeData().get(j).getConsumptionTotal()/
                    (JXConfluenceNode.getTimeData().get(j).getAgriculturalWaterRatio()*JXConfluenceNode.getTimeData().get(j).getAgriculturalWaterWeight()
                            +JXConfluenceNode.getTimeData().get(j).getDomesticWaterRatio()*JXConfluenceNode.getTimeData().get(j).getDomesticWaterWeight()
                            +JXConfluenceNode.getTimeData().get(j).getForestryWaterRatio()*JXConfluenceNode.getTimeData().get(j).getForestryWaterWeight()
                            +JXConfluenceNode.getTimeData().get(j).getIndustrialWaterRatio()*JXConfluenceNode.getTimeData().get(j).getIndustrialWaterWeight());
            dataList.get(8)[5][j]=JXConfluenceNode.getTimeData().get(j).getOutflowTotal();
            dataList.get(8)[6][j]=(double)dataList.get(8)[4][j]/JXConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(8)[7][j]=JXConfluenceNode.getTimeData().get(j).getAgriculturalWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(8)[8][j]=JXConfluenceNode.getTimeData().get(j).getIndustrialWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(8)[9][j]=JXConfluenceNode.getTimeData().get(j).getDomesticWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(8)[10][j]=JXConfluenceNode.getTimeData().get(j).getForestryWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(8)[11][j]=JXConfluenceNode.getTimeData().get(j).getAgriculturalWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(8)[12][j]=JXConfluenceNode.getTimeData().get(j).getIndustrialWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(8)[13][j]=JXConfluenceNode.getTimeData().get(j).getDomesticWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(8)[14][j]=JXConfluenceNode.getTimeData().get(j).getForestryWaterWeight()*JXConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();


            dataList.get(9)[1][j]=LCConfluenceNode.getTimeData().get(j).getInflowTotal();
            dataList.get(9)[2][j]=LCConfluenceNode.getTimeData().get(j).getConsumptionTotal();
            dataList.get(9)[3][j]=LCConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(9)[4][j]=LCConfluenceNode.getTimeData().get(j).getConsumptionTotal()/
                    (LCConfluenceNode.getTimeData().get(j).getAgriculturalWaterRatio()*LCConfluenceNode.getTimeData().get(j).getAgriculturalWaterWeight()
                            +LCConfluenceNode.getTimeData().get(j).getDomesticWaterRatio()*LCConfluenceNode.getTimeData().get(j).getDomesticWaterWeight()
                            +LCConfluenceNode.getTimeData().get(j).getForestryWaterRatio()*LCConfluenceNode.getTimeData().get(j).getForestryWaterWeight()
                            +LCConfluenceNode.getTimeData().get(j).getIndustrialWaterRatio()*LCConfluenceNode.getTimeData().get(j).getIndustrialWaterWeight());
            dataList.get(9)[5][j]=LCConfluenceNode.getTimeData().get(j).getOutflowTotal();
            dataList.get(9)[6][j]=(double)dataList.get(9)[4][j]/LCConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(9)[7][j]=LCConfluenceNode.getTimeData().get(j).getAgriculturalWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(9)[8][j]=LCConfluenceNode.getTimeData().get(j).getIndustrialWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(9)[9][j]=LCConfluenceNode.getTimeData().get(j).getDomesticWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(9)[10][j]=LCConfluenceNode.getTimeData().get(j).getForestryWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirement();
            dataList.get(9)[11][j]=LCConfluenceNode.getTimeData().get(j).getAgriculturalWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(9)[12][j]=LCConfluenceNode.getTimeData().get(j).getIndustrialWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(9)[13][j]=LCConfluenceNode.getTimeData().get(j).getDomesticWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(9)[14][j]=LCConfluenceNode.getTimeData().get(j).getForestryWaterWeight()*LCConfluenceNode.getTimeData().get(j).getWaterRequirementPlan();

            dataList.get(10)[1][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getInflowTotal();
            dataList.get(10)[2][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getConsumptionTotal();
            dataList.get(10)[3][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirement();
            dataList.get(10)[4][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(6).getTimeData().get(j).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getAgriculturalWaterWeight()
                            +rivers.get(0).getNodes().get(6).getTimeData().get(j).getDomesticWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getDomesticWaterWeight()
                            +rivers.get(0).getNodes().get(6).getTimeData().get(j).getForestryWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getForestryWaterWeight()
                            +rivers.get(0).getNodes().get(6).getTimeData().get(j).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getIndustrialWaterWeight());
            dataList.get(10)[5][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getOutflowTotal();
            dataList.get(10)[6][j]=(double)dataList.get(10)[4][j]/rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirement();
            dataList.get(10)[7][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirement();
            dataList.get(10)[8][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirement();
            dataList.get(10)[9][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirement();
            dataList.get(10)[10][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirement();
            dataList.get(10)[11][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(10)[12][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(10)[13][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(10)[14][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j).getWaterRequirementPlan();


            dataList.get(11)[1][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getInflowTotal();
            dataList.get(11)[2][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getConsumptionTotal();
            dataList.get(11)[3][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirement();
            dataList.get(11)[4][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(7).getTimeData().get(j).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getAgriculturalWaterWeight()
                            +rivers.get(0).getNodes().get(7).getTimeData().get(j).getDomesticWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getDomesticWaterWeight()
                            +rivers.get(0).getNodes().get(7).getTimeData().get(j).getForestryWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getForestryWaterWeight()
                            +rivers.get(0).getNodes().get(7).getTimeData().get(j).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getIndustrialWaterWeight());
            dataList.get(11)[5][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getOutflowTotal();
            dataList.get(11)[6][j]=(double)dataList.get(11)[4][j]/rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirement();
            dataList.get(11)[7][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirement();
            dataList.get(11)[8][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirement();
            dataList.get(11)[9][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirement();
            dataList.get(11)[10][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirement();
            dataList.get(11)[11][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(11)[12][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(11)[13][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getDomesticWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirementPlan();
            dataList.get(11)[14][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j).getForestryWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j).getWaterRequirementPlan();


            dataList.get(12)[1][j]=rivers.get(0).getNodes().get(1).getTimeData().get(j).getInflowTotal();
            dataList.get(12)[2][j]=rivers.get(0).getNodes().get(1).getTimeData().get(j).getOutflowTotal();


            dataList.get(13)[1][j]=rivers.get(0).getNodes().get(3).getTimeData().get(j).getInflowTotal();
            dataList.get(13)[2][j]=rivers.get(0).getNodes().get(3).getTimeData().get(j).getOutflowTotal();
        }

        HMSKlevel[0]=inputEntity.getHMstart();
        LFSKlevel[0]=inputEntity.getLFstart();
        for (int j = 0; j<timeLength; j++) {
            HMSKlevel[j+1]=rivers.get(0).getNodes().get(1).getTimeData().get(j).getLevelEnd();
            LFSKlevel[j+1]=rivers.get(0).getNodes().get(3).getTimeData().get(j).getLevelEnd();
        }
        
        
        for (int i = 0; i <12 ; i++) {
                resultEntity.getInflowTotal().add(i,dataList.get(i)[0]);
                resultEntity.getConsumptionTotal().add(i,dataList.get(i)[1]);
                resultEntity.getOutflowTotal().add(i,dataList.get(i)[2]);
                resultEntity.getWaterRequirement().add(i,dataList.get(i)[3]);
                resultEntity.getWaterRequirementPlan().add(i,dataList.get(i)[4]);
                resultEntity.getWaterRequirementFillRates().add(i,dataList.get(i)[5]);
                resultEntity.getIndustrialWaterRequirement().add(i,dataList.get(i)[6]);
                resultEntity.getAgriculturalWaterRequirement().add(i,dataList.get(i)[7]);
                resultEntity.getDomesticWaterRequirement().add(i,dataList.get(i)[8]);
                resultEntity.getForestryWaterRequirement().add(i,dataList.get(i)[9]);
                resultEntity.getIndustrialWaterRequirementPlan().add(i,dataList.get(i)[10]);
                resultEntity.getAgriculturalWaterRequirementPlan().add(i,dataList.get(i)[11]);
                resultEntity.getDomesticWaterRequirementPlan().add(i,dataList.get(i)[12]);
                resultEntity.getForestryWaterRequirementPlan().add(i,dataList.get(i)[13]);
        }
        
        
        
//        resultEntity.setGCXinflowTotal(dataList.get(0)[1]);
//        resultEntity.setGCXconsumptionTotal(dataList.get(0)[2]);
//        resultEntity.setGCXoutflowTotal(dataList.get(0)[3]);
//        resultEntity.setGCXwaterRequirement(dataList.get(0)[4]);
//        resultEntity.setGCXwaterRequirementPlan(dataList.get(0)[5]);
//        resultEntity.setGCXWaterRequirementFillRates(dataList.get(0)[6]);
//        resultEntity.setGCXindustrialWaterRequirement(dataList.get(0)[7]);
//        resultEntity.setGCXagriculturalWaterRequirement(dataList.get(0)[8]);
//        resultEntity.setGCXdomesticWaterRequirement(dataList.get(0)[9]);
//        resultEntity.setGCXforestryWaterRequirement(dataList.get(0)[10]);
//        resultEntity.setGCXindustrialWaterRequirementPlan(dataList.get(0)[11]);
//        resultEntity.setGCXagriculturalWaterRequirementPlan(dataList.get(0)[12]);
//        resultEntity.setGCXdomesticWaterRequirementPlan(dataList.get(0)[13]);
//        resultEntity.setGCXforestryWaterRequirementPlan(dataList.get(0)[14]);
//
//        resultEntity.setNFXinflowTotal(dataList.get(1)[1]);
//        resultEntity.setNFXconsumptionTotal(dataList.get(1)[2]);
//        resultEntity.setNFXoutflowTotal(dataList.get(1)[3]);
//        resultEntity.setNFXwaterRequirement(dataList.get(1)[4]);
//        resultEntity.setNFXwaterRequirementPlan(dataList.get(1)[5]);
//        resultEntity.setNFXWaterRequirementFillRates(dataList.get(1)[6]);
//        resultEntity.setNFXindustrialWaterRequirement(dataList.get(1)[7]);
//        resultEntity.setNFXagriculturalWaterRequirement(dataList.get(1)[8]);
//        resultEntity.setNFXdomesticWaterRequirement(dataList.get(1)[9]);
//        resultEntity.setNFXforestryWaterRequirement(dataList.get(1)[10]);
//        resultEntity.setNFXindustrialWaterRequirementPlan(dataList.get(1)[11]);
//        resultEntity.setNFXagriculturalWaterRequirementPlan(dataList.get(1)[12]);
//        resultEntity.setNFXdomesticWaterRequirementPlan(dataList.get(1)[13]);
//        resultEntity.setNFXforestryWaterRequirementPlan(dataList.get(1)[14]);
//
//        resultEntity.setLAXinflowTotal(dataList.get(2)[1]);
//        resultEntity.setLAXconsumptionTotal(dataList.get(2)[2]);
//        resultEntity.setLAXoutflowTotal(dataList.get(2)[3]);
//        resultEntity.setLAXwaterRequirement(dataList.get(2)[4]);
//        resultEntity.setLAXwaterRequirementPlan(dataList.get(2)[5]);
//        resultEntity.setLAXWaterRequirementFillRates(dataList.get(2)[6]);
//        resultEntity.setLAXindustrialWaterRequirement(dataList.get(2)[7]);
//        resultEntity.setLAXagriculturalWaterRequirement(dataList.get(2)[8]);
//        resultEntity.setLAXdomesticWaterRequirement(dataList.get(2)[9]);
//        resultEntity.setLAXforestryWaterRequirement(dataList.get(2)[10]);
//        resultEntity.setLAXindustrialWaterRequirementPlan(dataList.get(2)[11]);
//        resultEntity.setLAXagriculturalWaterRequirementPlan(dataList.get(2)[12]);
//        resultEntity.setLAXdomesticWaterRequirementPlan(dataList.get(2)[13]);
//        resultEntity.setLAXforestryWaterRequirementPlan(dataList.get(2)[14]);
//
//        resultEntity.setCRXinflowTotal(dataList.get(3)[1]);
//        resultEntity.setCRXconsumptionTotal(dataList.get(3)[2]);
//        resultEntity.setCRXoutflowTotal(dataList.get(3)[3]);
//        resultEntity.setCRXwaterRequirement(dataList.get(3)[4]);
//        resultEntity.setCRXwaterRequirementPlan(dataList.get(3)[5]);
//        resultEntity.setCRXWaterRequirementFillRates(dataList.get(3)[6]);
//        resultEntity.setCRXindustrialWaterRequirement(dataList.get(3)[7]);
//        resultEntity.setCRXagriculturalWaterRequirement(dataList.get(3)[8]);
//        resultEntity.setCRXdomesticWaterRequirement(dataList.get(3)[9]);
//        resultEntity.setCRXforestryWaterRequirement(dataList.get(3)[10]);
//        resultEntity.setCRXindustrialWaterRequirementPlan(dataList.get(3)[11]);
//        resultEntity.setCRXagriculturalWaterRequirementPlan(dataList.get(3)[12]);
//        resultEntity.setCRXdomesticWaterRequirementPlan(dataList.get(3)[13]);
//        resultEntity.setCRXforestryWaterRequirementPlan(dataList.get(3)[14]);
//
//        resultEntity.setYHXinflowTotal(dataList.get(4)[1]);
//        resultEntity.setYHXconsumptionTotal(dataList.get(4)[2]);
//        resultEntity.setYHXoutflowTotal(dataList.get(4)[3]);
//        resultEntity.setYHXwaterRequirement(dataList.get(4)[4]);
//        resultEntity.setYHXwaterRequirementPlan(dataList.get(4)[5]);
//        resultEntity.setYHXWaterRequirementFillRates(dataList.get(4)[6]);
//        resultEntity.setYHXindustrialWaterRequirement(dataList.get(4)[7]);
//        resultEntity.setYHXagriculturalWaterRequirement(dataList.get(4)[8]);
//        resultEntity.setYHXdomesticWaterRequirement(dataList.get(4)[9]);
//        resultEntity.setYHXforestryWaterRequirement(dataList.get(4)[10]);
//        resultEntity.setYHXindustrialWaterRequirementPlan(dataList.get(4)[11]);
//        resultEntity.setYHXagriculturalWaterRequirementPlan(dataList.get(4)[12]);
//        resultEntity.setYHXdomesticWaterRequirementPlan(dataList.get(4)[13]);
//        resultEntity.setYHXforestryWaterRequirementPlan(dataList.get(4)[14]);
//
//        resultEntity.setLCXinflowTotal(dataList.get(5)[1]);
//        resultEntity.setLCXconsumptionTotal(dataList.get(5)[2]);
//        resultEntity.setLCXoutflowTotal(dataList.get(5)[3]);
//        resultEntity.setLCXwaterRequirement(dataList.get(5)[4]);
//        resultEntity.setLCXwaterRequirementPlan(dataList.get(5)[5]);
//        resultEntity.setLCXWaterRequirementFillRates(dataList.get(5)[6]);
//        resultEntity.setLCXindustrialWaterRequirement(dataList.get(5)[7]);
//        resultEntity.setLCXagriculturalWaterRequirement(dataList.get(5)[8]);
//        resultEntity.setLCXdomesticWaterRequirement(dataList.get(5)[9]);
//        resultEntity.setLCXforestryWaterRequirement(dataList.get(5)[10]);
//        resultEntity.setLCXindustrialWaterRequirementPlan(dataList.get(5)[11]);
//        resultEntity.setLCXagriculturalWaterRequirementPlan(dataList.get(5)[12]);
//        resultEntity.setLCXdomesticWaterRequirementPlan(dataList.get(5)[13]);
//        resultEntity.setLCXforestryWaterRequirementPlan(dataList.get(5)[14]);
//
//        resultEntity.setNCXinflowTotal(dataList.get(6)[1]);
//        resultEntity.setNCXconsumptionTotal(dataList.get(6)[2]);
//        resultEntity.setNCXoutflowTotal(dataList.get(6)[3]);
//        resultEntity.setNCXwaterRequirement(dataList.get(6)[4]);
//        resultEntity.setNCXwaterRequirementPlan(dataList.get(6)[5]);
//        resultEntity.setNCXWaterRequirementFillRates(dataList.get(6)[6]);
//        resultEntity.setNCXindustrialWaterRequirement(dataList.get(6)[7]);
//        resultEntity.setNCXagriculturalWaterRequirement(dataList.get(6)[8]);
//        resultEntity.setNCXdomesticWaterRequirement(dataList.get(6)[9]);
//        resultEntity.setNCXforestryWaterRequirement(dataList.get(6)[10]);
//        resultEntity.setNCXindustrialWaterRequirementPlan(dataList.get(6)[11]);
//        resultEntity.setNCXagriculturalWaterRequirementPlan(dataList.get(6)[12]);
//        resultEntity.setNCXdomesticWaterRequirementPlan(dataList.get(6)[13]);
//        resultEntity.setNCXforestryWaterRequirementPlan(dataList.get(6)[14]);
//
//        resultEntity.setZXXinflowTotal(dataList.get(7)[1]);
//        resultEntity.setZXXconsumptionTotal(dataList.get(7)[2]);
//        resultEntity.setZXXoutflowTotal(dataList.get(7)[3]);
//        resultEntity.setZXXwaterRequirement(dataList.get(7)[4]);
//        resultEntity.setZXXwaterRequirementPlan(dataList.get(7)[5]);
//        resultEntity.setZXXWaterRequirementFillRates(dataList.get(7)[6]);
//        resultEntity.setZXXindustrialWaterRequirement(dataList.get(7)[7]);
//        resultEntity.setZXXagriculturalWaterRequirement(dataList.get(7)[8]);
//        resultEntity.setZXXdomesticWaterRequirement(dataList.get(7)[9]);
//        resultEntity.setZXXforestryWaterRequirement(dataList.get(7)[10]);
//        resultEntity.setZXXindustrialWaterRequirementPlan(dataList.get(7)[11]);
//        resultEntity.setZXXagriculturalWaterRequirementPlan(dataList.get(7)[12]);
//        resultEntity.setZXXdomesticWaterRequirementPlan(dataList.get(7)[13]);
//        resultEntity.setZXXforestryWaterRequirementPlan(dataList.get(7)[14]);
//
//        resultEntity.setJXXinflowTotal(dataList.get(8)[1]);
//        resultEntity.setJXXconsumptionTotal(dataList.get(8)[2]);
//        resultEntity.setJXXoutflowTotal(dataList.get(8)[3]);
//        resultEntity.setJXXwaterRequirement(dataList.get(8)[4]);
//        resultEntity.setJXXwaterRequirementPlan(dataList.get(8)[5]);
//        resultEntity.setJXXWaterRequirementFillRates(dataList.get(8)[6]);
//        resultEntity.setJXXindustrialWaterRequirement(dataList.get(8)[7]);
//        resultEntity.setJXXagriculturalWaterRequirement(dataList.get(8)[8]);
//        resultEntity.setJXXdomesticWaterRequirement(dataList.get(8)[9]);
//        resultEntity.setJXXforestryWaterRequirement(dataList.get(8)[10]);
//        resultEntity.setJXXindustrialWaterRequirementPlan(dataList.get(8)[11]);
//        resultEntity.setJXXagriculturalWaterRequirementPlan(dataList.get(8)[12]);
//        resultEntity.setJXXdomesticWaterRequirementPlan(dataList.get(8)[13]);
//        resultEntity.setJXXforestryWaterRequirementPlan(dataList.get(8)[14]);
//
//        resultEntity.setLCQinflowTotal(dataList.get(9)[1]);
//        resultEntity.setLCQconsumptionTotal(dataList.get(9)[2]);
//        resultEntity.setLCQoutflowTotal(dataList.get(9)[3]);
//        resultEntity.setLCQwaterRequirement(dataList.get(9)[4]);
//        resultEntity.setLCQwaterRequirementPlan(dataList.get(9)[5]);
//        resultEntity.setLCQWaterRequirementFillRates(dataList.get(9)[6]);
//        resultEntity.setLCQindustrialWaterRequirement(dataList.get(9)[7]);
//        resultEntity.setLCQagriculturalWaterRequirement(dataList.get(9)[8]);
//        resultEntity.setLCQdomesticWaterRequirement(dataList.get(9)[9]);
//        resultEntity.setLCQforestryWaterRequirement(dataList.get(9)[10]);
//        resultEntity.setLCQindustrialWaterRequirementPlan(dataList.get(9)[11]);
//        resultEntity.setLCQagriculturalWaterRequirementPlan(dataList.get(9)[12]);
//        resultEntity.setLCQdomesticWaterRequirementPlan(dataList.get(9)[13]);
//        resultEntity.setLCQforestryWaterRequirementPlan(dataList.get(9)[14]);
//
//        resultEntity.setDXXinflowTotal(dataList.get(10)[1]);
//        resultEntity.setDXXconsumptionTotal(dataList.get(10)[2]);
//        resultEntity.setDXXoutflowTotal(dataList.get(10)[3]);
//        resultEntity.setDXXwaterRequirement(dataList.get(10)[4]);
//        resultEntity.setDXXwaterRequirementPlan(dataList.get(10)[5]);
//        resultEntity.setDXXWaterRequirementFillRates(dataList.get(10)[6]);
//        resultEntity.setDXXindustrialWaterRequirement(dataList.get(10)[7]);
//        resultEntity.setDXXagriculturalWaterRequirement(dataList.get(10)[8]);
//        resultEntity.setDXXdomesticWaterRequirement(dataList.get(10)[9]);
//        resultEntity.setDXXforestryWaterRequirement(dataList.get(10)[10]);
//        resultEntity.setDXXindustrialWaterRequirementPlan(dataList.get(10)[11]);
//        resultEntity.setDXXagriculturalWaterRequirementPlan(dataList.get(10)[12]);
//        resultEntity.setDXXdomesticWaterRequirementPlan(dataList.get(10)[13]);
//        resultEntity.setDXXforestryWaterRequirementPlan(dataList.get(10)[14]);
//
//        resultEntity.setGFPYinflowTotal(dataList.get(11)[1]);
//        resultEntity.setGFPYconsumptionTotal(dataList.get(11)[2]);
//        resultEntity.setGFPYoutflowTotal(dataList.get(11)[3]);
//        resultEntity.setGFPYwaterRequirement(dataList.get(11)[4]);
//        resultEntity.setGFPYwaterRequirementPlan(dataList.get(11)[5]);
//        resultEntity.setGFPYWaterRequirementFillRates(dataList.get(11)[6]);
//        resultEntity.setGFPYindustrialWaterRequirement(dataList.get(11)[7]);
//        resultEntity.setGFPYagriculturalWaterRequirement(dataList.get(11)[8]);
//        resultEntity.setGFPYdomesticWaterRequirement(dataList.get(11)[9]);
//        resultEntity.setGFPYforestryWaterRequirement(dataList.get(11)[10]);
//        resultEntity.setGFPYindustrialWaterRequirementPlan(dataList.get(11)[11]);
//        resultEntity.setGFPYagriculturalWaterRequirementPlan(dataList.get(11)[12]);
//        resultEntity.setGFPYdomesticWaterRequirementPlan(dataList.get(11)[13]);
//        resultEntity.setGFPYforestryWaterRequirementPlan(dataList.get(11)[14]);

        resultEntity.setHMSKinflowTotal(dataList.get(12)[1]);
        resultEntity.setHMSKoutflowTotal(dataList.get(12)[2]);

        resultEntity.setLFSKinflowTotal(dataList.get(13)[1]);
        resultEntity.setLFSKoutflowTotal(dataList.get(13)[2]);

        resultEntity.setHMSKlevel(HMSKlevel);
        resultEntity.setLFSKlevel(LFSKlevel);
    }




    public void loadingExcel(List<River> rivers, int timeLength) throws IOException {
        String[] path ={System.getProperty("user.dir") + "/src/main/resources/result/GCX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/NFX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/LAX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/CRX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/YHX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/LCX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/NCX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/ZXX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/LCQ.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/JXX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/DXX.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/GFPY.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/HMSK.xlsx",
                System.getProperty("user.dir") + "/src/main/resources/result/LFSK.xlsx",
        };
        String sheet = "Sheet1";
        List<Object[][]> dataList = new ArrayList<>();
        for (int i = 0; i < path .length ; i++) {
            Object[][]data=new Object[100][timeLength+1];
            dataList.add(data);
        }
        for (int i = 0; i < path .length-2; i++) {
            dataList.get(i)[0][0]="";
            dataList.get(i)[1][0]="总来水量";
            dataList.get(i)[2][0]="总用水量";
            dataList.get(i)[3][0]="总需水量";
            dataList.get(i)[4][0]="计划需水量";
            dataList.get(i)[5][0]="总泄水量";
            dataList.get(i)[6][0]="用水满足率";
            dataList.get(i)[7][0]="农业需水量";
            dataList.get(i)[8][0]="工业需水量";
            dataList.get(i)[9][0]="生活需水量";
            dataList.get(i)[10][0]="林牧渔需水量";
            dataList.get(i)[11][0]="农业计划需水量";
            dataList.get(i)[12][0]="工业计划需水量";
            dataList.get(i)[13][0]="生活计划需水量";
            dataList.get(i)[14][0]="林牧渔计划需水量";
        }
        for (int i = 0; i <2; i++) {
            dataList.get(i+12)[0][0]="";
            dataList.get(i+12)[1][0]="总来水量";
            dataList.get(i+12)[2][0]="总泄水量";
            dataList.get(i+12)[3][0]="水位";
        }

        for (int i = 0; i < rivers.get(1).getNodes().size();i++){
            for (int j = 1; j <timeLength+1; j++) {
                TimeBean timeBean=rivers.get(1).getNodes().get(i).getTimeData().get(j-1);
                dataList.get(i)[0][j]=j;
                dataList.get(i)[1][j]=timeBean.getInflowTotal();
                dataList.get(i)[2][j]=timeBean.getConsumptionTotal();
                dataList.get(i)[3][j]=timeBean.getWaterRequirement();
                dataList.get(i)[4][j]=timeBean.getConsumptionTotal()/
                        (timeBean.getAgriculturalWaterRatio()*timeBean.getAgriculturalWaterWeight()
                        +timeBean.getDomesticWaterRatio()*timeBean.getDomesticWaterWeight()
                        +timeBean.getForestryWaterRatio()*timeBean.getForestryWaterWeight()
                        +timeBean.getIndustrialWaterRatio()*timeBean.getIndustrialWaterWeight());
                dataList.get(i)[5][j]=timeBean.getOutflowTotal();
                dataList.get(i)[6][j]=(double)dataList.get(i)[4][j]/timeBean.getWaterRequirement();
                dataList.get(i)[7][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[8][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[9][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[10][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i)[11][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i)[12][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i)[13][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i)[14][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirementPlan();
            }
        }
        for (int i = 0; i < rivers.get(2).getNodes().size();i++){
            for (int j = 1; j <timeLength+1; j++) {
                TimeBean timeBean=rivers.get(2).getNodes().get(i).getTimeData().get(j-1);
                dataList.get(i+2)[0][j]=j;
                dataList.get(i+2)[1][j]=timeBean.getInflowTotal();
                dataList.get(i+2)[2][j]=timeBean.getConsumptionTotal();
                dataList.get(i+2)[3][j]=timeBean.getWaterRequirement();
                dataList.get(i+2)[4][j]=timeBean.getConsumptionTotal()/
                        (timeBean.getAgriculturalWaterRatio()*timeBean.getAgriculturalWaterWeight()
                                +timeBean.getDomesticWaterRatio()*timeBean.getDomesticWaterWeight()
                                +timeBean.getForestryWaterRatio()*timeBean.getForestryWaterWeight()
                                +timeBean.getIndustrialWaterRatio()*timeBean.getIndustrialWaterWeight());
                dataList.get(i+2)[5][j]=timeBean.getOutflowTotal();
                dataList.get(i+2)[6][j]=(double)dataList.get(i+2)[4][j]/timeBean.getWaterRequirement();
                dataList.get(i+2)[7][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[8][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[9][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[10][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirement();
                dataList.get(i+2)[11][j]=timeBean.getAgriculturalWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i+2)[12][j]=timeBean.getIndustrialWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i+2)[13][j]=timeBean.getDomesticWaterWeight()*timeBean.getWaterRequirementPlan();
                dataList.get(i+2)[14][j]=timeBean.getForestryWaterWeight()*timeBean.getWaterRequirementPlan();
            }
        }
        for (int j = 1; j <timeLength+1; j++) {
            dataList.get(5)[0][j]=j;
            dataList.get(5)[1][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getInflowTotal();
            dataList.get(5)[2][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getConsumptionTotal();
            dataList.get(5)[3][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(5)[4][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getConsumptionTotal()/
                (rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getAgriculturalWaterWeight()
                +rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getDomesticWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getDomesticWaterWeight()
                +rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getForestryWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getForestryWaterWeight()
                +rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getIndustrialWaterWeight());
            dataList.get(5)[5][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getOutflowTotal();
            dataList.get(5)[6][j]=(double)dataList.get(5)[4][j]/rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(5)[7][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(5)[8][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(5)[9][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(5)[10][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(5)[11][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(5)[12][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(5)[13][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(5)[14][j]=rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(0).getTimeData().get(j-1).getWaterRequirementPlan();


            dataList.get(6)[0][j]=j;
            dataList.get(6)[1][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getInflowTotal();
            dataList.get(6)[2][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getConsumptionTotal();
            dataList.get(6)[3][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(6)[4][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getAgriculturalWaterWeight()
                    +rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getDomesticWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getDomesticWaterWeight()
                    +rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getForestryWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getForestryWaterWeight()
                    +rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getIndustrialWaterWeight());
            dataList.get(6)[5][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getOutflowTotal();
            dataList.get(6)[6][j]=(double)dataList.get(6)[4][j]/rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(6)[7][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(6)[8][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(6)[9][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(6)[10][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(6)[11][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(6)[12][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(6)[13][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(6)[14][j]=rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(2).getTimeData().get(j-1).getWaterRequirementPlan();

            dataList.get(7)[0][j]=j;
            dataList.get(7)[1][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getInflowTotal();
            dataList.get(7)[2][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getConsumptionTotal();
            dataList.get(7)[3][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(7)[4][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getAgriculturalWaterWeight()
                    +rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getDomesticWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getDomesticWaterWeight()
                    +rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getForestryWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getForestryWaterWeight()
                    +rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getIndustrialWaterWeight());
            dataList.get(7)[5][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getOutflowTotal();
            dataList.get(7)[6][j]=(double)dataList.get(7)[4][j]/rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(7)[7][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(7)[8][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(7)[9][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(7)[10][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(7)[11][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(7)[12][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(7)[13][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(7)[14][j]=rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(4).getTimeData().get(j-1).getWaterRequirementPlan();

            VirtualNode virtualNode=(VirtualNode) rivers.get(0).getNodes().get(5);
            ConfluenceNode JXConfluenceNode=virtualNode.getJXConfluenceNode();
            ConfluenceNode LCConfluenceNode=virtualNode.getLCConfluenceNode();
            dataList.get(8)[0][j]=j;
            dataList.get(8)[1][j]=LCConfluenceNode.getTimeData().get(j-1).getInflowTotal();
            dataList.get(8)[2][j]=LCConfluenceNode.getTimeData().get(j-1).getConsumptionTotal();
            dataList.get(8)[3][j]=LCConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(8)[4][j]= LCConfluenceNode.getTimeData().get(j-1).getConsumptionTotal()/
                    (LCConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterRatio()*LCConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterWeight()
                    +LCConfluenceNode.getTimeData().get(j-1).getDomesticWaterRatio()*LCConfluenceNode.getTimeData().get(j-1).getDomesticWaterWeight()
                    +LCConfluenceNode.getTimeData().get(j-1).getForestryWaterRatio()*LCConfluenceNode.getTimeData().get(j-1).getForestryWaterWeight()
                    +LCConfluenceNode.getTimeData().get(j-1).getIndustrialWaterRatio()*LCConfluenceNode.getTimeData().get(j-1).getIndustrialWaterWeight());
            dataList.get(8)[5][j]=LCConfluenceNode.getTimeData().get(j-1).getOutflowTotal();
            dataList.get(8)[6][j]=(double)dataList.get(8)[4][j]/LCConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(8)[7][j]=LCConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(8)[8][j]=LCConfluenceNode.getTimeData().get(j-1).getIndustrialWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(8)[9][j]=LCConfluenceNode.getTimeData().get(j-1).getDomesticWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(8)[10][j]=LCConfluenceNode.getTimeData().get(j-1).getForestryWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(8)[11][j]=LCConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(8)[12][j]=LCConfluenceNode.getTimeData().get(j-1).getIndustrialWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(8)[13][j]=LCConfluenceNode.getTimeData().get(j-1).getDomesticWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(8)[14][j]=LCConfluenceNode.getTimeData().get(j-1).getForestryWaterWeight()*LCConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();



            dataList.get(9)[0][j]=j;
            dataList.get(9)[1][j]=JXConfluenceNode.getTimeData().get(j-1).getInflowTotal();
            dataList.get(9)[2][j]=JXConfluenceNode.getTimeData().get(j-1).getConsumptionTotal();
            dataList.get(9)[3][j]=JXConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(9)[4][j]=JXConfluenceNode.getTimeData().get(j-1).getConsumptionTotal()/
                    (JXConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterRatio()*JXConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterWeight()
                    +JXConfluenceNode.getTimeData().get(j-1).getDomesticWaterRatio()*JXConfluenceNode.getTimeData().get(j-1).getDomesticWaterWeight()
                    +JXConfluenceNode.getTimeData().get(j-1).getForestryWaterRatio()*JXConfluenceNode.getTimeData().get(j-1).getForestryWaterWeight()
                    +JXConfluenceNode.getTimeData().get(j-1).getIndustrialWaterRatio()*JXConfluenceNode.getTimeData().get(j-1).getIndustrialWaterWeight());
            dataList.get(9)[5][j]=JXConfluenceNode.getTimeData().get(j-1).getOutflowTotal();
            dataList.get(9)[6][j]=(double)dataList.get(9)[4][j]/JXConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(9)[7][j]=JXConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(9)[8][j]=JXConfluenceNode.getTimeData().get(j-1).getIndustrialWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(9)[9][j]=JXConfluenceNode.getTimeData().get(j-1).getDomesticWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(9)[10][j]=JXConfluenceNode.getTimeData().get(j-1).getForestryWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirement();
            dataList.get(9)[11][j]=JXConfluenceNode.getTimeData().get(j-1).getAgriculturalWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(9)[12][j]=JXConfluenceNode.getTimeData().get(j-1).getIndustrialWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(9)[13][j]=JXConfluenceNode.getTimeData().get(j-1).getDomesticWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(9)[14][j]=JXConfluenceNode.getTimeData().get(j-1).getForestryWaterWeight()*JXConfluenceNode.getTimeData().get(j-1).getWaterRequirementPlan();

            dataList.get(10)[0][j]=j;
            dataList.get(10)[1][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getInflowTotal();
            dataList.get(10)[2][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getConsumptionTotal();
            dataList.get(10)[3][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(10)[4][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getAgriculturalWaterWeight()
                    +rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getDomesticWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getDomesticWaterWeight()
                    +rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getForestryWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getForestryWaterWeight()
                    +rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getIndustrialWaterWeight());
            dataList.get(10)[5][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getOutflowTotal();
            dataList.get(10)[6][j]=(double)dataList.get(10)[4][j]/rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(10)[7][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(10)[8][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(10)[9][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(10)[10][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(10)[11][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(10)[12][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(10)[13][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(10)[14][j]=rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(6).getTimeData().get(j-1).getWaterRequirementPlan();

            dataList.get(11)[0][j]=j;
            dataList.get(11)[1][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getInflowTotal();
            dataList.get(11)[2][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getConsumptionTotal();
            dataList.get(11)[3][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(11)[4][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getConsumptionTotal()/
                    (rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getAgriculturalWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getAgriculturalWaterWeight()
                    +rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getDomesticWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getDomesticWaterWeight()
                    +rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getForestryWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getForestryWaterWeight()
                    +rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getIndustrialWaterRatio()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getIndustrialWaterWeight());
            dataList.get(11)[5][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getOutflowTotal();
            dataList.get(11)[6][j]=(double)dataList.get(11)[4][j]/rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(11)[7][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(11)[8][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(11)[9][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(11)[10][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirement();
            dataList.get(11)[11][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getAgriculturalWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(11)[12][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getIndustrialWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(11)[13][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getDomesticWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirementPlan();
            dataList.get(11)[14][j]=rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getForestryWaterWeight()*rivers.get(0).getNodes().get(7).getTimeData().get(j-1).getWaterRequirementPlan();

            dataList.get(12)[0][j]=j;
            dataList.get(12)[1][j]=rivers.get(0).getNodes().get(1).getTimeData().get(j-1).getInflowTotal();
            dataList.get(12)[2][j]=rivers.get(0).getNodes().get(1).getTimeData().get(j-1).getOutflowTotal();
            dataList.get(12)[3][j]=rivers.get(0).getNodes().get(1).getTimeData().get(j-1).getLevelEnd();

            dataList.get(13)[0][j]=j;
            dataList.get(13)[1][j]=rivers.get(0).getNodes().get(3).getTimeData().get(j-1).getInflowTotal();
            dataList.get(13)[2][j]=rivers.get(0).getNodes().get(3).getTimeData().get(j-1).getOutflowTotal();
            dataList.get(13)[3][j]=rivers.get(0).getNodes().get(3).getTimeData().get(j-1).getLevelEnd();
        }

        for (int i = 0; i < path.length; i++) {
            ExcelTool.write07Excel(path[i], sheet,dataList.get(i));
        }
    }


    public void showResult(List<River>rivers,int timeLength){
        for (int i = 0; i <rivers.size() ; i++) {
            for (int j = 0; j < rivers.get(i).getNodes().size(); j++) {
                if (rivers.get(i).getNodes().get(j).getType() != 3) {
                    System.out.println(rivers.get(i).getNodes().get(j).getName());
                    for (int t = 0; t < timeLength; t++) {
                        System.out.print(rivers.get(i).getNodes().get(j).getTimeData().get(t).getInflowTotal());
                        System.out.println();
                        System.out.print(rivers.get(i).getNodes().get(j).getTimeData().get(t).getConsumptionTotal());
                        System.out.println();
                        System.out.print(rivers.get(i).getNodes().get(j).getTimeData().get(t).getOutflowTotal());
                        System.out.println();
                    }

                }
                if (rivers.get(i).getNodes().get(j).getType() == 3) {
                    VirtualNode virtualNode = (VirtualNode) rivers.get(i).getNodes().get(j);
                    System.out.println(virtualNode.getJXConfluenceNode().getName());
                    for (int t = 0; t < timeLength; t++) {
                        virtualNode.getJXConfluenceNode().getTimeData().get(t);
                    }
                    System.out.println(virtualNode.getLCConfluenceNode().getName());
                    for (int t = 0; t < timeLength; t++) {
                        virtualNode.getLCConfluenceNode().getTimeData().get(t);
                    }
                }
            }
        }
        for (int i = 0; i <rivers.size() ; i++) {
            for (int j = 0; j < rivers.get(i).getNodes().size(); j++){
                if(rivers.get(i).getNodes().get(j).getName()=="HMSK"){
                    System.out.println(rivers.get(i).getNodes().get(j).getName());
                    for (int t = 0; t <timeLength ; t++){
                        System.out.println(rivers.get(i).getNodes().get(j).getTimeData().get(t).getLevelEnd());
                    }
                }
                if(rivers.get(i).getNodes().get(j).getName()=="LFSK"){
                    System.out.println(rivers.get(i).getNodes().get(j).getName());
                    for (int t = 0; t <timeLength ; t++){
                        System.out.println(rivers.get(i).getNodes().get(j).getTimeData().get(t).getLevelEnd());
                    }
                }
            }
        }
    }
}
