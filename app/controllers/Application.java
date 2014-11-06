package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import play.Logger;
import models.*;
import com.google.code.morphia.annotations.Entity;

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
        Logger.info("User: "+ user.username);
        Logger.info("password: "+ user.password);
        Boolean login = user != null && user.password.equals(password);
        Logger.info("Login: "+login);
        //Scope.Session("username",user.username);
        
        session.put("username", username);
        Logger.info("test session = " + session.get("username"));


        render(login,user);
 
    }

    public static void create(String username, String password, String email, String firstname,
     String lastname, String phonenumber, String accountType) {

        User newuser = new User();
        newuser.username = username;
        newuser.firstName = firstname;
        newuser.lastName = lastname;
        newuser.email = email;
        newuser.password = password;
        newuser.phonenumber = phonenumber;
        
        newuser.save();
        User user = User.find("username", username).first();

        render(user);
    }

    public static void update(String username, String password, String firstname, String lastname, String phonenumber) {
        Logger.info("username dans la session = " + session.get("username"));
        User test = User.find("username", session.get("username") ).first();
        
        test.firstName = firstname;
        test.lastName = lastname;
        test.password = password;
        test.phonenumber = phonenumber;
        
        test.save();
        
        render(test);
    }


    public static void formulaire() {

        String language = play.i18n.Lang.get();
        Logger.info("language : " + language);
        if (language.equals("en")){
            play.i18n.Lang.change("fr");
        }
        else{
            play.i18n.Lang.change("en");
        }
        render();
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
        render();
    }

    public static void formulaireResto() {
        List<Restaurateur> listeResto = Restaurateur.findAll();
        render(listeResto);
    }

    public static void updateResto(String restoName) {

        Restaurant resto = Restaurant.find("name", restoName ).first();
        session.put("restaurant", restoName);
        List<Restaurateur> listeResto = Restaurateur.findAll();
        
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
            
            //List<Restaurant> listeResto = Restaurant.find("admin", session.get("username")).fetch();
            List<Restaurant> listeResto = Restaurant.findAll();
            render(login,listeResto);
        
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

        List<Restaurateur> listeResto = Restaurateur.findAll();
        render(listeResto);
    
    }

    public static void supprimerRestaurateur() {

        List<Restaurateur> listeResto = Restaurateur.findAll();
        render(listeResto);
    
    }

    public static void createRestaurateur(String username, String password, String email, String firstname,
        String lastname, String phonenumber, String restaurant) {
        Restaurateur test = new Restaurateur();
        test.username = username;
        test.firstName = firstname;
        test.lastName = lastname;
        test.email = email;
        test.phonenumber = phonenumber;
        test.restaurant = restaurant;
        
        test.save();

        Restaurateur restaurateur = test;
        render(restaurateur,restaurant);
    }

    public static void deleteRestaurateur(String restoName) {

        Restaurateur resto = Restaurateur.find("username", restoName ).first();

        resto.delete();
        render(restoName);
    }

    public static void updateRestaurateur(String restoName) {

        Restaurateur resto = Restaurateur.find("username", restoName ).first();
        Boolean restaurant = true;

        List<Restaurant> listeResto = Restaurant.findAll();


        if(resto.restaurant.isEmpty() ){
        restaurant = false;
        }

        render(resto,restaurant,listeResto);
    }

    public static void confirmationModificationRestaurateur(String username, String firstName, String lastName, String restaurant) {

        Restaurateur resto = Restaurateur.find("username", username ).first();
        
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
        Menu m = Menu.find("name", menu).first();
        m.plat = name;
        m.save();
        Plats p = new Plats();
        p.name = name;
        p.description = description;
        p.prix = prix;
        p.menu = menu;

        p.save();
        Plats plat = Plats.find("name", name).first();

        render(plat);
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
    
    public static void saveAndAddCommandeMenu(String restoName, String menuName, int quantite) {
        Logger.info("infos param = " + restoName + "  "+menuName+"  "+quantite);
        Restaurant r = Restaurant.find("name", restoName).first();
        session.put("resto", r.name);
        List<Menu> listeMenus = Menu.find("restoName", restoName).asList();
        Logger.info("listeMenus = " + listeMenus.get(0).name);

        User user = User.find("username", session.get("username") ).first();

        LignePanier newLigne = new LignePanier();
        Menu menuSave = Menu.find("name", menuName).first();
        newLigne.menuName = menuSave;
        newLigne.quantite = quantite;
        //newLigne.save();

        user.monPanier.lignes.add(newLigne);
        user.save();

        Logger.info("monPanier = " +  user.monPanier.lignes.get(0).menuName.name);
        Logger.info("listeLignePanier = " +  user.monPanier.lignes);
        render(listeMenus,r);
    }

    public static void sommaireCommande(Restaurant restoName) {

        Restaurant r = Restaurant.find("name", session.get("resto")).first();
        User user = User.find("username", session.get("username") ).first();
        Logger.info("listeLignePanier = " +  user.monPanier.lignes.get(0).somme());

        render(r, user);
    }

    public static void numReservation(String hour, String date, String adresse) {
          
        Commande comm = new Commande();
        comm.dateLivraison = date;
        comm.heureLivraison = hour;
        comm.adressLivraison = adresse;
        comm.save();

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

    public static void confirmationModificationMenu(String name, String plat, String restoName) {
        Menu menu = Menu.find("name", session.get("menu") ).first();

        menu.name = name;
        menu.plat = plat;
        menu.restoName = restoName;
        menu.save();
        
        render(menu);
    }

    public static void admin(){
        List<Restaurant> listeRestau = Restaurant.findAll();
        List<Restaurateur> listeResto = Restaurateur.findAll();
        List<User> listeUser = User.findAll();
        List<Menu> listeMenu = Menu.findAll();
        List<Plats> listePlat = Plats.findAll();
        List<LignePanier> listeLignePanier = LignePanier.findAll();
        List<Panier> listePanier = Panier.findAll();
        render(listeUser, listeResto, listeRestau, listeMenu, listePlat, listeLignePanier, listePanier);
    }

    public static void deleteLignePanier(){
        List<LignePanier> listeLignePanier = LignePanier.findAll();
        LignePanier lp = listeLignePanier.get(0);
        lp.delete();
        render();
    }
    
}

