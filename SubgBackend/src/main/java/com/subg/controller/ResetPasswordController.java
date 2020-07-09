package com.subg.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subg.dao.PasswordResetTokenDaoRepository;
import com.subg.model.GenericResponse;
import com.subg.model.Mail;
import com.subg.model.PasswordResetToken;
import com.subg.model.Users;
import com.subg.servicedao.EmailServiceDao;
import com.subg.servicedao.UserServiceDao;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class ResetPasswordController {

	@Autowired
	private UserServiceDao userServiceDao;
	
	@Autowired
	private EmailServiceDao emailServiceDao;
	
	@Autowired
	private PasswordResetTokenDaoRepository passwordResetTokenDaoRepository;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/resetPassword/{userEmail}")
	public GenericResponse resetPassword(HttpServletRequest request,@PathVariable(required=false,name="userEmail") String userEmail) { 
		Users users = userServiceDao.findByResetEmail(userEmail);
		if(users == null){
			throw new UsernameNotFoundException(userEmail);
		}
		else {
			PasswordResetToken resetToken  =new PasswordResetToken();
			 resetToken.setToken(UUID.randomUUID().toString());
			 resetToken.setUsers(users);
			 resetToken.setExpiryDate(30);
			 passwordResetTokenDaoRepository.save(resetToken);
			 Mail mail = new Mail();
			 mail.setFrom("Your e-mail id"); //Here enter your email id from this mail will be send.
			 mail.setTo(users.getEmail());
			 mail.setSubject("Password reset request");
			 
			 Map<String, Object> model =  new HashMap<String, Object>();
			 model.put("token", resetToken);
			 model.put("users", users);
			 model.put("signature", "https://subg.com"); //Put the your site signature mean that user can identify the mail 
			 String url = request.getScheme() + "://" + request.getServerName() + ":" + "4200"+"/"+"passwordReset/"+resetToken.getToken();
			 model.put("resetUrl", url + "/reset-password?token=" + resetToken.getToken()); 
		        mail.setModel(model);
		        emailServiceDao.sendEmail(mail,url);
			
		}
		return null;
	}
	
	
	
	@PostMapping("newPaswsword/{newpassword}/{token}")
	public void fornewPassword(@PathVariable(name = "newpassword") String newPassword,@PathVariable(name = "token") String resettoken) {
		System.out.println(newPassword + "         " + resettoken);
		PasswordResetToken passwordResetToken = passwordResetTokenDaoRepository.findByToken(resettoken);
		if(passwordResetToken==null) {
			System.out.println("Token not found!");
		}
		else if (passwordResetToken.isExpired()) {
			System.out.println("token expired!");
		}else {
			Users users = passwordResetToken.getUsers();
			String updatedPassword = passwordEncoder.encode(newPassword);
			userServiceDao.updatePassword(updatedPassword, users.getUserId());
			passwordResetTokenDaoRepository.delete(passwordResetToken);
		}
		
		
		
	}
}
