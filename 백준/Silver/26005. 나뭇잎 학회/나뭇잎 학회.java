import java.util.Scanner;

/**
 * <pre>
 * D[i] = D[i - 2] + 2(i - 1) {i가짝수일때}
 * 
 * D[i] = D[i - 1] + i {i가홀수일때}
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] D = new int[N + 1];
		
		for (int i = 2; i <= N; i++) {
			if (i % 2 == 0) {
				D[i] = D[i - 2] + 2 * (i - 1);
			} else {
				D[i] = D[i - 1] + i;
			}
		}
		System.out.println(D[N]);
	}

}
