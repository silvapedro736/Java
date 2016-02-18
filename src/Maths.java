/**
 * Created by pedrosilva on 1/13/16.
 */
public class Maths {

    public static void main (String[] args){

        double num = Math.random();

        double a = .2;
        double b = .4;
        double c = .6;
        double d = .8;

        if (num < a){
            print("1");
        }
        if (a < num){
            if (num < b) {
                print("2");
            }
        }
        if (b < num) {
            if (num < c) {
                print("3");
            }
        }
        if (c < num) {
            if (num < d) {
                print("4");
            }
        }
        if (num > d) {
            print("5");
        }
    }

    public static void print(String num){

        System.out.print(num);

    }

}
