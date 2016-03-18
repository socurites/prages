package com.prages.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prages.service.impl.EsService;

/**
 * Created by lks21c on 16. 3. 14.
 */
@Controller
public class JspController {
	Logger logger = LoggerFactory.getLogger(JspController.class);

	@Autowired
	EsService esService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(ModelMap map) {
		logger.info("[lks] info");

		esService.search();

		return new ModelAndView("site.dashboard");
	}
}
