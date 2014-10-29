package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class Restaurateur extends play.modules.morphia.Model
{

	//@Required
	public String firstName;

	//@Required
	public String lastName;

	//@Required
	public String username;

	//@Required
	public String email;

	//@Required
	public String phonenumber;
	
	public String restaurant;

	public Restaurateur(){
		
	}

	public Restaurateur(String firstName, String lastName, String username, String email, String phonenumber, String restaurant) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.restaurant = restaurant;
    }
}