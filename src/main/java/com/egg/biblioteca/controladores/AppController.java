package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.BusinessService;
import com.egg.biblioteca.servicios.UsuarioService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private BusinessService emailService;

    @Autowired
    private UsuarioService usuarioServicio;

    @GetMapping("/send-email/{id}")
    public String sendEmail(@PathVariable String id, ModelMap modelo) {
        Usuario usuario = usuarioServicio.getOne(id);
        modelo.put("usuario", usuario);
        return "redirect:/inicio";
    }

    @PostMapping("/send-email/{id}")
    public String sendEmail(@RequestParam("correoOculto") String email, @RequestParam String nombre, @RequestParam String comentario, @PathVariable String id, ModelMap modelo) {
        try {
            Usuario usuario = usuarioServicio.getOne(id);
            modelo.put("usuario", usuario);
            emailService.sendEmail(email, nombre);
            emailService.sendEmailPropio(email, nombre, comentario);
            modelo.put("exito", "email enviado correctamente");
            return "inicio.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            return "contacto.html";
        }
    }
}
