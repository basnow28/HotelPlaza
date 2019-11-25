package service;

import java.io.*;
import data.*;
import fileHandlers.*;
import java.util.*;
import ui.*;
import java.time.*;

public class Service{

   static Scanner scanner = new Scanner(System.in);
   private static Database database = new Database();
   private EmployeesFileHandler empFileHandler;
   private BookingsFileHandler bookFileHandler;
   private RoomsFileHandler roomsFileHandler;
   private PaymentsFileHandler payFileHandler;
   
   public Service(){
      empFileHandler = new EmployeesFileHandler();
      bookFileHandler = new BookingsFileHandler();
      roomsFileHandler = new RoomsFileHandler();
      payFileHandler = new PaymentsFileHandler();
      
      empFileHandler.setEmployeesData(database.getEmployees());
      bookFileHandler.setBookingsData(database.getBookings());
      roomsFileHandler.setRoomsData(database.getRooms());
      payFileHandler.setPaymentsData(database.getPayments());
   }
   
   //////////Employees///////////////
   
   public boolean doesEmployeeExist(int employeeNumber) {
   
      for(int i =0; i<database.getEmployees().size(); i++) {
         if(database.getEmployees().get(i).getEmployeeNumber() == employeeNumber) {
            return true;
         }
      }
      return false;         
   }
   public void addEmployeeToDatabase(Employee tempemployee) {
   
      database.getEmployees().add(tempemployee);
      retrieveEmployeeNumber();
   }
   public int retrieveEmployeeNumber() {
      int employeeNumber = database.getEmployees().get(database.getEmployees().size()-1).getEmployeeNumber();
      return employeeNumber;
   }
   
   public void createEmployeeFirstName(int employeeNumber) {
      
      System.out.println("Enter First Name of Employee");
      String firstName = scanner.next();
      database.getEmployees().get(employeeNumber).setFirstName(firstName);
   }
   public void createEmployeeLastName(int employeeNumber) {
      
      System.out.println("Enter Last Name of Employee");
      String lastName = scanner.next();      
      database.getEmployees().get(employeeNumber).setLastName(lastName);
   }
   public void createEmployeePhoneNumber (int employeeNumber) {   
         
      System.out.println("Enter Phone number of Employee");
      String phoneNumber = scanner.next();
      database.getEmployees().get(employeeNumber).setPhoneNumber(phoneNumber);
   }

   public void createEmployeeAddress(int employeeNumber) {
   
      Address address = new Address();
      System.out.println("Enter Address of Person:");      
      System.out.println("Enter Street Name:");  
      address.setStreetName(scanner.next());
      System.out.println("Please enter Street number/identifier:");
      address.setStreetNumber(scanner.next());
      System.out.println("Please enter Apartment Number:");
      address.setApartmentNumber(scanner.next());
      System.out.println("Please enter zipcode:");
      address.setZipCode(scanner.next());
      System.out.println("Please enter neighbourhood:");
      address.setNeighborhood(scanner.next());
      System.out.println("Please enter country:");
      address.setCountry(scanner.next());
      database.getEmployees().get(employeeNumber).setAddress(address);
      //
   }
   public void createEmployeeType(int employeeNumber) {
      System.out.println("What type of Employee are they? Please enter:\n[1] for Receptionist\n[2] for Accountant\n[3] for Cleaning Staff\n[4] add another Director");
      String answer = scanner.next();
      switch (answer) {
         case "1":
            database.getEmployees().get(employeeNumber).setEmployeeType("Receptionist");
            break;
         case "2":
            database.getEmployees().get(employeeNumber).setEmployeeType("Accountant");
            break;
         case "3":
            database.getEmployees().get(employeeNumber).setEmployeeType("Cleaning");
            break;
         case "4":
            database.getEmployees().get(employeeNumber).setEmployeeType("Director");
            break;
         default:
            System.out.println("Not a valid option. Please try again.");
            break;
      }
      empFileHandler.updateEmployeesFile(database.getEmployees());
   }
   public void createEmployeeSalary(int employeeNumber) {
      System.out.println("Please enter Salary amount (kr per month)");
      int salary = getNumber();
      database.getEmployees().get(employeeNumber).setSalary(salary);
      empFileHandler.updateEmployeesFile(database.getEmployees());
   }

