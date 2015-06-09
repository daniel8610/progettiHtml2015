

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortCountMapper extends
Mapper<LongWritable, Text, Text, IntWritable> {
	private static final IntWritable one = new IntWritable(1);
	public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {
		String[] cols = StringUtils.split(value.toString(),",");
		for(int i=1;i<cols.length;i++){
			context.write(new Text(cols[i]), one);
		}
		
		
	}

}
