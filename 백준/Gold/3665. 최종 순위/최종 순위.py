from collections import deque
import sys
input = sys.stdin.readline

def topology_sort():
    q = deque()
    result = []

    for i in range(1, n + 1):
        if indegree[i] == 0:
            q.append(i)

    while q:
        if len(q) > 1:
            break
        
        cur = q.popleft()
        result.append(cur)

        for nxt in graph[cur]:
            indegree[nxt] -= 1

            if indegree[nxt] == 0:
                q.append(nxt)
    if len(result) == n:
        print(*result)
    else:
        print('IMPOSSIBLE')

T = int(input())
for _ in range(T):
    n = int(input())
    graph = [set() for _ in range(n + 1)] # 방향그래프
    indegree = [0] * (n + 1) # 진입차수
    team = [0] * (n + 1) # 팀의 랭킹을 저장하는 리스트. 인덱스-팀번호, 값-순위
    data = list(map(int, input().split()))
    for i in range(n):
        team[data[i]] = i + 1 #팀번호에 해당하는 순위 저장

    for i in range(1, n + 1):
        for j in range(i + 1, n + 1):
            if team[i] < team[j]:
                graph[i].add(j)
                indegree[j] += 1
            else:
                graph[j].add(i)
                indegree[i] += 1
    
    m = int(input())
    for _ in range(m):
        a, b = map(int, input().split())
        x, y = team[a], team[b]
        if x > y and a in graph[b]:
            graph[b].remove(a)
            indegree[a] -= 1
            graph[a].add(b)
            indegree[b] += 1
        elif x < y and b in graph[a]:
            graph[a].remove(b)
            indegree[b] -= 1
            graph[b].add(a)
            indegree[a] += 1

    topology_sort()