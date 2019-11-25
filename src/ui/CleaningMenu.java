package ui;

import java.io.*;
import java.util.*;
import data.*;
import ui.*;


public class CleaningMenu {

   public CleaningMenu() {   
         
      Scanner scanner = new Scanner(System.in);
   
      System.out.println("");
      System.out.println("Cleaning Menu");
      System.out.println("[1] View room status\n[2] Change room status\n[3] Generate cleaning report\n[4] Return to Main Menu"); //new//
      System.out.println("");
             
      String choice ;
      choice = scanner.next();
             
      switch ( choice ){
         case "1":
            int menu = 2;
            App.getController().viewRoomStatusMenu("Cleaner");
            new CleaningMenu();
            break;
         case "2":
            App.getController().changeRoomStatus("Cleaner");
            new CleaningMenu();
            break;
         case "3":
            App.getController().generateCleaningReport();
            new CleaningMenu();
            break;
         case "4":
            new MainMenu();
            break;
         default :
            System.out.println("Not a valid option. Please try again.");
            new CleaningMenu();
      }
   } 
}