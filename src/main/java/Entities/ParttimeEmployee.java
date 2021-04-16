/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author henryangminh
 */
@Entity
public class ParttimeEmployee extends Employee {
    
    private Integer Wage;
    public Integer getWage() {
        return Wage;
    }
    
    public void setWage(Integer Wage) {
        this.Wage = Wage;
    }
    
    public ParttimeEmployee(String Name, Date BirthDate, Integer Wage) {
        super(Name, BirthDate);
        this.Wage = Wage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParttimeEmployee)) {
            return false;
        }
        ParttimeEmployee other = (ParttimeEmployee) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Wage: " + Wage.toString();
    }
}
