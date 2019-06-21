import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 */

/**
 * Testing the application class 
 * 
 * @author anusha 
 * 6/21/2019 
 */
public class WordCounterApplicationTest {

	/**
	 * Test method for {@link WordCounterApplication#getWordCounts(java.lang.String)}.
	 */
	@Test
	public void testGetWordCounts() {
		WordCounterApplication app = new WordCounterApplication();
		
		//Test 1 - file doesn't exist 
		try {
			ArrayList<WordCountBean> beans = app.getWordCounts("dummy.txt");
			// must throw exception or fail test 
			Assert.fail();
		} catch (Exception e) {
			assertEquals("file doesn't exist.", "Error opening file. Check if file exists and/or if there are enough permissions to read/write.", e.getMessage());
		}
		
		// Test 2 
		try {
			
			ArrayList<WordCountBean> actual = app.getWordCounts("simple_test1.txt");
			
			ArrayList<WordCountBean> expected = new ArrayList<WordCountBean>();
			expected.add(new WordCountBean("hello", 2));
			expected.add(new WordCountBean("world", 3));
			
			// sorting the results to check equality 
			Collections.sort(expected);
			Collections.sort(actual);
			Assert.assertTrue(expected.equals(actual));
			
			expected.add(new WordCountBean("Hello", 1));
			Assert.assertFalse("Method 1 Test - Expected has 1 extra bean", expected.equals(actual));
			
		} catch (Exception e) {
			Assert.fail("Test shouldn't be failing");
		}
		
	}

	/**
	 * Test method for {@link WordCounterApplication#heapifyWordCounts(java.lang.String)}.
	 */
	@Test
	public void testHeapifyWordCounts() {
		WordCounterApplication app = new WordCounterApplication();
		
		// Test 1 - file doesn't exist 
		try {
			ArrayList<WordCountBean> beans = app.heapifyWordCounts("dummy.txt");
			// must throw exception or fail test 
			Assert.fail();
		} catch (Exception e) {
			assertEquals("file doesn't exist.", "Error opening file. Check if file exists and/or if there are enough permissions to read/write.", e.getMessage());
		}
		
		// Test 2 
		try {
			
			ArrayList<WordCountBean> actual = app.getWordCounts("simple_test1.txt");
			
			ArrayList<WordCountBean> expected = new ArrayList<WordCountBean>();
			expected.add(new WordCountBean("hello", 2));
			expected.add(new WordCountBean("world", 3));
			
			// sorting the results to check equality 
			Collections.sort(expected);
			Collections.sort(actual);
			Assert.assertTrue(expected.equals(actual));
			
			expected.add(new WordCountBean("hello", 1));
			Assert.assertFalse("Method 2 Test - Expected has 1 extra bean", expected.equals(actual)); 
			
			expected = new ArrayList<WordCountBean>();
			expected.add(new WordCountBean("hello", 2));
			Assert.assertFalse("Method 2 Test - Expected has 1 less bean", expected.equals(actual)); 
			
		} catch (Exception e) {
			Assert.fail("Test shouldn't be failing");
		};
	}

}
