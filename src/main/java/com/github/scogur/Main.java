package com.github.scogur;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Drum drum = Drum.CreateDrum();
        while (true){
            System.out.println("Enter iterations number");
            Scanner scanner = new Scanner(System.in);
            int iters = scanner.nextInt();
            int totalOutcome = 0;
            int total = 0;
            for (int i = 0; i < iters; i++){
                int win = drum.RollDrum();
                totalOutcome+=win;
                //System.out.println("Win " + i + ": " + win);
                if (win>0){
                    total ++;
                }
            }
            System.out.println("Total Outcome " + totalOutcome);
            System.out.println("Total Hit Frequency " + (double)total/iters);
            System.out.println("Return to Player " + (double)totalOutcome/iters);
        }

    }
}