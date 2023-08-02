import java.util.Scanner;

/**
 * <pre>
 * N * N크기의 2차원배열 A의 인덱스는 1부터 시작함
 * A[i][j] = i * j일 때 A의 값들을 1차원배열 B에 오름차순 정렬하였을 때 B[k]의 값을 구해야한다.
 * N이 최대 10^5이므로 2차원배열 생성하거나 1차원배열을 만들경우 최소10^10만큼의 공간이 필요하므로 메모리 초과가 날것이다.
 * B[k]의 값을 S라고 할때, S보다 작거나 같은 숫자의 개수는 k개보다 작으면 안된다.
 * 만약 작다면 찾는 숫자의 시작 범위를 큰쪽으로 옮겨서 탐색한다.
 * B'에서 i행의 S보다 작은 숫자의 개수는 S/i와 n중 최소값이 된다. 
 * A를 1차원 배열에 옮기고 정렬하기전의 상태를 B' 라할때 i <= k 인 i에 대하여 B'[i] <= k 가 성립한다.
 * 마찬가지로 정렬된 배열 B에서 i <= k인 i에 대하여 B[i] <= k 도 성립한다.
 * </pre>
 * @author 박형규
 *
 */
public class Main {

	static int n, k;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); // 배열의 A의 크기
		k = sc.nextInt(); // 정렬된 배열 B의 인덱스
		
		System.out.println(binarySearch()); // 이진탐색 결과값 출력
	}
	
	/**
	 * 이진 탐색 알고리즘
	 * @return B[k]
	 */
	private static int binarySearch() {
		
		int start = 1, end = k; // 시작값 1, 끝값 k( B[k] <= k 이기 때문)
		int answer = 0; // B[k] 저장할 변수
		
		while (start <= end) {
			int mid = (start + end) / 2; // 중간값
			
			int cnt = getValue(mid); // 중간값보다 작은 숫자의 개수
			
			if (cnt < k) { // 중간값보다 작은 숫자의 개수가 k보다 작으면
				start = mid + 1; // 시작 지점을 교체
			} else { // 중간값보다 작은 숫자의 개수가 k보다 크거나 같으면
				end = mid - 1; // 종료 지점을 교체
				answer = mid; // 답을 중간값으로 지정
			}
		}
		
		return answer; 
	}
	
	/**
	 * S보다 작은 숫자의 개수를 반환하는 메서드
	 * @param s 숫자S
	 * @return 개수
	 */
	private static int getValue(int s) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result += Math.min(s / i, n); // S/i와 n중 최소값을 더함
		}
		return result;
	}

}
