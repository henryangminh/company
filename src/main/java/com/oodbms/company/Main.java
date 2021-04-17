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
        
        for (FulltimeEmployee fe : fes) {
            System.out.println(fe.toString() + ", Year of Exp: " + fe.calExp());
        }
        
        for (ParttimeEmployee pe : pes) {
            System.out.println(pe.toString());
        }
        
        for (TimeKeeping tk : tks) {
            System.out.println(tk.toString() + ", Working Hours: " + tk.calWorkingHour());
        }
        
        
        System.out.println("Calculate salary for employee with id=1 in April 2021");
        FulltimeEmployee e = fer.getById(Long.parseLong("1"));
        List<TimeKeeping> tkga = tkr.getAttendance(e,4,2021);
        long workingHours = 0;
        for (TimeKeeping tk : tkga) {
            System.out.println(tk.toString() + ", Working Hours: " + tk.calWorkingHour());
            workingHours += tk.calWorkingHour();
        }
        long salary = (workingHours / (1000 * 60 * 60)) * e.getHourlyRate();
        System.out.println("Salary of employee id = 1 in April 2021 is: " + salary);
        
        System.out.println("Calculate salary for all employee in April 2021");
        List<Object[]> tkall = tkr.getAttendace(4,2021);
        for (Object[] tk : tkall) {
            FulltimeEmployee fe = (FulltimeEmployee) tk[0];
            System.out.println(
                    "Employee Id: " + fe.getId() + 
                    " , salary: " + 
                    (Long.parseLong(tk[1].toString()) / (1000 * 60 * 60)) * fe.getHourlyRate()
            );
        }
    }
}
