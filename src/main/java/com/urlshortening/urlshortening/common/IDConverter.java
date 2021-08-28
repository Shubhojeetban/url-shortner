package com.urlshortening.urlshortening.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IDConverter {
	public static final IDConverter INSTANCE = new IDConverter();

	private static HashMap<Character, Integer> chartoIndexTable;
	private static List<Character> indexToCharTable;

	private IDConverter() {
		initializeCharToIndexTable();
		initializeIndextoCharTable();
	}

	private void initializeCharToIndexTable() {
		chartoIndexTable = new HashMap<Character, Integer>();

		// for a-> 0, b->1, c->2 etc.
		for (int i = 0; i < 26; i++) {
			char ch = 'a';
			ch += i;

			chartoIndexTable.put(ch, i);
		}

		// for A-> 26, B-> 27, C->28 etc
		for (int i = 26; i < 52; i++) {
			char ch = 'A';
			ch += (i - 26);

			chartoIndexTable.put(ch, i);
		}

		// for 0-> 52, 1->53, 2->54 etc
		for (int i = 52; i < 62; i++) {
			char ch = '0';
			ch += (i - 52);

			chartoIndexTable.put(ch, i);
		}
	}

	private void initializeIndextoCharTable() {
		indexToCharTable = new ArrayList<Character>();

		for (int i = 0; i < 26; i++) {
			char ch = 'a';
			ch += i;

			indexToCharTable.add(ch);
		}

		for (int i = 26; i < 52; i++) {
			char ch = 'A';
			ch += (i - 26);

			indexToCharTable.add(ch);
		}

		for (int i = 52; i < 62; i++) {
			char ch = '0';
			ch += (i - 52);

			indexToCharTable.add(ch);
		}
	}
	
	//function to take the id and convert it into the URL
	public String createUniqueueID(long Id) {
		List<Integer> base62ID = convertBase10To62(Id);
		StringBuilder uniqueueURLID = new StringBuilder();
		
		for(Integer it : base62ID) {
			uniqueueURLID.append(indexToCharTable.get(it));
		}
		return uniqueueURLID.toString();
	}
	
	private List<Integer> convertBase10To62(long Id) {
		List<Integer> digits = new LinkedList<Integer>();
		
		while(Id > 0) {
			int rem = (int)Id % 62;
			digits.add(rem);
			Id = Id /62;
		}
		return digits;
	}
	
	
	//function to take the URL and convert it into th id
	public long getDictionaryKeyFromUniqueID(String uniqueId) {
		List<Character> base62Number = new LinkedList<Character>();
		for(int i =0; i < uniqueId.length(); i++) {
			base62Number.add(uniqueId.charAt(i));
		}
		long dictionaryKey = convertBase62ToBase10ID(base62Number);
		
		return dictionaryKey;
	}
	
	private long convertBase62ToBase10ID(List<Character> ids) {
		long id = 0L;
		long exp = ids.size()-1;
		
		for(int i= 0; i < ids.size(); i++) {
			id += (chartoIndexTable.get(ids.get(i)) * Math.pow(62.0, exp));
			exp = exp-1;
		}
		
		return id;
	}
}
