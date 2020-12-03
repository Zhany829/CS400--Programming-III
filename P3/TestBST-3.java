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
//Description: This class aims to test the implementments of BST
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Students who get help from sources other than their partner and the course
//staff must fully acknowledge and credit those sources here. If you did not
//receive any help of any kind from outside sources, explicitly indicate NONE
//next to each of the labels below.
//
//Persons: NONE
//
//
//
///////////////////////////////////////////////////////////////////////////////

//@SuppressWarnings("rawtypes")
public class TestBST {

	protected STADT<Integer, String> bst;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		bst = new BST<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * CASE 123 Insert three values in sorted order and then check the root, left,
	 * and right keys to see if insert worked correctly.
	 */
	@Test
	void testBST_001_insert_sorted_order_simple() {
		try {
			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(10))
				fail("insert at root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyOfRightChildOf(10).equals(20))
				fail("insert to right child of root does not work");

			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(10))
				fail("inserting 30 changed root");
			if (!bst.getKeyOfRightChildOf(20).equals(30))
				fail("inserting 30 as right child of 20");

			Assertions.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
			Assertions.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
			Assertions.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * CASE 321 Insert three values in reverse sorted order and then check the root,
	 * left, and right keys to see if insert worked in the other direction.
	 */
	@Test
	void testBST_002_insert_reversed_sorted_order_simple() {
		try {
			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(30))
				fail("insert at root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyOfLeftChildOf(30).equals(20))
				fail("insert to left child of root does not work");

			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(30))
				fail("inserting 10 changed root");

			if (!bst.getKeyOfLeftChildOf(20).equals(10))
				fail("inserting 10 as left child of 20");

			Assertions.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
			Assertions.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
			Assertions.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * CASE 132 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if insert occurred
	 * correctly.
	 */
	@Test
	void testBST_003_insert_smallest_largest_middle_order_simple() {
		try {
			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(10))
				fail("insert at root does not work");

			bst.insert(30, "30");
			if (!bst.getKeyOfRightChildOf(10).equals(30))
				fail("insert to right child of root does not work");
			Assertions.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));

			bst.insert(20, "20");
			if (!bst.getKeyAtRoot().equals(10))
				fail("inserting 20 changed root");

			if (!bst.getKeyOfLeftChildOf(30).equals(20))
				fail("inserting 20 as left child of 30");

			Assertions.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
			Assertions.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
			Assertions.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * CASE 312 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if insert occurred
	 * correctly.
	 */
	@Test
	void testBST_004_insert_largest_smallest_middle_order_simple() {
		try {
			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(30))
				fail("insert at root does not work");

			bst.insert(10, "10");
			if (!bst.getKeyOfLeftChildOf(30).equals(10))
				fail("insert to left child of root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyAtRoot().equals(30))
				fail("inserting 10 changed root");

			if (!bst.getKeyOfRightChildOf(10).equals(20))
				fail("inserting 20 as right child of 10");
			Assertions.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
			Assertions.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
			Assertions.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert multiple values and check insert, numOfKeys, contains and get method
	 * 
	 * Then check the root, left, and right keys to see if the implementations work
	 * correctly.
	 */
	@Test
	void testBST_005_insert_then_check_contents() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			if (bst.numKeys() != 2) {
				fail("Method numOfKeys does not work correctly.");
			}
			bst.insert(5, "5");
			bst.insert(0, "0");
			bst.insert(200, "big value");
			if (!bst.get(200).equals("big value"))
				fail("Method get does not work correctly.");
			bst.insert(25, "25");
			bst.insert(6, "6");
			bst.insert(24, "24");
			if (bst.numKeys() != 8) {
				fail("Method numOfKeys does not work correctly.");
			}
			if (bst.contains(10) != true)
				fail("Method contains does not work correctly.");
			if (bst.contains(1000) == true)
				fail("Method contains does not work correctly.");
			bst.insert(7, "value");
			if (!bst.get(7).equals("value"))
				fail("Method get does not work correctly.");
			Assertions.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
			Assertions.assertEquals(bst.getKeyOfLeftChildOf(25), Integer.valueOf(24));
			Assertions.assertEquals(bst.getKeyOfRightChildOf(5), Integer.valueOf(6));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert multiple values and check in order traversal method
	 * 
	 * Then check the node to see if the implementation work correctly.
	 */
	@Test
	void testBST_006_insert_then_check_inorderTraversal() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			bst.insert(5, "5");
			bst.insert(0, "0");
			bst.insert(200, "big value");
			bst.insert(25, "25");
			bst.insert(6, "6");
			bst.insert(24, "24");
			bst.insert(7, "value");
			if (bst.getInOrderTraversal().get(0) != 0)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(1) != 5)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(2) != 6)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(3) != 7)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(4) != 10)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(5) != 20)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(6) != 24)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(7) != 25)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getInOrderTraversal().get(8) != 200)
				fail("Method getInOrderTraversal does not work correctly.");
			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert multiple values and check pre order traversal method
	 * 
	 * Then check the node to see if the implementation work correctly.
	 */
	@Test
	void testBST_007_insert_then_check_inorderTraversal() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			bst.insert(5, "5");
			bst.insert(0, "0");
			bst.insert(200, "big value");
			bst.insert(25, "25");
			bst.insert(6, "6");
			bst.insert(24, "24");
			bst.insert(7, "value");
			if (bst.getPreOrderTraversal().get(0) != 10)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(1) != 5)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(2) != 0)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(3) != 6)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(4) != 7)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(5) != 20)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(6) != 200)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(7) != 25)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPreOrderTraversal().get(8) != 24)
				fail("Method getInOrderTraversal does not work correctly.");
			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert multiple values and check post order traversal method
	 * 
	 * Then check the node to see if the implementation work correctly.
	 */
	@Test
	void testBST_008_insert_then_check_inorderTraversal() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			bst.insert(5, "5");
			bst.insert(0, "0");
			bst.insert(200, "big value");
			bst.insert(25, "25");
			bst.insert(6, "6");
			bst.insert(24, "24");
			bst.insert(7, "value");
			if (bst.getPostOrderTraversal().get(0) != 0)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(1) != 7)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(2) != 6)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(3) != 5)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(4) != 24)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(5) != 25)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(6) != 200)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(7) != 20)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(8) != 10)
				fail("Method getInOrderTraversal does not work correctly.");
			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert multiple values and then check the height of the tree to see if
	 * getHeight worked correctly.
	 */
	@Test
	void testBST_009_insert_test_height() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			bst.insert(30, "30");
			if (bst.getHeight() != 3)
				fail("The height calcualtion is not correct");
			bst.insert(45, "45");
			bst.insert(32, "32");
			bst.insert(320, "320");
			bst.insert(321, "321");
			bst.insert(322, "322");
			if (bst.numKeys() != 8)
				fail("The number of keys is not correct");
			Assertions.assertEquals(bst.getHeight(), Integer.valueOf(7));
			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert multiple values and check level order traversal method
	 * 
	 * Then check the node to see if the implementation work correctly.
	 */
	@Test
	void testBST_010_insert_then_check_levelOrderTraversal() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			bst.insert(5, "5");
			bst.insert(0, "0");
			bst.insert(200, "big value");
			bst.insert(25, "25");
			bst.insert(6, "6");
			bst.insert(24, "24");
			bst.insert(7, "value");
			if (bst.getPostOrderTraversal().get(0) != 0)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(1) != 7)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(2) != 6)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(3) != 5)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(4) != 24)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(5) != 25)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(6) != 200)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(7) != 20)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getPostOrderTraversal().get(8) != 10)
				fail("Method getInOrderTraversal does not work correctly.");
			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert multiple values and then remove some of them check the implementations
	 * of the BST tree to see if the remove worked correctly.
	 */
	@Test
	void testBST_011_insert_test_remove() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			bst.insert(30, "30");
			bst.insert(45, "45");
			bst.insert(32, "32");
			bst.insert(320, "320");
			if (!bst.contains(10))
				fail("The contain method is not correct");
			if (!bst.contains(32))
				fail("The contain method is not correct");
			if (bst.remove(10) != true)
				fail("The remove method is not correct");
			if (bst.remove(320) != true)
				fail("The remove method is not correct");
			if (bst.remove(10) == true)
				fail("The remove method is not correct");
			bst.insert(321, "321");
			bst.insert(322, "322");

			Assertions.assertEquals(bst.numKeys(), Integer.valueOf(6));
			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Insert many values and then remove some of them check the implementations of
	 * the BST tree to see if the remove worked correctly.
	 */
	@Test
	void testBST_012_insert_many_test_remove() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.insert(13, "13");
			bst.remove(13);
			Assertions.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(20));
			if (bst.getHeight() != 2)
				fail("The height method is not correct");
			if (bst.remove(13) == true)
				fail("The remove method is not correct");
			bst.insert(13, "13");
			bst.insert(17, "17");
			bst.insert(23, "23");
			bst.insert(27, "27");
			bst.insert(24, "24");
			bst.insert(10, "10");
			bst.insert(14, "14");
			if (bst.remove(10) != true)
				fail("The remove method is not correct");
			bst.insert(10, "10");
			if (bst.remove(320) == true)
				fail("The remove method is not correct");
			bst.insert(321, "321");
			bst.insert(322, "322");

			Assertions.assertEquals(bst.numKeys(), Integer.valueOf(12));
			Assertions.assertEquals(bst.getKeyOfRightChildOf(23), Integer.valueOf(24));
			Assertions.assertEquals(bst.getKeyOfLeftChildOf(15), Integer.valueOf(13));
			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	// test for holding exceptions
	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_013_test_getLeftKey_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.getKeyOfLeftChildOf(null); // get the key of left child of null key
			fail("fail to catch exception.");
		} catch (IllegalNullKeyException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}

	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_014_test_getLeftKey_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.getKeyOfLeftChildOf(1039); // get the key of left child of unexisted key
			fail("fail to catch exception.");
		} catch (KeyNotFoundException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}

	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_015_test_getRightKey_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.getKeyOfRightChildOf(null); // get the key of right child of null key
			fail("fail to catch exception.");
		} catch (IllegalNullKeyException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}

	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_016_test_getRightKey_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.getKeyOfRightChildOf(1039); // get the key of right child of unexisted key
			fail("fail to catch exception.");
			
		} catch (KeyNotFoundException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}
	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_017_test_insert_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.insert(null,"1"); // insert null key
			fail("fail to catch exception.");
		} catch (IllegalNullKeyException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}

	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_018_test_insert_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.insert(25,"25"); //insert duplicate key
			fail("fail to catch exception.");
		} catch (DuplicateKeyException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}
	/**
	 * Insert some values and then remove to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_019_test_remove_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.remove(100);
			if(bst.numKeys()!=2) {
				fail("Wrongly decrease size after removing key");
			}
			bst.remove(null); // remove null key
			fail("fail to catch exception.");
		} catch (IllegalNullKeyException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}
	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_020_test_get_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.get(null); // try to get null key in BST
			fail("fail to catch exception.");
		} catch (IllegalNullKeyException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}
	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_021_test_get_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.get(1); // try to get non-existed key in BST
			fail("fail to catch exception.");
		} catch (KeyNotFoundException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}
	/**
	 * Insert some values to check the implementations of the BST tree to see if
	 * those methods hold exception
	 */
	@Test
	void testBST_022_test_contains_exception() {
		try {
			bst.insert(20, "20");
			bst.insert(15, "15");
			bst.insert(25, "25");
			bst.contains(null); // try to check null key in BST
			fail("fail to catch exception.");
		} catch (IllegalNullKeyException e) {
			bst.print();
		}catch (Exception e) {
			fail("fail to catch right exception.");
		}
	}
	/**
	 * Insert some values to check the method of level order of the BST tree 
	 */
	@Test
	void testBST_023_test__levelOrder() {
		try {
			bst.insert(10, "10");
			bst.insert(20, "20");
			bst.insert(5, "5");
			bst.insert(0, "0");
			bst.insert(200, "big value");
			bst.insert(7, "7");
			bst.insert(18, "18");
			if (bst.getLevelOrderTraversal().get(0) != 10)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getLevelOrderTraversal().get(1) != 5)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getLevelOrderTraversal().get(2) != 20)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getLevelOrderTraversal().get(3) != 0)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getLevelOrderTraversal().get(4) != 7)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getLevelOrderTraversal().get(5) != 18)
				fail("Method getInOrderTraversal does not work correctly.");
			if (bst.getLevelOrderTraversal().get(6) != 200)
				fail("Method getInOrderTraversal does not work correctly.");
			bst.print();
			

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	// Add tests to make sure that bst grows as expected.
	// Does it maintain it's balance?
	// Does the height of the tree reflect it's actual height
	// Use the traversal orders to check.

	// Can you insert many and still "get" them back out?

	// Does delete work?
	// If delete is not implemented, does calling it throw an
	// UnsupportedOperationException

}
