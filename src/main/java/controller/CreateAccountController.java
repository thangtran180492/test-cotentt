package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.Account;

@WebServlet("/CreateAccountController")
public class CreateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public CreateAccountController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).invalidate();
		request.getSession(true);
		HttpSession session = request.getSession();
		
		String email = (String)request.getParameter("email");
		String password = (String)request.getParameter("password");
		String coverPassword = (String)request.getParameter("coverPassword");
		String fullName = (String)request.getParameter("fullName");
		String address = (String)request.getParameter("address");
		String phone = (String)request.getParameter("phone");
		
		String url = "";
		
		Account newAccount = new Account();
		newAccount.setCheck(email, password, coverPassword, fullName, address, phone);
		
		session.setAttribute("error", newAccount.getMessage());
		
		if(newAccount.getCheck() > 0) {
			url = "web/login.jsp";
		}else {
			url = "web/register.jsp";
		}
		response.sendRedirect(url);
	}

}
