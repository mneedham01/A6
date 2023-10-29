import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellingDictionary implements SpellingOperations {
    HashSet<StringBuilder> dictionary;
    // establish alphabet
    Character[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    /**
     * Constructor : creates a dictionary based on filename
     * @param filename
     */
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

    /** @returns ArrayList of Strings with possible 1-character deletions */
    public ArrayList<String> generateDeletions(StringBuilder word){
        // establish ArrayList
        ArrayList<String> deletions = new ArrayList<String>();
        // loop through word
        for (int i = 0; i < word.length(); i++) {
            String deletion = word.substring(0,i) + word.substring(i+1);
            deletions.add(deletion);
        }
        return deletions;
    }

    /** @returns ArrayList of Strings with possible 1-character insertions */
    public ArrayList<String> generateInsertions(StringBuilder word) {
        // establish ArrayList
        ArrayList<String> insertions = new ArrayList<String>();
        // loops through alphabet
        for (int i = 0; i < alphabet.length; i++) {
            // loop through word, insert letter to possible spots
            for (int j = 0; j < word.length() + 1; j++) {
                String insertion = word.substring(0, j) + alphabet[i] + word.substring(j);
                insertions.add(insertion);
            }
        }
        return insertions;
    }

    /** @return ArrayList of Strings with possible 1- char subsitutions */
    public ArrayList<String> generateSubstitutions(StringBuilder word) {
        // establish ArrayList
        ArrayList<String> substitutions = new ArrayList<String>();
        // loop through alphabet
        for (int i = 0; i < alphabet.length; i++) {
            Character letter = alphabet[i];
            // loop through word, substitute in possible spots
            for (int j = 0; j < word.length(); j++) {
                if (! letter.equals(word.charAt(j))) {
                    String substitution = word.substring(0, j) + letter + word.substring(j + 1);
                    substitutions.add(substitution);
                }
            }
        }
        return substitutions;
    }

    /**
     *  @param word the word to check
     *  @return a list of all valid words that are one edit away from the query
    */
    public ArrayList<String> nearMisses(String word) {
        ArrayList<String> nearMisses = new ArrayList<String>();

        // turn into StringBuilder
        StringBuilder wordSB = new StringBuilder(word);

        nearMisses.addAll(generateDeletions(wordSB));
        //nearMisses.addAll();

        return nearMisses;
    }

    // TEST
    public static void main(String[] args) {
        Test test = new Test();
        test.testFunctions();
    }
}
