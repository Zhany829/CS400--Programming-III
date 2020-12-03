////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: cs400 hashTable
//Files: HashTable.java, HashTableTest.java, MyProfiler.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 7/27/2020
//Description: This class aims to test the implementation of HashTable
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
import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.Assertions;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/***
 * This class aims to test the implementation of HashTable
 * @author ZHAN YU
 *
 */
public class HashTableTest {

	// TODO: add other fields that will be used by multiple tests

	// TODO: add code that runs before each test method
	@Before
	public void setUp() throws Exception {

	}

	// TODO: add code that runs after each test method
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Tests that a HashTable returns an integer code indicating which collision
	 * resolution strategy is used. REFER TO HashTableADT for valid collision scheme
	 * codes.
	 */
	@Test
	public void test000_collision_scheme() {
		HashTableADT htIntegerKey = new HashTable<Integer, String>();
		int scheme = htIntegerKey.getCollisionResolution();
		if (scheme < 1 || scheme > 9)
			fail("collision resolution must be indicated with 1-9");
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws
	 * IllegalNullKeyException
	 */
	@Test
	public void test001_IllegalNullKey() {
		try {
			HashTableADT htIntegerKey = new HashTable<Integer, String>();
			htIntegerKey.insert(null, null);
			fail("should not be able to insert null key");
		} catch (IllegalNullKeyException e) {
			/* expected */ } catch (Exception e) {
			fail("insert null key should not throw exception " + e.getClass().getName());
		}
	}
	/**
	 * Insert millions of key and get the value for each of them
	 * @throws IllegalNullKeyException 
	 * @throws KeyNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test002_insertMany_and_get() throws IllegalNullKeyException, KeyNotFoundException {
		//string containing allowed characters, modify according to your needs
	        String strAllowedCharacters = 
	                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        //initialize Random
	        Random random = new Random();
	        String randomStr = "";
			HashTableADT htIntegerKey = new HashTable<Integer, String>();
	
			for(Integer i=0;i<1500001;i++) {
			randomStr = getNextRandomString(strAllowedCharacters, random);
			htIntegerKey.insert(i, randomStr);
			if(!htIntegerKey.get(i).equals(randomStr)) {
				fail("the get method and insert method is not implemented correctly.");
			}						
			}		
	}
	 private static String getNextRandomString(String strAllowedCharacters, Random random) {	        
	        StringBuilder sbRandomString = new StringBuilder(20);	        
	        for(int i = 0 ; i < 20; i++){	            
	            //get random integer between 0 and string length
	            int randomInt = random.nextInt(strAllowedCharacters.length());            
	            //get char from randomInt index from string and append in StringBuilder
	            sbRandomString.append( strAllowedCharacters.charAt(randomInt) );
	        }	        
	        return sbRandomString.toString();
	        
	    }
	 	/**
		 * try to remove one key from the hashtable 
		 * @throws IllegalNullKeyException 
		 */
		@Test
		public void test003_remove() throws IllegalNullKeyException {
			
				HashTableADT htIntegerKey = new HashTable<Integer, String>();
				htIntegerKey.insert(1, "1");
				if(htIntegerKey.numKeys()!=1) {
					fail("The number of key is not correct");
				}
				htIntegerKey.remove(1);
				if(htIntegerKey.numKeys()!=0) {
					fail("The number of key is not correct");
				}
		}
		 /**
		 * try to remove one key from the hashtable 
		 */
		@Test
		public void test004_remove_exception() {
			try {
				HashTableADT htIntegerKey = new HashTable<Integer, String>();
				htIntegerKey.insert(1, "1");
				htIntegerKey.remove(null);
				fail("fail to throw exception when remove a null key.");
			}catch(IllegalNullKeyException e){
					//Expected
				}catch(Exception e) {
					fail("catch the wrong exception");
				}
		}
		 /**
		 * try to insert one key into the hashtable and the get a null key to if throws a IllegalNullKeyException
		 */
		@Test
		public void test005_get_IllegalNullKeyException() {
			try {
				HashTableADT htIntegerKey = new HashTable<Integer, String>();
				htIntegerKey.insert(1,"1");
				htIntegerKey.get(null);
				fail("fail to throw exception when remove a null key.");
			}catch(IllegalNullKeyException e){
					//Expected
				}catch(Exception e) {
					fail("catch the wrong exception");
				}
		}
		 /**
		  * try to insert one key into the hashtable and the get a non-exist key to if throws a keyNotFoundException	
		 */
		@Test
		public void test006_get_KeyNotFoundException() {
			try {
				HashTableADT htIntegerKey = new HashTable<Integer, String>();
				htIntegerKey.insert(1, "1");
				htIntegerKey.get(2);
				fail("fail to throw exception when remove a null key.");
			}catch(KeyNotFoundException e){
					//Expected
				}catch(Exception e) {
					fail("catch the wrong exception");
				}
		}


}
