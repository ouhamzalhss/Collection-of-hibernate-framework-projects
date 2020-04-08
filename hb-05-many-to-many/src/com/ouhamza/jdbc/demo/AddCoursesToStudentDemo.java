package com.ouhamza.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ouhamza.jdbc.entities.Course;
import com.ouhamza.jdbc.entities.Instructor;
import com.ouhamza.jdbc.entities.InstructorDetail;
import com.ouhamza.jdbc.entities.Review;
import com.ouhamza.jdbc.entities.Student;

public class AddCoursesToStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Saving data ....");
			
			session.beginTransaction();	
			
			Course course = new Course("Angular course and js");	
			
			session.save(course);
			
			Student student1 = new Student("Charafi", "Fatima", "fatima@gmail.com");

			Student student2 = new Student("Aliwi", "Amina", "amina@gmail.com");
			
			course.addStudent(student1);
			course.addStudent(student2);
			
			session.save(student1);
			session.save(student2);
			
			session.getTransaction().commit();
			System.out.println("well done...");
			
			/*session.beginTransaction();
			Course course = session.get(Course.class, 11);
			session.delete(course);
		session.getTransaction().commit();*/
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		

	}

}
