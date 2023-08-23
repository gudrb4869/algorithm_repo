import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * S에 시작해서 T에 끝나는 N개의 수업이 존재
 * 최소의 강의실을 사용해서 모든 수업을 가능하게 해야함
 * 
 * 정리
 * BJ 1931 회의실 배정과 살짝 비슷한 문제인데 살짝 다르다.
 * 위 문제는 한 강의실에 최대한 많은 강의를 넣어야하기때문에, 빨리끝나는수업을먼저넣어야 남은시간동안 더많은 수업을 넣을 가능성이 생기기때문에 끝나는시간을 우선으로 정렬해줬다.
 * 하지만 이문제는 최소한의 강의실을 사용하여 모든 수업들을 배정해야하기때문에, 빨리시작하는수업을먼저강의실에배정해야 최소한의 강의실로 배정할 가능성이 생긴다.
 * 따라서 시작시간 기준 오름차순정렬하고, 우선순위큐를 써서 만약 꺼낸값의 종료시간보다 해당수업의 시작시간이 더 작거나 같을땐 큐에서 꺼낸다.
 * 수업 종료시간을 우선순위큐에 삽입한다.
 * </pre>
 * @author 박형규
 */
public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st; // 공백 단위 입력 처리
		
		int N = Integer.parseInt(br.readLine()); // 수업의 개수
		
		Course[] courseList = new Course[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			courseList[i] = new Course(start, end);
		}
		
		Arrays.sort(courseList);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(courseList[0].end); // 첫 강의실 세팅
		for (int i = 1; i < N; i++) {
			if (pq.peek() <= courseList[i].start) {
				pq.poll();
			}
			pq.offer(courseList[i].end);
		}

		System.out.println(pq.size()); // 결과 출력
	}

}

class Course implements Comparable<Course> {
	int start, end;
	
	public Course(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Course o) {
		if (start == o.start) return end - o.end; // 시작 시간 같은 경우 끝나는 시간이 빠른 수업이 먼저오도록 정렬
		return start - o.start; // 시작 시간이 빠른 수업이 먼저오도록 정렬
	}
	
}