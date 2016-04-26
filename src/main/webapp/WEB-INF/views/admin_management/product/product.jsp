<%-- 
    Document   : newjsp
    Created on : Apr 12, 2016, 10:37:37 AM
    Author     : Tunlaya
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container-fluid" style="padding: 0">
	<div class="row-fluid">
		<div class="span2">
			<!--Sidebar content-->
                        <ul class="nav nav-list">
                            <li class="nav-header">Administrative</li>
                            <li><a href="/eshopping/admin/vendor">Manage vendors</a></li>
                            <li><a href="/eshopping/admin/category">Manage category</a></li>
                            <li class="active"><a href="#">Manage Product</a></li>
                            <li><a href="/eshopping/admin/system/viewHistory">Generate report</a></li>
                        </ul>
                </div>
		<div class="span10">
			<!--Body content-->
			<div>

				<legend>Products</legend>

				<table class="table table-bordered">
					<tr>
						<th>Product Name</th>
						<th>Details</th>
						<th>Vendor</th>
                                                <th>Category</th>
                                                <th>Price</th>
                                                <th>Status</th>
                                                <th>Action</th>
					</tr>
					<c:forEach var="product" items="${products}">

						<tr>
							<td>${product.name}</td>
							<td>${product.description}</td>
                                                        <td>${product.vendor.vendorName}</td>
                                                        <td>${product.category.name}</td>
                                                        <td>${product.price}</td>
							<td>
                                                            <c:choose>
                                                                <c:when test="${product.status == '0'}">
                                                                    pending 
                                                                </c:when>  
                                                                <c:when test="${product.status == '1'}">
                                                                    <font color="green">approved</font> 
                                                                </c:when>  
                                                                <c:otherwise>
                                                                    <font color="red">rejected</font>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
							<td>
								<button
									onclick="location.href = '/eshopping/admin/product/reject?id=${product.id}';"
									id="disable" class="btn btn-mini btn-danger">Reject</button>
								<button
									onclick="location.href = '/eshopping/admin/product/approve?id=${product.id}';"
									id="activate" class="btn btn-mini btn-success">Approve</button>
						</tr>

					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>



