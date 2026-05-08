package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = Integer.parseInt(scanner.nextLine());
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showPassedCourses( studentService, scanner );
                    break;
            }
        }
        while ( option != 8 );
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.nextLine();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.nextLine();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService, Scanner scanner )
    {
        //TODO What do we do before we grade a student?
        // 1. Ask for studentID
        // 1.1 Check whether the student exists
        // 2. Ask for the course ID
        // 2.1 Find whether the student is taking the course
        // 3 What is the grade to assign to the student?
        // 3.1 If the student is taking the course, then we can assign a grade

        System.out.println("Enter the student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentService.findStudent(studentId);

        if ( student != null )
        {
            System.out.printf( "Please enter the course ID for student ID: (%s): ", studentId);
            String courseId = scanner.nextLine();
            boolean isAttendingCourse = student.isAttendingCourse(courseId);

            // If the course is found where isAttendingCourse == true
            // then we grade the course
            // else we feed back that the student did not take the course

            if(isAttendingCourse){
                System.out.printf( "Please enter the score for course ID (%s): ", studentId);
                double score = Double.parseDouble(scanner.nextLine());
                if(score < 0 || score > 9)
                    System.out.println("Invalid score entry");
                else
                    System.out.println(student.setGrade(courseId, score));

            }else{
                System.out.println("Student is not attending this course.");

            }
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void showPassedCourses(StudentService studentService,Scanner scanner){
        System.out.println( "Enter student ID: " );
        String studentId = scanner.nextLine();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );

            // Show the courses the student passed
            studentService.showPassedCourses(student);
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.nextLine();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}
