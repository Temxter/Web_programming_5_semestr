package Parsers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
    ArrayList<Paragraph> paragraphs;
    ArrayList<String> codes;
    String sourceText;
    String regexp = "\\{.*\\}";

    public Text(String sourceText) {
        this.sourceText = sourceText;
        parse();
    }

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

    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public ArrayList<String> getCodes() {
        return codes;
    }

    public String getSourceText() {
        return sourceText;
    }
}
