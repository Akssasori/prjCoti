package persistence;

import entity.Usuario;

public class UsuarioDao extends Dao {
	
	public void create(Usuario a)throws Exception{
		open();
		//stmt=con.prepareStatement("insert into usuario values (seq_usuario.nextval,?,criptografa(?)  ) " );
		//stmt=con.prepareStatement("insert into usuario ( id,login,senha) values (?,?,?)");
		stmt = con.prepareStatement("insert into usuario values (null,?,?)");
		stmt.setString(1, a.getLogin());
		stmt.setString(2, a.getSenha());
		stmt.execute();
		close();
		
		
	}
	
	public Usuario findByLogin(Usuario a)throws Exception{
		open();
		//stmt=con.prepareStatement("select * from usuario where login=?" + " and senha=criptografa(?) ");
		stmt=con.prepareStatement("select * from usuario where login=? and senha=?");
		stmt.setString(1,a.getLogin());
		stmt.setString(2, a.getSenha());
		rs = stmt.executeQuery();
		Usuario logado = null;
		if(rs.next()) {
			logado = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
			
		}
		close();
		return logado;
		
		
		
		
	}

}
