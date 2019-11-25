package data;

import java.util.*;

public class Database{
   private ArrayList<Employee> employees;
   private ArrayList<Booking> bookings;
   private ArrayList<Room> rooms;
   private ArrayList<Payment> payments;
   
   public Database(){
      employees = new ArrayList<Employee>();
      bookings = new ArrayList<Booking>();
      rooms = new ArrayList<Room>();
      payments = new ArrayList<Payment>();
   }
   
   public void setEmployees(ArrayList<Employee> employees){
      this.employees = employees;
   }

   public ArrayList<Employee> getEmployees(){
      return employees;
   }
   
   public ArrayList<Room> getRooms(){
      return rooms;
   }
   
   public ArrayList<Booking> getBookings(){
      return bookings;
   }
   
   public ArrayList<Payment> getPayments(){
      return payments;
   }
}