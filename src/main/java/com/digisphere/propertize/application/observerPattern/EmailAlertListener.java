package com.digisphere.propertize.application.observerPattern;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class EmailAlertListener implements IObserver {

    private  JavaMailSender mailSender;

    private JavaMailSender createMailSender() {
        try {
            var properties = getPasswordEmail();
            JavaMailSenderImpl impl = new JavaMailSenderImpl();

            impl.setHost((String) properties.get("EMAIL_HOST"));
            impl.setPort(Integer.parseInt(properties.getProperty("EMAIL_PORT")));

            impl.setUsername(properties.getProperty("EMAIL_USERNAME"));
            impl.setPassword(properties.getProperty("EMAIL_PASS"));

            Properties props = impl.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return impl;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        throw new RuntimeException("ERRO NO ENVIO DE EMAIL");
    }


    @Override
    public void update(Map<String, String> data) {

        try {
            var properties = getPasswordEmail();
            mailSender = createMailSender();
            var message = new SimpleMailMessage();
            message.setFrom(properties.getProperty("EMAIL_USERNAME"));
            message.setTo(data.get("email"));
            message.setSubject("CREDENCIAIS PROPERTIZE");
            message.setText(String.format("""
                    Bem-Vindo(a) a propertize
                                   \s
                    abaixo estão suas credenciais de acesso ao sistema:\s
                    (obs: lembre-se de trocar a senha padrao)
                                   \s
                    Nome de usuário: %s
                    Senha: %s
                   \s""", data.get("userName"), data.get("password")));
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Properties getPasswordEmail() throws IOException {
        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("env.properties");
        properties.load(input);

        return properties;
    }
}
