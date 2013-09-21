import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] s;
   private int N = 0;
    
   public RandomizedQueue()           // construct an empty randomized queue
   { s =  (Item[]) new Object[1]; }
   
   public boolean isEmpty()           // is the queue empty?   
   {
      return (N == 0);
   }
   
   public void enqueue(Item item)     // add the item
   {
       if (item == null) { throw new java.lang.NullPointerException("Cannot insert a null item."); }
       else {
           if (N == s.length) {resize(2*s.length);}
           s[N++] = item;
       }
    }
       
   public int size()                  // return the number of items on the queue   
   {
       return N;
   }
   
   public Item dequeue()              // delete and return a random item   
   {
       if (N == 0) { throw new java.util.NoSuchElementException("Queue is empty"); }
       else {
           int ret = StdRandom.uniform(N);
           swap(N-1, ret);
           Item item = s[--N];
           s[N] = null;
           if (N > 0 && N == s.length/4) resize(s.length/2);
           return item;
       }
   }
   
   private void swap (int a, int b) {
       Item temp = s[a];
       s[a] = s[b];
       s[b] = temp;
       return;
   }
   
   private void resize(int capacity)
   {
       Item[] copy = (Item[]) new Object[capacity];
       for (int i = 0; i < N; i++)
           copy[i] = s[i];
       s = copy;
   }
   
   public Item sample()               // return (but do not delete) a random item
   {
       if (N == 0) { throw new java.util.NoSuchElementException("Queue is empty"); }
       else {
           int ret = StdRandom.uniform(N);
           return s[ret];
       }
   }
   
   public Iterator<Item> iterator()  // return an independent iterator over items in random order
   {
       return new RandomizedQueueIterator();
   }
   
   private class RandomizedQueueIterator implements Iterator<Item>
   {
       private int i = N;
       private Item[] it;
       RandomizedQueueIterator () 
       {
           it = (Item[]) new Object[i];
           for (int j = 0; j < i; j++)
               { it[j] = s[j]; }
           
           StdRandom.shuffle(it);
       }

       public boolean hasNext() { return i > 0; }
       public void remove() { /* not supported */ throw new java.lang.UnsupportedOperationException("Not supported.");}
       public Item next() 
       { 
           if (i == 0) throw new java.util.NoSuchElementException("No more elements.");
           Item ret = it[--i];
           it[i] = null;
           return ret; 
       }
   }
   
   public static void main (String[] args) {
       return;
   }
}