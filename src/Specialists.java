/**
 * Created by pedrosilva on 2/27/16.
 */
import java.util.Scanner;

public class Specialists{

    public static void main(String[] args){
        int count;
        Scanner scan = new Scanner(System.in);
        System.out.println("How many Specialists are you cataloguing?");
        count = Integer.parseInt(scan.nextLine());
        String specs[] = new String[count];
        String contact[] = new String[count];
        for (int i=0; i<count; i++){
            System.out.println("Enter the codename of Specialist " + (i+1));
            specs[i]=scan.nextLine();
        };
        System.out.println("You have entered the names of " + count + " Specialists. Their codenames are: ");
        int a=0;
        while(a<(count-1)){
            System.out.print(specs[a] + ", ");
            a++;
        };
        System.out.print(" and " + specs[count-1] + ".");
        for (int i=0; i<count; i++){
            System.out.println("Enter the contact word of Specialist " + (i+1));
            contact[i]=scan.nextLine();
        };
        System.out.println("You have entered the contact words of " + count + " Specialists. In order of entry, they are: ");
        int b=0;
        while(b<(count-1)){
            System.out.print(contact[b] + ", ");
            b++;
        };
        System.out.print(" and " + contact[count-1] + ". ");
        System.out.println("Which specialist do you want information on? Enter the number which they have been assigned to.");
        int pointer;
        pointer = Integer.parseInt(scan.nextLine());
        System.out.println("This specialist's codename is " + specs[pointer-1] + " and their contact word is " + contact[pointer-1]);
    }
}