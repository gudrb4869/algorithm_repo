import java.util.Scanner;

/**
 * <pre>
 * 둘레를 4구역으로 나눠서 for문으로 값을 채워나간다
 * 만약 n이 홀수면 가운데를 추가로 채워줌
 * </pre>
 * @author 박형규
 *
 */
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테스트 케이스 수
		
		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt(); // 달팽이의 크기
			int[][] arr = new int[n][n]; // 2차원배열 생성
			
			int val = 1; // 현재 달팽이 숫자
			int minL = 0; // 현재 최소 달팽이의 인덱스
			int maxL = n - 1; // 현재 최대 달팽이의 인덱스
			while (minL < maxL) {
				for (int i = minL; i < maxL; i++) {
					arr[minL][i] = val++; // 상단 저장
				}
				for (int i = minL; i < maxL; i++) {
					arr[i][maxL] = val++; // 우측면 저장
				}
				for (int i = maxL; i > minL; i--) {
					arr[maxL][i] = val++; // 하단 저장
				}
				for (int i = maxL; i > minL; i--) {
					arr[i][minL] = val++; // 좌측면 저장
				}
				minL++; // 최소 인덱스 1증가
				maxL--; // 최대 인덱스 1감소
			}
			if (minL == maxL) { // 달팽이 크기가 홀수면
				arr[minL][maxL] = val; // 한가운데 값 저장
			}
			
			// 출력
			System.out.println("#" + t);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

}
