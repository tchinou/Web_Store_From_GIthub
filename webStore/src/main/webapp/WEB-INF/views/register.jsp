<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<div class="content">
				<img src="/webstore/resources/images/top_bg.gif" alt="" width="100%" height="12">
				<form:form id="userForm" method="POST" action="${contextPath}/doRegister"  modelAttribute="customer">
					<input type="hidden" id="id" name="id" value="${customer.id}">
					
					<c:if test="${not empty msg}">
					    <div>
							<strong style="color: red; font-size: 16px">${msg}</strong>
					    </div>
					</c:if>
					
					<c:choose>
						<c:when test="${customer.id > 0}">
							<input type="hidden" id="userId" name="user.id" value="${customer.user.id}">
						</c:when>
					</c:choose>
					
					<table>
						<tr>
							<td><p class="line"><spring:message code="account.name"></spring:message>:</p></td>
							<td><input type="text" id="name" name="name" value="${customer.name}"></td>
							<td><font color="red"><form:errors path="name"></form:errors></font><br/></td>
						</tr>
						<tr>
							<td><p class="line"><spring:message code="account.birthdate"></spring:message>:</p></td>
							<td><input type="text" id="birthDate" name ="birthDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${customer.birthDate}" />" style="width: 173px" placeholder="yyyy-MM-dd" maxlength="10"></td>
							<td><font color="red">${dateError}</font><br/></td>
						</tr>
						<tr>
							<td><p class="line"><spring:message code="account.address"></spring:message>:</p></td>
							<td><input type="text" id="address" name="address" value="${customer.address}"></td>
							<td><font color="red"><form:errors path="address"></form:errors></font><br/></td>
						</tr>
						<tr>
							<td><p class="line"><spring:message code="account.username"></spring:message>:</p></td>
							<td><input type="text" id="user.username" name="user.username" value="${customer.user.username}"></td>
							<td><font color="red"><form:errors path="user.username"></form:errors></font><br/></td>
						</tr>
						<c:choose>
							<c:when test="${customer.id == 0}">
								<tr>
									<td><p class="line"><spring:message code="account.password"></spring:message>:</p></td>
									<td><input type="password" id="password" name = "user.password" value="${customer.user.password}"></td>
									<td><font color="red"><form:errors path="user.password"></form:errors></font><br/></td>
								</tr>
								<tr>
									<td><p class="line"><spring:message code="account.retypePassword"></spring:message>:</p></td>
									<td><input type="password" id="retypePassword"  name="user.retypePassword" value="${customer.user.retypePassword}"></td>
									<td><font color="red"><form:errors path="user.password"></form:errors></font><br/></td>
								</tr>
							</c:when>
							<c:otherwise>
								<input type="hidden" id="password" name = "user.password" value="${customer.user.password}">
							</c:otherwise>
						</c:choose>
						 <sec:authorize access="hasRole('ROLE_ADMIN')">
					 		<tr>
								<td><p class="line"><spring:message code="account.active"></spring:message>:</p></td>
								<td><input type="checkbox" id="active" name="active" <c:if test="${customer.active}">checked="checked"</c:if> > </td>
								<td></td>
							</tr>
						</sec:authorize>
					</table>
				<c:choose>
					<c:when test="${customer.id == 0}">
						<input type="submit" class="name" value='<spring:message code="account.register"></spring:message>'>
					</c:when>
					<c:otherwise>
						<input type="submit" class="name"  value='<spring:message code="account.edit"></spring:message>'>
						<input type="button" class="name" value='<spring:message code="account.changePassword"></spring:message>' onclick="location= '${contextPath}/account_change_password?id=${customer.id}'">
					</c:otherwise>
				</c:choose>
					<img src="/webstore/resources/images/bot_bg.gif" alt="" width="100%" height="10"><br>
				</form:form>
				
				    
				<!-- <input type="submit" class="button"> -->
				
		</div>
	</div>
	<ct:left></ct:left>
	<ct:right></ct:right>
</ct:body>
<ct:footer></ct:footer>