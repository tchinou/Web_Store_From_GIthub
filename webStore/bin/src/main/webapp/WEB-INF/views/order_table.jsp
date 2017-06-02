<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<form:form id="editProductForm" method="GET" modelAttribute="orders" action="${contextPath}/performOrderSearch">
			<c:if test="${not empty msg}">
			    <div>
					<strong style="color: red; font-size: 16px">${msg}</strong>
			    </div>
			</c:if>
			<label> <spring:message code="product.searchBy"></spring:message> </label><br/>
					<input type="radio" name="criteriaGroup" value="customer.name" checked="checked"> <spring:message code="order.criteria.customer.name"></spring:message>
					<input type="radio" name="criteriaGroup" value="product.name" checked="checked"> <spring:message code="order.criteria.product.name"></spring:message>
					<input type="radio" name="criteriaGroup" value="totalPrice"><spring:message code="order.criteria.totalPrice"></spring:message>
					<input type="radio" name="criteriaGroup" value="totalQuantity"><spring:message code="order.criteria.totalQuantity"></spring:message>
					<input type="radio" name="criteriaGroup" value="purchaseDate"><spring:message code="order.criteria.purchaseDate"></spring:message>
					<select name="operation">
						<option value="LIKE">=</option>
						<option value=">">></option>
						<option value="<"><</option>
					</select><input type="text" name="criteriaValue">
					
			<input type="submit" value="<spring:message code="order.searchButton"></spring:message>">	
    		
			<input type="hidden" name="orderId" />
			<input type="hidden" name="rowIndex" />
				<table id="orderTable" border="1" class="table table-striped" >
				  <tr>
				    <th> <spring:message code="order.id"></spring:message></th>
				    <th> <spring:message code="order.customer.name"></spring:message></th> 
				    <th> <spring:message code="order.ordertype.name"></spring:message></th>
				    <th> <spring:message code="order.totalPrice"></spring:message></th>
				    <th> <spring:message code="order.totalQuantity"></spring:message></th>
				    <th> <spring:message code="order.purchaseDate"></spring:message></th>
				    <th> <spring:message code="order.comment"></spring:message></th>
				    <th> <spring:message code="order.view"></spring:message></th>
				    <th> <spring:message code="order.refund"></spring:message></th>
				 </tr>
				 <c:forEach var="order" varStatus="status" items="${orders}">
				  <tr>
				    <td>${order.id}</td> 
				    <td>${order.customer.name}</td> 
				    <td>${order.orderType.name}</td>
				    <td>${order.totalPrice}</td>
				    <td>${order.totalQuantity}</td>
				    <td>${order.purchaseDate}</td>
				    <td>${order.comment}</td>
				    <td><a href="order_view?id=${order.id}" class="name"><spring:message code="order.view"></spring:message> </a></td>
				    <td><a href="order_refund?id=${order.id}" class="name"><spring:message code="order.refund"></spring:message> </a></td>
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

