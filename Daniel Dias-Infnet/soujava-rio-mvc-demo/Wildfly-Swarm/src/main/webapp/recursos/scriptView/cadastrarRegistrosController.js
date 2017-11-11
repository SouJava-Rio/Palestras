var cadastrarControllerApp = angular.module("cadastrarControllerApp",[]);

cadastrarControllerApp.controller("cadastrarController",function($scope, $window, $http){

	/*DEFININDO O MODELO PARA O NOSSO FORMULÁRIO*/
	$scope.nome  = null;
	$scope.leader = null;
	$scope.descricao = null;
	$scope.email = null;
	$scope.localidade  = null;
	
	$scope.salvarUsuario = function(){

		/*DEFINI O OBJETO QUE VAI SER ENVIADO VIA AJAX PELO ANGULARJS*/
		var usuarioModel =  new Object();
		usuarioModel.nome  = $scope.nome,
		usuarioModel.leader = $scope.leader,
		usuarioModel.descricao = $scope.descricao,
		//usuarioModel.dataCadastro = $scope.dataCadastro,
		usuarioModel.email = $scope.email,
		usuarioModel.localidade = $scope.localidade,
		usuarioModel.localidade = $scope.localidade;	

		/*EXECUTA O POST PARA SALVAR O REGISTRO*/
		var response = $http.post("/demo/app/jug/form", usuarioModel);

		response.success(function(data, status, headers, config) {

			 //$window.alert(data.mensagem);

			swal({
				text: data.mensagem,
				icon: "success"
				}).then(function() {
				// Redirect the user
				window.location.href = "../views/consultarRegistros.jsp";
				});
			 
	 });
		
		//$window.location.href ="../jsp/consultarRegistros.jsp";
		
	 response.error(function(data, status, headers, config) {
		 /*SE OCORRER ERRO NÃO TRATADO IREMOS MOSTRA EM MENSAGEM*/
		 //$window.alert(data);
		 swal(data, {
		      icon: "success", 
		    });

	 });		

	};
});