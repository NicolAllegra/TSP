
public class Tour {
    // Holds our tour of cities
    private City[] tour;
    // Cache
    private int distance = 0;
    private int[][] distances;


    // Constructs a tour from another tour
    public Tour(City[] tour, int[][] distances) {
        this.distances = distances;
        this.tour = tour.clone();
    }

    // Returns tour information
    public City[] getTour() {
        return tour;
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {

        return tour[tourPosition];
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour[tourPosition] = city;
        // If the tours been altered we need to reset the fitness and distance
        distance = 0;
    }


    // Gets the total distance of the tour
    public int getDistance() {
        if (distance == 0) {
            int tourDistance = 0;
            // Loop through our tour's cities
            for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
                // Get city we're traveling from
                City fromCity = getCity(cityIndex);
                // City we're traveling to
                City destinationCity;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if (cityIndex + 1 < tourSize()) {
                    destinationCity = getCity(cityIndex + 1);
                } else {
                    destinationCity = getCity(0);
                }
                // Get the distance between the two cities
                tourDistance +=distances[fromCity.getId() - 1][destinationCity.getId() - 1];

                //tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.length;
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i) + "|";
        }
        return geneString;
    }
}
