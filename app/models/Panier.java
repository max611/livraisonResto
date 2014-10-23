package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Panier extends Model
{

public String name;

public String prix;


}