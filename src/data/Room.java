package data;

// Rooms 101, 102, 103 are Doubles.    One double bed                (cap 1 - 2),   500kr,   available t/f, cleaned t/f.
// Rooms 201, 202, 203 are Twins.      Two double beds               (cap 1 - 4),   1000kr,  available t/f, cleaned t/f.
// Rooms 301, 302, 303 are Suites.     Two double beds, 2 singles    (cap 1 - 6),   1500kr,  available t/f, cleaned t/f.
// Room 401 is a Penthouse.            Four double beds, 2 singles   (cap 1 - 10),  3000kr,  available t/f, cleaned t/f.

import java.util.*;
import java.time.*;

public class Room {

   private int roomNumber;
   private String roomType;
   private int numberOfBeds;
   private int capacity;
   private double price;
   private boolean isAvailable;
   private boolean isClean;
   private ArrayList <Integer> bookingsIds;

   public Room() {
   }

   public Room(int roomNumber, String roomType, int numberOfBeds, int capacity, int cost, boolean isAvailable, boolean isClean) {
      this.roomNumber = roomNumber;
      this.roomType = roomType;
      this.numberOfBeds = numberOfBeds;
      this.capacity = capacity;
      this.price = price;
      this.isAvailable = isAvailable;
      this.isClean = isClean;
      
      bookingsIds = new ArrayList<Integer>();
   }
   
   public Room(String info) {
      Scanner scanner = new Scanner(info);
      this.roomNumber = scanner.nextInt();
      this.roomType = scanner.next();
      this.numberOfBeds = scanner.nextInt();
      this.capacity = scanner.nextInt();
      this.price = scanner.nextDouble();
      this.isAvailable = scanner.nextBoolean();
      this.isClean = scanner.nextBoolean();
      
      bookingsIds = new ArrayList<Integer>();
   }
   
   public int getRoomNumber() {
      return roomNumber;
   }
   
   public void setRoomNumber(int roomNumber) {
      this.roomNumber = roomNumber;
   }
   
   public String getRoomType() {
      return roomType;
   }
   
   public void setRoomType(String roomType) {
      this.roomType = roomType;
   }

   public int getNumberOfBeds() {
      return numberOfBeds;
   }
   
   public void setNumberOfBeds(int numberOfBeds) {
      this.numberOfBeds = numberOfBeds;
   }
   
   public int getCapacity() {
      return capacity;
   }
   
   public void setCapacity(int capacity) {
      this.capacity = capacity;
   }
   
   public double getPrice() {
      return price;
   }
   
   public void setPrice(double price) {
      this.price = price;
   }
   
   public boolean getAvailable() {
      return isAvailable;
   }
   
   public boolean isAvailable(String date, int numOfNights){
      return true;
   }
   
   public void setAvailable(boolean isAvailable) {
      this.isAvailable = isAvailable;
   }

   public boolean getIsClean() {
      return isClean;
   }
   
   public void setIsClean(boolean isClean) {
      this.isClean = isClean;
   }    
   
   public ArrayList<Integer> getBookingsIds(){
      return bookingsIds;
   }
   
   public String toString() {
      return this.roomNumber + " " + this.roomType + " " + this.numberOfBeds + " " + this.capacity + " " + this.price + " " +  this.isAvailable + " " + this.isClean;
   }
   
   public String toStringConsole() {
      return "Room " + this.roomNumber + " is a " + this.roomType + " and has " + this.numberOfBeds + " beds. It can sleep up to " + this.capacity + " and costs " + 
         this.price + " per night. It is available; " + this.isAvailable + " and clean " + this.isClean;
   }
   
   public boolean isAvailable(String newBookingDate, int numberOfNightsFromBookedBooking, String bookedDate){ 
      LocalDate localDate = LocalDate.parse(newBookingDate);
      LocalDate localDate2 = LocalDate.parse(bookedDate);
      LocalDate endDate = localDate2.plusDays(numberOfNightsFromBookedBooking);
      if (localDate.isAfter(localDate2) && localDate.isBefore(endDate) || localDate.isEqual(localDate2)){
         return false;
      }
      return true;
   }


}