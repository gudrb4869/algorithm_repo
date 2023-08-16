import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * 분할정복을 이용해 풀음
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int N; //영상의 가로세로크기
	static char[][] arr; // 영상의 정보를 저장할 2차원 배열
	static StringBuilder sb = new StringBuilder(); // 출력스트림
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		
		N = Integer.parseInt(br.readLine()); // 영상의 크기
		
		arr = new char[N][N]; // N*N 2차원배열 초기화
		for (int i = 0; i < N; i++) { // N개의 줄에 걸쳐
			arr[i] = br.readLine().toCharArray(); // 각줄의  영상의 정보를 한 문자단위로 쪼개어 배열로 저장 
		}
		
		compress(0, 0, N); // 분할정복 수행
		
		System.out.print(sb); // 출력스트림 출력
	}

	/**
	 * 분할 정복
	 * @param r 현재 살펴보고 있는 영역의 왼쪽상단 행
	 * @param c 현재 살펴보고 있는 영역의 왼쪽상단 열
	 * @param size 현재 살펴보고 있는 영역의 가로세로 길이
	 */
	private static void compress(int r, int c, int size) {
		
		int sum = 0; // 전체가 0인지 1인지 판별하기 위한 변수
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (arr[i][j] == '1') { // 해당영역이 1인경우
					sum++; // 1더해줌
				}
			}
		}
		
		if (sum == 0) { // 전체 영역이 0
			sb.append(0); // 0 삽입
		} else if (sum == size * size) { // 전체 영역이 1
			sb.append(1); // 1 삽입
		} else { // 전체영역이 0과 1이 섞여 있는 경우
			int half = size / 2; // 4분할하므로 가로,세로길이는 반으로 감소
			sb.append("("); // 4분할한 영역의 압축결과를 "("로 감싸고 시작
			compress(r, c, half); // 1구역(왼쪽 위)
			compress(r, c + half, half); // 2구역(오른쪽 위)
			compress(r + half, c, half); // 3구역(왼쪽 아래)
			compress(r + half, c + half, half); // 4구역(오른쪽 아래)
			sb.append(")"); // 4분할한 영역의 압축결과를 ")"로 감싸서 종료
		}
	}

}