package com.subg.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subg.model.Address;
import com.subg.model.CardDetail;
import com.subg.model.Cart;
import com.subg.model.OrderList;
import com.subg.model.Orders; 
import com.subg.model.Rating;
import com.subg.model.ResponseMessage;
import com.subg.model.Users;
import com.subg.payment.PaymentCallback;
import com.subg.payment.PaymentDetail;
import com.subg.payment.PaymentMode;
import com.subg.servicedao.AddresServiceDao;
import com.subg.servicedao.CardDetailServiceDao;
import com.subg.servicedao.CartServiceDao;
import com.subg.servicedao.OrderListServiceDao;
import com.subg.servicedao.OrderServiceDao; 
import com.subg.servicedao.RatingServiceDao;
import com.subg.servicedao.UserServiceDao;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	private UserServiceDao userServiceDao;
	
	
	@Autowired
	private CartServiceDao cartServiceDao;
	
	@Autowired
	private RatingServiceDao ratingServiceDao; 
	
	@Autowired
	private AddresServiceDao addresServiceDao;
	
	@Autowired
	private OrderServiceDao orderServiceDao;
	
	@Autowired
	private OrderListServiceDao orderListServiceDao;
	
	@Autowired
	private CardDetailServiceDao cardDetailServiceDao; 
	
	@PutMapping("/Userupdate")
	public Users updateUser(@RequestBody Users user) {
		return userServiceDao.updateUser(user);
	}
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public List<Users> UserList() {
		return userServiceDao.getUsersList();
		
	}
	@GetMapping("/User/{userId}")
	public Optional<Users> getUser(@PathVariable(name = "userId") Long userId) {
       return userServiceDao.getUser(userId);		
	}
	@DeleteMapping("/Delete/User{userId}")
	public void deletUser(@PathVariable(name = "userId") Long userId) {
		userServiceDao.deleteUser(userId);
		
	}
	
	 	
	  @GetMapping("/UserProfile")
	  @PreAuthorize("hasRole('USER')")
		public Optional<Users> getUser( Authentication  authentication) {
		  System.out.println(authentication.getName().toString());
		//   Optional<Users>  users = userServiceDao.findByEmail( authentication.getName().toString());
		//   Long user_id = users.get().getUserId();
	       return userServiceDao.findByEmail( authentication.getName().toString());
		}
 
	  @PostMapping("/addAddress")
	  @PreAuthorize("hasRole('USER')")
	  public ResponseEntity<?> addAddress(@RequestBody Address address,Authentication authentication) {
		  Optional<Users>  users = userServiceDao.findByEmail( authentication.getName().toString());
		  Long user_id = users.get().getUserId();
		  address.setUserId(user_id);
		  addresServiceDao.addAddress(address);
		  return new ResponseEntity<>(new ResponseMessage("Address Added successfully!"),HttpStatus.OK);
	  }
	  
	  @GetMapping("/getUserAddress")
	  @PreAuthorize("hasRole('USER')")
	  public List<Address> getUserAddress(Authentication authentication,Address address){
		  Optional<Users>  users = userServiceDao.findByEmail( authentication.getName().toString());
		  Long user_id = users.get().getUserId();
		  return addresServiceDao.getUserAddress(user_id);
	  }

	
	  @GetMapping("/cartList")
			@PreAuthorize("hasRole('USER')")
			public List<Cart> getAllCartItems(Authentication authentication,Cart cart){
			 // System.out.println(authentication.getName().toString());
		  	Optional<Users>  users = userServiceDao.findByEmail( authentication.getName().toString());
		  	Long user_id = users.get().getUserId();
				return cartServiceDao.findByAllCartItems(user_id);
			}
			
 
	  @PostMapping("/addCart/{product_id}")
	  @PreAuthorize("hasRole('USER')")
	  public Cart addCart(@PathVariable(name = "product_id") Long product_id,Authentication authentication,Cart cart) {
		  Optional<Users> User_email= userServiceDao.findByEmail(authentication.getName().toString());
		  cart.setUser_id(User_email.get().getUserId());
		  cart.setProduct_id(product_id);
		  return cartServiceDao.addCart(cart);
	  }
	
	  @DeleteMapping("/deleteCartItem/{id}")
	  @PreAuthorize("hasRole('USER')")
	  public void deleteCartItems(@PathVariable(name = "id") Long id,Authentication authentication) {
		   cartServiceDao.deleteCartItem(id);
	  }
	  

	  @GetMapping("/getCartCount")
	  @PreAuthorize("hasRole('USER')")
	  public int getCartCount(Authentication authentication){
		Optional<Users>  users = userServiceDao.findByEmail( authentication.getName().toString());
		Long user_id = users.get().getUserId();
		return cartServiceDao.countCartItems(user_id);
	  }

	  @PostMapping("/giveRating")
	  @PreAuthorize("hasRole('USER')")
		public Rating addRating(@RequestBody Rating rating, Authentication authentication) {
			Optional<Users> User_email= userServiceDao.findByEmail(authentication.getName().toString());
			  rating.setUser_id(User_email.get().getUserId());
			  return ratingServiceDao.addRating(rating);
		}
	  

	  @PostMapping("singleCheckOut/")
	  @PreAuthorize("hasRole('USER')")
	  public ResponseEntity<?> singleChackout(@PathVariable("")long address_Id,@PathVariable("")long product_Quantity,@PathVariable("")long produtc_id,OrderList orderList,Orders orders,Authentication authentication) {
		  Optional<Users> User_email= userServiceDao.findByEmail(authentication.getName().toString()); 
		  orders.setUser_Id(User_email.get().getUserId());
		  orders.setAddress_Id(address_Id); 
		  orderServiceDao.addOrder(orders);
		  orderList.setOrderId(orders.getOrder_Id());
		  orderList.setProduct_id(produtc_id);
		  orderList.setProduct_Quantity(product_Quantity);
		  orderListServiceDao.addorderList(orderList);
		  return ResponseEntity.ok(orders.getOrder_Id());
	  }
	  
	  @PostMapping("cardDetail/")
	  @PreAuthorize("hasRole('USER')")
	  public ResponseEntity<?> finalOrder(@RequestBody CardDetail cardDetail,Authentication authentication,Orders orders) {
		  String message;
		  if(true) {
			  orders.setTransaction_Id(1212121212);
			  LocalTime localTime = LocalTime.now();
			  LocalDate localDate = LocalDate.now();
			  orders.setOrder_Time(localTime);
			  orders.setOrder_Data(localDate); 
			  orderServiceDao.addOrder(orders);
			  cardDetailServiceDao.addCardDetail(cardDetail);
			  message="Order SuccessFull!";
		  }
		  else {
			  message="Order Failed!";
		  }
		  
		  return ResponseEntity.ok(message);
	  }
	  
	  
	  @GetMapping("/getOrders")
	  public List<Orders> getOrder() {
		  return orderServiceDao.getAllOrder();
	  }
	  
	  
	  
	  
	  
	  @PostMapping(path = "/payment-details")
	  @PreAuthorize("hasRole('USER')")
	    public PaymentDetail proceedPayment(@RequestBody PaymentDetail paymentDetail,Authentication authentication){
		  Optional<Users> user= userServiceDao.findByEmail(authentication.getName().toString());
		  paymentDetail.setEmail(user.get().getEmail());
		  //paymentDetail.setUser_id(user.get().getUserId());
		  paymentDetail.setPhone("9768420087");
		  paymentDetail.setFirstname(user.get().getFname());
		  paymentDetail.setService_provider("payu_paisa");
	        return orderServiceDao.proceedPayment(paymentDetail);
	    }

	    @RequestMapping(path = "/payment-response", method = RequestMethod.POST)
	    public String payuCallback(@RequestParam String mihpayid, @RequestParam String status, @RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash){
	        PaymentCallback paymentCallback = new PaymentCallback();
	        PaymentDetail detail = new PaymentDetail();
	        System.out.println(detail.getFirstname());
	        System.out.println("cooooooooo");
	        paymentCallback.setMihpayid(mihpayid);
	        paymentCallback.setTxnid(txnid);
	        paymentCallback.setMode(mode);
	        paymentCallback.setHash(hash);
	        paymentCallback.setStatus(status);
	        return orderServiceDao.payuCallback(paymentCallback);
	    }
	  
	  
	  
	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
		response.addHeader("Access-Control-Max-Age", "3600");
	}
}
