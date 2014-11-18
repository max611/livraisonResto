package models;
import java.util.List;
import java.util.ArrayList;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Panier extends Model
{

	@ElementCollection
	public List<LignePanier> lignes;
/*
	public int total(){
		int tot = 0;
		for(int i=0; i<lignes.size(); i++) {
			tot += lignes.get(i).somme();
		}
		return tot;
	}

	//constructeur par defaut
	public Panier(){
		lignes = new ArrayList<LignePanier>();
	}*/

}