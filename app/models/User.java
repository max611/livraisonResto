package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class User extends Model

{

	@Required
	public String firstName;

	@Required
	public String lastName;

	@Required
	public String username;

	@Required
	public String password;

	@Required
	public String email;

	@Required
	public int phonenumber;

	public String type;
	
	//constructeur par defaut
	public User(){
		
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
