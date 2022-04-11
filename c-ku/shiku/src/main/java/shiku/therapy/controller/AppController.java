package shiku.therapy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
/*@RequestMapping("/email")*/
public class AppController {

	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/email")
	public String viewHomePage() {
		return "indexx";
	}
	

	
	@GetMapping("/send_html_email")
	public String sendHTMLEmail(Model model) throws MessagingException {
		String from = "birichani.code@gmail.com";
		String to = "birichani25@gmail.com";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setSubject("This is an email notification from Mind counselling therapy");
		helper.setFrom(from);
		helper.setTo(to);

		boolean html = true;
		helper.setText("<b>Hey Dear Client</b>,<br><i>Welcome to Mind counselling Therapy,your appointment is being processed...</i>", html);

		mailSender.send(message);
		
		model.addAttribute("message", "Your Appointment has been Placed successfully,Check on your email");
		return "result";

	}


	

}
