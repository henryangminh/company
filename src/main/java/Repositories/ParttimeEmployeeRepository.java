/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.ParttimeEmployee;
import Migration.Migration;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author henryangminh
 */
public class ParttimeEmployeeRepository {
    public Migration migration = new Migration();
    
    public List<ParttimeEmployee> getAll() {
        TypedQuery<ParttimeEmployee> query;
        query = migration.em.createQuery(
                "SELECT pe FROM ParttimeEmployee pe", 
                ParttimeEmployee.class
        );
        return query.getResultList();
    }
}
