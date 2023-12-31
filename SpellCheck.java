import java.util.HashSet;
import java.util.Scanner;
/** reads in words and checks with SpellingDictionary  */
public class SpellCheck {

    /** Checks words given manually into command line */
    public static void checkFirst(String[] args, SpellingDictionary dict) {
        // loop through arguments
        for (String word : args) {
            // if correctly spelled
            if (dict.isListed(word)) {
                System.out.println("'" + word + "' is spelled correctly.");
            } else {
                System.out.println("Not found: "+ word);
                System.out.println("    Suggestions: " + dict.nearMisses(word));
            }
        }
    }

    /** formats the print statement for an incorrect word  */
    public static void printIncorrect(HashSet<String> incorrect, SpellingDictionary dict) {
        System.out.println("Total mispelled words: " + incorrect.size());
        for (String word: incorrect) {
            System.out.println("Not found: " + word);
            System.out.println("    Suggestions: " + dict.nearMisses(word));
        }
    }

    /** checks a file given to the main method */
    public static void checkSecond(SpellingDictionary dict) {
        HashSet<String> incorrect =  new HashSet<String>();

        // create a Scanner with input from System.in
        Scanner scanner = new Scanner(System.in);
        // read through words
        while (scanner.hasNext()) {
            String baseWord = scanner.next();
            Character lastChar = baseWord.charAt(baseWord.length() - 1);
            String word;
            // if no punctuation
            if (lastChar != ',' && lastChar != ':' && lastChar != '!' && lastChar != '.' && lastChar != '?') {
               word = String.valueOf(baseWord);
            } else {
                // has punctuation
                StringBuilder withComma = new StringBuilder(baseWord);
                word = withComma.substring(0, withComma.length() - 1);
            }
            // if has an apostrophe
            if (word.contains("'")) {
                StringBuilder sb = new StringBuilder(word);
                int apos = sb.indexOf("'");
                sb.deleteCharAt(apos);
                word = sb.toString();
            }
            // if incorrect, add to hashSet
            if (! dict.isListed(word) && ! incorrect.contains(word)) {
                // add to hashSet
                incorrect.add(word);
            }
        }
        // print hashSet
        printIncorrect(incorrect, dict);
    }

    /** main method evaluates which style of invocation and calls appropriate method */
    public static void main(String[] args) {
        SpellingDictionary dict = new SpellingDictionary("words.txt");
        // if len args > 0 : first style of invocation
        if (args.length > 0) {
            System.out.println("here");
            checkFirst(args, dict);
        }

        // if len args == 0 : second style of invocation
        if (args.length == 0) {
            checkSecond(dict);
        }
    }
}
