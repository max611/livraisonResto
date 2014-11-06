package models;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class LignePanier extends Model
{

	public Menu menuName;

	public int quantite;

	public int somme(){
		if(menuName!=null){
			Plats plat = Plats.find("menu", menuName.name).first();
			if (plat != null)	return (this.quantite * plat.prix);
			else				return 0;
		}
		return 0;
	}

	public LignePanier(){
		this.menuName = new Menu();
	}

	public LignePanier(Menu menuName, int quantite){
		this.menuName = menuName;
		this.quantite = quantite;
	}

}