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
    //Test pour un utilisateur
    public void createAndTestnewUser(){
    	// create a new user and save it
    	new User("john","avion123","john@gmail.com").save();
    	
    	User john = User.find("email","john@gmail.com").first();
    	//Test
    	assertNotNull(john);
    	assertEquals("john@gmail.com",john.email);
    }
    
}
