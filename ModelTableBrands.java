
package main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ModelTableBrands {
    
   private final StringProperty brandid;
    private final StringProperty brandname;
    private final StringProperty supname;
    private final StringProperty date;
    private final StringProperty des;

    //default constructor
    public ModelTableBrands(String brandid,String brandname, String supname, String date, String des) {
        this.brandid = new SimpleStringProperty(brandid);
        this.brandname = new SimpleStringProperty(brandname);
        this.supname = new SimpleStringProperty(supname);
        this.date = new SimpleStringProperty(date);
        this.des = new SimpleStringProperty(des);

    }

    //getters
      public String getBrandId() {
        return brandid.get();
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
    public void setBrandId(String value) {
        brandid.set(value);
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
    public StringProperty brandidProperty() {
        return brandid;
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
