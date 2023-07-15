package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/admin/UploadFotoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		for (Part part : request.getParts()) {
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.equals("")) {
				try {
					FotoControl.updatePhoto(id, part.getInputStream());
				} catch (SQLException sqlException) {
					System.out.println(sqlException);
				}
			}
		}

		response.sendRedirect(request.getContextPath()+"/admin/add.jsp");
	}

}
