package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Restaurant extends Model
{

	@Required
	public String name;

	@Required
	public String admin;

	@Required
	public String description;

	@Required
	public String adresse;

}