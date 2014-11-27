package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class Plats extends play.modules.morphia.Model
{

	public String name;

	public String description;

	public int prix;

	public String menu;

	public Plats(){

	}
	//constructeur 
	public Plats(String name1, String description1, int prix1, String menu1) {
        this.name = name1;
        this.description = description1;
        this.prix = prix1;  
        this.menu = menu1; 
	}

}