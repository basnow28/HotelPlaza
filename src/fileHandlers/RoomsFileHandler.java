package fileHandlers;

import java.util.*;
import java.io.*;
import data.*;

public class RoomsFileHandler{
   private final String roomsFileName = "rooms.txt";
   private static ArrayList<String> roomsFilesNames;
   
   public RoomsFileHandler(){
      roomsFilesNames = new ArrayList<String>();
      getRoomsFilesNames();
   }
   
   public void setRoomsData(ArrayList<Room> rooms){  
      
      for(String roomFileName : roomsFilesNames){  
         Scanner scanner = null;
         try {
            scanner = new Scanner(new File(roomFileName));
         } catch (FileNotFoundException e) {
            System.out.println("Cannot find room file");
         }
         String line = scanner.nextLine();
         rooms.add(new Room(line));
         
         while(scanner.hasNextLine()){
            rooms.get(rooms.size() - 1).getBookingsIds().add(Integer.parseInt(scanner.nextLine()));
         }
      }
   }
   
   
   private void getRoomsFilesNames(){
      File roomsFile = new File(roomsFileName);
      Scanner scanner = null;
        
      try {
         scanner = new Scanner(roomsFile);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
         
      while(scanner.hasNext()){
         roomsFilesNames.add("room" + scanner.next() + ".txt");
      }
   }
   
   
   /////////////New code 19.11 evening - Barbara
   public void updateRoomData(Room room){
      PrintStream ps = null;
      
      try{
         ps = new PrintStream(new File("room" + room.getRoomNumber() + ".txt"));
      }
      catch(FileNotFoundException e){
         System.out.println("File not found");
      }
      
      if(ps != null){
         ps.println(room.toString());
         
         for(Integer bookingId : room.getBookingsIds()){
            ps.println(bookingId);
         }
      }
   }
   
}