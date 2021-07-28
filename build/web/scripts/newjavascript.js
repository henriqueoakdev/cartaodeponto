function almocoFixoVerificacao(intervalo) {
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
    var horario = document.getElementById("horarioFixo").checked;
    var resposta;
    var mensagem = "";
    switch (intervalo) {
        case "1":
            {
                if (horario) {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
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
                            s1[i].value = document.getElementById("saidaAlmoco").value;
                            e2[i].value = document.getElementById("retornoAlmoco").value;
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                } else {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
                            resposta = totalHoras("horasAlmoco", i);
                            var entradaSaida = calculaMedia(e1[i].value, s1[i].value, resposta);
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
                            if (document.getElementsByName("txbE1")[i].value != "" && entradaSaida[0] != "00:00" && entradaSaida[0] != ":") {
                                document.getElementsByName("txbS1")[i].value = entradaSaida[0];
                                document.getElementsByName("txbE2")[i].value = entradaSaida[1];
                            }
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                }
            }
            break;

        case "2":
            {
                if (horario) {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
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
                            s2[i].value = document.getElementById("saidaAlmoco").value;
                            e3[i].value = document.getElementById("retornoAlmoco").value;
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                } else {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
                            resposta = totalHoras("horasAlmoco", i);
                            var entradaSaida = calculaMedia(e1[i].value, s1[i].value, resposta);
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
                            if (document.getElementsByName("txbE2")[i].value != "" && entradaSaida[0] != "00:00" && entradaSaida[0] != ":") {
                                document.getElementsByName("txbS2")[i].value = entradaSaida[0];
                                document.getElementsByName("txbE3")[i].value = entradaSaida[1];
                            }
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                }
            }
            break;

        case "3":
            {
                if (horario) {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
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
                            s3[i].value = document.getElementById("saidaAlmoco").value;
                            e4[i].value = document.getElementById("retornoAlmoco").value;
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                } else {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
                            resposta = totalHoras("horasAlmoco", i);
                            var entradaSaida = calculaMedia(e1[i].value, s1[i].value, resposta);
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
                            if (document.getElementsByName("txbE3")[i].value != "" && entradaSaida[0] != "00:00" && entradaSaida[0] != ":") {
                                document.getElementsByName("txbS3")[i].value = entradaSaida[0];
                                document.getElementsByName("txbE4")[i].value = entradaSaida[1];
                            }
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                }
            }
            break;

        case "4":
            {
                if (horario) {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
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
                            s4[i].value = document.getElementById("saidaAlmoco").value;
                            e5[i].value = document.getElementById("retornoAlmoco").value;
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                } else {
                    for (var i = 0; i < s1.length; i++) {
                        if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
                            resposta = totalHoras("horasAlmoco", i);
                            var entradaSaida = calculaMedia(e1[i].value, s1[i].value, resposta);
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
                            if (document.getElementsByName("txbE4")[i].value != "" && entradaSaida[0] != "00:00" && entradaSaida[0] != ":") {
                                document.getElementsByName("txbS4")[i].value = entradaSaida[0];
                                document.getElementsByName("txbE5")[i].value = entradaSaida[1];
                            }
                        } else {
                            mensagem += (i + 1) + "; ";
                        }
                    }
                    if (mensagem != "") {
                        window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                    }
                }
            }
            break;

        case "5":
        {
            if (horario) {
                for (var i = 0; i < s1.length; i++) {
                    if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
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
                        trocaColunasVerificacao("txbE14", "txbE15", i);
                        trocaColunasVerificacao("txbE13", "txbE14", i);
                        trocaColunasVerificacao("txbE12", "txbE13", i);
                        trocaColunasVerificacao("txbE11", "txbE12", i);
                        trocaColunasVerificacao("txbE10", "txbE11", i);
                        trocaColunasVerificacao("txbE9", "txbE10", i);
                        trocaColunasVerificacao("txbE8", "txbE9", i);
                        trocaColunasVerificacao("txbE7", "txbE8", i);
                        trocaColunasVerificacao("txbE6", "txbE7", i);
                        s5[i].value = document.getElementById("saidaAlmoco").value;
                        e6[i].value = document.getElementById("retornoAlmoco").value;
                    } else {
                        mensagem += (i + 1) + "; ";
                    }
                }
                if (mensagem != "") {
                    window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                }
            } else {
                for (var i = 0; i < s1.length; i++) {
                    if (document.getElementsByName("txbS14")[i].value == "" && document.getElementsByName("txbE15")[i].value == "") {
                        resposta = totalHoras("horasAlmoco", i);
                        var entradaSaida = calculaMedia(e1[i].value, s1[i].value, resposta);
                        trocaColunasVerificacao("txbS13", "txbS14", i);
                        trocaColunasVerificacao("txbS12", "txbS13", i);
                        trocaColunasVerificacao("txbS11", "txbS12", i);
                        trocaColunasVerificacao("txbS10", "txbS11", i);
                        trocaColunasVerificacao("txbS9", "txbS10", i);
                        trocaColunasVerificacao("txbS8", "txbS9", i);
                        trocaColunasVerificacao("txbS7", "txbS8", i);
                        trocaColunasVerificacao("txbS6", "txbS7", i);
                        trocaColunasVerificacao("txbS5", "txbS6", i);
                        trocaColunasVerificacao("txbE14", "txbE15", i);
                        trocaColunasVerificacao("txbE13", "txbE14", i);
                        trocaColunasVerificacao("txbE12", "txbE13", i);
                        trocaColunasVerificacao("txbE11", "txbE12", i);
                        trocaColunasVerificacao("txbE10", "txbE11", i);
                        trocaColunasVerificacao("txbE9", "txbE10", i);
                        trocaColunasVerificacao("txbE8", "txbE9", i);
                        trocaColunasVerificacao("txbE7", "txbE8", i);
                        trocaColunasVerificacao("txbE6", "txbE7", i);
                        if (document.getElementsByName("txbE5")[i].value != "" && entradaSaida[0] != "00:00" && entradaSaida[0] != ":") {
                            document.getElementsByName("txbS5")[i].value = entradaSaida[0];
                            document.getElementsByName("txbE6")[i].value = entradaSaida[1];
                        }
                    } else {
                        mensagem += (i + 1) + "; ";
                    }
                }
                if (mensagem != "") {
                    window.alert("As colunas S14 e E15 estão preenchidas na(s) linha(s): " + mensagem);
                }
            }
        }
    }
}
