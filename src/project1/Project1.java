
package project1;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;



public class Project1 extends Application
{
     private Label jobStatus = new Label();
    ToggleGroup tg1;
    private ListView list;
    Image image,image2;
   static String filepath,idNumber,fn,searchrs;
    Scene scene1,scene2,scene3,scene4,scene5,scene6;
  static Label lb8,lb10,lb12,lb14,lb16;
 static  ChoiceBox<String> cb2;
 static ChoiceBox<Integer> cb1;
    public static int id = 1000000;
    static int noofIdcards=0;
     static Button btn5,btn8,btn9,btn10,btn11;
      static ImageView imageView,imageView2,imageViewupdate2,imageView2update2;
   static   TextField txtf1,txtf2,txtf3,txtupdate;
     HBox hb5;
     VBox vb5;
     Printer defaultprinter;
     GridPane grid1,grid2,grid3;
      
          Alert a = new Alert(AlertType.NONE); 
          Alert b = new Alert(AlertType.INFORMATION);
          Alert c = new Alert(AlertType.NONE);
      //Declare Database Instance Variables(DB credentials)
       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
       final String db_url = "jdbc:mysql://localhost/project1";
       final String db_username = "root";
       final String db_password = "shortcuts100";
       
       //Set Connection variable
       Connection conn = null;
       //Set SQL statements.
       ResultSet rs = null;
       Statement stmt = null;
      
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        
         //Creating the components for scene 1
        Button btn1 = new Button("Create New ID Card");
        btn1.setPadding(new Insets(10,10,10,10));
        btn1.setMinWidth(200);
        btn1.setOnAction(
                e -> 
                {
                    primaryStage.setScene(scene2);
                    primaryStage.setTitle("New ID Card");
                }
               
          );
     
        Button btn2 = new Button("Modify Existing ID Card");
        btn2.setPadding(new Insets(10,10,10,10));
        btn2.setMinWidth(200);
        btn2.setOnAction(
                e ->
                {
                   primaryStage.setScene(scene4);
                   primaryStage.setTitle("Modify ID Card");
                }
        );
       

        Button btn3 = new Button("Delete Existing ID Card");  
        btn3.setPadding(new Insets(10,10,10,10));
        btn3.setMinWidth(200);
        btn3.setOnAction(
                e ->
                {
                    primaryStage.setScene(scene6);
                }
                );
       
        
        Button btn4 = new Button("Exit");
        btn4.setPadding(new Insets(10,10,10,10));
        btn4.setMinWidth(200);
        btn4.setOnAction(e -> primaryStage.close());
       
        
        Label lb0 = new Label("WELCOME...");
        lb0.setTextFill(Color.web("white"));
        lb0.setFont(Font.font("Verdana", FontPosture.ITALIC, 35));
       /* lb0.setStyle
        (
                
                        
        );*/
      
        
        GridPane grid1 = new GridPane();
        grid1.setVgap(30);
        grid1.setHgap(20);
        grid1.setPadding(new Insets(30,30,30,30));
          grid1.setStyle
        (
                "-fx-background-color: #009999;\n" +
               "-fx-border-color: black;"
        );
 
        
        //set constraints
        GridPane.setConstraints(lb0, 1, 0);
        GridPane.setConstraints(btn1, 1, 1);
        GridPane.setConstraints(btn2, 1, 2);
        GridPane.setConstraints(btn3, 1, 3);
        GridPane.setConstraints(btn4, 1, 4);
        grid1.getChildren().addAll(lb0,btn1, btn2, btn3, btn4); 
        grid1.setAlignment(Pos.CENTER);
        
        
        //Creating components for scene2         
          Label lb1 = new Label("Surname");
          TextField txtf1 = new TextField();
          txtf1.setStyle(".text-field");
          Separator sp1 = new Separator();
          sp1.setPadding(new Insets(10,10,10,10));
          VBox vb2 = new VBox();
          vb2.setPadding(new Insets(10,10,10,10));
          vb2.setSpacing(-10);
          vb2.getChildren().addAll(txtf1,sp1);
        
