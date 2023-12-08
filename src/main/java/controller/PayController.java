package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;
import model.Cart;
import model.Orders;

@WebServlet("/PayController")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String notification = "";
		String url = "";
		String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("cart") == null) {
			session.setAttribute("cart", new Cart());
		}
			OrdersDAO dao = new OrdersDAO();
			Cart cart = (Cart)session.getAttribute("cart");
			
			String email = request.getParameter("email").trim();
			String discount = request.getParameter("discount").trim();
			String address = request.getParameter("address").trim();
			String phone = request.getParameter("phone").trim();
		// nếu nhập đủ thông tin
		if(email.matches(regexMail) && !address.isEmpty() && !phone.isBlank() && phone.length() < 13 ) {
			request.setAttribute("cart", cart);
			request.setAttribute("email", email);
			request.setAttribute("discount", discount);
			request.setAttribute("address", address);
			request.setAttribute("phone", phone);
			
			Orders order = new Orders(email, 2, discount, address, phone, null);
			dao.insertOrder(order, cart);
			session.setAttribute("cart", new Cart());
			
			notification = dao.getMessage();
			url = "web/notification.jsp";
		}else {
			notification = "Error... input information!!!";
			url = "web/cart.jsp";
		}
		request.setAttribute("notification", notification);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
