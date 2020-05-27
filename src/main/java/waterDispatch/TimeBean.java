package waterDispatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeBean implements Serializable{

	//**************************基本信息*********************************
	/**起始时间*/
	private Date timeStart;
	/**终止时间*/
	private Date timeEnd;
	/**时段长度(按秒)*/
	private double timeLength;

	/**（输入）上游总来水量**/
	private double UpstreamInflowTotal;
	/**（输入）自产总来水量**/
	private double selfProducedInflowTotal;
	/**（输入）河道最小生态流量*/
	private double minInstreamingEcologicalFlow;


	/**（输出）总来水量**/
	private double inflowTotal;
	/**（输出）总用水量**/
	private double consumptionTotal;
	/**（输出）总泄水量**/
	private double outflowTotal;

	//**************************电站数据*********************************
	/**（输入）初水位**/
	private double levelBegin;
	/**（输出）末水位**/
	private double levelEnd;

	/**（输出）时刻水位**/
	private double level;

	//**************************区县数据*********************************
	/**（输入）需水量**/
	private double waterRequirement;
	/**（输出）计划需水量**/
	private double waterRequirementPlan;
	//*******用水系数********
	/**（输入）工业用水系数**/
	private double industrialWaterRatio;
	/**（输入）农业用水系数**/
	private double agriculturalWaterRatio;
	/**（输入）生活用水系数**/
	private double domesticWaterRatio;
	/**（输入）林牧渔业用水系数**/
	private double forestryWaterRatio;

	//*******用水比例********
	/**（输入）工业用水权重**/
	private double industrialWaterWeight;
	/**（输入）农业用水权重**/
	private double agriculturalWaterWeight;
	/**（输入）生活用水权重**/
	private double domesticWaterWeight;
	/**（输入）林牧渔业用水权重**/
	private double forestryWaterWeight;

	/**（输入）工业用水回归系数**/
	private double industrialWaterReturnRatio;
	/**（输入）农业用水回归系数**/
	private double agriculturalWaterReturnRatio;
	/**（输入）生活用水回归系数**/
	private double domesticWaterReturnRatio;
	/**（输入）林牧渔业用水回归系数**/
	private double forestryWaterReturnRatio;

//*************************虚拟节点数据*********************************
	/**（输入）权重系数系数1(JXX)**/
	private double weight1;
	/**（输入）权重系数系数2(LCQ)**/
	private double weight2;

//*************************汇流节点数据*********************************
	/**（输入）上游总来水量**/
	private double UpstreamInflowTotal1;//（支流）
	private double UpstreamInflowTotal2;//（干流）


	/** 节点来水频率 */
	double inflowFrequency;

	public double getInflowFrequency() {
		return inflowFrequency;
	}

	public void setInflowFrequency(double inflowFrequency) {
		this.inflowFrequency = inflowFrequency;
	}







	public double getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(double timeLength) {
		this.timeLength = timeLength;
	}

	public double getLevelBegin() {
		return levelBegin;
	}

	public void setLevelBegin(double levelBegin) {
		this.levelBegin = levelBegin;
	}

	public double getLevelEnd() {
		return levelEnd;
	}

	public void setLevelEnd(double levelEnd) {
		this.levelEnd = levelEnd;
	}

	public double getUpstreamInflowTotal() {
		return UpstreamInflowTotal;
	}

	public void setUpstreamInflowTotal(double upstreamInflowTotal) {
		UpstreamInflowTotal = upstreamInflowTotal;
	}

	public double getSelfProducedInflowTotal() {
		return selfProducedInflowTotal;
	}

	public void setSelfProducedInflowTotal(double selfProducedInflowTotal) {
		this.selfProducedInflowTotal = selfProducedInflowTotal;
	}

	public double getOutflowTotal() {
		return outflowTotal;
	}

	public void setOutflowTotal(double outflowTotal) {
		this.outflowTotal = outflowTotal;
	}

	public double getMinInstreamingEcologicalFlow() {
		return minInstreamingEcologicalFlow;
	}

	public void setMinInstreamingEcologicalFlow(double minInstreamingEcologicalFlow) {
		this.minInstreamingEcologicalFlow = minInstreamingEcologicalFlow;
	}

	public double getIndustrialWaterRatio() {
		return industrialWaterRatio;
	}

	public void setIndustrialWaterRatio(double industrialWaterRatio) {
		this.industrialWaterRatio = industrialWaterRatio;
	}

	public double getAgriculturalWaterRatio() {
		return agriculturalWaterRatio;
	}

	public void setAgriculturalWaterRatio(double agriculturalWaterRatio) {
		this.agriculturalWaterRatio = agriculturalWaterRatio;
	}

	public double getDomesticWaterRatio() {
		return domesticWaterRatio;
	}

	public void setDomesticWaterRatio(double domesticWaterRatio) {
		this.domesticWaterRatio = domesticWaterRatio;
	}

	public double getForestryWaterRatio() {
		return forestryWaterRatio;
	}

	public void setForestryWaterRatio(double forestryWaterRatio) {
		this.forestryWaterRatio = forestryWaterRatio;
	}

	public double getIndustrialWaterReturnRatio() {
		return industrialWaterReturnRatio;
	}

	public void setIndustrialWaterReturnRatio(double industrialWaterReturnRatio) {
		this.industrialWaterReturnRatio = industrialWaterReturnRatio;
	}

	public double getAgriculturalWaterReturnRatio() {
		return agriculturalWaterReturnRatio;
	}

	public void setAgriculturalWaterReturnRatio(double agriculturalWaterReturnRatio) {
		this.agriculturalWaterReturnRatio = agriculturalWaterReturnRatio;
	}

	public double getDomesticWaterReturnRatio() {
		return domesticWaterReturnRatio;
	}

	public void setDomesticWaterReturnRatio(double domesticWaterReturnRatio) {
		this.domesticWaterReturnRatio = domesticWaterReturnRatio;
	}

	public double getForestryWaterReturnRatio() {
		return forestryWaterReturnRatio;
	}

	public void setForestryWaterReturnRatio(double forestryWaterReturnRatio) {
		this.forestryWaterReturnRatio = forestryWaterReturnRatio;
	}

	public double getWaterRequirement() {
		return waterRequirement;
	}

	public void setWaterRequirement(double waterRequirement) {
		this.waterRequirement = waterRequirement;
	}

	public double getIndustrialWaterWeight() {
		return industrialWaterWeight;
	}

	public void setIndustrialWaterWeight(double industrialWaterWeight) {
		this.industrialWaterWeight = industrialWaterWeight;
	}

	public double getAgriculturalWaterWeight() {
		return agriculturalWaterWeight;
	}

	public void setAgriculturalWaterWeight(double agriculturalWaterWeight) {
		this.agriculturalWaterWeight = agriculturalWaterWeight;
	}

	public double getDomesticWaterWeight() {
		return domesticWaterWeight;
	}

	public void setDomesticWaterWeight(double domesticWaterWeight) {
		this.domesticWaterWeight = domesticWaterWeight;
	}

	public double getForestryWaterWeight() {
		return forestryWaterWeight;
	}

	public void setForestryWaterWeight(double forestryWaterWeight) {
		this.forestryWaterWeight = forestryWaterWeight;
	}

	public double getWeight1() {
		return weight1;
	}

	public void setWeight1(double weight1) {
		this.weight1 = weight1;
	}

	public double getWeight2() {
		return weight2;
	}

	public void setWeight2(double weight2) {
		this.weight2 = weight2;
	}

	public double getInflowTotal() {
		return inflowTotal;
	}

	public void setInflowTotal(double inflowTotal) {
		this.inflowTotal = inflowTotal;
	}

	public double getConsumptionTotal() {
		return consumptionTotal;
	}

	public void setConsumptionTotal(double consumptionTotal) {
		this.consumptionTotal = consumptionTotal;
	}

	public double getUpstreamInflowTotal1() {
		return UpstreamInflowTotal1;
	}

	public void setUpstreamInflowTotal1(double upstreamInflowTotal1) {
		UpstreamInflowTotal1 = upstreamInflowTotal1;
	}

	public double getUpstreamInflowTotal2() {
		return UpstreamInflowTotal2;
	}

	public void setUpstreamInflowTotal2(double upstreamInflowTotal2) {
		UpstreamInflowTotal2 = upstreamInflowTotal2;
	}

	public double getWaterRequirementPlan() {
		return waterRequirementPlan;
	}

	public void setWaterRequirementPlan(double waterRequirementPlan) {
		this.waterRequirementPlan = waterRequirementPlan;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}
}
