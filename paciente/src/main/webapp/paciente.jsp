<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Locale" %>
<%
	@ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("pacientes");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de pacientes</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Pacientes</h1>
	<a href="novo.html" class="Botao1">Novo Paciente</a>
	<a href="report" class="Botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Idade</th>
				<th>Peso (kg)</th>
				<th>Altura (m)</th>
				<th>IMC</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
				Locale.setDefault(Locale.US);
				double peso = lista.get(i).getPeso();
		        double altura = lista.get(i).getAltura();

		        double imc = peso / (altura * altura);
			%>
			<tr>
				<td><%=lista.get(i).getIdcon()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getIdade()%></td>
				<td><%=lista.get(i).getPeso()%></td>
				<td><%=lista.get(i).getAltura()%></td>
				<td><%= String.format("%.2f", imc) %></td>
				<td>
					<a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="Botao1">Editar</a> 
					<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="Botao2">Excluir</a>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>