import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpellingDictionary {
    HashSet<String> dictionary;
    /**  */
    public SpellingDictionary(String filename) {
        // establish empty HashSet
        dictionary = new HashSet<String>();
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
            String word = file.nextLine().toLowerCase();
            dictionary.add(word);
        }
    }
    // containsWord
    // nearMisses
}
