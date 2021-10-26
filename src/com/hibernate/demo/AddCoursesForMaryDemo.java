package com.hibernate.demo;
import org.hibernate.cfg.Configuration;
import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
        //  Hibernate here to create sessions objects 
		// These session objects would create only once when app will run
		//create session Factory 
		//Hibernate used to map the POJO objects to the Database table
		
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
					//Begin Transaction
					session.beginTransaction(); 
					int studentId = 2; 
					Student tempstudent = session.get(Student.class,studentId);
					System.out.println("]\n Loaded student" + tempstudent);
					System.out.println(tempstudent.getCourses());
					//create more courses
					Course tempcourse1= new Course("Rubik'S cube - How to speed  ");
					Course tempcourse2= new Course("Atari 2600 Game development ");
					//add student to course
					tempcourse1.addStudent(tempstudent);
					tempcourse2.addStudent( tempstudent);
					//save the courses
					System.out.println("\n saving the courses");
					session.save(tempcourse1);
					session.save(tempcourse2);
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
