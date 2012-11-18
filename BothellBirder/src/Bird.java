
public class Bird {
	private int birdID;
	private String birdColors, birdLocation, birdDescription, birdPicture, birdSound, birdScientificName;
	
	//this is obviously not even close to done, just wanted to play with it a bit since it's been a year since I used java
	public Bird(int idNum, String colors, String loc, String description, String pic, String sciName, String sound ) {
	    birdID = idNum; //I'm guessing we actually want the birdclass to provide the num here, and return it? or do we want to manually set it each time?
	    birdColors = colors;
	    birdLocation = loc; //probably dont want this as a string, just getting something in here for now
	    birdDescription = description;
	    birdPicture = pic; //how are we going to store pics? Just as a loc in the db?
	    birdScientificName = sciName; 
	    birdSound = sound; //same question about pic
	    
	}
	public Bird() {
	    //default constructor, need to have conversations about this
	}
	//will get getter setters in here in a bit, after some lunch
}