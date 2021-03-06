/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author henryangminh
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    String Name;
    public String getName() {
        return Name;
    }
    
    Date BirthDate;
    public Date getBirthDate() {
        return BirthDate;
    }
    
    public Integer calAge() {
        return LocalDate.now().getYear() - BirthDate.getYear();
    }
    
    public Employee(String Name, Date BirthDate) {
        this.Name = Name;
        this.BirthDate = BirthDate;
    }
    
    @OneToMany(mappedBy = "Employee", cascade = CascadeType.ALL)
    private Set<TimeKeeping> TimeKeepings;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "id: " + id + 
                ", Name: " + Name + 
                ", Birth Date: " + BirthDate.toString();
    }
    
}
