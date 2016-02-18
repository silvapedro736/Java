import java.util.Scanner;

/**
 * Created by pedrosilva on 1/7/16.
 *
 * Hello World Program!!!
 *
 */

public class hello {

    public static void main(String[] args){

        Scanner name = new Scanner(System.in);

        System.out.println("Enter your name: ");

        String n = name.next();

        System.out.println("Hello " + n + "!");

        String word = new String("1234567890");

        System.out.println(word.substring(3,6));

    }

}




