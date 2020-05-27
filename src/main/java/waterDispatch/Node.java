package waterDispatch;

import java.util.ArrayList;
import java.util.List;

public class Node {
    // **************************静态标识************************************
    // **用于判断节点类型(1为普通区县,2为水电站，3为虚拟节点，4为汇流节点)
    public static final int DISTRICT = 1;
    public static final int HYDROSTATION = 2;
    public static final int VIRTUAL_NODE = 3;
    public static final int CONFLUENCE_NODE = 4;

    // **************************节点参数************************************
    /** 节点名 */
     String name;
    /** 节点编号 */
     int id;
    /** 节点类型 */
     int type ;
    /** 下游节点 **/
     Node downNode;
    /** 需水总量 **/
    double waterRequirement;
    /** 节点时序数据 **/
     List<TimeBean> timeData;

    public Node(int timelength) {
        timeData=new ArrayList<>();
        for (int i = 0; i <timelength; i++) {
            TimeBean data=new TimeBean();
            timeData.add(data);
        }
    }

    public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

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



    public Node getDownNode() {
        return downNode;
    }

    public void setDownNode(Node downNode) {
        this.downNode = downNode;
    }

    public double getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(double waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public List<TimeBean> getTimeData() {
        return timeData;
    }

    public void setTimeData(List<TimeBean> timeData) {
        this.timeData = timeData;
    }
}
