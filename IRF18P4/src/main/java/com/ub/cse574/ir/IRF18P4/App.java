package com.ub.cse574.ir.IRF18P4;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SolrServerException, IOException
    {
    	String urlString = "http://localhost:8983/solr/IRF18P1";
    	HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
    	solr.setParser(new XMLResponseParser());
    	SolrQuery query = new SolrQuery();
    	query.set("q", "text_en:Trump");
    	//final SolrClient solr = getSolrClient();
    	QueryResponse response = solr.query(query);
    	 
    	SolrDocumentList docList = response.getResults();
    	 
    	for (SolrDocument doc : docList) {
    	     System.out.println(doc.getFieldValue("text_en"));
//    	     System.out.println("Vishal");
    	     
    	}
    }
    
    private static HttpSolrClient getSolrClient() {
    	final String solrUrl = "localhost:8983/solr/IRF18P1";
    	return new HttpSolrClient.Builder(solrUrl)
    	    .withConnectionTimeout(10000)
    	    .withSocketTimeout(60000)
    	    .build();

	}
}
