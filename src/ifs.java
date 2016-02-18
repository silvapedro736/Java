/**
 * Created by pedrosilva on 1/18/16.
 */
public class ifs {

    public static void main(String[] args){

        int score = 90;
        String result = new String();

        if (score == 90 | score > 90){

            result = "A";

        } else {

            if (score == 80 | score > 80){

                result = "B";

            } else {

                if (score < 70){

                    result = "C";

                }

            }

        }

        System.out.print("Your grade is " +  result);

    }

}
