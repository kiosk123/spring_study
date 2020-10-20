<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Reservation Form</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>

<body>
<!-- 
    post 메소드를 이용하여 폼 전송을 하고
    reservation 이라는 모델에 폼데이터를 바인딩 한다는 의미다
    modelAttribute="reservation" 브라우저가 쓴느 코드가 아니라
    HTML폼을 실제로 생성하는데 필요한 편의기능이다.
    
    cssClass 는 사용할 css class 이름이다
    errors path="*"는 모든 에러를 표시한다는 뜻이다.
    form:error및 form:input은 모두 form:form 태그 안에 두어야한다.
 -->
<form:form method="post" modelAttribute="reservation">
    <form:errors path="*" cssClass="error"/>
    <table>
        <tr>
            <td>Court Name</td>
            <td><form:input path="courtName"/></td>
            <td><form:errors path="courtName" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Date</td>
            <!--
                date 필드 값이 올바르지 않은 경우 컨트롤러는 다음 에러 코드를 생성한다.
                typeMismatch.command.date
                typeMismatch.date
                typeMismatch.java.time.LocalDate
                typeMismatch
                                    리소스번들이 정의되어 있다면 원하는 로케일의 리소스 번들 파일에 에러코드에 해당하는 메시지를 적는다
                ex) typeMismatch.date-Invalid date format
             -->
            <td><form:input path="date"/></td>
            <td><form:errors path="date" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Hour</td>
            <td><form:input path="hour"/></td>
            <td><form:errors path="hour" cssClass="error"/></td>
        </tr>
        <tr>
            
            <td colspan="3">
                <!-- 폼 데이터 전송 -->
                <input type="submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
