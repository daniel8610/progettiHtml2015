package bigdata3esercizio.bigdata3esercizio;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;

public class prova {
	public static void main(String []args){
		PairsWritable p,p1;
		p=new PairsWritable();
		p1=new PairsWritable();
		p.setCoppia1(new Text("gelato"));
		p.setCoppia2(new Text("vino"));
		p1.setCoppia1(new Text("vino"));
		p1.setCoppia2(new Text("gelato"));
		System.out.println("casa".hashCode());
		
		
		
		}
}
