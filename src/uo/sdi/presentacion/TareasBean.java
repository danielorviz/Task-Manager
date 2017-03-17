package uo.sdi.presentacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import uo.sdi.dto.Task;

@ManagedBean(name="tareasBean")
@SessionScoped
public class TareasBean {

	private List<Task> tareas = new ArrayList<Task>();
	private Task tareaSeleccionada;
	public List<Task> getTareas() {
		return tareas;
	}

	public void setTareas(List<Task> tareas) {
		this.tareas = tareas;
	}
	public void fecha(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, 6);
	}
	
	public Task getTareaSeleccionada() {
		return tareaSeleccionada;
	}

	public void setTareaSeleccionada(Task tareaSeleccionada) {
		this.tareaSeleccionada = tareaSeleccionada;
	}

	public String cargarInbox(){
		tareas = new ArrayList<Task>();
		tareas.add(new Task().setTitle("Titulo A"));
		tareas.add(new Task().setTitle("Titulo B"));
		tareas.add(new Task().setTitle("Titulo C"));
		return "EXITO";
	}
	
	public String borrar(){
		System.out.println("borrando" + tareaSeleccionada.getTitle());
		
		return "EXITO";
	}
	
}
