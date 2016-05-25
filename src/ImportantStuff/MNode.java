package ImportantStuff;

import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

public class MNode{

    String pay;
    MNode left;
    MNode right;

    public MNode(){};

    public MNode(String pay){
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

    public static MNode[] evenize(MNode[] input){
        if(input.length%2==0){return input;}
        else{
            MNode[] output = new MNode[input.length+1];
            for(int i=0; i<input.length; i++){
                output[i]=input[i];}
            output[input.length]=output[input.length-1];
            return output;}}

    public MNode merge(MNode other){
        MNode output = new MNode(sha256(this.pay+other.pay));
        output.left=this;
        output.right=other;
        return output;}

    public static MNode[] merkle(String[] words){
        MNode[] output = new MNode[words.length];
        for(int i=0; i<words.length; i++){
            output[i]=new MNode(sha256(words[i]));}
        return merkle(output);}

    public static MNode[] merkle(MNode[] hashes){
        if(hashes.length==1){return hashes;}
        else{
            MNode[] even = evenize(hashes);
            MNode[] up = new MNode[even.length/2];
            for(int i=0; i<up.length; i++){
                up[i]=even[2*i].merge(even[2*i+1]);}
            return merkle(up);}}

    public static String merkleRoot(String[] words){
        return merkle(words)[0].pay;}

    public MNode getI(int i){
        String bin = Integer.toBinaryString(i);
        MNode read = this;
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
        String[] output = new String[depth+1];
        String digi=new String("");
        for(int i=0; i<depth;i++){
            if(noi%2==0){
                output[i]=this.getI(noi+1).pay;
                noi=noi/2; digi=digi+"E";}
            else{
                output[i]=this.getI(noi-1).pay;
                noi=noi/2; digi=digi+"O";}}
        output[depth]=digi;
        return output;}

    public static boolean verify(String[] cert, String data, String root){
        String hash = sha256(data);
        String dir = cert[cert.length-1];
        for(int i =0; i<dir.length(); i++){
            if(dir.charAt(i)=='E'){hash=sha256(hash+cert[i]);}
            else{hash=sha256(cert[i]+hash);}}
        return root.equals(hash);}


    public static void main(String[] args) {
        String[] test = new String[7];
        test[0]="hi";
        test[1]="hiya";
        test[2]="ho";
        test[3]="hey";
        test[4]="hu";
        test[5]="ih";
        test[6]="hy";

        MNode merk = merkle(test)[0];
        System.out.println(merk.pay);
        String[] proof=merk.certificate(5,7);

        System.out.println(verify(proof,"hu",merk.pay));
//        String[] proof=merk.certificate(6,7);
//        for(int i=0;i<proof.length;i++){
//          System.out.println(proof[i]);}
//       
//        String h1 = sha256(test[5]);
//        String dir = proof[proof.length-1];
//          
//        for(int i =0; i<dir.length(); i++){
//          if(dir.charAt(i)=='E'){h1=sha256(h1+proof[i]);}
//          else{h1=sha256(proof[i]+h1);}}
//       System.out.println(h1);
//       System.out.println(h1.equals(merk.pay));
    }
}