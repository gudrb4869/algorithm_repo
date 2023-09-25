import sys, copy
from itertools import combinations
input = sys.stdin.readline

dr = (-1, 0, 1, 0)
dc = (0, 1, 0, -1)

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
empty = [(i, j) for i in range(n) for j in range(m) if graph[i][j] == 0]

answer = 0
for comb in list(combinations(empty, 3)):
    arr = copy.deepcopy(graph)
    for r, c in comb:
        arr[r][c] = 1

    virus = [(i, j) for i in range(n) for j in range(m) if arr[i][j] == 2]
    while virus:
        r, c = virus.pop()

        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]

            if 0 <= nr < n and 0 <= nc < m and arr[nr][nc] == 0:
                arr[nr][nc] = 2
                virus.append((nr, nc))
    
    cnt = 0
    for i in range(n):
        for j in range(m):
            if arr[i][j] == 0:
                cnt += 1
    answer = max(answer, cnt)

print(answer)