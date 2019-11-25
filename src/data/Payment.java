package data;
import java.util.*;
import java.io.*;

public class Payment {
   private static  int count = 0;
   
   private int paymentId;
   private String date;
   private String paymentType; // CASH, CARD
   private double amount;
   
   public Payment(String line) {
      Scanner scan = new Scanner(line);
      this.paymentId = scan.nextInt();
      this.date = scan.next();
      this.paymentType = scan.next();
      this.amount = scan.nextDouble();
      
      count++;
   }
   
   public Payment(){
   }
   
   
   public Payment(String date,String paymentType,double amount){
      this.paymentId = count;
      this.date=date;
      this.paymentType=paymentType;
      this.amount=amount;
      
      count++;
   }
   public int getPayemntId(){
      return paymentId;
   }
   public void setPaymentId (int paymentId){
      this.paymentId= paymentId;
   }
   
   
   public String getDate() {
      return date;
   }
   
   public void setDate(String date) {
      this.date = date;
   }
   
   public String getPaymentType() {
      return paymentType;
   }
   
   public void setPaymentType(String paymentType) {
      this.paymentType=paymentType;
   }
   
   public double getAmount() {
      return amount;
   }
   public void setAmount( double amount){
      this.amount=amount;
   }
   
   public String toString() {
      return this.paymentId + " " + this.date + " "  + this.paymentType + " " + this.amount ;
   }
   
   public String toStringConsole(){
      return "PaymentID: " + paymentId + "\nPayment Type: " + paymentType + "\nAmount: " + amount;
   }
}