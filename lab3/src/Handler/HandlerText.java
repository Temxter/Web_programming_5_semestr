package Handler;

import Parsers.Paragraph;
import Parsers.Sentence;
import Parsers.Text;

import java.util.ArrayList;
import java.util.Comparator;

public class HandlerText {

    Text text;
    ArrayList<Sentence> sortedSentences;

    public HandlerText(Text text) {
        this.text = text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getUniqueWordFromFirstSentence(){
        String uniqueWord = null;
        ArrayList<Paragraph> paragraphs = text.getParagraphs();
        ArrayList<Sentence> sentences = paragraphs.get(0).getSentences();
        ArrayList<String> words = sentences.get(0).getWords();
        for (String word: words){
            System.out.println("Searching: " + word);
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

    public void printSortSentencesByWords(){
        if (sortedSentences == null)
            sortSentencesByWords();
        for (Sentence sentence : sortedSentences){
            System.out.println(sentence.getWords().size() + ": " + sentence.getSourceText());
        }
    }
}
