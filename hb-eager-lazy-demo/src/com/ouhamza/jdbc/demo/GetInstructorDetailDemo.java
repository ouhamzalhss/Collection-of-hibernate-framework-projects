package com.ouhamza.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ouhamza.jdbc.entities.Instructor;
import com.ouhamza.jdbc.entities.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("deleting data....");
				session.beginTransaction();

				InstructorDetail detail = session.get(InstructorDetail.class,2);
				System.out.println(" instructor detail: "+ detail);
				System.out.println(" the associate instructor: "+ detail.getInstructor());

				session.getTransaction().commit();
			System.out.println("Done...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		

	}

}
