package com.digisphere.propertize.application.observerPattern.observers;

import com.digisphere.propertize.infra.ErrorHandler.CustomException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class EmailAlertObserver implements IObserver {

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

        throw new CustomException("ERRO NO ENVIO DE EMAIL");
    }


    @Override
    public void update(Map<String, String> data) {

        try {
            this.setMailSender(createMailSender());
            var properties = getPasswordEmail();
            var mimeMessage = createMailSender().createMimeMessage();
            var message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            message.setFrom(properties.getProperty("EMAIL_USERNAME"));
            message.setTo(data.get("email"));
            message.setSubject("CREDENCIAIS PROPERTIZE");
            message.setText(createHtmlString(data.get("userName"), data.get("password")), true);
            mailSender.send(mimeMessage);
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

    private String createHtmlString(String userName, String password) {
        return String.format("""
                <html>
                    <body>
                        <h1>Bem-vindo(a) à Propertize!</h1>
                        <h2>Abaixo estão suas credenciais de acesso ao sistema:</h2>
                        <p><strong>Nome de usuário:</strong> %s</p>
                        <p><strong>Senha:</strong> %s</p>
                        <p><em>Obs: lembre-se de trocar a senha padrão</em></p>
                    </body>
                </html>
            """, userName, password);
    }

    private void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
