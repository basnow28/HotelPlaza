package data;
import java.util.*;

public class Employee extends Person{
   private static int count = 0;
   
   private String employeeType;
   private int employeeNumber;
   private int salary;
   
   public Employee (){
      this.employeeNumber = count;
      count++;
   }
   
   public Employee (Scanner words){ 
      super(words.next(), words.next(), words.next(), new Address(words.next(), words.next(), words.next(), words.next(), words.next(), words.next()));
      while(words.hasNext()){
         this.employeeType = words.next();
         this.employeeNumber = words.nextInt();
         this.salary = words.nextInt();
         count++;
      }   
   }
   public Employee(String firstName, String lastName, String phoneNumber, Address address, String employeeType, int Salary) {
      super(firstName, lastName, phoneNumber, address);
      this.employeeType = employeeType;
      this.employeeNumber = count;
      this.salary = salary;
      count++;
   }
   public String getEmployeeType() {
      return employeeType;
   }
   
   public void setEmployeeType(String employeeType) {
      this.employeeType = employeeType;
   }
   
   public int getEmployeeNumber() {
      return employeeNumber;
   }
   
   public static void setCount(int newCount){
      count = newCount;
      System.out.println(count);
   }
      
   public void setEmployeeNumber(int employeeNumber) {
      this.employeeNumber = employeeNumber;
   }
   public int getSalary() {
      return salary;
   }
   public void setSalary(int salary) {
      this.salary = salary;
   }
   public String toString(){
      return super.toString() + " " + this.employeeType + " " + this.employeeNumber + " " + this.salary;
   }
   public String toStringConsole(){
      return super.toStringConsole() + ". Employee type:" + this.employeeType + ". Employee number:" + this.employeeNumber + ". Salary: " + this.salary;
   }
}