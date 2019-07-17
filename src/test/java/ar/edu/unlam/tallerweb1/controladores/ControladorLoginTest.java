package ar.edu.unlam.tallerweb1.controladores;


import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;

public class ControladorLoginTest {
    @Test
    public void validarLoginDeberiaRedigirAError() {
        Usuario user = new Usuario();
        
        ServicioLoginImpl sut = new ServicioLoginImpl();
		UsuarioDao uDao = mock(UsuarioDao.class);
		when(uDao.consultarUsuario(user)).thenReturn(null);
		sut.setServicioLoginDao(uDao);
		Usuario b = sut.consultarUsuario(user);
        ControladorLogin controladorLogin = mock(ControladorLogin.class);
        ModelMap model = new ModelMap();
        
        when(controladorLogin.validarLogin(b, null)).thenReturn(new ModelAndView("login", model));
        ModelAndView mav = controladorLogin.validarLogin(b, null);

        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel()).containsKey("error");
        assertThat(mav.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
    }
}