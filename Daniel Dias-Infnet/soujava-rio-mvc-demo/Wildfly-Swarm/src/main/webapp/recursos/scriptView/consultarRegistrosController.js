var consultarRegistrosControllerApp = angular.module("consultarRegistrosControllerApp",[]);

consultarRegistrosControllerApp.controller("consultarRegistrosController",function($scope, $window, $http){

	/*CRIANDO UM ARRAY PARA OS REGISTROS QUE VÃO SER RETORNADOS PELO REST*/
	 $scope.usuarios = new Array();

	 $scope.init = function(){

		 /*CHAMA O MÉTODO consultarTodos DO CONTROLLER GERENCIADO PELO REST*/
		 var response = $http.get("listar");

		 response.success(function(data, status, headers, config) {

			 /*SETA OS REGISTROS QUE FORAM RETORNADOS DO CONTROLLER DO REST,
			  ESSE REGISTROS VÃO PREENCHER OS CAMPOS DA TABELA DA PÁGINA consultarRegistros.jsp
			  ATAVÉS DO ng-repeat do AngularJS*/
			 $scope.usuarios = data;

		 });

		 response.error(function(data, status, headers, config) {
			 /*SE OCORRER ERRO NÃO TRATADO IREMOS MOSTRA EM MENSAGEM*/
			 swal(data, {
			      icon: "warning", 
			    });

		 });
	 }

	 /*FUNÇÃO PARA EXCLUIR UM REGISTRO*/	 
	 $scope.excluirRegistro = function(codigo){
		 
		 swal({
			 title : "Você tem certeza que deseja excluir este registro ?",
			 text : "Você não poderá recuperar este registro após excluir !",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  
				  var response = $http.delete("remover/" + codigo);
				  
				  response.success(function(data, status, headers, config) {

						// $window.alert(data.mensagem);
						 swal("sucesso", {
						      icon: "success", 
						    });
						 $scope.init();
					 });
			    
			  } else {
			    swal("Seus Dados estão seguros!"); 
			  }
			});
		 

//		 if($window.confirm("Deseja realmente excluir esse registro?")){
//
//			 /*CHAMA O MÉTODO DO SPRING PARA EXCLUIR UM REGISTRO*/
//			 var response = $http.delete("/RestFul-MongoDB/api/pessoa/deletar/" + codigo);
//
//			 response.success(function(data, status, headers, config) {
//
//				 $window.alert(data.mensagem);
//				 /*RECARREGANDO OS REGISTROS CADASTRADOS*/
//				 $scope.init();
//			 });
//
//			 response.error(function(data, status, headers, config) {
//				 /*SE OCORRER ERRO NÃO TRATADO IREMOS MOSTRA EM MENSAGEM*/
//				 $window.alert(data);
//			 });
//		 }
	 }
});