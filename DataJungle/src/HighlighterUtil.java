import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;

import org.apache.lucene.util.Version;

public class HighlighterUtil {
	private StandardAnalyzer analyzer;
	
	public HighlighterUtil(StandardAnalyzer analyzer){
		this.analyzer = analyzer;
		
	}
	
	public String getHighlightedString(String query, String s) throws IOException, InvalidTokenOffsetsException, ParseException
			   {
			     QueryParser q = new QueryParser(Version.LUCENE_47, "content", analyzer);
			     q.setDefaultOperator(QueryParser.Operator.AND);
			    Query qu = q.parse(query);

			     QueryScorer queryScorer = new QueryScorer(qu);
			     
			   Highlighter highlighter = new Highlighter(queryScorer);
			    highlighter.setTextFragmenter(new SimpleSpanFragmenter(queryScorer, 400));
			   highlighter.setMaxDocCharsToAnalyze(Integer.MAX_VALUE);

			    return highlighter.getBestFragment(analyzer, "content", s);
			  }
}
