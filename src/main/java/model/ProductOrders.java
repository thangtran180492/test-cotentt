package model;

public class ProductOrders {
	private int orderid;
	private int productId;
	private int amountProduct;
	private String nameProduct;
	
	public ProductOrders() {
		super();
	}
	public ProductOrders(int orderid, int productId, int amountProduct, String nameProduct) {
		super();
		this.orderid = orderid;
		this.productId = productId;
		this.amountProduct = amountProduct;
		this.nameProduct = nameProduct;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getAmountProduct() {
		return amountProduct;
	}
	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	
	
}
