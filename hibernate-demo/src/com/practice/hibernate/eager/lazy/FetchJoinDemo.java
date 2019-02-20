package com.practice.hibernate.eager.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.practice.hibernate.demo.entity.Course;
import com.practice.hibernate.demo.entity.Instructor;
import com.practice.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			//start the transaction
			session.beginTransaction();
			
			//get the instructor form db
			int theId = 6;
			
			//Option 2: Hiberante Query with HQL
			
			Query<Instructor> query= session.createQuery("select i from Instructor i "
								+ "JOIN FETCH i.courses "
								+ "where i.id=:theInstructorId",
								Instructor.class);
			query.setParameter("theInstructorId", theId);
			
			//execute query and get Instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luv2code : Instructor: "+tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			//closing the session
			session.close();
			
			//since courses are lazy loaded.. this should fail
			
			System.out.println("luv2code : Courses : "+tempInstructor.getCourses());
			
			System.out.println("luv2code : Done!!!");
			
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}
}
