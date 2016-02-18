import java.util.Arrays;

/**
 * Created by pedrosilva on 1/22/16.
 */
public class goOrder {

    public static int[] a = new int[20];

    public static int[] b = new int[20];

    public static goOrder g = new goOrder();

    public static void main(String[] args){

        g.createArray();
        g.orderArray();

        String c = new String(Arrays.toString(b));

        String d = new String(Arrays.toString(a));

        System.out.println(c);
        System.out.println(d);

    }

    public void createArray(){

        for (int i = 0; i < 20; i++){

            double b = Math.random() * 10;

            int c = (int) b;

            a[i] = c;

        }

        for (int i = 0; i < 20; i++){

            b[i] = -1;

        }

    }

    public void orderArray(){

            for (int i = 0; i < 20; i++) {

                System.out.println(a[i]);

                for (int r = 0; r < 20; r++) {

                    System.out.println(b[r]);

                        if (a[i] >= 0) {

                            if (b[r] == -1) {

                                if (a[i] > b[r]) {

                                    b[r] = a[i];

                                    a[i] = -1;

                                    break;

                                }
                            }
                        }

                }
            }
    }
}
