package listener;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import DTO.Scarpa;
import model.ScarpaDataSource;

/**
 * Application Lifecycle Listener implementation class UploadScarpaAttributeListener
 *
 */
@WebListener
public class UploadScarpaAttributeListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public UploadScarpaAttributeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ScarpaDataSource ds=new ScarpaDataSource();

		try {
			Collection<Scarpa> scarpe=ds.doRetrieveAll();

			sce.getServletContext().setAttribute("scarpe", scarpe);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
}
