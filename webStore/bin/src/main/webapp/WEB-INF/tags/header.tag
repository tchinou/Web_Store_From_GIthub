<h1>${title}</h1>

<%@include file="../views/taglibs.jsp"%>

<div id="header">
		<a href="${contextPath}/home" class="float"><img src="${images}/logo.jpg" alt="" width="171" height="73"></a>																																																		
	  <div class="topblock1">
			Currency:<br><select><option>Bulgarian Lev</option></select>
		</div>
	    <div class="topblock2">
			Lanquage:<br>
		    <a href="#"><img src="${images}/flag1.gif" alt="" width="19" height="11"></a>																																		
		    <a href="#"><img src="${images}/flag2.gif" alt="" width="19" height="11"></a>
		    <a href="#"><img src="${images}/flag3.gif" alt="" width="19" height="11"></a>
		    <a href="#"><img src="${images}/flag4.gif" alt="" width="19" height="11"></a>
		    <a href="#"><img src="${images}/flag5.gif" alt="" width="19" height="11"></a>
		    <a href="#"><img src="${images}/flag6.gif" alt="" width="19" height="11"></a>
		</div>
		<div class="topblock2">
			<img src="${images}/shopping.gif" alt="" width="24" height="24" class="shopping">																																																																									
		 	<a href="${contextPath}/cart"><spring:message code="cart.name"></spring:message></a> <p><strong> <a href="${contextPath}/cart">${fn:length(sessionScope.order.orderDetails)} items</a></strong></p>
		 	<c:choose>
		 		<c:when test="${empty pageContext.request.userPrincipal }">
		 			<a href="${contextPath}/login"><spring:message code="account.login"></spring:message></a>
		 		</c:when>
		 		<c:otherwise>
		 			<div>
		 				<strong>Hi, ${pageContext.request.userPrincipal.name}</strong>
		 			</div>
		 				<a href="${contextPath}/logout" style="margin-left: 35px"><spring:message code="account.logout"></spring:message></a>
		 			
		 		</c:otherwise>
		 	</c:choose>
		 </div>
		 <sec:authorize access="hasRole('ROLE_ADMIN')">
			 <div>
				<ul>
				  <li style="display: inline"><a href="${contextPath}/product_table" style="font-size: 16px"> <spring:message code="menu.admin.products"></spring:message> </a></li>
				  <li style="display: inline; "><a href="${contextPath}/account_table" style="font-size: 16px"><spring:message code="menu.admin.users"></spring:message> </a></li>
				  <li style="display: inline"><a href="${contextPath}/order_table" style="font-size: 16px"><spring:message code="menu.admin.orders"></spring:message> </a></li>
				</ul>
			</div>
		</sec:authorize>
		<ul id="menu">
			<li><img src="${images}/li.gif" alt="" width="19" height="29"></li>
			<li><a href="${contextPath}/home"><img src="${images}/but1_a.gif" alt="" width="90" height="29"></a></li>
			<li><a href="${contextPath}/performProductSearch?criteriaGroup=type.id&operation=LIKE&criteriaValue=2"><img src="${images}/but2.gif" alt="" width="129" height="29"></a></li>
			<li><a href="${contextPath}/performProductSearch?criteriaGroup=type.id&operation=LIKE&criteriaValue=3"><img src="${images}/but3.gif" alt="" width="127" height="29"></a></li>
			<li><a href="${contextPath }/account"><img src="${images}/but4.gif" alt="" width="113" height="29"></a></li>
			<li><a href="${contextPath }/cart"><img src="${images}/but5.gif" alt="" width="132" height="29"></a></li>
			<li><a href="${contextPath }/maps"><img src="${images}/but6.gif" alt="" width="105" height="29"></a></li>
			<li><a href="${contextPath }/faq"><img src="${images}/but7.gif" alt="" width="82" height="29"></a></li>
			<li><a href="${contextPath }/contact"><img src="${images}/but8.gif" alt="" width="112" height="29"></a></li>
			<li><a href="${contextPath }/rss"><img src="${images}/but9.gif" alt="" width="71" height="29"></a></li>
		</ul>
		
		
	</div>