          Label lb2 = new Label("Other Names");
          TextField txtf2  = new TextField();
          txtf2.setStyle(".text-field");
          Separator sp2 = new Separator();
          sp2.setPadding(new Insets(10,10,10,10));
          VBox vb3 = new VBox();
          vb3.setPadding(new Insets(10,10,10,10));
          vb3.setSpacing(-10);
          vb3.getChildren().addAll(txtf2,sp2);
         
         
          Label lb3 = new Label("Age");
          ChoiceBox<Integer> cb1 = new ChoiceBox<>();
         
          for(int i = 18; i<=60; i++)
          {
              cb1.getItems().add(i);
          }  
          cb1.setValue(18);
          cb1.autosize();

         
          
          Label lb4 = new Label("Job Type");
          cb2 = new ChoiceBox ();
          cb2.getItems().addAll("Regular","Special","Graduate Trainee");
          cb2.autosize();
         
         
        
          Label lb5 = new Label("Gender");
          RadioButton rb1 = new RadioButton("Male\t\t");
          //rb1.setOnAction(e-> displayGender(rb1));
          RadioButton rb2 = new RadioButton("Female");
          HBox hb1 = new HBox();
         hb1.getChildren().addAll(rb1,rb2);
        hb1.setPadding(new Insets(10,10,10,10));
        
        tg1= new ToggleGroup();
        rb1.setToggleGroup(tg1);
        rb1.setSelected(true);
        rb2.setToggleGroup(tg1);
        
         
        Label lb6 = new Label("Phone Number");
        TextField txtf3 = new TextField();
        txtf3.setStyle(".text-field");
        Separator sp3 = new Separator();
        sp3.setPadding(new Insets(10,10,10,10));
        VBox vb4 = new VBox();
        vb4.setPadding(new Insets(10,10,10,10));
        vb4.setSpacing(-10);
        vb4.getChildren().addAll(txtf3,sp3);
       
       
        
         btn5 = new Button("Generate ID Card");
         btn5.setOnAction(
                 e -> 
                 {
                    if(txtf1.getText().isEmpty() || txtf2.getText().isEmpty() || txtf3.getText().isEmpty() || cb2.getItems().isEmpty() )
                {
                    a.setAlertType(AlertType.ERROR);
                    a.setContentText("The Field(s) cannot be left blank. You must enter in a value");
                    a.show();
                }
                    else
                    {
                     primaryStage.setScene(scene3);
                     primaryStage.setTitle("Generated ID Card");
                     displayDetails(txtf1,txtf2,txtf3,cb2,rb1,rb2);
                       setImage(); 
                   
                     do
                     {
                         generateID(); 
                     }
                    while (btn5.isPressed());
                         
                    }
                
                 }
         );
         Button btn6 = new Button("Cancel");
         btn6.setOnAction(
                 e -> 
                 {
                         primaryStage.setScene(scene1);
                         primaryStage.setTitle("Id Card Maker");
                 }
         );
         
         
         HBox hb2 = new HBox();
         hb2.getChildren().addAll(btn5,btn6);
         hb2.setSpacing(50);
         hb2.setPadding(new Insets(10,10,10,10));
         
         //To get Image from User
          Button btnLoad = new Button("Upload Passport");
          btnLoad.setOnAction(btnLoadEventListener);
        
