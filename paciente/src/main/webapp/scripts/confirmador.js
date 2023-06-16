/**
 * Confirmar a exclusao de um paciente
 * 
 * @author Joelton Gomes
 * @author Manoel Neto
 * @param idcon
 */

function confirmar(idcon) {
	let resposta = confirm("Confirma a exclusão deste paciente?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
}