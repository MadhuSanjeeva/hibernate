package com.practice.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create a student object
			System.out.println("creating a new Student object...");
			Student theStudent = new Student("Sri","Vastav","sri@gmail.com");
			
			//begin the transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the Student...");
			session.save(theStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			factory.close();
		}
	}
}
