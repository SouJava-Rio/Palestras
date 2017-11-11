/**
 * funçoes para formulario
 */

function formata_mascara(campo_passado, mascara) {

	var campo = campo_passado.value.length;
	var saida = mascara.substring(0, 1);
	var texto = mascara.substring(campo);
	if (texto.substring(0, 1) != saida) {
		campo_passado.value += texto.substring(0, 1);

	}
}