/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interpolation;
import java.util.Arrays;
public class Interpolation {

    // Fungsi untuk Polinom Lagrange
    public static double lagrangeInterpolation(double[] x, double[] y, double xp) {
        double yp = 0;
        for (int i = 0; i < x.length; i++) {
            double p = 1;
            for (int j = 0; j < x.length; j++) {
                if (i != j) {
                    p *= (xp - x[j]) / (x[i] - x[j]);
                }
            }
            yp += p * y[i];
        }
        return yp;
    }

    // Fungsi untuk Polinom Newton
    public static double newtonInterpolation(double[] x, double[] y, double xp) {
        double[] dividedDiff = dividedDifferences(x, y);
        double yp = dividedDiff[0];
        double term = 1;
        for (int i = 1; i < x.length; i++) {
            term *= (xp - x[i - 1]);
            yp += term * dividedDiff[i];
        }
        return yp;
    }

    // Fungsi untuk menghitung divided differences untuk Polinom Newton
    private static double[] dividedDifferences(double[] x, double[] y) {
        double[] dividedDiff = Arrays.copyOf(y, y.length);
        for (int i = 1; i < x.length; i++) {
            for (int j = x.length - 1; j >= i; j--) {
                dividedDiff[j] = (dividedDiff[j] - dividedDiff[j - 1]) / (x[j] - x[j - i]);
            }
        }
        return dividedDiff;
    }

    // Fungsi utama untuk menjalankan interpolasi
    public static void main(String[] args) {
        // Contoh data x dan y, bisa disesuaikan dengan data yang diberikan
        double[] x = {5, 10, 15, 20, 25, 30, 35, 40};  // data x
        double[] y = {40, 30, 25, 40, 18, 20, 22, 15}; // data y

        // Rentang nilai xp yang ingin diinterpolasi
        double startRange = 5.0;
        double endRange = 40.0;
        double step = 1.0; // Langkah interpolasi

        System.out.println("Interpolasi dari " + startRange + " hingga " + endRange + " dengan langkah " + step);

        for (double xp = startRange; xp <= endRange; xp += step) {
            // Interpolasi menggunakan polinom Lagrange
            double ypLagrange = lagrangeInterpolation(x, y, xp);
            System.out.println("Lagrange: xp = " + xp + ", yp = " + ypLagrange);

            // Interpolasi menggunakan polinom Newton
            double ypNewton = newtonInterpolation(x, y, xp);
            System.out.println("Newton: xp = " + xp + ", yp = " + ypNewton);
        }
    }
}
