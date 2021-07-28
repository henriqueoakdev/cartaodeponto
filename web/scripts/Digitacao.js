//Toda e qualquer função que envolver salvar os valores da digitação devem chamar a função "habilitaCampos"

function trocaExcedente() //Função "Troca Excedente chamada" apartir da janela de digitação
{
    window.open("trocaExcedente", "_blank", "scrollable=yes,location=no,resizable=no");
    habilitaCampos();
    enviar('salvarDigitacao');
}
function salvaRelatorio() { //Função chamada na tela de emissão de relatórios
    habilitaCampos();
    enviar('emissaoRelatorios');
}
function salvarDigitacao() { //Função chamada para salvar os valores da digitação
    habilitaCampos();
    enviar('salvarDigitacao');
}
function zeraCampos() { //Função chamada para mudar os valores dos campos de total semanal e total mensal para 0, caso haja algum erro
    var semana = document.getElementsByName("totalSemana");
    var mes = document.getElementsByName("totalMes");
    for (var x = 0; x < semana.length; x++) {
        if (semana[x].value == "" || semana[x].value == "NaN") {
            semana[x].value = "0";
        }
    }
    for (var x = 0; x < mes.length; x++) {
        if (mes[x].value == "" || mes[x].value == "NaN") {
            mes[x].value = "0";
        }
    }
}
function habilitaCampos() { //Função chamada para habilitar todos os campos da tela de digitação
    exibeColunas('15');
    renameFolgas('f0');
    renameFolgas('f1');
    renameFolgas('f2');
    renameFolgas('f3');
    renameFolgas('f4');
    renameFolgas('f5');
    renameFolgas('f6');
    renameFolgas('f8');
    var lista1 = document.getElementsByName("datatabela");
    var lista2 = document.getElementsByName("totalHoras");
    var lista3 = document.getElementsByName("folga");
    var lista4 = document.getElementsByName("totalSemana");
    var lista5 = document.getElementsByName("totalMes");
    for (var x = 0; x < lista1.length; x++) {
        lista1[x].disabled = false;
    }
    for (var x = 0; x < lista2.length; x++) {
        lista2[x].disabled = false;
    }
    for (var x = 0; x < lista3.length; x++) {
        lista3[x].disabled = false;
    }
    for (var x = 0; x < lista4.length; x++) {
        lista4[x].disabled = false;
    }
    for (var x = 0; x < lista5.length; x++) {
        lista5[x].disabled = false;
    }
    zeraCampos();
}
function habilitaCamposGenerico(campo) {
    campo.disabled = false;
}
function renameFolgas(nome) { //Função chamada para definir dias como folgas
    var lista = document.getElementsByName(nome);
    while (lista.length > 0) {
        lista[0].name = "folga";
    }
}
function refresh() { //Função chamada para dar refresh na página de digitação
    habilitaCampos();
    enviar('atualizaDigitarCartao');
}
function jornadaSF() //Função chamada para abrir a janela da "Jornada Semanal Fixa"
{
    window.open("jornadaSF", "_blank", "scrollable=yes,location=no,resizable=no");
}
function jornadaDF() //Função chamada  para abrir a janela da "Jornada Diária Fixa"
{
    window.open("jornadaDF", "_blank", "scrollable=yes,location=no,resizable=no");
}
function folgasCompensatorias() { //Função chamada  para fazer as marcações de folgas compensatórias
    var datas = document.getElementsByName("datatabela");
    var totalHoras = document.getElementsByName("totalHoras");
    var campoFolgas = document.getElementsByClassName("folga");
    var dia;
    for (var i = 0; i < datas.length; i++) {
        dia = datas[i].value.substring(datas[i].value.length - 3, datas[i].value.length);
        if (dia == "Dom") {
            if (totalHoras[i].value == 0) {
                if (campoFolgas[i].value == "N") {
                    campoFolgas[i].value = "S";
                }
            } else {
                var j = 1;
                while (j < 7) {
                    if (i - j >= 0) {
                        if (totalHoras[i - j].value == 0) {
                            if (campoFolgas[i - j].value == "N") {
                                campoFolgas[i - j].value = "S";
                            }
                            j = 7;
                        }
                    }
                    j++;
                }
            }
        } else if (dia == "Fer") {
            if (i > 0) {
                var diaAnterior = datas[i - 1].value.substring(datas[i - 1].value.length - 3, datas[i - 1].value.length);
                if (campoFolgas[i].value == "N") {
                    campoFolgas[i].value = "S";
                } else {
                    var j = 1;
                    while (j < 7) {
                        if (totalHoras[i - j].value == 0) {
                            if (campoFolgas[i - j].value == "N") {
                                campoFolgas[i - j].value = "S";
                            }
                            j = 7;
                        }
                        j++;
                    }
                }
            } else if (i < datas.length - 1) {
                var diaSeguinte = datas[i + 1].value.substring(datas[i + 1].value.substring - 3, datas[i + 1].value.substring);
                if (diaSeguinte == "Seg") {
                    if (totalHoras[i].value == 0) {
                        if (campoFolgas[i].value == "N") {
                            campoFolgas[i].value = "S";
                        }
                    } else {
                        var j = 1;
                        while (j < 7) {
                            if (i - j >= 0) {
                                if (totalHoras[i - j].value == 0) {
                                    if (campoFolgas[i - j].value == "N") {
                                        campoFolgas[i - j].value = "S";
                                    }
                                    j = 7;
                                }
                            }
                            j++;
                        }
                    }
                }
            }
        }

    }
}
function limpaFolgas(tag) { //Função chamada  para mudar os valores dos campos de folga para "N"
    var lista = document.getElementsByName(tag);
    for (var x = 0; x < lista.length; x++) {
        lista[x].value = "N";
    }
}
function liberaFolgas(tag) { //Função chamada  para habilitar os campos de folga, permitindo alterar os valores manualmente
    var lista = document.getElementsByName(tag);
    for (var x = 0; x < lista.length; x++) {
        lista[x].disabled = false;
    }
}
function registraFolgas(tag) { //Função chamada  para marcar folgas nos campos determinadas por outra função
    var lista = document.getElementsByName(tag);
    for (var x = 0; x < lista.length; x++) {
        lista[x].value = "S";
    }
}
function marcaFolgas(valor) //Função chamada  para marcar folgas
{
    switch (valor) {
        case 0:
            registraFolgas('f0');
            break;
        case 1:
            registraFolgas('f1');
            break;
        case 2:
            registraFolgas('f2');
            break;
        case 3:
            registraFolgas('f3');
            break;
        case 4:
            registraFolgas('f4');
            break;
        case 5:
            registraFolgas('f5');
            break;
        case 6:
            registraFolgas('f6');
            break;
        case 7:
            registraFolgas('f0');
            registraFolgas('f8');
            break;
        case 8:
            registraFolgas('f0');
            registraFolgas('f6');
            registraFolgas('f8');
            break;
        case 9:
            limpaFolgas('f0');
            registraFolgas('f8');
            break;
        case 10:
            limpaFolgas('f0');
            limpaFolgas('f1');
            limpaFolgas('f2');
            limpaFolgas('f3');
            limpaFolgas('f4');
            limpaFolgas('f5');
            limpaFolgas('f6');
            limpaFolgas('f8');
            break;
        case 11:
            limpaFolgas('f0');
            limpaFolgas('f1');
            limpaFolgas('f2');
            limpaFolgas('f3');
            limpaFolgas('f4');
            limpaFolgas('f5');
            limpaFolgas('f6');
            registraFolgas('f8');
            break;
        case 12:

            break;
        case 13:
            liberaFolgas('f0');
            liberaFolgas('f1');
            liberaFolgas('f2');
            liberaFolgas('f3');
            liberaFolgas('f4');
            liberaFolgas('f5');
            liberaFolgas('f6');
            liberaFolgas('f8');
            break
    }
}
function refazSoma() {
    enviar("refazSoma");
}
function somaHoras(x, numSemana, numMes) //Função chamada  para fazer o somatório das horas semanais e mensais
{
    var totalhoras = 0;
    var E1 = document.getElementsByName("txbE1")[x].value;
    var E2 = document.getElementsByName("txbE2")[x].value;
    var E3 = document.getElementsByName("txbE3")[x].value;
    var E4 = document.getElementsByName("txbE4")[x].value;
    var E5 = document.getElementsByName("txbE5")[x].value;
    var E6 = document.getElementsByName("txbE6")[x].value;
    var E7 = document.getElementsByName("txbE7")[x].value;
    var E8 = document.getElementsByName("txbE8")[x].value;
    var E9 = document.getElementsByName("txbE9")[x].value;
    var E10 = document.getElementsByName("txbE10")[x].value;
    var E11 = document.getElementsByName("txbE11")[x].value;
    var E12 = document.getElementsByName("txbE12")[x].value;
    var E13 = document.getElementsByName("txbE13")[x].value;
    var E14 = document.getElementsByName("txbE14")[x].value;
    var E15 = document.getElementsByName("txbE15")[x].value;
    var S1 = document.getElementsByName("txbS1")[x].value;
    var S2 = document.getElementsByName("txbS2")[x].value;
    var S3 = document.getElementsByName("txbS3")[x].value;
    var S4 = document.getElementsByName("txbS4")[x].value;
    var S5 = document.getElementsByName("txbS5")[x].value;
    var S6 = document.getElementsByName("txbS6")[x].value;
    var S7 = document.getElementsByName("txbS7")[x].value;
    var S8 = document.getElementsByName("txbS8")[x].value;
    var S9 = document.getElementsByName("txbS9")[x].value;
    var S10 = document.getElementsByName("txbS10")[x].value;
    var S11 = document.getElementsByName("txbS11")[x].value;
    var S12 = document.getElementsByName("txbS12")[x].value;
    var S13 = document.getElementsByName("txbS13")[x].value;
    var S14 = document.getElementsByName("txbS14")[x].value;
    var S15 = document.getElementsByName("txbS15")[x].value;
    var colunas = document.getElementById("selectColunas").value;

    switch (colunas) {
        case "15":
        {
            totalhoras += verificaSoma(E15, S15);
        }
        case "14":
        {
            totalhoras += verificaSoma(E14, S14);
        }
        case "13":
        {
            totalhoras += verificaSoma(E13, S13);
        }
        case "12":
        {
            totalhoras += verificaSoma(E12, S12);
        }
        case "11":
        {
            totalhoras += verificaSoma(E11, S11);
        }
        case "10":
        {
            totalhoras += verificaSoma(E10, S10);
        }
        case "9":
        {
            totalhoras += verificaSoma(E9, S9);
        }
        case "8":
        {
            totalhoras += verificaSoma(E8, S8);
        }
        case "7":
        {
            totalhoras += verificaSoma(E7, S7);
        }
        case "6":
        {
            totalhoras += verificaSoma(E6, S6);
        }
        case "5":
        {
            totalhoras += verificaSoma(E5, S5);
        }
        case "4":
        {
            totalhoras += verificaSoma(E4, S4);
        }
        case "3":
        {
            totalhoras += verificaSoma(E3, S3);
        }
        case "2":
        {
            totalhoras += verificaSoma(E2, S2);
        }
        case "1":
            {
                totalhoras += verificaSoma(E1, S1);
            }
            break;
    }
    document.getElementsByName("totalHoras")[x].value = totalhoras;
    somaHorasSemanais(numSemana);
    somaHorasMensais(numMes);
}
//converte hora para decimal
function converteHoras(ex, sx) //Função chamada  para converter valores de horas para deciamais
{
    if (ex.length == 5 && sx.length == 5) {
        var ehm = ex.split(":");
        var shm = sx.split(":");
        var resultE = parseFloat(ehm[0]) + parseFloat((ehm[1] / 60));
        var resultS = parseFloat(shm[0]) + parseFloat((shm[1] / 60));
        if (resultE < resultS) {
            return resultS - resultE;
        } else {
            return 0;
        }
    } else {
        return 0;
    }
}

