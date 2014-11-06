package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class User extends play.modules.morphia.Model

{

	public String firstName;

	public String lastName;

	public String username;

	public String password;

	public String email;

	public String phonenumber;

	public String type;

	public Panier monPanier;
	
	//constructeur par defaut
	public User(){
		monPanier = new Panier();
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