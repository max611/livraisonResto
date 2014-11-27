package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;

@Entity
public class LignePanier extends play.modules.morphia.Model
{

	public String plats;

	public int quantite;

	/*public int somme(){
		if(menuName!=null){
			Plats plat = Plats.find("menu", menuName.name).first();
			if (plat != null)	return (this.quantite * plat.prix);
			else				return 0;
		}
		return 0;
	}

	public LignePanier(){
		this.menuName = new Menu();
		this.quantite = 1;
	}

	public LignePanier(Menu menuName, int quantite){
		this.menuName = menuName;
		this.quantite = quantite;
	}*/
	public LignePanier(String plats, int quantite){
		this.plats=plats;
		this.quantite=quantite;
	}
	// contructeur par defaut
	public LignePanier(){
		
	}
}