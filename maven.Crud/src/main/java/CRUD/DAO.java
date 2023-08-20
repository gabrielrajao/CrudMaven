package CRUD;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	//CREATE
	public boolean inserirProduto(Produto prod) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produtos (id,nome,preco,quant) "
					       + "VALUES ("+prod.getID()+ ", '" + prod.getNome() + "', '"  
					       + prod.getPreco() + "', '" + prod.getQuant() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	//READ
	
	public Produto[] getProdutos() {
		Produto[] prod = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");		
	         if(rs.next()){
	             rs.last();
	             prod = new Produto[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                prod[i] = new Produto(rs.getInt("id"), rs.getString("nome"), 
	                		                  rs.getFloat("preco"), rs.getInt("quant"),'T');
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return prod;
	}
	
	//UPDATE
	public boolean atualizarProduto(Produto prod) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE produtos SET nome = '" + prod.getNome() + "', preco = '"  
				       + prod.getPreco() + "', quant = '" + prod.getQuant() + "'"
					   + " WHERE id = " + prod.getID();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	//DELETE
	public boolean excluirProduto(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produtos WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	//Metodos fora do Crud
	
	//Check id
	public boolean checkID(int id) {
		boolean result = false;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produtos WHERE id = "+ id);		
	         if(rs.next()){
	             result = true;
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
		
	}
	
	
	//searchID
	public Produto searchID(int id) {
		Produto prod = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produtos WHERE id = "+ id);		
	         if(rs.next()){
	             prod = new Produto(rs.getInt("id"), rs.getString("nome"),rs.getFloat("preco"), rs.getInt("quant"),'T');
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return prod;
	}
	
	
	//Pegar produtos esgotados
	public Produto[] getProdEsgotados() {
		Produto[] prod = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produtos WHERE quant = " + 0);		
	         if(rs.next()){
	             rs.last();
	             prod = new Produto[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                prod[i] = new Produto(rs.getInt("id"), rs.getString("nome"), 
                         		                  rs.getFloat("preco"), rs.getInt("quant"),'T');
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return prod;
	}
	
}
