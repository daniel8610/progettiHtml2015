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

public class ParserWarc {
	
	
	
public static List<Document>  parseWarc() throws IOException{
 File f=new File("/Users/daniel/Desktop/giw/fileWarc");
File fil=f.listFiles()[1];

InputStream l=new FileInputStream(fil);
WarcReader wr=WarcReaderFactory.getReader(l);

wr.iterator().next();
Iterator<WarcRecord> warcIterator = wr.iterator();
List<Document> listaWarc=new LinkedList<Document>();

while(warcIterator.hasNext()){
	WarcRecord record=warcIterator.next();
	BufferedReader br = new BufferedReader(new InputStreamReader(record.getPayloadContent()));
	//System.out.println(record.getHttpHeader().getHeader("Date").value);
	String line;
	String html="";
	while ((line = br.readLine()) != null) {
		html+=line;
	}
	Document d=new Document();
	if(ParserHtml.hasBody(html)){
	Field content=new TextField("content",ParserHtml.getBody(html),Field.Store.YES);
	d.add(content);
	}
	if(ParserHtml.hasTitle(html)){
		Field title=new TextField("title",ParserHtml.getTitle(html),Field.Store.YES);
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
