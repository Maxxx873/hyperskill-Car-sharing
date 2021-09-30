package carsharing.controller;

import carsharing.dao.h2.CarDaoH2Impl;
import carsharing.dao.h2.CompanyDaoH2Impl;
import carsharing.dao.h2.ControllerH2;
import carsharing.entitie.Car;

import java.util.Scanner;


public class Menu {

    public static final Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
    }

    private static void managerMenu() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    private static void companyMenu() {
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
    }

    public static void managementCar(CarDaoH2Impl carDaoH2, CompanyDaoH2Impl companyDaoH2, int i) {
        Scanner scanner = new Scanner(System.in);
        if (i > 0) {
            String companyName = companyDaoH2.getAllCompanies().get(i - 1).getName();
            System.out.println("'" + companyName + "' company");
        } else {
            return;
        }

        for(;;) {
            if (companyDaoH2.getAllCompanies().size() >= i) {
                companyMenu();
                switch (userChoice()) {
                    case 0:
                        return;
                    case 1:
                        if(carDaoH2.getCars(i).size() == 0) {
                            System.out.println("The car list is empty!\n");
                        } else {

                            System.out.println("Car list:");
                            int id = 1;
                            for (Car car : carDaoH2.getCars(i)) {
                                System.out.println(id + ". " + car.getName());
                                id++;
                            }
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("Enter the car name:");
                        carDaoH2.createCar(scanner.nextLine(), i);
                        System.out.println("The car was added!\n");
                        break;
                }
            } else {
                return;
            }
        }
    }


    public static void managementCompany(CompanyDaoH2Impl companyDaoH2, CarDaoH2Impl carDaoH2) {
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            managerMenu();
            switch (userChoice()) {
                case 0:
                    return;
                case 1:
                    if(companyDaoH2.getAllCompanies().size() == 0) {
                        System.out.println("The company list is empty!\n");
                    } else {
                        System.out.println("Choose the company:");
                        companyDaoH2.getAllCompanies()
                                .stream()
                                .forEach(System.out::println);
                        System.out.println("0. Back");
                        managementCar(carDaoH2, companyDaoH2, userChoice());
                    }
                    break;
                case 2:
                    System.out.println("Enter the company name:");
                    companyDaoH2.createCompany(scanner.nextLine());
                    System.out.println("The company was created!\n");
                    break;
            }
        }
    }

    public static int userChoice() {
        return scanner.nextInt();
    }

}
