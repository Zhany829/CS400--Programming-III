import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: cs400 BST and RBT
//Files:BST.java, RBT.java, TestBST.java, TestRBT.java, compareDS.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 7/20/2020
//Description: This class aims to compare efficiency of BST and RBT insertion
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
//
///////////////////////////////////////////////////////////////////////////////
/***
 *  This class aims to compare efficiency of BST and RBT insertion
 * @author Zhan Yu
 *
 */
public class CompareDS {
	protected  STADT<Integer, String> bst;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void BSTsetUp() throws Exception {
		bst = new BST<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void BSTtearDown() throws Exception {
	}
	protected  RBT<Integer, String> rbt;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void RBTsetUp() throws Exception {
		rbt = new RBT<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void RBTtearDown() throws Exception {
	}
	
	public  static void main(String[] args) {
	       new CompareDS().trialOne();
	       new CompareDS().trialTwo();
	       new CompareDS().trialThree();
	}
	/**
	 * This method aims to insert 100 items in BST and RBT and then compare their efficiency
	 */
	public void trialOne(){
		 System.out.println("CompareDS.main Compares work time for: BST and RBT.");
		    System.out
		        .println("Description: Compare the time for inserting 100 items for each implementation.");
		    long bstStartTime1 = System.nanoTime();
		    
		    System.out.println("BST is doing work for 100 values");
		    bst = new BST<Integer, String>();
		    for (int i = 0; i < 100; i++) {
		    	try {
					bst.insert(i, String.valueOf(i));
				} catch (IllegalNullKeyException e) {
					e.printStackTrace();
				} catch (DuplicateKeyException e) {
					e.printStackTrace();
				}
		    }
		    long bstEndTime1 = System.nanoTime();
		    long bstElapsedTime1 = bstEndTime1 - bstStartTime1;
		    System.out.println("It took " + bstElapsedTime1 + " ns to process 100 items");

		    long rbtStartTime1 = System.nanoTime();
		    System.out.println("RBT is doing work for 100 values.");
		    rbt = new RBT<Integer, String>();
		    for (int i = 0; i < 100; i++) {
		    	try {
					rbt.insert(i, String.valueOf(i));
				} catch (IllegalNullKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    long rbtEndTime1 = System.nanoTime();
		    long rbtElapsedTime1 = rbtEndTime1 - rbtStartTime1;
		    System.out.println("It took " + rbtElapsedTime1 + " ns to process 100 items");
	}
	/**
	 * This method aims to insert 1000 items in BST and RBT and then compare their efficiency
	 */
	public void trialTwo(){
		 System.out.println("CompareDS.main Compares work time for: BST and RBT.");

		    System.out
		        .println("Description: Compare the time for inserting 100 items for each implementation.");
		    long bstStartTime1 = System.nanoTime();
		    
		    System.out.println("BST is doing work for 1000 values");
		    bst = new BST<Integer, String>();
		    for (int i = 0; i < 1000; i++) {
		    	try {
					bst.insert(i, String.valueOf(i));
				} catch (IllegalNullKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    long bstEndTime1 = System.nanoTime();
		    long bstElapsedTime1 = bstEndTime1 - bstStartTime1;
		    System.out.println("It took " + bstElapsedTime1 + " ns to process 1000 items");

		    long rbtStartTime1 = System.nanoTime();
		    System.out.println("RBT is doing work for 1000 values.");
		    rbt = new RBT<Integer, String>();
		    for (int i = 0; i < 1000; i++) {
		    	try {
					rbt.insert(i, String.valueOf(i));
				} catch (IllegalNullKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    long rbtEndTime1 = System.nanoTime();
		    long rbtElapsedTime1 = rbtEndTime1 - rbtStartTime1;
		    System.out.println("It took " + rbtElapsedTime1 + " ns to process 1000 items");
	}
	/**
	 * This method aims to insert 10000 items in BST and RBT and then compare their efficiency
	 */
	public void trialThree(){
		 System.out.println("CompareDS.main Compares work time for: BST and RBT.");
		    // trial one
		    System.out
		        .println("Description: Compare the time for inserting 10000 items for each implementation.");
		    long bstStartTime1 = System.nanoTime();
		    
		    System.out.println("BST is doing work for 10000 values");
		    bst = new BST<Integer, String>();
		    for (int i = 0; i < 10000; i++) {
		    	try {
					bst.insert(i, String.valueOf(i));
				} catch (IllegalNullKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    long bstEndTime1 = System.nanoTime();
		    long bstElapsedTime1 = bstEndTime1 - bstStartTime1;
		    System.out.println("It took " + bstElapsedTime1 + " ns to process 10000 items");

		    long rbtStartTime1 = System.nanoTime();
		    System.out.println("RBT is doing work for 10000 values.");
		    rbt = new RBT<Integer, String>();
		    for (int i = 0; i < 10000; i++) {
		    	try {
					rbt.insert(i, String.valueOf(i));
				} catch (IllegalNullKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    long rbtEndTime1 = System.nanoTime();
		    long rbtElapsedTime1 = rbtEndTime1 - rbtStartTime1;
		    System.out.println("It took " + rbtElapsedTime1 + " ns to process 10000 items");
	}
}