          imageView = new ImageView(); 
         
        
         /* VBox vb1 = new VBox();
          vb1.getChildren().addAll(btnLoad, imageView);*/
        
         
          GridPane grid2 = new GridPane();
          grid2.setVgap(50);
          grid2.setHgap(20);
          grid2.setPadding(new Insets(30,30,30,30));
          grid2.getChildren().addAll(lb1,vb2,lb2,vb3,lb3,cb1,lb4,cb2,lb5,hb1,lb6,vb4,hb2,imageView,btnLoad);
          grid2.setStyle
        (
                "-fx-background-color: #009999;\n" +
                "-fx-border-color: black;"
        );
 
       
         GridPane.setConstraints(imageView, 0, 0);
         GridPane.setConstraints(btnLoad, 1, 0);
         GridPane.setConstraints(lb1, 0, 1);
         GridPane.setConstraints(vb2, 1, 1);
         GridPane.setConstraints(lb2, 0, 2);
         GridPane.setConstraints(vb3, 1, 2);
         GridPane.setConstraints(lb3, 0, 3);
         GridPane.setConstraints(cb1, 1, 3);
         GridPane.setConstraints(lb4, 0, 4);
         GridPane.setConstraints(cb2, 1, 4);
         GridPane.setConstraints(lb5, 0, 5);
         GridPane.setConstraints(hb1, 1, 5);
         GridPane.setConstraints(lb6, 0, 6);
         GridPane.setConstraints(vb4, 1, 6);
         GridPane.setConstraints(hb2, 1, 7);
      
        
         grid2.setAlignment(Pos.CENTER);
        
        
         //Creating components for scene3
        Label lb7 = new Label("Name :");
         //lb7.setMinWidth(200);
         lb8 = new Label();
         
         Label lb9 = new Label("Job Type :");
         //lb9.setMinWidth(200);
         lb10 = new Label();
         
         Label lb11 = new Label("Gender :");
         lb12 = new Label();
         
         Label lb13 = new Label("Phone Number :");
         lb14 = new Label();
         
         Label lb15 = new Label("ID Number");
         lb16 = new Label();
         
         Button btn7 = new Button("Save");
          btn7.setOnAction(new EventHandler<ActionEvent>()
          {
             
            @Override
            public void handle(ActionEvent event) 
            {
                System.out.println("Hello World!");
                takeSnapShot(scene3);
                insertIntoDatabase(txtf1.getText(),txtf2.getText(),cb1.getValue(),cb2.getValue(),lb12.getText(),txtf3.getText(),filepath,lb16.getText());
                 btn7.setDisable(true);
                 btn8.setDisable(true);
               
                new Alert(AlertType.INFORMATION, "Data saved successfully!!!").showAndWait();
                 
            }
            
           
           
   
        });
          
         
           
   
        
         
          btn8 = new Button("Discard");
           btn8.setOnAction
        (
                e -> 
                {
                  
                     txtf1.clear();
                     txtf2.clear();
                     txtf3.clear();
                     cb2.getSelectionModel().clearSelection();
                     imageView.setImage(null);
                      btn7.setDisable(false);
                      btn8.setDisable(false);
                     
                         primaryStage.setScene(scene2);
                         primaryStage.setTitle("New ID Card");
              }
        );
        
   
            
         btn9 = new Button("Print");
         btn9.setOnAction
        (
                e ->
                {
                     //Getting the Default Printer
                Printer defaultprinter = Printer.getDefaultPrinter();
                 if (defaultprinter != null) 
                {
                    String name = defaultprinter.getName();
                    c.setAlertType(AlertType.INFORMATION);
                    c.setContentText("Default printer name: " + name);
                    c.show();
                } 
                else
                {
                    c.setContentText("No printers installed.");
                    c.show();
                }
                 //Button printSceneButton = new Button("Print Scene");
                  pageSetup(vb5,primaryStage);
 
                }
                
         );
         
         
         btn10 = new Button("Create another Card");
         btn10.setOnAction
        (
                e ->
                {
                         txtf1.clear();
                         txtf2.clear();
                         txtf3.clear();
                         cb2.getSelectionModel().clearSelection();
                         imageView.setImage(null);
                         btn7.setDisable(false);
                         btn8.setDisable(false);
                         primaryStage.setScene(scene2);
                         primaryStage.setTitle("New ID Card");
                }
         );
         
