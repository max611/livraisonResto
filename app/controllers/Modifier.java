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

/*import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;*/

public class Modifier extends Controller {

	public static void update(String username, String password, String firstname, String lastname, String phonenumber) {
        Logger.info("username dans la session = " + session.get("username"));
        User test = User.find("username", session.get("username") ).first();
        
        test.firstName = firstname;
        test.lastName = lastname;
        test.password = password;
        test.phonenumber = phonenumber;
        
        test.save();
        
        render(test);
    }

}