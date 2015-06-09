package bigdata1esercizio.bigdata1esercizio;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class SortCountReducer2 extends Reducer<LongWritable,Text,Text,LongWritable> {
	public void reduce(LongWritable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
	for(Text prod:values){
		context.write(prod, key);
	}
	}
}
