package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Product> items;

	public Cart() {
		this.items = new ArrayList<>();
	}
	
	/* thêm sản phẩm vào cart */
	public void add(Product ci) {
		for(Product x : items) {
			if(x.getId() == ci.getId()) {
				x.setNumber(x.getNumber() + 1);
				return;
			}
		}
		this.items.add(ci);
	}
	
	/* xóa 1 sản phảm khỏi cart*/
	public void remove(int id) {
		for(Product x: this.items) {
			if(x.getId() == id) {
				this.items.remove(x);
				return;
			}
		}
	}
	
	/* tổng giá cả các sản phẩm trong cart */
	public double getAmount() {
		double s = 0;
		for(Product x : this.items) {
			s += x.getPrice() * x.getNumber();
		}
		return Math.round(s * 100.0) / 100.0;
	}

	public List<Product> getItems() {
		return items;
	}
}
