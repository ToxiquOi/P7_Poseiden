package com.nnk.springboot.poseidon.controllers;

import com.nnk.springboot.poseidon.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
public class HomeController
{
	private final BidListService bidService;

	@Autowired
	public HomeController(BidListService bidService) {
		this.bidService = bidService;
	}

	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		model.addAttribute("bidlists", bidService.reads());
		return "redirect:/bidList/list";
	}


}
