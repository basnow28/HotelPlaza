package data;

import java.util.*;

public class Booking {

   private static int count = 0;
   private int bookingId;
   private String bookedFrom;
   private int numOfNights;
   private Guest guest;
   private int numOfGuests;
   private int roomNumber;
   private int paymentId;
   private String status; // CheckedIn, CheckedOut, Created, Cancelled
   private double price; 

   public Booking(String line) {
      Scanner elements = new Scanner(line);
      this.bookingId = elements.nextInt();
      this.bookedFrom = elements.next();
      this.numOfNights = elements.nextInt();
      this.guest = new Guest(elements.next(), elements.next(), elements.next(), new Address (elements.next(),elements.next(), elements.next(), elements.next(), elements.next(), elements.next()));
      this.numOfGuests = elements.nextInt();
      this.roomNumber = elements.nextInt();
      this.paymentId = elements.nextInt(); 
      this.status = elements.next();
      this.price = elements.nextDouble(); //Barbara 18.11
      
      count = bookingId + 1;
   }
   public Booking (String bookedFrom, int numOfNights, int numOfGuests) {
      this.bookedFrom = bookedFrom;
      this.numOfNights = numOfNights;
      this.numOfGuests = numOfGuests;
      this.paymentId = -1;
      guest = new Guest(); // why??
      
      this.bookingId = count;
      count++;
      System.out.println("Booking created \nBooking Id: " + this.bookingId);
      
      this.status = "CREATED";
   }
   
   public void setGuest(Guest guest){
      this.guest = guest;
   }
   
   public Guest getGuest(){
      return this.guest;
   }
  
   public void setStatus(String status){
      this.status = status;
   }

   public int getBookingId() {
      return bookingId;
   }
   
   public int getPaymentId() {
      return paymentId;
   }
   
   public void setPaymentId(int paymentId) {
      this.paymentId = paymentId;
   }
   
   public int getRoomNumber(){
      return this.roomNumber;
   }
   
   public void setRoomNumber(int roomNumber){
      this.roomNumber = roomNumber;
   }
   
   public int getNumOfGuests(){
      return this.numOfGuests;
   }
   
   public String getBookedFrom(){
      return this.bookedFrom;
   }
   
   public int getNumOfNights(){
      return numOfNights;
   }
   
   public void setPrice(double pricePerNight){
      this.price = pricePerNight * numOfNights;
   }
   
   public double getPrice(){
      return price;
   }
   
   public void overridePrice(double price) {
      this.price = price;
   }
   
   public String getStatus(){
      return this.status;
   }
   
   public String toStringConsole() {
      return "Room " + this.roomNumber + " booked for " + this.guest.getFirstName() + " " + this.guest.getLastName() + " from " + this.bookedFrom + " for " + numOfNights + " nights. Booking ID: " + this.bookingId + 
         ", payment ID: " + this.paymentId + ", status: " + this.status;
   }
   
   public String toString() {
      return bookingId + " " + bookedFrom + " " + numOfNights + " " + guest.toString() + " " + numOfGuests + " " + roomNumber + " " + paymentId + " " + status + " " + price;
   }
}