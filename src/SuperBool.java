import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pedrosilva on 1/15/16.
 */
public class SuperBool {

    static SuperBool sb = new SuperBool();

    public static Boolean A = new Boolean(false);
    public static Boolean B = new Boolean(false);
    public static JTextField tf = new JTextField();
    public static JLabel ans = new JLabel();

    public static void main(String[] args){

        JFrame main = new JFrame("Boolean's");

        main.setDefaultCloseOperation(main.EXIT_ON_CLOSE);

        main.setLayout(null);

        //---------------------------------------------------


        tf.setText("and");
        tf.setFont(new Font("Serif", Font.PLAIN, 30));
        tf.setBounds(100, 100, 90, 40);
        main.add(tf);

        JButton trueA = new JButton();
        trueA.setText("True");
        trueA.setFont(new Font("Serif", Font.PLAIN, 30));
        trueA.setBounds(200, 100, 90, 40);
        main.add(trueA);

        JButton falseA = new JButton();
        falseA.setText("False");
        falseA.setFont(new Font("Serif", Font.PLAIN, 30));
        falseA.setBounds(300, 100, 90, 40);
        main.add(falseA);

        JButton trueB = new JButton();
        trueB.setText("True");
        trueB.setFont(new Font("Serif", Font.PLAIN, 30));
        trueB.setBounds(100, 150, 90, 40);
        main.add(trueB);

        JButton falseB = new JButton();
        falseB.setText("False");
        falseB.setFont(new Font("Serif", Font.PLAIN, 30));
        falseB.setBounds(100, 200, 90, 40);
        main.add(falseB);

        JButton GO = new JButton();
        GO.setText("GO!");
        GO.setFont(new Font("Serif", Font.PLAIN, 35));
        GO.setBounds(150, 250, 200, 50);
        main.add(GO);

        ans.setFont(new Font("Serif", Font.PLAIN, 70));
        ans.setBounds(200, 140, 150, 100);
        main.add(ans);

        JLabel aa = new JLabel();
        aa.setFont(new Font("Serif", Font.PLAIN, 30));
        aa.setBounds(210, 30, 150, 100);
        main.add(aa);

        JLabel bb = new JLabel();
        bb.setFont(new Font("Serif", Font.PLAIN, 30));
        bb.setBounds(35, 140, 150, 100);
        main.add(bb);

        trueA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sb.setTrue("A");
                aa.setText(String.valueOf(A));

            }
        });

        falseA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sb.setFalse("A");
                aa.setText(String.valueOf(A));

            }
        });

        trueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sb.setTrue("B");
                bb.setText(String.valueOf(B));

            }
        });

        falseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sb.setFalse("B");
                bb.setText(String.valueOf(B));

            }
        });

        GO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sb.GO();

            }
        });

        //---------------------------------------------------

        main.setResizable(false);
        main.setMinimumSize(new Dimension(500, 430));
        main.pack();
        main.setVisible(true);

    }

    public void setTrue(String bool){

        if (bool == "A"){

            A = true;

        }

        if (bool == "B"){

            B = true;

        }

    }

    public void setFalse(String bool){

        if (bool == "A"){

            A = false;

        }

        if (bool == "B"){

            B = false;

        }

    }

    public void GO(){

        String op = new String();
        op = tf.getText();

        if (op.equals("and")){

            String c = new String(String.valueOf(A && B));

            ans.setText(c);

        }

        if (op.equals("or")){

            String c = new String(String.valueOf(A | B));

            ans.setText(c);

        }

        if (op.equals("nand")){

            String c = new String(String.valueOf(!(A && B)));

            ans.setText(c);

        }

        if (op.equals("nor")){

            String c = new String(String.valueOf(!(A | B)));

            ans.setText(c);

        }

        if (op.equals("xnor")){

            String c = new String(String.valueOf(!((A && B) | !(A | B))));

            ans.setText(c);

        }

        if (op.equals("xor")){

            String c = new String(String.valueOf(((A && B) | !(A | B))));

            ans.setText(c);

        }

    }

}
