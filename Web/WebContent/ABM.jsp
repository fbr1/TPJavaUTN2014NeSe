<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form>
		<c:set var="elecDom" value="${elecDom}" scope="request"/>
		<ul>
			<li><span>Descripcion: </span><input type="text" name="Descripcion" value="${elecDom.getDescripcion()}"/></li>		
			<li><span>Color: </span><input type="text" name="Color" value="${elecDom.getColor().getNombre()}"></li>
			<li><span>Consumo: </span><input type="text" name="Consumo" value="${elecDom.getConsumoEnergetico().getNombre()}"/></li>
			<li><span>Peso: </span><input type="text" name="Peso" value="${elecDom.getPeso()}"/></li>
			<li><span>Precio Base: </span><input type="text" name="Precio_Base" value="${elecDom.getPrecio_base()}"/></li>
			<li><span>Precio Final: </span><input type="text" name="PrecioFinal" value="${elecDom.getPrecioFinal()}"/></li>
		</ul>
	</form>
</body>
</html>