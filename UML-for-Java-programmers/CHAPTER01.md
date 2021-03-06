### 1장 이 책의 개요

* UML(통합 모델링 언어)은 소프트웨어 개념을 다이어그램으로 그리기 위해 사용하는 시각적인 표기법이다.
* 명세 차원과 구현 차원의 다이어그램은 소스코드와 관계가 깊다. 명세 차원 다이어 그램은 결국에는 소스코드로 바꾸려고 그리는 것이며, 구현 차원 다이어그램도 
이미 있는 소스코드를 설명하려고 그리기 때문이다.
* 개념 모델은 컴퓨터나 데이터 처리 프로그램에 대해 아무것도 말해 주지 않지만, 명세 모델은 '프로그램의 일부를 실제로' 기술 한다.

#### 클래스 다이어 그램
* 사각형은 클래스를 나타내고, 화살표는 관계를 나타낸다.
* 다이어그램에서 모든 관계는 연관이다.
* 연관 위에 쓴 이름은 참조를 담는 변수의 이름과 대응된다.
* <<interface>> 표기법은 Comparable 이 인터페이스임을 나타낸다.

#### 객체 다이어그램
* 다이어그램에서는 객체는 사각형으로 표현되며, 이름에 밑줄이 있다.
* 콜론(:) 다음에 나오는 이름은 이 객체가 속한 클래스의 이름이다.

#### 시퀀스 다이어그램
* 대괄호([ ]) 안의 부울린 표현식은 '가드(guard)' 라고 하며 어떤 경로를 따라가야 할지 알려준다.
