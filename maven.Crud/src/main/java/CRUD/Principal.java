package CRUD;
import java.util.*;
public class Principal {
	//scanner
	public static Scanner sc = new Scanner(System.in);
	//printProd
	public static void printProd(Produto[] p) {
		for (int i = 0; i < p.length; i++) {
			Produto index = p[i];
			System.out.println(index.toString());
		}
	}
	
	//insertProd
	public static void insertProd(DAO dao) throws Exception{
		System.out.println("Digite o id:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Digite o nome:");
		String nome = sc.nextLine();
		System.out.println("Digite o preco:");
		float preco = sc.nextFloat();
		System.out.println("Digite a quantidade:");
		int quant = sc.nextInt();
        Produto p = new Produto(id,nome,preco,quant, dao);
        dao.inserirProduto(p);
	}
	
	//excluir produtos
	public static void excluirProd(DAO dao) {
		sc.nextLine();
		System.out.println("Digite o id do produto: ");
		int id = sc.nextInt();
		if(dao.excluirProduto(id)) {
			System.out.println("Produto excluido com sucesso");
		}else {
			System.out.println("Erro ao excluir produto");
		}
	}
	
	//atualizar produtos
	public static void atualizaProd(DAO dao) throws Exception {
		sc.nextLine();
		System.out.println("Digite o id do produto:");
		int id = sc.nextInt();
		if(dao.checkID(id)) {
			Produto prod = dao.searchID(id);
			int selection = 0;
			while(selection != 5) {
				System.out.println("1) Mudar nome \n2) Mudar preco \n3)Mudar quantidade \n4)Mudar todos \n5)Sair\nDigite a opcao desejada: ");
				selection = sc.nextInt();
				if(selection == 1 || selection == 4) {
					System.out.println("Digite o novo nome do produto: ");
					sc.nextLine();
					prod.setNome(sc.nextLine());
					
				}
				if(selection == 2 || selection == 4) {
					System.out.println("Digite o novo preco do produto: ");
					prod.setPreco(sc.nextFloat());
									
				}
				if(selection == 3 || selection == 4) {
					System.out.println("Digite a nova quantidade do produto: ");
					prod.setQuant(sc.nextInt());
					
				}
			}
			dao.atualizarProduto(prod);
		} else {
			System.out.println("Id nao encontrado");
		}
	}
	
	//main
	public static void main(String args[]) {
		//definir dados
		int selection = 0;
		DAO dao = new DAO();
		
		
		//inicar conexao com banco de dados
		dao.conectar();
		
		//inicio do programa
		while(selection != 5) {
			
			try {
				System.out.println("Aperte ENTER para continuar");
				sc.nextLine();
				sc.nextLine();
				System.out.println("1) Listar \n2) Inserir \n3)Excluir \n4) Atualizar \n5) Sair \nDigite a opcao desejada: "); 
				selection = sc.nextInt();
				switch(selection) {
				default: 
					System.out.println("Opcao Invalida!"); 
					break;
				case 1: 
					Produto[] prod = dao.getProdutos();
					printProd(prod);
					break;
				case 2: 
					insertProd(dao);
					break;
				case 3:
					excluirProd(dao);
					break;
				case 4: 
					atualizaProd(dao);
					break;
				case 5:
					break;
					
				}
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		dao.close();
		System.out.println("Fim do programa");
	}
}
