package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Envia los dos correos del flujo de validacion de cuentas:
 * el link de confirmacion (prueba que el correo es valido) y el
 * aviso final de que el administrador ya dio acceso.
 */
public class EmailUtil {

    private EmailUtil() {
    }

    public static void enviarConfirmacionCorreo(String correoDestino, String nombreDocente, String linkConfirmacion) {
        enviar(correoDestino, "Confirma tu correo - Sistema de Calificaciones",
                "Hola " + nombreDocente + ",\n\n"
                        + "Gracias por registrarte. Confirma que este es tu correo dando clic en el siguiente link:\n\n"
                        + linkConfirmacion + "\n\n"
                        + "Despues de confirmarlo, el administrador debe darte acceso para que puedas iniciar sesion.\n\n"
                        + "Saludos.");
    }

    public static void enviarNotificacionAcceso(String correoDestino, String nombreDocente) {
        enviar(correoDestino, "Ya tienes acceso - Sistema de Calificaciones",
                "Hola " + nombreDocente + ",\n\n"
                        + "El administrador ya te dio acceso. Ya puedes iniciar sesion en el sistema "
                        + "con tu usuario y contrasena.\n\n"
                        + "Saludos.");
    }

    private static void enviar(String correoDestino, String asunto, String cuerpo) {
        String host = ConfigUtil.get("mail.smtp.host");
        String port = ConfigUtil.get("mail.smtp.port");
        String remitente = ConfigUtil.get("mail.remitente");
        String password = ConfigUtil.get("mail.password");

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", ConfigUtil.get("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", ConfigUtil.get("mail.smtp.starttls"));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport.send(mensaje);
        } catch (MessagingException e) {
            // No se interrumpe el flujo si el correo falla; se registra
            // en los logs de Tomcat para poder revisarlo.
            System.err.println("No se pudo enviar el correo (" + asunto + "): " + e.getMessage());
        }
    }
}
