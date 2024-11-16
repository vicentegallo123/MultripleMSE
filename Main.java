// Main.java
public class Main {

    public static void main(String[] args) {
        // Datos de ventas, publicidad y años
        double sales[] = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
        double advertising[] = {23, 26, 30, 34, 43, 48, 52, 57, 58};
        double years[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Crear un objeto de la clase MultipleLinearRegression
        MultipleLinearRegression mlr = new MultipleLinearRegression();

        // Calcular los coeficientes de la regresión múltiple
        mlr.calculateMlR(sales, advertising, years);

        // Mostrar los coeficientes de regresión
        mlr.displayRegressionCoefficients();

        // Evaluar el modelo con los datos 
        mlr.evaluateModel(sales, advertising, years);

        // Predecir ventas con datos dados de publicidad y años
        mlr.predict(60, 10);
    }
}
