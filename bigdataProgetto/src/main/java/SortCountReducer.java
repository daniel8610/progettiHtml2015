

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.SortedSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private PriorityQueue<Coppia> queue;
	
	@Override
    protected void setup(Context ctx) {
      queue = new PriorityQueue<Coppia>(10,new Comparator<Coppia>() {
          public int compare(Coppia p1, Coppia p2) {
              return p1.getCount().compareTo(p2.getCount());
            }});
    }
	public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
		int count=0;
		for(IntWritable i:values){
         count+=i.get();
			}
		queue.add(new Coppia(key.toString(),count));
		//context.write(key, new IntWritable(count));
	}
	@Override
	protected void cleanup(Context ctx) throws IOException, InterruptedException {
		
		while(!queue.isEmpty()){
			Coppia c=queue.remove();
			ctx.write(new Text(c.getProdotto()), new IntWritable(c.getCount()));
			
		}
		
		
		
	}

}