   public void listAllEmployees() {
      for(int i = 0; i < database.getEmployees().size(); i++) {
         System.out.println(database.getEmployees().get(i).toStringConsole());
      }
   }
   public void deleteEmployee(int employeeNumber) {
      for(int i=employeeNumber+1; i<database.getEmployees().size(); i++){
         database.getEmployees().get(i).setEmployeeNumber(i - 1);
      }
     
      database.getEmployees().remove(employeeNumber);
      empFileHandler.updateEmployeesFile(database.getEmployees());
   }
   public void showEmployeeInfo(int employeeNumber) {
      database.getEmployees().get(employeeNumber).toStringConsole();
   } 
   
  /////////////////////////////Bookings///////////////////////////////////
   public boolean doesBookingExist(int bookingId){
      if(getBooking(bookingId) != null){
         return true;
      }
      return false;
   }
   
   public void createNewBooking(String date, int numOfNights, int numOfGuests){
      database.getBookings().add(new Booking(date, numOfNights, numOfGuests));
   }
   
   public void createBooking(){
      System.out.println("What is the starting date for the booking? ");
      String date = scanner.next();
      System.out.println("How many nights?");
      int numOfNights = App.getController().getNumber();
      System.out.println("How many people?");
      int numOfGuests = App.getController().getNumber();
      while (numOfGuests > 10){
         System.out.println("Hotel Polski can accept max. 10 people in one booking.\n[1] Enter new number of guests\n[2] Go back to Reception Menu");
         switch (getNumber()){
            case 1:
               System.out.println("Enter number of the guests: ");
               numOfGuests = App.getController().getNumber();
               break;
            case 2:
               new ReceptionMenu();
               break;
            default:
               System.out.println("Enter 1 or 2.");
         }
      }
    
      Booking booking = new Booking(date, numOfNights, numOfGuests);
      database.getBookings().add(booking);
    
      updateGuestDetails(booking);
      allocateBooking(booking);
    
      App.getController().updateBookingsFile();
   }

   private void updateGuestDetails(Booking booking){
      System.out.println("Enter the guest's first name");
      booking.getGuest().setFirstName(scanner.next());
      System.out.println("Enter the guest's last name");
      booking.getGuest().setLastName(scanner.next());
      System.out.println("Enter the guest's telephone number");
      booking.getGuest().setPhoneNumber(scanner.next());
      System.out.println("Enter the guest's address");
      
      Address address = new Address();     
      System.out.println("Enter Street Name:");  
      address.setStreetName(scanner.next());
      System.out.println("Please enter Street number/identifier:");
      address.setStreetNumber(scanner.next());
      System.out.println("Please enter Apartment Number:");
      address.setApartmentNumber(scanner.next());
      System.out.println("Please enter zipcode:");
      address.setZipCode(scanner.next());
      System.out.println("Please enter neighbourhood:");
      address.setNeighborhood(scanner.next());
      System.out.println("Please enter country:");
      address.setCountry(scanner.next());
      
      booking.getGuest().setAddress(address);
   }
   
   //////////////////Barbara 19.11 evening 
   private void allocateBooking(Booking booking){
      System.out.println("Available rooms for this booking: ");
      displayAvailableRooms(booking);
      
      System.out.println("\nWhere would you like to allocate booking? ");
      booking.setRoomNumber(App.getController().getNumber());
      
      addBookingToRoom(booking);
      
      System.out.println("The total price of this booking is: ");
      booking.setPrice(getPricePerNight(booking.getBookingId()));
      System.out.println(booking.getPrice());
   }
   
   private void addBookingToRoom(Booking booking){
      for(Room room : database.getRooms()){
         if(room.getRoomNumber() == booking.getRoomNumber()){
            room.getBookingsIds().add(booking.getBookingId());
            roomsFileHandler.updateRoomData(room);
         }
      }
   }
   
