package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import context.DBcontext;
import model.Cart;
import model.Orders;
import model.Product;

public class OrdersDAO {
	private String message = "";
	
	public String getMessage() {
		return message;
	}

	/* lưu đơn hàng vào database */
	public void insertOrder(Orders order, Cart cart) {
		if(cart.getItems().size() <= 0) {
			this.message = "Your cart is empty";
			return;
		}
		Connection conn = null;
		try {
			conn = new DBcontext().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			this.message = "Error connect to database";
			return;
		}
		// thêm order vào trong cơ sở dữ liệu
		String orders = "INSERT INTO orders(user_mail, order_status, order_date, order_discount_code, order_address)"
				+ "value(?,?,CURDATE(),?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(orders, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, order.getUserEmail());
			stmt.setInt(2, order.getStatus());
			stmt.setString(3, order.getDiscount());
			stmt.setString(4, order.getAddress());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				order.setOrderId(rs.getInt(1));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e1) {
			this.message = "Error Insert  new data";
			return;
		}
		// thêm danh sách product với orders được tạo ra
		String orderDetail = "INSERT INTO orders_detail(order_id, product_id, amount_product, price_product) VALUE";
			
		int i = 0;
		int list = cart.getItems().size() - 1;
		for(Product x : cart.getItems()) {
			orderDetail += "( '"
					+ order.getOrderId() +"', '"
					+ x.getId()+"', '"
					+ x.getNumber()+"', '"
					+ (x.getNumber() * x.getPrice()) +"')";
			if(i < list) {
				orderDetail += ",";
			}
			i++;
		}
		
		try {
			PreparedStatement stmt2 = conn.prepareStatement(orderDetail);
			
			stmt2.executeUpdate();
			
			stmt2.close();
		} catch (SQLException e1) {
			this.message = "Error add orders detail";
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			this.message = "Error connect to database";
			return;
		}
		this.message = "Pay cart Successful!!!";
	}
}
