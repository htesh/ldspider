package com.ontologycentral.ldspider.robot;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.http.ConnectionManager;


/**
 * 
 * @author andhar
 *
 */
		
public class Robots {
	Logger _log = Logger.getLogger(this.getClass().getName());

	Map<String, Robot> _robots;

	private ConnectionManager _cm;
	private ErrorHandler _eh;

	
	public Robots(ConnectionManager cm, ErrorHandler eh) {
		_cm = cm;
		_robots = new HashMap<String, Robot>();
		_eh = eh;
	}
	
    

    public boolean accessOk(URI uri) {
    	String host = uri.getAuthority();

		Robot r = null;

    	synchronized (this) {
    		if (_robots.containsKey(host)) {
    			r = _robots.get(host);
    		} else {
    			r = new Robot(_cm, _eh, host);
    			
    			_robots.put(host, r);
    		}
    	}
    	
		URL url = null;
		try {
			url = uri.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		
    	return r.isUrlAllowed(url);
    }
}