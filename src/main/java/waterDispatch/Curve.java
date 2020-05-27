package waterDispatch;


import util.DoubleCurve;
import util.TribleCurve;
import java.io.Serializable;
import java.util.Map;

public class Curve implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6649501994266344067L;

	private int stationID;

	/**水位库容 按照水位从小到大排列*/
	private DoubleCurve levelCapacityCurve;
	/**下泄流量下游水位*/
	private DoubleCurve flowLeveldownCurve;
	/**下泄流量下游水位*/
	private TribleCurve threeFlowLeveldownCurve;
	/**下泄能力水位*/
	private DoubleCurve dischargelevelCurve;
	/**水位流量关系*/
	private DoubleCurve levelflowCurve;
	/**入库流量水头损失*/
	private Map<String, DoubleCurve> flowHLossCurve;
	/**水头预想出力*/
	private Map<String, DoubleCurve> headMaxpowerCurve;
	/**NHQ曲线*/
	private Map<String, TribleCurve> UnitNHQ;
	/**全站NHQ曲线*/
	private TribleCurve totalNHQ;



	/****************通用查询方法****************/

	/**
	 * 水位查库容
	 *
	 * @param waterLevel 水位 单位米
	 * @return 库容 单位百万立方米
	 */
	public  double getCapacityByLevel(double waterLevel) {
		return getLevel_Capacity().getV1ByV0(waterLevel);
	}

	/**
	 * 库容查水位
	 *
	 * @param capacity 库容
	 * @return 水位
	 */
	public  double getLevelByCapacity(double capacity) {
		return getLevel_Capacity().getV0ByV1(capacity);
	}

	/**
	 * 水位查下泄能力
	 * @param level 水位
	 * @return 下泄能力
	 */
	public  double getDischargeByLevel(double level) {
		return getDischarge_Level().getV1ByV0(level);
	}
	/**
	 * 水位查流量
	 * @param level 水位
	 * @return 流量
	 */
	public  double getflowByLevel(double level) {
		return getflow_Level().getV1ByV0(level);
	}

	/**
	 * 下泄流量下游水位(适用有顶托水库)
	 *
	 * @param downstreamlevel 下游水位
	 * @param outflow 下泄流量
	 * @return
	 */
	public  double getLeveldownByOutflow(double downstreamlevel, double outflow) {

		return getThreeFlowLeveldownCurve().getV2ByV0V1(downstreamlevel,outflow);
	}

	/**
	 * 下泄流量查下游水位(适用无顶托水库)
	 *
	 * @param outflow 下泄流量
	 * @return
	 */
	public  double getLeveldownByOutflow(double outflow) {
		return getFlow_LevelDown().getV1ByV0(outflow);
	}

	/**
	 * 通过水头查询机组预想出力
	 *
	 * @param num 机组编号
	 * @param H 水头
	 * @return
	 */
	public  double getExpectPowerByHead(int num, double H) {
		return getH_MaxPower(num).getV0ByV1(H);
	}


	/**
	 * 发电引用流量水头损失 (很多电站没有)
	 * @param num 机组编号
	 * @param flow 下泄流量
	 * @return
	 */
	public double getHeadLossByflow(int num, double flow) {
		double result = getFlow_HLoss(num).getV1ByV0(flow);
		return result>0?result:0;
	}

	/**
	 * 通过水头流量查机组出力
	 * @param num 机组编号
	 * @param H 水头
	 * @param Q 流量
	 * @return
	 */
	public double getPowrByHeadFlow(int num, double H, double Q) {
		return getUnitNHQ(num).getV2ByV0V1(H, Q);
	}

	/**
	 * 通过水头出力查机组流量
	 *
	 * @param num 机组编号
	 * @param H 水头
	 * @param N 出力
	 * @return
	 */
	public double getFlowByHeadPower(int num, double H, double N) {
		return getUnitNHQ(num).getV1ByV0V2(H, N);
	}

	/**
	 * 获取某水头下的所有出力值
	 * @param num 机组编号
	 * @param head 水头
	 * @return
	 */
	public double[] getPowersByHead(int num,double head)
	{
		return getUnitNHQ(num).getV2sByV0(head);
	}

	/**
	 * 获取某水头下的最小出力
	 * @param num
	 * @param head
	 * @return
	 */
	public double getPowerMinByHead(int num,double head)
	{
		double[] temp = getUnitNHQ(num).getV2sByV0(head);
		return temp[0];
	}

	/**
	 * 获取某水头下的最大出力
	 * @param num
	 * @param head
	 * @return
	 */
	public double getPowerMaxByHead(int num,double head)
	{
		double[] temp = getUnitNHQ(num).getV2sByV0(head);
		return temp[temp.length-1];
	}


	/**
	 *  获取某水头下的所有流量值
	 * @param num 机组编号
	 * @param head 水头
	 * @return
	 */
	public double[] getFlowsByHead(int num,double head)
	{
		return getUnitNHQ(num).getV1sByV0(head);
	}


	/**
	 * (全站)获取全站最大下泄流量
	 * @param head
	 * @return
	 */
	public double getMaxFlowByHead(double head)
	{
		double[] powers= getTotalNHQ().getV1sByV0(head);
		return powers[powers.length-1];
	}

	/**
	 * (全站)根据毛水头和发电流量查出力
	 * @param head
	 * @param flow
	 * @return
	 */
	public double getPowerByHeadFlow(double head,double flow)
	{
		return getTotalNHQ().getV2ByV0V1(head, flow);
	}

	/**
	 * (全站)根据毛水头和出力查发电流量
	 * @param head
	 * @param power
	 * @return
	 */
	public double getFlowByHeadPower(double head,double power)
	{
		return getTotalNHQ().getV1ByV0V2(head, power);
	}


	private synchronized DoubleCurve getLevel_Capacity() {
		return levelCapacityCurve;
	}

	private synchronized DoubleCurve getDischarge_Level() {
		return dischargelevelCurve;
	}
	private synchronized DoubleCurve getflow_Level() {
		return levelflowCurve;
	}
	public synchronized TribleCurve getThreeFlowLeveldownCurve() {
		return threeFlowLeveldownCurve;
	}

	public void setThreeFlowLeveldownCurve(TribleCurve threeFlowLeveldownCurve) {
		this.threeFlowLeveldownCurve = threeFlowLeveldownCurve;
	}

	private synchronized DoubleCurve getFlow_LevelDown() {
		return flowLeveldownCurve;
	}

	private synchronized DoubleCurve getFlow_HLoss(int num) {

		return flowHLossCurve.get("unitno"+num);
	}

	private synchronized DoubleCurve getH_MaxPower(int num) {

		return headMaxpowerCurve.get("unitno"+num);
	}

	/**
	 * 获取机组的NHQ曲线,一台机组则为为0
	 * @param num
	 * @return
	 */
	private synchronized TribleCurve getUnitNHQ(int num) {

		return UnitNHQ.get("unitno"+num);
	}


	/**
	 * 获取机组的NHQ曲线,一台机组则为为0
	 * @param
	 * @return
	 */
	public synchronized TribleCurve getTotalNHQ() {
		return totalNHQ;
	}


	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public  void clean(){
		flowHLossCurve = null;
		flowLeveldownCurve = null;
		headMaxpowerCurve = null;
		levelCapacityCurve = null;
		flowHLossCurve = null;
		UnitNHQ = null;
		totalNHQ = null;
	}

	public DoubleCurve getLevelCapacityCurve() {
		return levelCapacityCurve;
	}

	public void setLevelCapacityCurve(DoubleCurve levelCapacityCurve) {
		this.levelCapacityCurve = levelCapacityCurve;
	}

	public DoubleCurve getFlowLeveldownCurve() {
		return flowLeveldownCurve;
	}

	public void setFlowLeveldownCurve(DoubleCurve flowLeveldownCurve) {
		this.flowLeveldownCurve = flowLeveldownCurve;
	}

	public DoubleCurve getDischargelevelCurve() {
		return dischargelevelCurve;
	}

	public void setDischargelevelCurve(DoubleCurve dischargelevelCurve) {
		this.dischargelevelCurve = dischargelevelCurve;
	}
	public DoubleCurve getLevelflowCurve() {
		return levelflowCurve;
	}

	public void setLevelflowCurve(DoubleCurve levelflowCurve) {
		this.levelflowCurve = levelflowCurve;
	}

	public Map<String, DoubleCurve> getFlowHLossCurve() {
		return flowHLossCurve;
	}

	public void setFlowHLossCurve(Map<String, DoubleCurve> flowHLossCurve) {
		this.flowHLossCurve = flowHLossCurve;
	}

	public Map<String, DoubleCurve> getHeadMaxpowerCurve() {
		return headMaxpowerCurve;
	}

	public void setHeadMaxpowerCurve(Map<String, DoubleCurve> headMaxpowerCurve) {
		this.headMaxpowerCurve = headMaxpowerCurve;
	}

	public Map<String, TribleCurve> getUnitNHQ() {
		return UnitNHQ;
	}

	public void setUnitNHQ(Map<String, TribleCurve> unitNHQ) {
		UnitNHQ = unitNHQ;
	}

	public void setTotalNHQ(TribleCurve totalNHQ) {
		this.totalNHQ = totalNHQ;
	}

}
