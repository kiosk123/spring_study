<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Reservation Player Form</title>
<style>
.error {
  color: #ff0000;
  font-weight: bold;
}
</style>
</head>

<body>
<!-- 마법사 폼 세번째 페이지 -->
<!-- 모든 폼은 modelAttribute="reservation"에 바인딩 -->
<form:form method="post" modelAttribute="reservation">
<table>
  <tr>
    <td>Player Name</td>
    <td><form:input path="player.name" /></td>
    <td><form:errors path="player.name" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Player Phone</td>
    <td><form:input path="player.phone" /></td>
    <td><form:errors path="player.phone" cssClass="error" /></td>
  </tr>
  <tr>
    <td colspan="3">
      <input type="hidden" value="2" name="_page"/>
      <input type="submit" value="Previous" name="_target1" />
      <input type="submit" value="Finish" name="_finish" /> <!-- _finish 폼 작성이 완료되었다는 것을 알림 -->
      <input type="submit" value="Cancel" name="_cancel" />
    </td>
  </tr>
</table>
</form:form>
</body>
</html>
