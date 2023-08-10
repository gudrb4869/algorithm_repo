import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * N(짝수)개의 식재료를 N/2개씩 나누어 두개의 음식을 만들어야하기때문에 조합을 사용함
 * _nC_{n/2}로 A음식에 들어갈 식재료번호와 B에 들어갈 식재료번호를 구함
 * A음식과 B음식의 맛구할땐 조합을 이용해서 시너지값을 더했다.
 * A음식에 들어간 식재료번호N/2개중 순서를 고려해 2개를뽑아 S[i][j]와 S[j][i]를 더하여 A음식의 맛을 구했고
 * B음식에 들어간 식재료번호N/2개중 순서를 고려해 2개를뽑아 S[i][j]와 S[j][i]를 더하여 B음식의 맛을 구함
 * 두음식맛의 차이중 최소인 값을 결과값에 저장하여 출력햇음
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	private static int N, S[][], A[], B[], answer;
	private static boolean isA[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int t = 1; t <= T; t++) { // 케이스마다 반복
			
			N = Integer.parseInt(br.readLine()); // 식재료의 수
			S = new int[N + 1][N + 1]; // 시너지값 저장할 2차원배열
			A = new int[N >> 1]; // A음식에 들어간 식재료 번호들
			B = new int[N >> 1]; // B음식에 들어간 식재료 번호들
			isA = new boolean[N + 1]; // 식재료가 A에 들어간건지 여부
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken()); // 시너지값 저장
				}
			}
			
			answer = 999999;
			combination(0, 1); // A음식과 B음식 만들기 위해 조합 사용
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	
	private static void combination(int cnt, int start) {
		
		if (cnt == N>>1) { // A음식에 넣기 위한 식재료를 N/2개 다 고른경우. B음식에 들어갈 N/2개의 식재료가 자동으로 설정됨.
			int a = 0, b = 0; // A, B배열의 인덱스
			for (int i = 1; i <= N; i++) {
				if (isA[i]) { // A음식으로 선택된 경우
					A[a++] = i; // A배열에 식재료번호 저장
				} else { // A음식으로 선택되지않은경우(B음식으로 선택된 경우)
					B[b++] = i; // B배열에 식재료번호 저장
				}
			}
			
			int tasteA = 0, tasteB = 0; // A음식의 맛, B음식의 맛
			for (int i = 0; i < N>>1; i++) {
				for (int j = i + 1; j < N>>1; j++) {
					tasteA += S[A[i]][A[j]] + S[A[j]][A[i]];
					tasteB += S[B[i]][B[j]] + S[B[j]][B[i]];
				}
			}
			answer = Math.min(answer, Math.abs(tasteA - tasteB));
			return;
		}
		
		for (int i = start; i <= N; i++) {
			isA[i] = true; // A음식의 식재료로 선택
			combination(cnt+1, i+1); 
			isA[i] = false; // 해제
		}
		
	}

}
