<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza Contribuente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		<div class='card'>
		    <div class='card-header'>
		        Visualizza contribuente
		    </div>
			<form:form modelAttribute="visualizza_contribuente_attr" method="post" action="remove" novalidate="novalidate" >	
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${visualizza_contribuente_attr.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${visualizza_contribuente_attr.nome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${visualizza_contribuente_attr.cognome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
				  <dd class="col-sm-9">${visualizza_contribuente_attr.codiceFiscale}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data Di Nascita:</dt>
				  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${visualizza_contribuente_attr.dataDiNascita}" /></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Indirizzo</dt>
				  <dd class="col-sm-9">${visualizza_contribuente_attr.indirizzo}</dd>
		    	</dl>
		    	<p>
                <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Info Importi
                </a>
                
	            </p>
	            <div class="collapse" id="collapseExample">
	                <div class="card card-body">
	
	                    <dl class="row">
	                        <dt class="col-sm-3 text-right">Importo Totale:</dt>
	                        <dd class="col-sm-9">${show_importo_totale}</dd>
	                    </dl>
	
	                    <dl class="row">
	                        <dt class="col-sm-3 text-right">Importo Totale Cartelle Concluse:</dt>
	                        <dd class="col-sm-9">${show_importo_concluso}</dd>
	                    </dl>
	
	                    <c:if test="${show_totale_contenzioso != 0}">
	                        <dl class="row">
	                            <dt class="col-sm-3 text-right" style="color:${show_totale_contenzioso == 0 ? '' : 'red' };">Totale Cartelle in Contenzioso:</dt>
	                            <dd class="col-sm-9">${show_totale_contenzioso}</dd>
	                        </dl>
	                    </c:if>
	
	                </div>
	            </div>
	            <br>
		    	
		    	<!-- info Film -->
		    	<p>
				  <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
				    Elenco Cartelle Esattoriali:
				  </a>
				</p>
				<c:forEach var = "cartellaItem" items = "${visualizza_contribuente_attr.cartelleEsattoriali }">
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
						  <dt class="col-sm-3 text-right">Descrizione:</dt>
						  <dd class="col-sm-9">${cartellaItem.descrizione}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Importo:</dt>
						  <dd class="col-sm-9">${cartellaItem.importo}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Stato:</dt>
						  <dd class="col-sm-9">${cartellaItem.statoCartella}</dd>
					   	</dl>
					  </div>
					</div>
				</c:forEach>
				<!-- end info Regista -->
			</div>
			
			<div class='card-footer'>
		        <a href="${pageContext.request.contextPath }/contribuente/" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
        		<input type = "hidden" name = "id" value = "${visualizza_contribuente_attr.id }" />
		    	<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-danger float-right">Delete</button>
			</div>
			</form:form>
		</div>
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>