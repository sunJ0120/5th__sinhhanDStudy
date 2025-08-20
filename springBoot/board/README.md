## JPA 조인 시 주의할 점!

```java
List<Reply> replies = queryFactory
    .selectFrom(QReply.reply)
    .join(QReply.reply.board, QBoard.board).fetchJoin() // ← 여기!
    .where(QReply.reply.id.in(ids))
    .fetch(); // ← 결과 가져오기(터미널)
```

- 보통, join을 할 때 사용하는 방법은 다음과 같다.

1. 기본적으로 전부 LAZY 로딩 (지연로딩)
2. fetch join을 사용해서 가져온다. (join시)
3. @BatchSize를 사용해서 한번에 가져온다. (지연로딩시)

- 이렇게 하면 N+1(1+N) 문제를 해결할 수 있다.