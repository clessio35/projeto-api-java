package projeto.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projeto.api.pages.ReqresPage;

public class ReqresStep {
	
	private ReqresPage req = new ReqresPage();

	@Given("que acesso a API {string}")
	public void que_acesso_a_api(String url) {
		req.accessApi(url);
	}
	
	@When("realizo uma request GET para {string}")
	public void realizo_uma_request_get_para(String endpoint) {
		 req.requestGETMethod(endpoint);
	}

	@Then("eu valido que a resposta contém a lista de usuários")
	public void eu_valido_que_a_resposta_contém_a_lista_de_usuários() {
	     req.validateResponseUserList();     
	}
	
	@Then("eu valido a resposta da página {string} com a lista de usuários")
	public void eu_valido_a_resposta_da_página_com_a_lista_de_usuários(String page) {
		req.validateResponseUserPage(page);
	}

	@Then("eu valido os dados do usuário específico {string}")
	public void eu_valido_os_dados_do_usuário_específico(String user) {
	     req.validateResponseSpecificUser(user);
	}

	@Then("eu valido que o erro retornado tem o status code {string}")
	public void eu_valido_que_o_erro_retornado_tem_o_status_code(String status) {
		req.validateResponseInexistanceUser(status);
	}

	@When("realizo uma request POST para {string}")
	public void realizo_uma_request_post_para(String endpoint) {
		req.requestPOSTMethod(endpoint);
	}

	@Then("eu valido que a criação foi bem-sucedida com status code {string} e os dados corretos")
	public void eu_valido_que_a_criação_foi_bem_sucedida_com_status_code_e_os_dados_corretos(String status) {
		req.validateResponseUserCreated(status);
	}

	@Then("eu valido que o erro retornado tem status code {string} e a mensagem {string}")
	public void eu_valido_que_o_erro_retornado_tem_status_code_e_a_mensagem(String string, String string2) {
	     
	     
	}

	@When("realizo uma request PUT para {string}")
	public void realizo_uma_request_put_para(String string) {
	     
	     
	}

	@Then("eu valido que os dados do usuário foram atualizados com status code {string}")
	public void eu_valido_que_os_dados_do_usuário_foram_atualizados_com_status_code(String string) {
	     
	     
	}

	@Then("eu valido que o erro retornado tem status code {string} e a mensagem \"\"O campo {string} é obrigatório\"\"")
	public void eu_valido_que_o_erro_retornado_tem_status_code_e_a_mensagem_o_campo_é_obrigatório(String string, String string2) {
	     
	     
	}

	@When("realizo uma request DELETE para {string}")
	public void realizo_uma_request_delete_para(String string) {
	     
	     
	}

	@Then("eu valido que o status code é {string} e a resposta não contém conteúdo")
	public void eu_valido_que_o_status_code_é_e_a_resposta_não_contém_conteúdo(String string) {
	     
	     
	}

	@Then("eu valido que o erro retornado tem status code {string} e a mensagem \"\"Usuário não encontrado\"\"")
	public void eu_valido_que_o_erro_retornado_tem_status_code_e_a_mensagem_usuário_não_encontrado(String string) {
	     
	     
	}

	@Then("eu valido que o status code é {string} e a resposta contém um token válido")
	public void eu_valido_que_o_status_code_é_e_a_resposta_contém_um_token_válido(String string) {
	     
	     
	}

	@Then("eu valido que o erro retornado tem status code {string} e a mensagem \"\"Credenciais ausentes\"\"")
	public void eu_valido_que_o_erro_retornado_tem_status_code_e_a_mensagem_credenciais_ausentes(String string) {
	     
	     
	}

	@Then("eu valido que o erro retornado tem status code {string} e a mensagem \"\"Os campos {string} e {string} são obrigatórios\"\"")
	public void eu_valido_que_o_erro_retornado_tem_status_code_e_a_mensagem_os_campos_e_são_obrigatórios(String string, String string2, String string3) {
	     
	     
	}

	@Then("eu valido que a lista de usuários foi retornada corretamente e paginada")
	public void eu_valido_que_a_lista_de_usuários_foi_retornada_corretamente_e_paginada() {
	     
	     
	}

	@Then("eu valido que o status code é {string} e os dados estão corretos")
	public void eu_valido_que_o_status_code_é_e_os_dados_estão_corretos(String string) {
	     
	     
	}
}
