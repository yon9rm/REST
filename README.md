# REST 핵심 원칙

1. Addressability(주소표현성)
 1) 리소스 식별자를 URI로 한다. (Unique ID)
2) URI 경로의 단어는 명사를 권장.
3) URI의 상위 경로는 하위 경로의 집합을 의미하는 단어로 구성. /book/java/
4) token과 같이 리소스와 직접 관련이 없는 정보는 쿼리스트링으로 처리.

2. Connectedness(연결성)
1) 하나의 리소스는 다른 리소스들에 대한 정보를 포함할 수 있다.
2) HATEOS
 - 해당 URI와 관련된 URI를 응답에 포함하여 관련된 URI의 경로가 바뀌어도 동적으로 사용 가능.
 - 예) 계좌 조회 URI 응답안에 이체 URI 를 포함한다.

3. Statelessness(상태없음)
1) 세션, 쿠키를 사용하지 않는다.
2) 세션과 같이 서버측에서 상태유지를 담당하지 않고, 클라이언트 측에서 상태유지를 하도록 한다.
3) 웹브라우져에서 사용가능한 쿠키는 모바일 앱과 같은 곳에서 사용할 수 없기 때문에 사용하지 않는다.
 - 예) token과 같이 서버에서 클라이언트에 넘겨서 가지고 있게하고 클라이언트에서 서버에 token을 넘겨 유효한지를 체크하여 상태유지를 시킨다.

4. Homogeneous Interface(동종 인터페이스)
1) HTTP에서 제공하는 기본적인 4가지 method를 사용한다.
 - GET 	  : 리소스 조회
 - POST 	  : 리소스 추가
 - PUT 	  : 리소스 변경
 - DELETE  : 리소스 삭제
2) 같은 URI를 사용하더라도 method에 따라 의미가 다름
 - /service/employee/012 
 - method 가 GET 이면 사원번호 012번의 정보 조회
 - method 가 DELETE 이면 사원번호 012번의 정보 삭제

5. Content Negotiation(컨텐트 협상)
1) 요청 정보에 Accept Header를 추가하여 리소스의 원하는 형태를 지정한다.
 - Accept : application/xml -> xml 응답
 - Accept : application/json -> json 응답
2) URI에 확장자로 리소스의 원하는 형태를 지정한다.
 - /employee/012.xml
 - /employee/012.json

# Content Negotiation

1. xml view
<!-- 여기에 지정된 녀석만 보인다 -->
<property name="modelKey" value="data"></property>

2. json view
<!-- 여기에 지정된 녀석만 보인다 -->
<property name="modelKey" value="data"></property>
		
<!-- 필요없는 껍데기 제외 -->
<property name="extractValueFromSingleKeyModel" value="true"></property>

# PUT, DELETE method 사용시 주의할 점

tip) PUT, DELETE method 사용시 주의할 점
1) Content-Type : application/x-www-form-urlencoded 
 - form 형식의 미디어 타입 사용시 아파치 톰캣의 경우 PUT, DELETE 메소드를 사용할 수 없으므로 따로 설정을 해줘야한다. 
 - 예) filter
