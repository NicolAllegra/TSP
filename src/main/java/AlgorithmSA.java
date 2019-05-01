import java.util.Arrays;


public class AlgorithmSA {
    private int[][] distances;
    private int iterator;

    public AlgorithmSA(int[][] distances){
        this.distances = distances;
       // this.iterator=iterator;
    }

    public Tour applySA(Tour currentTour) {
        double temperature = 100, alpha = 0.97;

        Tour current = currentTour;
        Tour best = current;
        Algorithm2Opt twoOpt = new Algorithm2Opt(distances);
//int cont=1;
       while (System.currentTimeMillis() - Start.startTime < 150000) {
           // while (temperature > 0.00000000000000001) {
        //for(int u=0;u<iterator;u++){
            for (int i = 0; i < 100; i++) {
                Tour newTour = twoOpt.apply2Opt(doubleBridge(current.getTour()));
                int distNew = newTour.getDistance();
                int distCurrent = current.getDistance();

                //System.out.println("Next: "  + distNew + " Current " + distCurrent);
                if (distNew < distCurrent) {
                    current = newTour;
                    if (distNew < best.getDistance()) {
                        best = newTour;
                    }
                } else if (randomVerify(distNew, distCurrent, temperature)) {
                    current = newTour;
                }
            }
            //cont++;
            temperature *= alpha;
        }
        //System.out.println("ooo-->"+cont);
        return best;
    }

    private static boolean randomVerify(int next, int current, double temp) {
        double r = Start.r.nextDouble();
        double difference = -((double) next - (double) current);
        double d = Math.pow(Math.E, (difference / temp));
        //System.out.println("difference= " + difference + " r= " + r + "d= " + d);
        return (r < d);
    }

    public static City[] doubleBridge(City[] current) {

        City[] cities = new City[current.length];
        //City[] cities = new City[current.size()];
        int a = 0, b = 1, c = 2, d = 3;
        int position[] = new int[4];

        for (int i = 0; i < position.length; i++) {
            position[i] = Start.r.nextInt(current.length - 1);
        }

        Arrays.sort(position);
        int cont = 0;
        cities[cont] = current[position[a]];
        cont++;
        for (int i = position[c] + 1; i <= position[d]; i++) {

            cities[cont] = current[i];
            cont++;
        }
        for (int i = position[b] + 1; i <= position[c]; i++) {
            cities[cont] = current[i];
            cont++;
        }
        for (int i = position[a] + 1; i <= position[b]; i++) {

            cities[cont] = current[i];
            cont++;
        }
        for (int i = position[d] + 1; i < current.length - 1; i++) {

            cities[cont] = current[i];
            cont++;
        }
        for (int i = 0; i < position[a]; i++) {
            cities[cont] = current[i];
            cont++;
        }
        //System.out.println("Lunghezza" + current.length + " " + position[a] + " " + position[b] + " " + position[c] + " " + position[d] + " Contavettore " + cont);
        cities[cont++] = current[position[a]];
        return cities;
    }


}