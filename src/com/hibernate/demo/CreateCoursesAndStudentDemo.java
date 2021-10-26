package com.hibernate.demo;
import org.hibernate.cfg.Configuration;
import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class CreateCoursesAndStudentDemo {

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
		//create session
		Session session =  factory.getCurrentSession();
				try {
					session.beginTransaction();
					// course created
					Course tempCourse = new Course("Pacman - How to Score one million Points ");
					//save the course
					System.out.println("\n saving the course");
					session.save(tempCourse);
					System.out.println("saved the course" + tempCourse);
					//create the student 
					Student tempStudent1 = new Student("john" , "Doe" , "john@code.com");
					Student tempStudent2 = new Student("mary" , "Public" , "mary@code.com");
					//add student to the course
					tempCourse.addStudent(tempStudent1);
					tempCourse.addStudent(tempStudent2);
					//save the students
                    System.out.println("\n saving the students");
                    session.save(tempStudent1);
                    session.save(tempStudent2);
                    System.out.println("saved student" +tempCourse.getStudents() );
					//commit Transaction
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
