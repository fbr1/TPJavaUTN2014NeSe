<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/listado.css">
<title>Electrodomesticos</title>
</head>
<body>
	<div>
		<c:if test="${not empty error}" >
			<div id="error">
				${error}
			</div>
		</c:if>		
		<c:if test="${not empty elecDoms}" >
			<form action="/Web/ABM" name="form" id="form">
				<table>
					<tr>
						<th></th>
						<th>ID</th>
						<th>Descripcion</th>
						<th>Color</th>
						<th>Consumo Energetico</th>
						<th>Peso</th>
						<th>Precio Base</th>
						<th>Carga</th>
						<th>Resolucion</th>
						<th>TDT</th>
						<th>Precio Final</th>
					</tr>
					<c:forEach var="elecDom" items="${elecDoms}">
						<tr>
							<td><input type="checkbox" name="id" class='checkboxes' value="<c:out value="${elecDom.getId()}"/>" ></input></td>
							<td>${elecDom.getId()}</td>
							<td>${elecDom.getDescripcion()}</td>
							<td>${elecDom.getColor().getNombre()}</td>
							<td>${elecDom.getConsumoEnergetico().getNombre()}</td>
							<td>${elecDom.getPeso()}</td>
							<td>${elecDom.getPrecio_base()}</td>
							<c:choose>
							      <c:when test="${elecDom['class'] == 'class Entities.Lavarropas'}">
							            <td>${elecDom.getCarga()}</td>
							      </c:when>
							      <c:otherwise>				           
										<td>-</td>
							      </c:otherwise>
							</c:choose>
							<c:choose>
							      <c:when test="${elecDom['class'] == 'class Entities.Television'}">
							            <td>${elecDom.getResolucion()}</td>
										<td>${elecDom.tieneSinTDT()}</td>
							      </c:when>
							      <c:otherwise>
							            <td>-</td>
										<td>-</td>
							      </c:otherwise>
							</c:choose>		
							
							<td>${elecDom.getPrecioFinal()}</td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="Nuevo" name="action"/>
				<input type="submit" value="Modificar" name="action" />
				<input type="submit" value="Eliminar" name="action" />
			</form>
		</c:if>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	$("tr").click(function(){
		clearCheckbox()
  	  	$(this).addClass("selected").siblings().removeClass("selected");
  	  	$(this).find("input").prop('checked', true);  	        
	});
	function clearCheckbox(){
		$(".checkboxes").prop('checked', false);
	}
</script>
</html>