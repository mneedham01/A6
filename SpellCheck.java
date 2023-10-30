import java.util.Scanner;
public class SpellCheck {

    /**  */
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

    /**  */
    public static void checkSecond(SpellingDictionary dict) {
        // create a Scanner with input from System.in
        Scanner scanner = new Scanner(System.in);
        // read through words
        scanner.useDelimiter(" ");
        while (scanner.hasNext()) {
            String baseWord = scanner.next();
            Character lastChar = baseWord.charAt(baseWord.length() - 1);
            System.out.println("word = "+ baseWord);
            System.out.println("|||| lastChar = "+ lastChar);
            String word;
            // if no punctuation
            if (lastChar != ',' && lastChar != ':' && lastChar != '!' && lastChar != '.' && lastChar != '?') {
               word = String.valueOf(baseWord);
            } else {
                // has punctuation
                System.out.println("***IN HERE**");
                StringBuilder withComma = new StringBuilder(baseWord);
                word = withComma.substring(0, withComma.length() - 1);
            }
            System.out.println("||| new word= " + word);
        }
        // if correct - move on silently
        // if incorrect - print message and offer alternatives (only for first time)
    }

    /** */
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
