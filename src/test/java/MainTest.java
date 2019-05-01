import org.junit.Test;

import java.awt.*;
import java.io.*;

import static org.junit.Assert.*;

/*public class MainTest {
    private void run(String problem,long seed,int iterator) {
        Start.setStartTime();
        Start.setRandom(seed);
        System.out.println("Seed:" + seed);
        int dimension = 0;
        //apertura del file e parse per ottenere informazioni
        String fileName = problem;
        String f = MainTest.class.getResource(fileName).getFile();
        File file = new File(f);
        assert (file != null);
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


        int[][] distances = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                distances[i][j] = TourManager.getCity(i).distanceTo(TourManager.getCity(j));
            }
        }
        AlgorithmNN nn = new AlgorithmNN(distances);
        Algorithm2Opt twoOpt = new Algorithm2Opt(distances);
        AlgorithmSA sa = new AlgorithmSA(distances,iterator);





        Integer[] tripNN = nn.applyAlgorithmNN();
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


        Toolkit.getDefaultToolkit().beep();


    }


   @Test(timeout = 181000)
    public void ch130() {
        run("ch130.tsp",1556029249552L,4328);
    }

    @Test(timeout = 181000)
    public void d198() {
        run("d198.tsp",1556366797931L,1516);
    }

    @Test(timeout = 181000)
    public void eil76() {
        run("eil76.tsp",1556030369920L,16585);
    }

    @Test(timeout = 181000)
    public void fl1577() {
        run("fl1577.tsp",1556292834444L,7);
    }

    @Test(timeout = 181000)
    public void kroA100() {
        run("kroA100.tsp",1556031396763L,5839);
    }

    @Test(timeout = 181000)
    public void lin318() {
        run("lin318.tsp",1556275720588L,505);
    }

    @Test(timeout = 181000)
    public void pcb442() {
        run("pcb442.tsp",1556276691860L,243);
    }

    @Test(timeout = 181000)
    public void pr439() {
        run("pr439.tsp",1556033354706L,241);
    }
    @Test(timeout = 181000)
    public void rat783() {

        run("rat783.tsp",1556575244215L,52
        );

    }


/*
    @Test(timeout = 181000)
    public void u1060() {
        run("u1060.tsp",1556286916191L
        );
    }*/

