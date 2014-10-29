package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Plats extends Model
{

public String name;

public String description;

public String prix;

public String menu;


}