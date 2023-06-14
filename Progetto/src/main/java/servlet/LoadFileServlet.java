package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DTO.Scarpa;
import model.ScarpaDataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "LoadFile", urlPatterns = "/admin/LoadFile", initParams = {
		@WebInitParam(name="file-upload", value = "tmpDir")})
@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*50)
public class LoadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		int taglia=Integer.parseInt(request.getParameter("taglia")); 
		double prezzo=Double.parseDouble(request.getParameter("prezzo")); 
		Collection<Part> parts=request.getParts();

		Scarpa scarpa=new Scarpa();
		scarpa.setNome(nome);
		scarpa.setTaglia(taglia);
		scarpa.setPrezzo(prezzo);

		List<InputStream> foto=new ArrayList<>();
		for (Part part : parts)
			foto.add(part.getInputStream());
		
		Enumeration<InputStream> x=Collections.enumeration(foto);
		
		SequenceInputStream fotoStream=new SequenceInputStream(x);

		scarpa.setFoto(fotoStream);
		
		
		ScarpaDataSource ds=new ScarpaDataSource();

		Boolean result=null; 
		try { 
			result=ds.doSave(scarpa);
		} catch (SQLException e){
			e.printStackTrace();
		}finally { 
			if (result!=null && result) {
				response.sendRedirect(request.getContextPath()+"/admin/reserved.jsp");
			} else {
				request.setAttribute("messaggio", "Scarpa non aggiunta");
				request.getRequestDispatcher("add.jsp").forward(request, response);
				}
			}
	}

}
