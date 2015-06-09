package bigdata4esercizio.bigdata4esercizio;

import java.io.IOException;
import java.util.LinkedList;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class QuartoEsercizioReducer2 extends Reducer<Text,ProdottoWritable,Text,Text> {
	
	
protected void reduce(Text key, Iterable<ProdottoWritable> values, Context ctx) throws IOException, InterruptedException {	
	ProdottoWritable temp=new ProdottoWritable();
	LinkedList<ProdottoWritable> valueList = new LinkedList<ProdottoWritable>();
	for(ProdottoWritable p:values){
		if(p.getNomeCoppia().equals(key)){
	           temp.setNomeCoppia(p.getNomeCoppia());
	           temp.setQuantita(p.getQuantita());
			}else{
				valueList.add(new ProdottoWritable(p.getNomeCoppia(),p.getQuantita()));
			}
		
	}
	for(ProdottoWritable z:valueList){
		double res=(double)z.getQuantita().get()/(double)temp.getQuantita().get();
		ctx.write(z.getNomeCoppia(),new Text(""+res));
		
		
	}
}

}
