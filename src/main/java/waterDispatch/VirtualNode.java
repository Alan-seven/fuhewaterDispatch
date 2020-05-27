package waterDispatch;

public class VirtualNode extends Node {
    private ConfluenceNode LCConfluenceNode;
    private ConfluenceNode JXConfluenceNode;
    public VirtualNode(int timelength){
        super(timelength);
        LCConfluenceNode=new ConfluenceNode(timelength);
        JXConfluenceNode=new ConfluenceNode(timelength);
    }
    public ConfluenceNode getLCConfluenceNode() {
        return LCConfluenceNode;
    }

    public void setLCConfluenceNode(ConfluenceNode LCConfluenceNode) {
        this.LCConfluenceNode = LCConfluenceNode;
    }

    public ConfluenceNode getJXConfluenceNode() {
        return JXConfluenceNode;
    }

    public void setJXConfluenceNode(ConfluenceNode JXConfluenceNode) {
        this.JXConfluenceNode = JXConfluenceNode;
    }


}
