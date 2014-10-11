package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class User extends Model

{

	//@Required
	public String firstName;

	//@Required
	public String lastName;

	//@Required
	public String username;

	//@Required
	public String password;

	//@Required
	public String email;

	//@Required
	public String phonenumber;

	public String type;
	
	//constructeur par defaut
	public User(){
		
	}

	public User(String firstName, String lastName, String username, String password, String email, String
		phonenumber, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.type = type;
    }

	/*@Blaise 
	Ajoiut d'un constructeur pour la classe User, utile pour la phase de Test
	*/
	public User(String firstName1, String password1, String email1){
		firstName = firstName1;
		password=password1;
		email=email1;
	}
	public static User connect(String firstName, String password){
	
	return find("byfirstNameAndPassword",firstName,password).first();
	}
}