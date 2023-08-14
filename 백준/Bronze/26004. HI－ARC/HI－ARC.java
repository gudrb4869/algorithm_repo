import java.util.Scanner;

/**
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		char[] S = sc.next().toCharArray();
		
		int[] hiarc = new int[5]; // H=0, I=1, A=2, R=3, C=4 에 개수저장
		for (int i = 0; i < N; i++) {
			if (S[i] == 'H') {
				hiarc[0]++;
			} else if (S[i] == 'I') {
				hiarc[1]++;
			} else if (S[i] == 'A') {
				hiarc[2]++;
			} else if (S[i] == 'R') {
				hiarc[3]++;
			} else if (S[i] == 'C') {
				hiarc[4]++;
			}
		}
		
		int answer = hiarc[0];
		for (int i = 1; i < 5; i++) {
			if (answer > hiarc[i]) {
				answer = hiarc[i];
			}
		}
		System.out.println(answer);
	}

}
