import java.util.Random;

public class Start {
    public static Long startTime;
    public static Random r;


    public static void setRandom(Long seed) {
        r = new Random(seed);
    }

    public static void setStartTime() {
        startTime = System.currentTimeMillis();
    }
}
