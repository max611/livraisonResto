package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import org.apache.commons.mail.*;
import play.Logger;
import models.*;
import com.google.code.morphia.annotations.Entity;
import play.libs.Mail;
import javax.mail.Folder;

/*import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;*/

public class Application extends Controller {

    static List<Plats> panier = new ArrayList<Plats>();


    public static void index() {
        User user = User.find("username", session.get("username") ).first();
        Boolean login = user != null && user.username != null;
        
        render(login,user);
    }

    public static void login(String username, String password) {
        User user = User.find("username", username).first();
        Boolean login = user != null && user.password.equals(password);

        //Scope.Session("username",user.username);
        
        session.put("username", username);


        render(login,user);
 
    }

    public static void create(String username, String password, String email, String firstname,
     String lastname, String phonenumber, String adresse) {

        User newuser = new User();
        newuser.username = username;
        newuser.firstName = firstname;
        newuser.lastName = lastname;
        newuser.email = email;
        newuser.password = password;
        newuser.phonenumber = phonenumber;
        newuser.adresse = adresse;
        newuser.type = "Client";
        
        newuser.save();
        User user = User.find("username", username).first();

        render(user);
    }

    public static void update(String username, String password, String firstname, String lastname, String phonenumber, String adresse) {
        Logger.info("username dans la session = " + session.get("username"));
        User test = User.find("username", session.get("username") ).first();
        
        test.firstName = firstname;
        test.lastName = lastname;
        test.password = password;
        test.phonenumber = phonenumber;
        test.adresse = adresse;
        
        test.save();
        
        render(test);
    }


    public static void formulaire() {
        List listeCompte = new ArrayList();
        listeCompte.add("Client");
        listeCompte.add("Restaurateur");
        listeCompte.add("Livreur");

        String language = play.i18n.Lang.get();
        Logger.info("language : " + language);
        if (language.equals("en")){
            play.i18n.Lang.change("fr");
        }
        else{
            play.i18n.Lang.change("en");
        }
        render(listeCompte);
    }

    public static void logout() {
        session.clear();
        Logger.info("Session apres logout: "+ session);
        index();
    }

    public static void account(String username, String password, String firstname, String lastname, String phonenumber) {
        Logger.info("username dans la session = " + session.get("username"));
        User test = User.find("username", session.get("username") ).first();

        render(test);
    }

    public static void manageMenus(){
        render();
    }

    public static void manageRestaurant(){
        
        User user = User.find("username",session.get("username")).first();
        render(user);
    }

    public static void formulaireResto() {
        List<User> listeResto = User.find("type", "Restaurateur").asList();
        render(listeResto);
    }

    public static void updateResto(String restoName) {

        Restaurant resto = Restaurant.find("name", restoName ).first();
        session.put("restaurant", restoName);
        List<User> listeResto = User.find("type", "Restaurateur").asList();
        
        render(resto,listeResto);
    }

    public static void manageResto(String name, String admin, String description) {
        Boolean login = name != null;
        
        if(login){

        Restaurant resto = new Restaurant();
        resto.name = name;
        resto.admin = admin;
        resto.description = description;


        resto.save();

        render(resto,login);
            
        }
        else{
            User user = User.find("username", session.get("username") ).first();
            //List<Restaurant> listeResto = Restaurant.find("admin", session.get("username")).fetch();
            List<Restaurant> listeResto = Restaurant.findAll();
            List<Restaurant> listeRestoRestaurateur = Restaurant.find("admin", user.username).asList();
            render(login,listeResto, listeRestoRestaurateur, user);
        
        }
    }

    public static void deleteResto() {

        List<Restaurant> listeResto = Restaurant.findAll();
        render(listeResto);

    }

    public static void confirmationRestoDel(String restoName) {

        Restaurant resto = Restaurant.find("name", restoName ).first();

        resto.delete();
        render(restoName);
    }

    public static void updateRestaurant(String name, String restaurateur, String description) {

        Restaurant test = Restaurant.find("name", name ).first();
            
        test.name = name;
        test.admin = restaurateur;
        test.description = description;
        session.put("restaurant", name);
          
        test.save();
            
        render(test);
    
    }

    public static void confirmationModificationResto(String name, String admin, String description, String adresse) {
    
        Restaurant resto = Restaurant.find("name", session.get("restaurant") ).first();

        
        resto.name = name;
        resto.admin = admin;
        resto.description = description;
        resto.adresse = adresse;
        
        resto.save();
        
        render(resto);
    
    }

    public static void confirmationCreationResto(String name, String restoName, String description, String adresse) {

        Restaurant resto = new Restaurant();
        resto.name = name;
        resto.admin = restoName;
        resto.description = description;
        resto.adresse = adresse;
        resto.save();

        render(resto);
    
    }

    public static void manageRestaurateur() {

        render();
    
    }

    public static void nouveauRestaurateur() {

        List<Restaurant> listeResto = Restaurant.findAll();
        render(listeResto);
    
    }

