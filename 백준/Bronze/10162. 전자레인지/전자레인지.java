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
		int T = Integer.parseInt(br.readLine()); // 요리시간
		
		int[] time = {300, 60, 10};
		int[] answer = new int[3];
		
		if (T % 10 != 0) { // 3개의 버튼으로 T초를 맞출 수 없는 경우
			System.out.println(-1);
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			answer[i] = T / time[i];
			T %= time[i];
			System.out.print(answer[i] + " ");
		}
		
	}

}