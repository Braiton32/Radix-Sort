public class TestingRadixSort {
    // Archivo principal que llama la librería (renombrado desde RadixSortMain)

    public static void main(String[] args) {
        // Nombres de archivos de entrada y salida
        String archivoEntrada = "numeros.txt";
        String archivoSalida = "numeros_ordenados_radix.txt";

        System.out.println("Leyendo archivo: " + archivoEntrada);
        int[] numeros = RadixSort.leerArchivo(archivoEntrada);

        System.out.println("\nNúmeros originales:");
        RadixSort.imprimirArray(numeros);

        System.out.println("\nSe está ejecutando RadixSort (se ordena en tiempo real):");
        RadixSort.radixSort(numeros);

        System.out.println("\nNúmeros ordenados:");
        RadixSort.imprimirArray(numeros);

        RadixSort.guardarArchivo(archivoSalida, numeros);
        System.out.println("\nArchivo ordenado guardado como: " + archivoSalida);
    }
}
