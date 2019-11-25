package ui;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class MainMenu{
   
   public MainMenu(){
        
      Scanner scanner = new Scanner(System.in);
      
      System.out.println ();
      System.out.println("Main Menu");
      System.out.println ("[1] Director Menu");
      System.out.println("[2] Reception Menu");
      System.out.println("[3] Accountant Menu");
      System.out.println("[4] Cleaning Menu");
      System.out.println("[5] Close program");          
      System.out.print("\t Type number 1 - 5 : ");
      String choice;
      choice = scanner.next();
           
             
      switch ( choice ) {
         case "1":
            new DirectorMenu ();
            break;
         case "2":
            new ReceptionMenu ();
            break;
         case "3":
            new AccountantMenu ();
            break;
         case "4":
            new CleaningMenu ();
            break;
         case "5": 
            System.out.println("You're killing me. I want to live!");
            System.exit(0);
            break;
         default:
            System.out.println("Not a valid option. Please try again.");  
            new MainMenu();  
      }
   }      
}