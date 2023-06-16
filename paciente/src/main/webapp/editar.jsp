<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de pacientes</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar paciente</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly
					value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="idade" class="Caixa2"
					value="<%out.print(request.getAttribute("idade"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="peso" class="Caixa1"
					value="<%out.print(request.getAttribute("peso"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="altura" class="Caixa1"
					value="<%out.print(request.getAttribute("altura"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>