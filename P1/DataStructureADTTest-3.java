//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: cs400 Junit testing
// Files: DS_My.java, DataStructureADTTest.java, TestDS_My.java, CompareDS.java
// Author: ZHAN YU
// Email: zyu293@wisc.edu
// Course: SU20 CS 400 002
// Deadline: 7/3/2020
// Description: This program aims to Implement, test, and
// compare alternate implementations of an abstract data type (ADT).
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/***
 * This class aims to test multiple implementations of the data structure
 * 
 * @author Zhan Yu
 *
 * @param <T>
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T ds;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    ds = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    ds = null;
  }

  /**
   * This method checks for the correctness of both DS_My constructors and the instance method
   * size() when called on an empty DS_My object.
   * 
   */
  @Test
  void test00_empty_ds_size() {
    if (ds.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + ds.size());
  }

  /**
   * This method checks for the correctness of DS_My insert() method when inserting a key with a
   * value to DS_My object.
   * 
   */
  @Test
  void test01_insert_one() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (ds.size() == 1);
  }

  /**
   * This method checks for the correctness of DS_My insert() method and size() method when
   * inserting a key with a value to DS_My object.
   * 
   */
  @Test
  void test02_insert_remove_one_size_0() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (ds.remove(key)); // remove the key
    if (ds.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + ds.size());
  }

  /**
   * This method checks for the correctness of DS_My insert() method and size() method when
   * inserting duplicate key to DS_My object.
   * 
   */
  @Test
  void test03_duplicate_exception_thrown() {
    String key = "1";
    String value = "one";
    ds.insert("1", "one");
    ds.insert("2", "two");
    try {
      ds.insert(key, value);
      fail("duplicate exception not thrown");
    } catch (RuntimeException re) {
    }
    assert (ds.size() == 2);
  }

  /**
   * This method checks for the correctness of DS_My remove() method when remove a key from DS_My
   * object.
   * 
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (!ds.remove("2")); // remove non-existent key is false
    assert (ds.remove(key)); // remove existing key is true
    if (ds.get(key) != null)
      fail("get(" + key + ") returned " + ds.get(key) + " which should have been removed");
  }

  /**
   * This method checks for the correctness of DS_My remove() method and size() method when remove a
   * key from DS_My object.
   * 
   */
  @Test
  void test05_insert_remove_one() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (ds.remove(key)); // remove existing key is true
    if (ds.size() != 0)
      fail("size of data structure should be 0, but size=" + ds.size()
          + ", so the remove operation is not operated.");

  }

  /**
   * This method checks for the correctness of DS_My insert() method and size() method when insert
   * many keys into DS_My.
   * 
   */
  @Test
  void test06_insert_many_size() {

    ds.insert("10", "ten");
    ds.insert("2", "two");
    ds.insert("3", "three");
    ds.insert("4", "four");
    ds.insert("5", "five");
    ds.insert("6", "six");
    if (ds.size() != 6)
      fail("size of data structure should be 0, but size=" + ds.size());
  }

  /**
   * This method checks for the correctness of DS_My insert() method and size() method when
   * inserting two key with the same value into DS_My object.
   * 
   */
  @Test
  void test07_duplicate_values() {
    ds.insert("1", "one");
    if (ds.size() != 1)
      fail("size of data structure should be 1, but size=" + ds.size()
          + ", so the first insertion is not operated.");

    ds.insert("2", "one");
    if (ds.size() != 2)
      fail("size of data structure should be 2, but size=" + ds.size()
          + ", so the second insertion is not operated.");

  }

  /**
   * This method checks for the correctness of DS_My insert() method and size() method when insert a
   * null key into DS_My object.
   * 
   */
  @Test
  void test08_should_not_insert_null_key() {

    try {
      ds.insert(null, "1"); // insert a null key
      fail("null key");
    } catch (IllegalArgumentException re) {
    }
    assert (ds.size() == 0); // the size is 0
  }

  /**
   * This method checks for the correctness of DS_My insert() method and size() method when insert a
   * key with null value into DS_My object.
   * 
   */
  @Test
  void test09_can_insert_null_values() {
    ds.insert("1", null);// insert a key with a null value
    if (ds.size() != 1)
      fail("size of data structure should be 1, but size=" + ds.size()
          + ", so the key with null value is not inserted.");
  }

  /**
   * This method checks for the correctness of DS_My remove() method and size() method when remove a
   * null key into DS_My object.
   * 
   */
  @Test
  void test10_remove_null_key_throw_exception() {
    ds.insert("1", "one");
    try {
      assert (!ds.remove(null)); // remove null is false
    } catch (IllegalArgumentException re) {
    }
    assert (ds.size() == 1); // the size should still be 1
  }

  /**
   * This method checks for the correctness of DS_My contains() method and size() method after
   * inserting a existing key into DS_My object.
   * 
   */
  @Test
  void test11_contain_existing_key_return_true() {
    ds.insert("1", "one");
    if (ds.size() != 1) {
      fail("size of data structure should be 1, but size=" + ds.size());
    }
    if (!ds.contains("1"))
      fail("The method contains has some problems.");

  }
  /**
   * This method checks for the correctness of DS_My contains() method and size() method after
   * inserting a non-existing key into DS_My object.
   * 
   */
  @Test
  void test12_contain_nonexisting_key_return_false() {
    ds.insert("1", "one");
    if (ds.contains("2") || ds.contains(null))
      fail("The method contains has some problems.");
  }
  /**
   * This method checks for the correctness of DS_My remove() method and size() method after
   * inserting many existing keys into DS_My object and then remove some of them.
   * 
   */
  @Test
  void test13_insert_remove_multiple_keys() {
    ds.insert("9", "nine");
    ds.insert("2", "two");
    ds.insert("3", "three");
    ds.insert("4", "four");
    ds.insert("5", "five");
    ds.insert("6", "six");
    assert (ds.remove("3")); // remove existing key is true
    assert (ds.remove("6")); // remove existing key is true
    if (ds.size() != 4)
      fail("size of data structure should be 0, but size=" + ds.size()
          + ", so the remove operation is not operated correctly.");

  }
  /**
   * This method checks for the correctness of DS_My get() method  after
   * inserting a  key into DS_My object.
   * 
   */
  @Test
  void test14_get_value_of_key() {
    ds.insert("1", "one");
    if (!(ds.get("1").equals("one")))
      fail("the value of the given key is not correctly gotten.");


  }
}


