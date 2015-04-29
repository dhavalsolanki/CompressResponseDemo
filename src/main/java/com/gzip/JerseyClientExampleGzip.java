package com.gzip;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientExampleGzip {

	public static void main(String[] args) {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://www.apache.org/");

//			webResource.header("Accept-Encoding", "gzip");
			webResource.setProperty("Accept-Encoding", "gzip");
			ClientResponse response = webResource.header("Accept-Encoding", "gzip").accept("application/json")
					.get(ClientResponse.class);
			MultivaluedMap<String, String> headers = response.getHeaders();

			for (Entry<String, List<String>> s : headers.entrySet()) {
				System.out.println(" key  " + s.getKey());
				System.out.println(" value  " + s.getValue());
			}
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			System.out.println(" size " + response.getLength());

			
//			String output = response.getEntity(String.class);
//			System.out.println(" type " + response.getType());
//			System.out.println("Output from Server .... \n");
//			System.out.println(output);
			
			
			InputStream inputStream =  response.getEntityInputStream();
			
			byte[] bs = IOUtils.toByteArray(inputStream);
			GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bs));
	        BufferedReader bf = new BufferedReader(new InputStreamReader(gis));
	        String outStr = "";
	        String line;
	        while ((line=bf.readLine())!=null) {
	          outStr += line;
	        }
	        System.out.println("Output String lenght : " + outStr);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
