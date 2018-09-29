# ContentNegotiatingViewResolver

* ModelAndView 객체의 SetView 메서드를 이용해 수동으로 View를 지정하는게 아니라, Accept Header의 mediaType에 따라 자동으로 View 가 협상되도록 하는 법

  * Accept : text/html or 지정안하면 html 형식
  * Accept : application/json json 형식
  * Accept : application/xml xml 형식
  
```xml
<bean class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
	<property name="mediaTypes">
	    <map>
	        <entry key="xml" value="application/xml" />
	        <entry key="json" value="application/json" />
	        <entry key="html" value="text/html" />
	    </map>
	</property>
	<property name="viewResolvers">
	    <list>
	        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<property name="prefix" value="/WEB-INF/jsp/"/>
				<property name="suffix" value=".jsp"/>
			</bean>
	        <bean class="org.springframework.web.servlet.view.BeanNameViewResolver"/>
	    </list>
	</property>
	<property name="defaultViews">
		<list>
			<ref bean="xmlView" />
			<ref bean="jsonView" /> 
		</list>
	</property> 
</bean>
```
