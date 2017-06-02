<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<form:form method="GET" action="${contextPath }/performProductSearchAdmin">
			<c:if test="${not empty msg}">
			    <div>
					<strong style="color: red; font-size: 16px">${msg}</strong>
			    </div>
			</c:if>
			<label> <spring:message code="product.searchBy"></spring:message> </label><br/>
			<input type="radio" name="criteriaGroup" value="name" checked="checked"> <spring:message code="product.criteria.name"></spring:message>
			<input type="radio" name="criteriaGroup" value="type.name"><spring:message code="product.criteria.type"></spring:message>
			<input type="radio" name="criteriaGroup" value="quantity"><spring:message code="product.criteria.quantity"></spring:message>
			<input type="radio" name="criteriaGroup" value="singlePrice"><spring:message code="product.criteria.price"></spring:message>
			<input type="radio" name="criteriaGroup" value="active"><spring:message code="product.criteria.active"></spring:message>
			<select name="operation">
				<option value="LIKE">=</option>
				<option value=">">></option>
				<option value="<"><</option>
			</select><input type="text" name="criteriaValue">
			
			<input type="submit" value="<spring:message code="product.searchButton"></spring:message>">
		</form:form>
		
		<form:form id="editProductForm" method="POST" modelAttribute="products" action="${contextPath}/product_update">
			 <sec:authorize access="hasRole('ROLE_ADMIN')">
				 <div style="margin-bottom: 20px">
					<ul>
					  <li style="display: inline"><a href="${contextPath}/product_create" style="font-size: 16px"> <spring:message code="menu.admin.products.createProduct"></spring:message> </a></li>
					</ul>
				</div>
			</sec:authorize>
			<input type="hidden" name="productId" />
			<input type="hidden" name="rowIndex" />
				<table id="cartTable" border="1" class="table table-striped" >
				  <tr>
				    <th> <spring:message code="product.name"></spring:message></th>
				    <th> <spring:message code="product.description"></spring:message></th> 
				    <th> <spring:message code="product.quantity"></spring:message></th>
				    <th> <spring:message code="product.price"></spring:message></th>
				    <th> <spring:message code="product.active"></spring:message></th>
				    <th> <spring:message code="product.edit"></spring:message></th>
				    <th> <spring:message code="product.delete"></spring:message></th>
				 </tr>
				 <c:forEach var="product" varStatus="status" items="${products}">
				  <tr>
				    <%-- <td> <input type="text" name="name" value="${product.name }"></td>
				    <td> <input type="text" name="description" value="${product.description}"></td> 
				    <td> <input type="text" name="quantity" value="${product.quantity}"></td>
				    <td> <input type="text" name="singlePrice" value="${product.singlePrice}"> </td> --%>
				    <td>${fn:substring(product.name, 0,25)}</td>
				    <td>${fn:substring(product.description, 0,50)}</td> 
				    <td>${product.quantity}</td>
				    <td>${product.singlePrice}</td>
				    <td><input type="checkbox" disabled="disabled" <c:if test="${product.active}">checked="checked"</c:if> name = "active"></td>
				    <td><a href="product_edit?id=${product.id}" class="name"><spring:message code="product.edit"></spring:message> </a></td>
				    <td><a href="product_delete?id=${product.id}" class="name"><spring:message code="product.delete"></spring:message> </a></td>
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

