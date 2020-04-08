package com.ouhamza.jdbc.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ouhamza.jdbc.entities.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			 System.out.println("********************************************");
			 session.beginTransaction();
			 List<Student> students = session.createQuery("from Student").getResultList();
			 displayStudents(students);
			 
			 
			 System.out.println("********************************************");
			 students = session.createQuery("from Student s where s.lastName='Adil' ").getResultList();
			 displayStudents(students);
			 
			 session.getTransaction().commit();
			 session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		

	}

	private static void displayStudents(List<Student> students) {
		for(Student student: students) {
			 System.out.println("get Student: "+ student.toString());
		 }
	}

}
