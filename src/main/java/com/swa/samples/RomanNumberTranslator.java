package com.swa.samples;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

		String currentRomanLetter;
		int totalRomanValue = 0;
		int type1Counter = 0;

		// int
		String lastRomanLetter = "";
		int lastRomanNumberValue = 0;
		int currentRomanValue = 0;

		for (int i = 0; i < romanNumber.length();) {

			if (areThereTwoLetterGratherNext(romanNumber.substring(i))) {
				throw new InvalidRomanNumberException();
			}

			if (isLetterSubtractingEgualsToNextOne(romanNumber.substring(i))) {
				throw new InvalidRomanNumberException();
			}

			currentRomanLetter = getCurrentRomanNumber(romanNumber.substring(i));
			currentRomanValue = getArabicValue(currentRomanLetter);

			if (isLetterType1(currentRomanLetter)) {
				type1Counter++;
			} else {
				type1Counter = 0;
			}

			if (isLetterType5(lastRomanLetter) && (lastRomanNumberValue < currentRomanValue)) {
				throw new InvalidRomanNumberException();
			}

			if (isLetterType5(lastRomanLetter) && isLetterType5(currentRomanLetter)
					&& isLetterType5Equals(lastRomanLetter, currentRomanLetter)) {
				throw new InvalidRomanNumberException();
			}

			if (type1Counter > 3) {
				throw new InvalidRomanNumberException();
			}

			totalRomanValue += currentRomanValue;
			lastRomanLetter = currentRomanLetter;
			lastRomanNumberValue = currentRomanValue;
			i = i + currentRomanLetter.length();

		}
		return totalRomanValue;

	}

	private boolean isLetterSubtractingEgualsToNextOne(String romanNumber) {
		if (romanNumber.length() > 2) {
			int firtLetterValue = getValue(String.valueOf(romanNumber.charAt(0)));
			int secondLetterValue = getValue(String.valueOf(romanNumber.charAt(1)));
			int thirdLetterValue = getValue(String.valueOf(romanNumber.charAt(2)));
			if ((firtLetterValue < secondLetterValue) && (firtLetterValue == thirdLetterValue)) {
				return true;
			}
		}
		return false;
	}

	private boolean areThereTwoLetterGratherNext(String romanNumber) {

		if (romanNumber.length() > 2) {
			int firtLetterValue = getValue(String.valueOf(romanNumber.charAt(0)));
			int secondLetterValue = getValue(String.valueOf(romanNumber.charAt(1)));
			int thirdLetterValue = getValue(String.valueOf(romanNumber.charAt(2)));
			if ((secondLetterValue > firtLetterValue) && (thirdLetterValue > firtLetterValue)) {
				return true;
			}
		}
		return false;
	}

	private String getCurrentRomanNumber(String romanStringValue) {

		String currentLetter = String.valueOf(romanStringValue.charAt(0));
		int currentLetterValue = getValue(currentLetter);

		if (romanStringValue.length() > 1) {
			String nextRomanLetter = String.valueOf(romanStringValue.charAt(1));
			int nextLetterValue = getValue(nextRomanLetter);
			if (currentLetterValue < nextLetterValue) {
				return currentLetter.concat(nextRomanLetter);
			}
		}
		return currentLetter;

	}

	private int getValue(String currentRomanLetter) {
		String currentLetter = String.valueOf(currentRomanLetter.charAt(0));
		int currentLetterValue = decodeTable.get(currentLetter);
		return currentLetterValue;
	}

	private int getArabicValue(String currentRomanLetter) {

		int currentLetterValue = getValue(currentRomanLetter);

		if (currentRomanLetter.length() > 1) {
			if (isLetterType5(String.valueOf(currentRomanLetter.charAt(0)))) {
				throw new InvalidRomanNumberException();
			}
			String nextRomanLetter = String.valueOf(currentRomanLetter.charAt(1));
			int nextLetterValue = getValue(nextRomanLetter);
			return nextLetterValue - currentLetterValue;
		}
		return currentLetterValue;
	}

	private boolean isLetterType1(String currentRomanNumber) {

		if (currentRomanNumber.length() == 1) {
			if (currentRomanNumber.equals("I") || currentRomanNumber.equals("X") || currentRomanNumber.equals("C")
					|| currentRomanNumber.equals("M")) {

				return true;
			}
		}
		return false;
	}

	private boolean isLetterType5(String currentRomanLetter) {

		if (currentRomanLetter.contains("V") || currentRomanLetter.contains("L") || currentRomanLetter.contains("D")) {
			return true;
		}
		return false;
	}

	private boolean isLetterType5Equals(String lastRomanLetter, String currentRomanLetter) {
		if (lastRomanLetter.charAt(lastRomanLetter.length() - 1) == currentRomanLetter
				.charAt(currentRomanLetter.length() - 1)) {
			return true;
		}
		return false;
	}

	public void saveNumber(String filePath, String result) {

		File file = new File(filePath);

		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file, true))) {
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
