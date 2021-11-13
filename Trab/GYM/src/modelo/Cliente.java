package modelo;

import javax.swing.JOptionPane;
import persistencia.DMCliente;

public class Cliente {
	private int id;
	private String nome;
	private String cpf;
	private String dataNascimento; 
	
	private DMCliente dmCliente;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Cliente(String nome, String cpf, String dataNascimento){
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		dmCliente = new DMCliente();
    	dmCliente.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
        incluir(this);
	}
	
	public Cliente(String nome, String dataNascimento){
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		dmCliente = new DMCliente();
    	dmCliente.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
	}
	
	public Cliente(String cpf){
		this.cpf = cpf;
		dmCliente = new DMCliente();
    	dmCliente.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
	}
	
	public Cliente()
	{
		dmCliente = new DMCliente();
    	dmCliente.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela cliente com sucesso!");
		
	}
	
	private void incluir(Cliente cliente) {
        JOptionPane.showMessageDialog(null, cliente);

		if (cliente.getCpf().equals("") && cliente.getNome().equals("") && cliente.getDataNascimento().equals(""))
        {   JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!","AVISO",JOptionPane.WARNING_MESSAGE);
            System.out.println("Os campos s�o obrigat�rios!");
        }
        else
        {   if (pesquisar(cliente.getCpf())!= null)
            {   JOptionPane.showMessageDialog(null,"Cadastro de cliente n�o realizado!\n J� existe uma cliente com este cpf!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Cadastro do cliente n�o realizado! J� existe um cliente com este cpf!"); 
            }
            else
            { dmCliente.incluir(this); }
        }
	}
	
	public Object pesquisar(String cpf) {
		System.out.println("Metodo Pesquisa por CPF: "+cpf);
     	return (Cliente) dmCliente.consultar(cpf);
	}
	
	public Object pesquisar(Cliente cpf) {
		System.out.println("Metodo Pesquisa por CPF: "+cpf);
     	return (Cliente) dmCliente.consultar(cpf);
	}


	public void imprimir(){	
		System.out.println("Metodo imprimir - Cliente");
		JOptionPane.showMessageDialog(null,"CPF: "+ this.cpf + "\n NOME: " + this.nome + 
				                       "\n Data de Nascimento = "+ this.dataNascimento);
	}
	
	public Object consultar(){  
		return dmCliente.consultar(this);  
	}
	
	public void excluir(Cliente cliente){   
		if (cliente.getCpf().equals(""))
        {   JOptionPane.showMessageDialog(null,"CPF � obrigat�rio!","Mensagem de alerta",JOptionPane.WARNING_MESSAGE);
            System.out.println("O cpf � obrigat�rio!");
        }
        else
        {   if (dmCliente.consultar(this)!= null)
            { dmCliente.excluir(this); }
            else
            {   JOptionPane.showMessageDialog(null,"Exclus�o do cliente n�o realizada!\n Este cliente n�o existe!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Exclus�o de cliente n�o realizada!\n Este cliente n�o existe!");
            }
        }    
    }
	
	public void alterar(){   
		if (dmCliente.consultar(this)!= null)
        { dmCliente.alterar(this); }
        else
        {   JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            System.out.println("Impossivel alterar este cliente !"); }
    }

    
    public void shutDown(){   
    	dmCliente.shutDown();   
    }
	
}
