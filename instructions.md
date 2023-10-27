# A3: Spell Checking

The purpose of this assignment is to give you some experience in using hash tables. For this project, you will implement a spell-checking program that stores its dictionary in a hash table (implemented in the Java code by class [`HashSet`](https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html)).  <!--If you complete the assignment with time to spare, you can compare the performance of your program when an ordinary ``ArrayList`` is substituted for the ``HashSet``.-->

## Overview

The heart of this assignment is the `SpellDictionary` class.  This class will read in and store word spellings from a dictionary file (`words.txt`).  It will provide a method to check whether a given word is spelled correctly (i.e., it is in the dictionary).  It will also provide a method to suggest possible alternatives in the event of a misspelt word.  These suggestions will be formed from the set of "near misses" -- words that are a single edit away from the incorrect word, and are themselves valid words.

We have arranged this assignment into three phases, and recommend that you complete them in order.  Successful completion of phase one will earn at least a B.  Phase two comprises the remaining points on the assignment, and has two subparts.  Phase three is for kudos only.

- For phase one you will complete development of the `SpellDictionary` class described above.  You will also include comprehensive tests of each of its methods; these tests will be performed in `Main`.

- For phase two you will also write a `SpellChecker` class that operates in two possible modes.  In command-line mode it will provide explicit feedback on the spelling of specific words.  In file scanning mode it will take input from a file and print messages only for words that are not spelled correctly.  More details are given below about each of these modes.  You can choose to implement just one.

- For phase three you will create a second version of `SpellDictionary` that replaces the `HashSet`with our `SimpleSet` implementation for storing words.  Using the `Timer` class you will measure and compare the running time of the two dictionaries, and report in your readme on the observed differences and why they might be so.  (*Note: when running timing experiments, make sure that you start the timer after the dictionaries have been loaded.  Only time the word checking operations.  You will probably have to check more than just one word to get measurable times -- one strategy is to spell-check every word in the dictionary.*)

## Phase 1 Details:  SpellDictionary

Your constructor should allocate an empty `HashSet` and then fill it by reading words one at a time from a file whose name is given as a parameter.  (We will use the provided file named `words.txt`.)  You may wish to monitor what is happening with debugging print statements, but the final submission should load the dictionary silently.

The class will not provide any ordinary getter/setter methods for the set of word spellings, since other parts of your program should have no need to access it directly and we don't want to create opportunities for accidental changes.

The operations you should provide are described in the interface `SpellingOperations`, which your dictionary class should implement.  The two operations are described further below:

* You will provide a method to check whether the dictionary   `containsWord`  for a particular spelling, returning true or false.

* You will also provide a `nearMisses` method that will return an `ArrayList` of correctly spelled words that are exactly one edit away from a given incorrect word spelling.  It will do this by constructing all possible near misses, checking them against the dictionary, and returning any that are real words, without duplicates. 

You should consider the following types of near misses:

#### Deletions

Delete one letter from the word. (n possibilities for a word of length n)  
E.g.: **catttle** -> **cattle**

#### Insertions

Insert one letter into the word at any point. (26*(n+1) possibilities for a word of length n)  
E.g.: **catle** -> **cattle**

#### Substitutions

Replace one character with another. (25*n possibilities for a word of length n)  
E.g.: **caxtle** -> **cattle**

#### Transpositions

Swap two adjacent characters. (n-1 possibilities for a word of length n)  
E.g.: **cattel** -> **cattle**

#### Splits

Divide the word into two legal words. (n-1 possibilities for a word of length n) -- for this kind of near miss, the pair of words together should be recorded as a single entry, with a space between them. 
E.g.: **cattell** -> **cat tell**

(Note that there may be more than one way to generate a particular near miss using the above rules; the list returned by `nearMisses` should still include it only once.  Hint:  Think creatively about how you might eliminate any duplicates most efficiently.)

While debugging, you will probably want to have your program print out all the near-miss candidates it creates for some short (two-letter) nonsense word. Inspect this list carefully to make sure all desired candidates are present and correct, and there are no duplicates.

Your program may ignore capitalization and punctuation. You can do this by converting all words to lower case before checking their spelling, and by making sure that the ``Scanner`` skips over punctuation marks.  If you like you may implement a more sophisticated handling of capitalization -- please describe the choice in your readme in case you do.

Note that `SpellDictionary` should not print anything or interact directly with the user -- that will be the job of other classes.  Imagine that this is a class we might hope to use in many ways -- for the text-based checking described here, in an email program, for a window-based word processor, etc.  Printing to the `System.out` would not make sense for many of these modalities.

## Details:  Main

Similar to our testing paradigm on the first assignment of the semester, you should write tests to verify that `SpellDictionary` is functioning properly.  You are encouraged to use use JUnit if you wish; another more labor-intensive option is to write tests that you check by hand.

