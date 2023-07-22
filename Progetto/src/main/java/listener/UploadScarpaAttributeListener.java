package listener;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import DTO.Scarpa;
import model.ScarpaDataSource;

@WebListener
public class UploadScarpaAttributeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
    	ScarpaDataSource ds=new ScarpaDataSource();

		try {
			Collection<Scarpa> scarpe=ds.doRetrieveAll();

			sce.getServletContext().setAttribute("scarpe", scarpe);

		} catch (SQLException e) {
		}
    }
	
}
