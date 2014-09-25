package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import play.Logger;
import models.*;

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

    public static void create(String username, String password, String email, String firstname, String lastname, int phonenumber) {
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
        /*
		test.firstName = firstname;
		test.lastName = lastname;
		test.password = password;
		test.phonenumber = phonenumber;
    	
		test.save();
		*/
		render(test);
    }

    public static void manageMenus(){

    	render();
    }

    public static void formulaireResto() {
        render();
    }

    public static void updateResto(String name, String admin, String description) {
        Restaurant resto = new Restaurant();
        resto.name = name;
        resto.admin = admin;
        resto.description = description;


        resto.save();

        render(resto);
    }

    public static void manageResto(String name, String admin, String description) {
        Restaurant resto = new Restaurant();
        resto.name = name;
        resto.admin = admin;
        resto.description = description;


        resto.save();

        render(resto);


        render();
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

}