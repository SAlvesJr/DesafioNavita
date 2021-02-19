package classTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import numerosIrmaos.Number.GetNumber;

class GetNumberTest {

	@Test
	void invalidValueNumberTest() {
		Long valueInvalido = 100000001L;

		Integer valueReturn = GetNumber.validNumber(valueInvalido);

		assertEquals(-1, valueReturn);

		Long valueInvalido1 = -1L;

		Integer valueReturn1 = GetNumber.validNumber(valueInvalido1);

		assertEquals(-1, valueReturn1);
	}

	@Test
	void getHigherNumberTest() {
		String valueNumber = "213";
		Integer valueExpected = 321;

		Integer valorReturn = GetNumber.getHigherNumber(valueNumber);

		assertEquals(valueExpected, valorReturn);

		String valueNumber1 = "553";
		Integer valueExpected1 = 553;

		Integer valorReturn1 = GetNumber.getHigherNumber(valueNumber1);

		assertEquals(valueExpected1, valorReturn1);
	}

}
