
// Used as the data structure to test our hash table against Tree Map
import java.util.TreeMap;
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: cs400 hashTable
//Files: HashTable.java, HashTableTest.java, MyProfiler.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 7/27/2020
//Description: This class aims to create my profile
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Students who get help from sources other than their partner and the course
//staff must fully acknowledge and credit those sources here. If you did not
//receive any help of any kind from outside sources, explicitly indicate NONE
//next to each of the labels below.
//
//Persons: NONE
//Online Sources: 
//
///////////////////////////////////////////////////////////////////////////////
/***
 * This class aims to create my profile
 * @author ZHAN YU
 *
 * @param <K> generic type
 * @param <V> generic type
 */
public class MyProfiler<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;

	public MyProfiler() {
		hashtable = new HashTable<K, V>();
		treemap = new TreeMap<K, V>();
	}

	public void insert(K key, V value) throws IllegalNullKeyException {		
			hashtable.insert(key, value);
			treemap.put(key, value);
	}

	public void retrieve(K key) throws IllegalNullKeyException, KeyNotFoundException {
		hashtable.get(key);
		treemap.get(key);
	}

	public static void main(String[] args) {
		try {
			int numElements = Integer.parseInt(args[0]);
			MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();
			for (Integer i = 0; i < numElements; i++) {
				profile.insert(i,i);
			}
			for (Integer i = 0; i < numElements; i++) {
				profile.retrieve(i);
			}
			String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Usage: java MyProfiler <number_of_elements>");
			System.exit(1);
		}
	}
}
