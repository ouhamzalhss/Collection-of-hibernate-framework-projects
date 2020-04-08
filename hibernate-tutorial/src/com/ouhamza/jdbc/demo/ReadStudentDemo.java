package com.ouhamza.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ouhamza.jdbc.entities.Student;

public class ReadStudentDemo {

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
			Student theStudent = new Student("Ouhamza", "LHOUCIENE", "ouhamza.web.pro@gmail.com");
			 session.save(theStudent);
			session.getTransaction().commit();
			
			int idStudent = theStudent.getId();
			System.out.println(idStudent);
			 
			 session = factory.getCurrentSession();
			 session.beginTransaction();
			 //Student student = session.get(Student.class, idStudent );
			 Student student = session.get(Student.class, 1 );
			 System.out.println("get Student: "+ student.toString());
			 session.getTransaction().commit();
			
			
		} catch (Exception e) {
		   	e.printStackTrace();
		}finally {
			 session.close();
			 factory.close();
		}
		

	}

}
