package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import play.Logger;
import models.*;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    static List<Plats> panier = new ArrayList<Plats>();

    public static void index() {
    	User user = User.find("username", session.get("username") ).first();
    	Boolean	login = user != null && user.username != null;
    	
        render(login,user);
    }

    public static void login(String username, String password) {
    	User user = User.find("username", username).first();
    	Logger.info("User: "+ user.username);
    	Logger.info("password: "+ user.password);
    	Boolean	login = user != null && user.password.equals(password);
    	Logger.info("Login: "+login);
    	//Scope.Session("username",user.username);
    	
		session.put("username", username);
		Logger.info("test session = " + session.get("username"));


    	render(login,user);
 
    }

    public static void create(String username, String password, String email, String firstname,
     String lastname, int phonenumber, String accountType) {
		User test = new User();
		test.username = username;
		test.firstName = firstname;
		test.lastName = lastname;
		test.email = email;
		test.password = password;
		test.phonenumber = phonenumber;
    	
		test.save();
		User user = User.find("username", username).first();

		render(user);
    }

        public static void update(String username, String password, String firstname, String lastname, int phonenumber) {
        Logger.info("username dans la session = " + session.get("username"));
        User test = User.find("username", session.get("username") ).first();
        
		test.firstName = firstname;
		test.lastName = lastname;
		test.password = password;
		test.phonenumber = phonenumber;
    	
		test.save();
		
		render(test);
    }

    public static void formulaire() {

        render();
    }


    public static void logout() {
    	session.clear();
    	Logger.info("Session apres logout: "+ session);
    		index();
    }

    public static void account(String username, String password, String firstname, String lastname, int phonenumber) {
        Logger.info("username dans la session = " + session.get("username"));
        User test = User.find("username", session.get("username") ).first();

		render(test);
    }

    public static void manageMenus(){
    	render();
    }

    public static void formulaireResto() {
        List<Restaurateur> listeResto = Restaurateur.findAll();
        render(listeResto);
    }

    public static void updateResto(String restoName) {

        Restaurant resto = Restaurant.find("name", restoName ).first();
        session.put("restaurant", restoName);
        List<Restaurateur> listeResto = Restaurateur.findAll();
        
        render(resto,listeResto);
    }

    public static void manageResto(String name, String admin, String description) {
        Boolean login = name != null;
        
        if(login){

        Restaurant resto = new Restaurant();
        resto.name = name;
        resto.admin = admin;
        resto.description = description;


        resto.save();

        render(resto,login);
            
        }
        else{
            
            //List<Restaurant> listeResto = Restaurant.find("admin", session.get("username")).fetch();
            List<Restaurant> listeResto = Restaurant.findAll();
            render(login,listeResto);
        
        }
    }

    public static void deleteResto() {

        List<Restaurant> listeResto = Restaurant.findAll();
        render(listeResto);
            


        render();
    }

    public static void confirmationRestoDel(String restoName) {

        Restaurant resto = Restaurant.find("name", restoName ).first();

        resto.delete();
        render(restoName);
    }

    public static void updateRestaurant(String name, String restaurateur, String description) {

    Restaurant test = Restaurant.find("name", name ).first();
        
    test.name = name;
    test.admin = restaurateur;
    test.description = description;
    session.put("restaurant", name);
      
    test.save();
        
    render(test);
    
    }

    public static void confirmationModificationResto(String name, String admin, String description, String adresse) {
    

        Restaurant resto = Restaurant.find("name", session.get("restaurant") ).first();

        
        resto.name = name;
        resto.admin = admin;
        resto.description = description;
        resto.adresse = adresse;
        
        resto.save();
        
        render(resto);
    
    }

    public static void confirmationCreationResto(String name, String restoName, String description, String adresse) {

        Restaurant resto = new Restaurant();
        resto.name = name;
        resto.admin = restoName;
        resto.description = description;
        resto.adresse = adresse;


        resto.save();

        render(resto);
    
    }

    public static void manageRestaurateur() {

        render();
    
    }

    public static void nouveauRestaurateur() {
        List<Restaurant> listeResto = Restaurant.findAll();
        render(listeResto);
    
    }

    public static void modificationRestaurateur() {

        List<Restaurateur> listeResto = Restaurateur.findAll();
        render(listeResto);
    
    }

    public static void supprimerRestaurateur() {

        List<Restaurateur> listeResto = Restaurateur.findAll();
        render(listeResto);
    
    }

    public static void createRestaurateur(String username, String password, String email, String firstname,
        String lastname, int phonenumber, String restaurant) {
        Restaurateur test = new Restaurateur();
        test.username = username;
        test.firstName = firstname;
        test.lastName = lastname;
        test.email = email;
        test.phonenumber = phonenumber;


        test.restaurant = restaurant;
        //Restaurateur resto = Restaurateur.find("name", restaurant ).first();
        //resto.restaurateur = 
        
        test.save();

        Restaurateur restaurateur = test;
        render(restaurateur,restaurant);
    }

    public static void deleteRestaurateur(String restoName) {

        Restaurateur resto = Restaurateur.find("username", restoName ).first();

        resto.delete();
        render(restoName);


    }

    public static void updateRestaurateur(String restoName) {

        Restaurateur resto = Restaurateur.find("username", restoName ).first();
        Boolean restaurant = true;

        List<Restaurant> listeResto = Restaurant.findAll();


        if(resto.restaurant.isEmpty() ){
        restaurant = false;
        }

        render(resto,restaurant,listeResto);

    }

    public static void confirmationModificationRestaurateur(String username, String firstName, String lastName, String restaurant) {

        Restaurateur resto = Restaurateur.find("username", username ).first();
        
        
        resto.firstName = firstName;
        resto.lastName = lastName;
        resto.restaurant = restaurant;
        
        resto.save();
        
        render(resto);
    
        
    }

    public static void createMenu(String name, String resto) {
            Menu m = new Menu();
            m.name = name;
            m.restoName = resto;
        
        m.save();
        Menu menu = Menu.find("name", name).first();
        session.put("resto", name);
        render(menu);
    }

    public static void FormulaireMenu() {
        List<Restaurant> listeResto = Restaurant.findAll();
        render(listeResto);
    }

    public static void createPlat(String name, String menu, String prix) {
            Menu m = Menu.find("name", menu).first();
            m.plat = name;
            m.save();
            Plats p = new Plats();
            p.name = name;
            p.prix = prix;
            p.menu = menu;



        p.save();
        Plats plat = Plats.find("name", name).first();

        render(plat);
    }

    public static void FormulairePlat() {
        List<Menu> listeMenu = Menu.findAll();
        render(listeMenu);
    }
    
    public static void passerCommande() {
           
        List<Restaurant> listeResto = Restaurant.findAll();
        List<Menu> listeMenu = Menu.findAll();
        render(listeMenu,listeResto);
    }


    public static void passerCommandeMenu(String menuName) {
    Menu m = Menu.find("name", menuName).first();
    Restaurant r = Restaurant.find("name", m.restoName).first();

    //List<Plats> listePlat = Plats.findAll();
    List<Plats> listePlat = Plats.find("menu", menuName).fetch();
        render(listePlat,r);


    }

    public static void ajouterPlats(String plats) {
     

        Plats p = Plats.find("name", plats).first();

        Panier panier = new Panier();
        panier.name = p.name;
        panier.prix = p.prix;


        panier.save();

        List<Plats> listePlat = Plats.findAll();

        render(listePlat);


    }

    public static void terminerCommande() {
          
        List<Panier> listePanier = Panier.findAll();


        render(listePanier);


    }


    public static void payerCommande() {
        int total = 0;
        
        List<Panier> listePanier = Panier.findAll();
        Panier test = listePanier.get(0);


        for (int i=0; i<listePanier.size(); i++){
        Plats p = Plats.find("name", listePanier.get(i).name ).first();
        //total += listePanier.get(i).prix();
        total += Integer.parseInt(p.prix);
        }

        render(total);


    }

}