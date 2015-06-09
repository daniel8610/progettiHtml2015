import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Indexer {
public static void main1(String args[]) {
	try {
		//double tstart=System.currentTimeMillis();
		List<Document> listaDoc=ParserWarcConcorrente.parseWarc();
		//double tend=System.currentTimeMillis();
		//double res1=tend-tstart;
		//System.out.println(res1);
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		Directory index = FSDirectory.open(new File("/Users/daniel/Desktop/giw/index1"));
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		config.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(index, config);
		for(Document d : listaDoc){
		 writer.addDocument(d);
		 
		}
		writer.close();	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}
}
