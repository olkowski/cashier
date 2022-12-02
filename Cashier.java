package challenges;

import java.util.HashMap;
import java.util.Map;

public class Cashier {

	static Map<Integer, Integer> cc = new HashMap<Integer, Integer>();

	private static boolean canIGiveChange(Map<Integer, Integer> registerStatus, int customerPayment, int productCost) {
		int change = customerPayment - productCost;
		System.out.println("change = " + change);

		int max = 0, min = Integer.MAX_VALUE;
		for (int value : registerStatus.keySet()) {
			if (registerStatus.get(value) > 0) {
				cc.put(value, registerStatus.get(value));
				if (value > max) {
					max = value;
				}
				if (value < min) {
					min = value;
				}
			}

		}
		int[] arr = new int[change];

		return checkCompositions(arr, change, 0, min, max);

	}

	static boolean checkCompositions(int arr[], int n, int i, int min, int max) {
		if (n == 0) {
			if (isValid(arr, i)) {
				return true;
			}
		} else if (n > 0) {
			for (int k = 3; k <= max; k++) {
				arr[i] = k;
				if (checkCompositions(arr, n - k, i + 1, min, max)) {
					return true;
				}
			}
		}
		return false;
	}

	// Utility function to print array arr[]
	static boolean isValid(int arr[], int m) {

		boolean controle = true;

		Map<Integer, Integer> ccTemp = new HashMap<>(cc);

		for (int i = 0; i < m; i++) {
			if (ccTemp.get(arr[i]) != null && ccTemp.get(arr[i]) > 0) {
				ccTemp.put(arr[i], ccTemp.get(arr[i]) - 1);
			} else {
				controle = false;
				break;
			}
		}

		return controle;
	}

	// Driver program
	public static void main(String[] args) {

		Map<Integer, Integer> registerStatus = new HashMap<Integer, Integer>();

		registerStatus.put(1, 0); // One Dolar, 0 bills;
		registerStatus.put(2, 0); // Two Dolar, 0 bills;
		registerStatus.put(5, 3); // Five Dolar, 3 bills;
		registerStatus.put(10, 0); // Ten Dolar, 0 bills;
		int customerPayment = 20, productCost = 5;

		boolean result = canIGiveChange(registerStatus, customerPayment, productCost);
		System.out.printf("Result " + result);

	}
}
