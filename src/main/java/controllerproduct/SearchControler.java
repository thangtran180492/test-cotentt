package controllerproduct;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import model.Product;

@WebServlet("/SearchControler")
public class SearchControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		
		String name = request.getParameter("search");
		session.setAttribute("search", name);
		
		ListProductDAO list = new ListProductDAO();
		
		List<Product> products = list.search(name);
		request.setAttribute("products", products);
		
		request.getRequestDispatcher("web/search.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
