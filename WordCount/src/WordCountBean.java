/**
 * 
 */

/**
 * @author anuba
 * 6/21/2019 
 */
public class WordCountBean implements Comparable {
	
	private String word; 
	private int count; 
	
	public WordCountBean(String word, int count) {
		this.word = word; 
		this.count = count;
		
	} 
	
	public String toString() {
		return word + " (" + count + ")";
	}
	
	/**
	 * Would be used by heap ("priority queue"), set, etc. to compare elements 
	 * 
	 * For a Set, it wouldn't be enough to say two beans are equal if counts match, 
	 * it would discard another word with same frequency. To avoid this, we also compare the 
	 * words when counts match. 
	 * 
	 * @param o other object to compare 
	 * @return 0 if counts AND words match, > 0 if this word count is higher than the other or counts match and words differ, 
	 * and < 0 if this word count is lower than the count of given object and words differ  
	 */
	@Override
	public int compareTo(Object o) {
		if (!(o instanceof WordCountBean)) {
			System.out.println("Can't compare: " + this + " and " + o);
			return 0;
		} 
		WordCountBean other = (WordCountBean) o;
		if (other.getCount() == this.count) 
			
			// for descending order, to maintain natural lexicographic order use inverse calculation
			//return other.getWord().compareTo(word);    
			return word.compareTo(other.getWord()); 
		
		return this.count - other.getCount();
	}
	
	/**
	 * Two beans are equal if and only if both words and counts match because 
	 * we don't want a Set to feature only one word with frequency 1 
	 * 
	 * @param o Other object 
	 * @return true if both word and count match, false otherwise 
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof WordCountBean)) {
			//System.out.println("Can't compare: " + this + " and " + o);
			return false;
		} 
		WordCountBean other = (WordCountBean) o;
		if ((other.getCount() == this.count) && word.equals(other.getWord())) {
			return true;
		}
		return false;
		
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	} 

}