         btn11 = new Button("Back To Main Menu");
           btn11.setOnAction
        (
                e ->
                {
                         txtf1.clear();
                         txtf2.clear();
                         txtf3.clear();
                          cb2.getSelectionModel().clearSelection();
                         imageView.setImage(null);
                          btn7.setDisable(false);
                         btn8.setDisable(false);
                         primaryStage.setScene(scene1);
                         primaryStage.setTitle("Id Card Maker");
                }
         );
   
         
         imageView2 = new ImageView();
         
         HBox hb4 = new HBox();
         hb4.getChildren().addAll(btn7,btn8,btn9,btn10,btn11);
         hb4.setSpacing(10);
         hb4.setAlignment(Pos.BASELINE_CENTER);
        
         
          GridPane grid3  = new GridPane();
          grid3.setVgap(30);
          grid3.setHgap(20);
          grid3.setPadding(new Insets(30,30,30,30));
         
         //GridPane.setConstraints(imageView2, 1, 0);
         GridPane.setConstraints(lb7, 1, 1);
         GridPane.setConstraints(lb8, 2, 1);
         GridPane.setConstraints(lb9, 1, 2);
         GridPane.setConstraints(lb10, 2, 2);
         GridPane.setConstraints(lb11, 1, 3);
         GridPane.setConstraints(lb12, 2, 3);
         GridPane.setConstraints(lb13, 1, 4);
         GridPane.setConstraints(lb14, 2, 4);
         GridPane.setConstraints(lb15, 1, 5);
         GridPane.setConstraints(lb16, 2, 5);
         /*GridPane.setConstraints(btn7, 1, 7);
         GridPane.setConstraints(btn8, 2, 7);
         GridPane.setConstraints(btn9, 3, 7);
         GridPane.setConstraints(btn10, 4, 7);
         GridPane.setConstraints(btn11, 5, 7);*/
         
         
       /*     grid3.setStyle
        (
                "-fx-background-color: #009999;\n" +
               "-fx-border-color: black;"
        );*/
         
         
         grid3.getChildren().addAll(lb7,lb8,lb9,lb10,lb11,lb12,lb13,lb14,lb15,lb16);
         //grid3.setAlignment(Pos.CENTER);
      
        
         hb5 = new HBox();
         hb5.getChildren().addAll(imageView2,grid3);
         hb5.setAlignment(Pos.CENTER);
        
       
               VBox vb5 = new VBox();
               vb5.getChildren().addAll(hb5,hb4);
               vb5.setSpacing(40);
               vb5.setAlignment(Pos.CENTER);
                        vb5.setStyle
        (
                "-fx-background-color: #009999;\n" +
               "-fx-border-color: black;"
        );
                        hb5.setSpacing(50);
                        
                        
                        //Scene 4
                        Label lbupdate = new Label("ID Number :");
                        TextField txtupdate = new TextField();
                        txtupdate.setStyle(".text-field");
                       
                        Separator spupdate = new Separator();
                        spupdate.setPadding(new Insets(10,10,10,10));
                        
                          VBox vbupdate = new VBox();
                          vbupdate.setPadding(new Insets(10,10,10,10));
                          vbupdate.setSpacing(-10);
                          vbupdate.getChildren().addAll(txtupdate,spupdate);
                        
                        Button btnupdate = new Button("Search");
                        btnupdate.setOnAction
                       (
                                e ->
                                {
                                    checkDatabase();
                                     System.out.println(""+txtupdate.getText());
                                     searchrs = txtupdate.getText();
                                     primaryStage.setScene(scene5);
                                }
                        );
                        
                        
                        GridPane gridupdate = new GridPane();
                        gridupdate.getChildren().addAll(lbupdate,vbupdate,btnupdate);
                        gridupdate.setAlignment(Pos.CENTER);
                        gridupdate.setVgap(40);
                        gridupdate.setHgap(40);
                        gridupdate.setStyle
        (
                "-fx-background-color: #009999;\n" +
               "-fx-border-color: black;"
        );
                        
