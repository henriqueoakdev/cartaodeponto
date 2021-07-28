function enviar(String) {
    document.getElementById("action").value = String;
    document.form.submit();
}
function isIntNumero(numero) {
    numero.replace(",", ".");
    if (isNaN(numero)) {
        window.alert("Apenas números são válidos neste campo");
        return 0;
    }
    numero.replace(".", ",");
    return numero;
}

function isNumber(numero) {
    var i = numero.length;
    if (isNaN(numero.substring(i - 1, i))) {
        window.alert("Apenas números são válidos neste campo");
        return numero.substring(0, i - 1);
    }
    return numero;
}

function isInt(campo) {
    campo.value = campo.value.replace(",", ".");
    if (isNaN(campo.value)) {
        window.alert("Apenas números são válidos neste campo");
        campo.value = "";
    }
    campo.value = campo.value.replace(".", ",");
}

function validaData(data) {
    
    if (data.value.length < 10) {
        var check = data.value;
        check = check.replace("/", "");
        check = check.replace("/", "");
        if (isNaN(check)) {
            data.value = "";
            window.alert("Apenas numeros são aceitos neste campo");
        }
    } else if (data.value.length == 10) {
        var dia = (data.value.substring(0, 2));
        var mes = (data.value.substring(3, 5));
        var ano = (data.value.substring(6, 10));
        if (isNaN(dia)) {
            dia = 00;
        }
        if (isNaN(mes)) {
            mes = 00;
        }
        if (isNaN(ano)) {
            ano = 0000;
        }
        var situacao = "";
        // verifica o dia valido para cada mes 
        if ((dia < 01) || ((dia < 01 || dia > 30) && (mes == 04 || mes == 06 || mes == 9 || mes == 11)) || dia > 31) {
            situacao = "falsa";
        }
// verifica se o mes e valido 
        if (mes < 01 || mes > 12) {
            situacao = "falsa";
        }
// verifica se e ano bissexto 
        if (mes == 02 && (dia < 01 || dia > 29 || (dia > 28 && (parseInt(ano % 4) != 0)))) {
            situacao = "falsa";
        }
        if (data == "" || dia == "" || mes == "" || ano == "") {
            situacao = "falsa";
        }
        if (situacao == "falsa") {
            window.alert("Data inválida!");
            data.value = "";
            data.focus();
        }
    }
}

function mascaraData(campoData) {
    var data = campoData.value;
    if (data.length == 2) {
        data = data + "/";
    }
    if (data.length == 5) {
        data = data + '/';
    }
    campoData.value = data;
}


function formataCpf(value) {
    var cpf = value;
    cpf = cpf.replace(".", "");
    cpf = cpf.replace(".", "");
    cpf = cpf.replace("-", "");
    cpf = isIntNumero(cpf);
    if (cpf.length == 11) {
        cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
    return cpf;
}

function formataCnpj(value) {
    var cnpj = value;
    cnpj = cnpj.replace(".", "");
    cnpj = cnpj.replace(".", "");
    cnpj = cnpj.replace("/", "");
    cnpj = cnpj.replace("-", "");
    cnpj = isIntNumero(cnpj);
    if (cnpj.length == 14) {
        cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
    }
    return cnpj;
}

function formata(txb) {
    var tipo = txb.placeholder;
    var valor = txb.value;
    tipo == "cpf" ? txb.value = formataCpf(valor) : txb.value = formataCnpj(valor);
    }
function mascaraTelefone(campo) {
    var telefone = campo.value;
    telefone = isNumber(telefone);
    if (telefone.length==4) {
                        telefone += "-";
    }
    campo.value = telefone;
}

function mascaraDdd(campo) {
    var Ddd = campo.value;
    Ddd = isNumber(Ddd);
    switch (Ddd.length) {
        case 0:
            Ddd = "(";
            break;
        case 1:
            Ddd = "(0";
            break;
        case 2:
            Ddd = "(" + Ddd.substring(1, 2);
            break;
        case 3:
            Ddd = "(" + Ddd.substring(1, 3);
            break;
        case 4:
            Ddd = "(" + Ddd.substring(1, 4) + ")";
    }
    campo.value = Ddd;
}
