/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcs.csc311.lab6_tableview;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Brian
 */
public class Country {
    
    private SimpleStringProperty country;
    private SimpleStringProperty capital;
    private SimpleDoubleProperty population;
    
    public Country(String country, String capital, double population) {
        this.country = new SimpleStringProperty(country);
        this.capital = new SimpleStringProperty(capital);
        this.population = new SimpleDoubleProperty(population);
    }
    
    public void setCountry(String country) {
        this.country.set(country);
    }
    
    public String getCountry() {
        return this.country.get();
    }
    
    public void setCapital(String capital) {
        this.capital.set(capital);
    }
    
    public String getCapital() {
        return this.capital.get();
    }
    
    public void setPopulation(double population) {
        this.population.set(population);
    }
    
    public double getPopulation() {
        return this.population.get();
    }

    @Override
    public String toString() {
        return "Country{" + "country=" + country + ", capital=" + capital + ", population=" + population + '}';
    }
    
}
