package tp2_stClient;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDao;
import entities.Role;

public class RoleTest {
	public static IDao<Role> lookUpRoleRemote() throws NamingException {

		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8083");
		final Context context = new InitialContext(jndiProperties);

		return (IDao<Role>) context.lookup("ejb:tp2App/tp2_studentManagement/roleService!dao.IDao");
		// ejb:/tp2_studentManagement/roleService!dao.IDao
		
	}

	public static void main(String[] args) {
		try {
			IDao<Role> dao = lookUpRoleRemote();
		/*	dao.create(new Role("ddd"));
			dao.create(new Role("eeee"));
			dao.create(new Role("tttt"));
			*/
			 List<Role> roles = dao.findAll();
					
		       for (Role r : roles) {
		           System.out.println(r.getName());
		           System.out.println();
		     
		       }
		       
		       System.out.println("find ");
		       System.out.println(dao.findById(2));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("err "+e.getMessage());
		}
	}
}
