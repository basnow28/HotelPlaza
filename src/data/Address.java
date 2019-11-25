package data;

public class Address{
   private String streetName;
   private String streetNumber;
   private String apartmentNumber;
   private String zipCode;
   private String neighborhood;
   private String country;
   
   public Address(){}
   
   public Address(String streetName, String streetNumber, String apartmentNumber, String zipCode, String neighborhood, String country){
      this.streetName = streetName;
      this.streetNumber = streetNumber;
      this.apartmentNumber = apartmentNumber;
      this.zipCode = zipCode;
      this.neighborhood = neighborhood;
      this.country = country; 
   }
   public void setStreetName(String streetName){
      this.streetName = streetName;
   }
   public String getStreetName(){
      return streetName;
   }
   public void setStreetNumber(String streetNumber){
      this.streetNumber = streetNumber;
   }
   public String getStreetNumber(){
      return streetNumber;
   }
   public void setApartmentNumber(String apartmentNumber){
      this.apartmentNumber = apartmentNumber;
   }
   public String getApartmentNumber(){
      return apartmentNumber;
   }
   public void setZipCode(String zipCode){
      this.zipCode = zipCode;
   }
   public String getZipCode(){
      return zipCode;
   }
   public void setNeighborhood(String neighborhood){
      this.neighborhood = neighborhood;
   }
   public String getNeighborhood(){
      return neighborhood;
   }
   public void setCountry(String country){
      this.country = country;
   }
   public String getCountry(){
      return country;
   }
   public String toString(){
      return this.streetName + " " + this.streetNumber + " " + this.apartmentNumber + " " + this.zipCode + " " 
         + this.neighborhood + " " + this.country;
   }
}