public class Test {

    public SpellingDictionary test;
    public String[] testWords = {"dag","a","catle","kinfdom",""};

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

    /** Prints whether the expected & actual number of choices match */
    public void printPF(int expected, int actual) {
        String toPrint = expected == actual ? "TEST PASSED\n" : "TEST FAILED\n";
        System.out.println(toPrint);
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
            int expected = numChoices("deletion",testWords[i]);
            int actual = test.generateDeletions(testWord).size();
            System.out.println("Expected number of choices: " + expected);
            System.out.println("Actual number of choices: " + actual);
            printPF(expected, actual);

            // insertions
            System.out.println("Insertions:");
            expected = numChoices("insertion",testWords[i]);
            actual = test.generateInsertions(testWord).size();
            // System.out.println("Insertions: " + test.generateInsertions(testWord));
            System.out.println("Expected number of choices: " + expected);
            System.out.println("Actual number of choices: " + actual);
            printPF(expected, actual);

            // substitutions
            System.out.println("Substitutions:");
            // System.out.println("Substitutions: " + test.generateSubstitutions(testWord));
            expected = numChoices("substitution",testWords[i]);
            actual = test.generateSubstitutions(testWord).size();
            System.out.println("Expected number of choices: " + expected);
            System.out.println("Actual number of choices: " + actual);
            printPF(expected, actual);

            // transpositions
            System.out.println("Transpositions:");
            // System.out.println("Transpositions: " + test.generateTranspositions(testWord));
            expected = test.generateTranspositions(testWord).size();
            actual = test.generateTranspositions(testWord).size();
            System.out.println("Expected number of choices: " + expected);
            System.out.println("Actual number of choices: " + actual);
            printPF(expected, actual);

            // splits
            System.out.println("Splits");
            // System.out.println("Splits: " + test.generateSplits(testWord));
            expected = numChoices("split",testWords[i]);
            actual = test.generateSplits(testWord).size();
            System.out.println("Expected number of choices: " + expected);
            System.out.println("Actual number of choices: " + actual);
            printPF(expected, actual);

            // test nearMisses
            System.out.println("All valid nearMisses = " + test.nearMisses(testWords[i]));
        }
    }

    /** Runs test */
    public static void main(String[] args) {
        Test test = new Test();
        test.testFunctions();
    }
}
