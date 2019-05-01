import java.awt.*;
import java.io.*;


public class Main {

    public static void main(String[] args) {
        int[][] distances;


        int dimension = 0;
        //apertura del file e parse per ottenere informazioni
        String fileName = args[0];
        ClassLoader classLoader = new Main().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                row++;
                if (line.equals("EOF"))
                    break;
                if (row < 8) {
                    if (row == 4) {
                        dimension = Integer.parseInt(line.split(" ")[2]);
                        //numero delle città
                    }
                } else {
                    try {
                        String[] split = line.split(" ");
                        int idCity = Integer.parseInt(split[0]);
                        Double x = Double.parseDouble(split[1]);
                        Double y = Double.parseDouble(split[2]);
                        TourManager.addCity(new City(idCity, x, y));
                    } catch (NumberFormatException e) {
                        String[] split = line.split(" ");
                        int idCity = Integer.parseInt(split[1]);
                        Double x = Double.parseDouble(split[2]);
                        Double y = Double.parseDouble(split[3]);
                        TourManager.addCity(new City(idCity, x, y));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        distances = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                distances[i][j] = TourManager.getCity(i).distanceTo(TourManager.getCity(j));
            }
        }
        Algorithm2Opt twoOpt = new Algorithm2Opt(distances);
        AlgorithmSA sa = new AlgorithmSA(distances);


       /* for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                System.out.println("distanza-->" + distances[i][j] + " ");
            }
            System.out.println();
        }*/
        int k = 0;
        while (k < 50 ) {
        Start.setStartTime();
        Long seed = System.currentTimeMillis();
        //Long seed=1556033354706L;
        //Long seed = Long.parseLong(args[1]);

        Start.setRandom(seed);
        System.out.println("Seed:" + seed);


        Integer[] tripNN = new AlgorithmNN(distances).applyAlgorithmNN();
        //fino a qui in tripNN è tutto giusto!
        City[] cityTrip = new City[tripNN.length + 1];
        //l'ho fatto in questo modo almeno dopo il NN potevo aggiungere l'ultima città per fare il 2opt
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            cityTrip[i] = TourManager.getCity(tripNN[i]);
        }
        cityTrip[tripNN.length] = cityTrip[0];

        // Tour tour = new Tour(cityTrip);
        // System.out.println("NN--->" + tour.getDistance());
        //il nearest neighbor è giusto!!


        Tour tour2opt = twoOpt.apply2Opt(cityTrip);

        //System.out.println("distance 2opt-->" + tour2opt.getDistance());
           /* for (int i = 0; i < tour2opt.tourSize(); i++) {
                System.out.println(tour2opt.getCity(i).getId());
            }*/

        Tour tourSA = sa.applySA(tour2opt);
        System.out.println("distance SA-->" + tourSA.getDistance());


        PrintWriter out = null;

        try {
            out = new PrintWriter(fileName.substring(0, fileName.length() - 4) + ".opt.tour");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (BufferedReader br2 = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < 4; i++) {
                out.println(br2.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("TOUR_SECTION");


        for (int i = 0; i < tourSA.tourSize() - 1; i++) {
            out.println(tourSA.getCity(i).getId());
        }
        out.println("-1");
        out.println("EOF");

        out.close();

k++;
            }
        Toolkit.getDefaultToolkit().beep();


    }

    }







