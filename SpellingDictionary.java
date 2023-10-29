import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellingDictionary implements SpellingOperations {
    HashSet<String> dictionary;
    // establish alphabet
    Character[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    /**
     * Constructor : creates a dictionary based on filename
     * @param filename
     */
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

    /** @return ArrayList of Strings with all possible 1- char subsitutions */
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

    /** @return ArrayList of Strings with all possible 1- char transpositions */
    public ArrayList<String> generateTranspositions(StringBuilder word) {
        // establish ArrayList
        ArrayList<String> transpositions = new ArrayList<String>();
        // loop through word
        for (int i = 0; i < word.length() - 1; i++) {
            String transpo = word.substring(0, i)+ word.substring(i + 1, i + 2) + word.substring(i, i + 1) + word.substring(i + 2);
            transpositions.add(transpo);
        }
        return transpositions;
    }

    /** @return ArrayList of Strings with all possible splits */
    public ArrayList<String> generateSplits (StringBuilder word) {
        // establish ArrayList
        ArrayList<String> splits = new ArrayList<String>();
        // loop through word
        for (int i = 0; i < word.length() - 1; i++) {
            String split = word.substring(0, i + 1) + " " + word.substring(i + 1);
            splits.add(split);
        }
        return splits;

    }

    /**
     *  @param word the word to check
     *  @return a list of all valid words that are one edit away from the query
    */
    public ArrayList<String> nearMisses(String word) {
        ArrayList<String> allNearMisses = new ArrayList<String>();
        ArrayList<String> validNearMisses = new ArrayList<String>();

        // turn into StringBuilder
        StringBuilder wordSB = new StringBuilder(word);

        allNearMisses.addAll(generateDeletions(wordSB));
        allNearMisses.addAll(generateInsertions(wordSB));
        allNearMisses.addAll(generateSubstitutions(wordSB));
        allNearMisses.addAll(generateTranspositions(wordSB));
        allNearMisses.addAll(generateSplits(wordSB));

        // checks them against the dictionary and deletes any repetitions
        for (String nearMiss : allNearMisses) {
            // if in dictionary and not a repeat
            if (dictionary.contains(nearMiss)) {
                if (! validNearMisses.contains(nearMiss)) {
                    validNearMisses.add(nearMiss);
                }
            }
        }

        return validNearMisses;
    }

    // TEST
    public static void main(String[] args) {
        Test test = new Test();
        test.testFunctions();
    }
}
