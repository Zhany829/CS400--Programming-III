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
/***
 * This class aims to Implement, test, and compare alternate implementations of an abstract data
 * type (ADT).
 * 
 * @author Zhan Yu
 *
 */
public class DS_My implements DataStructureADT<String, String> {


  private int size;
  private String[] keys;
  private String[] values;

  /**
   * Constructor of the class
   */
  public DS_My() {
    this.size = 0;
    this.keys = new String[100000];
    this.values = new String[100000];
  }

  /**
  * This method provides the size of array of keys or values
  * @return size of array of keys or values
  */
  @Override
  public int size() {
    return this.size;
  }
  /**
   * The method aims to insert a key and its value to the array data structure
   * @param key value's key
   * @param given key's value
   * @throws IllegalArgumentException if the key is null
   * @throws RuntimeException if the key has already exists in the array of keys
   */
  @Override
  public void insert(String key, String value) {
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    for (int i = 0; i < size(); i++) {
      if (key.equals(keys[i]))
        throw new RuntimeException("duplicate key");
    }
    keys[size] = key;
    values[size] = value;
    this.size++;
  }
  /**
   * The method aims to remove a key and its value to the array data structure
   * @param key value's key
   * @return true if the key that is the same as the input has been removed
   * @throws IllegalArgumentException if the input key is null
   */
  @Override
  public boolean remove(String key) {
    for (int i = 0; i < size(); i++) {
      if (keys[i].equals(key)) {
        for (int j = i; j < size() - 1; j++) { //remove the element by moving later element forward
          keys[j] = keys[j + 1];
          values[j] = values[j + 1];
        }
        this.size--;
        return true;
      }
    }
    if (key == null) {
      throw new IllegalArgumentException("null key");
    } else {
      return false;
    }
  }
  /**
   * The method aims to get the value of a given key in the array data structure
   * @param key value's key
   * @return the given key's value
   * @return null if the given key is not found in the array
   * @throws IllegalArgumentException if the input key is null
   */
  @Override
  public String get(String key) {
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    int index = 0;
    for (int i = 0; i < size(); i++) {
      if (keys[i].equals(key)) {
        index = i;
        return values[index];
      }
    }
    if (key != null && index == 0) {
      return null;
    }

    return null;
  }

  /**
   * The method aims to check whether a given key existed in the array data structure
   * @param key value's key
   * @return true if the given key is found in the array
   */
  @Override
  public boolean contains(String key) {
    for (int i = 0; i < size(); i++) {
      if (keys[i].equals(key)) {
        return true;
      }
      if (key == null) {
        return false;
      }
    }
    return false;

  }


}

