package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Restaurateur extends Model
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
	
}