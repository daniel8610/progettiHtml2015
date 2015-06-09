import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ConcurrentDownloader {
	private final static int NCPU =Runtime.getRuntime().availableProcessors();
	private int partenza;
	private StringBuilder urlbase;
	public ConcurrentDownloader(int partenza,String urlbase){
		this.partenza=partenza;
		this.urlbase=new StringBuilder(urlbase);
	}
	public  void multiDownload() throws MalformedURLException{
		ExecutorService pool=Executors.newFixedThreadPool(NCPU);
		for(int i=0;i<7;i++){
			StringBuilder sb = new StringBuilder("CSdemo.rar");
			sb.insert(6,i);
			this.partenza=this.partenza+1;
			urlbase.replace(51,urlbase.length() ,new Integer(this.partenza).toString());
			URL url=new URL(urlbase.toString());
			pool.submit(new TaskDownloaderUrl(url,sb.toString()));
		 }
		System.out.println("fine");
	}
}
