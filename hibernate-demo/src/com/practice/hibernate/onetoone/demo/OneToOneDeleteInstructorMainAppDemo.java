package com.practice.hibernate.onetoone.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;

public class OneToOneDeleteInstructorMainAppDemo {

	public static void main(String[] args) {
		
		//create sessionfactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start the transaction
			session.beginTransaction();
			
			//Get the instructor details object Bi-Directional mapping
			int theInstructorId = 5;
			
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theInstructorId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail : "+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("The associated instructor : "+tempInstructorDetail.getInstructor());
			
			//now delete the instructor detail
			System.out.println("Deleting tempInstructorDetail : "+ tempInstructorDetail);
			
			//remove the associated object reference
			//break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
			
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
