
package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.repository.EditorialRepository;
import com.egg.biblioteca.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {
    
    @Autowired
    EditorialRepository editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws MiException{
        
        validar(nombre);
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
    }
    
    public List<Editorial> listarEditoriales(){
        
        List<Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
    }
    
    public void modificarEditorial(String id, String nombre) throws MiException{
        
        if (id.isEmpty() || id == null) {
            throw new MiException("el id no puede ser nulo o vacio");
        }
        
        validar(nombre);
        
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(id);
        
        if (respuestaEditorial.isPresent()) {
            
            Editorial editorial = respuestaEditorial.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        }
                
    }
    
    public Editorial getOne(String id){
        return editorialRepositorio.getOne(id);
    }
    
    private void validar (String nombre) throws MiException{
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o vacio");
        }
    }
}
