package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class Menu extends play.modules.morphia.Model
{

public String name;

public String plat;

public String restoName;

public Menu(){
	
}

}