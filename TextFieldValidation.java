
package Validation;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class TextFieldValidation {
 
   public static boolean isTextFieldNotEmpty(TextField tf){
   
       boolean b = false;
       if(tf.getText().length() != 0 || !tf.getText().isEmpty())
       b=true;
       return b;
    } 
    
     public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
   
       boolean b = true;
       String msg = null;
       if(!isTextFieldNotEmpty(tf)){
       b = false;
       msg = errorMessage;
       }
       lb.setText(msg);
       if(tf.getText().length() != 0 || !tf.getText().isEmpty())
       b=true;
       return b;
    }  
     
       public static boolean isTextFieldTypeNumber(TextField tf){
   
       boolean b = false;
       if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))
       b=true;
       return b;
    }  
     
         public static boolean isTextFieldTypeNumber(TextField tf,Label lb, String errorMessage){
   
       boolean b = true;
       String msg = null;
       if(!isTextFieldTypeNumber(tf)){
         b=true;
         msg = errorMessage;
       }
       lb.setText(msg);
       return b;
    } 
         
         
             public static boolean isTextFieldTypePhoneNumber(TextField tf){
   
       boolean b = false;
       if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+")&&tf.getText().length()==10)
       b=true;
       return b;
    }  
     
         public static boolean isTextFieldTypePhoneNumber(TextField tf,Label lb, String errorMessage){
   
       boolean b = true;
       String msg = null;
       if(!isTextFieldTypePhoneNumber(tf)){
         b=true;
         msg = errorMessage;
       }
       lb.setText(msg);
       return b;
    } 
     
     
  
   public static boolean isTextAreaNotEmpty(TextArea ta){
   
       boolean b = false;
       if(ta.getText().length() != 0 || !ta.getText().isEmpty())
       b=true;
       return b;
    } 
   
     public static boolean isTextAreaNotEmpty(TextArea ta, Label lb, String errorMessage){
   
       boolean b = true;
       String msg = null;
       if(!isTextAreaNotEmpty(ta)){
       b = false;
       msg = errorMessage;
       }
       lb.setText(msg);
       if(ta.getText().length() != 0 || !ta.getText().isEmpty())
       b=true;
       return b;
    } 
     
     public static boolean isPasswordFieldNotEmpty(PasswordField pwf){
   
       boolean b = false;
       if(pwf.getText().length() != 0 || !pwf.getText().isEmpty())
       b=true;
       return b;
    }
     
        public static boolean isPasswordFieldNotEmpty(PasswordField pwf, Label lb, String errorMessage){
   
       boolean b = true;
       String msg = null;
       if(!isPasswordFieldNotEmpty(pwf)){
       b = false;
       msg = errorMessage;
       }
       lb.setText(msg);
       if(pwf.getText().length() != 0 || !pwf.getText().isEmpty())
       b=true;
       return b;
    }

        
 }
     
     

 

   
    

