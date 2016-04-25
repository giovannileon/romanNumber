package com.swa.samples;

import java.util.Hashtable;

import java.util.Map;
import org.apache.logging.log4j.Logger;

public class RomanNumber {

   private Map<String, Integer> decodeTable;

   // private static Logger log = Logger

   public RomanNumber() {
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
      Integer value = 0;
      for (int i = 0; i < romanNumber.length(); i++) {

         String leftRomanLetter = romanNumber.substring(i, i + 1);
         int leftValue = decodeTable.get(leftRomanLetter);

         if (i + 2 < romanNumber.length()) {
            String rightRomanLetter = romanNumber.substring(i + 1, i + 2);
            int rightValue = decodeTable.get(rightRomanLetter);
            if (leftValue < rightValue) {
               leftValue = leftValue * -1;
            }
         }

         
         value += decodeTable.get(leftRomanLetter);

      }
      return value;
   }

}
