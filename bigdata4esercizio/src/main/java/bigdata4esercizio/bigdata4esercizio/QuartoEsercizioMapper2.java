package bigdata4esercizio.bigdata4esercizio;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class QuartoEsercizioMapper2 extends Mapper<Text,Text,Text,ProdottoWritable>  {
	
	
	
	protected void map(Text key, Text value, Context ctx) throws IOException, InterruptedException  {
		ProdottoWritable prod = new ProdottoWritable();
		String[] token = StringUtils.split(key.toString(),",");
		new IntWritable(Integer.parseInt(value.toString()));
		prod.setNomeCoppia(key);
		prod.setQuantita(new IntWritable(Integer.parseInt(value.toString())));
		
		
		
		
		ctx.write(new Text(token[0]), prod);
		
		
	}

}
