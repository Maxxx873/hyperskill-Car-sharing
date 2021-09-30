package carsharing;


import carsharing.controller.Menu;
import carsharing.dao.h2.CarDaoH2Impl;
import carsharing.dao.h2.CompanyDaoH2Impl;
import carsharing.dao.h2.ControllerH2;

import java.io.File;

public class Main {

    public static void main(String[] args) {

       ControllerH2 controllerH2 = new ControllerH2(getDbName(args));
       CompanyDaoH2Impl companyDaoH2 = new CompanyDaoH2Impl(controllerH2.getConnection());
       CarDaoH2Impl carDaoH2 = new CarDaoH2Impl(controllerH2.getConnection());


       for(;;) {
          Menu.mainMenu();
           switch (Menu.userChoice()) {
               case 1:
                   Menu.managementCompany(companyDaoH2, carDaoH2);
                   break;
               case 0:
                   controllerH2.closeConnection();

                   return;
           }
       }
    }


    private static String getDbName(String[] args) {
        if (args.length >= 2 && args[0].equals("-databaseFileName")) {
            return args[1];
        } else {
            return "carsharing";
        }
    }
}