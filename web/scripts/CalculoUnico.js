function completaProcesso(processo) {
    var numero = processo.value;
    var tipo = document.forms[0].tipo.value;
    for (i = 0; i < 5; i++) {
        numero = numero.replace(".", "");
        numero = numero.replace("-", "");
    }
    if (numero != "") {
        if (tipo == "cnj") {
            while (numero.length < 20) {
                numero = "0" + numero;
            }
            numero = numero.substring(0, 21);
        } else if (tipo == "unico") {
            while (numero.length < 17) {
                numero = "0" + numero;
            }
            numero = numero.substring(0, 18);
        }
        numero = isIntNumero(numero);
        if (numero != "")
            numero = formataNumero(numero, tipo);
        document.forms[0].numeroProcesso.value = numero;
        if (tipo == "cnj") {
            comarca(numero.substring(16, 17), numero.substring(18, 20), numero.substring(21, 25));
        } else {
            comarca(document.getElementById("ddlSegJustica").value, numero.substring(15, 17), numero.substring(11, 14));
        }
    }
}
function verificaAdmissaoDemissao() {
    var data1 = new Date(Date.parse(document.getElementById("admissao").value));
    var data2 = new Date(Date.parse(document.getElementById("demissao").value));
    if (data2 < data1) {
        window.alert("A data de demissão não pode ser inferior a data de admisão");
        return false;
    }
    return true;
}
function formataNumero(valor, tipodoc) {
    var formatar = valor;
    var tipo = tipodoc;
    tipo == "cnj" ?
            formatar = formatar.substring(0, 7) + "-" + formatar.substring(7, 9) + "." + formatar.substring(9, 13)
            + "." + formatar.substring(13, 14) + "." + formatar.substring(14, 16) + "." + formatar.substring(16, 20)
            :
            formatar = formatar.substring(0, 5) + "-" + formatar.substring(5, 9) + "-" + formatar.substring(9, 12)
            + "-" + formatar.substring(12, 14) + "-" + formatar.substring(14, 16) + "-" + formatar.substring(16, 17);
    return formatar;
}
function processoClick(radioButton) {
    document.forms[0].numeroProcesso.value = "";
    document.forms[0].tipo.value = radioButton.value;
    if (radioButton.value == "unico") {
        document.getElementById("trSegJustica").style = "display:normal";
    } else {
        document.getElementById("trSegJustica").style = "display:none";
    }
}
function validaDocs() {
    var message = true;
    for (i = 1; i < 10; i++) {
        var idTxb = "txb" + i + "doc";
        if (document.getElementById(idTxb).disabled == false &&
                (document.getElementById(idTxb).value == "" || document.getElementById(idTxb).value == "0")) {
            message = false;
            if (!message) {
                window.alert("Favor preencher os documentos de todas as Reclamadas ativas");
            }
        }
    }
    return message;
}



function altera(radio) {
    var value = radio.value;
    var id = "txb" + radio.name;
    if (value == "cpf") {
        document.getElementById(id).value = "";
        document.getElementById(id).placeholder = "cpf";
    } else {
        document.getElementById(id).value = "";
        document.getElementById(id).placeholder = "cnpj";
    }
}
function verificaDatas() {
    var adm = document.getElementById("admissao").value;
    var aju = document.getElementById("ajuizamento").value;
    var dem = document.getElementById("demissao").value;
    if (adm.length < 10 || aju.length < 10 || dem.length < 10) {
        window.alert("Favor preencher todas as datas");
        return false;
    } else {
        return true;
    }
}

