import java.io.IOException;
import java.net.URL;


public class MainClass {
	public static void main(String args[]) throws IOException{
		URL url=new URL("http://www.hltv.org/interfaces/download.php?demoid=18113");
		ConcurrentDownloader cd=new ConcurrentDownloader(18137,"http://www.hltv.org/interfaces/download.php?demoid=18137");
		cd.multiDownload();
		System.out.println("fine main");
	

		
	}
}