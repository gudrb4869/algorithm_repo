import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * B가 최대 10^11이므로 분할정복을 이용해 풀엇다
 * 같은 연산 반복을 피하기 위해 메모이제이션을 활용함
 * 해시맵에 계산결과 저장하여 이미 계산끝난 행렬제곱이면 갖다 썼음
 * </pre>
 * 
 * @author 박형규
 */
public class Main {

	static int N, A[][];
	static long B;
	static Map<Long, int[][]> mem = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 행렬의 크기
		B = Long.parseLong(st.nextToken()); // 행렬 A를 B제곱

		A = new int[N][N]; // 행렬 A

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()); // 행렬A의 각 원소 저장 0~1000
			}
		}

		int[][] result = dc(B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(" "); // 행렬 A를 B제곱한 결과
			}
			sb.append("\n");
		}
		System.out.print(sb); // 출력
	}

	private static int[][] dc(long n) {
		
		if (mem.get(n) == null) { // A의 n제곱 연산 결과가 존재하지 않으면 조건문 내부 수행
			
			int[][] result = new int[N][N];
			
			if (n == 1) { // 1제곱이면 A 자기자신의 원소들을 1000으로 나눈나머지로 하여 리턴
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						result[r][c] = A[r][c] % 1000;
					}
				}
				
			} else {
				
				int[][] a = dc(n / 2);
				int[][] b = (n % 2 == 1) ? dc(n / 2 + 1) : dc(n / 2); // n이 홀수 짝수인지에 따라 결과값 리턴
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						for (int i = 0; i < N; i++) {
							result[r][c] += (a[r][i] * b[i][c]) % 1000;
							result[r][c] %= 1000;
						}
					}
				}
			}
			mem.put(n, result); // n제곱 결과 맵에 저장
		}

		return mem.get(n); // A의 n제곱 결과 리턴
	}
}