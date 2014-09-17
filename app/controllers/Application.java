package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import play.Logger;

import models.*;

public class Application extends Controller 
{
    public static void index() {
    	User user = new User();
    	user.username = "spoder";
    	user.firstName = "max";
    	user.lastName = "stonge";
    	user.email = "max@spoderman.com";
    	user.password = "123123";
    	user.save();
        render();
    }

    public static void login(String username, String password) {
    	User user = User.find("username", username).first();
    	Boolean login = user != null && user.password.equals(password);
    	Logger.info("Login: "+login);
    	render(login);
    }
}