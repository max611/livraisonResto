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

}