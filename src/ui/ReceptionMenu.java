package ui;
import java.io.*;
import java.util.*;
import data.*;
import ui.*;
import java.time.*;

public class ReceptionMenu {
   private Scanner scanner = new Scanner(System.in);

   public ReceptionMenu(){
      System.out.println();
      System.out.println("Reception Menu");
      System.out.println("[1] Create Booking\n[2] Update Booking status\n[3] Update Guest Details\n[4] Add Payment\n[5] Display bookings\n[6] Display today's bookings\n[7] Return to Main Menu");
           
      String choice;
      choice = scanner.next();
           
      switch ( choice ){
         case "1":
            createNewBooking();
            break;
         case "2":
            displayTodaysBookings();
            updateBookingStatus();
            break;
         case "3":
            displayTodaysBookings();
            updateGuestDetails();
            break;
         case "4":
            addPayment();
            break;
         case "5":
            displayBookings();
            break;
         case "6":
            displayTodaysBookings();
            new ReceptionMenu();
            break;
         case "7":
            new MainMenu();
         default :
            System.out.println("Not a valid option. Please try again.");
            new ReceptionMenu();
      }
      new ReceptionMenu();
         
   }
   
   private void createNewBooking(){
      App.getController().createBooking();
      
      System.out.println("Press 0 to go back");
      if(App.getController().getNumber()==0){
         new ReceptionMenu();
      }
   }
    
   private void updateGuestDetails(){
      System.out.println("Enter the booking id");
      int bookingId = App.getController().getNumber();
      
      if(App.getController().doesBookingExist(bookingId)){
         App.getController().updateGuestDetails(bookingId);
      }else{
         System.out.println("No such a booking. Try again\nOr press 0 to go back");
         if(App.getController().getNumber() == 0){
            new ReceptionMenu(); 
         }
         updateGuestDetails();
      }
   }
   
   public void updateBookingStatus(){
      System.out.println("Enter bookingId");
      int bookingId = App.getController().getNumber();
      
      if(App.getController().doesBookingExist(bookingId)){
         System.out.println("[1] Check In\n[2] Check Out\n[3] Cancel\n");
         int statusOption = App.getController().getNumber();
         App.getController().updateBookingStatus(bookingId, statusOption);
         App.getController().updateBookingsFile();
         new ReceptionMenu();
      }else{
         System.out.println("No such a booking. Try again\nOr press 0 to go back");
         if(App.getController().getNumber() == 0){
            new ReceptionMenu(); 
         }
         updateBookingStatus();
      }
   }
   
   public void addPayment(){
      System.out.println("Enter the booking id");
      int bookingId = App.getController().getNumber();
      
      if(App.getController().doesBookingExist(bookingId)){
         if(!App.getController().isPaid(bookingId)){
            System.out.println("The price for the room is: " + App.getController().getPrice(bookingId));
            App.getController().setPayment(bookingId);
         }else{
            System.out.println("The boooking has been already paid");
         }
      }else{
         System.out.println("No such a booking. Try again\nOr press 0 to go back");
         if(App.getController().getNumber() == 0){
            new ReceptionMenu();
         }
         addPayment();
      }
      
      new ReceptionMenu();
   }
   
      
   public void displayBookings(){
      System.out.println("Enter keyword");
      String keyword = scanner.next();
     
      App.getController().displayBookings(keyword);
      new ReceptionMenu();
   }

   
   public void displayTodaysBookings(){
      LocalDate currentDate = LocalDate.now();
      App.getController().displayBookings(currentDate.toString());
   }
}