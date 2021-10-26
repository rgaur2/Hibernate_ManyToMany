package com.hibernate.demo;



import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DeleteMaryStudentDemo {

	public static void main(String[] args) {
        //Hibernate here to create sessions objects 
		//session objects would create only once when app will run
		//create session Factory 
		SessionFactory factory = new Configuration()
				                  .configure("hibernate.cfg.xml")
				                  .addAnnotatedClass(Instructor.class)
				                  .addAnnotatedClass(InstructorDetail.class)
				                  .addAnnotatedClass(Course.class)
				                  .addAnnotatedClass(Review.class)
				                  .addAnnotatedClass(Student.class)
				                  .buildSessionFactory();
		// session created
		Session session =  factory.getCurrentSession();
				try {
					//Begin trasaction
					session.beginTransaction();
					//Get the student marry form database 
					int studentId = 2;
					Student tempstudent = session.get(Student.class,studentId);
					System.out.println("]\n Loaded student" + tempstudent);
					System.out.println(tempstudent.getCourses());
					//DELETE Student
					System.out.println("\n delete student " +tempstudent );
					session.delete(tempstudent);
					// commit the transaction				
					session.getTransaction().commit();
					System.out.println("Done");
				}
				finally {
					//add clean code
					session.close();
					factory.close();
				}
	           }
	           }
