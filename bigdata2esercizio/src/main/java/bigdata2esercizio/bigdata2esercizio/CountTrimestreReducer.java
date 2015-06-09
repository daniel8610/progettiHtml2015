package bigdata2esercizio.bigdata2esercizio;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class CountTrimestreReducer extends Reducer<Text,LongWritable,Text,Text>  {
	
	protected void reduce(Text key, Iterable<LongWritable> values, Context ctx) throws IOException, InterruptedException {
		long g,f,m;
		g=0;f=0;m=0;
		for(LongWritable mesi:values){
			if(mesi.get()==0)
				g++;
			if(mesi.get()==1)
				f++;
			if(mesi.get()==2)
				m++;
			
		}
		String s="2015-01: "+g+"  2015-02: "+f+ "  2015-03: "+m;
		ctx.write(key, new Text(s));
		
		
		
	}

}
