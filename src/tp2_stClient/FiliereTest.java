package tp2_stClient;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDao;
import entities.Filiere;
import entities.Filiere;
public class FiliereTest {


		public static IDao<Filiere> lookUpFiliereRemote() throws NamingException {

			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8083");
			final Context context = new InitialContext(jndiProperties);

			return (IDao<Filiere>) context.lookup("ejb:tp2App/tp2_studentManagement/filiereService!dao.IDao");
			
			
		}

		public static void main(String[] args) {
		    try {
		        IDao<Filiere> dao = lookUpFiliereRemote();
		        
		    //    dao.create(new Filiere("CS11", "Computer Science"));
		    //    dao.create(new Filiere("EE22", "Electrical Engineering"));
		      //  dao.create(new Filiere("ME33", "Mechanical Engineering"));

		        for (Filiere Filiere : dao.findAll()) {
		            System.out.println("Filiere Name: " + Filiere.getName());
		            
		            System.out.println();
		        }
		    } catch (NamingException e) {
		        System.out.println("Error: " + e.getMessage());
		    }
		}


	}
