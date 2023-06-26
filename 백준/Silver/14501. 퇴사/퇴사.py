n = int(input())
T, P = [0], [0]
for _ in range(n):
    t, p = map(int, input().split())
    T.append(t)
    P.append(p)

D = [0] * (n + 1)

for i in range(1, n + 1):
    for j in range(i):
        if j + T[j] <= i:
            if i + T[i] <= n + 1:
                D[i] = max(D[i], D[j] + P[i])

print(max(D))