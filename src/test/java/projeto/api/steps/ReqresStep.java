package projeto.api.steps;

import java.io.IOException;

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
	public void eu_valido_que_a_resposta_contém_a_lista_de_usuários() throws IOException {
	     req.validateResponseUserList();     
	}
	
	@Then("eu valido a resposta da página {string} com a lista de usuários")
	public void eu_valido_a_resposta_da_página_com_a_lista_de_usuários(String page) throws IOException {
		req.validateResponseUserPage(page);
	}

	@Then("eu valido os dados do usuário específico {string}")
	public void eu_valido_os_dados_do_usuário_específico(String user) throws IOException {
	     req.validateResponseSpecificUser(user);
	}

	@Then("eu valido que o erro retornado tem o status code {string}")
	public void eu_valido_que_o_erro_retornado_tem_o_status_code(String status) throws IOException {
		req.validateResponseInexistanceUser(status);
	}

	@When("realizo uma request POST para {string}")
	public void realizo_uma_request_post_para(String endpoint) {
		req.requestPOSTMethod(endpoint);
	}

	@Then("eu valido que a criação foi bem-sucedida com status code {string} e os dados corretos")
	public void eu_valido_que_a_criação_foi_bem_sucedida_com_status_code_e_os_dados_corretos(String status) throws IOException {
		req.validateResponseUserCreated(status);
	}
	
	@When("realizo uma request POST com dados incorretos {string} {string} {string}")
	public void realizo_uma_request_post_para(String endpoint, String name, String job) {
		req.requestPOSTMethodInvalidMethod(endpoint, name, job);
	}

	@Then("eu valido resultado retornado com status code {string} {string} {string}")
	public void eu_valido_resultado_retornado_com_status_code(String statusCode, String name, String job) throws IOException {
		req.validateReturnResponseInvalidMethod(statusCode, name, job);
	}

	@When("realizo uma request PUT para {string}")
	public void realizo_uma_request_put_para(String endpoint) {
	     req.requestPUTMethod(endpoint);
	}

	@Then("eu valido que os dados do usuário foram atualizados")
	public void eu_valido_que_os_dados_do_usuário_foram_atualizados() throws IOException {
		req.validateResponseUpdateUser();
	}
	
	@When("realizo uma request PUT para {string} faltando informacoes")
	public void realizo_uma_request_put_para_faltando_informacoes(String endpoint) {
	     req.requestPUTMethodWithoutInformation(endpoint);
	}

	@Then("eu valido a alteracao com ausencia de informacao")
	public void eu_valido_a_alteracao_com_ausencia_de_informacao() throws IOException {
		req.validateResponseUpdateWithoutInformation();
	}

	@When("realizo uma request DELETE para {string}")
	public void realizo_uma_request_delete_para(String endpoint) {
		req.requestDELETEMethod(endpoint);
	}

	@Then("eu valido que o status code é {string} e a resposta não contém conteúdo")
	public void eu_valido_que_o_status_code_é_e_a_resposta_não_contém_conteúdo(String status) throws IOException {
		req.validateReturnRequestDelete(status);
	}
	
	@When("realizo uma request POST com as informacoes de login para {string}")
	public void realizo_uma_request_post_com_as_informacoes_de_login_para(String endpoint) {
		req.requestPOSTMethodLoginData(endpoint);
	}

	@Then("eu valido que a resposta contém um token")
	public void eu_valido_que_a_resposta_contém_um_token() throws IOException {
	     req.validateResponseLoginAccess();
	}

	@Then("eu valido que erro retornado e a mensagem {string} {string}")
	public void eu_valido_que_erro_retornado_e_a_mensagem(String status, String msg) throws IOException {
		req.validateResponseLoginUnsuccessfull(status, msg);  
	}
	

	@Then("eu valido que a lista de usuários foi retornada corretamente e paginada")
	public void eu_valido_que_a_lista_de_usuários_foi_retornada_corretamente_e_paginada() {
	     
	     
	}

	@Then("eu valido que o status code é {string} e os dados estão corretos")
	public void eu_valido_que_o_status_code_é_e_os_dados_estão_corretos(String string) {
	     
	     
	}
}
