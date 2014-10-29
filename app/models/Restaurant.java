package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;



@Entity
public class Restaurant extends play.modules.morphia.Model
{

	//@Required
	public String name;

	//@Required
	public String admin;

	//@Required
	public String description;

	//@Required
	public String adresse;

	public Restaurant(){
		
	}

	public Restaurant(String name, String admin, String description) {
        this.name = name;
        this.admin = admin;
        this.description = description;
    }

}
