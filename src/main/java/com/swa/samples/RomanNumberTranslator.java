package com.swa.samples;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import java.util.Map;

import org.apache.log4j.Logger;

import com.swa.samples.exceptions.InvalidRomanNumberException;

public class RomanNumberTranslator {

	private Map<String, Integer> decodeTable;
	private static Logger log = Logger.getLogger(RomanNumberTranslator.class);

	public RomanNumberTranslator() {
		decodeTable = new Hashtable<String, Integer>();
		decodeTable.put("I", 1);
		decodeTable.put("V", 5);
		decodeTable.put("X", 10);
		decodeTable.put("L", 50);
		decodeTable.put("C", 100);
		decodeTable.put("D", 500);
		decodeTable.put("M", 1000);

	}

	public int convert(String romanNumber) {
		// TODO Auto-generated method stub
		int value = 0;
		int indexNextLetter = 0;
		int length = romanNumber.length();
		char nextRomanLetter;
		char currentRomanLetter;

		int currentValue = 0;
		int base10counter = 0;

		for (int i = 0; i < length; i++) {
			indexNextLetter = i + 1;

			currentRomanLetter = romanNumber.charAt(i);

			if (indexNextLetter < length) {
				nextRomanLetter = romanNumber.charAt(indexNextLetter);

				if (isLetterType1(currentRomanLetter)) {
					base10counter++;
					verifiedLetterType1NextTwoValuesGrather(romanNumber, i);

					if (base10counter >= 3) {
						throw new InvalidRomanNumberException();
					}
				} else {
					base10counter = 0;
				}

				if (isLetterType5(currentRomanLetter)) {
					verifiedLetterType5(romanNumber, i);
				}

				currentValue = calculateValue(currentRomanLetter, nextRomanLetter);

			} else {
				currentValue = decodeTable.get(String.valueOf(currentRomanLetter));
			}
			value += currentValue;

		}
		return value;
	}

	private boolean isLetterType1(char currentRomanLetter) {
		if (currentRomanLetter == 'I' || currentRomanLetter == 'X' || currentRomanLetter == 'C'
				|| currentRomanLetter == 'M') {
			return true;
		}
		return false;
	}

	private void verifiedLetterType1NextTwoValuesGrather(String romanNumber, int index) {

		char currentRomanLetter = romanNumber.charAt(index);
		char nextRomanLetter = romanNumber.charAt(index + 1);
		char threeRomanLetter;
		if (index + 2 < romanNumber.length()) {
			threeRomanLetter = romanNumber.charAt(index + 2);
			if (firstLetterGratherTo(nextRomanLetter, currentRomanLetter)
					&& firstLetterGratherTo(threeRomanLetter, currentRomanLetter)) {
				throw new InvalidRomanNumberException();

			}

		}
	}

	private boolean firstLetterGratherTo(char firstLetter, char secondLetter) {
		int firstValue = decodeTable.get(String.valueOf(firstLetter));
		int secondValue = decodeTable.get(String.valueOf(secondLetter));

		if (firstValue > secondValue) {
			return true;
		}
		return false;
	}

	private boolean isLetterType5(char currentRomanLetter) {
		if (currentRomanLetter == 'V' || currentRomanLetter == 'L' || currentRomanLetter == 'D') {
			return true;
		}
		return false;
	}

	private void verifiedLetterType5(String romanNumber, int index) {

		char currentRomanLetter = romanNumber.charAt(index);
		char nextRomanLetter = romanNumber.charAt(index + 1);

		if (isPairNumberBase5(currentRomanLetter, nextRomanLetter)) {
			throw new InvalidRomanNumberException();
		}

		char threeRomanLetter;
		if (index + 2 < romanNumber.length()) {
			threeRomanLetter = romanNumber.charAt(index + 2);
			if (isLetterType5(threeRomanLetter)) {
				throw new InvalidRomanNumberException();

			}

		}
	}

	private boolean isPairNumberBase5(char currentRomanLetter, char nextRomanLetter) {
		int currentValue = decodeTable.get(String.valueOf(currentRomanLetter));
		int nextRomanValue = decodeTable.get(String.valueOf(nextRomanLetter));

		if (isLetterType5(currentRomanLetter) && isLetterType5(nextRomanLetter)) {
			if (nextRomanValue >= currentValue) {
				return true;
			}
		}
		return false;

	}

	private int calculateValue(char currentRomanLetter, char nextRomanLetter) {
		int currentValue = decodeTable.get(String.valueOf(currentRomanLetter));
		if (firstLetterGratherTo(nextRomanLetter, currentRomanLetter)) {
			currentValue = currentValue * -1;
		}
		return currentValue;
	}

	public void saveNumber(String filePath, String result) {

		File file = new File(filePath);

		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file,true))) {
			if (!file.exists()) {
				file.createNewFile();
			}

			byte[] bytesArray = result.getBytes();

			out.write(bytesArray);
			out.flush();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
