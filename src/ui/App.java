package ui;

import controller.Controller;

public class App{
   private static Controller controller;
   
   public static void main(String args[]){
      controller = new Controller();
      new MainMenu();
   }
   
   public static Controller getController(){
      return controller;
   }
}