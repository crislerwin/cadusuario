/**
 * Controller.js - Lógica do Formulário de Cadastro
 * Responsável por:
 * - Validação de campos com jQuery Validation
 * - Consumo da API ViaCEP para preenchimento automático de endereço
 * - Manipulação de eventos e feedback visual
 */

$(document).ready(function () {
  // ============================================
  // CONFIGURAÇÕES INICIAIS
  // ============================================

  const API_VIACEP = "https://viacep.com.br/ws";
  const API_BACKEND = "http://localhost:8080/api"; // API Spring Boot
  const TIMEOUT_API = 5000; // 5 segundos

  // ============================================
  // VALIDAÇÃO DO FORMULÁRIO COM JQUERY VALIDATION
  // ============================================

  $("#cadastroForm").validate({
    rules: {
      nome: {
        required: true,
        minlength: 3,
        pattern: /^[a-zA-ZáéíóúãõâêôçÁÉÍÓÚÃÕÂÊÔÇ\s]+$/,
      },
      sobrenome: {
        required: true,
        minlength: 3,
        pattern: /^[a-zA-ZáéíóúãõâêôçÁÉÍÓÚÃÕÂÊÔÇ\s]+$/,
      },
      email: {
        required: true,
        email: true,
      },
      senha: {
        required: true,
        minlength: 6,
      },
      cep: {
        required: true,
        pattern: /^\d{5}-?\d{3}$/,
      },
      rua: {
        required: true,
        minlength: 3,
      },
      numero: {
        required: true,
        pattern: /^\d+$/,
      },
      bairro: {
        required: true,
        minlength: 2,
      },
      cidade: {
        required: true,
        minlength: 2,
      },
      estado: {
        required: true,
        pattern: /^[A-Z]{2}$/,
      },
    },
    messages: {
      nome: {
        required: "Nome é obrigatório",
        minlength: "Nome deve ter no mínimo 3 caracteres",
        pattern: "Nome deve conter apenas letras",
      },
      sobrenome: {
        required: "Sobrenome é obrigatório",
        minlength: "Sobrenome deve ter no mínimo 3 caracteres",
        pattern: "Sobrenome deve conter apenas letras",
      },
      email: {
        required: "Email é obrigatório",
        email: "Por favor, insira um email válido",
      },
      senha: {
        required: "Senha é obrigatória",
        minlength: "Senha deve ter no mínimo 6 caracteres",
      },
      cep: {
        required: "CEP é obrigatório",
        pattern: "CEP deve estar no formato 00000-000",
      },
      rua: {
        required: "Rua é obrigatória",
        minlength: "Rua deve ter no mínimo 3 caracteres",
      },
      numero: {
        required: "Número é obrigatório",
        pattern: "Número deve conter apenas dígitos",
      },
      bairro: {
        required: "Bairro é obrigatório",
        minlength: "Bairro deve ter no mínimo 2 caracteres",
      },
      cidade: {
        required: "Cidade é obrigatória",
        minlength: "Cidade deve ter no mínimo 2 caracteres",
      },
      estado: {
        required: "Estado é obrigatório",
        pattern: "Estado deve ser a sigla (ex: SP, RJ, MG)",
      },
    },
    errorElement: "span",
    errorClass: "invalid-feedback",
    errorPlacement: function (error, element) {
      // Inserir erro após o input-group ou após o input
      if (element.parent().hasClass("input-group")) {
        error.insertAfter(element.parent());
      } else {
        error.insertAfter(element);
      }
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass("is-invalid").removeClass("is-valid");
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass("is-invalid").addClass("is-valid");
    },
    // Não validar enquanto o usuário digita, apenas ao submeter
    onkeyup: false,
    onfocusout: false,
    onclick: false,
    submitHandler: function (form) {
      handleSubmit(form);
    },
  });

  // ============================================
  // VALIDAÇÃO CUSTOMIZADA PARA PADRÕES
  // ============================================

  $.validator.addMethod(
    "pattern",
    function (value, element, param) {
      if (!value) return true;
      return param.test(value);
    },
    "Formato inválido",
  );

  // ============================================
  // TOGGLE DE VISIBILIDADE DE SENHA
  // ============================================

  $("#toggleSenha").on("click", function () {
    const senhaInput = $("#senha");
    const icon = $(this).find("i");

    if (senhaInput.attr("type") === "password") {
      senhaInput.attr("type", "text");
      icon.removeClass("fa-eye").addClass("fa-eye-slash");
    } else {
      senhaInput.attr("type", "password");
      icon.removeClass("fa-eye-slash").addClass("fa-eye");
    }
  });

  // ============================================
  // FORMATAÇÃO DO CEP
  // ============================================

  $("#cep").on("input", function () {
    let valor = $(this).val().replace(/\D/g, "");

    if (valor.length > 5) {
      valor = valor.substring(0, 5) + "-" + valor.substring(5, 8);
    }

    $(this).val(valor);
  });

  // ============================================
  // BUSCAR CEP NA API VIACEP
  // ============================================

  $("#buscarCep").on("click", function () {
    const cep = $("#cep").val().replace(/\D/g, "");

    // Validar CEP
    if (!cep || cep.length !== 8) {
      mostrarAlerta(
        "CEP inválido. Por favor, insira um CEP com 8 dígitos.",
        "erro",
      );
      return;
    }

    buscarCepViacep(cep);
  });

  // Permitir buscar CEP ao pressionar Enter no campo
  $("#cep").on("keypress", function (e) {
    if (e.which === 13) {
      e.preventDefault();
      $("#buscarCep").click();
    }
  });

  // ============================================
  // FUNÇÕES AUXILIARES
  // ============================================

  /**
   * Busca dados de endereço na API ViaCEP
   * @param {string} cep - CEP sem formatação
   */
  function buscarCepViacep(cep) {
    const botao = $("#buscarCep");
    const iconeBotao = botao.find("i");

    // Desabilitar botão e mostrar loading
    botao.prop("disabled", true);
    iconeBotao.removeClass("fa-search").addClass("fa-spinner fa-spin");

    // Limpar alertas anteriores
    ocultarAlerta();

    // Fazer requisição à API
    $.ajax({
      url: `${API_VIACEP}/${cep}/json/`,
      type: "GET",
      timeout: TIMEOUT_API,
      dataType: "json",
      success: function (data) {
        if (data.erro) {
          mostrarAlerta(
            "CEP não encontrado. Por favor, verifique o número.",
            "erro",
          );
          limparCamposEndereco();
        } else {
          preencherCamposEndereco(data);
          mostrarAlerta("Endereço encontrado com sucesso!", "sucesso");
        }
      },
      error: function (xhr, status, error) {
        console.error("Erro ao buscar CEP:", error);
        mostrarAlerta(
          "Erro ao buscar CEP. Verifique sua conexão e tente novamente.",
          "erro",
        );
        limparCamposEndereco();
      },
      complete: function () {
        // Reabilitar botão e restaurar ícone
        botao.prop("disabled", false);
        iconeBotao.removeClass("fa-spinner fa-spin").addClass("fa-search");
      },
    });
  }

  /**
   * Preenche os campos de endereço com os dados da API
   * @param {object} data - Dados retornados pela API ViaCEP
   */
  function preencherCamposEndereco(data) {
    $("#rua")
      .val(data.logradouro || "")
      .removeClass("is-invalid")
      .addClass("is-valid");
    $("#bairro")
      .val(data.bairro || "")
      .removeClass("is-invalid")
      .addClass("is-valid");
    $("#cidade")
      .val(data.localidade || "")
      .removeClass("is-invalid")
      .addClass("is-valid");
    $("#estado")
      .val(data.uf || "")
      .removeClass("is-invalid")
      .addClass("is-valid");

    // Focar no campo de número para o usuário continuar preenchendo
    $("#numero").focus();
  }

  /**
   * Limpa os campos de endereço
   */
  function limparCamposEndereco() {
    $("#rua").val("").removeClass("is-valid is-invalid");
    $("#bairro").val("").removeClass("is-valid is-invalid");
    $("#cidade").val("").removeClass("is-valid is-invalid");
    $("#estado").val("").removeClass("is-valid is-invalid");
  }

  /**
   * Valida se um email é válido
   * @param {string} email - Email a validar
   * @returns {boolean} - True se válido, false caso contrário
   */
  function isValidEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  }

  /**
   * Mostra alerta de sucesso ou erro
   * @param {string} mensagem - Mensagem a exibir
   * @param {string} tipo - Tipo de alerta ('sucesso' ou 'erro')
   */
  function mostrarAlerta(mensagem, tipo) {
    if (tipo === "erro") {
      $("#mensagemErro").text(mensagem);
      $("#alertaErro").removeClass("d-none").fadeIn();
      $("#alertaSucesso").addClass("d-none");
    } else if (tipo === "sucesso") {
      $("#alertaSucesso").removeClass("d-none").fadeIn();
      $("#alertaErro").addClass("d-none");
    }
  }

  /**
   * Oculta todos os alertas
   */
  function ocultarAlerta() {
    $("#alertaErro").addClass("d-none");
    $("#alertaSucesso").addClass("d-none");
  }

  /**
   * Manipula o envio do formulário
   * @param {object} form - Elemento do formulário
   */
  function handleSubmit(form) {
    // Coletar dados do formulário
    const dados = {
      nome: $("#nome").val(),
      sobrenome: $("#sobrenome").val(),
      email: $("#email").val(),
      senha: $("#senha").val(),
      cep: $("#cep").val(),
      rua: $("#rua").val(),
      numero: $("#numero").val(),
      complemento: $("#complemento").val(),
      bairro: $("#bairro").val(),
      cidade: $("#cidade").val(),
      estado: $("#estado").val(),
    };

    console.log("Enviando dados para a API:", dados);

    // Enviar dados para a API Spring Boot
    enviarDadosParaAPI(dados, form);

    // Retornar false para evitar envio padrão do formulário
    return false;
  }

  /**
   * Envia os dados do cadastro para a API Spring Boot
   * @param {object} dados - Dados do formulário
   * @param {object} form - Elemento do formulário
   */
  function enviarDadosParaAPI(dados, form) {
    const botaoCadastrar = $('button[type="submit"]');
    const iconeBotao = botaoCadastrar.find("i");

    // Desabilitar botão e mostrar loading
    botaoCadastrar.prop("disabled", true);
    if (iconeBotao.length) {
      iconeBotao.removeClass("fa-user-plus").addClass("fa-spinner fa-spin");
    }

    // Fazer requisição à API
    $.ajax({
      url: `${API_BACKEND}/usuarios`,
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(dados),
      timeout: TIMEOUT_API,
      dataType: "json",
      success: function (response) {
        console.log("Cadastro realizado com sucesso:", response);
        mostrarAlerta(
          "Cadastro realizado com sucesso! ID: " + response.id,
          "sucesso",
        );

        // Limpar formulário após 2 segundos
        setTimeout(function () {
          form.reset();
          $("#cadastroForm").validate().resetForm();
          $("input, textarea").removeClass("is-valid is-invalid");
          ocultarAlerta();
        }, 2000);
      },
      error: function (xhr, status, error) {
        console.error("Erro ao cadastrar usuário:", error);

        // Tratar diferentes tipos de erro
        let mensagemErro = "Erro ao cadastrar usuário. Tente novamente.";

        if (xhr.status === 400) {
          // Erro de validação
          const erros = xhr.responseJSON;
          if (typeof erros === "object") {
            // Se for um objeto com erros de validação
            if (erros.erro) {
              mensagemErro = erros.erro;
            } else {
              // Construir mensagem com todos os erros
              mensagemErro = Object.values(erros).join(", ");
            }
          }
        } else if (xhr.status === 500) {
          mensagemErro = "Erro no servidor. Tente novamente mais tarde.";
        } else if (status === "timeout") {
          mensagemErro = "Tempo limite excedido. Verifique sua conexão.";
        }

        mostrarAlerta(mensagemErro, "erro");
      },
      complete: function () {
        // Reabilitar botão e restaurar ícone
        botaoCadastrar.prop("disabled", false);
        if (iconeBotao.length) {
          iconeBotao.removeClass("fa-spinner fa-spin").addClass("fa-user-plus");
        }
      },
    });
  }

  // ============================================
  // INICIALIZAÇÃO
  // ============================================

  console.log("Formulário de Cadastro inicializado com sucesso!");
  console.log("API ViaCEP: " + API_VIACEP);
});
