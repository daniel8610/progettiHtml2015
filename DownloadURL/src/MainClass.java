import java.io.IOException;
import java.net.URL;


public class MainClass {
	public static void main(String args[]) throws IOException{
		ConcurrentDownloader cd=new ConcurrentDownloader(18137,"http://www.hltv.org/interfaces/download.php?demoid=18137");
		cd.multiDownload();
		System.out.println("fine main");
		/*in questo modo scarica pagine html
		DownloadUrl.downloadFromUrl(new URL("http://romanews.eu/2015/svelate-le-nuove-maglie-e-buona-la-prima-per-la-baby-roma-pomeriggio-pieno-di-incontri-per-sabatini/"), "filescaricati/prova");
	*/

		
	}
}
