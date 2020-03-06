package manager;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entity.Usuario;
import persistence.UsuarioDao;

@ManagedBean(name="mb")
@RequestScoped
public class ManagerBean {
	
	private Usuario usuario;
	private Usuario logado;
	
	
	public ManagerBean() {
		this.usuario = new Usuario();
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Usuario getLogado() {
		return logado;
	}


	public void setLogado(Usuario logado) {
		this.logado = logado;
	}
	
	public String logar(){// metodo para logar
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session =(HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		try {
			this.logado = new UsuarioDao().findByLogin(this.usuario);
			if(this.logado != null) {
				session.setAttribute("Logado", logado);
				return "logado.jsf?faces-redirect";
			}else {
				session.setAttribute("Logado", "");
				fc.addMessage(null, new FacesMessage("Não logado,Favor se cadastrar"));
				return null;
			}
			}catch(Exception ex){

				return null;
			}
			
		}
		
		public void criar() { //metodo para criar usuario 
			FacesContext fc= FacesContext.getCurrentInstance();
			try{
				new UsuarioDao().create(this.usuario);
				this.usuario = new Usuario();
				fc.addMessage(null, new FacesMessage("Dados cadastrados"));
			}catch(Exception ex) {
				fc.addMessage(null , new FacesMessage("Erro: "+ ex.getMessage()));
				
			}
		
		}
		
		public String logout() { //metodo logout
		  	 FacesContext fc = FacesContext.getCurrentInstance();
			 try {
		    HttpSession session = (HttpSession) 
			  	     fc.getExternalContext().getSession(true);
		    session.invalidate(); 
	   fc.addMessage("form1",   new FacesMessage("Logout Realizado"));
			 }catch(Exception ex) {
	fc.addMessage("form1", new FacesMessage("Error:" + ex.getMessage()));
			 }
			 return "sistema.jsf?faces-redirect";
		}

		 public void verificarLogin() {
			 try {
				  if (logado==null) {
					   this.usuario = new Usuario();
				  FacesContext.getCurrentInstance().getExternalContext().
	  			          redirect("sistema.jsf");
				  }
				  
			 }catch(Exception ex) {
				 
			 }
		 }
		

	}
