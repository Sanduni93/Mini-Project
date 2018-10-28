
package main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTableProduct {
private final StringProperty productid;
private final StringProperty productname;
private final StringProperty quantity;
private final StringProperty pprice;
private final StringProperty sprice;
private final StringProperty supname;
private final StringProperty brand;
private final StringProperty category;
private final StringProperty rma;
private final StringProperty date;
 
public ModelTableProduct(String productid, String productname, String quantity, String pprice, String sprice, String supname, String brand, String category, String rma, String date){
this.productid = new SimpleStringProperty(productid);
this.productname = new SimpleStringProperty(productname);
this.quantity = new SimpleStringProperty(quantity);
this.pprice = new SimpleStringProperty(pprice);
this.sprice = new SimpleStringProperty(sprice);
this.supname = new SimpleStringProperty(supname);
this.brand = new SimpleStringProperty(brand);
this.category = new SimpleStringProperty(category);
this.rma = new SimpleStringProperty(rma);
this.date = new SimpleStringProperty(date);
}
//getters
public String getProductId(){
return productid.get();
}
public String getProductName(){
return productname.get();
}
public String getQuantity(){
return quantity.get();
}
public String getPPrice(){
return pprice.get();
}
public String getSPrice(){
return sprice.get();
}
public String getSupName(){
return supname.get();
}
public String getBrand(){
return brand.get();
}
public String getCategory(){
return category.get();
}
public String getRma(){
return rma.get();
}
public String getDate(){
return date.get();
}

//setters
public void setProductId(String value){
productid.set(value);
}
public void setProductName(String value){
productname.set(value);
}
public void setQuantity(String value){
quantity.set(value);
}
public void setPPrice(String value){
pprice.set(value);
}
public void setSPrice(String value){
sprice.set(value);
}
public void setSupName(String value){
supname.set(value);
}
public void setBrand(String value){
brand.set(value);
}
public void setCategory(String value){
category.set(value);
}
public void setRma(String value){
rma.set(value);
}
public void setDate(String value){
date.set(value);
}
//property values
public StringProperty productidProperty(){
return productid;
}
public StringProperty productnameProperty(){
return productname;
}
public StringProperty quantityProperty(){
return quantity;
}
public StringProperty ppriceProperty(){
return pprice;
}
public StringProperty spriceProperty(){
return sprice;
}
public StringProperty supnameProperty(){
return supname;
}
public StringProperty brandProperty(){
return brand;
}
public StringProperty categoryProperty(){
return category;
}
public StringProperty rmaProperty(){
return rma;
}
public StringProperty dateProperty(){
return date;
}
}
