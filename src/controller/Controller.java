package controller;
import data.*;
import service.*;

public class Controller{
   private static Service service = new Service();
   
   public Controller(){
   }


////////////Employees/////////////////
   public boolean doesEmployeeExist(int employeeNumber) {
      return service.doesEmployeeExist(employeeNumber);
   } 
   public void addEmployeeToDatabase(Employee tempemployee) {
      service.addEmployeeToDatabase(tempemployee); 
   }
   public int retrieveEmployeeNumber() {
      int employeeNumber = service.retrieveEmployeeNumber();
      return employeeNumber;
   } 
   public void createEmployeeFirstName(int employeeNumber) {
      service.createEmployeeFirstName(employeeNumber);
   }
   public void createEmployeeLastName(int employeeNumber) {
      service.createEmployeeLastName(employeeNumber);
   }
   public void createEmployeePhoneNumber(int employeeNumber) {
      service.createEmployeePhoneNumber(employeeNumber);
   }
  
   public void createEmployeeAddress(int employeeNumber) {
      service.createEmployeeAddress(employeeNumber);
   }
   public void createEmployeeType(int employeeNumber) {
      service.createEmployeeType(employeeNumber);
   }
   
   public void listAllEmployees() {
      service.listAllEmployees();
   }
   
   public void showEmployeeInfo(int employeeNumber) {
      service.showEmployeeInfo(employeeNumber);
   }
   
   public void deleteEmployee(int employeeNumber) {
      service.deleteEmployee(employeeNumber);
   }
   
   
   //////////////Bookings////////////////////
   public void createNewBooking(String date, int numOfNights,int numOfGuests){
      service.createNewBooking(date, numOfNights, numOfGuests);
   }
   
   public void createBooking(){
      service.createBooking();
   }
   
   public void updateGuestDetails(int bookingId){
      service.updateGuestDetails(bookingId);
   }
   
   public int getLastBookingId(){
      return service.getLastBookingId();
   }
   
   public void updateBookingStatus(int bookingId, int statusOption){
      service.updateBookingStatus(bookingId, statusOption);
   }
   
   public boolean doesBookingExist(int bookingId){
      return service.doesBookingExist(bookingId);
   }
   public double getPrice(int bookingId){
      return service.getPrice(bookingId); 
   }
   
   public void setPayment(int bookingId){
      service.setPayment(bookingId);
   }
   
   public void displayBookings(String info){
      service.displayBookings(info);
   }

   public void updateBookingsFile(){
      service.updateBookingsFile();
   }
   
   public boolean isPaid(int bookingId){
      return service.isPaid(bookingId);
   }
   
   public void overridePrice(int bookingId){
      service.overridePrice(bookingId);
   }

   
   //////////////Cleaning / Room Status////////////////////
   
   public static void viewRoomStatusMenu(String menu){ 
      service.viewRoomStatusMenu(menu);
   }
   public static void changeRoomStatus(String menu){
      service.changeRoomStatus(menu);
   }
   public static void generateCleaningReport(){
      service.generateCleaningReport();
   } 
   public static void generateAccountingReport(){
      service.generateAccountingReport();
   }
   public void createEmployeeSalary(int employeeNumber) {
      service.createEmployeeSalary(employeeNumber);
   }
   
   /////////smth else//////
   public static int getNumber(){
      return service.getNumber();
   }
     
} // class bracket