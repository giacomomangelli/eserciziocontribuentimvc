<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
	<style>
		.ui-autocomplete-loading {
			background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
		}
		.error_field {
	        color: red; 
	    }
	</style>
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<%-- se l'attributo in request ha errori --%>
		<spring:hasBindErrors  name="cartella_contribuente_attr">
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
		        <h5>Inserisci nuovo elemento</h5> 
		    </div>
		    <div class='card-body'>

					<form:form method="post" modelAttribute="insert_cartella_attr" action="save" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="descrizione">Descrizione:</label>
								<spring:bind path="descrizione">
									<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${insert_cartella_attr.descrizione }">
								</spring:bind>
								<form:errors  path="descrizione" cssClass="error_field" />
							</div>
							
							<div class="form-group col-md-6">
								<label for="importo">Importo:</label>
								<spring:bind path="importo">
									<input type="number" name="importo" id="importo" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire l'importo" value="${insert_cartella_attr.importo }">
								</spring:bind>
								<form:errors  path="importo" cssClass="error_field" />
							</div>
						</div>
												
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="contribuenteSearchInput">Contribuente:</label>
								<spring:bind path="contribuente.id">
									<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="contribuenteSearchInput"
										name="contribuenteId" value="${insert_cartella_attr.contribuente.nome}${empty insert_cartella_attr.contribuente.nome?'':' '}${insert_cartella_attr.contribuente.cognome}">
								</spring:bind>
								<input type="hidden" name="contribuente.id" id="contribuenteId" value="${insert_cartella_attr.contribuente.id }">
								<form:errors  path="contribuente.id" cssClass="error_field" />
							</div>
						</div>
						<a href="${pageContext.request.contextPath }/cartellaesattoriale/" class='btn btn-outline-secondary' style='width:80px'>
		           			 <i class='fa fa-chevron-left'></i> Back
		        		</a>
						<input type = "hidden" name = "statoCartella" id = "statoCartella" value = "${insert_cartella_attr.statoCartella }" />
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-primary">Conferma</button>
						
					</form:form>
					
					<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
					<script>
						$("#contribuenteSearchInput").autocomplete({
							 source: function(request, response) {
							        $.ajax({
							            url: "../contribuente/searchContribuentiAjax",
							            datatype: "json",
							            data: {
							                term: request.term,   
							            },
							            success: function(data) {
							                response($.map(data, function(item) {
							                    return {
								                    label: item.label,
								                    value: item.value
							                    }
							                }))
							            }
							        })
							    },
							//quando seleziono la voce nel campo deve valorizzarsi la descrizione
						    focus: function(event, ui) {
						        $("#contribuenteSearchInput").val(ui.item.label)
						        return false
						    },
						    minLength: 2,
						    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
						    select: function( event, ui ) {
						    	$('#contribuenteId').val(ui.item.value);
						    	//console.log($('#registaId').val())
						        return false;
						    }
						});
					</script>
					<!-- end script autocomplete -->	
					
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>