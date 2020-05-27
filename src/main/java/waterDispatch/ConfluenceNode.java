package waterDispatch;

public class ConfluenceNode extends Node {
    /**上游汇入点1*/
    private Node upNode1;
    /**上游汇入点2*/
    private Node upNode2;

    public ConfluenceNode(int timelength){
        super(timelength);
    }
}
