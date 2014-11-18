package models;
import java.util.Date;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;



@Entity
public class Commande extends play.modules.morphia.Model
{

	public static int increment = 0;

	public int numConfirmation;

	public String dateLivraison;

	public String heureLivraison;

	public String adressLivraison;

	public String statut;

	public Commande(){
		this.numConfirmation = this.increment++;
	}

	public Commande(String dateLivraison, String heureLivraison, String adressLivraison){
		this.dateLivraison = dateLivraison;
		this.heureLivraison = heureLivraison;
		this.adressLivraison = adressLivraison;
		this.numConfirmation = this.increment++;
	}

}