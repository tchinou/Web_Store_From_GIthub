<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<form:form id="showOrderForm" method="GET" modelAttribute="orderDetails">
			<c:if test="${not empty msg}">
			    <div>
					<strong style="color: red; font-size: 16px">${msg}</strong>
			    </div>
			</c:if>
			<label> <spring:message code="order.label.id">${order.id}</spring:message> </label><br/>
			<input type="hidden" name="orderId" />
				<table id="orderTable" border="1" class="table table-striped" >
				  <tr>
				    <th> <spring:message code="order.id"></spring:message></th>
				    <th> <spring:message code="orderdetails.product.name"></spring:message></th> 
				    <th> <spring:message code="orderdetails.product.quantity"></spring:message></th>
				    <th> <spring:message code="orderdetails.product.price"></spring:message></th>
				    <th> <spring:message code="orderdetails.product.currency"></spring:message></th>
				 </tr>
				 <c:forEach var="detail" varStatus="status" items="${orderDetails}">
				  <tr>
				    <td>${detail.order.id}</td> 
				    <td>${detail.product.name}</td> 
				    <td>${detail.quantity}</td>
				    <td>${detail.price}</td>
				    <td>${detail.currency.name}</td>
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

