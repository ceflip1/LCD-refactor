package LCD;

/**
 * Clase principal que usa servicios de LCDimprimirmatrix para imprimir números como una pantalla LCD
 */
public class LCDPrinter {

	/**
	 * singleton clase para manejar la matriz
	 */
	private static LCDimprimirmatrix matrizlcd = new LCDimprimirmatrix();

	public LCDPrinter() {

	}

	/**
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
	 */
	public String procesar(String comando, int espacioDig) {

		String[] parametros;

		int size;

		if (!comando.contains(",")) {
			throw new IllegalArgumentException("cadena " + comando + " no tiene caracter ,");
		}

		parametros = comando.split(",");

		//Valida la cantidad de parametros
		if (parametros.length != 2) {
			throw new IllegalArgumentException("cadena " + comando + "contiene mas caracter ,");
		}

		//Valida que el parametro size sea un numerico
		if (isNumeric(parametros[0])) {
			size = Integer.parseInt(parametros[0]);

		 // se valida que el size este entre 1 y 10
			if (size < 1 || size > 10) {
				throw new IllegalArgumentException("El parametro size [" + size + "] debe estar entre 1 y 10"");
			}
		} else {
			throw new IllegalArgumentException("Parametro Size [" + parametros[0] + "]  no es un numero");
		}

		//  Realiza la impresion del numero
		String result = matrizlcd.imprimirNumero(size, parametros[1], espacioDig);
		System.out.println(result);
		return result;

	}

	/**
	 *
     * Metodo encargado de validar si una cadena es numerica
     * @param cadena Cadena
	 */
	protected static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}

