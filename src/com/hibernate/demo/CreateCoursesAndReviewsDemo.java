package com.hibernate.demo;
import org.hibernate.cfg.Configuration;
import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
        // Hibernate here to create sessions objects 
		//  session objects would create only once when app will run 
		//create session Factory 
		SessionFactory factory = new Configuration()
				                  .configure("hibernate.cfg.xml")
				                  .addAnnotatedClass(Instructor.class)
				                  .addAnnotatedClass(InstructorDetail.class)
				                  .addAnnotatedClass(Course.class)
				                  .addAnnotatedClass(Review.class)
				                  .buildSessionFactory();
	     	Session session =  factory.getCurrentSession();
				try {
					//create the object 
					//create the course
					Course tempCourse = new Course("Pacman - How to Score one million Points ");
					//add some course 
					tempCourse.addReview(new Review("Great course love it!"));
					tempCourse.addReview(new Review("cool course job well done"));
					tempCourse.addReview(new Review("Whats a dumb course, you are an idiot !!"));
					session.beginTransaction();
					System.out.println("saving the couse");
					//save the course  .... and leverage the cascade all :=)
					System.out.println(tempCourse);
					System.out.println(tempCourse.getReviews());
					session.save(tempCourse);
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
