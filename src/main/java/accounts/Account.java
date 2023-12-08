package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import context.DBcontext;

public class Account {
	private String usr, pwd;
	private int role;
	private String name, address, phone;
	private int check;
	
	private String message = "";
	
	public String getMessage() {
		return message;
	}

	public Account() {}

	public Account(String usr, String pwd, int role, String name, String address, String phone, int check) {
		super();
		this.usr = usr;
		this.pwd = pwd;
		this.role = role;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.check = check;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(String email, String password) {
		if(email == null || password == null) {
			this.message = "Error enter email or password empty";
			this.check = 0;
			return;
		}
		
		//make sure that email is valid
		String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		String regex = "[a-zA-Z0-9_!@#$%^&*]+";
		
		if (!password.matches(regex) || !email.matches(regexMail)) {
			this.message = "Error enter email or password is incorrect ";
			this.check = 0;
			return;
		}
		
		Connection conn = null;
		try {
			// kết nối Mysql
			conn = new DBcontext().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			this.message = "Error can't connect database";
			this.check = -1;
			return;
		}
		
		String sql = "SELECT COUNT(*) count FROM account WHERE user_mail LIKE '"+ email +"' AND password LIKE'"+ password +"'";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			// lấy kết quả từ truy vấn table
			if(rs.next())
				count = rs.getInt("count");
			
			if(count <= 0) {
				this.message = "Error enter email or password not exists";
				rs.close();
				conn.close();
				this.check = -1;
			}
			
		String sqlAcc = "SELECT * FROM account WHERE user_mail LIKE '"+ email +"' AND password LIKE'"+ password +"'";
			
		PreparedStatement stmt2 = conn.prepareStatement(sqlAcc);
		
		ResultSet rs2 = stmt2.executeQuery();
		
		// thiết lập thông tin tài khoản
		if(rs2.next()) {
			this.usr =  rs2.getString("user_mail");
			this.pwd =  rs2.getString("password");
			this.role = rs2.getInt("account_role");
			this.name = rs2.getString("user_name");
			this.address = rs2.getString("user_address");
			this.phone = rs2.getString("user_phone");
		}
		stmt2.close();
		rs2.close();
		} catch (SQLException e1) {
			this.message = "Error query data";
			this.check = -1;
			return;
		}
		
		try {
			// đóng kết nối
			conn.close();
		} catch (SQLException e) {
			this.message = "Error close connect database";
			this.check = -1;
			return;
		}
		
		this.check = 1;
	}
	public void setCheck(String email, String password, String coverPassword,
						 String fullName, String address, String phone) {
		//make sure that email is valid
		String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		String regex = "[a-zA-Z0-9_!@#$%^&*]+";
		
		// nếu có chuỗi trống
		if(email == null || password == null || coverPassword == null ||
		   fullName == null || address == null || phone == null) {
			this.message = "please, Enter information cannot be empty";
			this.check = 0;
			return;
		// nếu sau định dạng
		}else if(!password.matches(regex) || !email.matches(regexMail)) {
			this.message = "error email or password!!!";
			this.check = 0;
			return;
		// nếu nhập lại passowrd không đúng
		}else if(!password.equals(coverPassword)) {
			this.message = "error cover password!!!";
			this.check = 0;
			return;
		}
		
		Connection conn = null;
		
		try {
			conn = new DBcontext().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			this.message = "Error connect";
			this.check = -1;
			return;
		}
		
		String sql = "SELECT COUNT(*) as count FROM account WHERE user_mail LIKE '"+email+"'";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sql);
			ResultSet rs = stmt1.executeQuery();
			
			int count = 0;
			if(rs.next()) {
				count = rs.getInt("count");
			}
			// kiểm tra emai đã đăng ký chưa
			if(count > 0) {
				this.message = "Resigter Email exists";
				return;
			}
		} catch (SQLException e1) {
			this.message = "Error get Result";
			this.check = -1;
			return;
		}
		
		String insert = "INSERT INTO account(user_mail, password, account_role, user_name, user_address, user_phone)"
								   + " VALUE("
								   + "?,"
								   + "?,"
								   + "1,"
								   + "?,"
								   + "?, "
								   + "?)";
		
		try {
			// update dữ liệu
			PreparedStatement stmt2 = conn.prepareStatement(insert);
			
			stmt2.setString(1, email);
			stmt2.setString(2, password);
			stmt2.setString(3, fullName);
			stmt2.setString(4, address);
			stmt2.setString(5, phone);
			
			stmt2.executeUpdate();
			stmt2.close();
			
			this.message = "Create Account Successful!!";
		} catch (SQLException e1) {
			this.message = "Error can't create";
			this.check = -1;
			return;
		}
		
		
		try {
			conn.close();
		} catch (SQLException e) {
			this.message = "Error close connect";
			this.check = -1;
			return;
		}
		
		this.check = 1;
	}
}
