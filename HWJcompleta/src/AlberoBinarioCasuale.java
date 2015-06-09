
public class AlberoBinarioCasuale {
	public static Node creaAlbero(NodeImpl n,int lv){
		NodeImpl n1=new NodeImpl(1);
		NodeImpl n2=new NodeImpl(1);
		n.setDx(n1);
		n.setSx(n2);
		lv--;
		if(lv!=0){
		creaAlbero(n1,lv);
		creaAlbero(n2,lv);
		}
		
		return n;
		
	}
	public static Node creaAlberosbilanciato(NodeImpl n,int lv){
		Node nx;
		
		
		
		
		
		return n;
	}
}
