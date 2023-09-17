import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 박형규
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[3];
		for (int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if (arr[0] + arr[1] + arr[2] != 180) {
			System.out.println("Error");
		} else if (arr[0] == 60 && arr[1] == 60 && arr[2] == 60) {
			System.out.println("Equilateral");
		} else if (arr[0] == arr[1] || arr[1] == arr[2] || arr[2] == arr[0]) {
			System.out.println("Isosceles");
		} else {
			System.out.println("Scalene");
		}
	}

}