package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/common/GetPictureServlet")
public class GetPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			byte[] bt = FotoControl.load(id);

			ServletOutputStream out = response.getOutputStream();
			if (bt != null) {
				response.setContentType("image/png");
				out.write(bt);
			}
			out.close();
		}
	}

}
