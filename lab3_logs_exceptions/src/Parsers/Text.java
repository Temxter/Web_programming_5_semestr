package Parsers;

import Exceptions.TextException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Main class that parse text with code on chunks of text without code and chunks of text with code.
 * @author Andrew
 * @version 1.0
 */
public class Text {
    /**
    ArrayList for text without code.
     */
    private ArrayList<Paragraph> paragraphs;
    /**
    ArrayList for text with code.
     */
    private ArrayList<String> codes;
    /**
    String for save source text.
     */
    private String sourceText;
    /**
    String with regular expression for parse on text with code and without code.
     */
    private String regexp = "\\{.*\\}";

    /**
     * Constructor with auto parse after initialization class.
    @param sourceText is source text to parse
     */
    public Text(String sourceText) throws TextException {
        if (sourceText == null || sourceText.isEmpty())
            throw new TextException("Source text is null or empty.");
        this.sourceText = sourceText;
        parse();
    }

    /**
     * Parse text to code chunks and chunks without code.
     * Init {@link #paragraphs} and {@link #codes}
     */
    private void parse(){
        paragraphs = new ArrayList<>();
        String [] paragraphsArray = sourceText.split(regexp);
        for (String paragraphString: paragraphsArray)
        {
            Paragraph paragraph = new Paragraph(paragraphString);
            paragraphs.add(paragraph);
        }
        codes = new ArrayList<>();
        Matcher code = Pattern.compile(regexp, Pattern.DOTALL).matcher(sourceText);
        while (code.find())
            codes.add(code.group(0));
    }

    /**
     * @return {@link #paragraphs}
     */
    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    /**
     * @return {@link #codes}
     */
    public ArrayList<String> getCodes() {
        return codes;
    }

    /**
     *
     * @return {@link #sourceText}
     */
    public String getSourceText() {
        return sourceText;
    }
}
