import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 0과 1로만 이루어진 행렬A 행렬B가 있을때,
 * 행렬A를 행렬B로 바꾸는데 필요한 연산횟수의 최솟값은?
 * 행렬을 변환하는 연산=> 3*3크기의 부분 행렬에 있는 모든 원소를 뒤집는것
 * 
 * (N-2) * (M-2) 번 순회하면서 수가 다르면 3*3크기만큼의 수들을 뒤집어준다.
 * 이후 A와 B가 같다면 연산횟수 출력하고, 다르면 -1 출력하도록함.
 * </pre>
 * @author 박형규
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위 분리
		
		int N = Integer.parseInt(st.nextToken()); // 행렬의 세로크기
		int M = Integer.parseInt(st.nextToken()); // 행렬의 가로크기
		
		char[][] A = new char[N][M]; // 행렬 A
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine().toCharArray();
		}
		
		char[][] B = new char[N][M]; // 행렬 B
		for (int i = 0; i < N; i++) {
			B[i] = br.readLine().toCharArray();
		}
		
		int answer = 0; // 필요한 연산의 횟수
		
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 2; j++) {
				if (A[i][j] != B[i][j]) { // 서로 다르면
					answer++; // 연산의 횟수 1증가
					for (int r = i; r < i + 3; r++) { // A에서 3*3크기의 부분행렬에 있는 모든 원소를 뒤집어줌
						for (int c = j; c < j + 3; c++) {
							if (A[r][c] == '0') A[r][c] = '1';
							else A[r][c] = '0';
						}
					}
				}
			}
		}
		
		System.out.println(check(N, M, A, B) ? answer : -1); // A와 B가 같다면 A를 B로 바꿀수 있으므로 연산의 횟수 출력, 다르다면 바꿀수 없는경우이므로 -1출력
	}

	/**
	 * 행렬 A와 행렬 B가 같은지 체크하는 메서드
	 * @param N 행렬의 세로크기
	 * @param M 행렬의 가로크기
	 * @param A 행렬 A
	 * @param B 행렬 B
	 * @return 행렬A와 B가 같다면 true, 다르면 false 리턴
	 */
	private static boolean check(int N, int M, char[][] A, char[][] B) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] != B[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}