Your goal should be to make at least **one test of each specific behavior** of the dictionary class.  In particular, that means trying out _each style of near-miss_ to ensure that they are included in the suggestions.

Think about edge cases (like word endings and beginnings) and anything else that might be tricky.  You want to test your class as thoroughly as possible -- we'll also be running automated tests featuring some of our favorite misspelt words.

## Phase 2 Details: SpellChecker

The `SpellDictionary` class described above is not designed for user interation.  That role falls to `SpellChecker`:  its primary job is to interact with the user, in one of two ways.  Start with the first, and then add the second later.  Both modes of operation will be initiated via the command line.

You will run the program from the terminal by typing something like the following:

    java SpellChecker qest questt quest

The program will run, and might then respond with something like:

    Not found:  qest
      Suggestions:  cest est fest gest hest jest mest nest quest rest test vest yest zest 
    Not found:  questt
      Suggestions:  quest quests 
    'quest' is spelled correctly.

In the example, we provided three words to be checked, and the program responds to each one.  Note that in this style of program invocation, the three words are **command line arguments**, and they are relayed to your program via the `String[] args` parameter of the `main` function.  When command line arguments are provided, the program prints a message about every word, whether it is correct or not, and makes suggestions for incorrect words.  This is the first mode of operation.

The second mode of operation is invoked like this:

    java SpellChecker < sonnet.txt

The `<` in this mode indicates that we are sending the contents of the file `sonnet.txt` to the program for spell checking (this is called [input redirection](https://www.science.smith.edu/~nhowe/teaching/csc210/Tutorials/redir.php).  You will know that this mode has been invoked because `args.length` in `main` will be 0 (unlike the previous mode).  Detecting this, your program then will read the file contents using a `Scanner` taking input from `System.in`.

In this mode of operation, the program should read individual words from the input file and check their spelling.  If the word is spelled correctly the program should **silently** move on to the next word -- no message will be printed. On the other hand, if a word is misspelt, your program should print a message and offer suggestions for the correct spelling.  This message should appear *only* the first time a particular misspelling is encountered, even if the same misspelling appears several times. Make this happen in as efficient a way as you can.

You have some freedom about how to arrange the code into methods within this class.  Probably you will want separate methods for each mode of operation; you may also refine things further.  It is ok to use only static methods in this class.

Once you have your program working, you will want to test it out. Here's a sonnet in Shakespeare's original spelling that would make a good candidate:

<pre style="left-margin:0.5in; font-style:italic">Shall I compare thee to a Summers day?
Thou art more louely and more temperate:
Rough windes do shake the darling buds of Maie,
And Sommers lease hath all too short a date:
Sometime too hot the eye of heauen shines,
And often is his gold complexion dimm'd,
And euery faire from faire some-time declines,
By chance, or natures changing course vntrim'd
But thy eternal Sommer shall not fade,
Nor loose possession of that faire thou ow'st,
Nor shall death brag thou wandr'st in his shade,
When in eternall lines to time thou grow'st,
So long as men can breath or eyes can see,
So long liues this, and this giues life to thee.
</pre>

<!--### Some Notes on Design
This is a good opportunity to practice what you have learned about class design. Specifically, try to put methods in logical places **based upon the roles of the classes**:

- Think of ``SpellDictionary`` as a class that might be used by other programs -- a word processor, for example, or an email program -- so try to keep it general, while still offering useful and powerful methods. 
- Your ``SpellCheck`` program is one specific application. If you design ``SpellDictionary`` well, then your ``SpellCheck`` will be quite short... but you don't want ``SpellDictionary`` catering too much to ``SpellCheck``'s specific implementation!
-->

## Phase 3 Details:  Experiments (Kudos!)

Your spell checker provides a good opportunity to investigate the practical differences between different lookup structures. Make two new files with different versions of your program, the first using a ``TreeSet`` instead of a ``HashSet``, and the second using an ``ArrayList``. These should be relatively transparent substitutions, since the important method names are the same. 

You can use the `Timer` class to measure how long a piece of code takes to run.  Use `start()` to start the time running and `stop()` to stop it and return the elapsed time in seconds.  Check the running time of all three variations, and note the results in your readme file. Keep in mind that it takes some time just to build the dictionary, so you really should run two tests for each program: one with an empty file so you can see how long just the dictionary-building takes, and another where you spell-check the dictionary itself so you can see what the performance is like on a large file. The time difference between the two runs is the amount spent spell-checking. Compare these numbers for the three different data structures. Do the results match your expectations?  Include your observations in your reflection.

<!--### To Submit

*   ``SpellDictionary.java``
*   ``SpellCheck.java``
*   ``typescript``, showing the program compiling and running
*   ``readme.txt``, summarizing what you learned, including the results of your timing experiments.-->

### Acknowledgment

_The concept for this assignment is due to [David Eck](http://math.hws.edu/eck/)_