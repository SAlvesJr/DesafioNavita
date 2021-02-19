package numerosIrmaos.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import numerosIrmaos.util.NumberUtil;

public class GetNumber {

	/**
	 * Validar o número passado.
	 * 
	 * @param number valor inteiro
	 * @return maior valor irmão
	 */
	public static Integer validNumber(Long number) {

		return NumberUtil.isNumberValid(number.intValue()) 
				? getHigherNumber(String.valueOf(number)) : -1;
	}

	/**
	 * Pegar o maior numeor irmão.
	 * 
	 * @param numberValue
	 * @return
	 */
	public static Integer getHigherNumber(String numberValue) {
		List<Integer> array = new ArrayList<Integer>();

		for (int i = 0; i < numberValue.length(); i++) {
			array.add(Integer.valueOf(Character.getNumericValue(numberValue.charAt(i))));
		}

		Collections.sort(array);
		Collections.reverse(array);

		return Integer.valueOf(array.toString().replaceAll("\\D*", "").trim());
	}

}