   ////////////////////////////////////////
   
   public int getLastBookingId(){
      return database.getBookings().get(database.getBookings().size() - 1).getBookingId();
   }
   
   public void updateGuestDetails(int bookingId){
      Booking booking = getBooking(bookingId);
      updateGuestDetails(booking);
      bookFileHandler.updateBookingsFile(database.getBookings());
   }
   
   public void updateBookingStatus(int bookingId,int statusOption){
      switch(statusOption){
         case 1:
            getBooking(bookingId).setStatus("CHECKEDIN");
            System.out.println("Booking is checked in");
            break;
         case 2:
            getBooking(bookingId).setStatus("CHECKEDOUT");
            System.out.println("Booking is checked out");
            Room room = getRoom(getBooking(bookingId).getRoomNumber());
            room.setIsClean(false);
            roomsFileHandler.updateRoomData(room);
            printGuestReceipt(bookingId);
            break;
         case 3:
            getBooking(bookingId).setStatus("CANCELLED");
            System.out.println("Booking is cancelled");
            break;
         default:
            break;
      }
      
      bookFileHandler.updateBookingsFile(database.getBookings());
   }


   private double getPricePerNight(int bookingId){
      int roomNumber = getBooking(bookingId).getRoomNumber();
      
      for(int i=0; i<database.getRooms().size(); i++){
         if(roomNumber == database.getRooms().get(i).getRoomNumber()){
            return database.getRooms().get(i).getPrice();
         }
      }
      
      return 0;
   }
   
   public double getPrice(int bookingId){
      return getBooking(bookingId).getPrice();
   } 
     
   public void displayAvailableRooms(Booking booking){
      for(Room room : database.getRooms()){
         if(room.getCapacity() >= booking.getNumOfGuests() && room.getAvailable() ){
            if(isAvailable(room, booking)){
               System.out.print(room.getRoomNumber() + " ");
            }
         }
      }
   }
   
   private boolean isAvailable(Room room, Booking booking){
      boolean isAvailable = false;
      int countUnavailable = 0;
      
      for (int bookingId : room.getBookingsIds()){
         isAvailable = room.isAvailable(booking.getBookedFrom(),getBooking(bookingId).getNumOfNights(), getBooking(bookingId).getBookedFrom());
           
         if(isAvailable==false && !getBooking(bookingId).getStatus().equals("CANCELLED") ){
            countUnavailable++;
         }
      }
   
      if(countUnavailable > 0)
         return false;
            
      return true;
   }

   
   public void setPayment(int bookingId){
      double amount = getPrice(bookingId);
     
      System.out.println("Select [1] to pay with Cash or [2] to pay with Card");
      int paymentType = App.getController().getNumber();
      while (paymentType != 1 && paymentType != 2) { 
         System.out.println("Sorry, that is not a valid choice. Please try again.");
         paymentType = App.getController().getNumber();
      }
      
      if(paymentType == 1)
         database.getPayments().add(new Payment(LocalDate.now().toString(), "CASH", amount));
      else if(paymentType == 2)
         database.getPayments().add(new Payment(LocalDate.now().toString(), "CARD", amount));
   
      getBooking(bookingId).setPaymentId(database.getPayments().size() - 1);
      
      payFileHandler.updatePaymentsFile(database.getPayments());
      bookFileHandler.updateBookingsFile(database.getBookings());
      
      System.out.println("Payment has been successfully added");
      System.out.println("PaymentId: " + database.getPayments().get(database.getPayments().size() - 1).toStringConsole()); 
   }
   
   public void displayBookings(String info){
      for(Booking booking : database.getBookings()){
         if (booking.toString().toLowerCase().contains(info.toLowerCase())){
            System.out.println(booking.toStringConsole());
         }
      }
   }

   public void updateBookingsFile(){
      bookFileHandler.updateBookingsFile(database.getBookings());
   }
   
