

import Handler.HandlerText;
import Parsers.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String string = "This is program with code. The second sentence. " +
                "{a { print (\"hello!\")} } The end of code and start new sentece." +
                "{ if this is the code then go to parse code}" +
                "The last sentence!";
        Text text = new Text(string);
        System.out.println("Code:\n" + text.getCodes());

        //Written with foreach loops
//        for (Paragraph paragraph : text.getParagraphs()){
//            System.out.println("Paragraph:\n" + paragraph.getSourceText());
//            for (Sentence sentence : paragraph.getSentences()){
//                System.out.println("Sentence:\n" + sentence.getSourceText());
//                for (String words : sentence.getWords()){
//                    System.out.println("Word: " + words);
//                }
//            }
//        }


        ArrayList<Paragraph> paragraphs = text.getParagraphs();
        for (int iPar = 0; iPar < paragraphs.size(); iPar++){
            ArrayList<Sentence> sentences = paragraphs.get(iPar).getSentences();
            System.out.println("\nParagraph " + iPar+1 + ":\n"+ paragraphs.get(iPar).getSourceText());
            for (int iSent = 0; iSent < sentences.size(); iSent++){
                ArrayList<String> words = sentences.get(iSent).getWords();
                System.out.println("Sentence " + iSent+1 + ":\n"+ sentences.get(iSent).getSourceText());
                for (int iWord = 0; iWord < words.size(); iWord++){
                    System.out.println(iWord+1 + ") " + words.get(iWord) + ";");
                }
            }
        }

        HandlerText hText = new HandlerText(text);
        String uniqueWord = hText.getUniqueWordFromFirstSentence();
        if (uniqueWord == null)
            System.out.println("Doesn't exist unique word in first sentence.");
        else
            System.out.println("Unique word in first sentence: " + uniqueWord);

        System.out.println("\nSorted by words: ");
        hText.printSortSentencesByWords();
    }
}
