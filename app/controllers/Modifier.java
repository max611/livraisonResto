package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import org.apache.commons.mail.*;
import play.Logger;
import models.*;
import com.google.code.morphia.annotations.Entity;
import play.libs.Mail;
import javax.mail.Folder;

public class Modifier extends Controller {

	public static void update(String username, String password, String phonenumber, String adresse) {
        Logger.info("username dans la session = " + session.get("username"));
        User test = User.find("username", session.get("username") ).first();
        Adresse ad = Adresse.find("user", test.username).first();
        
        ad.adresse = adresse;
        test.password = password;
        test.phonenumber = phonenumber;
        
        test.save();
        ad.save();
        
        render(test, ad);
    }

}