//função para apagar linhas de um determinado dia da semana 
function diaSemana(campo) {
    var dia = campo.id;
    var lista = document.getElementsByName("datatabela");
    for (x = 0; x < lista.length; x++) {
        var diasemana = lista[x].value.split("-")[1];
        var trdia = lista[x].value.split("-")[0];
        if (diasemana == dia)
        {
            if (campo.checked == false)
            {
                document.getElementById(trdia).style.display = "none";
            } else {
                document.getElementById(trdia).style.display = "";
            }
        }
    }
}
//funções do botão de alternar dia sim/dia não
function alternaDia() {
    document.getElementById("diaSimNao").style = "margin-bottom:5px;display:none";
    document.getElementById("diaSequencial").style = "margin-bottom:5px;display:normal";
    var lista = document.getElementsByName("par");
    for (var x = 0; x < lista.length; x++) {
        lista[x].style = "display:none";
    }
}
function sequenciaDia() {
    document.getElementById("diaSimNao").style = "margin-bottom:5px;display:normal";
    document.getElementById("diaSequencial").style = "margin-bottom:5px;display:none";
    var lista = document.getElementsByName("par");
    for (var x = 0; x < lista.length; x++) {
        lista[x].style = "display:normal";
    }
}
//funções dos campos Modal
function abreJanela(campo) {
    document.getElementById(campo).style.display = "block";
}
function fechaJanela(campo) {
    document.getElementById(campo).style.display = "none";
}
window.onclick = function (event) {
    if (event.target == document.getElementById('modaldia')) {
        document.getElementById('modaldia').style.display = "none";
    }
    if (event.target == document.getElementById('modaldiasemana')) {
        document.getElementById('modaldiasemana').style.display = "none";
    }
    if (event.target == document.getElementById('modaltrocacoluna')) {
        document.getElementById('modaltrocacoluna').style.display = "none";
    }
    if (event.target == document.getElementById('modalalmocofixo')) {
        document.getElementById('modalalmocofixo').style.display = "none";
    }
}
//função dos campos de hora que passa pro proximo campo automaticamente
function ajustaHora(elmnt, content)
{
    if (content.replace(":", "") > 2359)
    {
        elmnt.value = "23:59";
    } else if (content.replace(":", "") < 0)
    {
        elmnt.value = "0";
    }
    if (content.length === elmnt.maxLength)
    {
        next = elmnt.tabIndex;
        var teste = document.forms[0].elements[next].disabled;
        while (teste === true)
        {
            var x = next;
            document.forms[0].elements[next].disabled = "false";
            next = document.forms[0].elements[next].tabIndex;
            document.forms[0].elements[x].disabled = "true";
            teste = document.forms[0].elements[next].disabled;
        }
        if (next < document.forms[0].elements.length) {
            document.forms[0].elements[next].focus();
        }
    }
    destacaHoras();
}

