package kz.kcell.apps.fish.audit;


import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Test;

import java.util.Iterator;

public class GlobalCacheTest {

    @Test
    public void testCirculQueue() {
        CircularFifoQueue<Integer> q = new CircularFifoQueue<>(3);

        for(int i = 0; i<5; i++) {
            q.add(i);
            print(q);
        }
    }

    private void print(CircularFifoQueue<Integer> q) {
        int i = 0;
        System.out.println( ">>>>>>>>>>>>>>>>>>");
        System.out.println( "size :"+q.size());
        System.out.println( "size :"+q.maxSize());

        Iterator<Integer> iterator = q.iterator();
        while (iterator.hasNext()) {
            i++;
            Integer next = iterator.next();
            System.out.println(i+": "+next);
        }
        System.out.println("<<<<<<<<<<<<<<<<<");
    }

    @Test
    public void testPrimat() {
        fun(new Integer(5));
        Integer iii = null;
        fun(iii);
    }

    public void fun(int i ) {
        System.out.println(i);
    }

}