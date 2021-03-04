package br.com.bancoVantorantim.steps;

import io.cucumber.java.pt.Dado;
import org.junit.Assert;
import page.objects.LoginBVPage;


public class LoginSteps {

    LoginBVPage loginBVPage = new LoginBVPage();

    @Dado("que eu estou no site do banco BV")
    public void queEuEstouNoSiteDoBancoBV() {
        Assert.assertTrue("O site não está sendo exibido", loginBVPage.getBVIcon());
    }

}
