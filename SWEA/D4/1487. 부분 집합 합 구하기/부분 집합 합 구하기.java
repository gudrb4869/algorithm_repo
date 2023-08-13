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
		
		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			n = sc.nextInt();
			s = sc.nextInt();
			arr = new Integer[n];
			answer = 0;
			
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			
			quickSort(0, n-1); // array 을 퀵정렬
			
			subset(0, 0);
			
			System.out.println(answer);
		}
	}
	
	private static void quickSort(int low, int high) {
		
		if (low < high) {
			int mid = quick(low, high);
			quickSort(low, mid - 1);
			quickSort(mid+1, high);
		}
	}

	private static int quick(int low, int high) {
		int pivot = low;
		int i = low + 1;
		int j = high;
		
		while (i < j) {
			while (i < high && arr[i] < arr[pivot]) {
				i++;
			}
			
			while (j > low && arr[pivot] <= arr[j]) {
				j--;
			}
			
			if (i < j) {
				swap(i, j);
			}
			
		}
		
		swap(pivot, j);
		
		return j;
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void subset(int cnt, int sum) {
		
		if (sum > s) {
			return;
		}
		
		if (cnt == n) {
			if (sum == s) {
				answer++;
			}
			return;
		}
		subset(cnt+1, sum+arr[cnt]);
		subset(cnt+1, sum);
	}

}