//função das Checkbox para desabilitar colunas
function exibe(campo)
{
    var tipo = campo.id;
    if (campo.checked === false)
    {
        var nome = "txb" + tipo;
        var lista = document.getElementsByName(tipo);
        var listaTxb = document.getElementsByName(nome);
        for (var x = 0; x < lista.length; x++) {
            lista[x].style = "display:none";
        }
        for (var x = 0; x < listaTxb.length; x++) {
            listaTxb[x].disabled = true;
        }
    } else
    {
        var nome = "txb" + tipo;
        var lista = document.getElementsByName(tipo);
        var listaTxb = document.getElementsByName(nome);
        for (var x = 0; x < lista.length; x++) {
            lista[x].style = "display:normal";
        }
        for (var x = 0; x < listaTxb.length; x++) {
            listaTxb[x].disabled = false;
        }
    }
}
//função para destacar a linha de um dia especifico
function pesquisaData()
{
    var diaFoco;
    var data = document.getElementById("pesquisadia").value;
    document.getElementById(data).style = "background-color:blue";
    diaFoco = data;
}
function limpaMarcacoes() { //Função chamada  para limpar marcações de dias
    var par = document.getElementsByName("par");
    var impar = document.getElementsByName("impar");
    for (var i = 0; i < par.length; i++) {
        if (par[i].style = "background-color:blue") {
            par[i].style = "";
        }
        if (impar[i].style = "background-color:blue") {
            impar[i].style = "";
        }
    }
}
//função jQuery que aplica máscara nos campos de hora
jQuery(function ($)
{
    $(".tempo").mask("99:99");
    $(".tempo5").mask("99/99/9999");
    $(".tempo3").mask("99:99");
});

//função que atribui um valor para o Action e envia uma solicitação para o Servlet
function enviar(destino) {
    document.getElementById("action").value = destino;
    var lista = document.getElementsByName("totalHoras");
    for (var x = 0; x < lista.length; x++) {
        lista[x].disabled = false;
    }
    document.form.submit();
}
//função que faz o somatório das horas semanais
function somaHorasSemanais(num) {
    var semana = "fds" + num;
    var totalSemana = "totalSemana" + num;
    var total = 0;
    var somaDias = document.getElementsByClassName(semana);
    for (var i = 0; i < somaDias.length; i++) {
        total += parseFloat(somaDias[i].value);
    }
    document.getElementById(totalSemana).value = total;
}
//função que faz o somatório das horas mensais
function somaHorasMensais(num) {
    var mes = "mes" + num;
    var totalMes = "totalMes" + num;
    var total = 0;
    var somaDias = document.getElementsByClassName(mes);
    for (var i = 0; i < somaDias.length; i++) {
        total += parseFloat(somaDias[i].value);
    }
    document.getElementById(totalMes).value = total;
} //Funções chamadas para conferir qual radio button da função Troca Colunas está marcado
function updateR1(radio) {
    document.getElementById("radioAuxiliar1").value = radio.value;
}
function updateR2(radio) {
    document.getElementById("radioAuxiliar2").value = radio.value;
}
function trocaColunasRadio() { //Função chamada para atribuir os valores à função Troca Colunas
    var radio1 = document.getElementById("radioAuxiliar1").value;
    var radio2 = document.getElementById("radioAuxiliar2").value;
    trocaColunas(radio1, radio2);
    fechaJanela('modaltrocacoluna');
}
function trocaColunas(radio1, radio2) { //Função chamada para trocar os valores de duas colunas
    for (var i = 0; i < document.getElementsByName(radio1).length; i++) {
        var auxTroca1 = document.getElementsByName(radio1)[i].value;
        var auxTroca2 = document.getElementsByName(radio2)[i].value;
        document.getElementsByName(radio2)[i].value = auxTroca1;
        document.getElementsByName(radio1)[i].value = auxTroca2;
    }
}
function trocaColunasVerificacao(coluna1, coluna2, i) { //Função que faz a conferência se os valores de S14 e E15 estão vazios
    var auxTroca1 = document.getElementsByName(coluna1)[i].value;
    var auxTroca2 = document.getElementsByName(coluna2)[i].value;
    if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
        document.getElementsByName(coluna2)[i].value = auxTroca1;
        document.getElementsByName(coluna1)[i].value = auxTroca2;
    }
}
function habilitaAlmoco(radio) { //Função que habilita qual tipo de intervalo de almoço será utilizado
    var saida = document.getElementById("saidaAlmoco");
    var retorno = document.getElementById("retornoAlmoco");
    var intervaloAcima = document.getElementById("intervaloAcimaAlmoco");
    var intervaloAbaixo = document.getElementById("intervaloAbaixoAlmoco");
    var horas = document.getElementById("horasAlmoco");
    if (radio.value == "movel") {
        saida.disabled = true;
        retorno.disabled = true;
        intervaloAcima.disabled = false;
        intervaloAbaixo.disabled = false;
        horas.disabled = false;
    } else if (radio.value == "fixo") {
        saida.disabled = false;
        retorno.disabled = false;
        intervaloAcima.disabled = true;
        intervaloAbaixo.disabled = true;
        horas.disabled = true;
    }
} //Funções chamadas para limpar os campos com valor igual à 00:00
function limpaCampo(campo) {
    for (var i = 0; i < campo.length; i++) {
        if (campo[i].value == "00:00") {
            campo[i].value = "";
        }
    }
}
function limpaTodosCampos() {
    var e1 = document.getElementsByName("txbE1");
    var s1 = document.getElementsByName("txbS1");
    var e2 = document.getElementsByName("txbE2");
    var s2 = document.getElementsByName("txbS2");
    var e3 = document.getElementsByName("txbE3");
    var s3 = document.getElementsByName("txbS3");
    var e4 = document.getElementsByName("txbE4");
    var s4 = document.getElementsByName("txbS4");
    var e5 = document.getElementsByName("txbE5");
    var s5 = document.getElementsByName("txbS5");
    var e6 = document.getElementsByName("txbE6");
    var s6 = document.getElementsByName("txbS6");
    var e7 = document.getElementsByName("txbE7");
    var s7 = document.getElementsByName("txbS7");
    var e8 = document.getElementsByName("txbE8");
    var s8 = document.getElementsByName("txbS8");
    var e9 = document.getElementsByName("txbE9");
    var s9 = document.getElementsByName("txbS9");
    var e10 = document.getElementsByName("txbE10");
    var s10 = document.getElementsByName("txbS10");
    var e11 = document.getElementsByName("txbE11");
    var s11 = document.getElementsByName("txbS11");
    var e12 = document.getElementsByName("txbE12");
    var s12 = document.getElementsByName("txbS12");
    var e13 = document.getElementsByName("txbE13");
    var s13 = document.getElementsByName("txbS13");
    var e14 = document.getElementsByName("txbE14");
    var s14 = document.getElementsByName("txbS14");
    var e15 = document.getElementsByName("txbE15");
    var s15 = document.getElementsByName("txbS15");
    limpaCampo(e1);
    limpaCampo(s1);
    limpaCampo(e2);
    limpaCampo(s2);
    limpaCampo(e3);
    limpaCampo(s3);
    limpaCampo(e4);
    limpaCampo(s4);
    limpaCampo(e5);
    limpaCampo(s5);
    limpaCampo(e6);
    limpaCampo(s6);
    limpaCampo(e7);
    limpaCampo(s7);
    limpaCampo(e8);
    limpaCampo(s8);
    limpaCampo(e9);
    limpaCampo(s9);
    limpaCampo(e10);
    limpaCampo(s10);
    limpaCampo(e11);
    limpaCampo(s11);
    limpaCampo(e12);
    limpaCampo(s12);
    limpaCampo(e13);
    limpaCampo(s13);
    limpaCampo(e14);
    limpaCampo(s14);
    limpaCampo(e15);
    limpaCampo(s15);
}

