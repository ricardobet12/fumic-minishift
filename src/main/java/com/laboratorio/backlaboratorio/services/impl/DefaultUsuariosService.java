/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services.impl;

import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import com.laboratorio.backlaboratorio.entity.Usuarios;
import com.laboratorio.backlaboratorio.repository.UsuariosRepository;
import com.laboratorio.backlaboratorio.services.UsuariosService;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
/**
 *
 * @author utp
 */
@Service
public class DefaultUsuariosService implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public UsuariosDTO registrarUsuario(UsuariosDTO u) {
        Usuarios usu = this.usuariosRepository.save(mapper.map(u, Usuarios.class));
        return mapper.map(usu, UsuariosDTO.class);
    }

    @Override
    public List<UsuariosDTO> listarUsuarios() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        List<UsuariosDTO> result = new ArrayList<>();
        if (usuarios != null || !usuarios.isEmpty()) {
            for (Usuarios u : usuarios) {
                result.add(mapper.map(u, UsuariosDTO.class));
            }
            return result;
        }
        return null;
    }

    

    @Override
    public boolean eliminarUsuario(Integer id) {
        if (id != null) {
            Optional<Usuarios> u = usuariosRepository.findById(id);
            if (u.isPresent()) {
                usuariosRepository.delete(u.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public UsuariosDTO login(String usuario, String clave) {
        List<Usuarios> usu = usuariosRepository.findAll();
        if (!usu.isEmpty()) {
            for (Usuarios usuarios : usu) {
                System.out.println(usuarios.getUsuario()+usuarios.getClave());
            }
        }else {
            System.out.println("la lista se encuetra vacia");
            UsuariosDTO u = new UsuariosDTO(1, "ricardo", "betancourth bolivar", 1088302179, "utp", usuario, clave, "secretaria", true);
            UsuariosDTO res = this.registrarUsuario(u);
        }
        System.out.println(usuario+clave);
        Usuarios u = usuariosRepository.findByUsuarioAndClave(usuario, clave);
        
        if (u != null) {
            System.out.println(u.getNombres());
            return mapper.map(u, UsuariosDTO.class);
        }
        return null;
    }

    @Override
    public List<UsuariosDTO> listarPorRol(String rol) {
        List<Usuarios> usuario = usuariosRepository.findByRol(rol);
        List<UsuariosDTO> result = new ArrayList<>();
        if (usuario != null || !usuario.isEmpty()) {
            for (Usuarios t : usuario) {
                result.add(mapper.map(t, UsuariosDTO.class));
            }
            return result;
        }
        return null;
    }

    @Override
    public UsuariosDTO obtenerUsuarioPorid(Integer id) {
        Optional<Usuarios> usuario = this.usuariosRepository.findById(id);
        if (usuario.isPresent()) {
            return mapper.map(usuario.get(), UsuariosDTO.class);
        }
        return null;
    }

    @Override
    public void sendNotificacion(UsuariosDTO u) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(u.getDireccion());
        mail.setFrom("ricardo.bet1209@gmail.com");
        mail.setSubject("usuario FUMIC");
        mail.setText("Bienvenido " + u.getNombres() + " " + u.getApellidos() + " a la empresa FUMIC, Usuario: " + u.getUsuario() + " Rol:  " + u.getRol());
        javaMailSender.send(mail);

    }

    @Override
    public void sendNotificacionAdjunto() throws MailException {
     /*   try {
            ObjectMapper mapeador = new ObjectMapper(); // TODO Auto-generated catch block
            Persona p1 = new Persona("pepe", "perez", 25);
            Persona p2 = new Persona("antonio", "sanchez", 30);
            Persona p3 = new Persona("ana", "jimenez", 40);
            List<Persona> lista = new ArrayList<Persona>();
            lista.add(p1);
            lista.add(p2);
            lista.add(p3);
            mapeador.writeValue(new File("/Users/ricardo/Desktop/milista.json"), lista);
            Json jason = new Json
            /*  try {
            descargar();
            } catch (Exception ex) {
            Logger.getLogger(DefaultUsuariosService.class.getName()).log(Level.SEVERE, null, ex);
            }
            File file = new File("/Users/ricardo/Desktop/lista.pdf");
            
            MimeMessage menssage = javaMailSender.createMimeMessage();
            MimeMessageHelper mensaje;
            try {
            mensaje = new MimeMessageHelper(menssage, true);
            mensaje.setTo("jhonfre1994@utp.edu.co ");
            mensaje.setFrom("ricardo.bet1209@gmail.com");
            mensaje.setSubject("usuario FUMIC");
            mensaje.setText("Bienvenido envio correctamente");
            mensaje.addAttachment("hola", file);
            javaMailSender.send(menssage);
            } catch (Exception e) {
            } 
        } catch (IOException ex) {
            Logger.getLogger(DefaultUsuariosService.class.getName()).log(Level.SEVERE, null, ex);
        } */
      JSONObject obj = new JSONObject();

      obj.put("name", "foo");
      obj.put("num", new Integer(100));
      obj.put("balance", new Double(1000.21));
      obj.put("is_vip", new Boolean(true));

      System.out.print(obj.toString());
        
    }

    public static void descargar() throws Exception {
        final int size = 2048;
        try {
            URL ficheroUrl = new URL("http://localhost:8181/jasperserver/flow.html?_flowId=viewReportFlow&_flowId=viewReportFlow&ParentFolderUri=%2FPruebasLocal&reportUnit=%2FPruebasLocal%2FReportePrueba2&standAlone=true&j_username=jasperadmin&j_password=jasperadmin&output=pdf");
            OutputStream outputStream;
            try (InputStream inputStream = ficheroUrl.openStream()) {
                outputStream = new FileOutputStream("/Users/ricardo/Desktop/lista.pdf");
                byte[] b = new byte[size];
                int longitud;
                while ((longitud = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, longitud);
                }
                inputStream.close();
            }
        } catch (Exception e) {
            Logger.getLogger(DefaultUsuariosService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
