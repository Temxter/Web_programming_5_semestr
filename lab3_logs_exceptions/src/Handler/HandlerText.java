package Handler;

import Exceptions.HandlerFindException;
import Parsers.Paragraph;
import Parsers.Sentence;
import Parsers.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The type Handler text.
 */
public class HandlerText {


    static final Logger loggerHandler = LogManager.getLogger(HandlerText.class);
    /**
     * The Text.
     */
    Text text;
    /**
     * The Sorted sentences.
     */
    ArrayList<Sentence> sortedSentences;

    /**
     * Instantiates a new Handler text.
     *
     * @param text the text
     */
    public HandlerText(Text text) {
        this.text = text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(Text text) {
        this.text = text;
    }

    /**
     * Get unique word from first sentence string.
     *
     * @return the string
     */
    public String getUniqueWordFromFirstSentence() throws HandlerFindException {
        loggerHandler.debug("getUniqueWordFromFirstSentence called");
        String uniqueWord = null;
        ArrayList<Paragraph> paragraphs = null;
        ArrayList<Sentence> sentences = null;
        ArrayList<String> words = null;
        try {
            paragraphs = text.getParagraphs();
            sentences = paragraphs.get(0).getSentences();
            words = sentences.get(0).getWords();
        }
        catch (Exception ex)
        {
            throw new HandlerFindException();
        }
        for (String word: words){
            //System.out.println("Searching: " + word);
            uniqueWord = searchUniqueInOtherSentences(word);
            if (uniqueWord != null)
                break;
        }
        return uniqueWord;
    }

    private String searchUniqueInOtherSentences(String wordToFind){
        ArrayList<Paragraph> paragraphs = text.getParagraphs();
        for (int iPar = 1; iPar < paragraphs.size(); iPar++){
            ArrayList<Sentence> sentences = paragraphs.get(iPar).getSentences();
            for (Sentence sentence : sentences){
                for (String word: sentence.getWords()){
                    if (wordToFind.compareTo(word) == 0)
                        return null;
                }
            }
        }
        return wordToFind;
    }

    private void sortSentencesByWords(){
        sortedSentences = new ArrayList<>();
        for (Paragraph paragraph : text.getParagraphs()){
            sortedSentences.addAll(paragraph.getSentences());
        }
        sortedSentences.sort(
                Comparator.comparingInt((Sentence a) -> -a.getWords().size())
        );
    }

    /**
     * Print sort sentences by words.
     */
    public void printSortSentencesByWords(){
        loggerHandler.debug("printSortSentencesByWords called");
        if (sortedSentences == null)
            sortSentencesByWords();
        for (Sentence sentence : sortedSentences){
            System.out.println(sentence.getWords().size() + ": " + sentence.getSourceText());
        }
    }
}
