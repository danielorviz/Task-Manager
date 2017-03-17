package uo.sdi.presentacion;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale;
	// uso de inyección de dependencia
	@ManagedProperty(value = "#{usuarioBean}")
	private UsuarioBean user;

	public UsuarioBean getUser() {
		return user;
	}

	public void setUser(UsuarioBean user) {
		this.user = user;
	}
	@PostConstruct
	public void init(){
		HttpServletRequest req = 
		(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
		.getRequest();
		if(locale == null)
			locale = req.getLocale();
	}


	// Es sólo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

	public Locale getLocale() {
		// Aqui habria que cambiar algo de código para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida “es”
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (user != null)
				user.iniciaUsuario(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (user != null)
				user.iniciaUsuario(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}