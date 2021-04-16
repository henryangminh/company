/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.*;
import Migration.Migration;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author henryangminh
 */
public class TimeKeepingRepository {
    public Migration migration = new Migration();
    
    public List<TimeKeeping> getAll() {
        TypedQuery<TimeKeeping> query;
        query = migration.em.createQuery(
                "SELECT tk FROM TimeKeeping tk", 
                TimeKeeping.class
        );
        return query.getResultList();
    }
    
    public List<TimeKeeping> getAttendance(Employee employee) {
        TypedQuery<TimeKeeping> query;
        query = migration.em.createQuery(
                "SELECT tk FROM TimeKeeping tk WHERE tk.Employee.id = " + employee.getId(), 
                TimeKeeping.class
        );
        return query.getResultList();
    }
}
