

import Handler.HandlerText;
import Localization.Localization;
import Parsers.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String string = "This is program with code. The second sentence. " +
                "{a { print (\"hello!\")} } The end of code and start new sentece." +
                "{ if this is the code then go to parse code}" +
                "The last sentence!";

        Localization localization = null;
        System.out.println("Choose language:\n1.English\n2.Russian");
        Scanner scanner = new Scanner(System.in);
        int chooseLang = scanner.nextInt();
        switch (chooseLang){
            case 1: localization = new Localization("en");
            case 2: localization = new Localization("ru");
            default: localization = new Localization("en");
        }

        String textCode = localization.getString("code");
        String textParagraph = localization.getString("paragraph");
        String textsentence = localization.getString("sentence");
        String textEmpty_unique_word = localization.getString("empty_unique_word");
        String textUnique_word = localization.getString("unique_word");
        String textSorted_by_words = localization.getString("sorted_by_words");

        Text text = new Text(string);
        System.out.println(textCode + ":\n" + text.getCodes());

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
            System.out.println("\n" + textParagraph + " " + iPar+1 + ":\n"+ paragraphs.get(iPar).getSourceText());
            for (int iSent = 0; iSent < sentences.size(); iSent++){
                ArrayList<String> words = sentences.get(iSent).getWords();
                System.out.println(textsentence + " " + iSent+1 + ":\n"+ sentences.get(iSent).getSourceText());
                for (int iWord = 0; iWord < words.size(); iWord++){
                    System.out.println(iWord+1 + ") " + words.get(iWord) + ";");
                }
            }
        }

        HandlerText hText = new HandlerText(text);
        String uniqueWord = hText.getUniqueWordFromFirstSentence();
        if (uniqueWord == null)
            System.out.println(textEmpty_unique_word);
        else
            System.out.println(textUnique_word + ": " + uniqueWord);

        System.out.println("\n " + textSorted_by_words + ": ");
        hText.printSortSentencesByWords();
    }
}
