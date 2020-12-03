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
@SuppressWarnings("rawtypes")
/***
 * This class is a subclass of DataStructureADT that will test DS_My class
 * @author Zhan Yu
 *
 */
public class TestDS_My extends DataStructureADTTest {

    // the return type must be the name of the data structure class you are testing
    @Override
    protected DataStructureADT createInstance() {
        return new DS_My();
    }

}
