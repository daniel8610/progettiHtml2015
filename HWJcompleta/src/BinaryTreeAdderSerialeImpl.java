
public class BinaryTreeAdderSerialeImpl {

	
	
	public static int count(Node node){
		if(node==null)
			return 0;
		FakeProcessor fp=new FakeProcessor(10000);
		int c=fp.onerousFunction(node.getValue());
		
		
		return c+count(node.getDx())+count(node.getSx());
	}
}
