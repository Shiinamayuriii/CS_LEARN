package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
//    @Test
//    public void testThreeAddThreeRemove(){
//        AListNoResizing<Integer> L = new AListNoResizing<>();
//        BuggyAList<Integer> L1 = new BuggyAList<>();
//        L.addLast(1);
//        L.addLast(2);
//        L.addLast(3);
//        L1.addLast(1);
//        L1.addLast(2);
//        L1.addLast(3);
//        assertEquals(L.size(), L1.size());
//        assertEquals(L.removeLast(), L1.removeLast());
//        assertEquals(L.removeLast(), L1.removeLast());
//        assertEquals(L.removeLast(), L1.removeLast());
//    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);

            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size1 = L1.size();
                assertEquals(size, size1);

            } else if (operationNumber == 2){
                if(L.size() == 0 || L1.size() == 0){
                    continue;
                }
                int last = L.getLast();
                int last1 = L1.getLast();
                assertEquals(last, last1);

            } else {
                if(L.size() == 0|| L1.size() == 0){
                    continue;
                }
                int last1 = L1.removeLast();
                int last = L.removeLast();
                assertEquals(last, last1);
                
            }
        }
    }
  // YOUR TESTS HERE
}
