/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Migration;

import Entities.FulltimeEmployee;
import Entities.ParttimeEmployee;
import Entities.TimeKeeping;
import Repositories.EmployeeRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.persistence.*;

/**
 *
 * @author henryangminh
 */
public class Migration {
    private EntityManagerFactory emf;
    public EntityManager em;
    
    public Migration() { 
        this.emf = Persistence.createEntityManagerFactory("$objectdb/db/company.odb");
        this.em = emf.createEntityManager();
    }
    
    public void migrate() throws ParseException {
        em.getTransaction().begin();
        
        em.getMetamodel().entity(FulltimeEmployee.class);
        em.getMetamodel().entity(ParttimeEmployee.class);
        em.getMetamodel().entity(TimeKeeping.class);
        
        em.getTransaction().commit();
        
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd,hh,mm,ss");
        
        /**
         * Import FulltimeEmployee
         */
        em.getTransaction().begin();
        try {
            File myObj = new File("./src/main/java/Migration/fulltimeemployee.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tokens = data.split(";");
                FulltimeEmployee fe = new FulltimeEmployee(
                        tokens[0], 
                        format.parse(tokens[1]), 
                        Integer.parseInt(tokens[2]), 
                        format.parse(tokens[3])
                );
                
                em.persist(fe);
            }
            myReader.close();
            em.getTransaction().commit();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        /**
         * Import ParttimeEmployee
         */
        em.getTransaction().begin();
        try {
            File myObj = new File("./src/main/java/Migration/parttimeemployee.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tokens = data.split(";");
                ParttimeEmployee pe = new ParttimeEmployee(
                        tokens[0], 
                        format.parse(tokens[1]), 
                        Integer.parseInt(tokens[2])
                );
                
                em.persist(pe);
            }
            myReader.close();
            em.getTransaction().commit();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        /**
         * Import TimeKeeping
         */
        em.getTransaction().begin();
        try {
            File myObj = new File("./src/main/java/Migration/timekeeping.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tokens = data.split(";");
                EmployeeRepository er = new EmployeeRepository();
                TimeKeeping tk = new TimeKeeping(
                        er.getById(Long.parseLong(tokens[0])), 
                        format.parse(tokens[1]), 
                        format.parse(tokens[2])
                );
                
                em.persist(tk);
            }
            myReader.close();
            em.getTransaction().commit();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}
