package fileHandlers;

import java.util.*;
import java.io.*;
import data.*;
public class EmployeesFileHandler{
   private final String filename = "employees.txt"; 
   private File employeesFile;
   
   public EmployeesFileHandler(){
      this.employeesFile = new File(filename);
   }
   
   public void setEmployeesData(ArrayList<Employee> employees){
      Scanner input= null;
      try{
         input = new Scanner (employeesFile);
      }catch (FileNotFoundException e){
         System.out.println("File not found.");
      }
      while(input.hasNextLine()){
         String line = input.nextLine();
         Scanner words = new Scanner (line);
         employees.add(new Employee(words));
      }

   }
   public void updateEmployeesFile(ArrayList<Employee> employees){
      PrintStream output=null;
      try{
         output = new PrintStream(employeesFile);
      }catch (FileNotFoundException e){
         System.out.println("File not found.");
      }
      for (int i =0; i<employees.size(); i++){
         output.println(employees.get(i).toString());
      }  
   }
}