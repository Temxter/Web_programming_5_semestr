package Parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    static final Logger loggerParser = LogManager.getLogger(Paragraph.class);
    /**
     * Instantiates a new Paragraph.
     *
     * @param sourceText the source text
     */
    public Paragraph(String sourceText) {
        loggerParser.debug("Create object Paragaph");
        if (sourceText == null || sourceText.isEmpty()) {
            String exMessage = "String is null or empty.";
            loggerParser.error(exMessage);
            throw new IllegalArgumentException(exMessage);
        }
        this.sourceText = sourceText;
        parse();
    }

    private void parse(){
        loggerParser.debug("Parse text: " + sourceText.substring(0, 10) + "...");
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
