package com.melon.helloes.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.melon.helloes.domain.NetworkItem;
import com.melon.helloes.service.MApplyService;

@RestController
@RequestMapping("/apply")
public class ApplicationController {
	@Resource
	private MApplyService applyService;
	
	@RequestMapping("/power_network")
	public ModelAndView powerNetworkView() {
		return new ModelAndView("site.apply.power_network");
	}
	
	@RequestMapping(value="/power_network/search", produces = "application/json; charset=utf8")
	public String powerNetworkSearch(@RequestParam("artistName") String artistName, @RequestParam("size") int size) throws UnsupportedEncodingException {
		Map<String, NetworkItem> powerNetwork = applyService.getPowerNetwork(artistName, size);
		
		Gson gson = new Gson();
		
		return gson.toJson(powerNetwork);
	}
}
