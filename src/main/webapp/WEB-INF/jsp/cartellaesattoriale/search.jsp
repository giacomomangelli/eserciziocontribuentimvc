<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
	
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
	
		<div class="alert alert-warning alert-dismissible fade show d-none" role="alert">
		  Attenzione!!! Funzionalità ancora non implementata. Sulla 'Conferma' per il momento parte il 'listAll'
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Ricerca elementi</h5> 
		    </div>
		    <div class='card-body'>

					<form:form method="post" action="list" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Descrizione:</label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" >
							</div>
							
							<div class="form-group col-md-6">
								<label>Importo:</label>
								<input type="number" name="importo" id="importo" class="form-control" placeholder="Inserire l'importo" >
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Data di Pubblicazione:</label>
                        		<input class="form-control" id="dataDiPubblicazione" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataDiPubblicazione" >
							</div>
						
						<div class="form-group col-md-6">
							<label for="stato">Stato:</label>
						    <select class="form-control" id="stato" name="stato" >
						    	<option value="" selected> - Selezionare - </option>
						   			<c:forEach items="${list_stati_attr}" var="statoItem">
							      		<option value="${statoItem }">${statoItem }</option>
							     	</c:forEach>
						    </select>
						</div>
							
						<div class="form-group col-md-6">
							<label for="contribuenteSearchInput">Contribuente:</label>
								<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="contribuenteSearchInput"
									name="contribuenteInput" value="${contribuente_search_attr.nome}${empty contribuente_search_attr.nome?'':' '}${contribuente_search_attr.cognome}">
								<input type="hidden" name="contribuente.id" id="contribuenteId" value="${contribuente_search_attr.id }">
						</div>
							
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
						<input class="btn btn-outline-warning" type="reset" value="Ripulisci">

						<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath }/cartellaesattoriale/insert">Add New</a>
						
					</form:form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
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
	
	
</body>
</html>