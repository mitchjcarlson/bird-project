
import static org.junit.Assert.*;

import org.junit.Test;

public class SearchTest {
	
	Search searchObj = new Search(" 	bird, 	red 	robin  washington blue");

	@Test
	public void testGetColors() {
		String[] expecteds = {"red", "blue"};
		String[] actuals = searchObj.getColors();
		assertArrayEquals(expecteds, actuals);		
	}
	
	@Test
	public void testGetLocations() {
		String[] expecteds = {"washington"};
		String[] actuals = searchObj.getLocations();
		assertArrayEquals(expecteds, actuals);	
	}
}
