package com.ouhamza.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ouhamza.jdbc.entities.Instructor;
import com.ouhamza.jdbc.entities.InstructorDetail;

public class CreateDemo {

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
			System.out.println("saving data....");
			session.beginTransaction();
			//Instructor instructor = new Instructor("Ouhamza", "LHOUCIENE", "ouhamza.web.pro@gmail.com");
			//InstructorDetail instDetail = new InstructorDetail("Ouhamza.channel", "sport");	
			
			Instructor instructor = new Instructor("aliwi", "li", "ali.web.pro@gmail.com");
			InstructorDetail instDetail = new InstructorDetail("ali.channel", "sport");
			
			instructor.setInstructorDetailId(instDetail);
			session.save(instructor);
			session.getTransaction().commit();
			System.out.println("Done...");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

}
