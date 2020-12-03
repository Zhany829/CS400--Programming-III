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
 * This class aims to compare insert efficiency of my implementation with that of Brian's.
 * @author ZHAN YU
 *
 */
public class CompareDS {

  public static void main(String args[]) {
    System.out.println("CompareDS.main Compares work time for: DS_My and DS_Brian.");
    // trial one
    System.out
        .println("Description: Compare the time for inserting 100 items for each implementation.");
    long myStartTime1 = System.nanoTime();
    DS_My my1 = new DS_My();
    System.out.println("DS_My is doing work for 100 values");
    for (int i = 0; i < 100; i++) {
      my1.insert(String.valueOf(i), String.valueOf(i));
    }
    long myEndTime1 = System.nanoTime();
    long myElapsedTime1 = myEndTime1 - myStartTime1;
    System.out.println("It took " + myElapsedTime1 + " ns to process 100 items");

    DS_Brian Brian1 = new DS_Brian();
    long BrianStartTime1 = System.nanoTime();
    System.out.println("DS_Brian is doing work for 100 values.");
    for (int i = 0; i < 100; i++) {
      Brian1.insert(String.valueOf(i), String.valueOf(i));
    }
    long BrianEndTime1 = System.nanoTime();
    long BrianElapsedTime1 = BrianEndTime1 - BrianStartTime1;
    System.out.println("It took " + BrianElapsedTime1 + " ns to process 100 items");

    // trial two
    System.out
        .println("Description: Compare the time for inserting 1000 items for each implementation.");
    long myStartTime2 = System.nanoTime();
    DS_My my2 = new DS_My();
    System.out.println("DS_My is doing work for 1000 values");
    for (int i = 0; i < 1000; i++) {
      my2.insert(String.valueOf(i), String.valueOf(i));
    }
    long myEndTime2 = System.nanoTime();
    long myElapsedTime2 = myEndTime2 - myStartTime2;
    System.out.println("It took " + myElapsedTime2 + " ns to process 1000 items");

    DS_Brian Brian2 = new DS_Brian();
    long BrianStartTime2 = System.nanoTime();
    System.out.println("DS_Brian is doing work for 1000 values.");
    for (int i = 0; i < 1000; i++) {
      Brian2.insert(String.valueOf(i), String.valueOf(i));
    }
    long BrianEndTime2 = System.nanoTime();
    long BrianElapsedTime2 = BrianEndTime2 - BrianStartTime2;
    System.out.println("It took " + BrianElapsedTime2 + " ns to process 1000 items");

    // trial three
    System.out.println(
        "Description: Compare the time for inserting 10000 items for each implementation.");
    long myStartTime3 = System.nanoTime();
    DS_My my3 = new DS_My();
    System.out.println("DS_My is doing work for 10000 values");
    for (int i = 0; i < 10000; i++) {
      my3.insert(String.valueOf(i), String.valueOf(i));
    }
    long myEndTime3 = System.nanoTime();
    long myElapsedTime3 = myEndTime3 - myStartTime3;
    System.out.println("It took " + myElapsedTime3 + " ns to process 10000 items");

    DS_Brian Brian3 = new DS_Brian();
    long BrianStartTime3 = System.nanoTime();
    System.out.println("DS_Brian is doing work for 10000 values.");
    for (int i = 0; i < 10000; i++) {
      Brian3.insert(String.valueOf(i), String.valueOf(i));
    }
    long BrianEndTime3 = System.nanoTime();
    long BrianElapsedTime3 = BrianEndTime3 - BrianStartTime3;
    System.out.println("It took " + BrianElapsedTime3 + " ns to process 10000 items");

    // trial four
    System.out.println(
        "Description: Compare the time for inserting 100000 items for each implementation.");
    long myStartTime4 = System.nanoTime();
    DS_My my4 = new DS_My();
    System.out.println("DS_My is doing work for 100000 values");
    for (int i = 0; i < 100000; i++) {
      my4.insert(String.valueOf(i), String.valueOf(i));
    }
    long myEndTime4 = System.nanoTime();
    long myElapsedTime4 = myEndTime4 - myStartTime4;
    System.out.println("It took " + myElapsedTime4 + " ns to process 100000 items");

    DS_Brian Brian4 = new DS_Brian();
    long BrianStartTime4 = System.nanoTime();
    System.out.println("DS_Brian is doing work for 100000 values.");
    for (int i = 0; i < 100000; i++) {
      Brian4.insert(String.valueOf(i), String.valueOf(i));
    }
    long BrianEndTime4 = System.nanoTime();
    long BrianElapsedTime4 = BrianEndTime4 - BrianStartTime4;
    System.out.println("It took " + BrianElapsedTime4 + " ns to process 100000 items");



  }
}
