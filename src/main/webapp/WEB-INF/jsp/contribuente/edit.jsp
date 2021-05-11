<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica contribuente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style>
	    .error_field {
	        color: red; 
	    }
	</style>
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<spring:hasBindErrors  name="update_contribuente_attr">
			<%-- alert errori --%>
			<div class="alert alert-danger " role="alert">
				Attenzione!! Sono presenti errori di validazione
			</div>
		</spring:hasBindErrors>
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica elemento</h5> 
		    </div>
		    <div class='card-body'>
		    
		    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form:form modelAttribute="update_contribuente_attr" method="post" action="modify" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${update_contribuente_attr.nome }" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger">*</span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${update_contribuente_attr.cognome }" required>
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Codice Fiscale <span class="text-danger">*</span></label>
								<input type="text" class="form-control" name="codiceFiscale" id="codiceFiscale" placeholder="Inserire il codice fiscale" value="${update_contribuente_attr.codiceFiscale }" required>
							</div>
							
							<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_contribuente_attr.dataDiNascita}' />
							<div class="form-group col-md-6">
								<label>Data di Nascita <span class="text-danger">*</span></label>
                        		<input class="form-control" id="dataDiNascita" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataDiNascita" required 
                            		value="${parsedDate}" >
							</div>
						</div>
							<div class="form-row">	
								<div class="form-group col-md-6">
									<label>Indirizzo <span class="text-danger">*</span></label>
									<input type="text" class="form-control" name="indirizzo" id="indirizzo" placeholder="Inserire l'indirizzo" value="${update_contribuente_attr.indirizzo }" required>
								</div>
							</div>
						<a href="${pageContext.request.contextPath }/contribuente/" class='btn btn-outline-secondary' style='width:80px'>
		            		<i class='fa fa-chevron-left'></i> Back
		        		</a>
		        		<input type = "hidden" name = "id" value = "${update_contribuente_attr.id }" />
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-primary">Conferma</button>

					</form:form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>