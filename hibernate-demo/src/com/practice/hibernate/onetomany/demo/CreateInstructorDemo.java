package com.practice.hibernate.onetomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Course;
import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			Instructor tempInstructor = new Instructor("Susan","Public","susan.public@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://susan.youtubechannel","Video Gamer");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start the transaction
			session.beginTransaction();
			
			//save a transaction
			//NOTE: this will also save the InstructorDetail object also 
			//because of CascadeType.ALL
			System.out.println("Saving Instructor: "+tempInstructor);
			session.save(tempInstructor);
			
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
