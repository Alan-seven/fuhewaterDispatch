package waterDispatch.entity;

import java.util.List;

public class ResultEntity {

    List<Double[]>inflowTotal;
    List<Double[]>consumptionTotal;//用水总量
    List<Double[]>outflowTotal;//下泄总量
    List<Double[]>waterRequirement;//申报总需水量
    List<Double[]>waterRequirementPlan;//计划总需水量
    List<Double[]>WaterRequirementFillRates;//需水满足率
    List<Double[]>industrialWaterRequirement;//工业需水量
    List<Double[]>agriculturalWaterRequirement;//农业需水量
    List<Double[]>domesticWaterRequirement;//生活需水量
    List<Double[]>forestryWaterRequirement;//林牧渔需水量
    List<Double[]>industrialWaterRequirementPlan;//计划分配工业需水量
    List<Double[]>agriculturalWaterRequirementPlan;//计划分配农业需水量
    List<Double[]>domesticWaterRequirementPlan;//计划分配生活需水量
    List<Double[]>forestryWaterRequirementPlan;//计划分配林牧渔需水量

    Double[]HMSKinflowTotal;
    Double[]HMSKoutflowTotal;
    Double[]HMSKlevel;

    Double[]LFSKinflowTotal;
    Double[]LFSKoutflowTotal;
    Double[]LFSKlevel;

    public List<Double[]> getInflowTotal() {
        return inflowTotal;
    }

    public void setInflowTotal(List<Double[]> inflowTotal) {
        this.inflowTotal = inflowTotal;
    }

    public List<Double[]> getConsumptionTotal() {
        return consumptionTotal;
    }

    public void setConsumptionTotal(List<Double[]> consumptionTotal) {
        this.consumptionTotal = consumptionTotal;
    }

    public List<Double[]> getOutflowTotal() {
        return outflowTotal;
    }

    public void setOutflowTotal(List<Double[]> outflowTotal) {
        this.outflowTotal = outflowTotal;
    }

    public List<Double[]> getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(List<Double[]> waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public List<Double[]> getWaterRequirementPlan() {
        return waterRequirementPlan;
    }

    public void setWaterRequirementPlan(List<Double[]> waterRequirementPlan) {
        this.waterRequirementPlan = waterRequirementPlan;
    }

    public List<Double[]> getWaterRequirementFillRates() {
        return WaterRequirementFillRates;
    }

    public void setWaterRequirementFillRates(List<Double[]> waterRequirementFillRates) {
        WaterRequirementFillRates = waterRequirementFillRates;
    }

    public List<Double[]> getIndustrialWaterRequirement() {
        return industrialWaterRequirement;
    }

    public void setIndustrialWaterRequirement(List<Double[]> industrialWaterRequirement) {
        this.industrialWaterRequirement = industrialWaterRequirement;
    }

    public List<Double[]> getAgriculturalWaterRequirement() {
        return agriculturalWaterRequirement;
    }

    public void setAgriculturalWaterRequirement(List<Double[]> agriculturalWaterRequirement) {
        this.agriculturalWaterRequirement = agriculturalWaterRequirement;
    }

    public List<Double[]> getDomesticWaterRequirement() {
        return domesticWaterRequirement;
    }

    public void setDomesticWaterRequirement(List<Double[]> domesticWaterRequirement) {
        this.domesticWaterRequirement = domesticWaterRequirement;
    }

    public List<Double[]> getForestryWaterRequirement() {
        return forestryWaterRequirement;
    }

    public void setForestryWaterRequirement(List<Double[]> forestryWaterRequirement) {
        this.forestryWaterRequirement = forestryWaterRequirement;
    }

    public List<Double[]> getIndustrialWaterRequirementPlan() {
        return industrialWaterRequirementPlan;
    }

    public void setIndustrialWaterRequirementPlan(List<Double[]> industrialWaterRequirementPlan) {
        this.industrialWaterRequirementPlan = industrialWaterRequirementPlan;
    }

    public List<Double[]> getAgriculturalWaterRequirementPlan() {
        return agriculturalWaterRequirementPlan;
    }

    public void setAgriculturalWaterRequirementPlan(List<Double[]> agriculturalWaterRequirementPlan) {
        this.agriculturalWaterRequirementPlan = agriculturalWaterRequirementPlan;
    }

    public List<Double[]> getDomesticWaterRequirementPlan() {
        return domesticWaterRequirementPlan;
    }

    public void setDomesticWaterRequirementPlan(List<Double[]> domesticWaterRequirementPlan) {
        this.domesticWaterRequirementPlan = domesticWaterRequirementPlan;
    }

    public List<Double[]> getForestryWaterRequirementPlan() {
        return forestryWaterRequirementPlan;
    }

    public void setForestryWaterRequirementPlan(List<Double[]> forestryWaterRequirementPlan) {
        this.forestryWaterRequirementPlan = forestryWaterRequirementPlan;
    }

    public Double[] getHMSKinflowTotal() {
        return HMSKinflowTotal;
    }

    public void setHMSKinflowTotal(Double[] HMSKinflowTotal) {
        this.HMSKinflowTotal = HMSKinflowTotal;
    }

    public Double[] getHMSKoutflowTotal() {
        return HMSKoutflowTotal;
    }

    public void setHMSKoutflowTotal(Double[] HMSKoutflowTotal) {
        this.HMSKoutflowTotal = HMSKoutflowTotal;
    }

    public Double[] getHMSKlevel() {
        return HMSKlevel;
    }

    public void setHMSKlevel(Double[] HMSKlevel) {
        this.HMSKlevel = HMSKlevel;
    }

    public Double[] getLFSKinflowTotal() {
        return LFSKinflowTotal;
    }

    public void setLFSKinflowTotal(Double[] LFSKinflowTotal) {
        this.LFSKinflowTotal = LFSKinflowTotal;
    }

    public Double[] getLFSKoutflowTotal() {
        return LFSKoutflowTotal;
    }

    public void setLFSKoutflowTotal(Double[] LFSKoutflowTotal) {
        this.LFSKoutflowTotal = LFSKoutflowTotal;
    }

    public Double[] getLFSKlevel() {
        return LFSKlevel;
    }

    public void setLFSKlevel(Double[] LFSKlevel) {
        this.LFSKlevel = LFSKlevel;
    }
}
