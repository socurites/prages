package com.prages.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lks21c on 16. 3. 14.
 */
@Controller
public class JspController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(ModelMap map) {
		return new ModelAndView("site.dashboard");
	}
}
