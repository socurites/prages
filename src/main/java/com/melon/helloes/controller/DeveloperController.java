package com.melon.helloes.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.melon.helloes.service.MSearchService;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
	@Resource
	private MSearchService searchService;
	
	@RequestMapping("/install")
	public ModelAndView installView() {
		return new ModelAndView("site.developer.install");
	}
}
