package bigdata2esercizio.bigdata2esercizio;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class CountTrimestreMapper extends Mapper<LongWritable,Text,Text,LongWritable>   {
	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException {
		String[] token = StringUtils.split(value.toString(),"-");
		if(Long.parseLong(token[1])<3){
			String [] prod=StringUtils.split(token[2],",");
			for(int i=1;i<prod.length;i++){
				ctx.write(new Text(prod[i]), new LongWritable(Long.parseLong(token[1])));
			}
				
			}
		
		
		
		
	}

}
