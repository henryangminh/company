/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author henryangminh
 */
@Entity
public class TimeKeeping implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private Date CheckIn;
    private Date CheckOut;
    
    @ManyToOne()
    @JoinColumn(name = "Employee")
    Employee Employee;
    
    public long calWorkingHour() {
        return CheckOut.getTime() - CheckIn.getTime();
    }
    
    public TimeKeeping(Employee Employee, Date CheckIn, Date CheckOut) {
        this.Employee = Employee;
        this.CheckIn = CheckIn;
        this.CheckOut = CheckOut;
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
        if (!(object instanceof TimeKeeping)) {
            return false;
        }
        TimeKeeping other = (TimeKeeping) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Employee Id: " + Employee.id + 
                ", Check In: " + CheckIn.toString() + 
                ", Check Out: " + CheckOut.toString();
    }
    
}
