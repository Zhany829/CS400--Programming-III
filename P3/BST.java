
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
//Description: This class aims to implementment BST
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Students who get help from sources other than their partner and the course
//staff must fully acknowledge and credit those sources here. If you did not
//receive any help of any kind from outside sources, explicitly indicate NONE
//next to each of the labels below.
//
//Persons: NONE
//Online Sources: //www.allprogrammingtutorials.com/tutorials/implementing-binary-search-tree-java.php
//https://blog.csdn.net/ybt_c_index/article/details/79625030
//
///////////////////////////////////////////////////////////////////////////////


/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface 
 * that are required solely to support gray-box testing 
 * of the internal tree structure.  They must be implemented
 * as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public class BST<K extends Comparable<K>, V> implements STADT<K,V> {
	 
	private Node root;	
	int size; //number of node and key
	
	/***
	 * Inner class for holding data
	 * @author Zhan Yu
	 *
	 */	
	private class Node{
		private K key;
		private	V value;
		private	Node left;
		private	Node right;
		private Node(K key,V value) {
			this.key = key;
			this.value = value;
		}
		private Node getLeft() {
			return left;
		}
		private Node getRight() {
			return this.right;
		}
		private K getKey() {
			return this.key;
		}
	}
    
	  /**
     * Returns the key that is in the root node of this ST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    public K getKeyAtRoot() {
    	if(root!=null) {
    	return root.getKey();
    	}
    	
    	return null;
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
       		if(key.equals(visit.key)&&visit.getLeft()!=null) {
       			return visit.getLeft().key;
       		}
       		if(key.equals(visit.key)&&visit.getLeft()==null) {
       			return null;
       		}
       	else if (key.compareTo(visit.key)<0) {
       		visit=visit.getLeft();
       	}else {
       		visit = visit.getRight();
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
         		if(key.equals(visit.key)&&visit.getRight()!=null) {
         			return visit.getRight().key;
         		}
         		if(key.equals(visit.key)&&visit.getRight()==null) {
         			return null;
         		}
         	else if (key.compareTo(visit.key)<0) {
         		visit=visit.getLeft();
         	}else {
         		visit = visit.getRight();
         	}
         	}
          
    return null;
    }
    

    /**
     * Returns the height of this BST.
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
      int left = heightHelper(node.getLeft());
      int right = heightHelper(node.getRight());
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
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
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
    public List<K> getLevelOrderTraversal() {
    	List<K> empty = new ArrayList<K>();
    	LinkedList<Node> linkedList = new LinkedList<Node>();  	
    	List<K> list = new ArrayList<K>();
    	if(root == null) {
    		return empty;
    	}else {
    		linkedList = levelOrderHelper(root,linkedList);
        for(int i =0;i<linkedList.size();i++) {
        	list.add(linkedList.get(i).getKey());
        }
        return list;
    	
  	}
      }
    /**
     * Helper method for inserting
     */
    private Node insertHelper(K key, V value, Node node) {
		if(node == null) {
			return new Node(key,value);
		}		
		// If input data is less than node data, modify left else modify right
		int result = key.compareTo(node.key);
		if(result < 0) {
			node.left = insertHelper(key,value, node.left);
		} else if(result > 0) {
			node.right = insertHelper(key,value, node.right);
		}
		
		return node;
	}
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
       if(key==null) {
    	   throw new IllegalNullKeyException("Cannot insert null key.");
       }
       if(contains(key)) {
    	   throw new DuplicateKeyException("Cannot insert duplicate key.");
       }
       root = insertHelper(key,value,root);
    	size+=1;
    	
    }
    
    /**
     * Helper method for deleting node
     */
    private Node removeHelper(K key, Node node) {    
		if(node==null)
			return node;
    	// If input data is less than node data, go left else right		
    	int compare = key.compareTo(node.key);
		if(compare < 0) {
			//if(getKeyOfLeftChildOf(node.key))
			node.left = removeHelper(key,node.left);
		} else if(compare > 0) {
			node.right = removeHelper(key,node.right);
		} else {
			// node with only one child or no child 
            if (node.left == null&&node.right==null) 
                node = null; 
            else if(node.left == null) {
				// move right child node as node since no left child is present
				node = node.right;
			} else if(node.right == null) {
				// move left child node as node since no right child is present
				node = node.left;
			} else {
				// find minimum value in right subtree and assign that to current node
				node.key = minKey(node.right);
				node.right = removeHelper(node.key, node.right);
			}
            // node with two children: Get the inorder successor (smallest 
            // in the right subtree) 
         
		}
		return node;
	}
    

    /** 
     * If key is found, remove the key,value pair from the data structure 
     * and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure, return false.
     * If key is null, throw IllegalNullKeyException
     */
    public boolean remove(K key) throws IllegalNullKeyException {
        if(key==null) {
        	throw new IllegalNullKeyException("Cannot insert null key");
        }
        if(!contains(key)) {    
         return false;
        }
        root = removeHelper(key, this.root);
        this.size-=1;
       return true;
      
    }
    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
       Node visit = root;
    	if(key==null)
    	   throw new IllegalNullKeyException();
       if(!contains(key))
    	   throw new KeyNotFoundException();
    	while(visit!=null) {
    		if(key.equals(visit.key))
    			return visit.value;
    	else if (key.compareTo(visit.key)<0) {
    		visit=visit.getLeft();
    	}else {
    		visit = visit.getRight();
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
     *  Returns the number of key,value pairs in the data structure
     */
    public int numKeys() {
        return this.size;
    } 
    /**
     * Print the tree. 
     *
     * For our testing purposes of your print method: 
     * all keys that we insert in the tree will have 
     * a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display a binary search in any of a variety of ways, 
     * but we must see a tree that we can identify left and right children 
     * of each node
     *
     * For example: 
     
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.

       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.

     */
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
 /**
  * get the node of the current's child which has the minimum key
  * @param node the current
  * @return the node's minimum key of the child of the current node
  */
    private K minKey(Node node) {
		if(node.left != null) {
			return minKey(node.left);
		}	
		return node.getKey();
	}
    
} // copyrighted material, students do not have permission to post on public sites




//  deppeler@cs.wisc.edu
