package com.github.scogur;

import java.util.*;

public class Drum {

    private Reel[] reels = new Reel[3];

    private enum Symbols{
        Star,
        Seven,
        Watermelon,
        Grape,
        Strawberry,
        Bell,
        Plum,
        Pear,
        Orange,
        Lemon,
        Cherry,
    }
    public enum Codes{
        P1,
        P2,
        P3,
        P4,
        P5,
        P6,
        P7,
        P8,
        P9,
        P10,
        P11,
    }
    private static HashMap<Codes, Symbols> symbolCode = new HashMap<>();
    static {
        symbolCode.put(Codes.P1, Symbols.Star);
        symbolCode.put(Codes.P2, Symbols.Seven);
        symbolCode.put(Codes.P3, Symbols.Watermelon);
        symbolCode.put(Codes.P4, Symbols.Grape);
        symbolCode.put(Codes.P5, Symbols.Strawberry);
        symbolCode.put(Codes.P6, Symbols.Bell);
        symbolCode.put(Codes.P7, Symbols.Plum);
        symbolCode.put(Codes.P8, Symbols.Pear);
        symbolCode.put(Codes.P9, Symbols.Orange);
        symbolCode.put(Codes.P10, Symbols.Lemon);
        symbolCode.put(Codes.P11, Symbols.Cherry);
    }

    private static final HashMap<Codes, Integer[]> payout = new HashMap<>();
    static {
        payout.put(Codes.P1, new Integer[]{100, 30, 0});
        payout.put(Codes.P2, new Integer[]{80, 20, 0});
        payout.put(Codes.P3, new Integer[]{80, 20, 0});
        payout.put(Codes.P4, new Integer[]{50, 10, 0});
        payout.put(Codes.P5, new Integer[]{50, 10, 0});
        payout.put(Codes.P6, new Integer[]{25, 5, 0});
        payout.put(Codes.P7, new Integer[]{20, 5, 0});
        payout.put(Codes.P8, new Integer[]{20, 5, 0});
        payout.put(Codes.P9, new Integer[]{10, 5, 0});
        payout.put(Codes.P10, new Integer[]{10, 5, 0});
        payout.put(Codes.P11, new Integer[]{10, 5, 2});
    }

    private static final HashMap<Codes, Integer[]> reelDistr = new HashMap<>();
    static {
        reelDistr.put(Codes.P1, new Integer[]{1, 1, 1});
        reelDistr.put(Codes.P2, new Integer[]{1, 1, 1});
        reelDistr.put(Codes.P3, new Integer[]{1, 1, 1});
        reelDistr.put(Codes.P4, new Integer[]{1, 2, 1});
        reelDistr.put(Codes.P5, new Integer[]{2, 1, 1});
        reelDistr.put(Codes.P6, new Integer[]{2, 2, 2});
        reelDistr.put(Codes.P7, new Integer[]{2, 4, 1});
        reelDistr.put(Codes.P8, new Integer[]{3, 2, 3});
        reelDistr.put(Codes.P9, new Integer[]{2, 5, 3});
        reelDistr.put(Codes.P10, new Integer[]{6, 1, 6});
        reelDistr.put(Codes.P11, new Integer[]{4, 5, 5});
    }

    private static final HashMap<Codes, Integer> reel1Distr = new HashMap<>();
    static {
        reel1Distr.put(Codes.P1, 1);
        reel1Distr.put(Codes.P2, 1);
        reel1Distr.put(Codes.P3, 1);
        reel1Distr.put(Codes.P4, 1);
        reel1Distr.put(Codes.P5, 2);
        reel1Distr.put(Codes.P6, 2);
        reel1Distr.put(Codes.P7, 2);
        reel1Distr.put(Codes.P8, 3);
        reel1Distr.put(Codes.P9, 2);
        reel1Distr.put(Codes.P10, 6);
        reel1Distr.put(Codes.P11, 4);
    }

    private static final HashMap<Codes, Integer> reel2Distr = new HashMap<>();
    static {
        reel2Distr.put(Codes.P1, 1);
        reel2Distr.put(Codes.P2, 1);
        reel2Distr.put(Codes.P3, 1);
        reel2Distr.put(Codes.P4, 2);
        reel2Distr.put(Codes.P5, 1);
        reel2Distr.put(Codes.P6, 2);
        reel2Distr.put(Codes.P7, 4);
        reel2Distr.put(Codes.P8, 2);
        reel2Distr.put(Codes.P9, 5);
        reel2Distr.put(Codes.P10, 1);
        reel2Distr.put(Codes.P11, 5);
    }

    private static final HashMap<Codes, Integer> reel3Distr = new HashMap<>();
    static {
        reel3Distr.put(Codes.P1, 1);
        reel3Distr.put(Codes.P2, 1);
        reel3Distr.put(Codes.P3, 1);
        reel3Distr.put(Codes.P4, 1);
        reel3Distr.put(Codes.P5, 1);
        reel3Distr.put(Codes.P6, 2);
        reel3Distr.put(Codes.P7, 1);
        reel3Distr.put(Codes.P8, 3);
        reel3Distr.put(Codes.P9, 3);
        reel3Distr.put(Codes.P10, 6);
        reel3Distr.put(Codes.P11, 5);
    }

    static Drum CreateDrum(){
        Drum drum = new Drum();
        System.out.println("Reel 1");
        drum.reels[0] = Reel.GenerateReel(reel1Distr);
        System.out.println("Reel 2");
        drum.reels[1] = Reel.GenerateReel(reel2Distr);
        System.out.println("Reel 3");
        drum.reels[2] = Reel.GenerateReel(reel3Distr);
        return drum;
    }

    public int RollDrum(){
        Codes[] result = new Codes[reels.length];
        for (int i = 0; i < reels.length; i++){
            result[i] = reels[i].Roll();
        }
        return winCheck(result);
    }

    private int winCheck(Codes[] results){
        int win = 0;
        int winType;
        Codes winCode = null;
        if ((results[0].equals(results[1])) || (results[0].equals(results[2])) || (results[1].equals(results[2]))){
            if ((results[0].equals(results[1])) && (results[0].equals(results[2]))){
                winType = 3;
                winCode = results[0];
            } else {
                winType = 2;
                if ((results[0].equals(results[1]))) winCode = results[0];
                else winCode = results[2];
            }
        } else {
            winType = 1;
            for (int i = 0; i < 3; i++) {
                if (results[i].equals(Codes.P11)) {
                    winCode = Codes.P11;
                    break;
                } else winCode = results[0];
            }
        }
        switch (winType){
            case (1):
                win = GetPayout(winCode, 2);
                break;
            case (2):
                win = GetPayout(winCode, 1);
                break;
            case (3):
                win = GetPayout(winCode, 0);
                break;
        }
        return win;
    }

    private int GetPayout(Codes winCode, int win){
        return payout.get(winCode)[win];
    }

    private Symbols getSymbol(Codes code){
        return symbolCode.get(code);
    }
}

class Reel {
    List<Drum.Codes> symbols = new ArrayList<>();
    static Reel GenerateReel(HashMap<Drum.Codes, Integer> distr){
        Reel reel = new Reel();
        distr.forEach((key, value) -> {
            for (int i = 0; i < value; i++)
            {
                reel.symbols.add(key);
                System.out.println(key.name());
            }
        });
        return reel;
    }
    public Drum.Codes Roll(){
        Random random = new Random();
        return symbols.get(random.nextInt(symbols.size()));
    }
}