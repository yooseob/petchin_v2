package kr.petchin.app;

public abstract class Suit {
    public abstract String getSuitName();
}

class waterSuit extends Suit{

    @Override
    public String getSuitName() {
        return "waterSuit";
    }
}


class sexSuit extends Suit{

    @Override
    public String getSuitName() {
        return "sexSuit";
    }
}

interface SuitFactoryInterface{
    Suit createSuit();
}

class waterFactory implements SuitFactoryInterface{

    @Override
    public Suit createSuit() {
        return new waterSuit();
    }
}

class sexFactory implements SuitFactoryInterface{

    @Override
    public Suit createSuit() {
        return new sexSuit();
    }
}

//팩토리 클래스를 파라미터로 받는 구현 클래스
class lsatFactory {
    public static Suit getSuit(SuitFactoryInterface suitFactory){
        return suitFactory.createSuit();
    }
}

