package com.practice.hibernate.student.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//begin the transaction
			session.beginTransaction();
			
			//query the students
			List<Student> theStudent = session.createQuery("from Student").list();
			
			//display the students
			displayStudents(theStudent);
			
			theStudent = session.createQuery("from Student s where s.lastName='Vastav'").getResultList();
			System.out.println("\n\nStudents who have lastname of Vastav ");
			displayStudents(theStudent);
			
			//query students lastName='Vastav' or firstname='Sudha'
			theStudent = session.createQuery("from Student s where s.lastName='Vastav' or s.firstName='Sudha'").getResultList();
			System.out.println("\n\nStudents who have lastname of Vastav or firstName Sudha ");
			displayStudents(theStudent);
			
			//query students where email like '%gmail.com'
			theStudent = session.createQuery("from Student s where s.email like '%gmail.com'").list();
			System.out.println("\n\nStudents whose email ends with gmail.com");
			displayStudents(theStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudent) {
		for(Student tempStudent : theStudent) {
			System.out.println(tempStudent);
		}
	}
}
