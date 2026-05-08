package com.generation.utils;

import com.generation.model.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PrinterHelper
{

    public static void showMainMenu(){
        System.out.println( "|-------------------------------|" );
        System.out.println( "| Welcome to StudentGen         |" );
        System.out.println( "|-------------------------------|" );
        System.out.println( "| Select 1 option:              |" );
        System.out.println( "| . 1 Register Student          |" );
        System.out.println( "| . 2 Find Student              |" );
        System.out.println( "| . 3 Grade Student             |" );
        System.out.println( "| . 4 Enroll Student to Course  |" );
        System.out.println( "| . 5 Show Students Summary     |" );
        System.out.println( "| . 6 Show Courses Summary      |" );
        System.out.println( "| . 7 Show Student Course Passes|" );
        System.out.println( "| . 8 Exit                      |" );
        System.out.println( "|-------------------------------|" );
    }

    public static Student createStudentMenu( Scanner scanner )
            throws ParseException
    {
        System.out.println( "|-------------------------------------|" );
        System.out.println( "| . 1 Register Student                |" );
        System.out.println( "|-------------------------------------|" );
        System.out.println( "| Enter student name:                 |" );
        String name = scanner.nextLine();
        System.out.println( "| Enter student ID:                   |" );
        String id = scanner.nextLine();
        System.out.println( "| Enter student email:                |" );
        String email = scanner.nextLine();

        //TODO (DONE) validate date format and catch exception to avoid crash
        // If the user enters a value that is NOT a date (try/catch)
        // Ask the user for a valid value

        DateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy");
        formatter.setLenient(false);
        Date birthDate = null;

        do {
            System.out.println( "| Enter student birth date(MM/dd/yyyy)|" );
            String inputDate = scanner.nextLine(); // "01 Jun 2001"

            try{
                birthDate = formatter.parse(inputDate);
                break;
            }catch (ParseException e){  // Exceptions
                System.out.println("Date format is invalid.");
            }

        }while(true);

        System.out.println( "|-------------------------------------|" );
        Student student = new Student( id, name, email, birthDate );
        System.out.println( "Student Successfully Registered! " );
        System.out.println(student);
        return student;
    }

}