<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<form:form id="editProductForm" method="POST" modelAttribute="product" action="${contextPath}/do_product">
			<c:if test="${not empty msg}">
			    <div>
					<strong style="color: red; font-size: 16px">${msg}</strong>
			    </div>
			</c:if>
			<input type="hidden" name="id" value="${product.id}"/>
			<input type="hidden" name="pictureName" value="${product.pictureName}"/>
				<table id="cartTable" style="width: 95%" frame="box">
				  <tr>
				    <td> <spring:message code="product.name"></spring:message></td>
				    <td><input type="text" name = "name" value="${product.name}" style="width: 100%"></td>
				    <td><font color="red"><form:errors path="name"></form:errors></font><br/></td>
				  </tr>
				  <tr>
				    <td> <spring:message code="product.picture"></spring:message></td>
					<td><img alt="" id="pictureName" name="pictureName" class="image_small" src="${images}/${product.pictureName}"/></td>
				</tr>
				  <tr>
				  	  <td> <spring:message code="product.description"></spring:message></td>
				  	  <td><input type="text" name = "description" value="${product.description}" style="width: 100%"></td>
				  	  <td><font color="red"><form:errors path="description"></form:errors></font><br/></td> 
				  <tr>
				  <tr>
				  	  <td> <spring:message code="product.type"></spring:message></td>
 			  	   	  <td> <select id="type" name="type.id">
 			  	   	  		<c:forEach items="${product.types }" var="type" varStatus="status">
 			  	   	  			<option value="${type.id }"  <c:if test="${type.id == product.type.id})"> selected</c:if>>${type.name }</option>
 			  	   	  		</c:forEach>
				  		</select>
				  	</td>
				  <tr>
				  <tr>
				  	<td> <spring:message code="product.quantity"></spring:message></td>
				  	<td><input type="number" name = "quantity" value="${product.quantity}" style="width: 100%" min="0"></td>
				  	<td><font color="red"><form:errors path="quantity"></form:errors></font><br/></td>
				  </tr>
				  <tr>
				  	<td> <spring:message code="product.price"></spring:message></td>
				  	<td><input type="number" name = "singlePrice" step="any" value="${product.singlePrice}" style="width: 100%" min="0" ></td>
				  	<td><font color="red"><form:errors path="singlePrice"></form:errors></font><br/></td>
				  <tr>
				  	<td> <spring:message code="product.currency"></spring:message></td>
				  	<td> <select id="currency" name="currency.id">
				  			<option value="${product.currency.id }">${product.currency.name }</option>
				  		</select>
				  	</td>
				  </tr>
				 <tr>
				    <td> <spring:message code="product.active"></spring:message></td>
				    <td><input type="checkbox" id="active"  name="active" <c:if test="${product.active}">checked="checked"</c:if>></td>
				 </tr>
				</table>
				<c:choose>
					<c:when test="${product.id==0}">
						<input type="submit" value='<spring:message code="product.create"></spring:message>'>
					</c:when>
					<c:otherwise>
						<input type="submit" value='<spring:message code="product.edit"></spring:message>'>
					</c:otherwise>
				</c:choose>
				
				
				<br>
		</form:form>
	</div>
	<ct:left></ct:left>
	<ct:right></ct:right>
</ct:body>
<ct:footer></ct:footer>

