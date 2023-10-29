public class Test {

    public SpellingDictionary test;
    public String[] testWords = {"abcdefg","a","ab","abc",""};

    public Test () {
        test = new SpellingDictionary("words.txt");
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
            if (wordLen != 0 && wordLen != 1) {
                numChoices = wordLen - 1;
            }
        }
        return numChoices;
    }

    /** prints tests of functions */
    public void testFunctions() {
        // Tests
        for (int i = 0; i < testWords.length; i++) {
            // turn into stringBuilder (usually done within nearMisses)
            StringBuilder testWord = new StringBuilder(testWords[i]);
            System.out.println("Original word: '" + testWords[i]+"'\n");

            // deletions
            System.out.println("Deletions:");
            // for first test, print out all options
            // System.out.println("Deletions: " + test.generateDeletions(testWord));
            System.out.println("Expected number of choices: " + numChoices("deletion",testWords[i]));
            System.out.println("Actual number of choices: " + test.generateDeletions(testWord).size()+"\n");

            // insertions
            System.out.println("Insertions:");
            // System.out.println("Insertions: " + test.generateInsertions(testWord));
            System.out.println("Expected number of choices: " + numChoices("insertion",testWords[i]));
            System.out.println("Actual number of choices: " + test.generateInsertions(testWord).size()+"\n");

            // substitutions
            System.out.println("Substitutions:");
            // System.out.println("Substitutions: " + test.generateSubstitutions(testWord));
            System.out.println("Expected number of choices: " + numChoices("substitution",testWords[i]));
            System.out.println("Actual number of choices: " + test.generateSubstitutions(testWord).size()+"\n");

            // transpositions
            System.out.println("Transpositions:");
            // System.out.println("Transpositions: " + test.generateTranspositions(testWord));
            System.out.println("Expected number of choices: " + numChoices("transposition",testWords[i]));
            System.out.println("Actual number of choices: " + test.generateTranspositions(testWord).size()+"\n");

            // splits
            System.out.println("Splits");
            // System.out.println("Splits: " + test.generateSplits(testWord));
            System.out.println("Expected number of choices: " + numChoices("split",testWords[i]));
            System.out.println("Actual number of choices: " + test.generateSplits(testWord).size()+"\n");
        }
    }
}
