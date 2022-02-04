package kr.petchin.app;

public class testfactory {

    public static void main(String[] args){

        Suit suit1 = lsatFactory.getSuit(new sexFactory());
        System.out.println(suit1.getSuitName());
    }
}
