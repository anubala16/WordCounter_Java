# WordCounter_Java
Simple word count application in Java using Hashmaps and HeapSort

### Getting Started & Running the application 

* To clone the repo: 

  `git clone 'https://github.com/anubala16/WordCounter_Java'
`
* Import the project into an IDE (preferably, Eclipse): 

* To run the application: 

  ` javac WordCountApplicationDemo.java`

  `java WordCountApplicationDemo `

### Project Structure 

* **src/** - application source files

   *  WordCountBean.java - simple bean class to store "word" and "count"
   *  WordCounterApplication.java - main logic to process input file and output word counts; Two algorithms implemented:
       * Method 1: Use hashmap1 to store "word: count", hashmap2 to store "count: words" and maxHeap to store "counts". Retrieve max counts from heap and output words with that frequency.
       *  Method 2: Heap sort WordCountBean objects
      *  **Both algorithms yield ***O(n log n)*** runtime although the second algorithm is simpler, needs lesser space, and needs fewer operations.**
   * WordCountApplicationDemo.java - program to demo the app

* **data/** - text file(s) to demo
* **test/** - some JUnit test cases

    * WordCountBean.java and
    * WordCounterApplicationTest.java 
