package com.practice.hiberante.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Course;
import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;
import com.practice.hibernate.demo.entity.Review;
import com.practice.hibernate.demo.entity.Student;

public class CreateCoureAndStudentsDemo {

	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start the transaction
			session.beginTransaction();
			
			//create the course
			Course tempCourse = new Course("Pacman");
			
			//saving the course..
			System.out.println("Saving the course...");
			session.save(tempCourse);
			System.out.println("Saved the Course : "+tempCourse);
			
			//create the student
			Student tempStudent1 = new Student("Srinu", "Sana", "srinu@gmail.com");
			Student tempStudent2 = new Student("Laxmi", "Gurram", "laxmi@gmail.com");
			
			//add the student
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			//save the student
			System.out.println("\n Saving the students");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved the students .... "+tempCourse.getStudents());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}
}
