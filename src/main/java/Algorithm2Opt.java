

public class Algorithm2Opt {

    private int[][] distances;
    public Algorithm2Opt(int[][] distances){
        this.distances = distances;
    }

    public Tour apply2Opt(City[] city) {
        int maxGain, gain, maxI = 0, maxJ = 0;

        //city.add(city.get(0));

        Tour tour = new Tour(city, distances);
        int size = tour.tourSize();


        do {
            maxGain = 0;
            for (int i = 0; i < size - 2; i++) {
                for (int j = i + 1; j < size - 1; j++) {


                    gain = calculateGain(tour.getCity(i), tour.getCity(i + 1), tour.getCity(j), tour.getCity(j + 1));
                    if (gain < 0 && gain < maxGain) {
                        maxGain = gain;
                        maxI = i;
                        maxJ = j;
                        swap(tour, maxI, maxJ);

                    }
                }

            }
        } while (maxGain != 0);
        return tour;
    }


    public static void swap(Tour tour, int from, int to) {
        for (int i = from + 1, j = to; i < j; i++, j--) {
            City tmp;
            tmp = tour.getCity(i);
            tour.setCity(i, tour.getCity(j));
            tour.setCity(j, tmp);
        }
    }

    //a:i b:i+1 c:j d:j+1
    private int calculateGain(City a, City b, City c, City d) {

        int ab = distances[a.getId() - 1][b.getId() - 1];
        int cd = distances[c.getId() - 1][d.getId() - 1];
        int ac = distances[a.getId() - 1][c.getId() - 1];
        int bd = distances[b.getId() - 1][d.getId() - 1];

        return (ac + bd) - (ab + cd);

    }


}

