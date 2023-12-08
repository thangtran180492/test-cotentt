package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.Account;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);
		try {
			//collect date from a login form
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			String rem = request.getParameter("remember");
			
			Account acc = new Account();
			acc.setCheck(user, password);
			
			//check account - use validate code in assignment 1 to valid user
			if(acc.getCheck() > 0) {
				//setting remember
				if(rem != null) {
					
					Cookie cooUser = new Cookie("cookuser", user);
					Cookie cooPass = new Cookie("cookpass", password);
					Cookie cooRemb = new Cookie("cookremb", rem);
					
					// setting time cookie
					cooUser.setMaxAge(1 * 60);
					cooPass.setMaxAge(1 * 60);
					cooRemb.setMaxAge(1 * 60);
					
					// add cookie
					response.addCookie(cooUser);
					response.addCookie(cooPass);
					response.addCookie(cooRemb);
				}
				
				//set session
				session.setAttribute("acc", acc);
				response.sendRedirect("web/admin/index.jsp");
			}else {
				session.setAttribute("error", acc.getMessage());
				response.sendRedirect("web/login.jsp");
			}
		}catch (NullPointerException e) {
			request.getRequestDispatcher("web/login.jsp").forward(request, response);
		}catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);
		try {
			
			//collect date from a login form
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			String rem = request.getParameter("remember");
			
			Account acc = new Account();
			acc.setCheck(user, password);
			
			//check account - use validate code in assignment 1 to valid user
			if(acc.getCheck() > 0) {
				//setting remember
				if(rem != null) {
					
					Cookie cooUser = new Cookie("cookuser", user);
					Cookie cooPass = new Cookie("cookpass", password);
					Cookie cooRemb = new Cookie("cookremb", rem);
					
					// setting time cookie
					cooUser.setMaxAge(1 * 60);
					cooPass.setMaxAge(1 * 60);
					cooRemb.setMaxAge(1 * 60);
					
					// add cookie
					response.addCookie(cooUser);
					response.addCookie(cooPass);
					response.addCookie(cooRemb);
				}
				
				//set session
				session.setAttribute("acc", acc);
				response.sendRedirect("web/admin/index.jsp");
			}else {
				session.setAttribute("error", acc.getMessage());
				response.sendRedirect("web/login.jsp");
			}
		}catch (NullPointerException e) {
			request.getRequestDispatcher("web/login.jsp").forward(request, response);
		}catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}

}
