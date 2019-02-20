package com.practice.hibernate.onetomanyuni.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Course;
import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;
import com.practice.hibernate.demo.entity.Review;

public class CreateCouresAndReviewsDemo {

	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start the transaction
			session.beginTransaction();
			
			//create the course
			Course tempCourse = new Course("Pacman");
			
			//create the review
			tempCourse.addReview(new Review("Good..."));
			tempCourse.addReview(new Review("Cool..."));
			tempCourse.addReview(new Review("What a Game!!!..."));
			
			//save the course and leverage the cascade all
			System.out.println("Saving Course");
			System.out.println("TempCourse : "+tempCourse);
			System.out.println("GetReviews : "+tempCourse.getReviews());
			session.save(tempCourse);
			
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
