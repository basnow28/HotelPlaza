package ui;

import java.io.*;
import java.util.*;
import data.*;
import ui.*;

public class AccountantMenu {

   public AccountantMenu() {
   
      Scanner scanner = new Scanner(System.in);
   
      System.out.println("");
      System.out.println("Accountant Menu");
      System.out.println("[1] Generate accounting report\n[2] Return to Main Menu"); //change 18.11
      System.out.println("");
             
      String choice;
      choice = scanner.next();
             
             
      switch( choice ){
         case "1": //change 18.11
            App.getController().generateAccountingReport();
            new AccountantMenu();
            break;
         case "2":
            new MainMenu(); 
         default:
            System.out.println("Not a valid option. Please try again.");
            new AccountantMenu();
      }
   }
}