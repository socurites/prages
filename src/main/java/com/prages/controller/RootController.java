package com.prages.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lks21c on 16. 3. 14.
 */
@RestController
public class RootController {

	@RequestMapping("")
	public String index() {
		return "Hello World";
	}
}
