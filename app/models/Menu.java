package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Menu extends Model
{

public String name;

public String plat;

public String restoName;

}