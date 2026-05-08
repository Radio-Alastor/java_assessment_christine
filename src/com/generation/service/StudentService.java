package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.*;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public StudentService(){
        subscribeStudent(new Student("001", "John", "johndoe@gmail.com", new Date("01/01/2000")));
        subscribeStudent(new Student("002", "Sally", "sallyso@hotmail.com", new Date("01/01/2005")));
        subscribeStudent(new Student("003", "Jane", "janesmith@gmail.com", new Date("01/01/2006")));
    }

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )
    {
        //TODO (DONE) implement this method
        // Check if the student exists in the system
        return students.containsKey(studentId);
    }

    public void showSummary()
    {
        //TODO (DONE) implement this method
        // 1. Show each student information
        // 2. Along with the course(s) that each the student is taking

        System.out.println("Students Information");
        students.forEach((studentId, studentInfo) ->{
            System.out.println(studentInfo);

            List<Course> studentCourses = studentInfo.getApprovedCourses();

            if(!studentCourses.isEmpty()){
                System.out.printf("Course(s) taken by studentId: %s%n", studentId);
                for(Course course : studentCourses){
                    System.out.println(course);
                }
                System.out.printf("%n");

            }else{
                System.out.printf("No Course taken by studentId: %s%n%n", studentId);
            }
        });
    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            students.get( studentId ).enrollToCourse( course );
        }
    }

    public void showPassedCourses(Student student){

        // 1. get the student
        // 2. call findPassedCourses()
        // 3. display the list of courses returned from findPassedCourses()

        List<Course> passedCourses = student.findPassedCourses();

        if (!passedCourses.isEmpty()) {
            //there are courses to be displayed
            System.out.println("Courses that the student passed:");
            passedCourses.forEach((course)->{
                System.out.println(course);
            });
        } else {
            //feedback that there are no passed courses
            System.out.println("The student did not pass any course(s).");
        }
    }
}
