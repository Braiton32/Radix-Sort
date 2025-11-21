import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RadixSort {

    // Función para leer los archivos
    public static int[] leerArchivo(String nombreArchivo) {
        ArrayList<Integer> numeros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split("[,\\s]+");
                for (String v : valores) {
                    v = v.trim();
                    if (!v.isEmpty()) {
                        numeros.add(Integer.parseInt(v));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            System.exit(1);
        }
        return numeros.stream().mapToInt(i -> i).toArray();
    }
    
    // Función para encontrar el número máximo
    public static int obtenerMaximo(int[] arreglo) {
        int maximo = arreglo[0];
        for (int numero : arreglo) {
            if (numero > maximo) maximo = numero;
        }
        return maximo;
    }

    // Proceso de RadixSort
    public static void radixSort(int[] arreglo) {
        int maximo = obtenerMaximo(arreglo);

        // El for procesará por cada dígito 
        for (int exponente = 1; maximo / exponente > 0; exponente *= 10) {
            countingSortPorDigito(arreglo, exponente);
            System.out.print("Ordenando por dígito (" + exponente + "): ");
            imprimirArray(arreglo);
        }
    }

    // CountingSort se aplicará para cada dígito
    public static void countingSortPorDigito(int[] arreglo, int exponente) {
        int[] salida = new int[arreglo.length];
        int[] contador = new int[10]; // Para los dígitos del 0 al 9

        // Se contarán las apariciones por dígito
        for (int numero : arreglo) {
            int posicion = (numero / exponente) % 10;
            contador[posicion]++;
        }

        // Se transformará en un acumulado
        for (int i = 1; i < 10; i++) {
            contador[i] += contador[i - 1];
        }

        // Se construirá el arreglo de salida
        for (int i = arreglo.length - 1; i >= 0; i--) {
            int posicion = (arreglo[i] / exponente) % 10;
            salida[contador[posicion] - 1] = arreglo[i];
            contador[posicion]--;
        }

        // Se copiará al arreglo original
        System.arraycopy(salida, 0, arreglo, 0, arreglo.length);
    }
    
    // Guardará el resultado en un archivo
    public static void guardarArchivo(String nombreArchivo, int[] datos) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            for (int numero : datos) {
                fw.write(numero + " ");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    // Método auxiliar para imprimir los arreglos
    public static void imprimirArray(int[] arreglo) {
        for (int numero : arreglo) {
            System.out.print(numero + " ");
        }
        System.out.println();
    }
}
