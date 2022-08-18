package br.com.petshop.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	@ResponseBody
	public String hello() {
		return "<a href='/api/Clientes'>Clientes </a><br /> <br />"
				+ "<a href='/api/Pets'>Pets </a>";
	}

}
