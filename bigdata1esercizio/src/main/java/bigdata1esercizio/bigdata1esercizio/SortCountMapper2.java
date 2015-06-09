package bigdata1esercizio.bigdata1esercizio;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortCountMapper2 extends Mapper<Text,Text,LongWritable,Text>  {
	protected void map(Text key, Text value, Context ctx)throws IOException, InterruptedException {
		ctx.write(new LongWritable(Long.parseLong(value.toString())), key);
	}
}
