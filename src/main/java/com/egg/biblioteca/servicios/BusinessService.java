package com.egg.biblioteca.servicios;

import com.egg.biblioteca.excepciones.MiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String email, String nombre) throws MiException {

        validar(nombre, email);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Gracias Por Contactarte con Egg");
        message.setText("Buenas " + nombre + ", desde ya muchas gracias por contactarte con Egg, "
                + "te responderemos a la brevedad, Muchas Gracias." + "\n"
                + "Atte Elias Giovanella." + "\n"
                + "Soporte de Egg.");
        mailSender.send(message); //método Send(envio), propio de Java Mail Sender.

    }

    public void sendEmailPropio(String email, String nombre, String comentario) throws MiException{
        validar(nombre, email);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("bibliotecasegg@gmail.com");
        message.setSubject("Nuevo Mensaje de: " + nombre);
        message.setText(comentario + "\nMensaje enviado desde: " + email);
        
        mailSender.send(message);
    }
    
    
    private void validar(String nombre, String email) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede estar vacio o nulo");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("El email no puede estar vacio o nulo");

        }
    }
}
