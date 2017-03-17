package uo.sdi.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.User;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {

	private String login;
	private String password;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login(){
		User user =null;
		try {
			UserService userService = Services.getUserService();
			user = userService.findLoggableUser(login, password);
			
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FRACASO";
		}
		
		if(user!= null){
			HttpSession sesion =(HttpSession) FacesContext.getCurrentInstance().
					getExternalContext().getSession(false);
			
			sesion.setAttribute("usuario", login);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
					"login completado",
					"el usuario" + login+ ""));
			FacesContext.getCurrentInstance()
			.getExternalContext()
			.getFlash().setKeepMessages(true);
			
			if (user.getIsAdmin())
			{
				UsuarioBean uB = new UsuarioBean();
				uB.listadoUsuarios();
				return "EXITOADMIN";
			}
			else
				return "EXITO";

		}
		else{
			
			return "FRACASO";
		}
		
	}
	
	public String cerrarSesion(){
		 FacesContext.getCurrentInstance().
				getExternalContext().invalidateSession();		
		
		return "EXITO";
	}
}
