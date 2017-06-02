<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<div class="content">
				<img src="/webstore/resources/images/top_bg.gif" alt="" width="100%" height="12">
				<form:form id="userForm" method="POST" action="${contextPath}/do_account_change_password"  modelAttribute="customer">
					<input type="hidden" id="id" name="id" value="${customer.id}">
					<input type="hidden" id="password" name="customer.user.password" value="${customer.user.password}">
					<input type="hidden" id="retypePassword" name="customer.user.retypePassword" value="${customer.user.password}">
					
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
							<td><p class="line"><spring:message code="account.password"></spring:message>:</p></td>
							<td><input type="password" id="password" name = "user.password" value="${customer.user.password}"></td>
							<td><font color="red"><form:errors path="user.password"></form:errors></font><br/></td>
						</tr>
						<tr>
							<td><p class="line"><spring:message code="account.retypePassword"></spring:message>:</p></td>
							<td><input type="password" id="retypePassword"  name="user.retypePassword" value="${customer.user.retypePassword}"></td>
							<td><font color="red"><form:errors path="user.password"></form:errors></font><br/></td>
						</tr>
					</table>
					<input type="submit" class="name" value='<spring:message code="account.changePassword"></spring:message>'>
					<img src="/webstore/resources/images/bot_bg.gif" alt="" width="100%" height="10"><br>
				</form:form>
				
				    
				<!-- <input type="submit" class="button"> -->
				
		</div>
	</div>
	<ct:left></ct:left>
	<ct:right></ct:right>
</ct:body>
<ct:footer></ct:footer>