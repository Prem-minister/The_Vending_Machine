/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.the_vending_machine;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author premsharaan
 */
class VenStaffDocumentListener implements DocumentListener {
    
    @Override
    public void insertUpdate(DocumentEvent e) {
       
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
       
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
      
       
    }
}


//This abstract class contains the fundamental methods required of a input validation
abstract class StringValidation {
    
    private String captionTitle, outputMsg, regex, negRegex;
    
    public StringValidation() {
        captionTitle = "Invalid input type!";
        outputMsg = "Textfields can only consists of letters, numbers and spacing.";
        regex = "^[a-zA-Z0-9 ]+";
        negRegex = "[^a-zA-Z0-9 ]";
    }
    
    public StringValidation(String captionTitle, String outputMsg, String regex, String negRegex) {
        this.captionTitle = captionTitle;
        this.outputMsg = outputMsg;
        this.regex = regex;
        this.negRegex = negRegex;
    }
    
    public StringValidation(String outputMsg, String regex, String negRegex) {
        captionTitle = "Invalid text input entered!";
        this.outputMsg = outputMsg;
        this.regex = regex;
        this.negRegex = negRegex;
    }
    
    public void setRegex(String regex){
        this.regex = regex;
    }
    
    public void setNegateRegex(String negRegex){
        this.negRegex = negRegex;
    }
    
    public void setPopup(String captionTitle, String outputMsg){
        this.captionTitle = captionTitle;
        this.outputMsg = outputMsg;
    }
    
    public String getRegex(){
        return regex;
    }
    
    public String getNegateRegex(){
        return negRegex;
    }
    
    public String getTitle(){
        return captionTitle;
    }
    
    public String getMessage(){
        return outputMsg;
    }
    
    public void runPopup(){
        JOptionPane.showMessageDialog(null, outputMsg, captionTitle, JOptionPane.WARNING_MESSAGE);
    }
    
    public void runValidate(JTextField txt){
        Runnable doDelete = new Runnable(){
            public void set(){
                String input = txt.getText();
                boolean matching = input.matches(getRegex());
                if (!matching && !"".equals(input)) {
                    runPopup();
                    String output = input.replaceAll(getNegateRegex(), "");
                    txt.setText(output);
                }
            }
            @Override
            public void run(){
                set();
            }
        };
        SwingUtilities.invokeLater(doDelete);
    }
    
}

// This will handles the subclass for username validation
class StaffUsernameValidation extends StringValidation {
       public StaffUsernameValidation() {
        super();
        setPopup("Entered invalid  staff username!", "Username can only consist of letters, numbers and escaped symbols.");
        setRegex("^[-a-zA-Z0-9!@#$%^&*()\\{\\}\\[\\]\"\';\\\\/?|.,><~`_+=]+");
        setNegateRegex("[^-a-zA-Z0-9!@#$%^&*()\\{\\}\\[\\]\"\';\\\\/?|.,><~`_+=]");
    }
}

// This will handles the subclass for password validation
class StaffPasswordValidation extends StringValidation {
      public StaffPasswordValidation() {
        super();
        setPopup("Entered invalid staff password!", "Password can only consist of letters, numbers and escaped symbols.");
        setRegex("^[-a-zA-Z0-9!@#$%^&*()\\{\\}\\[\\]\"\';\\\\/?|.,><~`_+=]+");
        setNegateRegex("[^-a-zA-Z0-9!@#$%^&*()\\{\\}\\[\\]\"\';\\\\/?|.,><~`_+=]");
    }
    
    public void runValidate(JPasswordField txt){
        Runnable doDelete = new Runnable(){
            public void set(){
                String input = String.valueOf(txt.getPassword());
                boolean matching = input.matches(getRegex());
                if (!matching && !"".equals(input)) {
                    runPopup();
                    String output = input.replaceAll(getNegateRegex(), "");
                    txt.setText(output);
                }
            }
            @Override
            public void run(){
                set();
            }
        };
        SwingUtilities.invokeLater(doDelete);
    }
}

// This will handles subclass validation for email
class StaffEmailValidation extends StringValidation {
    public StaffEmailValidation() {
        super();
        setPopup("Invalid staff's email address!", "Please follow the email format. (eg: abc@gmail.com)");
        setRegex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
    
    public boolean runValidate(JTextField txt, boolean dispenseMessage){
        boolean invalidEmail = false;
        String input = txt.getText();
        boolean matching = input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        if (matching == false && !"".equals(input)) {
            if (dispenseMessage) {
                runPopup();
            }
            String output = "";
            txt.setText(output);
            invalidEmail = true;
        };
        return invalidEmail;
    }
    
}



// This subclass handles validation for full name
class StaffFullNameValidation extends StringValidation {
    public StaffFullNameValidation() {
        super();
        setPopup("Invalid staff's full name!", "Full Name can only consists of letters and spacing.");
        setRegex("^[a-zA-Z ]+");
        setNegateRegex("[^a-zA-Z ]");
    }
}

 // This subclass handles validation for item name
   class ItemNameValidation extends StringValidation {
    public ItemNameValidation() {
        super();
        setPopup("Invalid item name input!", "Item name only can consists letters, numbers and spaces. (eg: 100plus)");
        setRegex("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$");
    }
    
       
    public boolean runValidate(JTextField txt, boolean dispenseMessage){
        boolean invalidQty = false;
        String input = txt.getText();
        boolean matching = input.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$");
        if (matching == false && !"".equals(input)) {
            if (dispenseMessage) {
                runPopup();
            }
            String output = "";
            txt.setText(output);
            invalidQty = true;
        };
        return invalidQty;
    }
    

   }

   // This subclass handles validation for inserted item price
   class ItemPriceValidation extends StringValidation {
    public ItemPriceValidation() {
        super();
        setPopup("Invalid item price input!", "Item price only can consists numbers and a single dot. (eg: 12.00)");
        setRegex("\\d*\\.\\d*");
    }
    
       
    public boolean runValidate(JTextField txt, boolean dispenseMessage){
        boolean invalidPrice = false;
        String input = txt.getText();
        boolean matching = input.matches("\\d*\\.\\d*");
        if (matching == false && !"".equals(input)) {
            if (dispenseMessage) {
                runPopup();
            }
            String output = "";
            txt.setText(output);
            invalidPrice = true;
        };
        
        return invalidPrice;
    
    }
    
   }


   class ItemQuantityValidation extends StringValidation {
   public ItemQuantityValidation() {
        super();
        setPopup("Invalid item quantity input!", "Item quantity only can consist numbers and not more than 25 quantities.");
        setRegex("^([1-9]|1[0-9]|2[0-5])$");
    }
         
    public boolean runValidate(JTextField txt, boolean dispenseMessage){
        boolean invalidQty = false;
        String input = txt.getText();
        boolean matching = input.matches("^([1-9]|1[0-9]|2[0-5])$");
        if (matching == false && !"".equals(input)) {
            if (dispenseMessage) {
                runPopup();
            }
            String output = "";
            txt.setText(output);
            invalidQty = true;
        };
        return invalidQty;
    }
    
   }




