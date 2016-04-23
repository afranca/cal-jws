/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author gauchoescoces
 */
public class Expense extends ModelTemplate{
    
    private double value;
    private Date  date;
    private String description;
  
    public Expense() {
        this.date = new Date();
    }        
    
    public Expense(String name, double value) {
        this.name = name;
        this.value = value;
        this.date = new Date();
        this.description = " no description";
    }    
    
    public Expense(String name, double value, String description) {
        this.name = name;
        this.value = value;
        this.date = new Date();
        this.description = description;
    }
    

    public Expense(String name, double value, Date date, String description) {
        this.name = name;
        this.value = value;
        this.date = date;
        this.description = description;
    }

       
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
            
    
}