   private Booking getBooking(int bookingId){
      for(Booking booking : database.getBookings()){
         if(booking.getBookingId() == bookingId)
            return booking;
      }
      return null;
   }

   
   public Room getRoom(int roomNumber){
      for(Room room : database.getRooms()){
         if(room.getRoomNumber() == roomNumber)
            return room;
      }
      return null;
   }

   public static void printGuestReceipt(int bookingId) {
      System.out.println("====================================");
      System.out.println("========HOTEL POLSKI RECEIPT========");
      System.out.println("====================================");
      System.out.printf("%-25s %-25d", "BOOKING ID", bookingId); 
      System.out.printf("\n%-25s %-25s", "BOOKED FROM", database.getBookings().get(bookingId).getBookedFrom());
      System.out.printf("\n%-25s %-25d", "NUMBER OF NIGHTS", database.getBookings().get(bookingId).getNumOfNights());
      System.out.printf("\n%-25s %-25d", "PAYMENT ID", database.getBookings().get(bookingId).getPaymentId());
      System.out.printf("\n%-25s %-25d", "ROOM NUMBER", database.getBookings().get(bookingId).getRoomNumber());
      System.out.printf("\n%-25s %-25d", "NUMBER OF GUESTS", database.getBookings().get(bookingId).getNumOfGuests());
      System.out.printf("\n%-25s %-25s", "TOTAL PRICE", database.getBookings().get(bookingId).getPrice());
      System.out.println("\n====================================");
   }
   
   public boolean isPaid(int bookingId){
      if(getBooking(bookingId).getPaymentId() >= 0)
         return true;
      return false;
   }
   
   public void overridePrice(int bookingId){
      int newPrice = 0;
      System.out.println("Please enter NEW total price of Booking:");
      newPrice = getNumber();
      System.out.println("Total price will change from " + database.getBookings().get(bookingId).getPrice() + " to " + newPrice + ". Type 'yes' to continue or anything else to exit to Director Menu");
      String confirm = scanner.next();
      if(confirm.equalsIgnoreCase("yes")){
         database.getBookings().get(bookingId).overridePrice(newPrice);
         bookFileHandler.updateBookingsFile(database.getBookings());
         System.out.println("Booking price changed!");
         new DirectorMenu();   
      } else {
         System.out.println("Booking price changed!");
         new DirectorMenu();
      }     
   }

   
      //////////////Cleaning / Room Status////////////////////
   public static void viewRoomStatusMenu(String menu){ 
      System.out.println("View Room Status Menu:");
      System.out.println("[1] Check individual room status\n[2] View status of all rooms");
      if(menu.equals("Director")){
         System.out.println("[3] Return to Director Menu");
      } else {
         System.out.println("[3] Return to Cleaning Menu");
      }
      String answer = scanner.next();
      switch (answer) {
         case "1":
            checkIndividualRoomStatus(menu);
            break;
         case "2":
            checkAllRoomStatus(menu);
            break;
         case "3":
            if(menu.equals("Director")) {
               new DirectorMenu();
               break;
            }
            else {
               new CleaningMenu();
               break;
            }
         default:
            System.out.println("Not a valid option. Please try again");
      }
   }
   public static void checkIndividualRoomStatus(String menu) {
      System.out.println("Enter Room number you wish to check");
      int wanted = getNumber();
      for(int i = 0; i<database.getRooms().size(); i++) {
         if (database.getRooms().get(i).getRoomNumber()==wanted) {
            System.out.println(database.getRooms().get(i).toStringConsole());
         }
      }
      viewRoomStatusMenu(menu); 
   }

   public static void checkAllRoomStatus(String menu) {
      for(int i = 0; i<database.getRooms().size(); i++) {
         System.out.println(database.getRooms().get(i).toStringConsole());
      }
      viewRoomStatusMenu(menu); 
   }
   
