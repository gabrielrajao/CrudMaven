package CRUD;

public class Produto {
	private int id;
	private String nome;
	private float preco;
	private int quant;
	//Construtores
	public Produto () {
		id = -1;
		nome = "";
		preco = 0;
		quant = 0;
	}
	public Produto (int id, String nome,float preco,int quant, DAO dao ) throws Exception {
		if(id < 0 || preco <= 0 || quant < 0 ) {
			throw new Exception("ERRO! Valores (ID, preco ou quantidade) sao menores que 0 (OBS: preco deve ser maior que 0!)");
		}
		if(dao.checkID(id)) {
			throw new Exception("ERRO! ID de produto ja existe");
		}
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quant = quant;
	}
	
	public Produto (int id, String nome,float preco,int quant, char p) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quant = quant;
	}
	
	//Sets
	
	public void setID(int id, DAO dao) throws Exception {
		if(id < 0 ) {
			throw new Exception("ERRO! Valores (ID, preco ou quantidade) sao menores que 0 (OBS: preco deve ser maior que 0!)");
		}
		if(dao.checkID(id)) {
			throw new Exception("ERRO! ID de produto ja existe");
		}
		this.id = id;
	}
	
	public void setPreco(float preco) throws Exception {
		if(preco <= 0) {
			throw new Exception("ERRO! Valores (ID, preco ou quantidade) sao menores que 0 (OBS: preco deve ser maior que 0!)");
		}
		this.preco = preco;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setQuant(int quant) throws Exception {
		if(quant < 0 ) {
			throw new Exception("ERRO! Valores (ID, preco ou quantidade) sao menores que 0 (OBS: preco deve ser maior que 0!)");
		}
		this.quant = quant;
	}
	
	
	//Gets
	
	public int getID() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public float getPreco() {
		return preco;
	}
	public int getQuant() {
		return quant;
	}

	@Override
	
	//metodo Produto para String
	
	public String toString() {
		return("ID = "+id+" , Nome = "+nome+" , Preco= "+preco+" , Quantidade= "+quant);
	}
	
	
}
