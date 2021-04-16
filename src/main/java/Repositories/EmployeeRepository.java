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
public class EmployeeRepository {
    public Migration migration = new Migration();
    
    public List<Employee> getAll() {
        TypedQuery<Employee> query;
        query = migration.em.createQuery(
                "SELECT e FROM Employee e", 
                Employee.class
        );
        return query.getResultList();
    }
    
    public Employee getById(Long id) {
        TypedQuery<Employee> query;
        query = migration.em.createQuery(
                "SELECT e FROM Employee e WHERE e.id = " + id.toString(), 
                Employee.class
        );
        return query.getSingleResult();
    }
}
