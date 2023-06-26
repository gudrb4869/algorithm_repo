n = int(input())
T, P = [0], [0]
for _ in range(n):
    t, p = map(int, input().split())
    T.append(t)
    P.append(p)

D = [0] * (n + 1)

for i in range(1, n + 1):
    if i + T[i] - 1 <= n:
        D[i + T[i] - 1] = max(D[i + T[i] - 1], D[i - 1] + P[i])
    D[i] = max(D[i], D[i - 1])

print(D[-1])