                        GridPane.setConstraints(lbupdate, 0, 0);
                        GridPane.setConstraints(vbupdate, 1, 0);
                        GridPane.setConstraints(btnupdate, 1, 1);
                        
                        
                        //Scene 5
                         //To get Image from User
          Button btnLoadupdate2 = new Button("Upload Passport");
          btnLoadupdate2.setOnAction(btnLoadEventListener);
        
          imageViewupdate2 = new ImageView();
                        
                        
            Label lbupdate2 = new Label("Surname");
          TextField txtupdate2 = new TextField();
          txtupdate2.setStyle(".text-field");
          Separator spupdate2 = new Separator();
          spupdate2.setPadding(new Insets(10,10,10,10));
          VBox vbupdate2 = new VBox();
          vbupdate2.setPadding(new Insets(10,10,10,10));
          vbupdate2.setSpacing(-10);
          vbupdate2.getChildren().addAll(txtupdate2,spupdate2);
        
          Label lb2update2 = new Label("Other Names");
          TextField txt2update2  = new TextField();
          txt2update2.setStyle(".text-field");
          Separator sp2update2 = new Separator();
          sp2.setPadding(new Insets(10,10,10,10));
          VBox vb3update2 = new VBox();
          vb3update2.setPadding(new Insets(10,10,10,10));
          vb3update2.setSpacing(-10);
          vb3update2.getChildren().addAll(txt2update2,sp2update2);
         
         
          Label lb3update2 = new Label("Age");
          ChoiceBox<Integer> cbupdate2 = new ChoiceBox<>();
         
          for(int i = 18; i<=60; i++)
          {
              cbupdate2.getItems().add(i);
          }  
          cbupdate2.setValue(18);
          cbupdate2.autosize();

         
          
          Label lb4update2 = new Label("Job Type");
         ChoiceBox<String> cb2update2 = new ChoiceBox<>();
          cb2update2 = new ChoiceBox ();
          cb2update2.getItems().addAll("Regular","Special","Graduate Trainee");
          cb2update2.autosize();
         
         
        
          Label lb5update2 = new Label("Gender");
          RadioButton rb1update2 = new RadioButton("Male\t\t");
          //rb1.setOnAction(e-> displayGender(rb1));
          RadioButton rb2update2 = new RadioButton("Female");
          HBox hbupdate2 = new HBox();
         hbupdate2.getChildren().addAll(rb1update2,rb2update2);
        hbupdate2.setPadding(new Insets(10,10,10,10));
        
       ToggleGroup tg1update2= new ToggleGroup();
        rb1update2.setToggleGroup(tg1update2);
        rb1update2.setSelected(true);
        rb2update2.setToggleGroup(tg1update2);
        
         
        Label lb6update2 = new Label("Phone Number");
        TextField txt3update2 = new TextField();
        txt3update2.setStyle(".text-field");
        Separator sp3update2 = new Separator();
        sp3update2.setPadding(new Insets(10,10,10,10));
        VBox vb4update2 = new VBox();
        vb4update2.setPadding(new Insets(10,10,10,10));
        vb4update2.setSpacing(-10);
        vb4update2.getChildren().addAll(txt3update2,sp3update2);
           
        Button btnupdateconfirm = new Button("Update");
         btnupdateconfirm.setOnAction(
                 e ->
                 {
                     insertIntoDatabase2(txtupdate2.getText(),txt2update2.getText(),cbupdate2.getValue(),cb2.getValue(),lb12.getText(),txtf3.getText(),filepath,lb16.getText());
                 }
                 );
                        
