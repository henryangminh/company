/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.*;
import Migration.Migration;
import java.text.ParseException;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    
    public List<TimeKeeping> getAttendance(Employee employee, Integer month, Integer year) throws ParseException {
        CriteriaBuilder cb = migration.em.getCriteriaBuilder();

        CriteriaQuery<TimeKeeping> q = cb.createQuery(TimeKeeping.class);
        Root<TimeKeeping> c = q.from(TimeKeeping.class);
        q.select(c).where(
                cb.equal(c.get("Employee").get("id"), employee.getId()),
                cb.equal(cb.function("MONTH", Integer.class, c.get("CheckIn")), month),
                cb.equal(cb.function("YEAR", Integer.class, c.get("CheckIn")), year)
        );

        TypedQuery<TimeKeeping> query = migration.em.createQuery(q);
        List<TimeKeeping> results = query.getResultList();
        return results;
    }
    
    public List<Object[]> getAttendace(Integer month, Integer year) {
        TypedQuery<Object[]> query;
        query = migration.em.createQuery(
                "select Employee, sum(tk.CheckOut.getTime() - tk.CheckIn.getTime()) " +
                "from TimeKeeping tk " +
                "where MONTH(tk.CheckIn) = " + month + " and YEAR(tk.CheckIn) = " + year + " " +
                "group by Employee", 
                Object[].class
        );
        
        return query.getResultList();
    }
}
