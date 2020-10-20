<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Reservation Court Form</title>
<style>
.error {
  color: #ff0000;
  font-weight: bold;
}
</style>
</head>

<body>
<!-- 마법사 폼 첫번째 페이지 -->
<!-- 모든 폼은 modelAttribute="reservation"에 바인딩 -->
<form:form method="post" modelAttribute="reservation">
<table>
  <tr>
    <td>Court Name</td>
    <td><form:input path="courtName" /></td>
    <td><form:errors path="courtName" cssClass="error" /></td>
  </tr>
  <tr>
    <td colspan="3">
      <input type="hidden" value="0" name="_page"/> <!-- _page 현재 페이지 인덱스를 나타냄, 현재 페이지를 추적하기 위함 -->
      <input type="submit" value="Next" name="_target1" /> <!-- _target1 다음 페이지  _targetx에서 x가 다음페이지 번호를 나타냄-->
      <input type="submit" value="Cancel" name="_cancel" /> <!-- _cancel 취소 -->
    </td>
  </tr>
</table>
</form:form>
</body>
</html>