    public static void modificationRestaurateur() {

        List<User> listeResto = User.find("type", "Restaurateur").asList();
        render(listeResto);
    
    }

    public static void supprimerRestaurateur() {

        List<User> listeResto = User.find("type", "Restaurateur").asList();
        render(listeResto);
    
    }

    public static void createRestaurateur(String username, String password, String email, String firstname,
        String lastname, String phonenumber, String restaurant) {
        User test = new User();
        test.username = username;
        test.firstName = firstname;
        test.lastName = lastname;
        test.email = email;
        test.phonenumber = phonenumber;
        test.type = "Restaurateur";
        test.restaurant = restaurant;
        
        test.save();

        User restaurateur = test;
        render(restaurateur,restaurant);
    }

    public static void deleteRestaurateur(String restoName) {

        User resto = User.find("username, type",restoName,"Restaurateur").first();

        resto.delete();
        render(restoName);
    }

    public static void updateRestaurateur(String restoName) {

        User resto = User.find("username, type",restoName,"Restaurateur").first();

        Boolean restaurant = true;

        List<Restaurant> listeResto = Restaurant.findAll();

        render(resto,restaurant,listeResto);
    }

    public static void confirmationModificationRestaurateur(String username, String firstName, String lastName, String restaurant) {

        User resto = User.find("username, type",username,"Restaurateur").first();
        resto.firstName = firstName;
        resto.lastName = lastName;
        resto.restaurant = restaurant;
        resto.save();
        
        render(resto);   
    }

    public static void changeLanguage(String language){
        if (language.equals("en")){
            play.i18n.Lang.change("en");
        }
        else{
            play.i18n.Lang.change("fr");
        }
    }

    public static void createMenu(String name, String resto) {
        Menu m = new Menu();
        m.name = name;
        m.restoName = resto;
        
        m.save();
        Menu menu = Menu.find("name", name).first();
        session.put("resto", name);
        render(menu);
    }

    public static void FormulaireMenu() {
        List<Restaurant> listeResto = Restaurant.findAll();
        render(listeResto);
    }

    public static void createPlat(String name, String description, String menu, int prix) {
        Plats p = new Plats();
        Boolean infodesc = true;
        p.name = name;
        p.description = description;
        p.prix = prix;
        p.menu = menu;

        if(description != null || !description.isEmpty()) infodesc = true;
        else    infodesc = false;

        p.save();
        Plats plat = Plats.find("name", name).first();

        render(plat, infodesc);
    }

    public static void FormulairePlat() {
        List<Menu> listeMenuRev = Menu.findAll();
        List<Menu> listeMenu = new ArrayList<Menu>();
        int total = listeMenuRev.size()-1;

        for(int i = 0 ; i <= total; i++){
            listeMenu.add(listeMenuRev.get(total-i));
            Logger.info("item : "+ listeMenuRev.get(i));            
        }

        render(listeMenu);
    }
    
    public static void passerCommande() {
           
        List<Restaurant> listeResto = Restaurant.findAll();
        render(listeResto);
    }


    public static void passerCommandeMenu(String restoName) {
        Restaurant r = Restaurant.find("name", restoName).first();
        List<Menu> listeMenus = Menu.find("restoName", r.name).asList();

        render(listeMenus,r);
    }

    public static void passerCommandePlat(String menuName) {
        //Logger.info("Menu = " + menuName);
        List<Plats> listePlat = Plats.find("menu", menuName ).asList();
        //Logger.info("plat = " + listePlat.get(0));
        render(listePlat);
    }
    
    public static void saveAndAddCommandeMenu( String platName, int quantite) {

        List<LignePanier> listePanier = LignePanier.findAll();
        Plats p = Plats.find("name", platName).first();
        String menuName = p.menu;
        Menu m = Menu.find("name", p.menu).first();
        Restaurant r = Restaurant.find("name", m.restoName).first();
        Logger.info("resto = " + r.name);
        session.put("resto", r.name);

        List<Plats> listePlat = Plats.find("menu", menuName).asList();

        User user = User.find("username", session.get("username") ).first();

        LignePanier newLigne = new LignePanier();
        newLigne.plats = platName;
        newLigne.quantite = quantite;
        //newLigne.save();

        newLigne.save();

        render(listePlat,r,listePanier);
    }

    public static void sommaireCommande(Restaurant restoName) {

        List<LignePanier> listePanier = LignePanier.findAll();

        int somme = 0;
        int i = 0;
        Plats p = null;
        Logger.info("size = " +listePanier.size());

        while( i < listePanier.size() ) {
        p = Plats.find("name", listePanier.get(i).plats ).first();
        somme += p.prix * listePanier.get(i).quantite ;
        Logger.info("Plats prix = " + p.prix);
        i++;
      }

        Restaurant r = Restaurant.find("name", session.get("resto")).first();
        User user = User.find("username", session.get("username") ).first();

        render(r, user, listePanier, somme);
    }

