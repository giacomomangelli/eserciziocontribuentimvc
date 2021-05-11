<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Elimina elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Mostra cartella esattoriale
		    </div>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${visualizza_cartella_attr.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9">${visualizza_cartella_attr.descrizione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Importo:</dt>
				  <dd class="col-sm-9">${visualizza_cartella_attr.importo}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9">${visualizza_cartella_attr.statoCartella}</dd>
				</dl>
		    	
		    	<!-- info Contribuente -->
		    	<p>
				  <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
				    Info Contribuente
				  </a>
				</p>
				<div class="collapse" id="collapseExample">
					
					  <div class="card card-body">
					  	<dl class="row">
						  <dt class="col-sm-3 text-right">Nome:</dt>
						  <dd class="col-sm-9">${visualizza_cartella_attr.contribuente.nome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Cognome:</dt>
						  <dd class="col-sm-9">${visualizza_cartella_attr.contribuente.cognome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
						  <dd class="col-sm-9">${visualizza_cartella_attr.contribuente.codiceFiscale}</dd>
					   	</dl>
						<dl class="row">
						  <dt class="col-sm-3 text-right">Data di Nascita:</dt>
						  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${visualizza_cartella_attr.contribuente.dataDiNascita}" /></dd>
				    	</dl>

				  </div>
				</div>
				<!-- end info Contribuente -->
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath }/cartellaesattoriale/" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		  </div>
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>