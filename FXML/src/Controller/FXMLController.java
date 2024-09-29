/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Medicament;
import Classes.Patient;
import Classes.Pharmacien;
import Classes.patMed;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FXMLController implements Initializable {
    
     @FXML
    private Label Ecode;

    @FXML
    private Label EemailL;

    @FXML
    private Label EemailS;

    @FXML
    private Label EmdpL;

    @FXML
    private Label EmdpS;

    @FXML
    private Label EnomMed;

    @FXML
    private Label EnomPat;

    @FXML
    private Label Eprix;

    @FXML
    private Label Eqte;

    @FXML
    private Label Etel;
    
    @FXML
    private Button ajouter;

    @FXML
    private Button ajouter1;

    @FXML
    private Button ajouterMed;

    @FXML
    private Button archiver;

    @FXML
    private Button archiver1;

    @FXML
    private TextField code;
    
    @FXML
    private TextField prix;

    @FXML
    private TableColumn<Patient,String> colCode;

    @FXML
    private TableColumn<Patient,String> colNom;

    @FXML
    private TableColumn<Medicament, String> colNomMed;

    @FXML
    private TableColumn<Medicament,Float> colPrix;

    @FXML
    private TableColumn<Medicament,Integer> colQte;
    
    @FXML
    private TableColumn<Medicament,Integer> colType1;

    @FXML
    private TableColumn<Patient, Integer> colTel;

    @FXML
    private TableColumn<Patient, String> colType;

    @FXML
    private AnchorPane crud;

    @FXML
    private TableColumn<patMed, Date> dateAchat;

    

    @FXML
    private TextField emailL;
    
    @FXML
    private TextField qte;

    @FXML
    private TextField emailS;

    @FXML
    private AnchorPane login;

    @FXML
    private PasswordField mdpL;

    @FXML
    private PasswordField mdpS;

    @FXML
    private TableColumn<Medicament, String> medNom;

    @FXML
    private AnchorPane medicament;

    @FXML
    private Button modifier;

    @FXML
    private Button modifier1;

    @FXML
    private TextField nom;

    @FXML
    private TextField nomMed;

    @FXML
    private TableColumn<Medicament, String> nomMed1;

    @FXML
    private TableColumn<patMed, String> patCode;

    @FXML
    private AnchorPane patient;

    @FXML
    private TableColumn<Medicament, Float> colPrix1;

    @FXML
    private TableColumn<Medicament, Integer> colQte1;

    @FXML
    private AnchorPane signup;

    @FXML
    private Button submitL;

    @FXML
    private Button submitS;

    @FXML  
    AnchorPane patMed;
    
    @FXML
    private Button supprimerMed;

    @FXML
    private TableView<Medicament> tabM;

    @FXML
    private TableView<Medicament> tabMed;

    @FXML
    private TableView<Patient> tabP;

    @FXML
    private TableView<patMed> tabPatMed;

    @FXML
    private TextField tel;

    @FXML
    private Button toAchat;

    @FXML
    private Button toLogin;

    @FXML
    private Button toMed;

    @FXML
    private Button toPatient;

    @FXML
    private Button toSignUp;

    @FXML
    private CheckBox type;
    
    private TableView<Patient> tabv;
    
    ObservableList <Patient> observableList;
    
    ObservableList <Medicament> observableListM;
    ObservableList <Medicament> observableListM1;
    
    ObservableList <patMed> observableListPatMed;
    
    private String cp;
    private String nm;
    private Date da;
    
    
    static Connection cd= DAO.LaConnexion.seConnecter();
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void toLogin(ActionEvent event){
        signup.setVisible(false);
        login.setVisible(true);
    }
    
    
    
    @FXML
    public void toSignUp(ActionEvent event){
        signup.setVisible(true);
        login.setVisible(false);
    }
    
    @FXML 
    public void register(ActionEvent event){
        try{
        String e = emailS.getText();
        String mdp=mdpS.getText();
        String m=DAO.DAOPharmacien.hashPassword(mdp);
        if(!e.contains("@") || e.length()<7 || !e.contains(".")){
            EemailS.setText("email invalid");
        }
        else EemailS.setText("");
        if(mdp.length()<=5){
            EmdpS.setText("mot de pass invalid");
        }else EmdpS.setText("");
        
        if(!(!e.contains("@") || e.length()<7 || !e.contains(".") || mdp.length()<=5)){
        DAO.DAOPharmacien.SignUp(new Pharmacien(e, m));
        emailS.clear();
        mdpS.clear();}
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void login(ActionEvent event){
        try{
            String e = emailL.getText();
            String mdp=mdpL.getText();
            String m=DAO.DAOPharmacien.hashPassword(mdp);
            if(!e.contains("@") || e.length()<7 || !e.contains(".")){
            EemailL.setText("email invalid");}
            else
                    EemailL.setText("");
                    
        
        if(mdp.length()<=5){
            EmdpL.setText("mot de pass invalid");
        }
        else{
            EmdpL.setText("");
        }
        if(!(!e.contains("@") || e.length()<7 || !e.contains(".") || mdp.length()<=5)){
        if(DAO.DAOPharmacien.Login(new Pharmacien(e, m))){
            
            
        emailL.clear();
        mdpL.clear();
        toMedicament();
        crud.setVisible(true);
        login.setVisible(false);}
        }}
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void Modifier(ActionEvent event){
        String c = code.getText();
        
        String n=nom.getText();
        String txt=tel.getText();
        
        
        try{
            Integer.parseInt(txt);
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modifier");
            alert.setHeaderText("Erraur");
            alert.setContentText("Le tel doit etre un  numero");
            alert.show();
            return ;
        }
        if(txt.length() != 8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modifier");
            alert.setHeaderText("Erraur");
            alert.setContentText("Le tel doit etre 8 chiifres");
            alert.show();
            return ;
        }
        
        
        int t=Integer.parseInt(txt);
        if(c.length()==0)
            Ecode.setText("code invalid");
        else{
            Ecode.setText("");
        }
        if(n.length()<3) 
            EnomPat.setText("nom invalid");
        else EnomPat.setText("");
        if(!String.valueOf(t).matches("\\d+")){
            Etel.setText("num de telephone invalid");
        }else{
            Etel.setText("");
        }
        if(!(!String.valueOf(t).matches("\\d+") || n.length()<3 ||c.length()==0)){
        Classes.Patient Patient= new Patient(c,n,t);
        DAO.DAOPatient.changer(c,Patient);
        lister();
        remiseAzéro();}
    }
    
    @FXML
    public void Modifier1(ActionEvent event){
        String n= nomMed.getText();
        Float p=Float.parseFloat(prix.getText());
        Integer q = Integer.parseInt(qte.getText());
        Medicament.TYPE t;
        if(type.isSelected())t=Medicament.TYPE.special;
        else t=Medicament.TYPE.normal;
        if(n.length()<3) 
            EnomMed.setText("nom invalid");
        else EnomMed.setText("");
        if(p<0){
            Eprix.setText("prix invalid");
        }
        else Eprix.setText("");
        if(q<0)
            Eqte.setText("quantité invalid");
        
        else Eqte.setText("");
        
        if(!(n.length()<3 || p<0 || q<0)){
        Classes.Medicament med= new Medicament(n,p,q,t);
        DAO.DAOMedicament.changer(n,med);
        lister1();
        remiseAzéro1();
        }
    }
    
    @FXML
    public void toPatient(){
        System.out.println("bohmid");
        cp=code.getText();
        if(DAO.DAOPatient.chercher(cp)!=null){
        cp=code.getText();
        patient.setVisible(false);
        patMed.setVisible(true);
        medicament.setVisible(false);
        crud.setVisible(false);
        lister2();
        lister3();
        }
        else
            System.out.println("cant find");
    }
    
    
    @FXML
    public void toMedicament(){
        patient.setVisible(false);
        medicament.setVisible(true);
        
    }
    @FXML
    public void toPatients(){
        medicament.setVisible(false);
        crud.setVisible(true);
        patient.setVisible(true);
        patMed.setVisible(false);
    }
    
    
    
    @FXML
    public void Ajouter(ActionEvent event) {
        String c = code.getText();
        String n=nom.getText();
        
        String txt=tel.getText();
        
        try{
            Integer.parseInt(txt);
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout");
            alert.setHeaderText("Erraur");
            alert.setContentText("Le tel doit etre un  numero");
            alert.show();
            return ;
        }
        if(txt.length() != 8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout");
            alert.setHeaderText("Erraur");
            alert.setContentText("Le tel doit etre 8 chiifres");
            alert.show();
            return ;
        }
        
        if(c.length()==0)
            Ecode.setText("code invalid");
        else
            Ecode.setText("");
        
        if(n.length()<3) 
            EnomPat.setText("nom invalid");
        else EnomPat.setText("");
        if(!txt.matches("\\d+") || txt.length()!=8){
            Etel.setText("num de telephone invalid");
        }else{
            int t=Integer.parseInt(txt);
            Etel.setText("");
        }
        if(!(!txt.matches("\\d+") || n.length()<3 ||c.length()==0)){
            int t=Integer.parseInt(txt);
        Classes.Patient Patient= new Patient(c,n,t);
        DAO.DAOPatient.ajouter(Patient);
        lister();
        remiseAzéro();}
    }
    
    @FXML
    private void ajouterMed(ActionEvent event){
        
        DAO.DAOPatMed.ajouter(new patMed(cp, nm, Date.valueOf(LocalDate.now())));
        lister3();
        lister2();
    }
    
    
    @FXML
    public void Ajouter1(ActionEvent event) {
        
        String n=nomMed.getText();
        if(prix.getText().matches("\\d*\\.?\\d+") || prix.getText().length()>0){
        Float p= Float.parseFloat(prix.getText());
         Eprix.setText("");
        }else 
            Eprix.setText("prix invalid");
        
        if(qte.getText().matches("\\d+") && qte.getText().length()>0){
        int q=Integer.parseInt(qte.getText());
        Eqte.setText("");}
        else 
            Eqte.setText("quantité invalid");
        
        
        
        
        Medicament.TYPE t;
        if(type.isSelected())
            t=Medicament.TYPE.special;
        else t=Medicament.TYPE.normal;
        
        if(type.isSelected())t=Medicament.TYPE.special;
        else t=Medicament.TYPE.normal;
        if(n.length()<3) 
            EnomMed.setText("nom medicament invalid");
        else EnomMed.setText("");
        
            
        
       
        
        if(!(n.length()<3 )){
            int q=Integer.parseInt(qte.getText());
            Float p= Float.parseFloat(prix.getText());
            Classes.Medicament medicament= new Medicament(n,p,q,t);
        DAO.DAOMedicament.ajouter(medicament);
        lister1();
        remiseAzéro1();
            
        }
        
    }
    
    
    
    @FXML
    private void supprimerMed(ActionEvent event) {
        try{
        DAO.DAOPatMed.supprimer(cp, nm);
        lister2();
        lister3();
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void Archiver(ActionEvent event) {
        try{
        
        DAO.DAOPatient.supprimer(code.getText());
        lister();
        remiseAzéro();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void Archiver1(ActionEvent event) {
        try{
        
        DAO.DAOMedicament.supprimer(nomMed.getText());
        lister1();
        remiseAzéro1();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void remiseAzéro1() {
        nomMed.clear();
        nomMed.requestFocus();
        qte.clear();
        type.setSelected(false);
        prix.clear();

    }
    
    private void remiseAzéro() {
        code.clear();
        code.requestFocus();
        
        tel.clear();
        nom.clear();

    }
    
    public void lister() {
       // Connection cd = cn.seConnecter();
         tabP.getItems().clear();
        try {
            ResultSet rs = cd.createStatement().executeQuery("select * from Patient");
            while (rs.next()) {
                observableList.add(new Patient(rs.getString(1), rs.getString(2), rs.getInt(3)));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       
        tabP.setItems(observableList);
      
    }
    
    public void lister1() {
       // Connection cd = cn.seConnecter();
         tabM.getItems().clear();
        try {
            ResultSet rs = cd.createStatement().executeQuery("select * from Medicament");
            while (rs.next()) {
                Medicament.TYPE t;
                if(rs.getString(4).compareTo("normal")==0) t=Medicament.TYPE.normal;
                else t=Medicament.TYPE.special;
                observableListM.add(new Medicament(rs.getString(1), rs.getFloat(2), rs.getInt(3),t ));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       
        tabM.setItems(observableListM);
      
    }
    
    public void lister2() {
       // Connection cd = cn.seConnecter();
         tabMed.getItems().clear();
         
        try {
            ResultSet rs = cd.createStatement().executeQuery("select * from Medicament");
            while (rs.next()) {
                Medicament.TYPE t;
                if(rs.getString(4).compareTo("normal")==0) t=Medicament.TYPE.normal;
                else t=Medicament.TYPE.special;
                observableListM1.add(new Medicament(rs.getString(1), rs.getFloat(2), rs.getInt(3),t ));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tabMed.setItems(observableListM1);
    }
    
    public void lister3() {
       // Connection cd = cn.seConnecter();
         tabPatMed.getItems().clear();
        try {
            ResultSet rs = cd.createStatement().executeQuery("select * from patMed where codePat='"+cp+"'");
            while (rs.next()) {
                
                
                observableListPatMed.add(new patMed(rs.getString(1), rs.getString(2), rs.getDate(3)));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       
        tabPatMed.setItems(observableListPatMed);
      
    }
    
    @FXML
    private void Consulter() {
         String c=this.tabP.getSelectionModel().getSelectedItem().getCode();
        String n=this.tabP.getSelectionModel().getSelectedItem().getNom();
        int t=this.tabP.getSelectionModel().getSelectedItem().getTel();
        
        this.code.setText(c);
        this.nom.setText(n);
        this.tel.setText(String.valueOf(t));
    }
    
    @FXML
    private void Consulter1() {
        
        Float p=this.tabM.getSelectionModel().getSelectedItem().getPrix();
        String n=this.tabM.getSelectionModel().getSelectedItem().getNom();
        int q=this.tabM.getSelectionModel().getSelectedItem().getQte();
        String t=this.tabM.getSelectionModel().getSelectedItem().getType();
        
        this.nomMed.setText(n);
        this.prix.setText(String.valueOf(p));
        this.qte.setText(String.valueOf(q));
        if(t=="normal")
        this.type.setSelected(false);
        else this.type.setSelected(true);
        
      
       
    }
    
    @FXML
    private void Consulter2() {
        
        
        
        nm=this.tabPatMed.getSelectionModel().getSelectedItem().getNomMed();
        da=this.tabPatMed.getSelectionModel().getSelectedItem().getDateAchat();
       
    }
    @FXML
    private void Consulter3() {
         nm=this.tabMed.getSelectionModel().getSelectedItem().getNom();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Charger l'image
    Image image = new Image("file:C:/Users/Near/Documents/NetBeansProjects/TP6FXML/src/pharmmacie.jpg");

    // Créer un fond d'écran avec l'image chargée
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background = new Background(backgroundImage);
    Image image1 = new Image("file:C:/Users/Near/Documents/NetBeansProjects/TP6FXML/src/phammacie2.jpg");

    // Créer un fond d'écran avec l'image chargée
    BackgroundSize backgroundSize1 = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage backgroundImage1 = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background1 = new Background(backgroundImage1);

    // Appliquer le fond d'écran à votre conteneur principal (par exemple, AnchorPane)
    login.setBackground(background);
crud.setBackground(background1);
signup.setBackground(background1);
patMed.setBackground(background1);
        
        
        
        observableList = FXCollections.observableArrayList();
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        lister();
        observableListM = FXCollections.observableArrayList();
        colNomMed.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        lister1();
        
        observableListPatMed = FXCollections.observableArrayList();
        patCode.setCellValueFactory(new PropertyValueFactory<>("codePat"));
        medNom.setCellValueFactory(new PropertyValueFactory<>("nomMed"));
        dateAchat.setCellValueFactory(new PropertyValueFactory<>("dateAchat"));
        
        
        lister3();
        
        observableListM1 = FXCollections.observableArrayList();
        nomMed1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQte1.setCellValueFactory(new PropertyValueFactory<>("qte"));
        colType1.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        lister2();
        
    }    

    
    
}
