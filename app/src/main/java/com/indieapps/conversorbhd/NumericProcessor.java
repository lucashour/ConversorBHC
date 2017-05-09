package com.indieapps.conversorbhd;

public class NumericProcessor {

	public static boolean verifyDecimal(String num){
		int index;
		char [] str = num.toCharArray();

		for(index = 0; index < str.length; index++)
			if (!(Character.isDigit(str[index])))
				return false;
		return true;
	}

	public static boolean verifyBinary(long num){
		long aux = 0;

		while (num != 0){
			aux = num % 10;
			num = num / 10;
			if (aux > 1)
				return false;
		}
		return true;
	}

	public static boolean verifyStringBinary(String num){
		char [] str = num.toCharArray();

		for(int index = 0; index < str.length; index++){
			if (!(str[index] == '0' || str[index] == '1')){
				return false;
			}
		}
		return true;
	}

	public static boolean verifyHexa(String num){
		int index;
		char [] str = num.toCharArray();
		for(index = 0; index < str.length; index++)
			if (!((Character.isDigit(str[index])) || (str[index] >= 'a' && str[index] <= 'f')))
				return false;
		return true;
	}

	public static String castDecimaltoToBinary(long num){
		return "" + Long.toBinaryString(num);
	}
	
	public static String castBinaryToDecimal(long num){
		long res = 0, aux = 0;
		int e = 1;

		while (num > 0){
			aux = num % 10;
			num = num / 10;
			res = res + (aux * e);
			e = e * 2;
		}

		return String.valueOf(res);
	}
	
	public static String castDecimalToHexa(String num){
		return "" + Long.toHexString(Long.valueOf(num));
	}
	
	public static String castHexaToDecimal(String num){
		int index;
		StringBuilder converted = new StringBuilder(num).reverse();
		char [] str = converted.toString().toCharArray();
		long res = 0;

		for (index = 0; index < converted.length(); index++){
			if (Character.isDigit(str[index]))
				res += Integer.parseInt("" + (str[index] - 48)*(long)Math.pow(16, index));
			else {
				switch(str[index]){
				case 'a': res += 10 * (long)Math.pow(16, index); break;
				case 'b': res += 11 * (long)Math.pow(16, index); break;
				case 'c': res += 12 * (long)Math.pow(16, index); break;
				case 'd': res += 13 * (long)Math.pow(16, index); break;
				case 'e': res += 14 * (long)Math.pow(16, index); break;
				case 'f': res += 15 * (long)Math.pow(16, index); break;
				}
			}
		}

		return Long.toString(res);
	}

	public static String castBinaryToHexa(String num){
		return castDecimalToHexa(castBinaryToDecimal(Long.parseLong(num)));
	}

	public static String castHexaToBinary(String num){
		return castDecimaltoToBinary(Long.parseLong(castHexaToDecimal(num)));
	}
}