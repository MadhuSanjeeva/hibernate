package com.practice.hibernate.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		
		//If we want to changing the starting index of the table - need to truncate the table.
		//Alter table student auto_increment=3000;
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create 3 student objects	
			System.out.println("creating a new Student object...");
			Student theStudent1 = new Student("Sri","Vastav","sri@gmail.com");
			Student theStudent2 = new Student("Madhu","Sanjeeva","sanjeeva@gmail.com");
			Student theStudent3 = new Student("Sudha","Raghava","sudha@gmail.com");
			
			//begin the transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the Student...");
			session.save(theStudent1);
			session.save(theStudent2);
			session.save(theStudent3);
			
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