function verificaReclamadasVazias() {
    var retorno = true;
    for (i = 1; i < 9; i++) {
        var nome1 = "txbNome" + i;
        var nome2 = "txbNome" + (i + 1);
        var trNext = "trReclamada" + (i + 1);
        if (document.getElementById(nome1).value == "" &&
                (document.getElementById(nome2).value != "" && document.getElementById(trNext).style != "display:none")) {
            window.alert("A reclamada " + i + " encontra-se vazia, mas a reclamada " + (i + 1) + " está preenchida.\n"
                    + "Favor preencher a reclamada " + i + " ou apagar a reclamada " + (i + 1) + " para prosseguir.\n"
                    + "(Alterar uma reclamada para LIVRE irá apagar seu conteúdo e pode permitir o avanço)");
            retorno = false;
        }
        if (document.getElementById("txbNomeRec").value == "" || (document.getElementById("txbNomeRec").value != "" && document.getElementById("txbRecdoc").value == "")) {
            window.alert("Nome ou documento do Reclamante não preenchidos");
            retorno = false;
        }
        return retorno;
    }
}
function existReclamante(txb) {
    var value = document.getElementById("txbNomeRec").value;
    value.length > 0 ? document.getElementById("txbRecdoc").disabled = false : document.getElementById("txbRecdoc").disabled = true;
}
function exist(txb) {
    var proximo = "trReclamada" + (txb + 1);
    var atual = "txbNome" + txb;
    var value = document.getElementById(atual).value;
    var doc = "txb" + txb + "doc";
    value.length > 0 ? document.getElementById(doc).disabled = false : document.getElementById(doc).disabled = true;
    if (value.length > 0) {
        document.getElementById(proximo).style = "display:normal";
    }
}
function tipoReclamada(dropdown, id) {
    var trNext = "trReclamada" + (id + 1);
    var cpf = id + "nomeCpf";
    var cnpj = id + "nomeCnpj";
    var idNome = "txbNome" + id;
    var idDoc = "txb" + id + "doc";
    if (dropdown.value != "") {
        var nome = dropdown.value.split(";;")[0];
        var doc = dropdown.value.split(";;")[1];
        document.getElementById(idNome).disabled = true;
        document.getElementById(idDoc).disabled = true;
        document.getElementById(idNome).value = nome;
        document.getElementById(idDoc).value = doc;
        document.getElementById(trNext).style = "display:normal";
        document.getElementById(cpf).checked = false;
        document.getElementById(cnpj).checked = true;
    } else {
        document.getElementById(idNome).disabled = false;
        document.getElementById(idNome).required = true;
        document.getElementById(idNome).value = "";
        document.getElementById(idDoc).value = "";
    }
}
function addVerba(campo) {
    document.getElementById("action").value = "calculoArbitramentoVerba";
    document.getElementById("tipoCalculo").value = campo.name;
    document.form.submit();
    janela = window.open("calculoArbitramentoAdicionaVerba", "_blank", "width=800,height=800,location=no,resizable=no");
}
function visualizaVerba(campo) {
    document.getElementById("action").value = "calculoArbitramentoVerbaVisualizarNone";
    document.getElementById("tipoCalculo").value = campo.name;
    document.form.submit();
}
function visualizarResultadoCalculo(campo) {
    document.getElementById("action").value = "visualizarResultadoCalculoNone";
    document.getElementById("tipoCalculo").value = campo.name;
    document.form.submit();
}

function confereAvisoPrevio(campo) {
    var valor = campo.value;
    if (valor < 30 || valor > 90) {
        window.alert("Período de aviso prévio inválido");
    } else {
        if (isNaN(valor)) {
            window.alert("Apenas números são válidos neste campo");
            valor = "";
        }
    }
    campo.value = valor;
}
function addObservacoes() {
    var obs = document.getElementById("ddlObservacoes").value;
    document.getElementById("txbObservacoes").value += obs +"\n";
}

function comarca(j, tr, oooo) {
    document.getElementById("auxComarca").value = j + ";;" + tr + ";;" + oooo;
    document.getElementById("action").value = "pesquisaComarca";
    for (i = 1; i < 10; i++) {
        var nome = "txbNome" + i;
        var doc = "txb" + i + "doc";
        document.getElementById(nome).disabled = false;
        document.getElementById(doc).disabled = false;
    }
    document.form.submit();
}

function limparDivisor(campo) {
    var divisor = campo.value;
    if (divisor != "outro") {
        document.getElementById("divisorOutro").style = "display:true";
        document.getElementById("divisorOutro").required = "false";
        document.getElementById("divisorOutro").value = divisor;
    } else {
        document.getElementById("divisorOutro").style = "display:false";
        document.getElementById("divisorOutro").required = "true";
    }
}