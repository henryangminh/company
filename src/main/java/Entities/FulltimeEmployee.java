/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;

/**
 *
 * @author henryangminh
 */
@Entity
public class FulltimeEmployee extends Employee {
    private Integer HourlyRate;
    public Integer getHourlyRate() {
        return HourlyRate;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.HourlyRate = hourlyRate;
    }
    
    private Date StartDate;
    public Date getStartDate() {
        return StartDate;
    }
    
    public Integer calExp() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Integer yearNow = calendar.get(Calendar.YEAR); 
        calendar.setTime(StartDate);
        Integer yearStart = calendar.get(Calendar.YEAR);
        return yearNow - yearStart;
    }
    
    public FulltimeEmployee(String Name, Date BirthDate, Integer HourlyRate, Date StartDate) {
        super(Name, BirthDate);
        this.HourlyRate = HourlyRate;
        this.StartDate = StartDate;
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
        if (!(object instanceof FulltimeEmployee)) {
            return false;
        }
        FulltimeEmployee other = (FulltimeEmployee) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Hourly Rate: " + HourlyRate.toString() +
                ", Start Date: " + StartDate.toString();
    }
    
}
