import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.spell.DirectSpellChecker;
import org.apache.lucene.search.spell.SuggestMode;
import org.apache.lucene.search.spell.SuggestWord;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class SuggesterV2 {
public static void main(String args[]){
	DirectSpellChecker dsc=new DirectSpellChecker();
	Directory directoryIndex;
	try {
		directoryIndex = FSDirectory.open(new File("/Users/daniel/Desktop/giw/index"));
		Term t=new Term("arphone");
		IndexReader reader = DirectoryReader.open(directoryIndex);
		SuggestWord [] s=dsc.suggestSimilar(t, 2, reader, SuggestMode.SUGGEST_MORE_POPULAR);
		System.out.println(s.length);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	
}
}
