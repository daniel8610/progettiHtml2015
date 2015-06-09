import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.postingshighlight.PostingsHighlighter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class SearchIndex {
private Directory directoryIndex;
private StandardAnalyzer analyzer;
private IndexSearcher searcher;
public SearchIndex(StandardAnalyzer analyzer){
	try {
		this.directoryIndex=FSDirectory.open(new File("/Users/daniel/Desktop/giw/index"));
		IndexReader reader = DirectoryReader.open(this.directoryIndex);
		this.searcher = new IndexSearcher(reader);
	} catch (IOException e) {
		e.printStackTrace();
	}
	this.analyzer=analyzer;
}
public List<Document> simpleQuery(String query){
	MultiFieldQueryParser qp  = new MultiFieldQueryParser(Version.LUCENE_47,new String[] {"content", "title"},analyzer);
	//per fare in and
	qp.setDefaultOperator(QueryParser.AND_OPERATOR);
	Query q;
	List<Document> results = new LinkedList<Document>();
	TopScoreDocCollector collector =TopScoreDocCollector.create(50, true);
	try {
		q = qp.parse(query);
		searcher.search(q, collector);
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	ScoreDoc[] sq=collector.topDocs().scoreDocs;
	for(int i=0;i<sq.length;++i) {
		int docId = sq[i].doc;
		Document d;
		try {
			d = searcher.doc(docId);
			results.add(d);
			//System.out.println("url: " + d.get("url") + " Body: " + d.get("content"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		}	 
	if(results.size()==0)
	   results.addAll(this.orQuery(query));
	return results;
}
public List<Document> orQuery(String query){
	MultiFieldQueryParser qp  = new MultiFieldQueryParser(Version.LUCENE_47,new String[] {"content", "title"},analyzer);
	Query q;
	List<Document> results = new LinkedList<Document>();
	TopScoreDocCollector collector =TopScoreDocCollector.create(50, true);
	try {
		q = qp.parse(query);
		searcher.search(q, collector);
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	ScoreDoc[] sq=collector.topDocs().scoreDocs;
	for(int i=0;i<sq.length;++i) {
		int docId = sq[i].doc;
		Document d;
		try {
			d = searcher.doc(docId);
			results.add(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}	 
	
	
	
	
	return results;
	
	
	
}
public List<Document> advancedQuery(String and,String not){
	MultiFieldQueryParser qp  = new MultiFieldQueryParser(Version.LUCENE_47,new String[] {"content", "title"},analyzer);
	Query andQuery,notQuery;
	List<Document> results = new LinkedList<Document>();;
	try {
		TopScoreDocCollector collector =TopScoreDocCollector.create(50, true);
		BooleanQuery in = new BooleanQuery();
		if(and!=""){
			andQuery=qp.parse(and);
			in.add(andQuery, Occur.MUST);
		}
		if(not!=""){
		notQuery=qp.parse(not);
		in.add(notQuery, Occur.MUST_NOT);
		}
		searcher.search(in, collector);
		ScoreDoc[] sq=collector.topDocs().scoreDocs;
		for(int i=0;i<sq.length;++i) {
			int docId = sq[i].doc;
			Document d;
			try {
				d = searcher.doc(docId);
				results.add(d);
				//System.out.println("url: " + d.get("url") + " Body: " + d.get("content"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			}	 
		
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	return results;
	
}




}
