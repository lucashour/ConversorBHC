package com.indieapps.conversorbhd;

public class ProcesadorNumerico {

	/* MÉTODOS DE VERIFICACIÓN DE VALORES */

	public static boolean verificarDecimal(String num){
		int i;
		char [] str = num.toCharArray();
		for(i = 0; i < str.length; i++){
			if (!(Character.isDigit(str[i]))){
				return false;
			}
		}
		return true;
	}

	public static boolean verificarBinario(long num){
		long aux = 0;
		while (num != 0){
			aux = num % 10;
			num = num / 10;
			if (aux > 1)
				return false;
		}
		return true;
	}

	public static boolean verificarBinarioDesdeString(String num){
		char [] str = num.toCharArray();
		for(int i = 0; i < str.length; i++){
			if (!(str[i] == '0' || str[i] == '1')){
				return false;
			}
		}
		return true;
	}

	public static boolean verificarHexadecimal(String num){
		int i;
		char [] str = num.toCharArray();
		for(i = 0; i < str.length; i++){
			if (!((Character.isDigit(str[i])) || (str[i] >= 'a' && str[i] <= 'f'))){
				return false;
			}
		}
		return true;
	}

	/* MÉTODOS DE CONVERSIÓN DE VALORES */
	
	public static String convertirDecimalBinario(long num){
		String resultado = "" + Long.toBinaryString(num);
		return resultado;
	}
	
	public static String convertirBinarioDecimal(long num){
		String resultado = new String();
		long res = 0;
		int e = 1;
		long aux = 0;;
		while (num > 0){
			aux = num % 10;
			num = num / 10;
			res = res + (aux * e);
			e = e * 2;
		}
		resultado = String.valueOf(res);
		return resultado;
	}
	
	public static String convertirDecimalHexadecimal(String num){
		String resultado = "" + Long.toHexString(Long.valueOf(num));
		return resultado;
	}
	
	public static String convertirHexadecimalDecimal(String num){
		int i;
		StringBuilder conversion = new StringBuilder(num).reverse();
		char [] str = conversion.toString().toCharArray();
		long res = 0;
		for (i = 0; i < conversion.length(); i++){
			if (Character.isDigit(str[i])){
				res = res + Integer.parseInt("" + (str[i] - 48)*(long)Math.pow(16, i));
			}
			else {
				switch(str[i]){
				case 'a': res = res + 10*(long)Math.pow(16, i); break;
				case 'b': res = res + 11*(long)Math.pow(16, i); break;
				case 'c': res = res + 12*(long)Math.pow(16, i); break;
				case 'd': res = res + 13*(long)Math.pow(16, i); break;
				case 'e': res = res + 14*(long)Math.pow(16, i); break;
				case 'f': res = res + 15*(long)Math.pow(16, i); break;
				}
			}
		}
		String resultado = new String();
		resultado = Long.toString(res);
		return resultado;
	}

	public static String convertirBinarioHexadecimal(String num){
		String resultado = convertirDecimalHexadecimal(convertirBinarioDecimal(Long.parseLong(num)));
		return resultado;
	}

	public static String convertirHexadecimalBinario(String num){
		String resultado = convertirDecimalBinario(Long.parseLong(convertirHexadecimalDecimal(num)));
		return resultado;
	}
}