        GridPane gridupdate2 = new GridPane();
        gridupdate2.getChildren().addAll( imageViewupdate2,btnLoadupdate2,lbupdate2,vbupdate2,lb2update2,vb3update2,lb3update2,cbupdate2,lb4update2,cb2update2,lb5update2,hbupdate2,lb6update2,vb4update2,btnupdateconfirm);
          gridupdate2.setVgap(30);
          gridupdate2.setHgap(20);
          gridupdate2.setPadding(new Insets(30,30,30,30));
         gridupdate2.setAlignment(Pos.CENTER);
                        gridupdate2.setStyle
        (
                "-fx-background-color: #009999;\n" +
               "-fx-border-color: black;"
        );
        
        GridPane.setConstraints( imageViewupdate2, 0, 0);
        GridPane.setConstraints( btnLoadupdate2, 1, 0); 
         GridPane.setConstraints( lbupdate2, 0, 1);
          GridPane.setConstraints( vbupdate2, 1, 1);
           GridPane.setConstraints( lb2update2, 0, 2);
            GridPane.setConstraints( vb3update2, 1, 2);
             GridPane.setConstraints( lb3update2, 0, 3);
              GridPane.setConstraints( cbupdate2, 1, 3);
               GridPane.setConstraints( lb4update2, 0, 4);
                GridPane.setConstraints( cb2update2, 1, 4);
                 GridPane.setConstraints( lb5update2, 0, 5);
                  GridPane.setConstraints( hbupdate2, 1, 5);
                   GridPane.setConstraints( lb6update2, 0, 6);
                    GridPane.setConstraints( vb4update2, 1, 6);
                    GridPane.setConstraints( btnupdateconfirm, 1, 8);
        
        
                    
                     //scene6
            
                      
                        Label lbdelete = new Label("ID Number :");
                        TextField txtdelete = new TextField();
                        txtdelete.setStyle(".text-field");
                       
                        Separator spdelete = new Separator();
                        spdelete.setPadding(new Insets(10,10,10,10));
                        
                          VBox vbdelete = new VBox();
                          vbdelete.setPadding(new Insets(10,10,10,10));
                          vbdelete.setSpacing(-10);
                          vbdelete.getChildren().addAll(txtupdate,spupdate);
                        
                        Button btndelete = new Button("Delete");
                        btndelete.setOnAction
                       (
                                e ->
                                {
                                    
                                    try{
                                        checkDatabase();
                                       String sql = "SELECT * FROM carddetails";
                                       String value = ""+txtdelete.getText();
                                         
                               //Deleting Database ROW
                                    String sql4 = "DELETE FROM carddetails where idnumber= '"+txtdelete.getText()+"'";
                                  
              //String sql4 = "DELETE FROM bio where id=3";

                                   stmt.executeUpdate(sql4);
                                  System.out.println("Details successfully Deleted");
                                    }
                                    catch(Exception w)
                                    {
                                        
                                    }

                                }
                        );
                        
                        
                        GridPane griddelete = new GridPane();
                        griddelete.getChildren().addAll(lbdelete,vbdelete,btndelete);
                        griddelete.setAlignment(Pos.CENTER);
                        griddelete.setVgap(40);
                        griddelete.setHgap(40);
                        griddelete.setStyle
        (
                "-fx-background-color: #009999;\n" +
               "-fx-border-color: black;"
        );
                        
                        GridPane.setConstraints(lbdelete, 0, 0);
                        GridPane.setConstraints(vbdelete, 1, 0);
                        GridPane.setConstraints(btndelete, 1, 1);
        
        
        
        scene1 = new Scene(grid1,700,650);
        scene1.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene2 = new Scene(grid2,1000,1100);
        scene2.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene2.getStylesheets().add(getClass().getResource("projectstyle.css").toExternalForm());
        scene3 = new Scene(vb5,1000,800);
        scene3.getStylesheets().add(getClass().getResource("projectstyle.css").toExternalForm());
        scene3.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene4 = new Scene(gridupdate,700,650);
        scene4.getStylesheets().add(getClass().getResource("projectstyle.css").toExternalForm());
        scene4.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene5 = new Scene(gridupdate2,1000,1100);
        scene5.getStylesheets().add(getClass().getResource("projectstyle.css").toExternalForm());
        scene5.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        scene6 = new Scene(griddelete,1000,1100);
        scene6.getStylesheets().add(getClass().getResource("projectstyle.css").toExternalForm());
        scene6.getStylesheets().add(getClass().getResource("css2.css").toExternalForm());
        
        
        
