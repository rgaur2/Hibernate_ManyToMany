package com.hibernate.demo;



import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DeletePacmanCourseDemo {

	public static void main(String[] args) {
        // hibernate here to create sessions objects 
		// session objects would create only once when app will run
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
					//begin trasaction
					session.beginTransaction();
					//get the   course id  form database 
					int courseId = 10;
					Course tempcourse = session.get(Course.class,courseId );
					//delete the course
					System.out.println("\n deleting the course" + tempcourse);
					session.delete(tempcourse);
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