function limitaPeriodo(inicio, final) {
    var diaInicio = inicio.split("/")[0];
    var mesInicio = inicio.split("/")[1];
    var anoInicio = inicio.split("/")[2];
    var diaFinal = final.split("/")[0];
    var mesFinal = final.split("/")[1];
    var anoFinal = final.split("/")[2];
    var cont = 0;
    for (var i = anoInicio; i <= anoFinal; i++) {
        if (i == anoFinal && mesFinal >= 2 && i % 4) {
            cont++;
        } else if (i == anoInicio && mesInicio <= 2 && i % 4) {
            cont++;
        } else if (i % 4 == 0) {
            cont++;
        }
    }
    var meses = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    if (anoInicio == anoFinal) {
        if (mesInicio == mesFinal) {
            if (diaInicio < diaFinal) {
                cont = diaFinal - diaInicio;
            }
        } else if (mesInicio < mesFinal) {
            var x = mesInicio;
            while (x < mesFinal - 1) {
                cont += meses[x - 1];
                x++;
            }
            cont += parseInt(meses[mesInicio - 1], 10) - parseInt(diaInicio, 10) + parseInt(diaFinal, 10);
        }
    } else if (anoInicio < anoFinal) {
        cont += ((anoFinal - anoInicio) - 1) * 365;
        var x = mesInicio;
        while (x != mesFinal - 1) {
            if (x >= 12) {
                x = 1;
            }
            cont += meses[x - 1];
            x++;
        }
        cont += parseInt(meses[mesInicio - 1], 10) - parseInt(diaInicio, 10) + parseInt(diaFinal, 10);
    }
    return cont;
}

function almocoFixo() { //Funções de quantos intervalos de almoço serão inseridos na digitação
    var intervalos = document.getElementById("numIntervalos").value;
    switch (intervalos) {
        case "1":
            {
                almocoFixoVerificacao(1);
            }
            break;

        case "2":
            {
                almocoFixoVerificacao(2);
            }
            break;


        case "3":
            {
                almocoFixoVerificacao(3);
            }
            break;

        case "4":
            {
                almocoFixoVerificacao(4);
            }
            break;

        case "5":
            {
                almocoFixoVerificacao(5);
            }
            break;
    }
}

function almocoFixoVerificacao(intervalo) { //Função que insere os intervalos de almoço
    var e1 = document.getElementsByName("txbE1");
    var s1 = document.getElementsByName("txbS1");
    var e2 = document.getElementsByName("txbE2");
    var s2 = document.getElementsByName("txbS2");
    var e3 = document.getElementsByName("txbE3");
    var s3 = document.getElementsByName("txbS3");
    var e4 = document.getElementsByName("txbE4");
    var s4 = document.getElementsByName("txbS4");
    var e5 = document.getElementsByName("txbE5");
    var s5 = document.getElementsByName("txbS5");
    var e6 = document.getElementsByName("txbE6");
    var s6 = document.getElementsByName("txbS6");
    var dataInicial = document.getElementById("dataInicialAlmocoFixo").value;
    var dataFinal = document.getElementById("dataFinalAlmocoFixo").value;
    var datas = document.getElementsByName("datatabela");
    var cont = limitaPeriodo(dataInicial, dataFinal);
    var x = 0;
    var one_day = 1000 * 60 * 60 * 24;    // Convert both dates to milliseconds
    var date1_ms = new Date(datas[0].value.substring(3, 4) + "/" + datas[0].value.substring(0, 2) + "/" + datas[0].value.substring(6, 10)).getTime();    // Calculate the difference in milliseconds  
    var date2_ms = new Date(dataInicial.substring(3, 4) + "/" + dataInicial.substring(0, 2) + "/" + dataInicial.substring(6, 10)).getTime();
    var difference_ms = date2_ms - date1_ms;        // Convert back to days and return   
    if (difference_ms >= 0) {
        x = Math.round(difference_ms / one_day);
        if (x + cont < s1.length) {
            var horario = document.getElementById("horarioFixo").checked;
            var resposta;
            var entradaSaida;
            var mensagem = "";
            var erro;
            for (var i = 0; i < cont; i++) {
                if (document.getElementsByName("txbS14")[i + x].value == "" && document.getElementsByName("txbE15")[i + x].value == "") {
                    resposta = totalHoras("horasAlmoco", i);
                    entradaSaida = calculaMedia(e1[i + x].value, s1[i + x].value, resposta);
                    EmpurraHoras(i + x);
                    switch (intervalo) {
                        case 5:
                            EmpurraHoras(i + x);
                        case 4:
                            EmpurraHoras(i + x);
                        case 3:
                            EmpurraHoras(i + x);
                        case 2:
                            EmpurraHoras(i + x);
                            break;
                    }
                    if (horario) {
                        switch (intervalo) {
                            case 5:
                                s5[i + x].value = document.getElementById("saidaAlmoco").value;
                                e6[i + x].value = document.getElementById("retornoAlmoco").value;
                            case 4:
                                s4[i + x].value = document.getElementById("saidaAlmoco").value;
                                e5[i + x].value = document.getElementById("retornoAlmoco").value;
                            case 3:
                                s3[i + x].value = document.getElementById("saidaAlmoco").value;
                                e4[i + x].value = document.getElementById("retornoAlmoco").value;
                            case 2:
                                s2[i + x].value = document.getElementById("saidaAlmoco").value;
                                e3[i + x].value = document.getElementById("retornoAlmoco").value;
                            case 1:
                                s1[i + x].value = document.getElementById("saidaAlmoco").value;
                                e2[i + x].value = document.getElementById("retornoAlmoco").value;
                                break;
                        }
                    } else {
                        erro = false;
                        switch (intervalo) {
                            case 5:
                            {
                                entradaSaida = calculaMedia(e1[i + x].value, s6[i + x].value, resposta, 6, 1);
                                erro = intervaloMovel(entradaSaida, "txbE1", "txbE2", "txbS1", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s6[i + x].value, resposta, 6, 2);
                                erro = intervaloMovel(entradaSaida, "txbE2", "txbE3", "txbS2", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s6[i + x].value, resposta, 6, 3);
                                erro = intervaloMovel(entradaSaida, "txbE3", "txbE4", "txbS3", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s6[i + x].value, resposta, 6, 4);
                                erro = intervaloMovel(entradaSaida, "txbE4", "txbE5", "txbS4", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s6[i + x].value, resposta, 6, 5);
                                erro = intervaloMovel(entradaSaida, "txbE5", "txbE6", "txbS5", i, erro);
                            }
                            case 4:
                            {
                                entradaSaida = calculaMedia(e1[i + x].value, s5[i + x].value, resposta, 5, 1);
                                erro = intervaloMovel(entradaSaida, "txbE1", "txbE2", "txbS1", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s5[i + x].value, resposta, 5, 2);
                                erro = intervaloMovel(entradaSaida, "txbE2", "txbE3", "txbS2", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s5[i + x].value, resposta, 5, 3);
                                erro = intervaloMovel(entradaSaida, "txbE3", "txbE4", "txbS3", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s5[i + x].value, resposta, 5, 4);
                                erro = intervaloMovel(entradaSaida, "txbE4", "txbE5", "txbS4", i, erro);
                            }
                            case 3:
                            {
                                entradaSaida = calculaMedia(e1[i + x].value, s4[i + x].value, resposta, 4, 1);
                                erro = intervaloMovel(entradaSaida, "txbE1", "txbE2", "txbS1", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s4[i + x].value, resposta, 4, 2);
                                erro = intervaloMovel(entradaSaida, "txbE2", "txbE3", "txbS2", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s4[i + x].value, resposta, 4, 3);
                                erro = intervaloMovel(entradaSaida, "txbE3", "txbE4", "txbS3", i, erro);
                            }
                            case 2:
                            {
                                entradaSaida = calculaMedia(e1[i + x].value, s3[i + x].value, resposta, 3, 1);
                                erro = intervaloMovel(entradaSaida, "txbE1", "txbE2", "txbS1", i, erro);
                                entradaSaida = calculaMedia(e1[i + x].value, s3[i + x].value, resposta, 3, 2);
                                erro = intervaloMovel(entradaSaida, "txbE2", "txbE3", "txbS2", i, erro);
                            }
                            case 1:
                                {
                                    entradaSaida = calculaMedia(e1[i + x].value, s2[i + x].value, resposta, 2, 1);
                                    erro = intervaloMovel(entradaSaida, "txbE1", "txbE2", "txbS1", i, erro);
                                }
                                break;
                        }
                        if (erro) {
                            mensagem += ((i + x + 1) + "; ");
                        }
                    }
                }
            }


            if (mensagem !== "") {
                window.alert("Não há valores aceitáveis para realização de cálculo nas linhas: " + mensagem);
            }
        } else {
            window.alert("A Data Final do Intervalo deve acabar antes da Data Final do Cartão");
        }
    } else {
        window.alert("A Data Inicial do Intervalo deve começar após a Data Inicial do Cartão");
    }
}

