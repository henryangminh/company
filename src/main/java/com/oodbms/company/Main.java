/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oodbms.company;
import javax.persistence.*;
import Entities.*;

/**
 *
 * @author henryangminh
 */
public class Main {
    public static void main(String[] args) {
        //Person p;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/company.odb");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        //p = new Person("Minh", 1998);
        //em.persist(p);
        em.getMetamodel().entity(FulltimeEmployee.class);
        
        em.getTransaction().commit();
        
//        Query q1 = em.createQuery("SELECT COUNT(p) FROM Person p");
//        System.out.println("Total Points: " + q1.getSingleResult());
    }
}
