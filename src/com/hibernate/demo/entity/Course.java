package com.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hibernate.demo.entity.Instructor;

@Entity
@Table(name="course")
public class Course {
	//define our fields
	//define constructor 
	//define getter and setters
	//define toStirng
	//annotate fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id ")
	private int id;
	private String title;
	//Remember just like Realworld project if we delete course it should not delete instructor
	@ManyToOne (cascade= {CascadeType.PERSIST ,CascadeType.MERGE ,CascadeType.DETACH , CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")  //Foreign key
	private Instructor instructor;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")//make id as Foreign key
	private List<Review> reviews;
	
	
	
	//Following code is used for manyTomany relationship 
	//In this we create new table course_student which picks courseid from course table and student id from student table
	//course_student is a join table 
	//course_student is maintain which student assign  which course
	@ManyToMany(
			fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	
	@JoinTable(
			name="course_student",
			joinColumns= @JoinColumn(name="course_id"),inverseJoinColumns=@JoinColumn(name="student_id"))
	private List<Student> students;
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void addStudent(Student thestudents)
	{
		if(students == null)
		{
			students = new ArrayList<>();
		}
		students.add(thestudents);
    }
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public Course()
	{
		
	}
	public Course(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	// add a convenience method
	
	public void addReview(Review theReview) {
		if (reviews == null) {
			reviews = new ArrayList<>();
			}
			reviews.add(theReview);
		}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
}
