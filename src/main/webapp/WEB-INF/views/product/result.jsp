<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>

<div id="sidebar" class="span3">
	<ul id="sideManu" class="nav nav-tabs nav-stacked">
		<li class="subMenu open"><a>Category</a>
			<ul>
				<li><a class="active" href="search_all"><i
						class="icon-chevron-right"></i>All</a></li>
				<c:forEach items="${categories}" var="cat" varStatus="loopCounter">

					<li><a class="active"
						href="<c:url value='search_by_cat'>
								<c:param name='id' value='${cat.id }'/>
								</c:url>"><i
							class="icon-chevron-right"></i> <c:out value="${cat.name}" /></a></li>

				</c:forEach>

			</ul></li>
	</ul>
</div>
<div class="span9">
	<ul class="thumbnails">
		<c:forEach items="${products}" var="product" varStatus="loopCounter">
                    
                        <!-- By Tunlaya  -->
                        <c:choose>
                            <c:when test="${product.status=='1'}">
                                <li class="span3">
                                        <div class="thumbnail" style="border: 0 none; box-shadow: none;">
                                                <img
                                                        style="width: 260px; height: 200px;"
                                                        src="/eshopping/admin/vendor/product/pic?pid=${product.id}" />
                                                <div class="caption">
                                                        <h5>
                                                                <c:out value="${product.name}" />
                                                                <!-- by Tunlaya -->
                                                                <c:if test="${product.quantity <= 0}">
                                                                    <b><font color="red">(Sold Out!)</font></b>
                                                                </c:if>
                                                        </h5>
                                                        <p>
                                                                <c:out value="${product.description}" />
                                                        </p>

                                                        <h4 style="text-align: center">
                                                                <!-- by Tunlaya -->
                                                                <c:choose>
                                                                    <c:when test="${product.quantity <= 0}">
                                                                        <a href="#" class="btn" disabled="disabled"> Add to <i class="icon-shopping-cart"></i></a> 
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <a
                                                                            href="<c:url value='/product/addtocart'>
                                                                                        <c:param name='id' value='${product.id }'/>
                                                                                  </c:url>"
                                                                            class="btn"> Add to <i class="icon-shopping-cart"></i></a> 

                                                                    </c:otherwise>
                                                                </c:choose> 
                                                                <a><c:out value="${product.price}" /></a>
                                                        </h4>
                                                </div>
                                        </div>
                                </li>
                            </c:when>
                        </c:choose>
		</c:forEach>
	</ul>

</div>

