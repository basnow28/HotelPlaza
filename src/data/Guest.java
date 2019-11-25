package data;

import java.util.*;

public class Guest extends Person {

   public Guest() {
   }
   
   public Guest(String firstName, String lastName, String phoneNumber, Address address) {
      super(firstName, lastName, phoneNumber, address);
   }
   
   public String toString() {
      return super.toString();
   }
   
   public String toStringConsole() {
      return super.toStringConsole();
   }  
}