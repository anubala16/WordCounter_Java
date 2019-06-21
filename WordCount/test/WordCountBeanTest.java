import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Testing the WordCountBean class 
 *  
 * @author anusha 
 * 6/21/2019 
 */
public class WordCountBeanTest {

	@Test
	public void testWordCountBean() {
		WordCountBean wcbean = new WordCountBean("hello", 2);
		assertEquals(2, wcbean.getCount());
		assertNotEquals(0, wcbean.getCount());
		assertEquals("hello", wcbean.getWord());
		assertNotEquals("Hello", wcbean.getWord());
	}

	@Test
	public void testToString() {
		WordCountBean wcbean = new WordCountBean("hello", 2);
		assertEquals("hello (2)", wcbean.toString());
	}

	@Test
	public void testCompareTo() {
		WordCountBean wcbean = new WordCountBean("hello", 2);
		WordCountBean wcbean2 = new WordCountBean("hello", 2);
		assertEquals("same word, same count", 0, wcbean.compareTo(wcbean2));
		
		wcbean2 = new WordCountBean("hello", 3);
		assertEquals("same word, higher count", -1, wcbean.compareTo(wcbean2));
		
		wcbean2 = new WordCountBean("hello", 1);
		assertEquals("same word, lower count", 1, wcbean.compareTo(wcbean2));
		
		wcbean2 = new WordCountBean("Hello", 2);
		assertEquals("diff word, same count", 'h'-'H', wcbean.compareTo(wcbean2));
		
	}

	@Test
	public void testEqualsObject() {
		WordCountBean wcbean = new WordCountBean("hello", 2);
		WordCountBean wcbean2 = new WordCountBean("hello", 2);
		assertEquals("same word, same count", true, wcbean.equals(wcbean2));
		
		wcbean2 = new WordCountBean("hello", 3);
		assertEquals("same word, diff count", false, wcbean.equals(wcbean2));
		
		wcbean2 = null; 
		assertEquals("null object", false, wcbean.equals(wcbean2));
		
		Object obj = new String("");
		assertEquals("WordCountBean versus String", false, wcbean.equals(obj)); 		
	}

}
