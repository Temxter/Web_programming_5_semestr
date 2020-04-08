package Parsers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Paragraph.
 */
public class Paragraph {
    private ArrayList<Sentence> sentences;
    private String sourceText;
    private String regexpSentence = "([^.!?]+[.!?])";

    /**
     * Instantiates a new Paragraph.
     *
     * @param sourceText the source text
     */
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

    /**
     * Gets sentences.
     *
     * @return the sentences
     */
    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    /**
     * Gets source text.
     *
     * @return the source text
     */
    public String getSourceText() {
        return sourceText;
    }
}
