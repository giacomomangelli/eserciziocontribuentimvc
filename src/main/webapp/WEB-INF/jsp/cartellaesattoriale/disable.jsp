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
		        Disabilita cartella
		    </div>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${disable_cartella_attr.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9">${disable_cartella_attr.descrizione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Importo:</dt>
				  <dd class="col-sm-9">${disable_cartella_attr.importo}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9">${disable_cartella_attr.statoCartella}</dd>
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
						  <dd class="col-sm-9">${disable_cartella_attr.contribuente.nome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Cognome:</dt>
						  <dd class="col-sm-9">${disable_cartella_attr.contribuente.cognome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
						  <dd class="col-sm-9">${disable_cartella_attr.contribuente.codiceFiscale}</dd>
					   	</dl>
						<dl class="row">
						  <dt class="col-sm-3 text-right">Data di Nascita:</dt>
						  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${disable_cartella_attr.contribuente.dataDiNascita}" /></dd>
				    	</dl>

				  </div>
				</div>
				<!-- end info Contribuente -->
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath }/cartellaesattoriale/" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
   		    	<button type="submit" name="submit" value="submit" id="submit_${disable_cartella_attr.id }" class="btn btn-outline-danger link-for-modal float-right"  data-toggle="modal" data-target="#confirmOperationModal">Disable</button>
		    </div>
		  </div>
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
	<div class="modal fade" id="confirmOperationModal" tabindex="-1" role="dialog" aria-labelledby="confirmOperationModalLabel" aria-hidden="true">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Operazione</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                Continuare con la disabilitazione della Cartella Esattoriale?
	            </div>
	            <form:form method="post" action = "remove" modelAttribute="disable_cartella_attr">
		            <div class="modal-footer">
		        		<input type = "hidden" name = "id" id= "idCartellaForDisable" value = "${disable_cartella_attr.id }"/>
		                <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Disabilita"  class="btn btn-danger">
		            </div>
	            </form:form>
	        </div>
	    </div>
	</div>
	
	<script type="text/javascript">
		$(".link-for-modal").click(function(){
			var callerId = $(this).attr('id').substring(7);
			$('#idCartellaForDisable').val(callerId);
		});
	</script>
	
</body>
</html>