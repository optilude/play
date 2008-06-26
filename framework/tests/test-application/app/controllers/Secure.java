package controllers;

import play.mvc.Controller;
import play.mvc.Before;
import play.libs.OpenID;

public class Secure extends Controller {
	
	@Before(unless={"Secure.authenticate"})
	static void checkAuth() throws Exception {
		System.out.println(session);
		if(session.get("id") == null) {
			OpenID.verify("http://guillaume.bort.fr", "Secure.authenticate");
		} 
	}
	
	public static void authenticate() throws Exception  { 
		session.put("id", OpenID.getVerifiedID());
		index();
	}
	
	public static void index() {
		renderText("Hello %s", session.get("id"));
	}
	
}