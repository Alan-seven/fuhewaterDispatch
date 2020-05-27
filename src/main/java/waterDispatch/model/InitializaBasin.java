package waterDispatch.model;
import util.DoubleCurve;
import util.ExcelTool;
import waterDispatch.*;
import waterDispatch.entity.InputEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitializaBasin {
    //初始化河流及节点,从前端界面传参
    public void initializationByInterface(List<River>rivers,InputEntity inputEntity) throws IOException {
        int timeLength=inputEntity.getTimeLength();
        District LCDistrict=new District(timeLength);
        HydroStation HMHydroStation=new HydroStation(timeLength);
        ConfluenceNode NCConfluenceNode=new ConfluenceNode(timeLength);
        HydroStation LFHydroStation=new HydroStation(timeLength);
        District ZXDistrict=new District(timeLength);
        VirtualNode virtualNode=new VirtualNode(timeLength);
        District DXDistrict=new District(timeLength);
        District GFPYDistrict=new District(timeLength);

        District GCDistrict=new District(timeLength);
        District NFDistrict=new District(timeLength);

        District LADistrict=new District(timeLength);
        District CRDistrict=new District(timeLength);
        District YHDistrict=new District(timeLength);


        // *******************************初始化电站曲线数据**********************************************
        //电站曲线
        String path = System.getProperty("user.dir") + "/src/main/resources/levelCapacity.xlsx";
        String[] sheet = new String[]{"HMSK", "LFSK"};
        List<Object[][]> stationCurve = new ArrayList<>();
        for (int i = 0; i < sheet.length; i++) {
            stationCurve.add(ExcelTool.read07Excel(path, sheet[i]));
        }
        double[][] HMlevelCapacity = new double[stationCurve.get(0).length][2];
        double[][] LFlevelCapacity = new double[stationCurve.get(1).length][2];
        for (int i = 0; i < stationCurve.get(0).length; i++) {
            for (int j = 0; j < stationCurve.get(0)[i].length; j++) {
                HMlevelCapacity[i][j] = (double) stationCurve.get(0)[i][j];
            }
        }
        for (int i = 0; i < stationCurve.get(1).length; i++) {
            for (int j = 0; j < stationCurve.get(1)[i].length; j++) {
                LFlevelCapacity[i][j] = (double) stationCurve.get(1)[i][j];
            }
        }
        DoubleCurve HMlevelCapacityCurve = new DoubleCurve(HMlevelCapacity);
        DoubleCurve LFlevelCapacityCurve = new DoubleCurve(LFlevelCapacity);
        HMHydroStation.getCurve().setLevelCapacityCurve(HMlevelCapacityCurve);
        LFHydroStation.getCurve().setLevelCapacityCurve(LFlevelCapacityCurve);
        HMHydroStation.setLevelNormal(100);
        HMHydroStation.setLevelDead(92);
        LFHydroStation.setLevelNormal(65);
        LFHydroStation.setLevelDead(61);

// *******************************初始化节点数据**********************************************
        for (int i = 0; i <timeLength; i++) {
            GCDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(0)[i]);
            GCDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(0)[i]);
            GCDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(0));
            GCDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(0));
            GCDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(0));
            GCDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(0));
            GCDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(0)[i]);
            GCDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(0)*inputEntity.getIndustrialWaterWeight().get(0)*inputEntity.getIndustrialWaterRatio().get(0)[i] +
                    inputEntity.getWaterRequirement().get(0)*inputEntity.getAgriculturalWaterWeight().get(0)*inputEntity.getAgriculturalWaterRatio().get(0)[i]+
                    inputEntity.getWaterRequirement().get(0)*inputEntity.getDomesticWaterWeight().get(0)*inputEntity.getDomesticWaterRatio().get(0)[i]+
                    inputEntity.getWaterRequirement().get(0)*inputEntity.getForestryWaterWeight().get(0)*inputEntity.getForestryWaterRatio().get(0)[i]);
            GCDistrict.getTimeData().get(i).setWaterRequirementPlan(GCDistrict.getTimeData().get(i).getWaterRequirement());


            NFDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(1)[i]);
            NFDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(1)[i]);
            NFDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(1));
            NFDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(1));
            NFDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(1));
            NFDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(1));
            NFDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(1)[i]);
            NFDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(1)*inputEntity.getIndustrialWaterWeight().get(1)*inputEntity.getIndustrialWaterRatio().get(1)[i] +
                            inputEntity.getWaterRequirement().get(1)*inputEntity.getAgriculturalWaterWeight().get(1)*inputEntity.getAgriculturalWaterRatio().get(1)[i]+
                            inputEntity.getWaterRequirement().get(1)*inputEntity.getDomesticWaterWeight().get(1)*inputEntity.getDomesticWaterRatio().get(1)[i]+
                            inputEntity.getWaterRequirement().get(1)*inputEntity.getForestryWaterWeight().get(1)*inputEntity.getForestryWaterRatio().get(1)[i]);
            NFDistrict.getTimeData().get(i).setWaterRequirementPlan(NFDistrict.getTimeData().get(i).getWaterRequirement());

            LADistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(2)[i]);
            LADistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(2)[i]);
            LADistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(2));
            LADistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(2));
            LADistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(2));
            LADistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(2));
            LADistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(2)[i]);
            LADistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(2)*inputEntity.getIndustrialWaterWeight().get(2)*inputEntity.getIndustrialWaterRatio().get(2)[i] +
                            inputEntity.getWaterRequirement().get(2)*inputEntity.getAgriculturalWaterWeight().get(2)*inputEntity.getAgriculturalWaterRatio().get(2)[i]+
                            inputEntity.getWaterRequirement().get(2)*inputEntity.getDomesticWaterWeight().get(2)*inputEntity.getDomesticWaterRatio().get(2)[i]+
                            inputEntity.getWaterRequirement().get(2)*inputEntity.getForestryWaterWeight().get(2)*inputEntity.getForestryWaterRatio().get(2)[i]);
            LADistrict.getTimeData().get(i).setWaterRequirementPlan(LADistrict.getTimeData().get(i).getWaterRequirement());

            CRDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(3)[i]);
            CRDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(3)[i]);
            CRDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(3));
            CRDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(3));
            CRDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(3));
            CRDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(3));
            CRDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(3)[i]);
            CRDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(3)*inputEntity.getIndustrialWaterWeight().get(3)*inputEntity.getIndustrialWaterRatio().get(3)[i] +
                            inputEntity.getWaterRequirement().get(3)*inputEntity.getAgriculturalWaterWeight().get(3)*inputEntity.getAgriculturalWaterRatio().get(3)[i]+
                            inputEntity.getWaterRequirement().get(3)*inputEntity.getDomesticWaterWeight().get(3)*inputEntity.getDomesticWaterRatio().get(3)[i]+
                            inputEntity.getWaterRequirement().get(3)*inputEntity.getForestryWaterWeight().get(3)*inputEntity.getForestryWaterRatio().get(3)[i]);
            CRDistrict.getTimeData().get(i).setWaterRequirementPlan(CRDistrict.getTimeData().get(i).getWaterRequirement());

            YHDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(4)[i]);
            YHDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(4)[i]);
            YHDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(4));
            YHDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(4));
            YHDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(4));
            YHDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(4));
            YHDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(4)[i]);
            YHDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(4)*inputEntity.getIndustrialWaterWeight().get(4)*inputEntity.getIndustrialWaterRatio().get(4)[i] +
                            inputEntity.getWaterRequirement().get(4)*inputEntity.getAgriculturalWaterWeight().get(4)*inputEntity.getAgriculturalWaterRatio().get(4)[i]+
                            inputEntity.getWaterRequirement().get(4)*inputEntity.getDomesticWaterWeight().get(4)*inputEntity.getDomesticWaterRatio().get(4)[i]+
                            inputEntity.getWaterRequirement().get(4)*inputEntity.getForestryWaterWeight().get(4)*inputEntity.getForestryWaterRatio().get(4)[i]);
            YHDistrict.getTimeData().get(i).setWaterRequirementPlan(YHDistrict.getTimeData().get(i).getWaterRequirement());


            LCDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(5)[i]);
            LCDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(5)[i]);
            LCDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(5));
            LCDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(5));
            LCDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(5));
            LCDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(5));
            LCDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(5)[i]);
            LCDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(5)*inputEntity.getIndustrialWaterWeight().get(5)*inputEntity.getIndustrialWaterRatio().get(5)[i] +
                            inputEntity.getWaterRequirement().get(5)*inputEntity.getAgriculturalWaterWeight().get(5)*inputEntity.getAgriculturalWaterRatio().get(5)[i]+
                            inputEntity.getWaterRequirement().get(5)*inputEntity.getDomesticWaterWeight().get(5)*inputEntity.getDomesticWaterRatio().get(5)[i]+
                            inputEntity.getWaterRequirement().get(5)*inputEntity.getForestryWaterWeight().get(5)*inputEntity.getForestryWaterRatio().get(5)[i]);
            LCDistrict.getTimeData().get(i).setWaterRequirementPlan(LCDistrict.getTimeData().get(i).getWaterRequirement());

            NCConfluenceNode.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(6));
            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(6));
            NCConfluenceNode.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(6));
            NCConfluenceNode.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(6));
            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(6)*inputEntity.getIndustrialWaterWeight().get(6)*inputEntity.getIndustrialWaterRatio().get(6)[i] +
                            inputEntity.getWaterRequirement().get(6)*inputEntity.getAgriculturalWaterWeight().get(6)*inputEntity.getAgriculturalWaterRatio().get(6)[i]+
                            inputEntity.getWaterRequirement().get(6)*inputEntity.getDomesticWaterWeight().get(6)*inputEntity.getDomesticWaterRatio().get(6)[i]+
                            inputEntity.getWaterRequirement().get(6)*inputEntity.getForestryWaterWeight().get(6)*inputEntity.getForestryWaterRatio().get(6)[i]);
            NCConfluenceNode.getTimeData().get(i).setWaterRequirementPlan(NCConfluenceNode.getTimeData().get(i).getWaterRequirement());
            
            ZXDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(7));
            ZXDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(7));
            ZXDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(7));
            ZXDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(7));
            ZXDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(7)*inputEntity.getIndustrialWaterWeight().get(7)*inputEntity.getIndustrialWaterRatio().get(7)[i] +
                            inputEntity.getWaterRequirement().get(7)*inputEntity.getAgriculturalWaterWeight().get(7)*inputEntity.getAgriculturalWaterRatio().get(7)[i]+
                            inputEntity.getWaterRequirement().get(7)*inputEntity.getDomesticWaterWeight().get(7)*inputEntity.getDomesticWaterRatio().get(7)[i]+
                            inputEntity.getWaterRequirement().get(7)*inputEntity.getForestryWaterWeight().get(7)*inputEntity.getForestryWaterRatio().get(7)[i]);
            ZXDistrict.getTimeData().get(i).setWaterRequirementPlan(ZXDistrict.getTimeData().get(i).getWaterRequirement());

            virtualNode.getJXConfluenceNode().getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(8));
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(8));
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(8));
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(8));
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(8)*inputEntity.getIndustrialWaterWeight().get(8)*inputEntity.getIndustrialWaterRatio().get(8)[i] +
                            inputEntity.getWaterRequirement().get(8)*inputEntity.getAgriculturalWaterWeight().get(8)*inputEntity.getAgriculturalWaterRatio().get(8)[i]+
                            inputEntity.getWaterRequirement().get(8)*inputEntity.getDomesticWaterWeight().get(8)*inputEntity.getDomesticWaterRatio().get(8)[i]+
                            inputEntity.getWaterRequirement().get(8)*inputEntity.getForestryWaterWeight().get(8)*inputEntity.getForestryWaterRatio().get(8)[i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setWaterRequirementPlan(virtualNode.getJXConfluenceNode().getTimeData().get(i).getWaterRequirement());

            virtualNode.getLCConfluenceNode().getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(9));
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(9));
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(9));
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(9));
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(9)*inputEntity.getIndustrialWaterWeight().get(9)*inputEntity.getIndustrialWaterRatio().get(9)[i] +
                            inputEntity.getWaterRequirement().get(9)*inputEntity.getAgriculturalWaterWeight().get(9)*inputEntity.getAgriculturalWaterRatio().get(9)[i]+
                            inputEntity.getWaterRequirement().get(9)*inputEntity.getDomesticWaterWeight().get(9)*inputEntity.getDomesticWaterRatio().get(9)[i]+
                            inputEntity.getWaterRequirement().get(9)*inputEntity.getForestryWaterWeight().get(9)*inputEntity.getForestryWaterRatio().get(9)[i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setWaterRequirementPlan(virtualNode.getLCConfluenceNode().getTimeData().get(i).getWaterRequirement());
           
           
            DXDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(10)[i]);
            DXDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(10)[i]);
            DXDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(10));
            DXDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(10));
            DXDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(10));
            DXDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(10));
            DXDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(10)[i]);
            DXDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(10)*inputEntity.getIndustrialWaterWeight().get(10)*inputEntity.getIndustrialWaterRatio().get(10)[i] +
                            inputEntity.getWaterRequirement().get(10)*inputEntity.getAgriculturalWaterWeight().get(10)*inputEntity.getAgriculturalWaterRatio().get(10)[i]+
                            inputEntity.getWaterRequirement().get(10)*inputEntity.getDomesticWaterWeight().get(10)*inputEntity.getDomesticWaterRatio().get(10)[i]+
                            inputEntity.getWaterRequirement().get(10)*inputEntity.getForestryWaterWeight().get(10)*inputEntity.getForestryWaterRatio().get(10)[i]);
            DXDistrict.getTimeData().get(i).setWaterRequirementPlan(DXDistrict.getTimeData().get(i).getWaterRequirement());

            GFPYDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getSelfProducedInflow().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getMinInstreamingEcologicalFlow().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getIndustrialWaterRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getAgriculturalWaterRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDomesticWaterRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getForestryWaterRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getIndustrialWaterWeight().get(11));
            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getAgriculturalWaterWeight().get(11));
            GFPYDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDomesticWaterWeight().get(11));
            GFPYDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getForestryWaterWeight().get(11));
            GFPYDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getIndustrialWaterReturnRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getAgriculturalWaterReturnRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDomesticWaterReturnRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getForestryWaterReturnRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getInflowFrequency().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setWaterRequirement(
                    inputEntity.getWaterRequirement().get(11)*inputEntity.getIndustrialWaterWeight().get(11)*inputEntity.getIndustrialWaterRatio().get(11)[i] +
                            inputEntity.getWaterRequirement().get(11)*inputEntity.getAgriculturalWaterWeight().get(11)*inputEntity.getAgriculturalWaterRatio().get(11)[i]+
                            inputEntity.getWaterRequirement().get(11)*inputEntity.getDomesticWaterWeight().get(11)*inputEntity.getDomesticWaterRatio().get(11)[i]+
                            inputEntity.getWaterRequirement().get(11)*inputEntity.getForestryWaterWeight().get(11)*inputEntity.getForestryWaterRatio().get(11)[i]);
            GFPYDistrict.getTimeData().get(i).setWaterRequirementPlan(GFPYDistrict.getTimeData().get(i).getWaterRequirement());
        }

