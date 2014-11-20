package models;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class Adresse extends play.modules.morphia.Model{

	public String adresse ="";

	public String user;

	public Adresse(){}

	public Adresse(String adresse, String user) {
        this.adresse = adresse;
        this.user = user;
    }
}