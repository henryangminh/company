/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oodbms.company;
import Entities.*;
import Migration.Migration;
import Repositories.*;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author henryangminh
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        Migration migration = new Migration();
        migration.migrate();
        
        EmployeeRepository er = new EmployeeRepository();
        FulltimeEmployeeRepository fer = new FulltimeEmployeeRepository();
        ParttimeEmployeeRepository per = new ParttimeEmployeeRepository();
        TimeKeepingRepository tkr = new TimeKeepingRepository();
        List<FulltimeEmployee> fes = fer.getAll();
        List<ParttimeEmployee> pes = per.getAll();
        List<TimeKeeping> tks = tkr.getAll();
        List<TimeKeeping> tkga = tkr.getAttendance(er.getById(Long.parseLong("1")));
        
        for (FulltimeEmployee fe : fes) {
            System.out.println(fe.toString() + ", Year of Exp: " + fe.calExp());
        }
        
        for (ParttimeEmployee pe : pes) {
            System.out.println(pe.toString());
        }
        
        for (TimeKeeping tk : tks) {
            System.out.println(tk.toString() + ", Working Hours: " + tk.calWorkingHour());
        }
        
        for (TimeKeeping tk : tkga) {
            System.out.println(tk.toString() + ", Working Hours: " + tk.calWorkingHour());
        }
        
//        Query q1 = em.createQuery("SELECT COUNT(p) FROM Person p");
//        System.out.println("Total Points: " + q1.getSingleResult());
    }
}