//        for (int i = 0; i <timeLength; i++) {
//            GCDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getGCXselfProducedInflow()[i]);
//            GCDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getGCXminInstreamingEcologicalFlow()[i]);
//            GCDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getGCXindustrialWaterRatio()[i]);
//            GCDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getGCXagriculturalWaterRatio()[i]);
//            GCDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getGCXdomesticWaterRatio()[i]);
//            GCDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getGCXforestryWaterRatio()[i]);
//            GCDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getGCXindustrialWaterWeight());
//            GCDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getGCXagriculturalWaterWeight());
//            GCDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getGCXdomesticWaterWeight());
//            GCDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getGCXforestryWaterWeight());
//            GCDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getGCXindustrialWaterReturnRatio()[i]);
//            GCDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getGCXagriculturalWaterReturnRatio()[i]);
//            GCDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getGCXdomesticWaterReturnRatio()[i]);
//            GCDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getGCXforestryWaterReturnRatio()[i]);
//            GCDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getGCXinflowFrequency()[i]);
//            GCDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getGCXwaterRequirement()*inputEntity.getGCXindustrialWaterWeight()*inputEntity.getGCXindustrialWaterRatio()[i] +
//                    inputEntity.getGCXwaterRequirement()*inputEntity.getGCXagriculturalWaterWeight()*inputEntity.getGCXagriculturalWaterRatio()[i]+
//                    inputEntity.getGCXwaterRequirement()*inputEntity.getGCXdomesticWaterWeight()*inputEntity.getGCXdomesticWaterRatio()[i]+
//                    inputEntity.getGCXwaterRequirement()*inputEntity.getGCXforestryWaterWeight()*inputEntity.getGCXforestryWaterRatio()[i]);
//            GCDistrict.getTimeData().get(i).setWaterRequirementPlan(GCDistrict.getTimeData().get(i).getWaterRequirement());
//
//            GFPYDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getGFPYselfProducedInflow()[i]);
//            GFPYDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getGFPYminInstreamingEcologicalFlow()[i]);
//            GFPYDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getGFPYindustrialWaterRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getGFPYagriculturalWaterRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getGFPYdomesticWaterRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getGFPYforestryWaterRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getGFPYindustrialWaterWeight());
//            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getGFPYagriculturalWaterWeight());
//            GFPYDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getGFPYdomesticWaterWeight());
//            GFPYDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getGFPYforestryWaterWeight());
//            GFPYDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getGFPYindustrialWaterReturnRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getGFPYagriculturalWaterReturnRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getGFPYdomesticWaterReturnRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getGFPYforestryWaterReturnRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getGFPYinflowFrequency()[i]);
//            GFPYDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getGFPYwaterRequirement()*inputEntity.getGFPYindustrialWaterWeight()*inputEntity.getGFPYindustrialWaterRatio()[i] +
//                            inputEntity.getGFPYwaterRequirement()*inputEntity.getGFPYagriculturalWaterWeight()*inputEntity.getGFPYagriculturalWaterRatio()[i]+
//                            inputEntity.getGFPYwaterRequirement()*inputEntity.getGFPYdomesticWaterWeight()*inputEntity.getGFPYdomesticWaterRatio()[i]+
//                            inputEntity.getGFPYwaterRequirement()*inputEntity.getGFPYforestryWaterWeight()*inputEntity.getGFPYforestryWaterRatio()[i]);
//            GFPYDistrict.getTimeData().get(i).setWaterRequirementPlan(GFPYDistrict.getTimeData().get(i).getWaterRequirement());
//
//            LCDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getLCXselfProducedInflow()[i]);
//            LCDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getLCXminInstreamingEcologicalFlow()[i]);
//            LCDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getLCXindustrialWaterRatio()[i]);
//            LCDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getLCXagriculturalWaterRatio()[i]);
//            LCDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getLCXdomesticWaterRatio()[i]);
//            LCDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getLCXforestryWaterRatio()[i]);
//            LCDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getLCXindustrialWaterWeight());
//            LCDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getLCXagriculturalWaterWeight());
//            LCDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getLCXdomesticWaterWeight());
//            LCDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getLCXforestryWaterWeight());
//            LCDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getLCXindustrialWaterReturnRatio()[i]);
//            LCDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getLCXagriculturalWaterReturnRatio()[i]);
//            LCDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getLCXdomesticWaterReturnRatio()[i]);
//            LCDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getLCXforestryWaterReturnRatio()[i]);
//            LCDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getLCXinflowFrequency()[i]);
//            LCDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getLCXwaterRequirement()*inputEntity.getLCXindustrialWaterWeight()*inputEntity.getLCXindustrialWaterRatio()[i] +
//                            inputEntity.getLCXwaterRequirement()*inputEntity.getLCXagriculturalWaterWeight()*inputEntity.getLCXagriculturalWaterRatio()[i]+
//                            inputEntity.getLCXwaterRequirement()*inputEntity.getLCXdomesticWaterWeight()*inputEntity.getLCXdomesticWaterRatio()[i]+
//                            inputEntity.getLCXwaterRequirement()*inputEntity.getLCXforestryWaterWeight()*inputEntity.getLCXforestryWaterRatio()[i]);
//            LCDistrict.getTimeData().get(i).setWaterRequirementPlan(LCDistrict.getTimeData().get(i).getWaterRequirement());
//
//            ZXDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getZXXselfProducedInflow()[i]);
//            ZXDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getZXXminInstreamingEcologicalFlow()[i]);
//            ZXDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getZXXindustrialWaterRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getZXXagriculturalWaterRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getZXXdomesticWaterRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getZXXforestryWaterRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getZXXindustrialWaterWeight());
//            ZXDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getZXXagriculturalWaterWeight());
//            ZXDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getZXXdomesticWaterWeight());
//            ZXDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getZXXforestryWaterWeight());
//            ZXDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getZXXindustrialWaterReturnRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getZXXagriculturalWaterReturnRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getZXXdomesticWaterReturnRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getZXXforestryWaterReturnRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getZXXinflowFrequency()[i]);
//            ZXDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getZXXwaterRequirement()*inputEntity.getZXXindustrialWaterWeight()*inputEntity.getZXXindustrialWaterRatio()[i] +
//                            inputEntity.getZXXwaterRequirement()*inputEntity.getZXXagriculturalWaterWeight()*inputEntity.getZXXagriculturalWaterRatio()[i]+
//                            inputEntity.getZXXwaterRequirement()*inputEntity.getZXXdomesticWaterWeight()*inputEntity.getZXXdomesticWaterRatio()[i]+
//                            inputEntity.getZXXwaterRequirement()*inputEntity.getZXXforestryWaterWeight()*inputEntity.getZXXforestryWaterRatio()[i]);
//            ZXDistrict.getTimeData().get(i).setWaterRequirementPlan(ZXDistrict.getTimeData().get(i).getWaterRequirement());
//
//            DXDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getDXXselfProducedInflow()[i]);
//            DXDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getDXXminInstreamingEcologicalFlow()[i]);
//            DXDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getDXXindustrialWaterRatio()[i]);
//            DXDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getDXXagriculturalWaterRatio()[i]);
//            DXDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getDXXdomesticWaterRatio()[i]);
//            DXDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getDXXforestryWaterRatio()[i]);
//            DXDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getDXXindustrialWaterWeight());
//            DXDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getDXXagriculturalWaterWeight());
//            DXDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getDXXdomesticWaterWeight());
//            DXDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getDXXforestryWaterWeight());
//            DXDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getDXXindustrialWaterReturnRatio()[i]);
//            DXDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getDXXagriculturalWaterReturnRatio()[i]);
//            DXDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getDXXdomesticWaterReturnRatio()[i]);
//            DXDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getDXXforestryWaterReturnRatio()[i]);
//            DXDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getDXXinflowFrequency()[i]);
//            DXDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getDXXwaterRequirement()*inputEntity.getDXXindustrialWaterWeight()*inputEntity.getDXXindustrialWaterRatio()[i] +
//                            inputEntity.getDXXwaterRequirement()*inputEntity.getDXXagriculturalWaterWeight()*inputEntity.getDXXagriculturalWaterRatio()[i]+
//                            inputEntity.getDXXwaterRequirement()*inputEntity.getDXXdomesticWaterWeight()*inputEntity.getDXXdomesticWaterRatio()[i]+
//                            inputEntity.getDXXwaterRequirement()*inputEntity.getDXXforestryWaterWeight()*inputEntity.getDXXforestryWaterRatio()[i]);
//            DXDistrict.getTimeData().get(i).setWaterRequirementPlan(DXDistrict.getTimeData().get(i).getWaterRequirement());
//
//            NFDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getNFXselfProducedInflow()[i]);
//            NFDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getNFXminInstreamingEcologicalFlow()[i]);
//            NFDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getNFXindustrialWaterRatio()[i]);
//            NFDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getNFXagriculturalWaterRatio()[i]);
//            NFDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getNFXdomesticWaterRatio()[i]);
//            NFDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getNFXforestryWaterRatio()[i]);
//            NFDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getNFXindustrialWaterWeight());
//            NFDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getNFXagriculturalWaterWeight());
//            NFDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getNFXdomesticWaterWeight());
//            NFDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getNFXforestryWaterWeight());
//            NFDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getNFXindustrialWaterReturnRatio()[i]);
//            NFDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getNFXagriculturalWaterReturnRatio()[i]);
//            NFDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getNFXdomesticWaterReturnRatio()[i]);
//            NFDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getNFXforestryWaterReturnRatio()[i]);
//            NFDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getNFXinflowFrequency()[i]);
//            NFDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getNFXwaterRequirement()*inputEntity.getNFXindustrialWaterWeight()*inputEntity.getNFXindustrialWaterRatio()[i] +
//                            inputEntity.getNFXwaterRequirement()*inputEntity.getNFXagriculturalWaterWeight()*inputEntity.getNFXagriculturalWaterRatio()[i]+
//                            inputEntity.getNFXwaterRequirement()*inputEntity.getNFXdomesticWaterWeight()*inputEntity.getNFXdomesticWaterRatio()[i]+
//                            inputEntity.getNFXwaterRequirement()*inputEntity.getNFXforestryWaterWeight()*inputEntity.getNFXforestryWaterRatio()[i]);
//            NFDistrict.getTimeData().get(i).setWaterRequirementPlan(NFDistrict.getTimeData().get(i).getWaterRequirement());
//
//            LADistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getLAXselfProducedInflow()[i]);
//            LADistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getLAXminInstreamingEcologicalFlow()[i]);
//            LADistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getLAXindustrialWaterRatio()[i]);
//            LADistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getLAXagriculturalWaterRatio()[i]);
//            LADistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getLAXdomesticWaterRatio()[i]);
//            LADistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getLAXforestryWaterRatio()[i]);
//            LADistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getLAXindustrialWaterWeight());
//            LADistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getLAXagriculturalWaterWeight());
//            LADistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getLAXdomesticWaterWeight());
//            LADistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getLAXforestryWaterWeight());
//            LADistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getLAXindustrialWaterReturnRatio()[i]);
//            LADistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getLAXagriculturalWaterReturnRatio()[i]);
//            LADistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getLAXdomesticWaterReturnRatio()[i]);
//            LADistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getLAXforestryWaterReturnRatio()[i]);
//            LADistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getLAXinflowFrequency()[i]);
//            LADistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getLAXwaterRequirement()*inputEntity.getLAXindustrialWaterWeight()*inputEntity.getLAXindustrialWaterRatio()[i] +
//                            inputEntity.getLAXwaterRequirement()*inputEntity.getLAXagriculturalWaterWeight()*inputEntity.getLAXagriculturalWaterRatio()[i]+
//                            inputEntity.getLAXwaterRequirement()*inputEntity.getLAXdomesticWaterWeight()*inputEntity.getLAXdomesticWaterRatio()[i]+
//                            inputEntity.getLAXwaterRequirement()*inputEntity.getLAXforestryWaterWeight()*inputEntity.getLAXforestryWaterRatio()[i]);
//            LADistrict.getTimeData().get(i).setWaterRequirementPlan(LADistrict.getTimeData().get(i).getWaterRequirement());
//
//            CRDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getCRXselfProducedInflow()[i]);
//            CRDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getCRXminInstreamingEcologicalFlow()[i]);
//            CRDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getCRXindustrialWaterRatio()[i]);
//            CRDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getCRXagriculturalWaterRatio()[i]);
//            CRDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getCRXdomesticWaterRatio()[i]);
//            CRDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getCRXforestryWaterRatio()[i]);
//            CRDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getCRXindustrialWaterWeight());
//            CRDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getCRXagriculturalWaterWeight());
//            CRDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getCRXdomesticWaterWeight());
//            CRDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getCRXforestryWaterWeight());
//            CRDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getCRXindustrialWaterReturnRatio()[i]);
//            CRDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getCRXagriculturalWaterReturnRatio()[i]);
//            CRDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getCRXdomesticWaterReturnRatio()[i]);
//            CRDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getCRXforestryWaterReturnRatio()[i]);
//            CRDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getCRXinflowFrequency()[i]);
//            CRDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getCRXwaterRequirement()*inputEntity.getCRXindustrialWaterWeight()*inputEntity.getCRXindustrialWaterRatio()[i] +
//                            inputEntity.getCRXwaterRequirement()*inputEntity.getCRXagriculturalWaterWeight()*inputEntity.getCRXagriculturalWaterRatio()[i]+
//                            inputEntity.getCRXwaterRequirement()*inputEntity.getCRXdomesticWaterWeight()*inputEntity.getCRXdomesticWaterRatio()[i]+
//                            inputEntity.getCRXwaterRequirement()*inputEntity.getCRXforestryWaterWeight()*inputEntity.getCRXforestryWaterRatio()[i]);
//            CRDistrict.getTimeData().get(i).setWaterRequirementPlan(CRDistrict.getTimeData().get(i).getWaterRequirement());
//
//            YHDistrict.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getYHXselfProducedInflow()[i]);
//            YHDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getYHXminInstreamingEcologicalFlow()[i]);
//            YHDistrict.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getYHXindustrialWaterRatio()[i]);
//            YHDistrict.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getYHXagriculturalWaterRatio()[i]);
//            YHDistrict.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getYHXdomesticWaterRatio()[i]);
//            YHDistrict.getTimeData().get(i).setForestryWaterRatio(inputEntity.getYHXforestryWaterRatio()[i]);
//            YHDistrict.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getYHXindustrialWaterWeight());
//            YHDistrict.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getYHXagriculturalWaterWeight());
//            YHDistrict.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getYHXdomesticWaterWeight());
//            YHDistrict.getTimeData().get(i).setForestryWaterWeight(inputEntity.getYHXforestryWaterWeight());
//            YHDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getYHXindustrialWaterReturnRatio()[i]);
//            YHDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getYHXagriculturalWaterReturnRatio()[i]);
//            YHDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getYHXdomesticWaterReturnRatio()[i]);
//            YHDistrict.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getYHXforestryWaterReturnRatio()[i]);
//            YHDistrict.getTimeData().get(i).setInflowFrequency(inputEntity.getYHXinflowFrequency()[i]);
//            YHDistrict.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getYHXwaterRequirement()*inputEntity.getYHXindustrialWaterWeight()*inputEntity.getYHXindustrialWaterRatio()[i] +
//                            inputEntity.getYHXwaterRequirement()*inputEntity.getYHXagriculturalWaterWeight()*inputEntity.getYHXagriculturalWaterRatio()[i]+
//                            inputEntity.getYHXwaterRequirement()*inputEntity.getYHXdomesticWaterWeight()*inputEntity.getYHXdomesticWaterRatio()[i]+
//                            inputEntity.getYHXwaterRequirement()*inputEntity.getYHXforestryWaterWeight()*inputEntity.getYHXforestryWaterRatio()[i]);
//            YHDistrict.getTimeData().get(i).setWaterRequirementPlan(YHDistrict.getTimeData().get(i).getWaterRequirement());
//
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getJXXselfProducedInflow()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getJXXminInstreamingEcologicalFlow()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getJXXindustrialWaterRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getJXXagriculturalWaterRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterRatio(inputEntity.getJXXdomesticWaterRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterRatio(inputEntity.getJXXforestryWaterRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getJXXindustrialWaterWeight());
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getJXXagriculturalWaterWeight());
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterWeight(inputEntity.getJXXdomesticWaterWeight());
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterWeight(inputEntity.getJXXforestryWaterWeight());
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getJXXindustrialWaterReturnRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getJXXagriculturalWaterReturnRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getJXXdomesticWaterReturnRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getJXXforestryWaterReturnRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setInflowFrequency(inputEntity.getJXXinflowFrequency()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getJXXwaterRequirement()*inputEntity.getJXXindustrialWaterWeight()*inputEntity.getJXXindustrialWaterRatio()[i] +
//                            inputEntity.getJXXwaterRequirement()*inputEntity.getJXXagriculturalWaterWeight()*inputEntity.getJXXagriculturalWaterRatio()[i]+
//                            inputEntity.getJXXwaterRequirement()*inputEntity.getJXXdomesticWaterWeight()*inputEntity.getJXXdomesticWaterRatio()[i]+
//                            inputEntity.getJXXwaterRequirement()*inputEntity.getJXXforestryWaterWeight()*inputEntity.getJXXforestryWaterRatio()[i]);
//            virtualNode.getJXConfluenceNode().getTimeData().get(i).setWaterRequirementPlan(virtualNode.getJXConfluenceNode().getTimeData().get(i).getWaterRequirement());
//
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getLCQselfProducedInflow()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getLCQminInstreamingEcologicalFlow()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getLCQindustrialWaterRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getLCQagriculturalWaterRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterRatio(inputEntity.getLCQdomesticWaterRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterRatio(inputEntity.getLCQforestryWaterRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getLCQindustrialWaterWeight());
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getLCQagriculturalWaterWeight());
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterWeight(inputEntity.getLCQdomesticWaterWeight());
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterWeight(inputEntity.getLCQforestryWaterWeight());
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getLCQindustrialWaterReturnRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getLCQagriculturalWaterReturnRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getLCQdomesticWaterReturnRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getLCQforestryWaterReturnRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setInflowFrequency(inputEntity.getLCQinflowFrequency()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getLCQwaterRequirement()*inputEntity.getLCQindustrialWaterWeight()*inputEntity.getLCQindustrialWaterRatio()[i] +
//                            inputEntity.getLCQwaterRequirement()*inputEntity.getLCQagriculturalWaterWeight()*inputEntity.getLCQagriculturalWaterRatio()[i]+
//                            inputEntity.getLCQwaterRequirement()*inputEntity.getLCQdomesticWaterWeight()*inputEntity.getLCQdomesticWaterRatio()[i]+
//                            inputEntity.getLCQwaterRequirement()*inputEntity.getLCQforestryWaterWeight()*inputEntity.getLCQforestryWaterRatio()[i]);
//            virtualNode.getLCConfluenceNode().getTimeData().get(i).setWaterRequirementPlan(virtualNode.getLCConfluenceNode().getTimeData().get(i).getWaterRequirement());
//
//            NCConfluenceNode.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getNCXselfProducedInflow()[i]);
//            NCConfluenceNode.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getNCXminInstreamingEcologicalFlow()[i]);
//            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterRatio(inputEntity.getNCXindustrialWaterRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterRatio(inputEntity.getNCXagriculturalWaterRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setDomesticWaterRatio(inputEntity.getNCXdomesticWaterRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setForestryWaterRatio(inputEntity.getNCXforestryWaterRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterWeight(inputEntity.getNCXindustrialWaterWeight());
//            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterWeight(inputEntity.getNCXagriculturalWaterWeight());
//            NCConfluenceNode.getTimeData().get(i).setDomesticWaterWeight(inputEntity.getNCXdomesticWaterWeight());
//            NCConfluenceNode.getTimeData().get(i).setForestryWaterWeight(inputEntity.getNCXforestryWaterWeight());
//            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterReturnRatio(inputEntity.getNCXindustrialWaterReturnRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterReturnRatio(inputEntity.getNCXagriculturalWaterReturnRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setDomesticWaterReturnRatio(inputEntity.getNCXdomesticWaterReturnRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setForestryWaterReturnRatio(inputEntity.getNCXforestryWaterReturnRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setInflowFrequency(inputEntity.getNCXinflowFrequency()[i]);
//            NCConfluenceNode.getTimeData().get(i).setWaterRequirement(
//                    inputEntity.getNCXwaterRequirement()*inputEntity.getNCXindustrialWaterWeight()*inputEntity.getNCXindustrialWaterRatio()[i] +
//                            inputEntity.getNCXwaterRequirement()*inputEntity.getNCXagriculturalWaterWeight()*inputEntity.getNCXagriculturalWaterRatio()[i]+
//                            inputEntity.getNCXwaterRequirement()*inputEntity.getNCXdomesticWaterWeight()*inputEntity.getNCXdomesticWaterRatio()[i]+
//                            inputEntity.getNCXwaterRequirement()*inputEntity.getNCXforestryWaterWeight()*inputEntity.getNCXforestryWaterRatio()[i]);
//            NCConfluenceNode.getTimeData().get(i).setWaterRequirementPlan(NCConfluenceNode.getTimeData().get(i).getWaterRequirement());
//        }
        
        for (int i = 0; i <timeLength; i++) {
            HMHydroStation.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getHMSKselfProducedInflow()[i]);
            HMHydroStation.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getHMSKminInstreamingEcologicalFlow()[i]);
            HMHydroStation.getTimeData().get(i).setInflowFrequency(inputEntity.getHMSKinflowFrequency()[i]);
        }
        for (int i = 0; i <timeLength; i++) {
            LFHydroStation.getTimeData().get(i).setSelfProducedInflowTotal(inputEntity.getLFSKselfProducedInflow()[i]);
            LFHydroStation.getTimeData().get(i).setMinInstreamingEcologicalFlow(inputEntity.getLFSKminInstreamingEcologicalFlow()[i]);
            LFHydroStation.getTimeData().get(i).setInflowFrequency(inputEntity.getLFSKinflowFrequency()[i]);
        }
        //虚节点分水比例
        for (int i = 0; i <timeLength; i++) {
            virtualNode.getTimeData().get(i).setWeight1(inputEntity.getJXXWeight());
            virtualNode.getTimeData().get(i).setWeight2(inputEntity.getLCQWeight());
        }
