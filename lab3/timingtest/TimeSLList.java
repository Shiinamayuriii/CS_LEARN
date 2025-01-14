package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int M = 10000;
        int TestSize = 500;
        for(int i = 0; i <8; i ++){
            TestSize *= 2;
            SLList<Integer> L = new SLList<>();
            for (int j = 0; j < TestSize; j++){
                L.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for(int x = 0; x < M; x++){
                L.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            Ns.addLast(TestSize);
            times.addLast(timeInSeconds);
            opCounts.addLast(M);
        }
        printTimingTable(Ns, times, opCounts);
    }

}

//    public static void timeAListConstruction() {
//        AList<Integer> Ns = new AList<>();
//        AList<Double> times = new AList<>();
//        AList<Integer> opCounts = new AList<>();
//        for (int i = 1000; i <= 128000; i *= 2) {
//            AList<Integer> L = new AList<>();
//            Stopwatch sw = new Stopwatch();
//            for (int j = 0; j < i; j++) {
//                L.addLast(j);
//            }
//            double timeInSeconds = sw.elapsedTime();
//            Ns.addLast(i);
//            times.addLast(timeInSeconds);
//            opCounts.addLast(i);
//        }
//        printTimingTable(Ns, times, opCounts);
//    }