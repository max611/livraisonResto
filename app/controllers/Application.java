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

public class Application extends Controller 
{
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
        render();
    }

    public static void updateResto(String restoName) {

        Restaurant resto = Restaurant.find("name", restoName ).first();
        
        render(resto);
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
            
            List<Restaurant> listeResto = Restaurant.find("admin", session.get("username")).fetch();
            render(login,listeResto);
        
        }
    }

    public static void deleteResto() {


            


        render();
    }

    public static void confirmationRestoDel(String name) {

        Restaurant resto = Restaurant.find("name", name ).first();

        resto.delete();
        render(name);
    }

    public static void passerCommande() {

        render();
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

    public static void confirmationModificationResto(String name, String admin, String description) {
    

        Restaurant resto = Restaurant.find("name", session.get("restaurant") ).first();
        
        resto.name = name;
        resto.admin = admin;
        resto.description = description;
        
        resto.save();
        
        render(resto);
    
    }

    public static void confirmationCreationResto(String name, String admin, String description) {

        Restaurant resto = new Restaurant();
        resto.name = name;
        resto.admin = admin;
        resto.description = description;


        resto.save();

        render(resto);
    
    }

    public static void manageRestaurateur() {

        render();
    
    }

    public static void nouveauRestaurateur() {

        render();
    
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
        test.password = password;
        test.phonenumber = phonenumber;

        Boolean restaurantEmpty = true;

        if(restaurant.isEmpty() ){
        restaurantEmpty = false;
        }

        test.restaurant = restaurant;

        
        test.save();

        Restaurateur restaurateur = test;
        render(restaurateur,restaurantEmpty);
    }

    public static void deleteRestaurateur(String restoName) {

        Restaurateur resto = Restaurateur.find("username", restoName ).first();

        resto.delete();
        render(restoName);


    }

    public static void updateRestaurateur(String restoName) {

        Restaurateur resto = Restaurateur.find("username", restoName ).first();
        Boolean restaurant = true;

        if(resto.restaurant.isEmpty() ){
        restaurant = false;
        }

        render(resto,restaurant);

    }

    public static void confirmationModificationRestaurateur(String username, String firstName, String lastName, String restaurant) {

        Restaurateur resto = Restaurateur.find("username", username ).first();
        
        
        resto.firstName = firstName;
        resto.lastName = lastName;
        resto.restaurant = restaurant;
        
        resto.save();
        
        render(resto);
    
        
    }


}