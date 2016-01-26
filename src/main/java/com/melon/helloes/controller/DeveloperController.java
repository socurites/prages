package com.melon.helloes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

	@RequestMapping("/install")
	public ModelAndView installView() {
		return new ModelAndView("site.developer.install");
	}
}
