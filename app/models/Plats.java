package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Plats extends Model
{

	public String name;

	public String description;

	public int prix;

	public String menu;

	public Plats(){

	}

	public Plats(String name, String description, int prix, String menu){
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.menu = menu;
	}

}