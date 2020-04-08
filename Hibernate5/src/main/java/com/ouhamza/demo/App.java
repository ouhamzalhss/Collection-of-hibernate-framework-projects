package com.ouhamza.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.MultiIdentifierLoadAccess;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ouhamza.demo.entity.Student;
import com.ouhamza.demo.entity.Subject;
import com.ouhamza.demo.util.HibernateUtil;


public class App {
    public static void main(String[] args) {
    	  init();
         // getStream();
         // getMoreEntities();
    	 unassociatedEntities();
    }
    
    

    
    public static void unassociatedEntities() {
    	
  	    Transaction transaction = null;
    	
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	transaction = session.beginTransaction();
        	
            Query q = session.createQuery("SELECT st.firstName, count(sb.id) FROM Student st INNER JOIN Subject sb ON sb.studentId = st.id GROUP BY st.firstName");
            Object[] r = (Object[]) q.getSingleResult();
            
        	System.out.println(r[0]+" teaches "+ r[1] + " subject");
            
        	transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
    	
    }    
    
    public static void getMoreEntities() {
    	
  	    Transaction transaction = null;
    	
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	transaction = session.beginTransaction();
        	
        	MultiIdentifierLoadAccess<Student> multi = session.byMultipleIds(Student.class);
        	List<Student> students = multi.multiLoad(1, 2, 3);
        	System.out.println(students);
            
        	transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
    	
    }
    
    public static void getStream() {
    	Transaction transaction = null;
    	
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	transaction = session.beginTransaction();
        	
        	Stream<Student> students = session.createQuery("SELECT s FROM Student s").stream();
        	students.map(b -> b.getFirstName()+ " has email   " + b.getEmail())
        		    .forEach(m -> System.out.println(m));
            
        	transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
            
    }
    
    public static void init() {
    	   Student student = new Student("Anas", "Anassi", "anas@gamil.com",LocalDate.of(2018, 10,5));
           Student student1 = new Student("Achraf", "Charafi", "achraf@gmail.com",LocalDate.of(2019, 10, 3));
           Student student2 = new Student("Ali", "Alioui", "ali@gmail.com",LocalDate.of(2022, 10, 2));
           
           
           Subject subject = new Subject("Maths", 1);
           Subject subject2 = new Subject("Svt", 1);
           
           
           Transaction transaction = null;
           try (Session session = HibernateUtil.getSessionFactory().openSession()) {
               // start a transaction
               transaction = session.beginTransaction();
               // save the student objects
               session.save(student);
               session.save(student1);
               session.save(student2);        
               
               session.save(subject);
               session.save(subject2);
               
               // commit transaction
               transaction.commit();
           } catch (Exception e) {
               if (transaction != null) {
                   transaction.rollback();
               }
               e.printStackTrace();
           }
    }
}