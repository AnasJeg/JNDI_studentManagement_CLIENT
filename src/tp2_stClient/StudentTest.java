package tp2_stClient;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDao;
import entities.Filiere;
import entities.Student;

public class StudentTest {
	public static IDao<Student> lookUpStudentRemote() throws NamingException {

		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8083");
		final Context context = new InitialContext(jndiProperties);

		return (IDao<Student>) context.lookup("ejb:tp2App/tp2_studentManagement/studentService!dao.IDao");
		
		
	}

	public static void main(String[] args) {
	    try {
	    	FiliereTest filiereTest=new FiliereTest();
	        IDao<Student> dao = lookUpStudentRemote();
	        
/*
	        Student student1 = new Student("password1", "login1", "anas", "jeg", "0687367652");
	       student1.setFiliere(filiereTest.lookUpFiliereRemote().findById(1));
	        dao.create(student1);

	        Student student2 = new Student("password2", "login2", "lachg", "emsi", "0798387331");
	        student2.setFiliere(filiereTest.lookUpFiliereRemote().findById(2));
	        dao.create(student2);

	        Student student3 = new Student("password3", "login3", "aaaa", "tttt", "0623286397");
	        student3.setFiliere(filiereTest.lookUpFiliereRemote().findById(3));
	        dao.create(student3);
*/
	        for (Student student : dao.findAll()) {
	            System.out.println("Name: " + student.getFirstname() + " " + student.getRoles()+" ");
	            System.out.println();
	        }
	    } catch (NamingException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}


}
