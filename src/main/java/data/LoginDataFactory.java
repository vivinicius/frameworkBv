package data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static data.utils.GeraCpfCnpj.cpf;

public class LoginDataFactory {

    private static Faker faker = new Faker(new Locale("pt-BR"));

    public static String unRegisteredCpf() {
        return cpf(false);
    }

    public static String unRegisteredBirthDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(faker.date().birthday(16, 60));
        return date;
    }

    public static String unRegisteredPassword() {
        return "senhaNaoCadastrada@123";
    }

}
