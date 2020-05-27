package waterDispatch;
import java.util.List;

/**
 * A class of Hydrostations
 * 
 * @author zouyibo
 *
 */
public class HydroStation extends Node {

	/** 电站曲线 */
	private Curve curve;

	// **************************电站基本参数*********************************
	/** 校核洪水位 */
	private double levelFloodCheck;
	/** 设计洪水位 */
	private double levelFloodDesign;
	/** 防洪高水位 */
	private double levelFloodControl;
	/** 正常蓄水位 */
	private double levelNormal;
	/** 防洪限制水位 */
	private double levelFloodLimiting;
	/** 死水位 */
	private double levelDead;

	/** 总库容(亿立方) */
	private double storageTotal;
	/** 调洪库容 */
	private double storageControl;
	/** 防洪库容 */
	private double storageProtect;
	/** 调节库容 */
	private double storageRegulating;
	/** 死库容 */
	private double storageDead;

	/** 多年平均发电量(亿度) **/
	private double powerProductionMeanAnnual;
	/** 装机容量(万千瓦) */
	private double powerInstalled;
	/** 保证出力(万千瓦) */
	private double outputGuaranteed;
	/** 平均出力系数 **/
	private double outputCoefficient;
	/** 机组类型数量 */
	private int unitTypeNum;
	/** 机组台数 */
	private int unitNum;
	/** （中间变量）是否处于弃水模式 **/
	private boolean isDsrtMode;

	// **************************电站运行参数*********************************
	/** （短期输入）控制电量 **/
	private double powerPlan;
	/** 时间间隔类型单位为是月 旬 日 */
	private int timeLengtForLong;
	/** 时段间隔长度,单位是分钟 */
	private double timeLengthForShort;

	/** （输入）最大水头 **/
	private double headMax;
	/** （输入）最小水头 **/
	private double headMin;
	/** （输入）最大下泄 **/
	private double outflowMax;
	/** （输入）最小下泄 **/
	private double outflowMin;
	/** （输入）到下游电站的水流时滞 **/
	private int flowDelay;
	/** （输入）日水位涨幅 **/
	private double levelDayChangeUp;
	/** （输入）日水位跌幅 **/
	private double levelDayChangeDown;
	/** （输入）小时水位涨幅 **/
	private double levelHourChangeUp;
	/** （输入）小时水位跌幅 **/
	private double levelHourChangeDown;
	/** （输入）日下游水位变幅 **/
	private double levelDownDayChange;
	/** （输入）小时下游水位变幅 **/
	private double levelDownHourChange;
	/** （输入）爬坡约束 **/
	private double climb;

	public HydroStation(int timelength){
		super(timelength);
		curve=new Curve();
	}






	// **************************约束检查方法***********************************
	/**
	 * 水位约束检查
	 * 
	 * @param waterlevel
	 * @param currentPeriod
	 * @return
	 */
//	public int CheckWaterLevelLimit(double waterlevel, int currentPeriod) {
//		if (waterlevel < timeData.get(currentPeriod).getLevelMin())
//			return 0;
//		else if (waterlevel > timeData.get(currentPeriod).getLevelMax())
//			return 1;
//
//		return 3;
//	}

	/**
	 * 根据初、末水位获取流量差值
	 * 
	 * @param Currentwaterlevel
	 * @param Nextwaterlevel
	 * @param currentPeriod
	 * @return
	 */
	public double GetFluxFromDeltaWaterLevel(double Currentwaterlevel, double Nextwaterlevel, int currentPeriod) {
		double Deltaflux;
		double Currentcontent = 0;
		double Nextcontent = 0;
		Currentcontent = getCurve().getCapacityByLevel(Currentwaterlevel);
		Nextcontent = getCurve().getCapacityByLevel(Nextwaterlevel);
		Deltaflux = (Currentcontent - Nextcontent) * 1E8 / (timeData.get(currentPeriod).getTimeLength());
		return Deltaflux;
	}

