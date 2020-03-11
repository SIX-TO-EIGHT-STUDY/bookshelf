* RULE 10) toString 은 항상 재정의하라.
    * toString 의 일반 규약을 보면, toString 이 반환하는 문자열은 "...유용한 정보를 제공해야한다." 고 되어있다.
    * toString 을 잘 만들어 놓으면 클래스를 좀 더 쾌적하게 사용할 수 있다.
    * toString 메서드는 println 이나 printf 같은 함수, 문자열 연결 연산자(string concatenation operator), assert, 디버거등에 객체가 전달되면 자동으로 호출된다.
    * 가능하다면 toString 메서드는 객체내의 중요 정보를 전부 담아 반환해야 한다.
    * toString 이 반환하는 문자열의 형식을 명시하건 그렇지 않건 간에, 어떤 의도인지는 문서에 분명하게 남겨야 한다.
    * toString 이 반환하는 문자열에 포함되는 정보들은 전부 프로그래밍을 통해서 가져올 수 있도록 하라.
        * ex) PhoneNumber class 는 지역번호, 국번, 회선 번호의 정보를 가져올 수 있는 접근자(accessor) 메서드를 포함해야 한다.