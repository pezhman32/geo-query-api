package com.goeuro.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class App {

    public static void main(String[] args) {
	    HttpClient client = new DefaultHttpClient();
	    if (StringUtils.isBlank("1")) {
		    System.out.println("hah?");
	    } else {
		    System.out.println("hah2");
	    }
	    System.out.println("here is the output 4");
    }
}
