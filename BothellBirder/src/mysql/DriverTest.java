package mysql;

public class DriverTest {
	
	public static void main( String args[] ) throws Exception {
	
		TestDatabase test = new TestDatabase( "74.54.145.98", "3306", "nofrobro_javaTest", "nofrobro_test", "james1" );
		
		test.runExamples();
	
	}

}
