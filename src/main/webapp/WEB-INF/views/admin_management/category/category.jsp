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


<div class="container-fluid" style="padding: 0">
	<div class="row-fluid">
		<div class="span2">
			<!--Sidebar content-->
			<ul class="nav nav-list">
				<li class="nav-header">Administrative</li>
				<li><a href="/eshopping/admin/vendor">Manage vendors</a></li>
				<li class="active"><a href="#">Manage category</a></li>
                                <li><a href="/eshopping/admin/product">Manage Product</a></li>
				<li><a href="/eshopping/admin/system/viewHistory">Generate report</a></li>
			</ul>
		</div>
		<div class="span10">
			<!--Body content-->
			<div>
                                <legend>Category List</legend>
				
                               
                                <div style="padding-left: 20px; padding-top: 10px;">
					<form action="/eshopping/admin/category/add">
						<input type="submit" id="btnAdd" class="btn btn-mini btn-success" value="Add Category" />
					</form>
				</div>
				
				<table id="example" class="table table-hove" cellspacing="0" width="75%">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>


					<tbody>
						<c:forEach items="${categories}" var="category">
							<tr>
								<td>${category.name}</td>
								<td>${category.description}</td>
								<td>
									<button
										onclick="location.href = '/eshopping/admin/category/edit?pid=${category.id}';"
										id="btnEdit" class="btn btn-mini">Edit</button>
									<button
										onclick="location.href = '/eshopping/admin/category/delete?pid=${category.id}';"
										id="btnDelete" class="btn btn-mini btn-danger">Delete</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>
</div>


