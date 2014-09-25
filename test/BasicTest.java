import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    /* void createAndTestnewUser(){
    	// create a new user and save it
    	new User("john","avion123").save();
    	
    	User john = User.find("byName", "john").first();
    	//Test
    	assertNotNull(john);
    	assertEquals("john",john.fullname);
    }
    */
}
