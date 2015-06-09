package bigdata3esercizio.bigdata3esercizio;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class top10pairsMapper extends Mapper<LongWritable,Text,Text,IntWritable>  {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	
	
	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException {
		String[] token = StringUtils.split(value.toString(),",");
		PairsWritable p=new PairsWritable();
		String s;
		for(int i=1;i<token.length-1;i++){
			for(int j=i+1;j<token.length;j++){
				if(token[i].compareTo(token[j])<=0){
					s=token[i]+","+token[j];
					ctx.write(new Text(s), ONE);
					}else{
						s=token[j]+","+token[i];
						ctx.write(new Text(s), ONE);
					}
			//p.setCoppia1(new Text(token[i]));
			//p.setCoppia2(new Text(token[j]));
			//ctx.write(p, ONE);
			}
			
		}
		
		
		
		
		
		
		
		
		
	}
}
