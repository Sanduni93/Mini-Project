package main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTableSuppliers {
    private final StringProperty supid;
    private final StringProperty supname;
    private final StringProperty supconnum;
    private final StringProperty supadd;
    private final StringProperty des;

    //default constructor
    public ModelTableSuppliers(String supid,String supname, String supconnum, String supadd, String des) {
        this.supid = new SimpleStringProperty(supid);
        this.supname = new SimpleStringProperty(supname);
        this.supconnum = new SimpleStringProperty(supconnum);
        this.supadd = new SimpleStringProperty(supadd);
        this.des = new SimpleStringProperty(des);

    }

    //getters
      public String getSupId() {
        return supid.get();
    }
    
    public String getSupName() {
        return supname.get();
    }

    public String getSupConNum() {
        return supconnum.get();
    }

    public String getSupAdd() {
        return supadd.get();
    }

    public String getDes() {
        return des.get();
    }

    //setters
    public void setSupId(String value) {
        supid.set(value);
    }
    public void setSupName(String value) {
        supname.set(value);
    }

    public void setSupConNum(String value) {
        supconnum.set(value);
    }

    public void setSupAdd(String value) {
        supadd.set(value);
    }

    public void setDes(String value) {
        des.set(value);
    }

    //property values
    public StringProperty supidProperty() {
        return supid;
    }
    public StringProperty supnameProperty() {
        return supname;
    }

    public StringProperty supconnumProperty() {
        return supconnum;
    }

    public StringProperty supaddProperty() {
        return supadd;
    }

    public StringProperty desProperty() {
        return des;
    }
    
    

}
