import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: cs400 BST and RBT
//Files:BST.java, RBT.java, TestBST.java, TestRBT.java, compareDS.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 7/20/2020
//Description: This class aims to test the implementments of RBT
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
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test if a Red-Black tree 
// extension of BST is correct.  Mostly check node color and position

//@SuppressWarnings("rawtypes")
public class TestRBT {

	protected RBT<Integer, String> rbt;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		rbt = new RBT<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * CASE 123 Insert three values in sorted order and then check the root, left,
	 * and right keys to see if RBT rebalancing occurred.
	 * 
	 */
	@Test
	void testRBT_001_insert_sorted_order_simple() {
		try {
			rbt.insert(10, "10");
			Assertions.assertTrue(rbt.rootIsBlack());

			rbt.insert(20, "20");
			Assertions.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20));
			Assertions.assertEquals(rbt.colorOf(20), RBT.RED);

			rbt.insert(30, "30"); // SHOULD CAUSE REBALANCING
			Assertions.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assertions.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assertions.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assertions.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();

		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * CASE 321 Insert three values in reverse sorted order and then check the root,
	 * left, and right keys to see if rebalancing occurred in the other direction.
	 */
	@Test
	void testRBT_002_insert_reversed_sorted_order_simple() {
		try {
			rbt.insert(30, "30");
			Assertions.assertTrue(rbt.rootIsBlack());

			rbt.insert(20, "20");
			Assertions.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(20));
			Assertions.assertEquals(rbt.colorOf(20), RBT.RED);

			rbt.insert(10, "10"); // SHOULD CAUSE REBALANCING
			Assertions.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assertions.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assertions.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assertions.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();

		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * CASE 132 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred
	 * correctly.
	 */
	@Test
	void testRBT_003_insert_smallest_largest_middle_order_simple() {

		try {
			rbt.insert(10, "10");
			Assertions.assertTrue(rbt.rootIsBlack());

			rbt.insert(30, "30");
			Assertions.assertTrue(rbt.getKeyOfRightChildOf(10).equals(30));
			Assertions.assertEquals(rbt.colorOf(30), RBT.RED);

			rbt.insert(20, "20"); // SHOULD CAUSE REBALANCING
			Assertions.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assertions.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assertions.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assertions.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();

		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}

	}

	/**
	 * CASE 312 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred
	 * correctly.
	 */
	@Test
	void testRBT_004_insert_largest_smallest_middle_order_simple() {

		try {
			rbt.insert(30, "30");
			Assertions.assertTrue(rbt.rootIsBlack());

			rbt.insert(10, "10");
			Assertions.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(10));
			Assertions.assertEquals(rbt.colorOf(10), RBT.RED);

			rbt.insert(20, "20"); // SHOULD CAUSE REBALANCING
			Assertions.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assertions.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assertions.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assertions.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();

		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert values and check their left and right child node and the color of some
	 * of the nodes in RBT
	 * 
	 * Then check the keys of RBT to see if rebalancing occurred correctly.
	 */
	@Test
	void testRBT_005_insert_largest_smallest_middle_order_simple() {

		try {
			rbt.insert(3, "3");
			rbt.insert(1, "1");
			rbt.insert(5, "5");
			rbt.insert(-1, "-1");
			rbt.insert(0, "0");
			if(rbt.isRed(0)) {
				fail("The method isRed is not correctly implemented.");
				
			}
			if(!rbt.isBlack(5)) {
				fail("The method isBlack is not correctly implemented.");
				
			}
			Assertions.assertEquals(rbt.colorOf(-1), RBT.RED);
			Assertions.assertEquals(rbt.colorOf(0), RBT.BLACK);
			Assertions.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(3));
			Assertions.assertEquals(rbt.getKeyOfLeftChildOf(0), Integer.valueOf(-1));
			Assertions.assertEquals(rbt.getKeyOfRightChildOf(0), Integer.valueOf(1));

			rbt.print();

		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}
	/**
	 * Insert multiple values and then check the height of the tree to see if
	 * getHeight worked correctly.
	 */
	@Test
	void testRBT_006_insert_test_height() {
		try {
			rbt.insert(10, "10");
			rbt.insert(20, "20");
			rbt.insert(30, "30");
			if (rbt.getHeight() != 2)
				fail("The height calcualtion is not correct");
			rbt.insert(45, "45");
			rbt.insert(32, "32");
			rbt.insert(320, "320");
			rbt.insert(321, "321");
			rbt.insert(322, "322");
			if (rbt.numKeys() != 8)
				fail("The number of keys is not correct");
			Assertions.assertEquals(rbt.getHeight(), Integer.valueOf(4));
			rbt.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}
	// test for holding exceptions
		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_007_test_getLeftKey_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.getKeyOfLeftChildOf(null); // get the key of left child of null key
				fail("fail to catch exception.");
			} catch (IllegalNullKeyException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}

		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_008_test_getLeftKey_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.getKeyOfLeftChildOf(1039); // get the key of left child of unexisted key
				fail("fail to catch exception.");
			} catch (KeyNotFoundException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}

		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_009_test_getRightKey_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.getKeyOfRightChildOf(null); // get the key of right child of null key
				fail("fail to catch exception.");
			} catch (IllegalNullKeyException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}

		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_010_test_getRightKey_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.getKeyOfRightChildOf(1039); // get the key of right child of unexisted key
				fail("fail to catch exception.");
				
			} catch (KeyNotFoundException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}
		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_011_test_insert_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.insert(null,"1"); // insert null key
				fail("fail to catch exception.");
			} catch (IllegalNullKeyException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}

		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_012_test_insert_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.insert(25,"25"); //insert duplicate key
				fail("fail to catch exception.");
			} catch (DuplicateKeyException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}
		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_013_test_get_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.get(null); // try to get null key in BST
				fail("fail to catch exception.");
			} catch (IllegalNullKeyException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}
		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_014_test_get_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.get(1); // try to get non-existed key in BST
				fail("fail to catch exception.");
			} catch (KeyNotFoundException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}
		/**
		 * Insert some values to check the implementations of the RBT tree to see if
		 * those methods hold exception
		 */
		@Test
		void testRBT_015_test_contains_exception() {
			try {
				rbt.insert(20, "20");
				rbt.insert(15, "15");
				rbt.insert(25, "25");
				rbt.contains(null); // try to check null key in BST
				fail("fail to catch exception.");
			} catch (IllegalNullKeyException e) {
				rbt.print();
			}catch (Exception e) {
				fail("fail to catch right exception.");
			}
		}

	// TODO: Add your own tests

	// Add tests to make sure that rebalancing occurs even if the
	// tree is larger. Does it maintain it's balance?
	// Does the height of the tree reflect it's actual height
	// Use the traversal orders to check.

	// Can you insert many and still "get" them back out?

	// Does delete work? Does the tree maintain balance when a key is deleted?
	// If delete is not implemented, does calling it throw an
	// UnsupportedOperationException

} // copyright Deb Deppeler, all rights reserved, DO NOT SHARE
