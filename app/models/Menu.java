package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class Menu extends play.modules.morphia.Model
{

public String name;

public String restoName;

public Menu(){
	
}
//contructeur
public Menu(String name, String restoName){
	this.name=name;
	this.restoName=restoName;	
}

}