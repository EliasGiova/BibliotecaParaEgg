
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.repository.AutorRepository;
import com.egg.biblioteca.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    
    @Autowired
    AutorRepository autorRepositorio;
    
    @Transactional
    public void CrearAutor(String nombre) throws MiException{
       
        validar(nombre);
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
    public List<Autor> listarAutores(){
       
        List<Autor> autores = new ArrayList();
        
        autores = autorRepositorio.findAll();
        
        return autores;
    }
    
    public void modificarAutor(String id, String nombre) throws MiException{
       
        if (id.isEmpty() || id == null) {
            throw new MiException("el id no puede estar nulo o vacio");
        }
        
        validar(nombre);
        
        Optional<Autor>respuestaAutor = autorRepositorio.findById(id);
        
        
        
        if (respuestaAutor.isPresent()) {
            
            Autor autor = respuestaAutor.get();
            
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        }
    }
    
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }
    
    private void validar (String nombre) throws MiException{
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o vacio");
        }
    }
}
