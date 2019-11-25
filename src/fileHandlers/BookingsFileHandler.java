package fileHandlers;

import java.io.*;
import java.util.*;
import data.*; 
public class BookingsFileHandler{
   private final String fileName = "bookings.txt";
   private File bookingsFile; 
   
   public BookingsFileHandler(){
      bookingsFile = new File(fileName);
   }
   public void setBookingsData(ArrayList<Booking> bookings){ 
      Scanner scanner = null;
      try {
         scanner = new Scanner(bookingsFile);
      }catch (FileNotFoundException e){
         System.out.println("File not found.");
      }
      
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         bookings.add(new Booking(line));
      }
   }
   
   public void updateBookingsFile(ArrayList<Booking> bookings){
      PrintStream output = null;
      try {
         output = new PrintStream(bookingsFile); 
      }catch (FileNotFoundException e){
         System.out.println("File not found");
      } 
      for (int i =0; i<bookings.size(); i++){
         output.println(bookings.get(i).toString());
      }
   }

   



}