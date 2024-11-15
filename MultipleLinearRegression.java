// MultipleLinearRegression.java
import java.lang.Math;

public class MultipleLinearRegression {
    // Variables para los parámetros beta0, beta1 y beta2
    private double beta0;
    private double beta1;
    private double beta2;

    // Constructor
    public MultipleLinearRegression() {
        beta0 = 0;
        beta1 = 0;
        beta2 = 0;
    }

    // Método para calcular los coeficientes de la regresión múltiple
    public void calculateMlR(double[] sales, double[] advertising, double[] years) {
        int n = sales.length;
        double sumX2 = 0, sumX2Squared = 0, sumY = 0, sumX1 = 0, sumX1Squared = 0;
        double determinant, determinantB0, determinantB1, determinantB2;
        double sumX1X2 = 0, sumX1Y = 0, sumX2Y = 0;

        // Calcular sumatorias
        for (int i = 0; i < n; i++) {
            sumX2 += advertising[i];
            sumX2Squared += Math.pow(advertising[i], 2);
            sumY += sales[i];
            sumX1 += years[i];
            sumX1Squared += Math.pow(years[i], 2);
            sumX1X2 += advertising[i] * years[i];
            sumX2Y += advertising[i] * sales[i];
            sumX1Y += years[i] * sales[i];
        }

        // Calcular determinantes
        determinant = (n * sumX1Squared * sumX2Squared) + (sumX1 * sumX1X2 * sumX2)
                - (sumX2 * sumX1Squared * sumX2) - (n * Math.pow(sumX1X2, 2)) - (sumX1 * sumX1 * sumX2Squared);
        determinantB0 = (sumY * sumX1Squared * sumX2Squared) + (sumX1 * sumX1X2 * sumX2Y)
                - (sumX2Y * sumX1Squared * sumX2) - (sumX1X2 * sumX1X2 * sumY) - (sumX2Squared * sumX1 * sumX1Y);
        determinantB1 = (n * sumX1Y * sumX2Squared) + (sumY * sumX1X2 * sumX2)
                - (sumX2 * sumX1Y * n) - (sumX2Y * sumX1X2 * n) - (sumX2Squared * sumX1 * sumY);
        determinantB2 = (n * sumX1Squared * sumX2Y) + (sumX1 * sumX1Y * sumX2)
                - (sumX2 * sumX1Squared * sumY) - (sumX1X2 * sumX1Y * n) - (sumX2Y * sumX1 * sumX1);

        // Calcular los coeficientes
        beta0 = determinantB0 / determinant;
        beta1 = determinantB1 / determinant;
        beta2 = determinantB2 / determinant;
    }

    // Método para mostrar los coeficientes de regresión
    public void displayRegressionCoefficients() {
        System.out.println("\nCoeficientes de Regresión:");
        System.out.println("Beta0 (intercepto) = " + beta0);
        System.out.println("Beta1 (años) = " + beta1);
        System.out.println("Beta2 (publicidad) = " + beta2);
    }

    // Método para predecir ventas basadas en la publicidad y los años
    public void predict(double advertising, double year) {
        double predictedSales = beta0 + (beta1 * year) + (beta2 * advertising);
        System.out.println("\nCon un valor de publicidad: " + advertising +
                           " y años: " + year +
                           ", las ventas predichas son = " + predictedSales);
    }

    // Método para evaluar el modelo con el Error Cuadrático Medio (MSE)
    public void evaluateModel(double[] actualSales, double[] advertising, double[] years) {
        double mse = 0;
        int n = actualSales.length;
        for (int i = 0; i < n; i++) {
            double predictedSales = beta0 + (beta1 * years[i]) + (beta2 * advertising[i]);
            mse += Math.pow(actualSales[i] - predictedSales, 2);
        }
        mse /= n;
        System.out.println("\nError Cuadrático Medio (MSE) = " + mse);
    }
}
