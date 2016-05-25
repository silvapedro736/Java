package ImportantStuff;

import sun.misc.IOUtils;

import java.io.File;
import java.security.MessageDigest;
import java.math.*;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

public class MNodeS{

    String pay;
    MNodeS left;
    MNodeS right;

    public MNodeS(){};

    public MNodeS(String pay){
        this.pay=pay;}


    public static String sha256(String data) {
        String result = null;
        try {
            MessageDigest dig = MessageDigest.getInstance("SHA-256");
            byte[] hash = dig.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {ex.printStackTrace();}
        return result;}

    public static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);}

    public String sha256(){
        return sha256(this.pay);}

    public static MNodeS[] evenize(MNodeS[] input){
        if(input.length%2==0){return input;}
        else{
            MNodeS[] output = new MNodeS[input.length+1];
            for(int i=0; i<input.length; i++){
                output[i]=input[i];}
            output[input.length]=output[input.length-1];
            return output;}}

    public static String merge(String a, String b){
        BigInteger A = new BigInteger(a, 16);
        BigInteger B = new BigInteger(b, 16);
        return sha256((A.add(B)).toString(16));
    }

    public MNodeS merge(MNodeS other){
        MNodeS output = new MNodeS(merge(this.pay,other.pay));
        output.left=this;
        output.right=other;
        return output;}

    public static MNodeS[] merkle(String[] words){
        MNodeS[] output = new MNodeS[words.length];
        for(int i=0; i<words.length; i++){
            output[i]=new MNodeS(sha256(words[i]));}
        return merkle(output);}

    public static MNodeS[] merkle(File[] words){
        MNodeS[] output = new MNodeS[words.length];
        for(int i=0; i<words.length; i++){
            byte[] bFile = new byte[(int) words[i].length()];
            output[i]=new MNodeS(sha256(bFile.toString()));
        }
        return merkle(output);}

    public static MNodeS[] merkle(MNodeS[] hashes){
        if(hashes.length==1){return hashes;}
        else{
            MNodeS[] even = evenize(hashes);
            MNodeS[] up = new MNodeS[even.length/2];
            for(int i=0; i<up.length; i++){
                up[i]=even[2*i].merge(even[2*i+1]);}
            return merkle(up);}}

    public static String merkleRoot(String[] words){
        return merkle(words)[0].pay;}

    public MNodeS getI(int i){
        String bin = Integer.toBinaryString(i);
        MNodeS read = this;
        for(int j=1;j<bin.length();j++)
        { if(bin.charAt(j)=='0'){
            if(read.left==null){return null;}
            else{read=read.left;}}
        else{
            if(read.right==null){return null;}
            else{read=read.right;}}
        }
        return read;
    }

    public String[] certificate(int n, int m){
        int depth = (int) Math.ceil(Math.log(m)/Math.log(2));
        int noi = (int)Math.pow(2,depth)+(n-1);
        String[] output = new String[depth];
        String digi=new String("");
        for(int i=0; i<depth;i++){
            if(noi%2==0){
                output[i]=this.getI(noi+1).pay;}
            else{
                output[i]=this.getI(noi-1).pay;}
            noi=noi/2;}
        return output;}

    public static boolean verify(String[] cert, String data, String root){
        String hash = sha256(data);
        for(int i =0; i<cert.length; i++){
            hash=merge(hash,cert[i]);}
        return root.equals(hash);}

    public static boolean verify(String[] cert, File data, String root){
        byte[] bFile = new byte[(int) data.length()];
        String hash = sha256(sha256(bFile.toString()));
        for(int i =0; i<cert.length; i++){
            hash=merge(hash,cert[i]);}
        return root.equals(hash);}

    /*public void graphI(int i){
        MNodeS read = this.getI(i);
        if (read!=null){
            String bin = Integer.toBinaryString(i);
            int height = bin.length()-1;
            int y=height;
            double d=0;
            for(int j=1;j<bin.length();j++){
                if(bin.charAt(j)=='0'){d=d-1.0/( Math.pow(2,j-1));}
                else{d=d+1.0/( Math.pow(2,j-1));}}
            StdDraw.circle(d,y , 0.03);
            StdDraw.setPenColor(StdDraw.RED);
            // StdDraw.text(d-0.2, y, Integer.toString(i));
            StdDraw.setPenColor(StdDraw.BLUE);
            //StdDraw.textLeft(d+0.2, y, read.pay);
            StdDraw.setPenColor();
            // StdDraw.text(d+0.2, y, read.toString());
            if(read.left != null){
                StdDraw.line(d, y, d-1.0/Math.pow(2,y), y+1);}
            if(read.right != null){
                StdDraw.line(d, y, d+1.0/Math.pow(2,y), y+1);}
        }}*/


    public static void main(String[] args) {
        /*String[] test = new String[15];
        test[0]="hi";
        test[1]="hiya";
        test[2]="ho";
        test[3]="hey";
        test[4]="hu";
        test[5]="ih";
        test[6]="hy";
        test[7]="hue";
        test[8]="ihe";
        test[9]="hye";
        test[10]="ohi";
        test[11]="ohiya";
        test[12]="oho";
        test[13]="ohey";
        test[14]="ohu";*/
//        test[15]="oih";
//        test[16]="ohy";
//        test[17]="ohue";
//        test[18]="oihe";
//        test[19]="ohye";


//        StdDraw.setXscale( -3 , 3);
//        StdDraw.setYscale(6 , -0.1 );

        File[] test = new File[5];
        test[0] = new File(System.getProperty("user.dir") + "/Test 1.txt");
        test[1] = new File(System.getProperty("user.dir") + "/Test 1 copy.txt");
        test[2] = new File(System.getProperty("user.dir") + "/Test 1 copy 2.txt");
        test[3] =new File(System.getProperty("user.dir") + "/Test 1 copy 3.txt");
        test[4] =new File(System.getProperty("user.dir") + "/Test 1 copy 4.txt");


        MNodeS merk = merkle(test)[0];
        System.out.println(merk.pay);

        for(int n = 1; n<=test.length; n++){
            System.out.println(n+"   " + test[n-1]);

            String[] proof=merk.certificate(n,test.length);
            System.out.println(verify(proof,test[n-1],merk.pay));

            for(int i=0;i<proof.length;i++){
                System.out.println(proof[i]);}
        }

//        int x=1;
//        while(merk.getI(x)!=null){
//            merk.graphI(x);
//            x++;}
//        String h1 = sha256(test[5]);
//        String dir = proof[proof.length-1];
//          
//        for(int i =0; i<dir.length(); i++){
//          if(dir.charAt(i)=='E'){h1=sha256(h1+proof[i]);}
//          else{h1=sha256(proof[i]+h1);}}
//       System.out.println(h1);
//       System.out.println(h1.equals(merk.pay));
        //BigInteger k = new BigInteger(sha256("tony"), 16);
        //System.out.println(k);
    }
}