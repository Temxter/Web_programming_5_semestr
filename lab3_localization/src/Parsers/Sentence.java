package Parsers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Sentence.
 */
public class Sentence {
    private ArrayList<String> words;
    private ArrayList<String> punctuationPoints;
    private String sourceText;
    private String regexpWords = "(\\w+)";
    private String regexpPunctuation = "([\\.\\-!?;:'\"])";

    /**
     * Instantiates a new Sentence.
     *
     * @param sourceText the source text
     */
    public Sentence(String sourceText) {
        this.sourceText = sourceText;
        parse();
    }

    private void parse(){
        words = new ArrayList<>();
        Matcher wordsParsed = Pattern.compile(regexpWords).matcher(sourceText);
        while (wordsParsed.find()) {
            String word = wordsParsed.group();
            words.add(word);
        }
        punctuationPoints = new ArrayList<>();
        Matcher punctuationParsed = Pattern.compile(regexpPunctuation).matcher(sourceText);
        while (punctuationParsed.find()) {
            String punctuationPoint = punctuationParsed.group();
            punctuationPoints.add(punctuationPoint);
        }
    }

    /**
     * Gets words.
     *
     * @return the words
     */
    public ArrayList<String> getWords() {
        return words;
    }

    /**
     * Gets source text.
     *
     * @return the source text
     */
    public String getSourceText() {
        return sourceText;
    }

    /**
     * Gets punctuation points.
     *
     * @return the punctuation points
     */
    public ArrayList<String> getPunctuationPoints() {
        return punctuationPoints;
    }
}
