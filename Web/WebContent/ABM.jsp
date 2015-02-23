<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/abm.css">
<title>ABM</title>
</head>
<body>
	<c:if test="${not empty error}" >
			<div id="error">
				${error}
			</div>
	</c:if>
	<form method="get" id="formAbm">
		<c:set var="elecDom" value="${elecDom}" scope="request"/>
		<ul>
			<li><span>Descripcion: </span><input type="text" name="Descripcion" value="${elecDom.getDescripcion()}"/></li>				
			<li>
				<select name="selectcolor">
		          <c:forEach var="color" items="${colores}">
		            <option value="${color.getId()}" ${color.getId() == selectedColor ? 'selected="selected"' : ''} >${color.nombre}</option>
		          </c:forEach>
		        </select>
			</li>
			<li>
				<select name="selectconsumo">
		          <c:forEach var="consumo" items="${consumos}">
		            <option value="${consumo.getId()}" ${consumo.getId() == selectedConsumo ? 'selected="selected"' : ''} >${consumo.nombre}</option>
		          </c:forEach>
		        </select>
			</li>
			<li><span>Peso: </span><input type="text" name="Peso" value="${elecDom.getPeso()}"/></li>
			<li><span>Precio Base: </span><input type="text" name="Precio_Base" value="${elecDom.getPrecio_base()}"/></li>			
			<c:choose>
			      <c:when test="${action == 'Nuevo'}">
			            <li><span> Tipo </span> 
						    <select id="selecttipo" name="selecttipo">
							    <option value="1">Lavarropas</option>
							    <option value="2">Television</option>			  	    
						    </select>
						</li>
						<li class="lavarropas"><span>Carga: </span><input type="text" name="Carga" value=""/></li>
						<li class="television"><span>Resolucion: </span><input type="text" name="Resolucion" value=""/></li>
						<li class="television"><span>Sintonizador </span><input type="checkbox" name="Sintonizador" value=""/></li>	
			      </c:when>
			      <c:otherwise>	
			      		<c:choose>		            
							<c:when test="${elecDom['class'] == 'class Entities.Television'}">	
								<li><span> Tipo </span> 
								    <select disabled id="selecttipo" >
									    <option value="1">Lavarropas</option>
									    <option selected="selected" value="2">Television</option>			  	    
								    </select>
								    <input type="hidden" name="selecttipo" value="2"/>	
								</li>
								<li class="television"><span>Resolucion: </span><input type="text" name="Resolucion" value="${elecDom.getResolucion()}"/></li>
								<li class="television"><span>Sintonizador </span><input type="checkbox" name="Sintonizador"  ${elecDom.tieneSinTDT() == true ? 'checked' : ''} /></li>	
							</c:when>
							<c:otherwise>
								<li><span> Tipo </span> 
								    <select disabled id="selecttipo" >
									    <option selected="selected"value="1">Lavarropas</option>
									    <option value="2">Television</option>			  	    
								    </select>
								    <input type="hidden" name="selecttipo" value="1"/>	
								</li>
								<li class="lavarropas"><span>Carga: </span><input type="text" name="Carga" value="${elecDom.getCarga()}"/></li>
							</c:otherwise>
						</c:choose>
			      </c:otherwise>
			</c:choose>												
		</ul>			
		<input type="hidden" name="id" value="${elecDom.getId()}"/>	
		<input type="submit" value="${action}" name="confirmacion"/>
		<input type="submit" value="Cancelar" name="confirmacion" />	
	</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	$(function(){	
		checkSelectedItem()	
		$("#selecttipo").change(function() {			
			checkSelectedItem()	
		})
		function checkSelectedItem() {
		$("#selecttipo").each(function() {			
			if($(this).val()==2){
				$(".television").show();
				$(".lavarropas").hide()
			}else if($(this).val() == 1){
				$(".television").hide()
				$(".lavarropas").show()
			}		
		})		   
	}
	});	
</script>
</html>