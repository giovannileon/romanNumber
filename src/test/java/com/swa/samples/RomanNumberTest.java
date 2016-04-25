package com.swa.samples;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

public class RomanNumberTest {
   private RomanNumber roman;

   @BeforeTest
   public void initialize() {
      roman = new RomanNumber();
   }

   @Test
   public void aSingleNumberI() {
      int number = roman.convert("I");
      assertEquals(1, number);
   }

   @Test
   public void aSingleNumberV() {
      int number = roman.convert("V");
      assertEquals(5, number);
   }

   @Test
   public void aSingleNumberX() {
      int number = roman.convert("X");
      assertEquals(10, number);
   }

   @Test
   public void aSingleNumberL() {
      int number = roman.convert("L");
      assertEquals(50, number);
   }

   @Test
   public void aSingleNumberC() {
      int number = roman.convert("C");
      assertEquals(100, number);
   }

   @Test
   public void aSingleNumberD() {
      int number = roman.convert("D");
      assertEquals(500, number);
   }

   @Test
   public void aSingleNumberM() {
      int number = roman.convert("M");
      assertEquals(1000, number);
   }

   @Test
   public void twoLetterSimple() {
      int number = roman.convert("II");
      assertEquals(2, number);
   }
   
   @Test
   public void TwoLetterWithLessValueToTheLeft() {
      int number = roman.convert("IV");
      assertEquals(4, number);
   }

}