	/**
	 * 根据下泄流量和初水位得到末水位
	 * 
	 * @param levelbegin
	 *            初水位
	 * @param deltflow
	 *            下泄流量
	 * @param CurrentPeriod
	 *            当前时段
	 * @return
	 */
	public double GetLevelEndFromLevelBDeltaflow(double levelbegin, double deltflow, int CurrentPeriod) {
		double Currentcontent = 0;
		double nextlevel = 0;
		double Nextcontent;
		Currentcontent = getCurve().getCapacityByLevel(levelbegin);
		Nextcontent = Currentcontent + deltflow * (getTimeData().get(CurrentPeriod).getTimeLength()) / 1E8;
		nextlevel = getCurve().getLevelByCapacity(Nextcontent);
		return nextlevel;

	}

	/**
	 * 根据下泄流量和末水位得到初水位
	 *
	 *            初水位
	 * @param deltflow
	 *            下泄流量
	 * @param CurrentPeriod
	 *            当前时段
	 * @return
	 */
	public double GetLevelBFromLevelEDeltaflow(double levelend, double deltflow, int CurrentPeriod) {
		double begincontent = 0;
		double beginlevel = 0;
		double endcontent;
		endcontent = getCurve().getCapacityByLevel(levelend);
		begincontent = endcontent - deltflow * (getTimeData().get(CurrentPeriod).getTimeLength()) / 1E8;
		beginlevel = getCurve().getLevelByCapacity(begincontent);
		return beginlevel;
	}

//	/**
//	 * 检查流量约束
//	 *
//	 * @param decisionflux
//	 * @param currentPeriod
//	 * @return 0 小于最小流量,1,大于最大流量,2满足流量约束
//	 */
//	public int CheckFluxLimit(double decisionflux, int currentPeriod) {
////		if (decisionflux < (timeData.get(currentPeriod).getOutflowMin() - 1))
////			return 0;
////		else if (decisionflux > (timeData.get(currentPeriod).getOutflowMax() + 1))
////			return 1;
////		else {
////			return 2;
////		}
////	}

	public boolean CheckWaterhead(double decisionWaterHead) {
		if ((decisionWaterHead < headMin - 0.1) || (decisionWaterHead > headMax + 0.1))
			return false;
		return true;
	}
	// **************************Get and Set *********************************

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public Curve getCurve() {
		return curve;
	}

	public void setCurve(Curve curve) {
		this.curve = curve;
	}


	public List<TimeBean> getTimeData() {
		return timeData;
	}

	public void setTimeData(List<TimeBean> timeData) {
		this.timeData = timeData;
	}


	public double getLevelFloodCheck() {
		return levelFloodCheck;
	}

	public void setLevelFloodCheck(double levelFloodCheck) {
		this.levelFloodCheck = levelFloodCheck;
	}

	public double getLevelFloodDesign() {
		return levelFloodDesign;
	}

	public void setLevelFloodDesign(double levelFloodDesign) {
		this.levelFloodDesign = levelFloodDesign;
	}

	public double getLevelFloodControl() {
		return levelFloodControl;
	}

	public void setLevelFloodControl(double levelFloodControl) {
		this.levelFloodControl = levelFloodControl;
	}

	public double getLevelNormal() {
		return levelNormal;
	}

	public void setLevelNormal(double levelNormal) {
		this.levelNormal = levelNormal;
	}

	public double getLevelFloodLimiting() {
		return levelFloodLimiting;
	}

	public void setLevelFloodLimiting(double levelFloodLimiting) {
		this.levelFloodLimiting = levelFloodLimiting;
	}

	public double getLevelDead() {
		return levelDead;
	}

	public void setLevelDead(double levelDead) {
		this.levelDead = levelDead;
	}

	public double getStorageTotal() {
		return storageTotal;
	}

	public void setStorageTotal(double storageTotal) {
		this.storageTotal = storageTotal;
	}

	public double getStorageControl() {
		return storageControl;
	}

	public void setStorageControl(double storageControl) {
		this.storageControl = storageControl;
	}

	public double getStorageProtect() {
		return storageProtect;
	}

	public void setStorageProtect(double storageProtect) {
		this.storageProtect = storageProtect;
	}

	public double getStorageRegulating() {
		return storageRegulating;
	}

	public void setStorageRegulating(double storageRegulating) {
		this.storageRegulating = storageRegulating;
	}

