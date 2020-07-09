package com.subg.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CardDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long CartDetail_id;
	private long Card_Number;
	private LocalDate Cart_Expiry_Date;
	private String Cart_Type; 
	private String bank_Name;
	private long order_id;
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public long getCartDetail_id() {
		return CartDetail_id;
	}
	public void setCartDetail_id(long cartDetail_id) {
		CartDetail_id = cartDetail_id;
	}
	public long getCard_Number() {
		return Card_Number;
	}
	public void setCard_Number(long card_Number) {
		Card_Number = card_Number;
	}
	public LocalDate getCart_Expiry_Date() {
		return Cart_Expiry_Date;
	}
	public void setCart_Expiry_Date(LocalDate cart_Expiry_Date) {
		Cart_Expiry_Date = cart_Expiry_Date;
	}
	public String getCart_Type() {
		return Cart_Type;
	}
	public void setCart_Type(String cart_Type) {
		Cart_Type = cart_Type;
	}
	public String getBank_Name() {
		return bank_Name;
	}
	public void setBank_Name(String bank_Name) {
		this.bank_Name = bank_Name;
	}
	
	
}
