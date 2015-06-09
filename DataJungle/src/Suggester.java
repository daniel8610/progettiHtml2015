

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.JaroWinklerDistance;
import org.apache.lucene.search.spell.LevensteinDistance;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.LuceneLevenshteinDistance;
import org.apache.lucene.search.spell.NGramDistance;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.search.spell.StringDistance;
import org.apache.lucene.search.spell.SuggestMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class  Suggester{
	private Directory directorySpell;
	public Suggester(Directory d){
		this.directorySpell=d;
	}
	
	public void createSpellChekerIndex() throws CorruptIndexException,
    IOException {
	Directory directoryIndex=FSDirectory.open(new File("/Users/daniel/Desktop/giw/index1"));
	IndexReader reader = DirectoryReader.open(directoryIndex);
    Dictionary dictionary = new LuceneDictionary(reader,"content");
    SpellChecker spellChecker = new SpellChecker(this.directorySpell);
    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
    IndexWriterConfig writerConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
    spellChecker.indexDictionary(dictionary, writerConfig, true);
    spellChecker.close();
}
	
	
	public String getSuggestions( String queryString, int numberOfSuggestions, final float accuracy) {
	         SpellChecker spellChecker;
			try {
				Directory directoryIndex=FSDirectory.open(new File("/Users/daniel/Desktop/giw/index"));
				IndexReader reader = DirectoryReader.open(directoryIndex);
				spellChecker = new SpellChecker(this.directorySpell);
				String[] similarWords = spellChecker.suggestSimilar(queryString, numberOfSuggestions,reader,"content",SuggestMode.SUGGEST_MORE_POPULAR, accuracy);	
				if (similarWords.length!=0){
					return similarWords[0];
				}
			       
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	public String getSuggestionsNormalDictonary(String queryString,int numberOfSuggestions) throws IOException{
		Directory directoryIndex=FSDirectory.open(new File("/Users/daniel/Desktop/giw/indexspell"));
		SpellChecker sp = new SpellChecker(directoryIndex);
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		sp.indexDictionary(new PlainTextDictionary(new File("/Users/daniel/Desktop/giw/fulldictionary.txt")), config, true);
		return sp.suggestSimilar(queryString, 1)[0];
	}
	
	
	
	/*  public static void main(String args[]){
		  Directory d;
		try {
			d = FSDirectory.open(new File("/Users/daniel/Desktop/giw/indexSpellchecker"));
			Suggester s=new Suggester(d);
			s.createSpellChekerIndex();
			//System.out.println(s.getSuggestions("bouse",10,0.2f));
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		  
		  
	}*/
	
}
