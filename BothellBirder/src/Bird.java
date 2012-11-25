
import java.util.Calendar;

public class Bird {
	enum GETSETRETURNSTATUS {
		SUCCESS, FAILURE, UNKNOWN_ERROR;
	}
	
	// We should have these data members be their own class/objects //
	
	private int birdID;
	
	private boolean birdSex, endangeredStatus; //0 for boy, 1 for girl?
	
	private String birdName, birdVideo, birdColors, birdLocation,
			birdDescription, birdPicture, birdSound, birdScientificName;

	// this is obviously not even close to done, just wanted to play with it a
	// bit since it's been a year since I used java
	public Bird(int idNum, String name, String colors, String loc,
			String description, String pic, String sciName, String sound) {
		birdID = birdID(); // I'm guessing we actually want the birdclass to
							// provide the num here, and return it? or do we
							// want to manually set it each time?
		birdName = name;
		birdColors = colors;
		birdLocation = loc; // probably dont want this as a string, just getting
							// something in here for now
		birdDescription = description;
		birdPicture = pic; // how are we going to store pics? Just as a loc in
							// the db?
		birdScientificName = sciName;
		birdSound = sound; // same question about pic
	}

	public Bird(String name) {
		// default constructor, need to have conversations about this, what
		// minimum info etc...
		birdName = name;
		birdID = birdID();
	}
	
	public Bird() {
	}

	// for testing purposes
	public static void main(String[] args) {
		System.out.println(birdID());
	}

	// just a thought, bird id could be time in milliseconds since this should
	// never be repeated?
	// or do we want a unique DB entry identifier num?
	// Drew, can the DB assign the ID?
		// Yeah, the db is set to auto-increment the bridId field.
		// This means no id should ever be the same.

	private static int birdID() {
		Calendar now = Calendar.getInstance();
		int time = (int) now.getTimeInMillis();
		return time;
	}

	public int getId() {
		return birdID;
	}
	
	public void setId( int id ) {
		birdID = id;
	}
	

	// getters and setters
	public String getName() {
		return (birdName == null) ? "NAME NOT SET!" : birdName;
	}

	public void setName(String nameSet) {
		
		//need to setup a way of checking for duplicates, would we query the DB for this?
			// Don't worry about that here.  When an entire bird object is passed into the mysql
			// class, I will have it check for dups before inserting
		birdName = nameSet;
	}

	public String getColor() {
		return (birdColors == null) ? "COLOR NOT SET!" : birdColors;
	}

	public void setColor(String colorSet) {
		birdColors = colorSet;
	}

	public String getLocation() {
		return (birdLocation == null) ? "LOC NOT SET!" : birdLocation;
	}

	public void setLocation(String locationSet) {
		birdLocation = locationSet;
	}

	public String getPicture() {
		return (birdPicture == null) ? "PICTURE NOT SET!" : birdPicture;
	}

	// We probably want a separate picture class that can format based on device
	// size, unless android can do this on it's own?
	public void setPicture(String pictureSet) {
		birdPicture = pictureSet;
	}

	public String getVideo() {
		return (birdVideo == null) ? "VIDEO NOT SET!" : birdVideo;
	}

	public void setVideo(String videoSet) {
		birdVideo = videoSet;
	}

	public String getSound() {
		return (birdSound == null) ? "SOUND NOT SET!" : birdSound;
	}

	public void setSound(String sound) {
		birdSound = sound;
	}

	public String getDescription() {
		return (birdDescription == null) ? "DESCRIPTION NOT SET!"
				: birdDescription;
	}

	public void setDescription(String description) {
		birdDescription = description;
	}

	public String getScientificName() {
		return (birdScientificName == null) ? "SCIENTIFICNAME NOT SET!"
				: birdScientificName;
	}

	public void setScientificName(String scientificName) {
		birdScientificName = scientificName;
	}
	
	public boolean getEndangeredStatus() {
		return endangeredStatus;
	}
	
	public void setEndangeredStatus( boolean eS ) {
		endangeredStatus = eS;
	}
	
	public boolean getSex() {
		return birdSex;
	}
	
	public void setSex( boolean s ) {
		birdSex = s;
	}
}