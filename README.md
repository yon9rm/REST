# HttpMessageConverter 

* Response Body, Request Body를 지정된 포맷으로 변환해 준다.

  * MarshallingHttpMessageConverter
  * MappingJacksonHttpMessageConverter

* Converter가 어떤 Representation으로 변환할지를 결정하기 위해 bean의 Property를 추가해야 한다.

```xml
<bean id="marshallingConverter" class="org.springframework.http.converter.xml.MarshallingHttpMessageConverter">
	<constructor-arg ref="jaxb2Marshaller" />
	<property name="supportedMediaTypes" value="application/xml"/>
</bean>

<bean id="jsonConverter" class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
	<property name="supportedMediaTypes" value="application/json" />
</bean>
```
* AnnotationMethodHandlerAdapter Bean에 Converter 추가

  * issue
    * issue스프링 3.2 부터 DefaultAnnotationHandlerMapping -> RequestMappingHandlerMapping 로 변경
    * issue스프링 3.2 부터 AnnotationMethodHandlerAdapter -> RequestMappingHandlerAdapter 로 변경

```xml
<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	<property name="messageConverters">
		<list>
			<ref bean="marshallingConverter" />
			<ref bean="jsonConverter" />
		</list>
	</property>
</bean>
```

* View 이름 또는 ModelAndView 객체가 아닌 @ResponseBody를 이용하여 실제 리소스 객체를 직접 리턴한다.
