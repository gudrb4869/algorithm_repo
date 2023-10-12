import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * | F(n+1) F(n) | = |1 1|^(n)   (n>=1)  
 * | F(n) F(n-1)   |   |1 0|
 * 
 * 행렬의 거듭제곱을 계산하기 위하여 분할정복과 메모이제이션을 이용하여 문제를 풀었다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static Map<Long, long[][]> map = new HashMap<>();
	static final int MOD = 1000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map.put(1L, new long[][] {{1,1}, {1,0}});
		
		long n = Long.parseLong(br.readLine()); // 1 ~ 10^18

		long[][] matrix = power(n); // 행렬 [[1,1],[1,0]] 의 n 거듭제곱.
		// matrix[0][0] = f(n+1), matrix[0][1] = matrix[1][0] = f(n), matrix[1][1] = f(n-1)
		
		System.out.println(matrix[0][1]);
	}

	private static long[][] power(long n) {
		
		if (map.get(n) != null) {
			return map.get(n);
		}
		
		long[][] matrix = new long[2][2];
		
		long[][] A = ((n & 1) == 1) ? power((n >> 1) + 1) : power(n >> 1);
		long[][] B = power(n >> 1);
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					matrix[i][j] = (matrix[i][j] + A[i][k] * B[k][j]) % MOD;
				}
			}
		}
		
		map.put(n, matrix);
		return matrix;
	}

}