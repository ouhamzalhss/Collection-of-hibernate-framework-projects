package com.ouhamza.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.ouhamza.jdbc.entities.Course;
import com.ouhamza.jdbc.entities.Instructor;
import com.ouhamza.jdbc.entities.InstructorDetail;

public class JoinFetchDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("getting data....");
				session.beginTransaction();

				Query<Instructor> query = session.createQuery("select i from Instructor i "
						+ " JOIN FETCH i.courses "
						+ " where i.id=:theInstructorId");
				
				query.setParameter("theInstructorId", 1);
				
				Instructor instructor= query.getSingleResult();
						
				System.out.println(" instructor : "+ instructor);

				session.getTransaction().commit();
				
				session.close();
				System.out.println("\nhe the session is closed\n");
				// lazy exception 
				System.out.println(" the associate courses: "+ instructor.getCourse());

			System.out.println("Done...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		

	}

}
