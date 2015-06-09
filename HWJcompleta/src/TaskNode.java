import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class TaskNode implements Callable<Integer> {
 private BlockingDeque<Node>  buffer;
 private ExecutorService pool;
 private BlockingDeque<Future<Integer>> risultati;
	public TaskNode(BlockingDeque<Node> buffer, ExecutorService pool,BlockingDeque<Future<Integer>> risultati){
		  this.buffer=buffer;
		  this.pool=pool;
		  this.risultati=risultati;
		
		
	}
	
	
	
	public Integer call() throws Exception {
		OnerousProcessor processor = new FakeProcessor(10000);
		Node nodoCorrente=this.buffer.take();
		Integer result=processor.onerousFunction(nodoCorrente.getValue());
		if(nodoCorrente.getDx()!=null){
			this.buffer.put(nodoCorrente.getDx());
		    risultati.put(pool.submit(new TaskNode(this.buffer,this.pool,this.risultati)));
		}
		if(nodoCorrente.getSx()!=null){
			this.buffer.put(nodoCorrente.getSx());
			risultati.put(pool.submit(new TaskNode(this.buffer,this.pool,this.risultati)));
		}
		return result;
	}

}
