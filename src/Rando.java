/**
 * Created by pedrosilva on 1/21/16.
 */
public class Rando {

    public static void main(String[] args){

        System.out.print(array(20, 10));

    }

    public static int random(int len){

        return (int) (len*Math.random()+1);

    }

    public static int[] array(int len, int len2){

        int[] a = new int[len];

        for (int i=0; i<len; i++){

            a[i] = random(len2);

        }

        return (int []) a;

    }

}
