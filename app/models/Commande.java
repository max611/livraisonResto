package models;
import java.util.Date;

import play.db.jpa.*;
import javax.persistence.*;
import play.data.validation.*;

@Entity
public class Commande extends Model
{

public String numConfirmation;

public Date dateLivraison;

public String adressLivraison;

public Commande(){

}

}