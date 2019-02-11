package com.practice.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on primary key
			System.out.println("Getting student with id: "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//delete the Student
			//System.out.println("Deleting the Student: "+myStudent);
			//session.delete(myStudent);
			
			//commit the transaction
			//session.getTransaction().commit();
			
			//Another way to delete the record
			System.out.println("Deleting the Student id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			factory.close();
		}
	}
}
