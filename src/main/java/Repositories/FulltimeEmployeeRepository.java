/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.FulltimeEmployee;
import Migration.Migration;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author henryangminh
 */
public class FulltimeEmployeeRepository {
    public Migration migration = new Migration();
    
    public List<FulltimeEmployee> getAll() {
        TypedQuery<FulltimeEmployee> query;
        query = migration.em.createQuery(
                "SELECT fe FROM FulltimeEmployee fe", 
                FulltimeEmployee.class
        );
        return query.getResultList();
    }
    
    public FulltimeEmployee getById(Long id) {
        TypedQuery<FulltimeEmployee> query;
        query = migration.em.createQuery(
                "SELECT fe FROM FulltimeEmployee fe WHERE fe.id = " + id.toString(), 
                FulltimeEmployee.class
        );
        return query.getSingleResult();
    }
}
