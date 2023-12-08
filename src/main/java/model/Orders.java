package model;

import java.sql.Date;
import java.util.List;

public class Orders {
	private int orderId;
	private float price;
	private int status;
	private Date order;
	private String address;
	private String phone;
	private List<ProductOrders> lp;
	private String userEmail;
	private Date receivedDate;
	private String discount;
	
	
	public Orders(String userEmail, int status, String discount, String address, String phone,  Date receivedDate) {
		super();
		this.status = status;
		this.address = address;
		this.phone = phone;
		this.userEmail = userEmail;
		this.receivedDate = receivedDate;
		this.discount = discount;
	}
	public Orders(int orderId, float price, int status, Date order, String address, String phone, List<ProductOrders> lp,
			String userEmail, Date receivedDate) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.status = status;
		this.order = order;
		this.address = address;
		this.phone = phone;
		this.lp = lp;
		this.userEmail = userEmail;
		this.receivedDate = receivedDate;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getOrder() {
		return order;
	}
	public void setOrder(Date order) {
		this.order = order;
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
	public List<ProductOrders> getLp() {
		return lp;
	}
	public void setLp(List<ProductOrders> lp) {
		this.lp = lp;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	
	
}
