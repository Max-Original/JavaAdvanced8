package hw.domain;

import java.util.Date;

public class Basket {

	private Integer id;
	private Integer user_id;
	private Integer product_id;
	private Date date_purchase;
	public Basket(Integer id, Integer user_id, Integer product_id, Date date_purchase) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.date_purchase = date_purchase;
	}
	public Basket(Integer user_id, Integer product_id, Date date_purchase) {
		super();
		this.user_id = user_id;
		this.product_id = product_id;
		this.date_purchase = date_purchase;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Date getDate_purchase() {
		return date_purchase;
	}
	public void setDate_purchase(Date date_purchase) {
		this.date_purchase = date_purchase;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_purchase == null) ? 0 : date_purchase.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Basket other = (Basket) obj;
		if (date_purchase == null) {
			if (other.date_purchase != null)
				return false;
		} else if (!date_purchase.equals(other.date_purchase))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Basket [id=" + id + ", user_id=" + user_id + ", product_id=" + product_id + ", date_purchase="
				+ date_purchase + "]";
	}
	
	
	
	
}