       primaryStage.getIcons().add( new Image("file:daniel.png"));
        primaryStage.setTitle("Id Card Maker");
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

   
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public void displayDetails(TextField f1, TextField f2,TextField f3,ChoiceBox ch1,RadioButton R1, RadioButton R2)
    {
        
       
         
        //Declaring variables
         String surname = f1.getText();
         String othernames = f2.getText();
         String phoneno = f3.getText();
         String job_type = ch1.getValue().toString();
         
         //Names
         String txt= "";
         txt += " "+surname +" "+othernames;
         lb8.setText(txt);
         
         //PhoneNumbers
         String txt2 = "";
         txt2 = " "+phoneno;
         lb14.setText(txt2);
         
         //JobType
         
         String txt3 = "";
         txt3 += " "+job_type;
         lb10.setText(txt3);
        
       
           
         if(tg1.getSelectedToggle().equals(R1))
         {
              lb12.setText("Male");
         }
         else  if(tg1.getSelectedToggle().equals(R2))
         {
              lb12.setText("Female");
         }
         
        
       
    }
    
    public void generateID()
    {
        
              id++;
       
        String jobType = cb2.getValue();
        String txt4 = "";
      
       
        
        if(jobType.equals("Regular"))
                {
                    txt4 += "REG"+id;
                    lb16.setText(txt4);
                    
                }
        
        else if(jobType.equals("Special"))
                {
                    txt4 += "SPC"+id;
                    lb16.setText(txt4);
                }
         
        else if(jobType.equals("Graduate Trainee"))
                {
                    txt4 += "GT"+id;
                    lb16.setText(txt4);
                }
         //setting the fit height and width of the image view 
                imageView2.setFitHeight(250); 
                imageView2.setFitWidth(200); 
        
         noofIdcards++;
       
      
    }
  