function EmpurraHoras(i) { //Função que desloca os valores de cada linha da digitação para possibilitar a inserção dos valores de intervalo
    trocaColunasVerificacao("txbS13", "txbS14", i);
    trocaColunasVerificacao("txbS12", "txbS13", i);
    trocaColunasVerificacao("txbS11", "txbS12", i);
    trocaColunasVerificacao("txbS10", "txbS11", i);
    trocaColunasVerificacao("txbS9", "txbS10", i);
    trocaColunasVerificacao("txbS8", "txbS9", i);
    trocaColunasVerificacao("txbS7", "txbS8", i);
    trocaColunasVerificacao("txbS6", "txbS7", i);
    trocaColunasVerificacao("txbS5", "txbS6", i);
    trocaColunasVerificacao("txbS4", "txbS5", i);
    trocaColunasVerificacao("txbS3", "txbS4", i);
    trocaColunasVerificacao("txbS2", "txbS3", i);
    trocaColunasVerificacao("txbS1", "txbS2", i);
    trocaColunasVerificacao("txbE14", "txbE15", i);
    trocaColunasVerificacao("txbE13", "txbE14", i);
    trocaColunasVerificacao("txbE12", "txbE13", i);
    trocaColunasVerificacao("txbE11", "txbE12", i);
    trocaColunasVerificacao("txbE10", "txbE11", i);
    trocaColunasVerificacao("txbE9", "txbE10", i);
    trocaColunasVerificacao("txbE8", "txbE9", i);
    trocaColunasVerificacao("txbE7", "txbE8", i);
    trocaColunasVerificacao("txbE6", "txbE7", i);
    trocaColunasVerificacao("txbE5", "txbE6", i);
    trocaColunasVerificacao("txbE4", "txbE5", i);
    trocaColunasVerificacao("txbE3", "txbE4", i);
    trocaColunasVerificacao("txbE2", "txbE3", i);
}

function totalHoras(nomeValor, i) {
    var campoHoras = document.getElementsByName("totalHoras")[i].value;
    var total = converteHora(document.getElementById(nomeValor).value);
    if (campoHoras > total) {
        return document.getElementById("intervaloAcimaAlmoco").value;
    } else {
        return document.getElementById("intervaloAbaixoAlmoco").value;
        ;
    }
}
function calculaMedia(entrada, saida, intervalo, qtSaidas, posIntervalo) { //Função que calcula a média entre 2 campos da digitação para fornecer o resultado
    var e1Valor = converteHora(entrada);
    var s1Valor = converteHora(saida);
    var intervaloValor = converteHora(intervalo);
    var novoS1 = e1Valor + (posIntervalo * ((s1Valor - e1Valor) / qtSaidas));
    var novoE2 = novoS1 + intervaloValor;
    var vetorRetorno = [converteDecimalHora(novoS1), converteDecimalHora(novoE2)];
    return vetorRetorno;
}
function converteHora(hora) { //Função que retorna o total de horas em decimais
    if (hora == "") {
        hora = "00:00";
    }
    var valorSplit = hora.split(":");
    var horas = parseInt(valorSplit[0]) * 60;
    var minutos = parseInt(valorSplit[1]);
    return (horas + minutos) / 60;
}
function converteDecimalHora(decimal) { //Função que retorna o total de horas convertido em horas
    var total = decimal * 60;
    var hora = parseInt(total / 60);
    var minuto = (total - (hora * 60));
    if (minuto < 10) {
        minuto = "0" + minuto;
    }
    if (hora < 10) {
        hora = "0" + hora;
    }
    return hora + ":" + minuto;
} //Funções chamadas para adicionar ou reduzir jornadas
function adRedJornada() {
    if (document.getElementById("radEntrada").checked == true) {
        adRedJornadaEntrada();
    } else {
        adRedJornadaSaida();
    }
}

