import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 우선순위큐를 이용한 다익스트라 알고리즘 사용
 * </pre>
 * @author 박형규
 */
public class Main {
    
	static List<int[]>[] graph; // 각 정점마다 연결된 간선의 번호와 가중치를 저장할 리스트 배열
    static int[] dist; // 시작정점 기준으로 다른정점까지의 최단거리를 저장할 배열ㅌ
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        int start = Integer.parseInt(br.readLine()); // 시작 정점의 번호
        
        graph = new ArrayList[V+1]; // 각 정점마다 연결된 다른정점의번호와가중치를 저장할 어레이리스트 배열
        dist = new int[V+1]; // 시작정점기준 다른정점까지의 최단거리저장할 배열
        Arrays.fill(dist,Integer.MAX_VALUE); // 최단거리를 무한대로 초기화
        
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>(); // 각 정점마다 어레이리스트 초기화
        }
        
        for(int i=0; i<E; i++){  // 그래프 정보 저장
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 시작
            int v = Integer.parseInt(st.nextToken()); // 끝
            int w = Integer.parseInt(st.nextToken()); // 가중치
            graph[u].add(new int[] {v, w}); // 저장
        }
        
        dijkstra(start); // 다익스트라 알고리즘
        
        for(int i=1; i<=V; i++){
            if(dist[i]==Integer.MAX_VALUE) sb.append("INF\n"); // 무한대면 INF
            else sb.append(dist[i]).append("\n"); // 아니면 거리
        }
        System.out.print(sb); // 출력
    }

    static void dijkstra(int start){ // start: 시작정점
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        	@Override
        	public int compare(int[] a, int[] b) {
        		if (a[0] == b[0]) return a[1] - b[1]; // 거리가 같다면 정점번호 작은거부터
        		return a[0] - b[0]; // 거리가 작은것부터
        	}
		});
        
        dist[start] = 0; // 시작위치 거리는 0
        pq.offer(new int[] {0, start}); // 우선순위큐에 시작위치거리, 시작위치번호 삽입
        
        while(!pq.isEmpty()) {
            int[] info = pq.poll(); // 추출
            int distance = info[0], current = info[1];
            
            if (dist[current] < distance) { // 현재정점까지 도달하는데 걸리는 거리가 현재거리보다 이미 작아져잇는상태면
            	continue; // 수행하지 않고 넘어감
            }
            
            for (int[] edge : graph[current]) { // 현재정점과 연결된 다른 정점들 탐색
				int next = edge[0], weight = edge[1]; // 다음 정점번호 다음정점으로 가는데 필요한 가중치
				if (dist[next] > dist[current] + weight) { // 시작정점에서 다음정점으로 가는 기존에 저장된 거리보다 시작정점에서 현재정점을 거쳐 다음정점으로 가는 거리가 더 짧으면
					dist[next] = dist[current] + weight; // 거리 갱신해주고
					pq.offer(new int[] {dist[next], next}); //우선순위큐에 넣어줌
				}
			}
            
        }
    }
    
}