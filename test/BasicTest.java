import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    /*
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }
*/
    public void createAndTestnewUser(){
    	// create a new user and save it
    	new User("john@yahoo.fr", "avion123","john").save();
    	
    	User john = User.find("byEmail", "john@yahoo.fr").first();
    	//Test
    	assertNotNull(john);
    	assertEquals("john",john.fullname);
    }
}
