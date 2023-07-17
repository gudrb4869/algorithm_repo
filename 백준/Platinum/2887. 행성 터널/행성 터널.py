import sys
input = sys.stdin.readline

n = int(input())
graph, edges = [], []
parent = [i for i in range(n)]

for i in range(n):
    x, y, z = map(int, input().split())
    graph.append((x, y, z, i))

def find(n):
    if parent[n] != n:
        parent[n] = find(parent[n])
    return parent[n]

def union(a, b):
    a = find(a)
    b = find(b)

    if a == b:
        return False
    parent[b] = a
    return True

def add_edges(c):
    graph.sort(key=lambda x: x[c])
    for i in range(1, n):
        edges.append((graph[i - 1][3], graph[i][3], abs(graph[i - 1][c] - graph[i][c])))

add_edges(0)
add_edges(1)
add_edges(2)

edges.sort(key=lambda x: x[2])

result = 0
for start, end, weight in edges:
    if union(start, end):
        result += weight
print(result)