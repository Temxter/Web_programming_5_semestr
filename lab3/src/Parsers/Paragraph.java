package Parsers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paragraph {
    private ArrayList<Sentence> sentences;
    private String sourceText;
    private String regexpSentence = "([^.!?]+[.!?])";

    public Paragraph(String sourceText) {
        this.sourceText = sourceText;
        parse();
    }

    private void parse(){
        sentences = new ArrayList<>();
        Matcher sentencesParsed = Pattern.compile(regexpSentence).matcher(sourceText);
        while (sentencesParsed.find()) {
            Sentence sentence = new Sentence(sentencesParsed.group(0));
            sentences.add(sentence);
        }
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    public String getSourceText() {
        return sourceText;
    }
}
