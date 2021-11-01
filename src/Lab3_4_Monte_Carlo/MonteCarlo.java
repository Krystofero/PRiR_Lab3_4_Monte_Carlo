package Lab3_4_Monte_Carlo;

public class MonteCarlo extends Thread {
    double xStart, yStart, xStop, yStop;
    int n;
    double wynik = 0;

    public MonteCarlo(double xStart, double yStart, double xStop, double yStop, int n) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.n = n;
    }

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

    public double getWynik() {
        return this.wynik;
    }

}
