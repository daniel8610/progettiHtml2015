import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.jsoup.parser.Parser;
import org.jwat.warc.WarcDateParser;
import org.jwat.warc.WarcReader;
import org.jwat.warc.WarcReaderFactory;
import org.jwat.warc.WarcRecord;

public class ParserWarcConcorrente {
	
	private final static int NCPU =Runtime.getRuntime().availableProcessors();
	
	
public static List<Document>  parseWarc() throws IOException{
	ExecutorService pool=Executors.newFixedThreadPool(NCPU);
	BlockingDeque<Future<List<Document>>> risultati=new LinkedBlockingDeque<Future<List<Document>>>();
	List<Document> lista=new LinkedList<Document>();
 File f=new File("/Users/daniel/Desktop/giw/fileWarc1");
 File files[]=f.listFiles();
 for(int i=1;i<files.length;i++){
	 risultati.add(pool.submit(new TaskParserWarc(files[i])));
 }
 for(Future<List<Document>> l:risultati){
		try {
			lista.addAll(l.get());
		} catch (InterruptedException |ExecutionException e) {
			e.printStackTrace();
		}} 
	pool.shutdown();
return lista;
}      
}