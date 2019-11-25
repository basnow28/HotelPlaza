package ui;

import java.io.*;
import java.util.*;
import data.*;
import ui.*;
import java.time.*;

public class DirectorMenu{
   
   static Scanner scanner = new Scanner(System.in);
   
   public DirectorMenu() {
             
      System.out.println();
      System.out.println("Director Menu");
      System.out.println("[1] Manage employees");
      System.out.println("[2] Create Booking");
      System.out.println("[3] Update Booking");
      System.out.println("[4] Generate reports"); //new//
      System.out.println("[5] View room status");
      System.out.println("[6] Change room status");
      System.out.println("[7] Return to Main Menu");
   
            
      String choice;
      choice = scanner.next();
            
      switch ( choice ) {
         case "1":
            manageEmployeeMenu();
            break;
         case "2":
            createNewBooking();
            break;
         case "3":
            updateBookingMenu();
            break;
         case "4":
            generateReports(); 
            break;
         case "5":
            App.getController().viewRoomStatusMenu("Director"); 
            break;
         case "6":
            App.getController().changeRoomStatus("Director"); 
            break;
         case "7":
            new MainMenu();
         default:
            System.out.println("Not a valid option. Please try again.");
            new DirectorMenu();
      }
   }
   
   
   ////////////////Bookings //////////////////
   private void createNewBooking(){
      App.getController().createBooking();
      
      System.out.println("Press 0 to go back");
      if(App.getController().getNumber()==0){
         new ReceptionMenu();
      }
   }
   
