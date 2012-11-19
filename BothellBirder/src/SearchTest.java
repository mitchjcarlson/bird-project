import static org.junit.Assert.*;

import org.junit.Test;


public class SearchTest {

	@Test
	public void testSearch() {

		Search values = new Search(" 	bird, 	red 	robin  ");
		
		assertEquals("bird", values.get(0));
		assertEquals("red", values.get(1));
		assertEquals("robin", values.get(2));
	}

}
