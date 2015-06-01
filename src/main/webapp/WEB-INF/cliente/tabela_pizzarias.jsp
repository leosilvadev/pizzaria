<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<td style="width: 30%">Nome</td>
			<td style="width: 40%">Endereço</td>
			<td style="width: 20%">Atendendo desde</td>
			<td style="width: 10%">Entre em contato</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pizzarias}" var="pizzaria">
			<tr data-id="${pizzaria.id}">
				<td>${pizzaria.nome}</td>
				<td>${pizzaria.endereco}</td>
				<td><fmt:formatDate value="${pizzaria.dataCadastro.time}" pattern="dd/MM/yyyy"/></td>
				<td><a href="#">Contato</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">Pizzaria encontradas: <span id="quantidade-pizzaria">${pizzarias.size()}</span></td>
		</tr>
	</tfoot>
</table>