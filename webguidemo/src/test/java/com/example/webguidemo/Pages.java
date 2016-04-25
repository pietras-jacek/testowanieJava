package com.example.webguidemo;

import org.jbehave.web.selenium.WebDriverProvider;

import com.example.webguidemo.pages.Post;
import com.example.webguidemo.pages.Home;

public class Pages {

	private WebDriverProvider driverProvider;
	
	//Pages
	private Home home;
	private Post post;
	// ...

	public Pages(WebDriverProvider driverProvider) {
		super();
		this.driverProvider = driverProvider;
	}

	public Home home() {
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
	
	public Post post(){
		if (post == null) {
			post = new Post(driverProvider);
		}
		return post;
	}
	
}
