<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>


<table>
	<tr>
	    <td><h3>Add New Product</h3></td>
	    <td>
	        </td>
	    </tr>
</table>
<div class="container">
	<springForm:form modelAttribute="product" class="form-horizontal" action="addit" enctype="multipart/form-data">
	<springForm:errors path="*" cssClass="alert alert-danger" element="div" />
	<fieldset>
		<legend>Product details</legend>
		<table>
			<tr>
				<td style="width:130px">Product Name:</td>
				<td><springForm:input id="name" path="name" type="text" /></td>
				<td><springForm:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><springForm:input id="description" path="description" type="text" /></td>
				<td><springForm:errors path="description" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><springForm:input id="price" path="price" type="text" /></td>
				<td><springForm:errors path="description" cssClass="error" /></td>
			</tr>

			
			<tr>
				<td>Category:</td>
				<td>	
				<form:select multiple="single" path="category.id" id="category.id" cssClass="dropDownSelect-small" tabindex="3" onchange="changeState()">
    				<form:options items="${categories}" itemValue="id" itemLabel="name" />
				</form:select>
				</td>
			</tr>

			
			<tr>
				<td>Image:</td>
				<td><springForm:input id="productImage" path="productImage" type="file" /></td>
				<td><springForm:errors path="productImage" cssClass="error" /></td>
			</tr>
		</table>
	</fieldset>
	<div class="form-group" style="padding-top: 20px;">
         <div style="display:inline-block; padding-right: 10px;">
             <input type="submit" id="btnAdd" class="btn btn-primary"
		value="Register" /> 
         </div>
     </div>
	</springForm:form>
	
</div>
