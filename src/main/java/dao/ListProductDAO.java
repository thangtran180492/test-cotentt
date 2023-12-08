package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBcontext;
import model.Product;

public class ListProductDAO {
	private String message = "";
	
	public ListProductDAO() {
	}

	public String getMessage() {
		return message;
	}

	public List<Product> search(String characters){
		List<Product> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = new DBcontext().getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			this.message = "Error connect to data";
			return list;
		}
		
		String sql = "SELECT * FROM products WHERE product_name LIKE '%"+ characters +"%'";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sql);
			
			ResultSet rs1 = stmt1.executeQuery();
			
			while(rs1.next()) {
				int p_id = rs1.getInt("product_id");
				String p_name = rs1.getString("product_name");
				String p_des = rs1.getString("product_des");
				float p_price = rs1.getFloat("product_price");
				String p_img_src = rs1.getString("product_img_source");
				String p_type = rs1.getString("product_type");
				String p_brand = rs1.getString("product_brand");
				list.add(new Product(p_id, p_name, p_des, p_price, p_img_src, p_type, p_brand, 1));
			}
			
			rs1.close();
			stmt1.close();
		} catch (SQLException e1) {
			this.message = "Error query database";
			return list;
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			this.message = "Error close connect";
			return list;
		}
		
		return list;
	}
	
	public Product getProduct(String characters) {
		Product newProduct = new Product();
		
		Connection conn = null;
		
		try {
			conn = new DBcontext().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			this.message = "Error connect database";
			return newProduct;
		}
		
		String sql = "SELECT * FROM products WHERE product_id="+ characters +"";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				newProduct.setId(rs.getInt("product_id"));
				newProduct.setName(rs.getString("product_name"));
				newProduct.setDescription(rs.getString("product_des"));
				newProduct.setPrice((float)rs.getDouble("product_price"));
				newProduct.setSrc(rs.getString("product_img_source"));
				newProduct.setType(rs.getString("product_type"));
				newProduct.setBrand(rs.getString("product_brand"));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e1) {
			this.message += "Error query";
			return newProduct;
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			this.message = "Error close database";
			return newProduct;
		}
		
		this.message += "Ok query database";
		return newProduct;
	}
}
