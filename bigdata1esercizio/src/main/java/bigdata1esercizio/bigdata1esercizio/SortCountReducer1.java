package bigdata1esercizio.bigdata1esercizio;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortCountReducer1 extends Reducer<Text,LongWritable,Text,LongWritable> {
	
	
protected void reduce(Text key, Iterable<LongWritable> values, Context ctx) throws IOException, InterruptedException {	
	long count=0;
	for(LongWritable i:values){
     count+=i.get();
		}
	ctx.write(key, new LongWritable(count));
	

}	
}
