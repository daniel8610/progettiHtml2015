import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.Callable;



public class TaskDownloaderUrl implements Callable<Integer>{

	private URL url;
	private String nomefile;
	public TaskDownloaderUrl(URL url,String nomefile){
		this.url=url;
		this.nomefile=nomefile;
	}
	public Integer call() throws Exception {
		    InputStream is = null;
		    FileOutputStream fos = null;

		    try {
		        URLConnection urlConn = this.url.openConnection();
		        urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

		        is = urlConn.getInputStream();               //get connection inputstream
		        fos = new FileOutputStream(this.nomefile);   //open outputstream to local file

		        byte[] buffer = new byte[4096];              //declare 4KB buffer
		        int len;

		        //while we have availble data, continue downloading and storing to local file
		        while ((len = is.read(buffer)) > 0) {  
		            fos.write(buffer, 0, len);
		        }
		    } finally {
		        try {
		            if (is != null) {
		                is.close();
		            }
		        } finally {
		            if (fos != null) {
		                fos.close();
		            }
		        }
		    }
		
		
		System.out.println("file finito :"+url.toString()+"  "+nomefile);
		
		return 0;
	}
	

}
