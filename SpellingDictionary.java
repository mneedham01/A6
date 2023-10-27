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
        return dictionary.contains(word.toLowerCase());
    }

    public ArrayList<String> generateDeletions(String word){
        ArrayList<String> deletions = new ArrayList<String>();
        // loop through word
        //
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

    public static void main(String[] args) {
        SpellingDictionary test = new SpellingDictionary("words.txt");
    }
}
