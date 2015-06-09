package bigdata3esercizio.bigdata3esercizio;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;




public class top10pairsReducer extends Reducer<Text,IntWritable,Text,IntWritable>  {
	
	protected void reduce(Text key, Iterable<IntWritable> values, Context ctx) throws IOException, InterruptedException {
		
		int c=0;
		for(IntWritable i:values){
			c=c+i.get();
		}
		
		
		
		ctx.write(new Text(key.toString()), new IntWritable(c));
		
		
		
	}
	
	
	
	
	

}
