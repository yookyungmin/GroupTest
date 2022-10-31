package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MessagesDTO;
import dao.MessagesDAO;

@WebServlet("*message")
public class MessagesController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		try {
			
			if(uri.equals("/input.message")) {
				
				String writer = request.getParameter("writer");
				String msg = request.getParameter("message");
				
				MessagesDAO dao = MessagesDAO.getInstance();
				int reslut = dao.insert(writer, msg);
				response.sendRedirect("/index.jsp");
				
			} else if(uri.equals("/output.message")) {
				
				MessagesDAO dao = MessagesDAO.getInstance();
				List<MessagesDTO> list = dao.selectAll();
				
				request.setAttribute("list", list);
				request.getRequestDispatcher("/outputForm.jsp").forward(request, response);
				
			} else if(uri.equals("/delete.message")) {
				
				int seq = Integer.parseInt(request.getParameter("delSeq"));
				MessagesDAO dao = MessagesDAO.getInstance();
				
				int result = dao.delete(seq);
				response.sendRedirect("output.message");
				
			} else if(uri.equals("/update.message")) {
				
				int seq = Integer.parseInt(request.getParameter("seq"));
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");
				
				MessagesDAO dao = MessagesDAO.getInstance();
				MessagesDTO dto = new MessagesDTO(seq,writer,message);
				int result = dao.update(dto);
				response.sendRedirect("output.message");
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
