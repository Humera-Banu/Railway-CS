package com.RrsCaseStudy.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginResource {
	@RequestMapping("/login")
	public String Disp()
	{
		return "Hello World";
	}

}

