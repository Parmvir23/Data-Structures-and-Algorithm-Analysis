import java.util.Scanner;
import java.util.Random;

class AVLTree{
	AvlNode root;
   
   public static class AvlNode{ //node class
      AvlNode left, right;
      int data, count;
      int height;
   
      AvlNode(int x, AvlNode a, AvlNode b){ //constructor
         data = x;
         left = a;
         right = b;
         height = 0;
      } 
   }
   
	int height(AvlNode t){ //calculate the height of the tree
    	if (t == null)
        	return 0;
    	return t.height;
   }

   int max(int x, int y){ //get the maximum value
      return (x > y) ? x : y;
   }
   
   AvlNode findMin(AvlNode t){ //find the left most leaf
      if (t == null) return null;
      
      AvlNode current = t;
      
      if (current.left != null)
         current = current.left;
      return current;
   }
   
	AvlNode rotateToLeft(AvlNode k1){ //do a left rotation with the right child
       AvlNode k2 = k1.right;                                                                                                           
       k1.right = k2.left;                                                                                                           
       k2.left = k1;                                                                                                                 
       k1.height = max( height( k1.left ), height( k1.right ) ) + 1;                                                                 
       k2.height = max( height( k2.right ), k1.height ) + 1;                                                                         
       return k2;    
   }
   
   AvlNode doubleRotateToLeft(AvlNode k3){ //do a double left rotation with the right child
     	k3.right = rotateToRight (k3.right);
     	return rotateToLeft (k3);
  	}
   
   AvlNode rotateToRight(AvlNode k2){ //do a right rotation with the left child
     	AvlNode k1 = k2.left;
     	k2.left = k1.right;
     	k1.right = k2;
     	k2.height = max(height(k2.left), height(k2.right)) + 1;
     	k1.height = max(height(k1.left), k2.height) + 1;
     	return k1;   	
   }  
   
   AvlNode doubleRotateToRight (AvlNode k3){ //do a double right rotation with the left child
     	k3.left = rotateToLeft(k3.left);
      return rotateToRight (k3);
   }
   	
	AvlNode insert (AvlNode t, int x) { //inserting the random values into the tree
    	if (t == null)
        	return new AvlNode(x, null, null);
    //  else if (x == t.data){
    //     (t.count)++;
    //     return t;
    //  }  
      else if (x < t.data){
         t.left = insert (t.left, x);
         if (height(t.left) - height(t.right) == 2){ //check the balance
            if (x < t.left.data)
               t = rotateToRight(t);
            else 
           		t = doubleRotateToRight(t);
         }
      }
      else if (x > t.data){
         t.right = insert (t.right, x);
         if (height(t.right) - height(t.left) == 2){ //check the balance
            if (x > t.right.data)
           		t = rotateToLeft(t);
            else
           		t = doubleRotateToLeft(t);
         }
      }   
      else
         ; 
      t.height = max(height(t.left), height(t.right)) + 1; //update the height
      
      return t;
   }    
   
   AvlNode remove (AvlNode t, int x){
      if (t == null) return t;
      
      if (x < t.data){
         t.left = remove (t.left, x);
         if (height(t.right) - height(t.left) == 2){ //check the balance
            if (height(t.right.right) >= height(t.right.left))
               t = rotateToLeft(t);
            else
               t = doubleRotateToLeft(t);
          }
      }
      else if (x > t.data){
         t.right = remove (t.right, x);
         if (height(t.left) - height(t.right) == 2){ //check the balance
            if (height(t.left.left) >= height(t.left.right))
               t = rotateToRight(t);
            else 
               t = doubleRotateToRight(t);
         }
       }
       else{ 
          if (t.left != null && t.right != null){
             t.data = findMin(t.right).data;
             t.right = remove(t.right, t.data);
          }
          else if (t.left == null){
             t = t.right;
          }
          else{
             t = t.left;
          }
       }
     
       if (t == null)
         return t;
       t.height = max(height(t.left), height(t.right)) + 1; //update the height
    
       return t;
   }  
   
   void inOrder(AvlNode t){
      if (t != null) {
            inOrder(t.left);
            System.out.print(t.data + " ");
            inOrder(t.right);
       }
    }
}

class AvlMain{   	
	public static void main(String[] args){
     	int[] num = new int[100];
   	Random rand = new Random();
      AVLTree tree1 = new AVLTree();
      
      for (int i = 0; i < num.length; i++){
         num[i] = rand.nextInt(98);
      }
        
   	for (int i = 0; i < num.length; i++){      	
         tree1.root = tree1.insert (tree1.root, num[i]);
   	}
      
      AVLTree tree2 = new AVLTree();//Initialize the second tree
      AVLTree temp = new AVLTree();
      
      while(true){
         int val = tree1.findMin(tree1.root).data;
         tree1.root = tree1.remove(tree1.root, val);
         System.out.println("The process with a priority of % " +
	                        val + " is now scheduled to run!\n");
         tree2.root = tree2.insert(tree2.root, val);   //   
         System.out.println("The process with a priority of % " +
	                        val + " has run out of its timeslice!\n");
         if (tree1.root == null){
            Scanner scan = new Scanner(System.in);
            System.out.println("\nEvery process has had a chance to run:\n" +
	                            "Please press \"Enter\" to begin the next round!");  
            scan.nextLine();
            
            temp = tree1; //swap the trees
            tree1 = tree2;
            tree2 = temp;
         }   
      }
   }
}