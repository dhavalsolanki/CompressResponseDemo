package com.gzip;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GzipLoginDemo {

	public static void main(String[] args) {
		
		try {
			
			Date startTime = new Date();
			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:7171/hmis/masters/getData");
//			String input = "{\"userId\" : \"ad001\", \"password\" : \"Admn1\", \"appType\" : \"ThirdParty\", \"hospitalType\" : \"UNM\"}";

			
			List<String> mastersName = getMastersName();
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			String input = mapper.writeValueAsString(mastersName);
			ClientResponse response = webResource.header("Accept-Encoding", "gzip")
					.header("token_id", "590c82aa-1935-4b31-95c9-462b1b81e865")
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.type("application/json").post(ClientResponse.class, input);
			
			
			
			MultivaluedMap<String, String> headers = response.getHeaders();

			for (Entry<String, List<String>> s : headers.entrySet()) {
				System.out.println(" key  " + s.getKey());
				System.out.println(" value  " + s.getValue());
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
	        
	        ResponseBean responseBean = mapper.readValue(outStr, 
	    		    ResponseBean.class);
	        
	        
	        Map<MastersEnum, String> map = (HashMap<MastersEnum, String>)responseBean.getData();
//	        System.out.println(" map :-- " + map);
	        
	        for(Entry<MastersEnum, String> s : map.entrySet()) {
	        	System.out.println(" key :-- " + s.getKey());
	        	System.out.println(" value :-- " + s.getValue());
	        }
	        
	        
	        
	        
//	        System.out.println("Output String lenght : " + outStr);
//			System.out.println(" output string lenfgth " + outStr.length());
//			
			System.out.println("Start time " + startTime);
			System.out.println(" end time :-- " + new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<String> getMastersName() {
		List<String> list = new ArrayList<String>();
		for(MastersEnum enum1 : MastersEnum.values()) {
			list.add(enum1.name());
		}
		return list;
	}
}
