import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;


public class BinaryTreeAdderImpl implements BinaryTreeAdder {
  private ExecutorService pool;
  private final static int NCPU =Runtime.getRuntime().availableProcessors();
	
public 	BinaryTreeAdderImpl(){
	this.pool=Executors.newFixedThreadPool(NCPU);
}

public int computeOnerousSum(Node root) {
	BlockingDeque<Future<Integer>> risultati=new LinkedBlockingDeque<Future<Integer>>();
	BlockingDeque<Node> buffer=new LinkedBlockingDeque<Node>();
	try {
		buffer.put(root);
		
	   risultati.add(this.pool.submit(new TaskNode(buffer,this.pool,risultati)));
		
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	int res=0;
	while(risultati.size()!=0){
		try {
			res+=risultati.take().get().intValue();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	pool.shutdown();
	return res;
}

	
	
}