	public double getStorageDead() {
		return storageDead;
	}

	public void setStorageDead(double storageDead) {
		this.storageDead = storageDead;
	}

	public double getPowerProductionMeanAnnual() {
		return powerProductionMeanAnnual;
	}

	public void setPowerProductionMeanAnnual(double powerProductionMeanAnnual) {
		this.powerProductionMeanAnnual = powerProductionMeanAnnual;
	}

	public double getPowerInstalled() {
		return powerInstalled;
	}

	public void setPowerInstalled(double powerInstalled) {
		this.powerInstalled = powerInstalled;
	}

	public double getOutputGuaranteed() {
		return outputGuaranteed;
	}

	public void setOutputGuaranteed(double outputGuaranteed) {
		this.outputGuaranteed = outputGuaranteed;
	}

	public double getOutputCoefficient() {
		return outputCoefficient;
	}

	public void setOutputCoefficient(double outputCoefficient) {
		this.outputCoefficient = outputCoefficient;
	}

	public int getUnitTypeNum() {
		return unitTypeNum;
	}

	public void setUnitTypeNum(int unitTypeNum) {
		this.unitTypeNum = unitTypeNum;
	}

	public int getTimeLengtForLong() {
		return timeLengtForLong;
	}

	public void setTimeLengtForLong(int timeLengtForLong) {
		this.timeLengtForLong = timeLengtForLong;
	}

	public double getTimeLengthForShort() {
		return timeLengthForShort;
	}

	public void setTimeLengthForShort(double timeLengthForShort) {
		this.timeLengthForShort = timeLengthForShort;
	}

	public double getHeadMax() {
		return headMax;
	}

	public void setHeadMax(double headMax) {
		this.headMax = headMax;
	}

	public double getHeadMin() {
		return headMin;
	}

	public void setHeadMin(double headMin) {
		this.headMin = headMin;
	}

	public double getOutflowMax() {
		return outflowMax;
	}

	public void setOutflowMax(double outflowMax) {
		this.outflowMax = outflowMax;
	}

	public double getOutflowMin() {
		return outflowMin;
	}

	public void setOutflowMin(double outflowMin) {
		this.outflowMin = outflowMin;
	}

	public int getFlowDelay() {
		return flowDelay;
	}

	public void setFlowDelay(int flowDelay) {
		this.flowDelay = flowDelay;
	}

	public double getLevelDayChangeUp() {
		return levelDayChangeUp;
	}

	public void setLevelDayChangeUp(double levelDayChangeUp) {
		this.levelDayChangeUp = levelDayChangeUp;
	}

	public double getLevelDayChangeDown() {
		return levelDayChangeDown;
	}

	public void setLevelDayChangeDown(double levelDayChangeDown) {
		this.levelDayChangeDown = levelDayChangeDown;
	}

	public double getLevelHourChangeUp() {
		return levelHourChangeUp;
	}

	public void setLevelHourChangeUp(double levelHourChangeUp) {
		this.levelHourChangeUp = levelHourChangeUp;
	}

	public double getLevelHourChangeDown() {
		return levelHourChangeDown;
	}

	public void setLevelHourChangeDown(double levelHourChangeDown) {
		this.levelHourChangeDown = levelHourChangeDown;
	}

	public double getLevelDownDayChange() {
		return levelDownDayChange;
	}

	public void setLevelDownDayChange(double levelDownDayChange) {
		this.levelDownDayChange = levelDownDayChange;
	}

	public double getLevelDownHourChange() {
		return levelDownHourChange;
	}

	public void setLevelDownHourChange(double levelDownHourChange) {
		this.levelDownHourChange = levelDownHourChange;
	}

	public double getClimb() {
		return climb;
	}

	public void setClimb(double climb) {
		this.climb = climb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(int unitNum) {
		this.unitNum = unitNum;
	}

	public boolean isDsrtMode() {
		return isDsrtMode;
	}

	public void setDsrtMode(boolean isDsrtMode) {
		this.isDsrtMode = isDsrtMode;
	}

	public double getPowerPlan() {
		return powerPlan;
	}

	public void setPowerPlan(double powerPlan) {
		this.powerPlan = powerPlan;
	}
}
