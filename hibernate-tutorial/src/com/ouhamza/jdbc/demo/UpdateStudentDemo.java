package com.ouhamza.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ouhamza.jdbc.entities.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("saving data....");
			session.beginTransaction();
			Student student = session.get(Student.class, 1);
			student.setEmail("ouhamza.web.pro@hotmail.fr");
			session.getTransaction().commit();
			
			// or 
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("update Student s set email='adil.net@gmail.ma' where s.firstName='Tagguine' ").executeUpdate();
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
