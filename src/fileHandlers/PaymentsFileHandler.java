package fileHandlers;


import java.io.*;
import java.util.*;
import data.*;

public class PaymentsFileHandler{
   private final String fileName = "payments.txt";
   private File paymentsFile; 
   
   public PaymentsFileHandler(){
      paymentsFile = new File(fileName);
   }
   
   public void setPaymentsData(ArrayList<Payment> payments){ 
      Scanner scanner = null;
      try {
         scanner = new Scanner(paymentsFile);
      }
      catch (FileNotFoundException e){
         System.out.println(" File not found");
      }
      
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         payments.add(new Payment(line));
      }
   }
   public void updatePaymentsFile(ArrayList<Payment> payments){
      PrintStream output = null ;
      try{
         output = new PrintStream(paymentsFile); 
      }catch (FileNotFoundException e){
         System.out.println("File not found");
      } 
   
      for (int i =0; i<payments.size(); i++){
         output.println(payments.get(i).toString());
      }
   }
}