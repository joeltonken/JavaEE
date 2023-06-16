/**
 * Validar campos obrigatorios
 * 
 * @author Joelton Gomes
 * @author Manoel Neto
 */

function validar() {
	let nome = frmContato.nome.value
	let peso = frmContato.peso.value
	let altura = frmContato.altura.value
	if (nome === "") {
		alert('Preencha o campo Nome')
		frmContato.nome.focus()
		return false
	} else if (peso === "") {
		alert('Preencha o campo Peso')
		frmContato.peso.focus()
		return false
	} else if (altura === "") {
		alert('Preencha o campo Altura') 
		frmContato.altura.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}