<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<form:form method="GET" action="${contextPath }/performAccountSearchAdmin">
			<c:if test="${not empty msg}">
			    <div>
					<strong style="color: red; font-size: 16px">${msg}</strong>
			    </div>
			</c:if>
			<label> <spring:message code="product.searchBy"></spring:message> </label><br/>
			<input type="radio" name="criteriaGroup" value="name" checked="checked"> <spring:message code="account.criteria.name"></spring:message>
			<input type="radio" name="criteriaGroup" value="birthDate"><spring:message code="account.criteria.birthDate"></spring:message>
			<input type="radio" name="criteriaGroup" value="user.username"><spring:message code="account.criteria.username"></spring:message>
			<input type="radio" name="criteriaGroup" value="active"><spring:message code="account.criteria.active"></spring:message>
			<select name="operation">
				<option value="LIKE">=</option>
				<option value=">">></option>
				<option value="<"><</option>
			</select><input type="text" name="criteriaValue">
			
			<input type="submit" value="<spring:message code="product.searchButton"></spring:message>">
		</form:form>
		
		<form:form id="accountsForm" method="GET" modelAttribute="accounts">
				<table id="cartTable" border="1" class="table table-striped" >
				  <tr>
				    <th> <spring:message code="account.name"></spring:message></th>
				    <th> <spring:message code="account.birthdate"></spring:message></th> 
				    <th> <spring:message code="account.address"></spring:message></th>
				    <th> <spring:message code="account.username"></spring:message></th>
				    <th> <spring:message code="account.edit"></spring:message></th>
				    <th> <spring:message code="account.delete"></spring:message></th>
				 </tr>
				 <c:forEach var="account" varStatus="status" items="${accounts}">
				  <tr>
				    <td>${account.name}</td>
				    <td> <fmt:formatDate pattern="yyyy-MM-dd" value="${account.birthDate}" /></td> 
				    <td>${account.address}</td>
				    <td>${account.user.username}</td>
				    <td><a href="account_edit?id=${account.id}" class="name"><spring:message code="account.link_edit"></spring:message> </a></td>
				    <td><a href="account_delete?id=${account.id}" class="name"><spring:message code="account.link_delete"></spring:message> </a></td>
				  </tr>
				  </c:forEach>
				</table>
				
				<br>
		</form:form>
	</div>
	<ct:left></ct:left>
	<ct:right></ct:right>
</ct:body>
<ct:footer></ct:footer>

