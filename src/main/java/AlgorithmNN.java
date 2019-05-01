import java.util.Random;

public class AlgorithmNN {

    private int[][] distances;

    public AlgorithmNN(int[][] distances){
        this.distances = distances;
    }

    //nearest neightboard
    public Integer[] applyAlgorithmNN() {

        int cont = 0;
        Integer[] list = new Integer[distances.length];

        int city = Start.r.nextInt(distances.length);
        list[cont] =  city;
        int next = city;
        cont++;

        for (int i = 0; i < distances.length - 1; i++) {
            next = findMin(list, next);
            list[cont] = next;
            cont++;

        }
        return list;
    }

    private int findMin(Integer[] list, int i) {
        int min = Integer.MAX_VALUE;
        int next = i;
        for (int j = 0; j < distances.length; j++) {
            if (j == i) {
                continue;
            } else if (distances[i][j] < min && (!contains(list, j))) {
                min = distances[i][j];
                //System.out.println("j--->"+j);
                next = j;//cosÃ¬ riconosco qual Ã¨ la prossima cittÃ  in cui devo andare
            }
        }
        return next;//ritorno l'indice della prossima cittÃ 
    }


    private static boolean contains(Integer[] list, int check) {
        /*for (int k = 0; k < list.length; k++) {
            System.out.println("provalista" + list[k]);
        }
        System.out.println();*/
        for (Integer in : list) {
            if (in != null) {
                if (in == check)
                    return true;
            }
        }
        return false;
    }


}

