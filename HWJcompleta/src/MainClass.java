
public class MainClass {
public static void main(String args[]){
	double tstart,tend,res,res1;
	NodeImpl nx = new NodeImpl(1);
    BinaryTreeAdderImpl b=new BinaryTreeAdderImpl();
    Node alb=AlberoBinarioCasuale.creaAlbero(nx,15);
    for(int i=0;i<10;i++){
    	BinaryTreeAdderImpl c=new BinaryTreeAdderImpl();
    	c.computeOnerousSum(nx);
    }
    tstart=System.currentTimeMillis();
    System.out.println(BinaryTreeAdderSerialeImpl.count(alb));
    tend=System.currentTimeMillis();
    res1=tend-tstart;
    tstart=System.currentTimeMillis();
    System.out.println(b.computeOnerousSum(nx));
    tend=System.currentTimeMillis();
    res=tend-tstart;
	System.out.println("Soluzione Concorrente -> il tempo � in mills:"+res);
	System.out.println("Soluzione Seriale ->il tempo � in mills:"+res1);
	System.out.println("SpeedUp con "+Runtime.getRuntime().availableProcessors()+" core : "+res1/res);
	
    
	
}
}
