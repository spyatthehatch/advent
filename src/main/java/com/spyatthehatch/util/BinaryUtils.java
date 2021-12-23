package com.spyatthehatch.util;

import java.math.BigInteger;

/**
 * Utility class of methods to support binary calculations.
 * 
 * @author Bill Everton
 * @version Advent 2021
 */
public class BinaryUtils {
	/**
	 * Return an integer value to an array of binary values, where the 0th bit
	 * is the Most Significant Digit.
	 * 
	 * @param binary Array of binary values.
	 * @return Integer value.
	 */
	public static int binaryArrayToInt(final int[] binary){
		final int length = binary.length;
		int sum = 0;
	
		for(int i = 0; i < length; i++){
			sum += binary[i] * Math.pow(2, length - 1 - i);
		}
		return sum;
	}
	
	/**
	 * Return an array of binary values from a String input, where the 0th bit
	 * is the Most Significant Digit.
	 * 
	 * @param input String input of 1s and 0s.
	 * @return Array of integer values, where the 0th bit is the MSD.
	 */
	public static int[] stringToBinaryArray(final String input){
		int output[] = new int[input.length()];
		
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '0'){
				output[i] = 0;
			} else {
				output[i] =1;
			}
		}
		return output;
	}

	/**
	 * 
	 * @param hex
	 * @return
	 */
	public static String hexToBinary(String hex){
		hex = hex.replaceAll("0", "0000");
		hex = hex.replaceAll("1", "0001");
		hex = hex.replaceAll("2", "0010");
		hex = hex.replaceAll("3", "0011");
		hex = hex.replaceAll("4", "0100");
		hex = hex.replaceAll("5", "0101");
		hex = hex.replaceAll("6", "0110");
		hex = hex.replaceAll("7", "0111");
		hex = hex.replaceAll("8", "1000");
		hex = hex.replaceAll("9", "1001");
		hex = hex.replaceAll("A", "1010");
		hex = hex.replaceAll("B", "1011");
		hex = hex.replaceAll("C", "1100");
		hex = hex.replaceAll("D", "1101");
		hex = hex.replaceAll("E", "1110");
		hex = hex.replaceAll("F", "1111");
		return hex;
	}
	
	public static int binaryToDecimal(final String s){
		return Integer.parseInt(s, 2);
	}
}