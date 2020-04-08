package Parsers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    private ArrayList<String> words;
    private ArrayList<String> punctuationPoints;
    private String sourceText;
    private String regexpWords = "(\\w+)";
    private String regexpPunctuation = "([\\.\\-!?;:'\"])";

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

    public ArrayList<String> getWords() {
        return words;
    }

    public String getSourceText() {
        return sourceText;
    }

    public ArrayList<String> getPunctuationPoints() {
        return punctuationPoints;
    }
}
