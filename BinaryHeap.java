import java.util.NoSuchElementException;

public class BinaryHeap{
   int maxSize;
   int currentSize;
   int[] heap;
   int min;
   
   public BinaryHeap(int maxSize){
      this.currentSize = 0;
      this.maxSize = maxSize;
      heap = new int[maxSize + 1];
   }
   
   public int size(){
      return currentSize;
   }
   
   public void buildHeap(){
      for (int i = currentSize/2; i > 0; i--)
         percolateDown(i);
   }
   
   public boolean isEmpty(){
      return currentSize == 0;
   }
   
   public boolean isFull(){
      return currentSize == heap.length;
   }    
   
   public void insert(int x){
      if (isFull())
         throw new NoSuchElementException("Overflow Exception");      
      int hole = ++currentSize;
      for (; hole>1 && x<heap[hole/2]; hole/=2)
         heap[hole] = heap[hole/2];
      heap[hole] = x;
   }
      
   public int deleteMin(){
      if (isEmpty())
         throw new NoSuchElementException("Underflow Exception");
      int child = 1;
      int hole = 1;
      min = heap[hole];
      heap[hole] = heap[currentSize--];
      percolateDown(hole);
      
      return min;
   }
               
   public void percolateDown(int hole){
      int child;
      int tmp = heap[hole];
      for (; hole*2 <= currentSize; hole = child){
         child = hole*2;
         if (child != currentSize && heap[child+1] < heap[child])
               child++;
         if (heap[child] < tmp)
            heap[hole] = heap[child];
         else
            break;
      }
      heap[hole] = tmp;
   }
   /*
   private int[] getRandom(int size){
		int[] num = new int[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			num[i] = rand.nextInt(49);
		}
		return num;
	}*/
   
   public void printHeap() {
		for (int i = 0; i < currentSize; i++) {
			System.out.print(heap[i] + ", ");
		}
		System.out.println();
       }
}

