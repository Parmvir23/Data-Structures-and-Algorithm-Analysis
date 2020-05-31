import java.util.Scanner;
import java.util.Random;

public class testHeap{   
   public static void main(String[] args){
   
      //int[] num = new int[item - 1];
   	Random rand = new Random();
      
      //for (int i = 0; i < num.length; i++){
      //   num[i] = rand.nextInt(49);
      // }
      int heap2;
      int[] arr = new int[49];
      BinaryHeap bheap = new BinaryHeap(49);        
      BinaryHeap bheap2 = new BinaryHeap(49);//Initialize the second heap 
      BinaryHeap temp = new BinaryHeap(49);
      
      bheap.currentSize = 0;
      bheap2.currentSize = 0;
      long time = System.nanoTime();
      
      for(int i = 0; i < arr.length; i++){
         arr[i] = rand.nextInt(49);
      }
     
      for (int i = 0; i < arr.length; i++){
         bheap.insert(arr[i]);
      }

      while(true){
         bheap.deleteMin();
         System.out.println("The process with a priority of % " +
	                        bheap.min() + " is now scheduled to run!\n");
         heap2 = rand.nextInt(49);
         bheap2.insert(heap2);     
         System.out.println("The process with a priority of % " +
	                        heap2 + " has run out of its timeslice!\n");
         if (bheap == null){
            Scanner scan = new Scanner(System.in);
            System.out.println("\nEvery process has had a chance to run:\n" +
	                            "Please press \"Enter\" to begin the next round!");  
            scan.nextLine();
            
            temp = bheap; //swap the trees
            bheap = bheap2;
            bheap2 = temp;
         }   
      }
   }
}