package com.ontologycentral.ldspider;
import java.net.URI;

import junit.framework.TestCase;

import org.semanticweb.yars.nx.parser.Callback;
import org.semanticweb.yars.util.CallbackNxOutputStream;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilterRdfXml;
import com.ontologycentral.ldspider.hooks.links.LinkFilterDummy;
import com.ontologycentral.ldspider.hooks.sink.SinkCallback;


public class CrawlerTest extends TestCase {
//	public void testCrawl() throws Exception {
//		Crawler c = new Crawler(1);
//
//		Frontier frontier = new BasicFrontier();
//		frontier.add(new URI("http://harth.org/andreas/foaf.rdf"));
//
//		ErrorHandler eh = new ErrorHandlerLogger(System.out, null);
//		c.setErrorHandler(eh);
//	
//		c.evaluateLoadBalanced(frontier, 1);
//	}
	
	
	public void testCrawl2() throws Exception {
		Crawler c = new Crawler(1);

		Frontier frontier = new BasicFrontier();
		frontier.add(new URI("http://harth.org/andreas/foaf.rdf"));
		frontier.add(new URI("http://umbrich.net/foaf.rdf"));

		//frontier.setBlacklist(CrawlerConstants.BLACKLIST);

        c.setFetchFilter(new FetchFilterRdfXml());
        c.setLinkFilter(new LinkFilterDummy());
        
		ErrorHandler eh = new ErrorHandlerLogger(null, null);
		c.setErrorHandler(eh);
		
		Callback cb = new CallbackNxOutputStream(System.out);
		SinkCallback sc = new SinkCallback(cb, true);
		
		c.setOutputCallback(sc);

		c.evaluateBreadthFirst(frontier, 0, -1, -1);
	}
}

