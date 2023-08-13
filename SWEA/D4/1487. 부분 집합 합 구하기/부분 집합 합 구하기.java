import java.util.Scanner;

/**
 * <pre> 
 * N개의 원소가 있을때 합이 S인 부분집합의 개수를 구하는 문제
 * nC1부터 nCn까지 다 보면 될거같다.
 * 바이너리 카운팅으로 해봐야겟다
 * </pre>
 * @author 박형규
 */
public class Solution {

	static int n, s, answer;
	static Integer[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테스트 케이스 수
		
		for (int t = 1; t <= T; t++) { // 테스트 케이스마다 수행
			n = sc.nextInt(); // 집합원소의 개수
			s = sc.nextInt(); // 문제에서 요구한 부분집합 원소의 합
			arr = new Integer[n]; // 원소의 값을 저장할 배열
			answer = 0; // 원소의 합이 S인 부분집합의 개수
			
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			
			quickSort(0, n-1); // array 을 퀵정렬
			
			subset(0, 0); // 부분집합
			
			System.out.println(answer); // 경우의수 출력
		}
	}
	
	private static void quickSort(int low, int high) {
		
		if (low < high) { // 퀵정렬 조건
			int mid = quick(low, high); // pivot값
			quickSort(low, mid - 1); // pivot값 기준 좌측 퀵정렬 재귀호출
			quickSort(mid+1, high); // pivot값 기준 우측 퀵정렬 재귀호출
		}
	}

	private static int quick(int low, int high) {
		int pivot = low; // pivot값, low~high 범위내에서 low로 설정
		int i = low + 1; // pivot값보다 크거나 같은 값이 있는지 탐색
		int j = high; // pivot값보다 작은 값이 있는지 탐색
		
		while (i < j) { // 반복 조건
			while (i < high && arr[i] < arr[pivot]) { // high에 도달하거나 i위치값이 pivot위치값보다 크거나같은경우 반복중단
				i++; // i 한칸뒤로
			}
			
			while (j > low && arr[pivot] <= arr[j]) { // low에 도달하거나 j위치값이 pivot위치값보다 작은경우 반복중단
				j--; // j 한칸앞으로
			}
			
			if (i < j) { // i가 j보다 작은경우에만
				swap(i, j); // 해당 위치에 있는 값 교환
			}
			
		}
		
		swap(pivot, j); // pivot과 j위치 값 교환
		
		// j기준으로 low~j-1은 j에있는값보다 작은값만 존재, j+1~high는 j에있는값보다 크거나같은값만 존재
		return j; // j위치에 pivot에 있던값이 저장되어있음
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void subset(int cnt, int sum) { // cnt: 현재탐색위치, sum: 현재까지 원소의합
		
		if (sum > s) { // 합이 문제요구사항인 S보다 커지면 더이상 탐색할 필요가 없으므로
			return; // 중단
		}
		
		if (cnt == n) { // n개의 원소모두 탐색완료한 경우
			if (sum == s) { // 합이 s와같은 경우
				answer++; // 경우의수 1카운트
			}
			return;
		}
		subset(cnt+1, sum+arr[cnt]); // cnt위치원소를 뽑은경우
		subset(cnt+1, sum); // cnt위치원소를 뽑지않은경우
	}

}
