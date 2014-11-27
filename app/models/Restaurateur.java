package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class Restaurateur extends play.modules.morphia.Model
{

	public String firstName;

	public String lastName;

	public String username;

	public String email;

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

	public static Restaurateur connect(String firstName, String restaurant) {

		return find("byfirstNameAndRestaurant", firstName, restaurant).first();
	}
}