   public void displayRoomStatus(int roomNumber){ 
   
      System.out.print("Room " + roomNumber + " is currently ");
      if (getRoom(roomNumber).getAvailable()) {
         System.out.print("available and ");
      }
      if (!getRoom(roomNumber).getAvailable()) {
         System.out.print("not available and ");
      }
      if (getRoom(roomNumber).getIsClean()) {
         System.out.println("clean.");
      }
      if (!getRoom(roomNumber).getIsClean()) {
         System.out.println("not clean.");
      }
   }
   
   public static boolean doesRoomExist(int enteredNumber){
   
      for(int i = 0; i<database.getRooms().size(); i++) {
         if (database.getRooms().get(i).getRoomNumber()==enteredNumber){
            return true;
         }
      }
      return false;
   }
   
   public void changeRoomStatus(String menu){
   
      System.out.println("Enter Room number you wish to change"); 
      int roomNumber = getNumber();
      
      if(!doesRoomExist(roomNumber)){
         System.out.println("Not a valid Room Number. Press [1] to try again or [2] to cancel.");
         String answer = scanner.next();
         switch (answer) {
            case "1":
               changeRoomStatus(menu);
               break;
            case "2":
               viewRoomStatusMenu(menu);
               break;
            default:
               System.out.println("Not a valid option. Please try again.");
               changeRoomStatus(menu);
         }
      }     
      displayRoomStatus(roomNumber);
      System.out.println("Type:\n[1] if room is currently CLEAN\n[2] if room is currently NOT CLEAN\n[3] to make room AVAILABLE\n[4] to make room UNAVAILABLE");
      if(menu.equals("Director")){
         System.out.println("[5] quit to Director Menu");
      } else {
         System.out.println("[5] quit to Cleaning Menu");
      }
      int answer = scanner.nextInt();
      switch (answer) {
         case 1:
            getRoom(roomNumber).setIsClean(true);
            break;
         case 2:
            getRoom(roomNumber).setIsClean(false);
            break;
         case 3:
            getRoom(roomNumber).setAvailable(true);
            break;
         case 4:
            getRoom(roomNumber).setAvailable(false);
            break;
         case 5:
            if(menu.equals("Director")){
               new DirectorMenu();
            } else {
               new CleaningMenu();
            }
            break;
         default:
            System.out.println("Not a valid option. Please try again.");
            changeRoomStatus(menu);
      }
      displayRoomStatus(roomNumber);
      roomsFileHandler.updateRoomData(getRoom(roomNumber)); //////////////////////////////////////////////////////
      new CleaningMenu();  
   }
   
   ///////////// get number method /////////////////
   
   public static int getNumber() {   
         
      boolean error=false;
      int number=0;
      do	{
         try {
            Scanner scanner = new Scanner(System.in);
            number = scanner.nextInt();
            error=false;
         }
         catch(InputMismatchException e) {
            System.out.println("Not a valid number. Please try again");
            error=true;
         }
      }
      while(error);
      return number;
   }
   
   public static void generateCleaningReport(){
      System.out.println("\t\tCleaning report:");
      System.out.println("Room number:"+"\tAvailable:"+"\tIs clean:");
      for (int i = 0; i<database.getRooms().size(); i++){
         System.out.println(database.getRooms().get(i).getRoomNumber() + "\t\t\t\t"+database.getRooms().get(i).getAvailable() 
            + "\t\t\t"+ database.getRooms().get(i).getIsClean());
      }
   }
   public static void generateAccountingReport(){
      double sumCash = 0;
      double sumCard = 0;
     
      System.out.println("\tAccountant Report:");
      System.out.println("Amount:" + "\tPayment type:" + "\tDate:");
      for(Payment payment : database.getPayments()){
         System.out.println(payment.getAmount() + "\t\t\t\t"+ payment.getPaymentType()
            +"\t\t\t"+ payment.getDate());
         
         if(payment.getPaymentType().equals("CASH"))
            sumCash += payment.getAmount();
         else
            sumCard += payment.getAmount();
      }  
      
      System.out.println("Cash sum: " + sumCash + "\nCard sum: " + sumCard + "\n");
   }

} //class bracket