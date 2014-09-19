package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import play.Logger;

import models.*;

public class Application extends Controller 
{
    public static void index() {
        render();
    }

    public static void login(String username, String password) {
    	User user = User.find("username", username).first();
    	Boolean login = user != null && user.password.equals(password);
    	Logger.info("Login: "+login);
    	Scope.Session("username",user.username);
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
		String name = user.username;
		render(user);
    }

        public static void update(String password, String firstname, String lastname, int phonenumber) {
		User test = User.find("username", session("username")).first();

		test.firstName = firstname;
		test.lastName = lastname;
		test.password = password;
		test.phonenumber = phonenumber;
    	
		test.save();
		render();
    }

    public static void formulaire() {

        render();
    }
}