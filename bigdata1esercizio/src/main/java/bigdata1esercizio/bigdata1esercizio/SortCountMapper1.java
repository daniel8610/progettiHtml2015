package bigdata1esercizio.bigdata1esercizio;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.commons.lang.StringUtils;




public class SortCountMapper1 extends Mapper<LongWritable,Text,Text,LongWritable>  {
	
	private static final LongWritable ONE = new LongWritable(1);
	
	
	
	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException {
		String[] token = StringUtils.split(value.toString(),",");
		for(int i=1;i<token.length;i++){
			ctx.write(new Text(token[i]), ONE);
		}
	    }
	  }
	
	
	

