<%@tag import="com.softuni.webstore.controller.BaseController"%>
<h1></h1>
<%@include file="../views/taglibs.jsp"%>
<c:set var="types2" value="<%=BaseController.getProductTypes() %>"></c:set> 
<div id="left" class="column">
	  	<div class="block">
		<img src="${images}/title1.gif" alt="" width="168" height="42"><br>
			<ul id="navigation">
				<li><a href="${contextPath}/home"><spring:message code="product.categories.all"/></a></li>
				<form:form method="GET" modelAttribute="types2">
					<c:forEach var="type" varStatus="status" items="${types2}">
						<c:choose>
							<c:when test="${status.index % 2 == 0 }">
								<li class="color"><a href="${contextPath}/performProductSearch?criteriaGroup=type.id&operation=LIKE&criteriaValue=${type.id}">${type.name}</a></li>	
							</c:when>
							<c:otherwise>
								<li><a href="${contextPath}/performProductSearch?criteriaGroup=type.id&operation=LIKE&criteriaValue=${type.id}">${type.name}</a></li>	
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:form>
			</ul>
		</div>
		<a href="#"><img src="${images}/banner1.jpg" alt="" width="172" height="200"></a>
	  </div>
