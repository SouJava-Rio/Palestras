<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MVC 1.0 DEMO</title>
</head>

<body>

<div class="container">
    <c:if test="${errors != null}">
    <div class="row">
        <div class="col-md-12">
            <p class="alert alert-danger">${errors}</p>
        </div>
    </div>
    </c:if>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form action="../update" method="post">
                <h2>Alterar dados da JUG</h2>
                <input type="text" name="id" value="${update.id }" hidden="true">
                
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input id="nome" name="nome" class="form-control" value="${update.nome}"autofocus>
                </div>
                <div class="form-group">
                    <label for="leader">Leader:</label>
                    <input type="text" id="leader" name="leader" class="form-control" value="${update.leader}">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" class="form-control" value="${update.email}">
                </div>
                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <input type="text" id="estado" name="estado" class="form-control" value="${update.localidade.estado}">
                </div>
                <div class="form-group">
                    <label for="pais">País:</label>
                    <select id="pais" name="pais" class="form-control">
				   			<option value="${update.localidade.pais}">${update.localidade.pais}</option>
					   			<c:forEach var="pais" items="${paises}">
					   					<option>${pais}</option>
					   			</c:forEach>
				</select> 
                </div>
                <div class="form-group">
                    <label for="descricao">Descricao:</label>
                    <textarea type="" id="descricao" name="descricao" class="form-control">${update.descricao}</textarea>
                </div>
                <button class="btn btn-primary btn-block" type="submit">Alterar</button>
            </form>
        </div>
    </div>
</div>
<br />
<br />
<jsp:include page="/templates/rodape.jsp"></jsp:include>
</body>
</html>
