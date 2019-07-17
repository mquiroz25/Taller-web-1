package ar.edu.unlam.tallerweb1.services;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;


public class LoginServiceTest {

	@Test
	public void encuentraUsuarioDeberiaDevolverUsuario() {
		Usuario user = new Usuario();
		ServicioLoginImpl sut = new ServicioLoginImpl();
		UsuarioDao uDao = mock(UsuarioDao.class);
		when(uDao.consultarUsuario(user)).thenReturn(new Usuario());
		sut.setServicioLoginDao(uDao);
		Usuario b = sut.consultarUsuario(user);
		assertNotNull(b);
	}
	
	@Test
	public void encuentraUsuarioDeberiaSerNULL() {
		Usuario user = new Usuario();
		ServicioLoginImpl sut = new ServicioLoginImpl();
		UsuarioDao uDao = mock(UsuarioDao.class);
		when(uDao.consultarUsuario(user)).thenReturn(null);
		sut.setServicioLoginDao(uDao);
		Usuario b = sut.consultarUsuario(user);
		assertNull(b);
	}
}
