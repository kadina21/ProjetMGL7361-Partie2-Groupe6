package com.example.newspaper.outils;

import java.util.Comparator;

import org.json.simple.JSONObject;

public class ComparateurJSON implements Comparator<JSONObject> {

	public ComparateurJSON() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compare(JSONObject o1, JSONObject o2) {
	    String v1 = (String) ((JSONObject) o1.get("attributes")).get("COMMERCIALNAME_E");
	    String v3 = (String) ((JSONObject) o2.get("attributes")).get("COMMERCIALNAME_E");
	    return v1.compareTo(v3);
	}

}