   private void updateBookingMenu(){
      System.out.println();
      System.out.println("Update Booking Menu");
      System.out.println("[1] Update Booking Status");
      System.out.println("[2] Update Guest Details");
      System.out.println("[3] Add Payment");
      System.out.println("[4] Display Bookings"); //new//
      System.out.println("[5] Display Today's Bookings");
      System.out.println("[6] Override price on Booking");
      System.out.println("[7] Go back to Director Menu");
   
      
      String choice;
      choice = scanner.next();
      
      switch ( choice ){
         case "1":
            updateBookingStatus();
            break;
         case "2":
            updateGuestDetails();
            break;
         case "3":
            addPayment();
            break;
         case "4":
            displayBookings();
            break;
         case "5":
            displayTodaysBookings();
            break;
         case "6":
            overridePriceMenu();
            break;
         case "7":
            new DirectorMenu();
            break;
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
            updateBookingMenu(); 
         }
         updateGuestDetails();
      }
   }
   
   private void updateBookingStatus(){
      System.out.println("Enter bookingId");
      int bookingId = App.getController().getNumber();
      
      if(App.getController().doesBookingExist(bookingId)){
         System.out.println("[1]Check In\n[2]Check out\n[3]Cancel\n");
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
      System.out.println("Enter the booking ID");
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
      
      new DirectorMenu();
   }

   public void displayBookings(){
      System.out.println("Enter keyword");
      String keyword = scanner.next();
     
      App.getController().displayBookings(keyword);
      new DirectorMenu(); 
   }

   public void displayTodaysBookings(){
      LocalDate currentDate = LocalDate.now();
      App.getController().displayBookings(currentDate.toString());
      new DirectorMenu();
   }
   
   public void overridePriceMenu() {
      System.out.println("Please enter Booking Number:");
      int bookingId = App.getController().getNumber();
      if(App.getController().doesBookingExist(bookingId)){
         App.getController().overridePrice(bookingId);
      } else {
         System.out.println("Booking Number does not exist.");
         updateBookingMenu();
      }
   }


      ////////////////////////Empoyees///////////////////////
   
   public static void manageEmployeeMenu() {
   
      System.out.println("MANAGE EMPLOYEE MENU");
      System.out.println("[1] Create New Employee\n[2] Modify Existing Employee\n[3] Delete Employee\n[4] Return to Director Menu");
      String answer = scanner.next();
      switch (answer) {
         case "1":
            createNewEmployeeMenu();
            break;
         case "2":
            updateEmployeeMenu();
            break;
         case "3":
            deleteEmployeeMenu();
            break;
         case"4":
            new DirectorMenu();
            break;
         default:
            System.out.println("Not a valid option. Please try again.");
            manageEmployeeMenu();
            break;
      }
   }
  
   public static void createNewEmployeeMenu() {
   
      Employee tempemployee = new Employee();
      App.getController().addEmployeeToDatabase(tempemployee);
      int employeeNumber = App.getController().retrieveEmployeeNumber();
      App.getController().createEmployeeFirstName(employeeNumber);
      App.getController().createEmployeeLastName(employeeNumber);
      App.getController().createEmployeePhoneNumber(employeeNumber);
      App.getController().createEmployeeAddress(employeeNumber);
      App.getController().createEmployeeType(employeeNumber);
      App.getController().createEmployeeSalary(employeeNumber);
   
      System.out.println("Employee number " + App.getController().retrieveEmployeeNumber() + " created!");
      new DirectorMenu();
   }
      
   
   public static void updateEmployeeMenu() {
      
      System.out.println("UPDATE EMPLOYEE MENU");
      System.out.println("[1] Employee number known\n[2] List all Employees\n[3] Return to Director Menu");
      String answer = scanner.next();
      switch (answer) {
         case "1":
            break;
         case "2":
            App.getController().listAllEmployees();
            updateEmployeeMenu();
            break;
         case "3":
            new DirectorMenu();
         default:
            System.out.println("Not a valid option. Please try again");
            updateEmployeeMenu();
      }
      
      System.out.println("Enter Employee Number of Employee you wish to modify:");
      int employeeNumber = App.getController().getNumber();
      while(!App.getController().doesEmployeeExist(employeeNumber)) { //this needs further testing - MAYBE it doesn't work all the time?
         System.out.println("Not a valid Employee Number");
         employeeNumber = App.getController().getNumber();
      }
      App.getController().createEmployeeFirstName(employeeNumber);
      App.getController().createEmployeeLastName(employeeNumber);
      App.getController().createEmployeePhoneNumber(employeeNumber);
      App.getController().createEmployeeAddress(employeeNumber);
      App.getController().createEmployeeType(employeeNumber);
      App.getController().createEmployeeSalary(employeeNumber);
      System.out.println("Employee number " + App.getController().retrieveEmployeeNumber() + " modified!");
      new DirectorMenu();
   }
   public static void deleteEmployeeMenu() {
   
      System.out.println("Enter Employee Number of Employee you wish to delete:");
      int employeeNumber = App.getController().getNumber();
      while(!App.getController().doesEmployeeExist(employeeNumber)) {
         System.out.println("Not a valid Employee Number");
         employeeNumber = App.getController().getNumber();
      }
      App.getController().showEmployeeInfo(employeeNumber);
      System.out.println("Are you sure you wish to delete the above employee? Type 'yes' to delete. Type 'no' to return to Director Menu.");
      String answer = scanner.next();
      if(answer.equalsIgnoreCase("yes")) {
         App.getController().deleteEmployee(employeeNumber);
         System.out.println("Employee deleted!\n");
         manageEmployeeMenu();
         
      } 
      else if (answer.equalsIgnoreCase("no")) {
         new DirectorMenu();
      } else {
         System.out.println("Not a valid answer.");
         deleteEmployeeMenu();
      }   
   
   }
   //////////////////////////////////////////new/////////////////////////////////
   public static void generateReports(){
      System.out.println("[1] Cleaning report\n[2] Accounting report\n[3] Return to Director Menu");
      int choice = App.getController().getNumber();
      switch (choice){
         case 1:
            App.getController().generateCleaningReport();
            generateReports();
            break;
         case 2:
            App.getController().generateAccountingReport(); 
            generateReports();
            break;
         case 3:
            new DirectorMenu();
            break;
         default:
            generateReports();
      }   
   }
   
} //class bracket
  
