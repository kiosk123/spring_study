<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <!-- 
        code : 프로퍼티 파일에 정의된 프로퍼티명 
        text : 해당 프로퍼티명에 정의된 값이 없을 경우 출력할 값
    -->
    <title><spring:message code="welcome.title" text="Welcome"/></title>
</head>

<body>
<h2><spring:message code="welcome.message"
                    text="Welcome to Court Reservation System"/></h2>

Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>.

<hr/>
Handling time : ${handlingTime} ms

<br/>
Locale : ${pageContext.response.locale}

</body>
</html>
