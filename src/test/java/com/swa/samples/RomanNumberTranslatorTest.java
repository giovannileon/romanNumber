package com.swa.samples;

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.FileInputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swa.samples.exceptions.InvalidRomanNumberException;

import org.testng.annotations.BeforeTest;

public class RomanNumberTranslatorTest {
	private RomanNumberTranslator roman;

	@BeforeTest
	public void initialize() {
		roman = new RomanNumberTranslator();
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
	public void twoLetterWithLessValueToTheLeftIV() {
		int number = roman.convert("IV");
		assertEquals(4, number);
	}

	@Test
	public void thereLetterTest_VIII() {
		int number = roman.convert("VIII");
		assertEquals(8, number);
	}

	@Test
	public void moreThanTwoLetters_XLV() {
		int number = roman.convert("XLV");
		assertEquals(45, number);
	}

	@Test
	public void numbers_I_and_base10_cannot_repeat_just_4times() {
		try {
			roman.convert("IIII");
			Assert.fail("No exception was throw it is a invalid Number");
		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void fourLetterBaseTen() {
		try {
			roman.convert("XXXXXX");
			Assert.fail("No exception was throw it is a invalid Number");
		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void numberWithBaseVCanNotBeRepeated_VV() {

		try {

			roman.convert("VV");
			Assert.fail("No exception was throw it is a invalid Number");
		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}

	}

	@Test
	public void numberWithBaseLCanNotBeRepeated_LL() {

		try {

			roman.convert("LL");
			Assert.fail("No exception was throw");
		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}

	}

	@Test
	public void numberWithBaseDCanNotBeRepeated_DDD() {

		try {

			roman.convert("DDD");
			Assert.fail("No exception was throw");
		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}

	}

	@Test
	public void threeLetterBaseTen() {
		try {
			int number = roman.convert("XXX");
			assertEquals(30, number);

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letter_Type5subtract_45() {
		try {
			roman.convert("VL");
			Assert.fail("No exception was throw it is a invalid Number");
		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letter_Type5subtract_405() {
		try {
			roman.convert("VD");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letter_Type5subtract_450() {
		try {
			roman.convert("LD");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letterTypeIrepeted() {
		try {
			roman.convert("CCCC");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letterTypeIwithTwoMoreVaueVIX() {
		try {
			roman.convert("VIX");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letter_Type5subtract_9() {
		try {
			roman.convert("VIV");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letterTypeIwithTwoMoreVaue_IXX() {
		try {
			roman.convert("IXX");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letterTypeIwithTwoMoreVaueXCLX() {
		try {
			roman.convert("XCLX");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}

	@Test
	public void letterTipoFiveRepetedLXL() {
		try {
			roman.convert("LXL");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}
	
	@Test
	public void letterTipoFiveRepeted_XCC() {
		try {
			roman.convert("XCC");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}
	
	@Test
	public void letterTipoFiveRepeted_CMM() {
		try {
			roman.convert("CMM");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}
	@Test
	public void letterTipoFiveRepeted_IXVI() {
		try {
			roman.convert("IXVI");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}
	
	@Test
	public void letterTipoFiveRepeted_IVI() {
		try {
			roman.convert("IVI");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}
	
	@Test
	public void letterTipoFiveRepeted_CMC() {
		try {
			roman.convert("CMC");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}
	
	@Test
	public void letterTipoOneandRepetedLeft_IIV() {
		try {
			roman.convert("IIV");
			Assert.fail("No exception was throw it is a invalid Number");

		} catch (InvalidRomanNumberException e) {
			// if come here test is ok
		}
	}
	
		

	@Test
	public void saveNumberInFile() {

		String filePath = "C:/tmp/hello.txt";
		File f = new File(filePath);
		roman.saveNumber(filePath, "X equals 10\r\n");
		Assert.assertTrue(f.exists());
	}
	

}
