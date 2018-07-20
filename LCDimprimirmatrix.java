package LCD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * clase que procesa e imprime una matrix que contiene   
 */
public class LCDimprimirmatrix {

	//Constantes para imprimir dígitos como un conjunto de segmentos
	private static final String CARACTER_VERTICAL = "|";
	private static final String CARACTER_HORIZONTAL = "-";
	
	private static final String  POSICION_X = "X";
	private static final String  POSICION_Y = "Y";

	private int size;

	 // Puntos fijos
	private final int[] pf1;
	private final int[] pf2;
	private final int[] pf3;
	private final int[] pf4;
	private final int[] pf5;

	private String[][] matrizImpr;
	
  // Calcula el numero de filasDig
	private int totalColum;
	private int totalfilas;
	private int filasDig;
	private int columDig;

	/**
	 * constructor  de  LCDimprimirmatrix (LCDMatrixFactory)
	 */
	public LCDimprimirmatrix() {

		this.pf1 = new int[2];
		this.pf2 = new int[2];
		this.pf3 = new int[2];
		this.pf4 = new int[2];
		this.pf5 = new int[2];
	}

	/**
	 *
	 * Metodo encargado de añadir una linea a la matriz de Impresion
    *
    * @param matriz Matriz Impresion
    * @param punto Punto Pivote
    * @param posFija Posicion Fija
    * @param size Tamaño Segmento
    * @param caracter Caracter Segmento
    */  
	
    private void addLine(String[][] matriz, int[] punto, String posFija, int size, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) {
            for (int y = 1; y <= size; y++) {
                int valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } else {
            for (int i = 1; i <= size; i++) {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }
    
    /**
	 *
	 * Metodo encargado de un segmento a la matriz de Impresion
	 * 
   * @param segmento Segmento a adicionar
	 */
	private void addSegment(int segmento) {

		switch (segmento) {
		case 1:
			addLine(this.matrizImpr, this.pf1, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 2:
			addLine(this.matrizImpr, this.pf2, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 3:
			addLine(this.matrizImpr, this.pf5, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 4:
			addLine(this.matrizImpr, this.pf4, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 5:
			addLine(this.matrizImpr, this.pf1, POSICION_X, this.size, CARACTER_HORIZONTAL);
			break;
		case 6:
			addLine(this.matrizImpr, this.pf2, POSICION_X, this.size, CARACTER_HORIZONTAL);
			break;
		case 7:
			addLine(this.matrizImpr, this.pf3, POSICION_X, this.size,CARACTER_HORIZONTAL);
			break;
		default:
			break;
		}
	}
	
	/**
	 *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
	 */
	private void addDigit(int numbero) {
		// Establece los segmentos de cada numero
		List<Integer> segList = new ArrayList<>();

		switch (numbero) {
		case 1:
			segList.add(3);
			segList.add(4);
			break;
		case 2:
			segList.add(5);
			segList.add(3);
			segList.add(6);
			segList.add(2);
			segList.add(7);
			break;
		case 3:
			segList.add(5);
			segList.add(3);
			segList.add(6);
			segList.add(4);
			segList.add(7);
			break;
		case 4:
			segList.add(1);
			segList.add(6);
			segList.add(3);
			segList.add(4);
			break;
		case 5:
			segList.add(5);
			segList.add(1);
			segList.add(6);
			segList.add(4);
			segList.add(7);
			break;
		case 6:
			segList.add(5);
			segList.add(1);
			segList.add(6);
			segList.add(2);
			segList.add(7);
			segList.add(4);
			break;
		case 7:
			segList.add(5);
			segList.add(3);
			segList.add(4);
			break;
		case 8:
			segList.add(1);
			segList.add(2);
			segList.add(3);
			segList.add(4);
			segList.add(5);
			segList.add(6);
			segList.add(7);
			break;
		case 9:
			segList.add(1);
			segList.add(3);
			segList.add(4);
			segList.add(5);
			segList.add(6);
			segList.add(7);
			break;
		case 0:
			segList.add(1);
			segList.add(2);
			segList.add(3);
			segList.add(4);
			segList.add(5);
			segList.add(7);
			break;
		default:
			break;
		}

		Iterator<Integer> iterator = segList.iterator();

		while (iterator.hasNext()) {
			addSegment(iterator.next());
		}
	}
	
	
	/**
	 *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
	 */
	public String imprimirNumero(int size, String numberoimp, int espacio) {

		int pivotX = 0;

		char[] digits = numberoimp.toCharArray();

		digits = numberoimp.toCharArray();

		int longitud = numberoimp.length();

		initMatConfig(longitud, espacio, size);

		for (char digit : digits) {
			 //Valida que el caracter sea un digito
			if (!Character.isDigit(digit)) {
				throw new IllegalArgumentException("caracter " + digit + " no es un digito");
			}

			int number = Integer.parseInt(String.valueOf(digit));
			//Calcula puntos fijos
			this.pf1[0] = 0;
			this.pf1[1] = 0 + pivotX;

			this.pf2[0] = (this.filasDig / 2);
			this.pf2[1] = 0 + pivotX;

			this.pf3[0] = (this.filasDig - 1);
			this.pf3[1] = 0 + pivotX;

			this.pf4[0] = (this.columDig - 1);
			this.pf4[1] = (this.filasDig / 2) + pivotX;

			this.pf5[0] = 0;
			this.pf5[1] = (this.columDig - 1) + pivotX;

			pivotX = pivotX + this.columDig + espacio;

			addDigit(number);
		}
		
		String result = "";
		// imprimir  matriz
		for (int i = 0; i < this.totalfilas; i++) {
			for (int j = 0; j < this.totalColum; j++) {
				result+=this.matrizImpr[i][j];
			}
			result+="\n";
		}
		return result;
	}

	

	/**
* Procedimiento que inicializa la matriz que se imprimirá.
* @param stringLength longitud de la cadena de números a imprimir
* @param digitGap espacio entre dígitos
* @ siza  tamaño paramétrico de dígitos al imprimir
	 */
	private void initMatConfig(int stringLength, int digitGap, int size) {

		this.size = size;

		   // Calcula el numero de filas y columnas de cada digito
		this.filasDig = (2 * this.size) + 3;

		this.columDig = this.size + 2;

		// Calcula el total de filas y total columnas de la matriz en la que se almacenaran los digitos
		this.totalfilas = this.filasDig;

		this.totalColum = (this.columDig * stringLength) + (digitGap * stringLength);

		this.matrizImpr = new String[this.totalfilas][this.totalColum];

		for (int i = 0; i < this.totalfilas; i++) {
			for (int j = 0; j < this.totalColum; j++) {
				this.matrizImpr[i][j] = " ";
			}
		}

	}

}


