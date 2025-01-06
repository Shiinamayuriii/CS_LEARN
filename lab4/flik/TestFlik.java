package flik;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFlik {
    @Test
    public void test(){
        int i = 0;
        for (int j = 0; i < 200; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break;
            }
        }
        System.out.println("i is " + i);

        assertEquals(i, 200);
    }
}
