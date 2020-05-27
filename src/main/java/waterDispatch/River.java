/*
 * @(#) Algorithm.java           1.0 2015/1/15/ ChaoWang
 *
 * Copyright(C) 2011-2015 ChaoWang   All Rights Reserved
 */
package waterDispatch;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class River implements Serializable{
	private List<Node> nodes;

	public List<Node> getNodes(){
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public River() {
		nodes = new ArrayList<Node>();
	}

	/**
	 * 通过Name获取子元素
	 * @param name
	 * @return
	 */
	public Node getNodeByName(String name){

		for(Node node:getNodes()){
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}

	/**
	 * 通过id获取子元素
	 * @param id
	 * @return
	 */
	public Node getNodeByID(int id){
		for(Node node:getNodes()){
			if(node.getId()==(id)){
				return node;
			}
		}
		return null;
	}
}
