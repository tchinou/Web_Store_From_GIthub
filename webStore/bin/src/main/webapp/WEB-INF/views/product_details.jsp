<%@include file="master.jsp"%>

<ct:header></ct:header>
<div id="container">
		<form:form method="POST" action="${contextPath}/addToCart" modelAttribute="product">
			<div id="center" class="column">
		  	<div id="content">
				<div id="about">
					<p class="tree"><a href="#">${product.productTypesList[0] }</a>  &gt;  <a href="#">${product.productTypesList[1]}</a>  &gt;  <a href="#">${product.productTypesList[2]} </a>  &gt; ${product.name }</p>
					<div class="photos">
						<img src="${images}/${product.pictureName}" class="image_big" alt="" width="227" height="215"><br>
						<a href="#" class="moreph">more photos</a>
						<a href="#" class="comments">View Comments (27)</a>
					</div>
					<div class="description">																																																																																																																																																			
						<p><u>${fn:substring(product.name, 0,25)}</u> <span class="price">${product.singlePrice } ${product.currency.name }</span></p>
						<p>${product.description}</p>
						<p><strong>Short features:</strong></p>
						<ul id="features">
							<c:forEach var="desc" varStatus="status"  items="${fn:split(product.description, ',')}">
								<li><span>${desc }</span></li>
							</c:forEach>
						</ul>
						<input type="submit" value="Add to Cart" onclick="location= '${contextPath}/cart">
						<input type="hidden" name="productId" value="${product.id}">
					</div>
				</div>
				<img src="${images}/title6.gif" alt="" width="537" height="23" class="pad25">
				<div class="stuff">
					<div class="item">
						<img src="${images}/pic1.jpg" alt="" width="124" height="90">
						<a href="index2.html" class="name">Name Product</a>
						<span>$250</span>
						<a href="#"><img src="${images}/zoom.gif" alt="" width="53" height="19"></a><a href="#"><img src="${images}/cart.gif" alt="" width="71" height="19"></a>
					</div>
					<div class="item">
						<img src="${images}/pic2.jpg" alt="" width="124" height="111">
						<a href="index2.html" class="name">Name Product</a>
						<span>$850</span>
						<a href="#"><img src="${images}/zoom.gif" alt="" width="53" height="19"></a><a href="#"><img src="${images}/cart.gif" alt="" width="71" height="19"></a>
					</div>
					<div class="item">
						<img src="${images}/pic3.jpg" alt="" width="124" height="89">
						<a href="index2.html" class="name">Name Product</a>
						<span>$400</span>
						<a href="#"><img src="${images}/zoom.gif" alt="" width="53" height="19"></a><a href="#"><img src="${images}/cart.gif" alt="" width="71" height="19"></a>
					</div>
					<div class="item">
						<img src="${images}/pic4.jpg" alt="" width="124" height="89">
						<a href="index2.html" class="name">Name Product</a>
						<span>$350</span>
						<a href="#"><img src="${images}/zoom.gif" alt="" width="53" height="19"></a><a href="#"><img src="${images}/cart.gif" alt="" width="71" height="19"></a>
					</div>
					<div class="item">
						<img src="${images}/pic5.jpg" alt="" width="124" height="97">
						<a href="index2.html" class="name">Name Product</a>
						<span>$250</span>
						<a href="#"><img src="${images}/zoom.gif" alt="" width="53" height="19"></a><a href="#"><img src="${images}/cart.gif" alt="" width="71" height="19"></a>
					</div>
					<div class="item">
						<img src="${images}/pic6.jpg" alt="" width="124" height="111">
						<a href="index2.html" class="name">Name Product</a>
						<span>$2250</span>
						<a href="#"><img src="${images}/zoom.gif" alt="" width="53" height="19"></a><a href="#"><img src="${images}/cart.gif" alt="" width="71" height="19"></a>
					</div>
				</div>
			</div>
	  </div>
	</form:form>
	<ct:left></ct:left>
	<ct:right></ct:right>
</div>
<ct:footer></ct:footer>