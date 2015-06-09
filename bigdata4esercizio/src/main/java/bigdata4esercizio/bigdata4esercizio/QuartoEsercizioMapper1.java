package bigdata4esercizio.bigdata4esercizio;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class QuartoEsercizioMapper1 extends Mapper<LongWritable,Text,Text,IntWritable>  {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	
	
	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException  {
		String[] token = StringUtils.split(value.toString(),",");
		String prod;
		for(int i=1;i<token.length;i++){
			ctx.write(new Text(token[i]),ONE);
			for(int j=1;j<token.length;j++){
				if(i!=j){
					prod=token[i]+","+token[j];
					ctx.write(new Text(prod), ONE);
				}
			}
			
		}
		
		
		
		
		
		
	}

}
