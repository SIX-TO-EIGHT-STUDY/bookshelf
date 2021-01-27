####2.7 문서를 작성하는 대신 테스트를 만드세요.

* 코드를 문서화 하는 대신 코드를 깔끔하게 만들기 바랍니다.
* 훌륭하고 깔끔한 단위 테스트를 만들기 위해 드릴 수 있는 최고의 조언은 '메인' 코드만큼 단위 테스트에도 관심을 기울이라는 것입니다.

####2.8 모의 객체(Mock) 대신 페이크 객체(Fake)를 사용하세요.
* 요점은 모킹이 나쁜 프랙티스라는 사실입니다.
* 테스트가 객체의 내부의 구현 세부사항을 알면 테스트가 취약해지고 유지보수도 하기 어려워 집니다.

####2.9 인터페이스를 짧게 유지하고 스마트를 사용하세요.
* 클래스를 작게 만드는 것이 중요하다면 인터페이스를 작게 만드는 것은 훨씬 더 중요합니다.

```java
interface Exchange { 
    float rate(String target);
    float rate(String source, String target);
}
```

너무 많은 것을 요구하기 때문에 설계 관점에서는 형편없는 인터페이스 입니다.
따라서, 인터페이스 안에 '스마트(smart)' 추가해서 해결할 수 있습니다.

```java
interface Exchange {
    float rate(String Source, String target);
    final class Smart {
        private final Exchange origin;
        public float toUsd(String source) {
            return this.origin.rate(source, "USD");
        }   
    }
}
```