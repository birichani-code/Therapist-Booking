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
	
	@GetMapping("/send_text_email")
	public String sendPlainTextEmail(Model model) {
		String from = "birichani.code@gmail.com";
		String to = "birichani25@gmail.com";
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("This is a plain text email");
		message.setText("Hello guys! This is a plain text email.");
		
		mailSender.send(message);
		
		model.addAttribute("message", "A plain text email has been sent");
		return "result";
	}
	
	@GetMapping("/send_html_email")
	public String sendHTMLEmail(Model model) throws MessagingException {
		String from = "birichani.code@gmail.com";
		String to = "birichani25@gmail.com";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setSubject("This is an HTML email");
		helper.setFrom(from);
		helper.setTo(to);

		boolean html = true;
		helper.setText("<b>Hey guys</b>,<br><i>wecome to welCARE hospital</i>", html);

		mailSender.send(message);
		
		model.addAttribute("message", "An HTML email notification has been sent");
		return "result";		
	}
	
	@GetMapping("/send_email_attachment")
	public String sendHTMLEmailWithAttachment(Model model) throws MessagingException {
		
		String from = "birichani.code@gmail.com";
		String to = "birichani25@gmail.com";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setSubject("Here's your e-book");
		helper.setFrom(from);
		helper.setTo(to);
		
		helper.setText("<b>Dear friend</b>,<br><i>Please find the document attached.</i>", true);
		
		FileSystemResource file = new FileSystemResource(new File("/home/solomon/Downloads/solomon birichani (Responses).xlsx"));
		helper.addAttachment("solomon birichani (Responses).xlsx", file);

		mailSender.send(message);
		
		model.addAttribute("message", "An  email with attachment has been sent");
		return "result";		
	}
	
	@GetMapping("/send_email_inline_image")
	public String sendHTMLEmailWithInlineImage(Model model) throws MessagingException {
		
		String from = "birichani.code@gmail.com";
		String to = "birichani25@gmail.com";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setSubject("Here's your pic");
		helper.setFrom(from);
		helper.setTo(to);
		
		String content = "<b>Dear guru</b>,<br><i>Please look at this nice picture:.</i>"
							+ "<br><img src='/home/solomon/Pictures/img5.jpeg'/><br><b>Best Regards</b>";
		helper.setText(content, true);
		
		FileSystemResource resource = new FileSystemResource(new File("/home/solomon/Downloads/solomon birichani (Responses).xlsx"));
		helper.addInline("img5.jpeg", resource);

		mailSender.send(message);
		
		model.addAttribute("message", "An  email with inline image has been sent");
		return "result";		
	}	
}
