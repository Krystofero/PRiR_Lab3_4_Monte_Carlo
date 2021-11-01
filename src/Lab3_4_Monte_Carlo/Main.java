package Lab3_4_Monte_Carlo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Liczba generowanych punktów
        System.out.println("Podaj liczbę losowanych punktów (n)(wpływa na dokladność obliczeń): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //Wątki i ich przedziały w Kwadracie 2x2
        MonteCarlo m1 = new MonteCarlo(0,0, 1, 1, n);
        MonteCarlo m2 = new MonteCarlo(1,0, 2, 1, n);
        MonteCarlo m3 = new MonteCarlo(0, 1, 1, 2, n);
        MonteCarlo m4 = new MonteCarlo(1,1, 2, 2, n);

        m1.run();
        m2.run();
        m3.run();
        m4.run();

        try {
        m1.join();
        m2.join();
        m3.join();
        m4.join();
        }catch (Exception e){ }

        //liczba punktów w kole
        double poleKola = m1.getWynik() + m2.getWynik() + m3.getWynik() + m4.getWynik();

        //liczba punktów w kole / liczba wszystkich punktów * pole kwadratu
        poleKola = (poleKola / ((double)n * 4)) * (2 * 2);

        System.out.println("Pole kola wpisanego w kwardat o boku równym 2 (czyli przybliżenie liczby pi) = " + poleKola);
    }
}
