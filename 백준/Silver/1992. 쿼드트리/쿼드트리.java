import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * 분할정복을 이용해 풀엇다
 * </pre>
 * 
 * @author 박형규
 */
public class Main {

	static int arr[][]; // 영상의 정보를 저장할 2차원 배열
	static StringBuilder sb = new StringBuilder(); // 출력스트림
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		int N = Integer.parseInt(br.readLine()); // 영상의 가로세로길이

		arr = new int[N][N]; // N*N 크기의 영상 정보를 저장하기 위해 2차원배열 초기화
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (c[j] == '1') { // 0=흰점, 1=검은점
					arr[i][j] = 1; // 1일때 1로세팅
				}
			}
		}
		
		divideAndConquor(0, 0, N); // 분할정복 수행
		
		System.out.print(sb); // 출력 결과
	}

	private static void divideAndConquor(int r, int c, int size) {
		
		int sum = 0;
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				sum += arr[i][j];
			}
		}
		
		if (sum == 0) { // size*size가 모두 흰점일때
			sb.append(0);
		} else if (sum == size * size) { // 모두 검은점일때
			sb.append(1);
		} else { // 4개 영역으로 분할
			int half = size/2;
			sb.append("(");
			divideAndConquor(r, c, half);
			divideAndConquor(r, c+half, half);
			divideAndConquor(r+half, c, half);
			divideAndConquor(r+half, c+half, half);
			sb.append(")");
			
		}
		
	}

}
