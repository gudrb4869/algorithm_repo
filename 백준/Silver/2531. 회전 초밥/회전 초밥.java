import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int[] sushi = new int[N * 2 - 1];
		
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		for (int i = N; i < N * 2 - 1; i++) {
			sushi[i] = sushi[i - N];
		}
		
		int[] selected = new int[d + 1]; // 고른 초밥의 개수를 저장할 배열 
		int count = 0; // 먹을수 있는 초밥의 가짓수
		boolean flag = false; // 쿠폰번호와 같은 초밥 선택 여부
		for (int i = 0; i < k; i++) {
			if (selected[sushi[i]]++ == 0) { // 아직 미선택 초밥인 경우
				if (sushi[i] == c) // 쿠폰번호와 같은 초밥인 경우
					flag = true;
				count++; // 가짓수 1증가
			}
		}
		
		int left = 0, right = k; // 투 포인터
		int answer = count; // 초밥의 가짓수의 최댓값
		
		while (right < N * 2 - 1) {
			if (--selected[sushi[left]] == 0) { // 왼쪽 초밥 개수 1감소했는데 0인 경우
				if (sushi[left] == c)
					flag = false;
				count--; // 가짓수 1감소
			}
			if (selected[sushi[right]]++ == 0) { // 오른쪽 초밥이 0개인 경우. 동시에 오른쪽 초밥 개수 1증가
				if (sushi[right] == c)
					flag = true;
				count++; // 가짓수 1증가
			}
			answer = Math.max(answer, flag ? count : count + 1); // 쿠폰번호에해당하는 초밥을 고른 경우에는 count, 안고른경우에는 count+1을 현재 최댓값과 비교한다음 갱신
			
			left++; // 포인터 한칸이동
			right++; // 포인터 한칸이동
		}
		
		System.out.println(answer);
	}

}