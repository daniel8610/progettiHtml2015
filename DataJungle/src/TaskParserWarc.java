import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.jwat.warc.WarcReader;
import org.jwat.warc.WarcReaderFactory;
import org.jwat.warc.WarcRecord;


public class TaskParserWarc implements Callable<List<Document>>{
private File file;
	public TaskParserWarc(File f){
		this.file=f;
	}
	@Override
	public List<Document> call() throws Exception {
		InputStream l=new FileInputStream(this.file);
		WarcReader wr=WarcReaderFactory.getReader(l);

		wr.iterator().next();
		Iterator<WarcRecord> warcIterator = wr.iterator();
		List<Document> listaWarc=new LinkedList<Document>();

		while(warcIterator.hasNext()){
			WarcRecord record=warcIterator.next();
			BufferedReader br = new BufferedReader(new InputStreamReader(record.getPayloadContent() ));
			//System.out.println(record.getHttpHeader().getHeader("Date").value);
			String line ;
			String html="";
			while ((line = br.readLine()) != null) {
				html+=line;
				
			}
			Document d=new Document();
		
			if(ParserHtml.hasBody(html)){
			//Field content=new TextField("content",ParserHtml.getBody(html),Field.Store.YES);
			/////codice per term vector su body
			final FieldType BodyOptions = new FieldType(); 
			BodyOptions.setIndexed(true); 
			BodyOptions.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS); 
			BodyOptions.setStored(true); 
			BodyOptions.setStoreTermVectors(true); 
			BodyOptions.setTokenized(true);
			Field content=new Field("content", ParserHtml.getBody(html), BodyOptions);
			
			
			
			
			
			/////
			
			
			
			
			d.add(content);
			}
			if(ParserHtml.hasTitle(html)){
				Field title=new TextField("title",ParserHtml.getTitle(html),Field.Store.YES);
				title.setBoost(2L);
				d.add(title);
			}
			//StoredField per non indicizzare//
			StoredField url = new StoredField ("url",record.getHeader("WARC-Target-URI").value);
			if(record.getHttpHeader().getHeader("Date")!=null){
			StoredField date=new StoredField ("data",record.getHttpHeader().getHeader("Date").value);
			d.add(date);
			}
			d.add(url);
			listaWarc.add(d);
		}
		return listaWarc;
	}

}
