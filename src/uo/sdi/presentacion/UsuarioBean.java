package uo.sdi.presentacion;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import uo.sdi.business.AdminService;
import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.User;
import uo.sdi.dto.types.UserStatus;

@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean {
	// modificado
	private User user = new User();
	private String passwordConfirmacion;
	private List<User> users;

	public UsuarioBean() { }
	
	public User getUser() {
		return user;
	}
	  
	public List<User> getUsers () {
		return users;
	}
    public void setUsers(List<User> users) {
    	this.users = users;
	} 
	
	public void setPasswordConfirmacion(String pass){
		this.passwordConfirmacion=pass;
	}
	public String getPasswordConfirmacion(){
		return this.passwordConfirmacion;
	}
	
	public String registrar(){
		if(passwordConfirmacion.equals(user.getPassword())){
			// insertar
			UserService userService = Services.getUserService();
			
			try {
				userService.registerUser(user);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "FRACASO";
			}
			
		}
		System.out.println(user);
		return "EXITO";
		
	}
	
	public String listadoUsuarios() {
		AdminService service;
		try {  
			service = Services.getAdminService();
			users = service.findAllUsers();
			
			return "EXITO"; //Nos vamos a la vista principalAdmin.xhtml
			
		} catch (Exception e) {
			e.printStackTrace();  
			return "ERROR";   //Nos vamos la vista de error
		} 
	}
	
	public String eliminarUsuario(Long idUser) {
		AdminService service;
		  try {
			service = Services.getAdminService();
			
			User usuario = 	service.findUserById(idUser);
			
			service.deepDeleteUser(idUser);
			
			users.remove(usuario);
			
			return "EXITO";   //Actualizamos la vista de listadoUsuarios.
			
		  } catch (Exception e) {
			e.printStackTrace();  
			return "ERROR";     //Nos vamos a la vista de error
		  }
	}

	public void iniciaUsuario(Object object) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication()
				.getResourceBundle(facesContext, "msgs");
		user.setId(null);
		user.setEmail("");
		user.setLogin("");
		user.setPassword("");
		user.setIsAdmin(false);
		user.setStatus(UserStatus.DISABLED);
	}
	
}
