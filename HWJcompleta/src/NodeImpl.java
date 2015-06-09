
public class NodeImpl implements Node {
  private int value;
  private Node nodosx;
  private Node nododx;

  public NodeImpl(int value){
	  this.value=value;
	  this.nododx=null;
	  this.nodosx=null;
	  
	  
  }
    public void setSx(Node n) {
	 this.nodosx=n;
	}
    public void setDx(Node n) {
   	 this.nododx=n;
   	}
    
  
	public Node getSx() {
		return this.nodosx;
	}

	
	public Node getDx() {
		return this.nododx;
	}

	
	public int getValue() {
		return this.value;
	}

}
