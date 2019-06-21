import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Application to count word frequencies and sort them in descending order. 
 * 
 * @author anusha  
 * 6/21/2019 
 */
public class WordCounterApplication {
	
	// dictionary/HashMap with words as keys and word  frequency as values 
	private HashMap<String, Integer> wordCounts; 
	
	/**
	 * Simple constructor to initialize hahmap 
	 */
	public WordCounterApplication() {
		wordCounts = new HashMap<>();
	}
	
	/**
	 * Method 1: More Manual way (with larger memory footprint). 
	 *  
	 * O(m log m) => O(n log n) where m is the number of unique words and n is total number of words in the file 
	 * 		O(n) processFile() => we parse input file and create a dictionary with word frequencies 
	 * 		
	 * 		O(n log n) sortWords() => explained in function's javadoc  
	 * 
	 * @param fileName 
	 * @return list of words and their counts ("WordCountBean") in descending order. CASE-INSENSITIVE 
	 * @throws Exception 
	 */
	public ArrayList<WordCountBean> getWordCounts(String fileName) throws Exception {
		// reset word counts before processing file (in case it's filled by previous use) 
		wordCounts = new HashMap<>();
		
		processFile(fileName);
		
		// sort and return the words in descending order of frequency 
		return sortWords();
	} 
	
	private void processFile(String fileName) throws Exception {
		// trying to open file 
		File input; 
		Scanner reader = null;
		try {
			input = new File("data/" + fileName);
			reader = new Scanner(input);
		} catch (FileNotFoundException e) {
			throw new Exception("Error opening file. Check if file exists and/or if there are enough permissions to read/write.");
		}
		
		String line, token;
		
		// process file line by line 
		// word by word and update 'wordCounts' counter dictionary 
		while (reader.hasNextLine()) {
			line = reader.nextLine().toLowerCase(); // making input case-insensitive 
			
			Pattern p = Pattern.compile("\\w+");
			Matcher m = p.matcher(line);
			
			/**
			 * this doesn't handle commas and other punctuation well. 
			 * 
			 * Considers word as unique :/ 
			 * The above regex works better.  
			 */
			//tokens = line.split(" "); 
			while (m.find()) {
				token = m.group();
				
				/**
				 * curFreq would be the old value in the hashmap 
				 * so either null - if the word didn't exist before 
				 * or current frequency if the word already existed 
				 */
				Integer curFreq = this.wordCounts.putIfAbsent(token, 1); // adding new word in dict
				
				if (curFreq != null) { // updating an already seen word 
					this.wordCounts.put(token, ++curFreq);
				}
			}
		}
		
		//System.out.println("Raw counts (before sorting): ");
		//System.out.println(wordCounts);
		reader.close();
	}
	
	/** 
	 * O(n log n) where 'n' is the total number of words in the file 
	 * 
	 * 		Creating the max heap with word frequencies in wordCounts dict is O(m * log m) => O(n * log n) 
	 * 			as we add m elements and each operation is approximately log m time. 
	 * 
	 * @return
	 */
	private ArrayList<WordCountBean> sortWords() {
		// create an inverse hashmap of wordCounts where keys are word frequencies and values are the list of words 
		HashMap<Integer, ArrayList<String>> wordCounts_inverse = new HashMap<>();
		
		/**
		 * Initially, I tried creating a priority queue for each value (aka word frequency) in the hashmap
		 * but this didn't work b/c hashmap can take in duplicate values.
		 * A TreeSet doesn't allow duplicates yet keeps the elements in order. 
		 * It's very similar to a heap => O(log n)
		 */
		TreeSet<Integer> maxHeap = new TreeSet<>(); 
		
		// 1. Get all the elements in the hashmap 
		// 2. iterate over them 
		// 3. fill in the inverse hashmap and "maxheap" (actually, a TreeSet) 
		Set<Map.Entry<String, Integer>> entries = this.wordCounts.entrySet();
		
		// O(m log m) => m is the unique words in file 
		for (Map.Entry<String, Integer> entry  : entries) { // "m" times (unique words in file) 
			ArrayList<String> words = new ArrayList<String>(); 
			words.add(entry.getKey());
			
			// approx. constant runtime 
			words = wordCounts_inverse.putIfAbsent(entry.getValue(), words);
			if (words != null) {
				words.add(entry.getKey());
				wordCounts_inverse.put(entry.getValue(), words);
			}
			
			// log m cost per call 
			maxHeap.add(entry.getValue());	
		}
		
		// ------------------ Sorting the words ------------------ 
		/**
		 * Strategy to sort the words: 
		 * 
		 * Poll (Remove) max word count from maxHeap one by one  
		 * Get the words with the associated frequency (from "inverse wordCounts") 
		 * Append the elements to the result 
		 * 
		 * Return result => most to least frequent words with their frequencies 
		 */
		
		// final list of words with their frequencies (sorted) 
		ArrayList<WordCountBean> result = new ArrayList<WordCountBean>();
		
		// pollLast() - retrieve elements in DESCENDING order 
		Integer max = maxHeap.pollLast(); 
		
		// auxiliary variable to hold words with this particular frequency
		ArrayList<String> words;
		while (max != null) { // m log m (call m times, and log m cost to re-arrange the heap) 
			
			// words with this particular frequency
			words = wordCounts_inverse.get(max);
			//Collections.sort(words);
			for (String word : words) {
				// append word and count to result 
				result.add(new WordCountBean(word, max));
			}
			max = maxHeap.pollLast();
		}
		
		// words and their frequencies ("WordCountBeans")  
		// in the descending order. 
		return result;
	} 
	
	/**
	 * Method 2: Heap sort using heap and custom Comparator in WordCountBean 
	 * 
	 * O(m log m) => O(n log n) where m is the number of UNIQUE words and n is TOTAL number of words in the file 
	 * 		
	 * 		O(n) - processFile() - parse input file and create a dictionary with word frequencies is O(n)
	 * 		Creating the max heap with elements in hashmap is O(m * log m) => O(n * log n) 
	 * 			as we add "m" elements and each operation is approximately (log m) time. 
	 * 
	 * @param fileName 
	 * @return list of words and their counts ("WordCountBean") in descending order.  
	 * @throws Exception 
	 */
	public ArrayList<WordCountBean> heapifyWordCounts(String fileName) throws Exception {
		// reset word counts before processing file 
		wordCounts = new HashMap<>();
		
		// O(n) - n is the total words in file 
		processFile(fileName); 
		
		ArrayList<WordCountBean> result = new ArrayList<WordCountBean>();
		
		Set<Map.Entry<String, Integer>> entries = this.wordCounts.entrySet();
		
		// real max heap 
		PriorityQueue<WordCountBean> maxHeap = new PriorityQueue<>(100, Collections.reverseOrder());
		//PriorityQueue<WordCountBean> maxHeap = new PriorityQueue<>();
		WordCountBean temp = null;
		
		for (Map.Entry<String, Integer> entry  : entries) { // called m times 
			temp = new WordCountBean(entry.getKey(), entry.getValue());
			maxHeap.offer(temp); // log m cost to re-arrange ("heapify") the heap 
		} 
		
		temp = maxHeap.poll();
		while (temp != null) { // m iterations 
			result.add(temp);
			temp = maxHeap.poll(); //log m 
		}
		
		// words and their frequencies ("WordCountBeans")  
		// in the descending order. 
		return result;
	}
}

