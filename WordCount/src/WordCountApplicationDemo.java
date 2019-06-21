import java.util.ArrayList;

/**
 * Demo two methods of WordCounterApplication 
 * 
 * Method 1: Using inverse HashMap and simple sorted set 
 * 
 * Method 2: Heap sort with custom WordCountBean Comparator  
 * @author anusha 
 * 6/21/2019 
 */
public class WordCountApplicationDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String fileName = "simple.txt";
		WordCounterApplication app = new WordCounterApplication(); 
		
		ArrayList<WordCountBean> wordCounts = null;
		try {
			// Method 1: Using inverse HashMap and simple sorted set 
			wordCounts = app.getWordCounts(fileName);
			System.out.println("Method 1 (Hashmaps and Max Heap using default comparator) Result: \n--------------------");
			printList(wordCounts);
			System.out.println("-------------------------\n");
			
			// Method 2: Heap sort with custom WordCountBean Comparator 
			wordCounts = app.heapifyWordCounts(fileName);
			System.out.println("Method 2 (Heap Sort using custom Comparator) Result: \n--------------------");
			printList(wordCounts); 
			System.out.println("**Note: Words are in reverse alphabetical order for Method 2 as we are sorting in descending order.");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	// Helper function to print result 
	public static void printList(ArrayList<WordCountBean> wordCounts) {
		for (WordCountBean wordCount : wordCounts) {
			System.out.println(wordCount);
		}
		System.out.println();
	}

}