    public static void numReservation(String hour, String date, String adresse, String adresseRestaurant) {
        Random r = new Random();
        int numConfirmation = r.nextInt(100-1) + 1;
        User user = User.find("username", session.get("username") ).first();
        user.adresse = adresse;
        Commande comm = new Commande();
        comm.numConfirmation = numConfirmation;
        comm.dateLivraison = date;
        comm.heureLivraison = hour;
        comm.adressLivraison = adresse;
        comm.statut = "En préparation";
        comm.user = user.username;
        comm.adresseRestaurant = adresseRestaurant;
        comm.save();
        user.save();

        int i = 0;
        List<LignePanier> listePanier = LignePanier.findAll();

        //Vide le panier
        while( i < listePanier.size() ) {
            listePanier.get(i).delete() ;
            i++;
        }

        User m = User.find("username", session.get("username") ).first();

        SimpleEmail email = new SimpleEmail();
        try{
            email.setFrom("log210@ets.com");
            email.addTo(m.email);
            email.setSubject("Confirmation");
            email.setMsg("Voici votre confirmation pour votre commande passé sur LivraisonPizza");
            Mail.send(email); 
        } catch (EmailException e) {
            e.printStackTrace();
        }

        render(comm);
    }


    public static void payerCommande() {
        int total = 0;
        
        List<Panier> listePanier = Panier.findAll();
        Panier test = listePanier.get(0);


        /*for (int i=0; i<listePanier.size(); i++){
            Plats p = Plats.find("name", listePanier.get(i).name ).first();
            //total += listePanier.get(i).prix();
            total += Integer.parseInt(p.prix);
        }*/

        render(total);


    }

    public static void supprimerMenu(){
        List<Menu> listeMenu = Menu.findAll();
        render(listeMenu);
    }

    public static void confirmationMenuDel(String menuName) {
        Menu menu = Menu.find("name", menuName ).first();
        menu.delete();
        render(menuName);
    }

    public static void modifierMenu(){
        List<Menu> listeMenu = Menu.findAll();
        render(listeMenu);
    }

    public static void updateMenu(String menuName){
        Menu menu = Menu.find("name", menuName ).first();
        session.put("menu", menuName);
        List<Menu> listeMenu = Menu.findAll();
        List<Restaurant> listeResto = Restaurant.findAll();
        
        render(menu, listeResto);
    }

    public static void confirmationModificationMenu(String name, String restoName) {
        Menu menu = Menu.find("name", session.get("menu") ).first();

        menu.name = name;
        menu.restoName = restoName;
        menu.save();
        
        render(menu);
    }

    public static void admin(){
        List<Restaurant> listeRestau = Restaurant.findAll();
        List<User> listeUser = User.findAll();
        List<Menu> listeMenu = Menu.findAll();
        List<Plats> listePlat = Plats.findAll();
        List<LignePanier> listeLignePanier = LignePanier.findAll();
        List<Panier> listePanier = Panier.findAll();
        List<Commande> listeCommande = Commande.findAll();
        render(listeUser, listeRestau, listeMenu, listePlat, listeLignePanier, listePanier, listeCommande);
    }

    public static void deleteLignePanier(){
        List<LignePanier> listeLignePanier = LignePanier.findAll();
        LignePanier lp = listeLignePanier.get(0);
        lp.delete();
        render();
    }

    public static void addAdressLivreur(){
        render();
    }

    public static void gestionCommande(String adresse){
        User user = User.find("username", session.get("username") ).first();
        user.adresse = adresse;
        user.save();

        List<Commande> listeCommandeprete = Commande.find("statut", "Prête").asList();
        List<Commande> listeCommandepreparation = Commande.find("statut", "En préparation").asList();
        render(user, listeCommandeprete, listeCommandepreparation);
    }

    public static void updateCommande(String commandeNum){
        User user = User.find("username", session.get("username")).first();
        int numConfirmation = Integer.parseInt(commandeNum);
        Logger.info("numConfirmation = " + commandeNum );
        Commande c = Commande.find("numConfirmation", numConfirmation).first();
        Logger.info("numConfirmation = " + c.numConfirmation );
        User client = User.find("username", c.user).first();

       
        render(c, user);
    }

    public static void confirmationCommande(String numConfirm, String statut){
        User user = User.find("username", session.get("username")).first();
        int numConfirmation = Integer.parseInt(numConfirm);
        Logger.info("numConfirmation = " + numConfirm );
        Commande c = Commande.find("numConfirmation", numConfirmation).first();
        c.statut = statut;
        c.save();

        render(user, c);
    }

    public static void map(){
        render();
    }

    public static void creationLivreur(){
        List listeCompte = new ArrayList();
        listeCompte.add("Client");
        listeCompte.add("Restaurateur");
        listeCompte.add("Livreur");
        listeCompte.add("Entrepreneur");

        render(listeCompte);
    }
    
}

