Programowanie równolegle i rozproszone - projekt 4 labolatorium  Monte Carlo

Projekt przedstawia aplikację wielowątkową obliczającą pole okręgu na podstawie algorytmu Monte Carlo.

W programie jest liczone pole kola wpisanego w kwadrat o bokach 2x2 - w ten sposób uzyskujemy przybliżenie liczby pi.

W main'ie podajemy liczbę generowanych punktow ( im jest większa tym dokładniejsze uzyskujemy przybliżenie):

        System.out.println("Podaj liczbę losowanych punktów (n)(wpływa na dokladność obliczeń): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

Tworzymy obiekty klasy MonteCarlo, które mają pracować jako odzielne wątki na innych przedziałach kwadratu w celu przyspieszenia pracy algorytmu
(kwadrat dzielimy na 4 rowne częsci - każda z nimch odpowiada poszczególnemu wątkowi):

        MonteCarlo m1 = new MonteCarlo(0,0, 1, 1, n);
        MonteCarlo m2 = new MonteCarlo(1,0, 2, 1, n);
        MonteCarlo m3 = new MonteCarlo(0, 1, 1, 2, n);
        MonteCarlo m4 = new MonteCarlo(1,1, 2, 2, n);
        
Uruchamiamy wszystkie wątki:

        m1.run();
        m2.run();
        m3.run();
        m4.run();

A także ograniczamy by następny wątek gdy ukończył pracę czekał na poprzedni wątek:

        try {
        m1.join();
        m2.join();
        m3.join();
        m4.join();
        }catch (Exception e){ }
        
Sumujemy liczby punktów znajdujących się w kole dla każdego z wątków:
        
        double poleKola = m1.getWynik() + m2.getWynik() + m3.getWynik() + m4.getWynik();
        
I wyznaczamy pole koła (liczba punktów w kole / liczba wszystkich wygenerowanych punktów * pole kwadratu):

        poleKola = (poleKola / ((double)n * 4)) * (2 * 2);
        
Klasa MonteCarlo rozszerza klasę Thread.
Konstruktor przyjmuje parametry początkowego punktu przedziału pracy tego wątku, parametry końcowego punktu oraz liczbę generowanych punktów.

    public MonteCarlo(double xStart, double yStart, double xStop, double yStop, int n) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.n = n;
    }

W metodzie run() generujemy punkty o losowych współrzędnych x oraz y i sprawdzamy czy znajdują się w naszym okręgu:

 @Override
    public void run() {
        int inCircle = 0;

        for (int i = 0; i < this.n; i++) {
            double x = Math.random();
            double y = Math.random();

            if (x * x + y * y <= 1) inCircle++;
        }

        this.wynik = inCircle;
    }

Metoda getWynik zwraca liczbę punktów w kole dla przedziału pracy danego wątku:

  public double getWynik() {
        return this.wynik;
    }