function adRedJornadaEntrada() {
    var valorAdc = document.getElementById("valorAdc");
    var valorRed = document.getElementById("valorRed");
    var dias = document.getElementsByName("txbE1");
    var valorRad;
    var dataInicial = document.getElementById("dataInicialAdrJornada").value;
    var dataFinal = document.getElementById("dataFinalAdrJornada").value;
    var datas = document.getElementsByName("datatabela");
    var cont = limitaPeriodo(dataInicial, dataFinal);
    var x = 0;
    var one_day = 1000 * 60 * 60 * 24;    // Convert both dates to milliseconds
    var date1_ms = new Date(datas[0].value.substring(3, 4) + "/" + datas[0].value.substring(0, 2) + "/" + datas[0].value.substring(6, 10)).getTime();    // Calculate the difference in milliseconds  
    var date2_ms = new Date(dataInicial.substring(3, 4) + "/" + dataInicial.substring(0, 2) + "/" + dataInicial.substring(6, 10)).getTime();
    var difference_ms = date2_ms - date1_ms;        // Convert back to days and return
    if (difference_ms >= 0) {
        x = Math.round(difference_ms / one_day);
        if (document.getElementById("radValorAdc").checked == true) {
            valorRad = "valorAdc";
        } else {
            valorRad = "valorRed";
        }
        var horaDias = [];
        for (var i = 0; i <= cont; i++) {
            if (valorRad == "valorAdc") {
                horaDias[i + x] = (converteHora(dias[i + x].value) + converteHora(valorAdc.value));
            }
            if (valorRad == "valorRed") {
                horaDias[i + x] = (converteHora(dias[i + x].value) - converteHora(valorRed.value));
            }
            while (horaDias[i + x] >= 24) {
                horaDias[i] -= 24;
            }
            while (horaDias[i + x] < 0) {
                horaDias[i + x] += 24;
            }
            horaDias[i + x] = converteDecimalHora(horaDias[i + x]);
            dias[i + x].value = horaDias[i + x];
        }
    }
}
function adRedJornadaSaida() {
    var valorAdc = document.getElementById("valorAdc");
    var valorRed = document.getElementById("valorRed");
    var diasS1 = document.getElementsByName("txbS1");
    var diasS2 = document.getElementsByName("txbS2");
    var diasS3 = document.getElementsByName("txbS3");
    var diasS4 = document.getElementsByName("txbS4");
    var diasS5 = document.getElementsByName("txbS5");
    var diasS6 = document.getElementsByName("txbS6");
    var diasS7 = document.getElementsByName("txbS7");
    var diasS8 = document.getElementsByName("txbS8");
    var diasS9 = document.getElementsByName("txbS9");
    var diasS10 = document.getElementsByName("txbS10");
    var diasS11 = document.getElementsByName("txbS11");
    var diasS12 = document.getElementsByName("txbS12");
    var diasS13 = document.getElementsByName("txbS13");
    var diasS14 = document.getElementsByName("txbS14");
    var diasS15 = document.getElementsByName("txbS15");
    var valorRad;
    var mensagem = "";
    var dataInicial = document.getElementById("dataInicialAdrJornada").value;
    var dataFinal = document.getElementById("dataFinalAdrJornada").value;
    var datas = document.getElementsByName("datatabela");
    var cont = limitaPeriodo(dataInicial, dataFinal);
    var x = 0;
    var one_day = 1000 * 60 * 60 * 24;    // Convert both dates to milliseconds
    var date1_ms = new Date(datas[0].value.substring(3, 4) + "/" + datas[0].value.substring(0, 2) + "/" + datas[0].value.substring(6, 10)).getTime();    // Calculate the difference in milliseconds  
    var date2_ms = new Date(dataInicial.substring(3, 4) + "/" + dataInicial.substring(0, 2) + "/" + dataInicial.substring(6, 10)).getTime();
    var difference_ms = date2_ms - date1_ms;        // Convert back to days and return
    if (difference_ms >= 0) {
        x = Math.round(difference_ms / one_day);
        if (document.getElementById("radValorAdc").checked == true) {
            valorRad = "valorAdc";
        } else {
            valorRad = "valorRed";
        }
        var horaDias = [];
        var dias = [];
        for (var i = 0; i <= cont; i++) {
            if (diasS15[i + x].value != "") {
                dias[i + x] = diasS15[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS15[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS15[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS14[i + x].value != "") {
                dias[i + x] = diasS14[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS14[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS14[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS13[i + x].value != "") {
                dias[i + x] = diasS13[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS13[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS13[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS12[i + x].value != "") {
                dias[i + x] = diasS12[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS12[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS12[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS11[i + x].value != "") {
                dias[i + x] = diasS11[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS11[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS11[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS10[i + x].value != "") {
                dias[i + x] = diasS10[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS10[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS10[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS9[i + x].value != "") {
                dias[i + x] = diasS9[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS9[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS9[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS8[i + x].value != "") {
                dias[i + x] = diasS8[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS8[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS8[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS7[i + x].value != "") {
                dias[i + x] = diasS7[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS7[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS7[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS6[i + x].value != "") {
                dias[i + x] = diasS6[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS6[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS6[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS5[i + x].value != "") {
                dias[i + x] = diasS5[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS5[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS5[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS4[i + x].value != "") {
                dias[i + x] = diasS4[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS4[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS4[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS3[i + x].value != "") {
                dias[i + x] = diasS3[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS3[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS3[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS2[i + x].value != "") {
                dias[i + x] = diasS2[i + x];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS2[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS2[i + x].value) - converteHora(valorRed.value));
                }
            } else if (diasS1[i + x].value != "") {
                dias[i + x] = diasS1[i];
                if (valorRad == "valorAdc") {
                    horaDias[i + x] = (converteHora(diasS1[i + x].value) + converteHora(valorAdc.value));
                } else {
                    horaDias[i + x] = (converteHora(diasS1[i + x].value) - converteHora(valorRed.value));
                }
            } else {
                mensagem += "Não há valores em nenhuma das saídas da linha " + (i + 1 + x) + ";\n";
            }
            if (horaDias[i + x] == null) {
                while (horaDias[i + x] >= 24) {
                    horaDias[i + x] -= 24;
                }
                while (horaDias[i + x] < 0) {
                    horaDias[i + x] += 24;
                }
            }
            horaDias[i + x] = converteDecimalHora(horaDias[i + x]);
            dias[i + x].value = horaDias[i + x];
        }
        if (mensagem != "") {
            window.alert(mensagem);
        }
    }
}
function habilitaValores(radio) {
    var campoAdc = document.getElementById("valorAdc");
    var campoRed = document.getElementById("valorRed");
    if (radio.value == "valorAdc") {
        campoAdc.disabled = false;
        campoRed.disabled = true;
    } else {
        campoAdc.disabled = true;
        campoRed.disabled = false;
    }
}
function destacaHoras() {
    var alertaHoras = document.getElementById("alertaHoras");
    var totalHoras = document.getElementsByName("totalHoras");
    for (var i = 0; i < totalHoras.length; i++) {
        if (parseInt(alertaHoras.value) < parseInt(totalHoras[i].value)) {
            totalHoras[i].style = "background-color:red";
        } else {
            totalHoras[i].style = "";
        }
    }
}
function importaCartao1() {
    enviar('importaCartao');
    somaHoras();
    enviar('salvarDigitacao');
}
function importaCartao2() {
    enviar('importaCartao2');
    somaHoras();
    enviar('salvarDigitacao');
}

function intervaloMovel(entradaSaida, campoAnterior, campoEntrada, campoSaida, i, erro) {
    if (document.getElementsByName(campoAnterior)[i].value != "" && entradaSaida[0] != "00:00") {
        document.getElementsByName(campoSaida)[i].value = entradaSaida[0];
        document.getElementsByName(campoEntrada)[i].value = entradaSaida[1];
        if (erro == false) {
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }
}

function exibeColunas(num) {
    var e2 = document.getElementsByName("E2");
    var s2 = document.getElementsByName("S2");
    var e3 = document.getElementsByName("E3");
    var s3 = document.getElementsByName("S3");
    var e4 = document.getElementsByName("E4");
    var s4 = document.getElementsByName("S4");
    var e5 = document.getElementsByName("E5");
    var s5 = document.getElementsByName("S5");
    var e6 = document.getElementsByName("E6");
    var s6 = document.getElementsByName("S6");
    var e7 = document.getElementsByName("E7");
    var s7 = document.getElementsByName("S7");
    var e8 = document.getElementsByName("E8");
    var s8 = document.getElementsByName("S8");
    var e9 = document.getElementsByName("E9");
    var s9 = document.getElementsByName("S9");
    var e10 = document.getElementsByName("E10");
    var s10 = document.getElementsByName("S10");
    var e11 = document.getElementsByName("E11");
    var s11 = document.getElementsByName("S11");
    var e12 = document.getElementsByName("E12");
    var s12 = document.getElementsByName("S12");
    var e13 = document.getElementsByName("E13");
    var s13 = document.getElementsByName("S13");
    var e14 = document.getElementsByName("E14");
    var s14 = document.getElementsByName("S14");
    var e15 = document.getElementsByName("E15");
    var s15 = document.getElementsByName("S15");

    ocultaColunaSelecionada(e2, "txbE2");
    ocultaColunaSelecionada(s2, "txbS2");
    ocultaColunaSelecionada(e3, "txbE3");
    ocultaColunaSelecionada(s3, "txbS3");
    ocultaColunaSelecionada(e4, "txbE4");
    ocultaColunaSelecionada(s4, "txbS4");
    ocultaColunaSelecionada(e5, "txbE5");
    ocultaColunaSelecionada(s5, "txbS5");
    ocultaColunaSelecionada(e6, "txbE6");
    ocultaColunaSelecionada(s6, "txbS6");
    ocultaColunaSelecionada(e7, "txbE7");
    ocultaColunaSelecionada(s7, "txbS7");
    ocultaColunaSelecionada(e8, "txbE8");
    ocultaColunaSelecionada(s8, "txbS8");
    ocultaColunaSelecionada(e9, "txbE9");
    ocultaColunaSelecionada(s9, "txbS9");
    ocultaColunaSelecionada(e10, "txbE10");
    ocultaColunaSelecionada(s10, "txbS10");
    ocultaColunaSelecionada(e11, "txbE11");
    ocultaColunaSelecionada(s11, "txbS11");
    ocultaColunaSelecionada(e12, "txbE12");
    ocultaColunaSelecionada(s12, "txbS12");
    ocultaColunaSelecionada(e13, "txbE13");
    ocultaColunaSelecionada(s13, "txbS13");
    ocultaColunaSelecionada(e14, "txbE14");
    ocultaColunaSelecionada(s14, "txbS14");
    ocultaColunaSelecionada(e15, "txbE15");
    ocultaColunaSelecionada(s15, "txbS15");

    switch (num) {
        case "15":
            exibeColunaSelecionada(e15, "txbE15");
            exibeColunaSelecionada(s15, "txbS15");
        case "14":
            exibeColunaSelecionada(e14, "txbE14");
            exibeColunaSelecionada(s14, "txbS14");
        case "13":
            exibeColunaSelecionada(e13, "txbE13");
            exibeColunaSelecionada(s13, "txbS13");
        case "12":
            exibeColunaSelecionada(e12, "txbE12");
            exibeColunaSelecionada(s12, "txbS12");
        case "11":
            exibeColunaSelecionada(e11, "txbE11");
            exibeColunaSelecionada(s11, "txbS11");
        case "10":
            exibeColunaSelecionada(e10, "txbE10");
            exibeColunaSelecionada(s10, "txbS10");
        case "9":
            exibeColunaSelecionada(e9, "txbE9");
            exibeColunaSelecionada(s9, "txbS9");
        case "8":
            exibeColunaSelecionada(e8, "txbE8");
            exibeColunaSelecionada(s8, "txbS8");
        case "7":
            exibeColunaSelecionada(e7, "txbE7");
            exibeColunaSelecionada(s7, "txbS7");
        case "6":
            exibeColunaSelecionada(e6, "txbE6");
            exibeColunaSelecionada(s6, "txbS6");
        case "5":
            exibeColunaSelecionada(e5, "txbE5");
            exibeColunaSelecionada(s5, "txbS5");
        case "4":
            exibeColunaSelecionada(e4, "txbE4");
            exibeColunaSelecionada(s4, "txbS4");
        case "3":
            exibeColunaSelecionada(e3, "txbE3");
            exibeColunaSelecionada(s3, "txbS3");
        case "2":
            exibeColunaSelecionada(e2, "txbE2");
            exibeColunaSelecionada(s2, "txbS2");
            break;
    }
}

function exibeColunaSelecionada(coluna, campos) {
    apareceColunas(document.getElementsByName(campos));
    for (var i = 0; i < coluna.length; i++) {
        coluna[i].style.display = "";
    }
}

function ocultaColunaSelecionada(coluna, campos) {
    apagaColunas(document.getElementsByName(campos));
    for (var i = 0; i < coluna.length; i++) {
        coluna[i].style.display = "none";
    }
}

function apagaColunas(colunas) {
    for (var i = 0; i < colunas.length; i++) {
        colunas[i].disabled = true;
    }
}

function apareceColunas(colunas) {
    for (var i = 0; i < colunas.length; i++) {
        colunas[i].disabled = false;
    }
}

function buscaPagina() {
    habilitaCampos();
    enviar('buscarPagina');
}

function proximaPagina() {
    habilitaCampos();
    enviar('aumentarIndex');
}

function anteriorPagina() {
    habilitaCampos();
    enviar('reduzirIndex');
}

function apagaPeriodo() {
    var dataInicial = document.getElementById("diaInicialLimpaPeriodo").value;
    var dataFinal = document.getElementById("diaFinalLimpaPeriodo").value;
    var datatabela = document.getElementsByName("datatabela");
    var posicaoInicial = -1;
    var posicaoFinal = -1;
    var i = 0;
    while (i < datatabela.length && posicaoInicial == -1) {
        if (datatabela[i].value.substring(0, datatabela[i].value.length - 4) == dataInicial) {
            posicaoInicial = i;
        }
        i++;
    }
    while (i < datatabela.length && posicaoFinal == -1) {
        if (datatabela[i].value.substring(0, datatabela[i].value.length - 4) == dataFinal) {
            posicaoFinal = i;
        }
        i++;
    }
    if (posicaoInicial > -1 && posicaoFinal > -1) {
        limpaElemento("txbE1", posicaoInicial, posicaoFinal);
        limpaElemento("txbS1", posicaoInicial, posicaoFinal);
        limpaElemento("txbE2", posicaoInicial, posicaoFinal);
        limpaElemento("txbS2", posicaoInicial, posicaoFinal);
        limpaElemento("txbE3", posicaoInicial, posicaoFinal);
        limpaElemento("txbS3", posicaoInicial, posicaoFinal);
        limpaElemento("txbE4", posicaoInicial, posicaoFinal);
        limpaElemento("txbS4", posicaoInicial, posicaoFinal);
        limpaElemento("txbE5", posicaoInicial, posicaoFinal);
        limpaElemento("txbS5", posicaoInicial, posicaoFinal);
        limpaElemento("txbE6", posicaoInicial, posicaoFinal);
        limpaElemento("txbS6", posicaoInicial, posicaoFinal);
        limpaElemento("txbE7", posicaoInicial, posicaoFinal);
        limpaElemento("txbS7", posicaoInicial, posicaoFinal);
        limpaElemento("txbE8", posicaoInicial, posicaoFinal);
        limpaElemento("txbS8", posicaoInicial, posicaoFinal);
        limpaElemento("txbE9", posicaoInicial, posicaoFinal);
        limpaElemento("txbS9", posicaoInicial, posicaoFinal);
        limpaElemento("txbE10", posicaoInicial, posicaoFinal);
        limpaElemento("txbS10", posicaoInicial, posicaoFinal);
        limpaElemento("txbE11", posicaoInicial, posicaoFinal);
        limpaElemento("txbS11", posicaoInicial, posicaoFinal);
        limpaElemento("txbE12", posicaoInicial, posicaoFinal);
        limpaElemento("txbS12", posicaoInicial, posicaoFinal);
        limpaElemento("txbE13", posicaoInicial, posicaoFinal);
        limpaElemento("txbS13", posicaoInicial, posicaoFinal);
        limpaElemento("txbE14", posicaoInicial, posicaoFinal);
        limpaElemento("txbS14", posicaoInicial, posicaoFinal);
        limpaElemento("txbE15", posicaoInicial, posicaoFinal);
        limpaElemento("txbS15", posicaoInicial, posicaoFinal);
    }
}

function limpaElemento(elemento, inicio, fim) {
    var campo = document.getElementsByName(elemento);
    for (var i = inicio; i <= fim; i++) {
        campo[i].value = "";
    }
}

function verificaSoma(entrada, saida) {
    var horasEntrada = entrada.split(":")[0], minutosEntrada = entrada.split(":")[1],
            horasSaida = saida.split(":")[0], minutosSaida = saida.split(":")[1], totalHoras = 0, totalMinutos = 0;
    if (minutosEntrada == null || minutosSaida == null) {
        return 0;
    }
    if (horasEntrada < horasSaida || (horasEntrada == horasSaida && minutosEntrada <= minutosSaida)) {
        totalHoras = horasSaida - horasEntrada;
        totalMinutos = minutosSaida - minutosEntrada;
    } else {
        if (minutosEntrada > minutosSaida) {
            totalHoras = ((24 - horasEntrada) + parseInt(horasSaida)) - 1;
            totalMinutos = (60 - minutosEntrada) + parseInt(minutosSaida);
        } else {
            totalHoras = (24 - horasEntrada) + parseInt(horasSaida);
            totalMinutos = parseInt(minutosSaida) - parseInt(minutosEntrada);
        }
    }
    if (totalMinutos < 0) {
        totalHoras -= 1;
        totalMinutos += 60;
    }
    if (totalHoras < 0) {
        totalHoras = 0;
    }

    return (((totalHoras * 60) + totalMinutos) / 60);
}

function copiaExcel() {
    var entrada = 0, saida = 0, i = 0, total = 0;
    var dias = document.getElementsByName("txbE1");
    var valor = document.getElementById("textoCopiaExcel").value;
    var linha = valor.split("\n");
    while (i < dias.length && i < linha.length) {
        var campo = linha[i].split("\t");
        for (var j = 0; j < campo.length; j++) {
            if (campo[j].length == 4) {
                campo[j] = "0" + campo[j];
            }
            if (campo[j] == "") {
                campo[j] = "00:00";
            }
        }
        switch (campo.length) {
            case 30:
            {
                document.getElementsByName("txbS15")[i].value = campo[29];
                saida = campo[29];
            }
            case 29:
            {
                document.getElementsByName("txbE15")[i].value = campo[28];
                entrada = campo[28];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 28:
            {
                document.getElementsByName("txbS14")[i].value = campo[27];
                saida = campo[27];
            }
            case 27:
            {
                document.getElementsByName("txbE14")[i].value = campo[26];
                entrada = campo[26];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 26:
            {
                document.getElementsByName("txbS13")[i].value = campo[25];
                saida = campo[25];
            }
            case 25:
            {
                document.getElementsByName("txbE13")[i].value = campo[24];
                entrada = campo[24];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 24:
            {
                document.getElementsByName("txbS12")[i].value = campo[23];
                saida = campo[23];
            }
            case 23:
            {
                document.getElementsByName("txbE12")[i].value = campo[22];
                entrada = campo[22];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 22:
            {
                document.getElementsByName("txbS11")[i].value = campo[21];
                saida = campo[21];
            }
            case 21:
            {
                document.getElementsByName("txbE11")[i].value = campo[20];
                entrada = campo[20];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 20:
            {
                document.getElementsByName("txbS10")[i].value = campo[19];
                saida = campo[19];
            }
            case 19:
            {
                document.getElementsByName("txbE10")[i].value = campo[18];
                entrada = campo[18];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 18:
            {
                document.getElementsByName("txbS9")[i].value = campo[17];
                saida = campo[17];
            }
            case 17:
            {
                document.getElementsByName("txbE9")[i].value = campo[16];
                entrada = campo[16];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 16:
            {
                document.getElementsByName("txbS8")[i].value = campo[15];
                saida = campo[15];
            }
            case 15:
            {
                document.getElementsByName("txbE8")[i].value = campo[14];
                entrada = campo[14];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 14:
            {
                document.getElementsByName("txbS7")[i].value = campo[13];
                saida = campo[13];
            }
            case 13:
            {
                document.getElementsByName("txbE7")[i].value = campo[12];
                entrada = campo[12];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 12:
            {
                document.getElementsByName("txbS6")[i].value = campo[11];
                saida = campo[11];
            }
            case 11:
            {
                document.getElementsByName("txbE6")[i].value = campo[10];
                entrada = campo[10];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 10:
            {
                document.getElementsByName("txbS5")[i].value = campo[9];
                saida = campo[9];
            }
            case 9:
            {
                document.getElementsByName("txbE5")[i].value = campo[8];
                entrada = campo[8];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 8:
            {
                document.getElementsByName("txbS4")[i].value = campo[7];
                saida = campo[7];
            }
            case 7:
            {
                document.getElementsByName("txbE4")[i].value = campo[6];
                entrada = campo[6];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 6:
            {
                document.getElementsByName("txbS3")[i].value = campo[5];
                saida = campo[5];
            }
            case 5:
            {
                document.getElementsByName("txbE3")[i].value = campo[4];
                entrada = campo[4];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 4:
            {
                document.getElementsByName("txbS2")[i].value = campo[3];
                saida = campo[3];
            }
            case 3:
            {
                document.getElementsByName("txbE2")[i].value = campo[2];
                entrada = campo[2];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 2:
            {
                document.getElementsByName("txbS1")[i].value = campo[1];
                saida = campo[1];
            }
            case 1:
                {
                    document.getElementsByName("txbE1")[i].value = campo[0];
                    entrada = campo[0];
                    total += parseFloat(verificaSoma(entrada, saida));
                }
                break;
        }
        document.getElementsByName("totalHoras")[i].value = total;
        total = 0;
        i++;
    }
    fechaJanela('modalcopiaexcel');
}

function copiaCartaoSada() {
    var entrada = 0, saida = 0, i = 0, total = 0;
    var dias = document.getElementsByName("txbE1");
    var valor = document.getElementById("textoCopiaSada").value;
    var linha = valor.split("\n");
    var dataInicial = document.getElementById("diaInicialCopiaSada").value;
    var datatabela = document.getElementsByName("datatabela");
    var i = 0;
    while (i < dias.length && i < linha.length) {
        linha[i] = linha[i].substring(15, linha[i].length);
        var campo = linha[i].split(" o ");
        for (var j = 0; j < campo.length; j++) {
            campo[j]=campo[j].replace(" ","");
            campo[j]=campo[j].replace("o","");
        }
        switch (campo.length) {
            case 30:
            {
                document.getElementsByName("txbS15")[i].value = campo[29];
                saida = campo[29];
            }
            case 29:
            {
                document.getElementsByName("txbE15")[i].value = campo[28];
                entrada = campo[28];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 28:
            {
                document.getElementsByName("txbS14")[i].value = campo[27];
                saida = campo[27];
            }
            case 27:
            {
                document.getElementsByName("txbE14")[i].value = campo[26];
                entrada = campo[26];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 26:
            {
                document.getElementsByName("txbS13")[i].value = campo[25];
                saida = campo[25];
            }
            case 25:
            {
                document.getElementsByName("txbE13")[i].value = campo[24];
                entrada = campo[24];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 24:
            {
                document.getElementsByName("txbS12")[i].value = campo[23];
                saida = campo[23];
            }
            case 23:
            {
                document.getElementsByName("txbE12")[i].value = campo[22];
                entrada = campo[22];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 22:
            {
                document.getElementsByName("txbS11")[i].value = campo[21];
                saida = campo[21];
            }
            case 21:
            {
                document.getElementsByName("txbE11")[i].value = campo[20];
                entrada = campo[20];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 20:
            {
                document.getElementsByName("txbS10")[i].value = campo[19];
                saida = campo[19];
            }
            case 19:
            {
                document.getElementsByName("txbE10")[i].value = campo[18];
                entrada = campo[18];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 18:
            {
                document.getElementsByName("txbS9")[i].value = campo[17];
                saida = campo[17];
            }
            case 17:
            {
                document.getElementsByName("txbE9")[i].value = campo[16];
                entrada = campo[16];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 16:
            {
                document.getElementsByName("txbS8")[i].value = campo[15];
                saida = campo[15];
            }
            case 15:
            {
                document.getElementsByName("txbE8")[i].value = campo[14];
                entrada = campo[14];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 14:
            {
                document.getElementsByName("txbS7")[i].value = campo[13];
                saida = campo[13];
            }
            case 13:
            {
                document.getElementsByName("txbE7")[i].value = campo[12];
                entrada = campo[12];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 12:
            {
                document.getElementsByName("txbS6")[i].value = campo[11];
                saida = campo[11];
            }
            case 11:
            {
                document.getElementsByName("txbE6")[i].value = campo[10];
                entrada = campo[10];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 10:
            {
                document.getElementsByName("txbS5")[i].value = campo[9];
                saida = campo[9];
            }
            case 9:
            {
                document.getElementsByName("txbE5")[i].value = campo[8];
                entrada = campo[8];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 8:
            {
                document.getElementsByName("txbS4")[i].value = campo[7];
                saida = campo[7];
            }
            case 7:
            {
                document.getElementsByName("txbE4")[i].value = campo[6];
                entrada = campo[6];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 6:
            {
                document.getElementsByName("txbS3")[i].value = campo[5];
                saida = campo[5];
            }
            case 5:
            {
                document.getElementsByName("txbE3")[i].value = campo[4];
                entrada = campo[4];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 4:
            {
                document.getElementsByName("txbS2")[i].value = campo[3];
                saida = campo[3];
            }
            case 3:
            {
                document.getElementsByName("txbE2")[i].value = campo[2];
                entrada = campo[2];
                total += parseFloat(verificaSoma(entrada, saida));
            }
            case 2:
            {
                document.getElementsByName("txbS1")[i].value = campo[1];
                saida = campo[1];
            }
            case 1:
                {
                    document.getElementsByName("txbE1")[i].value = campo[0];
                    entrada = campo[0];
                    total += parseFloat(verificaSoma(entrada, saida));
                }
                break;
        }
        document.getElementsByName("totalHoras")[i].value = total;
        total = 0;
        i++;
    }
    fechaJanela('modalcopiacartaosada');
}

function mudarModal(modalfechado, modalaberto) {
    fechaJanela(modalfechado);
    abreJanela(modalaberto);
}