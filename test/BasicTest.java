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
    	// create a new users and save it
    	new User("john","Shine","lena","avion123","john@gmail.com","123456","client").save();
    	User john = User.find("email","john@gmail.com").first();
    	User john1 = User.find("type","cli").first();
    	new User("john2","avion123","john2@gmail.com").save();
    	User john2 = User.find("firstName","john2").first();
    	
    	// create a new restaurateur
    	new Restaurateur("restaurateur","Shine","lena","restaurateur@gmail.com","123456","restaurant").save();
    	Restaurateur restaurateur = Restaurateur.find("email","restaurateur@gmail.com").first();
    	// create a new restaurant
    	new Restaurant("restaurant","admin","descr").save();
    	Restaurant restaurant = Restaurant.find("name","restaurant").first();
    	// create a new plat
    	new Plats("plat","Salade",10,"menu1").save();
    	Plats plat = Plats.find("name","plat").first();
    	// create a new Menu
    	new Menu("name","restoname").save();
    	Menu menu = Menu.find("restoName","restoname").first();
    	// create a new LignePanier
    	new LignePanier("plat",1).save();
    	LignePanier lignepanier = LignePanier.find("plats","plat").first();
    	// create a new commande
    	new Commande("dateLivraison","heureLivraison","adressLivraison").save();
    	Commande commande = Commande.find("adressLivraison","adressLivraison").first();
    	// create a new Adresse
    	new Adresse("address","username").save();
    	Adresse adresse = Adresse.find("user","username").first();
    			
    	//Test
    	assertNotNull(john);
    	assertEquals("john@gmail.com",john.email);
    	assertNotNull(restaurateur);
    	assertEquals("restaurateur@gmail.com",restaurateur.email);
    	assertNotNull(restaurant);
    	assertEquals("restaurant",restaurant.name);
    	assertNotNull(plat);
    	assertEquals("plat",plat.name);
    	assertNotNull(menu);
    	assertEquals("restoname",menu.restoName);
    	assertNotNull(lignepanier);
    	assertEquals("plat",lignepanier.plats);
    	assertNotNull(commande);
    	assertEquals("adressLivraison",commande.adressLivraison);
    	assertNotNull(adresse);
    	assertEquals("username",adresse.user);
    }
    //Test de connection d'un utilisateur
    public void connectAsUser(){
    	new User("john","avion123","john@gmail.com").save();
    	//Test
    	assertNotNull(User.connect("john","avion123"));
    	assertNotNull(User.connect("john","error"));
    	assertNotNull(User.connect("blaise","avion123"));
    	assertNotNull(Restaurateur.connect("john","avion123"));
    	assertNotNull(Restaurant.connect("john","error"));
    	
    }
    public void connectAsRestaurateur(){
    	new Restaurateur("restaurateur","Shine","lena","restaurateur@gmail.com","123456","restaurant").save();
    	//Test
    	assertNotNull(Restaurateur.connect("restaurateur","avion123"));
    	assertNotNull(Restaurateur.connect("restaurateur1","error"));
    	assertNotNull(Restaurateur.connect("restaurateur2","avion123"));
    	
    }
    public void connectAsRestaurant(){
    	new Restaurant("restaurant","admin","descr").save();
    	//Test
    	assertNotNull(Restaurateur.connect("restaurant","avion123"));
    	assertNotNull(Restaurateur.connect("restaurant1","error"));
    	assertNotNull(Restaurateur.connect("restaurant2","avion123"));
    	
    }
    //methode permettant de supprimer la base de donnee apres un test
    @Before
    public void setup(){
    	Fixtures.deleteDatabase();
    }
    
}