//        LCDistrict.setWaterRequirement(inputEntity.getLCXwaterRequirement());
//        NCConfluenceNode.setWaterRequirement(inputEntity.getNFXwaterRequirement());
//        ZXDistrict.setWaterRequirement(inputEntity.getZXXwaterRequirement());
//        virtualNode.getJXConfluenceNode().setWaterRequirement(inputEntity.getJXXwaterRequirement());
//        virtualNode.getLCConfluenceNode().setWaterRequirement(inputEntity.getLCQwaterRequirement());
//        DXDistrict.setWaterRequirement(inputEntity.getDXXwaterRequirement());
//        GFPYDistrict.setWaterRequirement(inputEntity.getGFPYwaterRequirement());
//
//        GCDistrict.setWaterRequirement(inputEntity.getGCXwaterRequirement());
//        NFDistrict.setWaterRequirement(inputEntity.getNFXwaterRequirement());
//
//        LADistrict.setWaterRequirement(inputEntity.getLAXwaterRequirement());
//        CRDistrict.setWaterRequirement(inputEntity.getCRXwaterRequirement());
//        YHDistrict.setWaterRequirement(inputEntity.getYHXwaterRequirement());

        //初始化河流
        River FHRiver=new River();
        River XJRiver=new River();
        River LSRiver=new River();
        rivers.add(0,FHRiver);
        rivers.add(1,XJRiver);
        rivers.add(2,LSRiver);
        //初始化河流中的节点
        List<Node> nodes1=new ArrayList<>();
        List<Node> nodes2=new ArrayList<>();
        List<Node> nodes3=new ArrayList<>();
        //干流中的节点
        nodes1.add(0,LCDistrict);
        nodes1.add(1,HMHydroStation);
        nodes1.add(2,NCConfluenceNode);
        nodes1.add(3,LFHydroStation);
        nodes1.add(4,ZXDistrict);
        nodes1.add(5,virtualNode);
        nodes1.add(6,DXDistrict);
        nodes1.add(7,GFPYDistrict);
        //盱江中的节点
        nodes2.add(0,GCDistrict);
        nodes2.add(1,NFDistrict);
        //临水中的节点
        nodes3.add(0,LADistrict);
        nodes3.add(1,CRDistrict);
        nodes3.add(2,YHDistrict);
        //同一放到rivers中
        rivers.get(0).setNodes(nodes1);
        rivers.get(1).setNodes(nodes2);
        rivers.get(2).setNodes(nodes3);


        // ********节点类型******
        //盱江
        GCDistrict.setType(1);
        NFDistrict.setType(1);
        //临水
        LADistrict.setType(1);
        CRDistrict.setType(1);
        YHDistrict.setType(1);
        //干流
        LCDistrict.setType(1);
        HMHydroStation.setType(2);
        NCConfluenceNode.setType(4);
        LFHydroStation.setType(2);
        ZXDistrict.setType(1);
        virtualNode.setType(3);
        DXDistrict.setType(1);
        GFPYDistrict.setType(1);
        virtualNode.getJXConfluenceNode().setType(4);
        virtualNode.getLCConfluenceNode().setType(4);

        // ********节点名字******
        //盱江
        GCDistrict.setName("GCX");
        NFDistrict.setName("NFX");
        //临水
        LADistrict.setName("LAX");
        CRDistrict.setName("CRX");
        YHDistrict.setName("YHX");
        //干流
        LCDistrict.setName("LCX");
        HMHydroStation.setName("HMSK");
        NCConfluenceNode.setName("NCX");
        LFHydroStation.setName("LFSK");
        ZXDistrict.setName("ZXX");
        DXDistrict.setName("DXX");
        GFPYDistrict.setName("GFPY");
        virtualNode.getJXConfluenceNode().setName("LCQ");
        virtualNode.getLCConfluenceNode().setName("JXX");

        // ********节点上下游关系******
        //盱江
        GCDistrict.setDownNode(NFDistrict);
        NFDistrict.setDownNode(NCConfluenceNode);
        //临水
        LADistrict.setDownNode(CRDistrict);
        CRDistrict.setDownNode(YHDistrict);
        YHDistrict.setDownNode(virtualNode);
        //干流
        LCDistrict.setDownNode(HMHydroStation);
        HMHydroStation.setDownNode(NCConfluenceNode);
        NCConfluenceNode.setDownNode(LFHydroStation);
        LFHydroStation.setDownNode(ZXDistrict);
        ZXDistrict.setDownNode(virtualNode);
        virtualNode.setDownNode(DXDistrict);
        DXDistrict.setDownNode(GFPYDistrict);
    }



    //初始化河流及节点,从EXCEL读取
    public void initializationByExcel(List<River>rivers,int timeLength) throws IOException {
        District LCDistrict=new District(timeLength);
        HydroStation HMHydroStation=new HydroStation(timeLength);
        ConfluenceNode NCConfluenceNode=new ConfluenceNode(timeLength);
        HydroStation LFHydroStation=new HydroStation(timeLength);
        District ZXDistrict=new District(timeLength);
        VirtualNode virtualNode=new VirtualNode(timeLength);
        District DXDistrict=new District(timeLength);
        District GFPYDistrict=new District(timeLength);

        District GCDistrict=new District(timeLength);
        District NFDistrict=new District(timeLength);

        District LADistrict=new District(timeLength);
        District CRDistrict=new District(timeLength);
        District YHDistrict=new District(timeLength);


        // *******************************初始化电站曲线数据**********************************************
        //电站曲线
        String path = System.getProperty("user.dir") + "/src/main/resources/levelCapacity.xlsx";
        String[] sheet = new String[]{"HMSK", "LFSK"};
        List<Object[][]> stationCurve = new ArrayList<>();
        for (int i = 0; i < sheet.length; i++) {
            stationCurve.add(ExcelTool.read07Excel(path, sheet[i]));
        }
        double[][] HMlevelCapacity = new double[stationCurve.get(0).length][2];
        double[][] LFlevelCapacity = new double[stationCurve.get(1).length][2];
        for (int i = 0; i < stationCurve.get(0).length; i++) {
            for (int j = 0; j < stationCurve.get(0)[i].length; j++) {
                HMlevelCapacity[i][j] = (double) stationCurve.get(0)[i][j];
            }
        }
        for (int i = 0; i < stationCurve.get(1).length; i++) {
            for (int j = 0; j < stationCurve.get(1)[i].length; j++) {
                LFlevelCapacity[i][j] = (double) stationCurve.get(1)[i][j];
            }
        }
        DoubleCurve HMlevelCapacityCurve = new DoubleCurve(HMlevelCapacity);
        DoubleCurve LFlevelCapacityCurve = new DoubleCurve(LFlevelCapacity);
        HMHydroStation.getCurve().setLevelCapacityCurve(HMlevelCapacityCurve);
        LFHydroStation.getCurve().setLevelCapacityCurve(LFlevelCapacityCurve);
        HMHydroStation.setLevelNormal(100);
        HMHydroStation.setLevelDead(92);
        LFHydroStation.setLevelNormal(65);
        LFHydroStation.setLevelDead(61);

// *******************************初始化节点数据**********************************************

        //读取EXCEL方法
        String path1 = System.getProperty("user.dir") + "/src/main/resources/test.xlsx";
        String[] sheet1 = new String[]{"GCX","NFX","LAX","CRX","YHX","LCX","NCX","ZXX","LCQ","JXX","DXX","GFPY","HMSK", "LFSK"};
        List<Object[][]> dataList = new ArrayList<>();
        for (int i = 0; i < sheet1.length; i++) {
            dataList.add(ExcelTool.read07Excel(path1, sheet1[i]));
        }
        double[][] GCX = new double[dataList.get(0).length-1][dataList.get(0)[0].length-1];
        double[][] NFX = new double[dataList.get(1).length-1][dataList.get(1)[0].length-1];
        double[][] LAX = new double[dataList.get(2).length-1][dataList.get(2)[0].length-1];
        double[][] CRX = new double[dataList.get(3).length-1][dataList.get(3)[0].length-1];
        double[][] YHX = new double[dataList.get(4).length-1][dataList.get(4)[0].length-1];
        double[][] LCX = new double[dataList.get(5).length-1][dataList.get(5)[0].length-1];
        double[][] NCX = new double[dataList.get(6).length-1][dataList.get(6)[0].length-1];
        double[][] ZXX = new double[dataList.get(7).length-1][dataList.get(7)[0].length-1];
        double[][] LCQ = new double[dataList.get(8).length-1][dataList.get(8)[0].length-1];
        double[][] JXX = new double[dataList.get(9).length-1][dataList.get(9)[0].length-1];
        double[][] DXX = new double[dataList.get(10).length-1][dataList.get(10)[0].length-1];
        double[][] GFPY = new double[dataList.get(11).length-1][dataList.get(11)[0].length-1];
        double[][] HMSK = new double[dataList.get(12).length-1][dataList.get(12)[0].length-1];
        double[][] LFSK = new double[dataList.get(13).length-1][dataList.get(13)[0].length-1];
        for (int i = 1; i < dataList.get(0).length; i++) {
            for (int j = 1; j < dataList.get(0)[i].length; j++) {
                GCX[i-1][j-1]=(double)dataList.get(0)[i][j];
                NFX[i-1][j-1]=(double)dataList.get(1)[i][j];
                LAX[i-1][j-1]=(double)dataList.get(2)[i][j];
                CRX[i-1][j-1]=(double)dataList.get(3)[i][j];
                YHX[i-1][j-1]=(double)dataList.get(4)[i][j];
                LCX[i-1][j-1]=(double)dataList.get(5)[i][j];
                NCX[i-1][j-1]=(double)dataList.get(6)[i][j];
                ZXX[i-1][j-1]=(double)dataList.get(7)[i][j];
                LCQ[i-1][j-1]=(double)dataList.get(8)[i][j];
                JXX[i-1][j-1]=(double)dataList.get(9)[i][j];
                DXX[i-1][j-1]=(double)dataList.get(10)[i][j];
                GFPY[i-1][j-1]=(double)dataList.get(11)[i][j];
            }
        }
        for (int i = 1; i < dataList.get(12).length; i++) {
            for (int j = 1; j < dataList.get(12)[i].length; j++){
                HMSK[i-1][j-1]=(double)dataList.get(12)[i][j];
                LFSK[i-1][j-1]=(double)dataList.get(13)[i][j];
            }
        }
        for (int i = 0; i <timeLength; i++) {
            GCDistrict.getTimeData().get(i).setSelfProducedInflowTotal(GCX[0][i]);
            GCDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(GCX[1][i]);
            GCDistrict.getTimeData().get(i).setWaterRequirement(GCX[2][i]);
            GCDistrict.getTimeData().get(i).setWaterRequirementPlan(GCX[2][i]);
            GCDistrict.getTimeData().get(i).setIndustrialWaterRatio(GCX[3][i]);
            GCDistrict.getTimeData().get(i).setAgriculturalWaterRatio(GCX[4][i]);
            GCDistrict.getTimeData().get(i).setDomesticWaterRatio(GCX[5][i]);
            GCDistrict.getTimeData().get(i).setForestryWaterRatio(GCX[6][i]);
            GCDistrict.getTimeData().get(i).setIndustrialWaterWeight(GCX[7][i]);
            GCDistrict.getTimeData().get(i).setAgriculturalWaterWeight(GCX[8][i]);
            GCDistrict.getTimeData().get(i).setDomesticWaterWeight(GCX[9][i]);
            GCDistrict.getTimeData().get(i).setForestryWaterWeight(GCX[10][i]);
            GCDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(GCX[11][i]);
            GCDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(GCX[12][i]);
            GCDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(GCX[13][i]);
            GCDistrict.getTimeData().get(i).setForestryWaterReturnRatio(GCX[14][i]);
            GCDistrict.getTimeData().get(i).setInflowFrequency(GCX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            NFDistrict.getTimeData().get(i).setSelfProducedInflowTotal(NFX[0][i]);
            NFDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(NFX[1][i]);
            NFDistrict.getTimeData().get(i).setWaterRequirement(NFX[2][i]);
            NFDistrict.getTimeData().get(i).setWaterRequirementPlan(NFX[2][i]);
            NFDistrict.getTimeData().get(i).setIndustrialWaterRatio(NFX[3][i]);
            NFDistrict.getTimeData().get(i).setAgriculturalWaterRatio(NFX[4][i]);
            NFDistrict.getTimeData().get(i).setDomesticWaterRatio(NFX[5][i]);
            NFDistrict.getTimeData().get(i).setForestryWaterRatio(NFX[6][i]);
            NFDistrict.getTimeData().get(i).setIndustrialWaterWeight(NFX[7][i]);
            NFDistrict.getTimeData().get(i).setAgriculturalWaterWeight(NFX[8][i]);
            NFDistrict.getTimeData().get(i).setDomesticWaterWeight(NFX[9][i]);
            NFDistrict.getTimeData().get(i).setForestryWaterWeight(NFX[10][i]);
            NFDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(NFX[11][i]);
            NFDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(NFX[12][i]);
            NFDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(NFX[13][i]);
            NFDistrict.getTimeData().get(i).setForestryWaterReturnRatio(NFX[14][i]);
            NFDistrict.getTimeData().get(i).setInflowFrequency(NFX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            LADistrict.getTimeData().get(i).setSelfProducedInflowTotal(LAX[0][i]);
            LADistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(LAX[1][i]);
            LADistrict.getTimeData().get(i).setWaterRequirement(LAX[2][i]);
            LADistrict.getTimeData().get(i).setWaterRequirementPlan(LAX[2][i]);
            LADistrict.getTimeData().get(i).setIndustrialWaterRatio(LAX[3][i]);
            LADistrict.getTimeData().get(i).setAgriculturalWaterRatio(LAX[4][i]);
            LADistrict.getTimeData().get(i).setDomesticWaterRatio(LAX[5][i]);
            LADistrict.getTimeData().get(i).setForestryWaterRatio(LAX[6][i]);
            LADistrict.getTimeData().get(i).setIndustrialWaterWeight(LAX[7][i]);
            LADistrict.getTimeData().get(i).setAgriculturalWaterWeight(LAX[8][i]);
            LADistrict.getTimeData().get(i).setDomesticWaterWeight(LAX[9][i]);
            LADistrict.getTimeData().get(i).setForestryWaterWeight(LAX[10][i]);
            LADistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(LAX[11][i]);
            LADistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(LAX[12][i]);
            LADistrict.getTimeData().get(i).setDomesticWaterReturnRatio(LAX[13][i]);
            LADistrict.getTimeData().get(i).setForestryWaterReturnRatio(LAX[14][i]);
            LADistrict.getTimeData().get(i).setInflowFrequency(LAX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            CRDistrict.getTimeData().get(i).setSelfProducedInflowTotal(CRX[0][i]);
            CRDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(CRX[1][i]);
            CRDistrict.getTimeData().get(i).setWaterRequirement(CRX[2][i]);
            CRDistrict.getTimeData().get(i).setWaterRequirementPlan(CRX[2][i]);
            CRDistrict.getTimeData().get(i).setIndustrialWaterRatio(CRX[3][i]);
            CRDistrict.getTimeData().get(i).setAgriculturalWaterRatio(CRX[4][i]);
            CRDistrict.getTimeData().get(i).setDomesticWaterRatio(CRX[5][i]);
            CRDistrict.getTimeData().get(i).setForestryWaterRatio(CRX[6][i]);
            CRDistrict.getTimeData().get(i).setIndustrialWaterWeight(CRX[7][i]);
            CRDistrict.getTimeData().get(i).setAgriculturalWaterWeight(CRX[8][i]);
            CRDistrict.getTimeData().get(i).setDomesticWaterWeight(CRX[9][i]);
            CRDistrict.getTimeData().get(i).setForestryWaterWeight(CRX[10][i]);
            CRDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(CRX[11][i]);
            CRDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(CRX[12][i]);
            CRDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(CRX[13][i]);
            CRDistrict.getTimeData().get(i).setForestryWaterReturnRatio(CRX[14][i]);
            CRDistrict.getTimeData().get(i).setInflowFrequency(CRX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            YHDistrict.getTimeData().get(i).setSelfProducedInflowTotal(YHX[0][i]);
            YHDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(YHX[1][i]);
            YHDistrict.getTimeData().get(i).setWaterRequirement(YHX[2][i]);
            YHDistrict.getTimeData().get(i).setWaterRequirementPlan(YHX[2][i]);
            YHDistrict.getTimeData().get(i).setIndustrialWaterRatio(YHX[3][i]);
            YHDistrict.getTimeData().get(i).setAgriculturalWaterRatio(YHX[4][i]);
            YHDistrict.getTimeData().get(i).setDomesticWaterRatio(YHX[5][i]);
            YHDistrict.getTimeData().get(i).setForestryWaterRatio(YHX[6][i]);
            YHDistrict.getTimeData().get(i).setIndustrialWaterWeight(YHX[7][i]);
            YHDistrict.getTimeData().get(i).setAgriculturalWaterWeight(YHX[8][i]);
            YHDistrict.getTimeData().get(i).setDomesticWaterWeight(YHX[9][i]);
            YHDistrict.getTimeData().get(i).setForestryWaterWeight(YHX[10][i]);
            YHDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(YHX[11][i]);
            YHDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(YHX[12][i]);
            YHDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(YHX[13][i]);
            YHDistrict.getTimeData().get(i).setForestryWaterReturnRatio(YHX[14][i]);
            YHDistrict.getTimeData().get(i).setInflowFrequency(YHX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            LCDistrict.getTimeData().get(i).setSelfProducedInflowTotal(LCX[0][i]);
            LCDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(LCX[1][i]);
            LCDistrict.getTimeData().get(i).setWaterRequirement(LCX[2][i]);
            LCDistrict.getTimeData().get(i).setWaterRequirementPlan(LCX[2][i]);
            LCDistrict.getTimeData().get(i).setIndustrialWaterRatio(LCX[3][i]);
            LCDistrict.getTimeData().get(i).setAgriculturalWaterRatio(LCX[4][i]);
            LCDistrict.getTimeData().get(i).setDomesticWaterRatio(LCX[5][i]);
            LCDistrict.getTimeData().get(i).setForestryWaterRatio(LCX[6][i]);
            LCDistrict.getTimeData().get(i).setIndustrialWaterWeight(LCX[7][i]);
            LCDistrict.getTimeData().get(i).setAgriculturalWaterWeight(LCX[8][i]);
            LCDistrict.getTimeData().get(i).setDomesticWaterWeight(LCX[9][i]);
            LCDistrict.getTimeData().get(i).setForestryWaterWeight(LCX[10][i]);
            LCDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(LCX[11][i]);
            LCDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(LCX[12][i]);
            LCDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(LCX[13][i]);
            LCDistrict.getTimeData().get(i).setForestryWaterReturnRatio(LCX[14][i]);
            LCDistrict.getTimeData().get(i).setInflowFrequency(LCX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            NCConfluenceNode.getTimeData().get(i).setSelfProducedInflowTotal(NCX[0][i]);
            NCConfluenceNode.getTimeData().get(i).setMinInstreamingEcologicalFlow(NCX[1][i]);
            NCConfluenceNode.getTimeData().get(i).setWaterRequirement(NCX[2][i]);
            NCConfluenceNode.getTimeData().get(i).setWaterRequirementPlan(NCX[2][i]);
            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterRatio(NCX[3][i]);
            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterRatio(NCX[4][i]);
            NCConfluenceNode.getTimeData().get(i).setDomesticWaterRatio(NCX[5][i]);
            NCConfluenceNode.getTimeData().get(i).setForestryWaterRatio(NCX[6][i]);
            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterWeight(NCX[7][i]);
            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterWeight(NCX[8][i]);
            NCConfluenceNode.getTimeData().get(i).setDomesticWaterWeight(NCX[9][i]);
            NCConfluenceNode.getTimeData().get(i).setForestryWaterWeight(NCX[10][i]);
            NCConfluenceNode.getTimeData().get(i).setIndustrialWaterReturnRatio(NCX[11][i]);
            NCConfluenceNode.getTimeData().get(i).setAgriculturalWaterReturnRatio(NCX[12][i]);
            NCConfluenceNode.getTimeData().get(i).setDomesticWaterReturnRatio(NCX[13][i]);
            NCConfluenceNode.getTimeData().get(i).setForestryWaterReturnRatio(NCX[14][i]);
            NCConfluenceNode.getTimeData().get(i).setInflowFrequency(NCX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            ZXDistrict.getTimeData().get(i).setSelfProducedInflowTotal(ZXX[0][i]);
            ZXDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(ZXX[1][i]);
            ZXDistrict.getTimeData().get(i).setWaterRequirement(ZXX[2][i]);
            ZXDistrict.getTimeData().get(i).setWaterRequirementPlan(ZXX[2][i]);
            ZXDistrict.getTimeData().get(i).setIndustrialWaterRatio(ZXX[3][i]);
            ZXDistrict.getTimeData().get(i).setAgriculturalWaterRatio(ZXX[4][i]);
            ZXDistrict.getTimeData().get(i).setDomesticWaterRatio(ZXX[5][i]);
            ZXDistrict.getTimeData().get(i).setForestryWaterRatio(ZXX[6][i]);
            ZXDistrict.getTimeData().get(i).setIndustrialWaterWeight(ZXX[7][i]);
            ZXDistrict.getTimeData().get(i).setAgriculturalWaterWeight(ZXX[8][i]);
            ZXDistrict.getTimeData().get(i).setDomesticWaterWeight(ZXX[9][i]);
            ZXDistrict.getTimeData().get(i).setForestryWaterWeight(ZXX[10][i]);
            ZXDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(ZXX[11][i]);
            ZXDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(ZXX[12][i]);
            ZXDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(ZXX[13][i]);
            ZXDistrict.getTimeData().get(i).setForestryWaterReturnRatio(ZXX[14][i]);
            ZXDistrict.getTimeData().get(i).setInflowFrequency(ZXX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setSelfProducedInflowTotal(JXX[0][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setMinInstreamingEcologicalFlow(JXX[1][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setWaterRequirement(JXX[2][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setWaterRequirementPlan(JXX[2][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterRatio(JXX[3][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterRatio(JXX[4][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterRatio(JXX[5][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterRatio(JXX[6][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterWeight(JXX[7][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterWeight(JXX[8][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterWeight(JXX[9][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterWeight(JXX[10][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setIndustrialWaterReturnRatio(JXX[11][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setAgriculturalWaterReturnRatio(JXX[12][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setDomesticWaterReturnRatio(JXX[13][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setForestryWaterReturnRatio(JXX[14][i]);
            virtualNode.getJXConfluenceNode().getTimeData().get(i).setInflowFrequency(JXX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setSelfProducedInflowTotal(LCQ[0][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setMinInstreamingEcologicalFlow(LCQ[1][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setWaterRequirement(LCQ[2][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setWaterRequirementPlan(LCQ[2][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterRatio(LCQ[3][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterRatio(LCQ[4][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterRatio(LCQ[5][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterRatio(LCQ[6][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterWeight(LCQ[7][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterWeight(LCQ[8][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterWeight(LCQ[9][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterWeight(LCQ[10][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setIndustrialWaterReturnRatio(LCQ[11][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setAgriculturalWaterReturnRatio(LCQ[12][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setDomesticWaterReturnRatio(LCQ[13][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setForestryWaterReturnRatio(LCQ[14][i]);
            virtualNode.getLCConfluenceNode().getTimeData().get(i).setInflowFrequency(LCQ[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            DXDistrict.getTimeData().get(i).setSelfProducedInflowTotal(DXX[0][i]);
            DXDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(DXX[1][i]);
            DXDistrict.getTimeData().get(i).setWaterRequirement(DXX[2][i]);
            DXDistrict.getTimeData().get(i).setWaterRequirementPlan(DXX[2][i]);
            DXDistrict.getTimeData().get(i).setIndustrialWaterRatio(DXX[3][i]);
            DXDistrict.getTimeData().get(i).setAgriculturalWaterRatio(DXX[4][i]);
            DXDistrict.getTimeData().get(i).setDomesticWaterRatio(DXX[5][i]);
            DXDistrict.getTimeData().get(i).setForestryWaterRatio(DXX[6][i]);
            DXDistrict.getTimeData().get(i).setIndustrialWaterWeight(DXX[7][i]);
            DXDistrict.getTimeData().get(i).setAgriculturalWaterWeight(DXX[8][i]);
            DXDistrict.getTimeData().get(i).setDomesticWaterWeight(DXX[9][i]);
            DXDistrict.getTimeData().get(i).setForestryWaterWeight(DXX[10][i]);
            DXDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(DXX[11][i]);
            DXDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(DXX[12][i]);
            DXDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(DXX[13][i]);
            DXDistrict.getTimeData().get(i).setForestryWaterReturnRatio(DXX[14][i]);
            DXDistrict.getTimeData().get(i).setInflowFrequency(DXX[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            GFPYDistrict.getTimeData().get(i).setSelfProducedInflowTotal(GFPY[0][i]);
            GFPYDistrict.getTimeData().get(i).setMinInstreamingEcologicalFlow(GFPY[1][i]);
            GFPYDistrict.getTimeData().get(i).setWaterRequirement(GFPY[2][i]);
            GFPYDistrict.getTimeData().get(i).setWaterRequirementPlan(GFPY[2][i]);
            GFPYDistrict.getTimeData().get(i).setIndustrialWaterRatio(GFPY[3][i]);
            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterRatio(GFPY[4][i]);
            GFPYDistrict.getTimeData().get(i).setDomesticWaterRatio(GFPY[5][i]);
            GFPYDistrict.getTimeData().get(i).setForestryWaterRatio(GFPY[6][i]);
            GFPYDistrict.getTimeData().get(i).setIndustrialWaterWeight(GFPY[7][i]);
            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterWeight(GFPY[8][i]);
            GFPYDistrict.getTimeData().get(i).setDomesticWaterWeight(GFPY[9][i]);
            GFPYDistrict.getTimeData().get(i).setForestryWaterWeight(GFPY[10][i]);
            GFPYDistrict.getTimeData().get(i).setIndustrialWaterReturnRatio(GFPY[11][i]);
            GFPYDistrict.getTimeData().get(i).setAgriculturalWaterReturnRatio(GFPY[12][i]);
            GFPYDistrict.getTimeData().get(i).setDomesticWaterReturnRatio(GFPY[13][i]);
            GFPYDistrict.getTimeData().get(i).setForestryWaterReturnRatio(GFPY[14][i]);
            GFPYDistrict.getTimeData().get(i).setInflowFrequency(GFPY[15][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            HMHydroStation.getTimeData().get(i).setSelfProducedInflowTotal(HMSK[0][i]);
            HMHydroStation.getTimeData().get(i).setMinInstreamingEcologicalFlow(HMSK[1][i]);
            HMHydroStation.getTimeData().get(i).setInflowFrequency(HMSK[2][i]);
        }
        for (int i = 0; i <timeLength; i++) {
            LFHydroStation.getTimeData().get(i).setSelfProducedInflowTotal(LFSK[0][i]);
            LFHydroStation.getTimeData().get(i).setMinInstreamingEcologicalFlow(LFSK[1][i]);
            LFHydroStation.getTimeData().get(i).setInflowFrequency(LFSK[2][i]);
        }
        //虚节点分水比例
        for (int i = 0; i <timeLength; i++) {
            virtualNode.getTimeData().get(i).setWeight1(0.5);
            virtualNode.getTimeData().get(i).setWeight2(0.5);
        }

        //初始化河流
        River FHRiver=new River();
        River XJRiver=new River();
        River LSRiver=new River();
        rivers.add(0,FHRiver);
        rivers.add(1,XJRiver);
        rivers.add(2,LSRiver);
        //初始化河流中的节点
        List<Node> nodes1=new ArrayList<>();
        List<Node> nodes2=new ArrayList<>();
        List<Node> nodes3=new ArrayList<>();
        //干流中的节点
        nodes1.add(0,LCDistrict);
        nodes1.add(1,HMHydroStation);
        nodes1.add(2,NCConfluenceNode);
        nodes1.add(3,LFHydroStation);
        nodes1.add(4,ZXDistrict);
        nodes1.add(5,virtualNode);
        nodes1.add(6,DXDistrict);
        nodes1.add(7,GFPYDistrict);
        //盱江中的节点
        nodes2.add(0,GCDistrict);
        nodes2.add(1,NFDistrict);
        //临水中的节点
        nodes3.add(0,LADistrict);
        nodes3.add(1,CRDistrict);
        nodes3.add(2,YHDistrict);
        //同一放到rivers中
        rivers.get(0).setNodes(nodes1);
        rivers.get(1).setNodes(nodes2);
        rivers.get(2).setNodes(nodes3);


        // ********节点类型******
        //盱江
        GCDistrict.setType(1);
        NFDistrict.setType(1);
        //临水
        LADistrict.setType(1);
        CRDistrict.setType(1);
        YHDistrict.setType(1);
        //干流
        LCDistrict.setType(1);
        HMHydroStation.setType(2);
        NCConfluenceNode.setType(4);
        LFHydroStation.setType(2);
        ZXDistrict.setType(1);
        virtualNode.setType(3);
        DXDistrict.setType(1);
        GFPYDistrict.setType(1);
        virtualNode.getJXConfluenceNode().setType(4);
        virtualNode.getLCConfluenceNode().setType(4);

        // ********节点名字******
        //盱江
        GCDistrict.setName("GCX");
        NFDistrict.setName("NFX");
        //临水
        LADistrict.setName("LAX");
        CRDistrict.setName("CRX");
        YHDistrict.setName("YHX");
        //干流
        LCDistrict.setName("LCX");
        HMHydroStation.setName("HMSK");
        NCConfluenceNode.setName("NCX");
        LFHydroStation.setName("LFSK");
        ZXDistrict.setName("ZXX");
        DXDistrict.setName("DXX");
        GFPYDistrict.setName("GFPY");
        virtualNode.getJXConfluenceNode().setName("LCQ");
        virtualNode.getLCConfluenceNode().setName("JXX");

        // ********节点上下游关系******
        //盱江
        GCDistrict.setDownNode(NFDistrict);
        NFDistrict.setDownNode(NCConfluenceNode);
        //临水
        LADistrict.setDownNode(CRDistrict);
        CRDistrict.setDownNode(YHDistrict);
        YHDistrict.setDownNode(virtualNode);
        //干流
        LCDistrict.setDownNode(HMHydroStation);
        HMHydroStation.setDownNode(NCConfluenceNode);
        NCConfluenceNode.setDownNode(LFHydroStation);
        LFHydroStation.setDownNode(ZXDistrict);
        ZXDistrict.setDownNode(virtualNode);
        virtualNode.setDownNode(DXDistrict);
        DXDistrict.setDownNode(GFPYDistrict);
    }
}
