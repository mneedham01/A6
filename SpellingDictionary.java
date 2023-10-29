import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellingDictionary implements SpellingOperations {
    HashSet<StringBuilder> dictionary;
    /**  */
    public SpellingDictionary(String filename) {
        // establish empty HashSet
        dictionary = new HashSet<StringBuilder>();
        // establish Scanner
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        } catch (Exception e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
        // read through file and add to hashSet
        while (file.hasNextLine()) {
            // convert to lowerCase
            StringBuilder word = new StringBuilder(file.nextLine().toLowerCase());
            dictionary.add(word);
        }
    }
     /**
      *  @param word the word to check
      *  @return true if the query word is in the dictionary.
    */
    public boolean isListed(String word) {
        StringBuilder sb = new StringBuilder(word.toLowerCase());
        return dictionary.contains(sb);
    }

    public ArrayList<StringBuilder> generateDeletions(StringBuilder word){
        // establish ArrayList
        ArrayList<StringBuilder> deletions = new ArrayList<StringBuilder>();
        // loop through word
        for (int i = 0; i < word.length(); i++) {
            StringBuilder deletion = new StringBuilder(word.substring(0,i) + word.substring(i+1));
            deletions.add(deletion);
        }
        return deletions;
    }

    /**
     *  @param word the word to check
     *  @return a list of all valid words that are one edit away from the query
    */
    public ArrayList<String> nearMisses(String word) {
        ArrayList<String> nearMisses = new ArrayList<String>();

        return nearMisses;
    }

    // TESTS
    public static void main(String[] args) {
        SpellingDictionary test = new SpellingDictionary("words.txt");

        String[] testWords = {"abcdefg","a","ab","abc",""};

        // Test for Deletions
        for (int i = 0; i < testWords.length; i++) {
            // turn into stringBuilder (usually done within nearMisses)
            StringBuilder testWord = new StringBuilder(testWords[i]);
            System.out.println("Original word: '" + testWords[i]+ "' \nDeletions: " + test.generateDeletions(testWord)+"\n");
        }
    }
}
