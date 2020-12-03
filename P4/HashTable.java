import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;

////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: cs400 hashTable
//Files: HashTable.java, HashTableTest.java, MyProfiler.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 7/27/2020
//Description: This class aims to implementation HashTable
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

// My scheme is chained bucket which is to use arraylist to hold nodes 
//and resize if total number of nodes meet a value.
//
//My hashing algorithm is to use key to get hashcode 
//then use the code to % the total number of buckets which is the size of arraylist to get the index for the node
//
/***
 * This class aims to implementment HashTable
 * 
 * @author Zhan Yu
 *
 * @param <K>        generic type for key
 * @param <V>generic type for value
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
	// A node of chains
	@SuppressWarnings("hiding")
	/**
	 * inner class for creating node
	 *
	 * @param <K> generic type for key
	 * @param <V> generic type for value
	 */
	private class HashNode<K, V> {
		K key;
		V value;
		// Reference to next node
		HashNode<K, V> next;

		// Constructor
		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private double loadFactorThreshold;
	// Current size of hashtable
	private int size;
	private int capacity;
	private final int DEFAULT_CAPACITY = 2;
	private float defaultLoadFactorThreshold = 0.75f;
	// bucketArray is used to store array of chains
	private ArrayList<HashNode<K, V>> bucketArray;
	// Current capacity of array list
	private int numBuckets;

	/**
	 * default no-arg constructor
	 */
	public HashTable() {
		init(DEFAULT_CAPACITY, defaultLoadFactorThreshold);
	}

	/**
	 * constructor of the class
	 * 
	 * @param initialCapacity     initial capacity of hashtable
	 * @param loadFactorThreshold threshold of load factor of this hashtable
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		init(initialCapacity, loadFactorThreshold);
	}

	/**
	 * method for initializing variables
	 * 
	 * @param initialCapacity     initial capacity of hashtable
	 * @param loadFactorThreshold threshold of load factor of this hashtable
	 */
	void init(int initialCapacity, double loadFactorThreshold) {
		this.size = 0;
		this.capacity = initialCapacity;
		this.loadFactorThreshold = loadFactorThreshold;
		bucketArray = new ArrayList<>();
		numBuckets = (int) (capacity / loadFactorThreshold) + 1;
		// Create empty chains
		for (int i = 0; i < numBuckets; i++)
			bucketArray.add(null);
	}

	/**
	 * This implements hash function to find index for a key
	 * 
	 * @param key key for getten index
	 * @return key of node 's index
	 */
	private int getBucketIndex(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return index;
	}

	/**
	 * add a key with a value to the hashtable
	 * 
	 * @param key   key of insert node
	 * @param value value of insert node
	 * @throws IllegalNullKeyException if key is null
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();

		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		HashNode<K, V> head = bucketArray.get(bucketIndex);

		// Check if key is already present
		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		// Insert key in chain
		size++;
		head = bucketArray.get(bucketIndex);
		HashNode<K, V> newNode = new HashNode<K, V>(key, value);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);

		// If load factor goes beyond threshold, then
		// double hash table size
		if ((1.0 * size) / numBuckets >= loadFactorThreshold) {
			ArrayList<HashNode<K, V>> temp = bucketArray;
			bucketArray = new ArrayList<>();
			numBuckets = 2 * numBuckets + 1;
			capacity = 2 * capacity + 1;
			size = 0;
			for (int i = 0; i < numBuckets; i++)
				bucketArray.add(null);

			for (HashNode<K, V> headNode : temp) {
				while (headNode != null) {
					insert(headNode.key, headNode.value);
					headNode = headNode.next;
				}
			}
		}
	}

	/**
	 * remove a key with a value to the hashtable
	 * 
	 * @param key   key of remove node
	 * @param value value of remove node
	 * @throws IllegalNullKeyException if key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		// Apply hash function to find index for given key
		int bucketIndex = getBucketIndex(key);

		// Get head of chain
		HashNode<K, V> head = bucketArray.get(bucketIndex);
		// Search for key in its chain
		HashNode<K, V> prev = null;
		while (head != null) {
			// If Key found
			if (head.key.equals(key))
				break;
			// Else keep moving in chain
			prev = head;
			head = head.next;
		}
		// If key was not there
		if (head == null)
			return false;
		// Reduce size
		size--;
		// Remove key
		if (prev != null)
			prev.next = head.next;
		else
			bucketArray.set(bucketIndex, head.next);
		return true;
	}

	/**
	 * method to get the node with a given key
	 * 
	 * @param key of node needed to be find
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();
		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		if (bucketIndex < 0 || bucketIndex >= bucketArray.size())
			throw new KeyNotFoundException();
		HashNode<K, V> head = bucketArray.get(bucketIndex);

		// Search key in chain
		while (head != null) {
			if (head.key.equals(key))
				return head.value;
			head = head.next;
		}

		// If key not found
		throw new KeyNotFoundException();
	}

	/**
	 * return the scheme number for this implementation
	 */
	@Override
	public int getCollisionResolution() {
		// return scheme: CHAINED BUCKET: array of linked nodes
		return 5;
	}

	/**
	 * return the number of nodes in hashtable
	 */
	@Override
	public int numKeys() {
		return this.size;
	}

	/**
	 * return threshold of load factor for this hashtable
	 */
	@Override
	public double getLoadFactorThreshold() {
		return this.loadFactorThreshold;
	}

	/**
	 * return the current load factor of hashtable
	 */
	@Override
	public double getLoadFactor() {
		return (this.size / this.numBuckets);
	}

	/**
	 * return the capacity of hashtable
	 */
	@Override
	public int getCapacity() {
		return (int) (this.numBuckets * this.loadFactorThreshold);
	}

}

// TODO: add all unimplemented methods so that the class can compile
