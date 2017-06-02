<%@include file="master.jsp"%>

	<ct:header></ct:header>
	<ct:body>
	<div id="center" class="column">
				<div class="stuff">
				
				<form:form method="GET" action="${contextPath }/performProductSearch">
					<label> <spring:message code="product.searchBy"></spring:message> </label><br/>
					<input type="radio" name="criteriaGroup" value="name" checked="checked"> <spring:message code="product.criteria.name"></spring:message>
					<input type="radio" name="criteriaGroup" value="type.name"><spring:message code="product.criteria.type"></spring:message>
					<input type="radio" name="criteriaGroup" value="singlePrice"><spring:message code="product.criteria.price"></spring:message>
					<select name="operation">
						<option value="LIKE">=</option>
						<option value=">">></option>
						<option value="<"><</option>
					</select><input type="text" name="criteriaValue">
					
					<input type="submit" value="<spring:message code="product.searchButton"></spring:message>">
				</form:form>
				
				<form:form id="cartForm" method="POST" action="${contextPath}/addToCart"  modelAttribute="products">
					 	<input type="hidden" name="productId" />
					 
					<c:forEach var="product" varStatus="status" items="${products}">
					 	<div class="item">
								<img src="${images}/${product.pictureName }" class="image_small">
								<a href="product_details?id=${product.id}" class="name">${fn:substring(product.name, 0,16)}</a>
								 <span>${product.singlePrice } ${product.currency.name} </span>
							 	<input type="button" id="${product.id }" value="Add to Cart" name="addToCart" onclick="submitter(this)">
							</div>
					</c:forEach>
					<img src="${images}/title6.gif" alt="" width="537" height="23" class="pad25">
					<c:forEach var="product" varStatus="status" items="${products}">
							<c:if test="${status.index < 2 }">
					 		<div class="item">
								<img src="${images}/${product.pictureName }" class="image_small">
								<a href="product_details?id=${product.id}" class="name">${fn:substring(product.name, 0,16)}</a>
								 <span>${product.singlePrice } ${product.currency.name} </span>
							 	<input type="button" id="${product.id }" value="Add to Cart" name="addToCart" onclick="submitter(this)">
							</div>
							</c:if>
					</c:forEach>
				</form:form>
			</div>
			</div>
			<ct:left></ct:left>
			<ct:right></ct:right>
	<!-- </div> -->
	</ct:body>
	<ct:footer></ct:footer>