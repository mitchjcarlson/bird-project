import static org.junit.Assert.*;

import org.junit.Test;


public class SearchTest {
	
	Search search = new Search(" 	bird, 	red 	robin  ");

	@Test
	public void testSearch() {

		
		assertEquals("bird", search.get(0));
		assertEquals("red", search.get(1));
		assertEquals("robin", search.get(2));
	}

	@Test
	public void testGet() {
		search.getColor();
		search.getName();
	}
}
