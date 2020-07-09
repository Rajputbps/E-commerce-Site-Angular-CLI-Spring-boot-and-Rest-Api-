package com.subg.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.subg.dao.RoleDaoRepository;
import com.subg.dao.UserDaoRepository;
import com.subg.model.Categorys;
import com.subg.model.Images;
import com.subg.model.JwtResponse;
import com.subg.model.Orders;
import com.subg.model.Products; 
import com.subg.model.ResponseMessage;
import com.subg.model.Role;
import com.subg.model.RoleName;
import com.subg.model.SubCategorys;
import com.subg.model.Users;
import com.subg.request.Login;
import com.subg.request.SignUp;
import com.subg.security.JwtProvider; 
import com.subg.servicedao.CategoryServiceDao;
import com.subg.servicedao.ImageServiceDao;
import com.subg.servicedao.OrderServiceDao;
import com.subg.servicedao.ProductServiceDao;
import com.subg.servicedao.RatingServiceDao;
import com.subg.servicedao.SubCategoryServiceDao; 

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	 private AuthenticationManager authenticationManager;
	
	@Autowired
	private CategoryServiceDao categoryServiceDao; 
	
	@Autowired
	private ProductServiceDao productServiceDao; 
	
	@Autowired
	private RatingServiceDao ratingServiceDao; 
	
	@Autowired
	private ImageServiceDao imageServiceDao;
	
	@Autowired
	private SubCategoryServiceDao subCategoryServiceDao;
	
	@Autowired
	private OrderServiceDao orderServiceDao;
	
 
	


	 
	 @Autowired
	 UserDaoRepository  userDao;
	 
	 @Autowired
	 RoleDaoRepository roleDao;
	 
	 @Autowired
	 private PasswordEncoder encoder;

	 @Autowired
	 private JwtProvider jwtProvider;

	 
	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getEmail(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtProvider.generateJwtToken(authentication);
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities()));
	    }           

	 
	 
	 
	 
	  @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUp signUpRequest) {

	        if(userDao.existsByEmail(signUpRequest.getEmail())) {
	            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
	                    HttpStatus.BAD_REQUEST);
	        }

	        // Creating user's account
	        Users user = new Users(signUpRequest.getFname(),signUpRequest.getLname(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getPhone(),signUpRequest.getEmail());

 
	        Set<String> strRoles = signUpRequest.getRole();
	        Set<Role> roles = new HashSet<>();

	        strRoles.forEach(role -> {
	        	switch(role) {
		    		case "admin":
		    			Role adminRole = roleDao.findByName(RoleName.ROLE_ADMIN)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		    			roles.add(adminRole);
		    			
		    			break;
		    		case "pm":
		            	Role pmRole = roleDao.findByName(RoleName.ROLE_PM)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		            	roles.add(pmRole);
		            	
		    			break;
		    		default:
		        		Role userRole = roleDao.findByName(RoleName.ROLE_USER)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		        		roles.add(userRole);        			
	        	}
	        });
	        
	        user.setRoles(roles);
	        userDao.save(user);

	        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"),HttpStatus.OK);
	    }
	  
	
 
	  	// Category
	

	@GetMapping("/categoryList")
	public List<Categorys> listCat(){
		return categoryServiceDao.getCategoryList();
	}
		//Product
	
	@PostMapping("/addProduct")
	private ResponseEntity<?> addProduct(@RequestBody Products product) {
		productServiceDao.addProduct(product);
		return new ResponseEntity<>(new ResponseMessage("Product Added successfully!"),HttpStatus.OK);	 
	}
	
	@PostMapping("/addProductImg")
	private List<String> uploadImage(@RequestParam("files") MultipartFile[] files,@RequestParam("product_id") long product_id)
	{
		System.out.println("---------------->"+product_id);
	        return Arrays.asList(files).stream().map(file -> uploadFile(file, product_id)).collect(Collectors.toList());
	}
	
	 
	
	

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("product_id") long product_id) {
		
		Images  images = new Images(); 
		
		String imagePath = "/home/rajput/Angular/Subg/src/assets/product/";
		System.out.println("Path found");
		
		imagePath=imagePath+String.valueOf(product_id+"-"+file.getOriginalFilename());
			
			System.out.println("Path is: "+ imagePath);
			if(!file.isEmpty())
				{
				System.out.println(file);
				try
					{
						byte[] bytes=file.getBytes();
						Path  path = Paths.get(imagePath);
						Files.write(path, bytes);
             			 System.out.println("File Uploaded Successfully");
             			  
					}
				catch(Exception e)
				{
					System.out.println("Exception Arised"+e);
				}
				}
			else
			{
				System.out.println("File is Empty not Uploaded");
			}
			images.setProduct_id(product_id);
			images.setImg_url(product_id+"-"+file.getOriginalFilename());
			imageServiceDao.addImages(images);
		 return "Updated" ;
	}

 
	
	
	@GetMapping("/getCategoryLisyById/{catid}")
	public Optional<Categorys> getCategoryLisyById(@PathVariable(name = "catid") long catid) {
		System.out.println(catid);
		return categoryServiceDao.getCategory(catid);
	}
	
		@GetMapping("/productList/{id}")
		public SubCategorys productList(@PathVariable(name= "id") Long id){
			return subCategoryServiceDao.getSubCategoryProduct(id);
		}
		
		@GetMapping("/AllProductList")
		public List<Products> getAllProductList(){
			return productServiceDao.getProductList();
		}
		
		@GetMapping("/fullProductDetail/{id}")
		public Products getFullProduct(@PathVariable(name = "id") Long product_id){
			return productServiceDao.getProductFullDetail(product_id);
		}
		
		@GetMapping("/searchProduct/{name}")
		public List<Products> productSearchedName(@PathVariable(name="name") String name){
			System.out.println("hggggggggggggggggggggggggggggggg");
			return productServiceDao.findByName(name);
		} 

//		@GetMapping("/searchItems/{item}")
//		public List<Categorys> searchItems(@PathVariable(name = "item") String item){
//			return categoryServiceDao.findByName(item);
//		}
		
		
		
		
		@GetMapping("/getRating")
		public double getRating() {
			return ratingServiceDao.getRating();
		}
		
		@GetMapping("/getProductRating/{product_id}")
		public double getProductRating(@PathVariable(name = "product_id") long product_id) {
			return ratingServiceDao.getProductRating(product_id);
		}
		
		


}
