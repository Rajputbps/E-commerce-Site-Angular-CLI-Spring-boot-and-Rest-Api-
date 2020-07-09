package com.subg.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.subg.payment.PaymentMode;
import com.subg.payment.PaymentStatus;


@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Order_Id;
	private LocalDate Order_Data;
	private LocalTime Order_Time;
	private long Transaction_Id;
	private long User_Id;
	private long Address_Id; 
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus; 
	
	@Temporal(TemporalType.DATE)
	private Date paymentDate; 
	private String txnId; 
	private String mihpayId; 
	
	@Enumerated(EnumType.STRING)
    private PaymentMode mode;
	
	
	@OneToMany(mappedBy="orderId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<OrderList> orderLists;
	
//	@OneToMany(mappedBy = "order_id",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	private Set<CardDetail> cardDetail;
	
	@OneToOne(targetEntity = Address.class)
	@JoinColumn(name = "Address_Id",nullable = false,insertable = false,updatable = false)
	private Address address;
	
	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	 
	public long getOrder_Id() {
		return Order_Id;
	}

	public void setOrder_Id(long order_Id) {
		Order_Id = order_Id;
	}

	public LocalDate getOrder_Data() {
		return Order_Data;
	}

	public void setOrder_Data(LocalDate order_Data) {
		Order_Data = order_Data;
	}

	public LocalTime getOrder_Time() {
		return Order_Time;
	}

	public void setOrder_Time(LocalTime order_Time) {
		Order_Time = order_Time;
	}

	public long getTransaction_Id() {
		return Transaction_Id;
	}

	public void setTransaction_Id(long transaction_Id) {
		Transaction_Id = transaction_Id;
	}

	public long getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(long user_Id) {
		User_Id = user_Id;
	}

	public long getAddress_Id() {
		return Address_Id;
	}

	public void setAddress_Id(long address_Id) {
		Address_Id = address_Id;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getMihpayId() {
		return mihpayId;
	}

	public void setMihpayId(String mihpayId) {
		this.mihpayId = mihpayId;
	}

//	public Set<CardDetail> getCardDetail() {
//		return cardDetail;
//	}
//
//	public void setCardDetail(Set<CardDetail> cardDetail) {
//		this.cardDetail = cardDetail;
//	}

	public Set<OrderList> getOrderLists() {
		return orderLists;
	}

	public void setOrderLists(Set<OrderList> orderLists) {
		this.orderLists = orderLists;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	 
	
	
	
}
