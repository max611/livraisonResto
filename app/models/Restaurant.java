package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;



@Entity
public class Restaurant extends play.modules.morphia.Model
{

	public String name;

	public String admin;

	public String description;

	public Restaurant(){
		
	}

	public Restaurant(String name, String admin, String description) {
        this.name = name;
        this.admin = admin;
        this.description = description;
    }

}
