package LCD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LCDTester {

	private static final String CADENA_FINAL = "0,0";

	public static void main(String[] args) {

		List<String> comList = new ArrayList<>();
		String comando;
		int espacioDig;

		try {

			try (Scanner lector = new Scanner(System.in)) {

				System.out.print("Espacio entre Digitos (0 a 5): ");
				comando = lector.next();

				if (LCDPrinter.isNumeric(comando)) {
					espacioDig = Integer.parseInt(comando);

					// se valida que el espaciado este entre 0 y 5
					if (espacioDig < 0 || espacioDig > 5) {
						throw new IllegalArgumentException(
								"el espacio entre [" + espacioDig + "] digitos debe estar entre 0 y 5");
					}

				} else {
					throw new IllegalArgumentException("cadena " + comando + "  no es un entero");
				}

				do {
					System.out.print("Entrada: ");
					comando = lector.next();
					if (!comando.equalsIgnoreCase(CADENA_FINAL)) {
						comList.add(comando);
					}
				} while (!comando.equalsIgnoreCase(CADENA_FINAL));
			}
//
			LCDPrinter lcdPrinter = new LCDPrinter();

			Iterator<String> iterator = comList.iterator();
			while (iterator.hasNext()) {
				try {
					lcdPrinter.procesar(iterator.next(), espacioDig);
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}

}
