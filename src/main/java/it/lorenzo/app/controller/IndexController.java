package it.lorenzo.app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(value = "/homepage", method = RequestMethod.POST)
	public String homepage() {
		return "homepage";
	}

	@RequestMapping(value = PATH)
	public String error() {
		return "errorPage";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}