       EventHandler<ActionEvent> btnLoadEventListener = new EventHandler<ActionEvent>()
       {

        @Override
        public void handle(ActionEvent t) 
        {
              
                  
            FileChooser fileChooser = new FileChooser();
            
            //To Set extension filter for image format
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
             
            //Show open file dialog to select an image
            File file = fileChooser.showOpenDialog(null);
                      
            try 
            {
                BufferedImage bufferedImage = ImageIO.read(file);
                filepath=file.toString();
                image = SwingFXUtils.toFXImage(bufferedImage, null);
                image2 = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
                //setting the fit height and width of the image view 
               imageView.setFitHeight(150); 
                imageView.setFitWidth(150); 
            }
            catch (IOException ex) 
            {
                Logger.getLogger(Project1.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
        }
    };

    public void setImage()
    {
         imageView2.setImage(image2);
         imageView2.setFitHeight(700);
         imageView2.setFitWidth(300);
         
    }
 
     private void takeSnapShot(Scene scene)
     {
        WritableImage writableImage = new WritableImage((int)scene.getWidth(), (int)scene.getHeight());
        scene.snapshot(writableImage);
         
        File file = new File("snapshot.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            System.out.println("snapshot saved: " + file.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(Project1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   
    public void insertIntoDatabase(String fn,String on,int ag,String jt,String g,String phoneno,String passport,String idno)
    {
          try
        {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to Database");
            
            //open conncetion to database
            conn = DriverManager.getConnection(db_url,db_username,db_password);
             System.out.println("Connected to Database");
             
             stmt = conn.createStatement();
             //Inserting into databse
              String sql = "INSERT INTO carddetails (firstname,othernames,age,jobtype,gender,phonenumber,passport,idnumber)"
                      +"VALUES('"
                      + fn + "'" +
                       ",'"+ on +"'"+
                       ",'" + ag +"'"+
                       ",'" + jt +"'"+
                       ",'" + g +"'"+
                       ",'" + phoneno +"'"+
                       ",'" + passport +"'"+
                       ",'" + idno +"'"+
                   ")";
                      
                      stmt.executeUpdate(sql);
                      System.out.println("Successfully inserted into database");
                      
               
        }
        catch(Exception e)
                {
                    e.printStackTrace();
                }
    }
    
          private void pageSetup(VBox vb5, Stage primaryStage) 
    {
        // Create the PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();
         
        if (job == null) 
        {
            return;
        }
         
        // Show the page setup dialog
        boolean proceed = job.showPageSetupDialog(primaryStage);
         
        if (proceed) 
        {
            print(job, hb5);
        }
    }
    
       /* private void print()
        {
            // Define the Job Status Message
                jobStatus.textProperty().unbind();
                jobStatus.setText("Creating a printer job...");
                
                  // Create a printer job for the default printer
             PrinterJob job = PrinterJob.createPrinterJob();
             
              if (job != null) 
        {
            // Show the printer job status
            jobStatus.textProperty().bind(job.jobStatusProperty().asString());
             
            // Print the node
            boolean printed = job.printPage(hb5);
 
            if (printed) 
            {
                // End the printer job
                job.endJob();
            } 
            else
            {
                // Write Error Message
                jobStatus.textProperty().unbind();
                jobStatus.setText("Printing failed.");
            }
        } 
         
                {
            // Write Error Message
            jobStatus.setText("Could not create a printer job.");
        }
        }*/
        
          private void print(PrinterJob job, HBox hb5) 
    {
        // Set the Job Status Message
        jobStatus.textProperty().bind(job.jobStatusProperty().asString());
         
        // Print the node
        boolean printed = job.printPage(hb5);
     
        if (printed) 
        {
            job.endJob();
        }
    }   
        
          public void checkDatabase()
          {
               //connect to database
        try
        {
            Class.forName(JDBC_DRIVER);//register driver
            System.out.println("Connecting to database...");
            //open connection
            conn = DriverManager.getConnection(db_url, db_username, db_password);
            System.out.println("Connected successfully to database.");
            
            //create statement
            stmt = conn.createStatement();
          
        
            String sql = "SELECT * FROM carddetails WHERE idnumber = '"+searchrs +"'";
            rs = stmt.executeQuery(sql);
            
             while(rs.next())
                     {
                  
                            System.out.println(rs.getString("firstname")+ " : ");
                            
                     }
                  
            
        }
        
        catch(Exception e)
        {
            System.err.println("Unable to connect to database");
        }
          }
          
       
    public void insertIntoDatabase2(String fn,String on,int ag,String jt,String g,String phoneno,String passport,String idno)
    {
          try
        {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to Database");
            
            //open conncetion to database
            conn = DriverManager.getConnection(db_url,db_username,db_password);
             System.out.println("Connected to Database");
             
             stmt = conn.createStatement();
             //Inserting into databse
              String sql = "INSERT INTO carddetails (firstname,othernames,age,jobtype,gender,phonenumber,passport,idnumber)"
                      +"VALUES('"
                      + fn + "'" +
                       ",'"+ on +"'"+
                       ",'" + ag +"'"+
                       ",'" + jt +"'"+
                       ",'" + g +"'"+
                       ",'" + phoneno +"'"+
                       ",'" + passport +"'"+
                       ",'" + idno +"'"+
                   ")";
                      
                      stmt.executeUpdate(sql);
                      System.out.println("Successfully updated database");
                      
               
        }
        catch(Exception e)
                {
                    e.printStackTrace();
                }
    }
    
  
}

