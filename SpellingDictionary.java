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
        // establish alphabet
        Character[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        // loops through alphabet
        for (int i = 0; i < alphabet.length; i++) {
            Character letter = alphabet[i];
            // loop through word
            for (int j = 0; j < word.length() + 1; j++) {
                String insertion = word.substring(0, j) + letter + word.substring(j);
                insertions.add(insertion);
            }
        }
        return insertions;
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

    /** @returns the expected number of choices for a given function and word */
    public int numChoices (String function, String word) {
        int wordLen = word.length();
        int numChoices = 0;
        if (function == "deletion") {
            numChoices = wordLen;
        }
        if (function == "insertion") {
            numChoices = 26 * (wordLen + 1);
        }
        if (function == "substitution") {
            numChoices = 25 * wordLen;
        }
        if (function == "transposition" || function == "split") {
            numChoices = wordLen - 1;
        }
        return numChoices;
    }

    // TESTS
    public static void main(String[] args) {
        SpellingDictionary test = new SpellingDictionary("words.txt");

        String[] testWords = {"abcdefg","a","ab","abc",""};

        // Tests
        for (int i = 0; i < testWords.length; i++) {
            // turn into stringBuilder (usually done within nearMisses)
            StringBuilder testWord = new StringBuilder(testWords[i]);
            System.out.println("Original word: '" + testWords[i]+"'\n");

            // deletions
            System.out.println("Deletions: " + test.generateDeletions(testWord));
            System.out.println("Expected number of choices: " + test.numChoices("deletion",testWords[i]));
            System.out.println("Actual number of choices: " + test.generateDeletions(testWord).size()+"\n");

            // insertions
            System.out.println("Insertions: ");
            // System.out.println("Insertions: " + test.generateInsertions(testWord));
            System.out.println("Expected number of choices: " + test.numChoices("insertion",testWords[i]));
            System.out.println("Actual number of choices: " + test.generateInsertions(testWord).size()+"\n");
        }
    }
}
