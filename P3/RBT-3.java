import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: cs400 BST and RBT
//Files:BST.java, RBT.java, TestBST.java, TestRBT.java, compareDS.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 7/20/2020
//Description: This class aims to implementment RBT
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
/**
 * Implements a generic Red-Black tree.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS
 * DO NOT ADD ANY PACKAGE LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove.
 * If you do not override this operation,
 * you may still have the method in your type, 
 * and have the method throw new UnsupportedOperationException.
 * See https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K,V>{

    // USE AND DO NOT EDIT THESE CONSTANTS
    public static final int RED = 0;
    public static final int BLACK = 1;

    
    private Node root;
    int size;
    /**
     * Constructor of the class
     */
    public RBT() {
    	root=null;
  
    }
    /***
	 * Inner class for holding data
	 * @author Zhan Yu
	 *
	 */	
	private class Node{
		 private K key;
		private	V value;
		private int color;
		private	Node left;
		private	Node right;
		private	Node parent;
		
		/**
		 * Constructor of inner class
		 * @param key node's key
		 * @param value node's value
		 * @param color node's color
		 */
		private Node(K key,V value, int color) {
			this.key = key;
			this.value = value;
			this.color=color;
			this.left = null;
			this.right = null;
			this.parent = null;
			
		}
	
	}
	/**
	 * This method aims to get the node in RBT by the given key
	 * @param visit start node for finding the specific node
	 * @param key of the node needed to be found
	 * @return the node that has the given key
	 */
    private Node getNodeWith(Node visit,K key) {
    	if(key==null) return null;
    	
    	while(visit!=null) {
    		if(key.equals(visit.key))
    			return visit;
    	else if (key.compareTo(visit.key)<0) {
    		visit=visit.left;
    	}else {
    		
    		visit = visit.right;
    	}
    	}
    	return null;
    }
    /**
     * Returns the color of the node that contains the specified key.
     * Returns RBT.RED if the node is red, and RBT.BLACK if the node is black.
     * Returns -1 if the node is not found.
     * @param key for finding the node  
     * @return the color of the node
     */
    public int colorOf(K key) {
    	Node found = getNodeWith(root,key);
       	return found==null ? -1 : found.color;   
    }

    /**
     * Returns true if root is null or the color of the root is black.
     * If root is null, returns true.
     * @return true if root is black, else returns false (for red)
     */
    public boolean rootIsBlack() {
    	if(root==null||root.color==BLACK) {
    		return true;
    	}
        return false;
    }

    /**
     * Returns true if the node that contains this key is RED.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is RED,
     * else return false if key is found and the node's color is BLACK.
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if RBT does not contain the node with the given key
     */
    public boolean isRed(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
    	if(key==null)
    		throw new IllegalNullKeyException();
    	if (!contains(key))
    		throw new KeyNotFoundException();   	
    	if(colorOf(key)==0)
    	  return true;
    	return false;
    }

    /**
     * Returns true if the node that contains this key is BLACK.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is BLACK,
     * else return false if key is found and the node's color is RED.
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if RBT does not contain the node with the given key
     */
    public boolean isBlack(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
    	if(key==null)
    		throw new IllegalNullKeyException();
    	if (!contains(key))
    		throw new KeyNotFoundException();
    	if(colorOf(key)==1)
    	  return true;
    	return false;
    }
    /**
     * Get the key of the root
     * @return the key of the root 
     */
    @Override
    public K getKeyAtRoot() {      
        return root.key;
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	Node visit = root;
    	//check whether the key is null
    	if(key==null) {
    	   throw new IllegalNullKeyException("The key needed to be found is null.");
       }   
          if(!contains(key)) {
       	   throw new KeyNotFoundException();
          }
       	while(visit!=null) {
       		if(key.equals(visit.key)&&visit.left!=null) {
       			return visit.left.key;
       		}
       		if(key.equals(visit.key)&&visit.left==null) {
       			return null;
       		}
       	else if (key.compareTo(visit.key)<0) {
       		visit=visit.left;
       	}else {
       		visit = visit.right;
       	}
       	}
          
    return null;
    }
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	Node visit = root;
    	//check whether the key is null
    	if(key==null) {
    	   throw new IllegalNullKeyException("The key needed to be found is null.");
       }   
          if(!contains(key)) {
       	   throw new KeyNotFoundException();
          }
          while(visit!=null) {
         		if(key.equals(visit.key)&&visit.right!=null) {
         			return visit.right.key;
         		}
         		if(key.equals(visit.key)&&visit.right==null) {
         			return null;
         		}
         	else if (key.compareTo(visit.key)<0) {
         		visit=visit.left;
         	}else {
         		visit = visit.right;
         	}
         	}
        return null;
    }
    /**
     * Returns the height of this RBT.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    @Override
    public int getHeight() {
    	if(this.root==null) return 0;
    	return heightHelper(this.root);
    }
    /**
     * Recursive helper method that computes the height of the subtree rooted at current
     * 
     * @param node pointer to the current node within a the tree
     * @return height of the subtree rooted at current node
     */
    public  int heightHelper(Node node) {
      if (node == null) {
        return 0;
      }
      int left = heightHelper(node.left);
      int right = heightHelper(node.right);
      if (left > right) {
        return left+1;
      } else {
        return right+1;
      }
    }
    
    /**
     * Helper method for in order traversal
     * @param node the start node for traversal
     * @param list position for putting in nodes
     */
    private void inOrderHelper(Node node,List<K> list) {
		if(node == null) {
			return ;
		}
		inOrderHelper(node.left,list);
		list.add(node.key);
		inOrderHelper(node.right,list);
	}
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    @Override
    public List<K> getInOrderTraversal() {
    	List<K> empty = new ArrayList<K>();
    	if(root == null) {
    		return empty;
    	}else {
    	List<K> list = new ArrayList<K>();
    	inOrderHelper(root,list);
      return list;
    	}
    } 

    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    @Override
    public List<K> getPreOrderTraversal() {
    	List<K> empty = new ArrayList<K>();
    	if(root == null) {
    		return empty;
    	}else {
    	List<K> list = new ArrayList<K>();
    	preOrderHelper(root,list);
      return list;
      }
    }
    /**
     * Helper method for pre order traversal
     * @param node the start node for traversal
     * @param list position for putting in nodes
     */
    private void preOrderHelper(Node node,List<K> list) {
		if(node == null) {
			return ;
		}
		list.add(node.key);
		preOrderHelper(node.left,list);		
		preOrderHelper(node.right,list);
	}
    /**
     * Helper method for post order traversal
     * @param node the start node for traversal
     * @param list position for putting in nodes
     */
    private void postOrderHelper(Node node,List<K> list) {
		if(node == null) {
			return ;
		}
		postOrderHelper(node.left,list);		
		postOrderHelper(node.right,list);
		list.add(node.key);
	}
    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    @Override
    public List<K> getPostOrderTraversal() {
    	List<K> empty = new ArrayList<K>();
    	if(root == null) {
    		return empty;
    	}else {
    	List<K> list = new ArrayList<K>();
    	postOrderHelper(root,list);
      return list;
      }
    }
   
    /**
     * Helper method for level order traversal
     * @param node the start node for traversal
     * @param list position for putting in nodes
     */
    private LinkedList<Node> levelOrderHelper(Node root,LinkedList<Node> queue ) {
    	if (root == null){
            return null;
        }    
        Node current = null;
        LinkedList<Node> list = new LinkedList<Node>();
        queue.offer(root); // put in the root node
        while (!queue.isEmpty()){ //execute while there is a element in the queue
            current = queue.poll(); // put out the head element
            list.add(current);
            // put in left child into the queue
            if (current.left != null)
                queue.offer(current.left);
            // put in the right child into the queue
            if (current.right != null)
                queue.offer(current.right);
        }
        return list;
	}
    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    @Override
    public List<K> getLevelOrderTraversal() {
    	List<K> empty = new ArrayList<K>();
    	LinkedList<Node> linkedList = new LinkedList<Node>();  	
    	List<K> list = new ArrayList<K>();
    	if(root == null) {
    		return empty;
    	}else {
    		linkedList = levelOrderHelper(root,linkedList);
        for(int i =0;i<linkedList.size();i++) {
        	list.add(linkedList.get(i).key);
        }
        return list;
    	
  	}
      }

    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
       if(key==null) {
    	   throw new IllegalNullKeyException("Cannot insert null key.");
       }
       if(contains(key)) {
    	   throw new DuplicateKeyException("Cannot insert duplicate key.");
       }
       insertHelper(key,value);
		size += 1;
       }
    /**
     * Helper method for inserting
     * @param key the key of inserted node
     * @param value value of the inserted node
     * 
     */
    private void insertHelper(K key, V value) {
    	Node insertNode = new Node(key,value,RED);
		Node parentNode = null, current = root;
		while( current!=null ){
			parentNode = current;
			if( key.compareTo(current.key)<0 )
				current = current.left;
			else current = current.right;
		}
		insertNode.parent = parentNode;
		if( parentNode==null ) root = insertNode;
		else if( key.compareTo(parentNode.key)<0 ) parentNode.left = insertNode;
		else parentNode.right = insertNode;
		//print();
		
		insertBalance(insertNode);
	}
    /**
     * This method aims to rotate left at the given node
     * @param node node for left rotation
     */
	private void LeftRotate(Node node){
		Node rightChildOfNode = node.right;
		node.right = rightChildOfNode.left;
		if( rightChildOfNode.left!=null ) rightChildOfNode.left.parent = node;
		rightChildOfNode.parent = node.parent;
		
		if( node.parent==null ) root = rightChildOfNode; // update the tree root
		else{
			if( node.parent.left==node ) node.parent.left = rightChildOfNode;
			else node.parent.right = rightChildOfNode;
		}
		rightChildOfNode.left = node;
		node.parent = rightChildOfNode;
	}
	 /**
     * This method aims to rotate right at the given node
     * @param node node for right rotation
     */
	private void RightRotate(Node node){
		Node leftChildOfNode = node.left;
		node.left = leftChildOfNode.right;
		if( leftChildOfNode.right!=null ) leftChildOfNode.right.parent = node;
		leftChildOfNode.parent = node.parent;
		
		if( node.parent==null ) root = leftChildOfNode; // update the tree root
		else{
			if( node.parent.left==node ) node.parent.left = leftChildOfNode;
			else node.parent.right = leftChildOfNode;
		}
		leftChildOfNode.right = node;
		node.parent = leftChildOfNode;
	}
	/**
	 * This method aims to balance the tree for not violating RBT properties
	 * @param node node for starting balancing
	 */
	private void insertBalance(Node node){
		for(Node moveNode=null; node.parent!=null && node.parent.color==RED; ){
			if( node.parent ==node.parent.parent.left ){
				moveNode = node.parent.parent.right;
				if( moveNode!=null && moveNode.color==RED ){
					node.parent.color = BLACK;
					moveNode.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
				}else{
					if( node==node.parent.right ){
						node = node.parent;
						LeftRotate(node);
					}
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					RightRotate(node.parent.parent);
				}
			}else{
				moveNode = node.parent.parent.left;
				if( moveNode!=null && moveNode.color==RED ){
					node.parent.color = BLACK;
					moveNode.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
				}else{
					if( node==node.parent.left ){
						node = node.parent;
						RightRotate(node);
					}
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					LeftRotate(node.parent.parent);
				}
			}
		}
		root.color = BLACK;
	}
	 /** 
     * If key is found, remove the key,value pair from the data structure 
     * and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure, return false.
     * If key is null, throw IllegalNullKeyException
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        return false;
    }

    
    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(key==null)
    	   throw new IllegalNullKeyException();
       if(!contains(key))
    	   throw new KeyNotFoundException();
     
       return getHelper(this.root,key);
   }
    /**
     * Helper method for get the node with a given key
     * @param root start node for finding 
     * @param key the key of the node needed to be found
     * @return the node if found, else return null
     */
   private V getHelper(Node root,K key){
       while(root != null){
           int cmp = key.compareTo(root.key);
           if(cmp == 0) 
        	   return root.value;
           if(cmp < 0){
               root = root.left;
           }else{
               root = root.right;
           }
       }
       return null;
   }

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    public boolean contains(K key) throws IllegalNullKeyException { 
      if(key==null) {
    	  throw new IllegalNullKeyException();
      }
    	return contains(root,key);
    }
    /**
     *  contains helper method
     * @param n root node
     * @param key key needed to be found
     * @return true if the key is found in the tree
     */
    private boolean contains(Node n,K key)  { 
    	if(n==null)return false;
    	if(n.key.equals(key))return true;
    	if(key.compareTo(n.key)<0)
    		return contains(n.left,key);
        return contains(n.right,key);
    }
/**
 * give the number of keys in RBT
 * @return number of keys in RBT
 */
    @Override
    public int numKeys() {
        return this.size;
    }
    /**
     * print the tree
     */
    @Override
    public void print() {
    	printHelper(root,0,10);
    }
   /**
    * Helper method for print the BST
    * @param root root node which is the node to start with
    * @param levelSpace value of space between levels
    * @param levelDistance constant distance between different levels for increasing
    */
 private void printHelper(Node root,int levelSpace, int levelDistance) {
    	//base case
	 if(root==null)
		 return;
	 //increase the space between levels
	 levelSpace += levelDistance;
	 //print right side
	 printHelper(root.right, levelSpace, 10);
	 //print key
	 System.out.print("\n");
	 for (int i = levelDistance; i < levelSpace; i++)  
	        System.out.print(" ");  
	    System.out.print(root.key + "\n");    
	    // Process left child  
	    printHelper(root.left, levelSpace,10);        
    }

    
    }


    // TODO: override the insert method so that it rebalances 
    //       according to red-black tree insert algorithm.


    // TODO [OPTIONAL] you may override print() to include
    //      color R-red or B-black.
    

