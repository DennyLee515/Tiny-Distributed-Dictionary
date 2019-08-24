/**
 * COMP90015 Distributed System
 * Assignment 1 multi-thread dictionary server
 * Tutor:Minxian Xu
 * Name: Dongming Li
 * Student ID:1002971
 * Email：dongming@student.unimelb.edu.au
 */

package com.dictionary.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Repository implements Serializable{
	public Repository(){
		this.setWord("apple", "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila).");
		this.setWord("peach","A peach is a soft, juicy and fleshy stone fruit produced by a peach tree.");
	}
	private static Map<String,String> dictionary = new HashMap<String,String>();

	public Map<String, String> getDictionary() {
		return dictionary;
	}

	public void setDictionary(Map<String, String> dictionary) {
		this.dictionary = dictionary;
	}
	
	public void setWord(String word,String meaning) {
		dictionary.put(word, meaning);
	}
	public boolean checkWordExists(String word) {
		return dictionary.containsKey(word);
	}
	
	public String findByWord(String word) {
		return dictionary.get(word);
	}

	public void deleteByWord(String word) {
		dictionary.remove(word);
	}
	
	
}
