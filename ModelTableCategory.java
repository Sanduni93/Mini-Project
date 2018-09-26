
package main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTableCategory {
 
    private final StringProperty categoryid;
    private final StringProperty categoryname;
    private final StringProperty brandname;
    private final StringProperty supname;
    private final StringProperty date;  
    private final StringProperty des;  
    
     //default constructor
    public ModelTableCategory(String categoryid,String categoryname, String brandname,String supname, String date, String des) {
        this.categoryid = new SimpleStringProperty(categoryid);
        this.categoryname = new SimpleStringProperty(categoryname);
        this.brandname = new SimpleStringProperty(brandname);
        this.supname = new SimpleStringProperty(supname);
        this.date = new SimpleStringProperty(date);
        this.des = new SimpleStringProperty(des);

    }
    
     //getters
      public String getCategoryId() {
        return categoryid.get();
    }
    
    public String getCategoryName() {
        return categoryname.get();
    }
     public String getBrandName() {
        return brandname.get();
    }
    
    public String getSupName() {
        return supname.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getDes() {
        return des.get();
    }
    
    //setters
    public void setCategoryId(String value) {
        categoryid.set(value);
    }
      public void setCategoryName(String value) {
        categoryname.set(value);
    }
    public void setBrandName(String value) {
        brandname.set(value);
    }

    public void setSupName(String value) {
        supname.set(value);
    }

    public void setDate(String value) {
        date.set(value);
    }

    public void setDes(String value) {
        des.set(value);
    }
    
     //property values
    public StringProperty categoryidProperty() {
        return categoryid;
    }
     public StringProperty categorynameProperty() {
        return categoryname;
    }
    public StringProperty brandnameProperty() {
        return brandname;
    }

    public StringProperty supnameProperty() {
        return supname;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty desProperty() {
        return des;
    }

    
}
