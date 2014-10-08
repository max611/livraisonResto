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
    //Test de creation d'un utilisateur
    public void createAndTestnewUser(){
    	// create a new user and save it
    	new User("john","avion123","john@gmail.com").save();
    	
    	User john = User.find("email","john@gmail.com").first();
    	//Test
    	assertNotNull(john);
    	assertEquals("john@gmail.com",john.email);
    }
    //Test de connection d'un utilisateur
    public void connectAsUser(){
    	new User("john","avion123","john@gmail.com").save();
    	//Test
    	assertNotNull(User.connect("john","avion123"));
    	assertNotNull(User.connect("john","error"));
    	assertNotNull(User.connect("blaise","avion123"));
    	
    }
    //methode permettant de supprimer la base de donnee apres un test
    @Before
    public void setup(){
    	Fixtures.deleteDatabase();
    }
    
}
