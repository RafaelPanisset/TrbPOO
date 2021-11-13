package modelo;

import javax.swing.JOptionPane;
import persistencia.DMPlano;

public class Plano {
	private int id;
	private float mensalidade;
	private int horasDiarias;
	
	private Cliente cliente; 
	private DMPlano dmPlano;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getMensalidade() {
		return mensalidade;
	}
	public void setMensalidade(float mensalidade) {
		this.mensalidade = mensalidade;
	}
	public int getHorasDiarias() {
		return horasDiarias;
	}
	public void setHorasDiarias(int horasDiarias) {
		this.horasDiarias = horasDiarias;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Plano(int horasDiarias, float mensalidade, Cliente cliente){
		this.mensalidade = mensalidade;
		this.horasDiarias = horasDiarias;
		this.cliente = cliente;
		dmPlano = new DMPlano();
    	dmPlano.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
	}
	
	public Plano(Cliente cliente) {
		this.cliente = cliente;
		dmPlano = new DMPlano();
    	dmPlano.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Plano com sucesso!");
	}
	public Plano() {
		dmPlano = new DMPlano();
    	dmPlano.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Plano com sucesso!");
	}
	public void incluir(Plano plano) {
		if ( plano.getMensalidade() == 0 && plano.getHorasDiarias() == 0)
        {   JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!","AVISO",JOptionPane.WARNING_MESSAGE);
            System.out.println("Os campos s�o obrigat�rios!");
        }
        else
        {   if (pesquisar(plano) != null)
            {   JOptionPane.showMessageDialog(null,"Cadastro de cliente n�o realizado!\n J� existe uma cliente com este cpf!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Cadastro do cliente n�o realizado! J� existe um cliente com este cpf!"); 
            }
            else
            { dmPlano.incluir(this); }
        }
	}
	
	public Object pesquisar(Plano plano) {
		System.out.println("Metodo Pesquisa por ID do cliente: "+plano);
     	return (Plano) dmPlano.consultar(plano);
	}
	
	public Object pesquisar(String plano) {
		System.out.println("Metodo Pesquisa por ID do cliente: "+plano);
     	return (Plano) dmPlano.consultar(plano);
	}

	public void imprimir(){	
		System.out.println("Metodo imprimir - Plano");
		JOptionPane.showMessageDialog(null,"ID: "+ this.id + "\nDias da semana" + this.mensalidade + "\nHoras por dias: " + this.horasDiarias+ 
				                       "\nCpf do Cliente = "+ this.getCliente().getCpf());
	}
	
	public Object consultar(){  
		return dmPlano.consultar(this);  
	}
	
	public void excluir(Plano plano){   
		if (cliente.getCpf().equals(""))
        {   JOptionPane.showMessageDialog(null,"CPF � obrigat�rio!","Mensagem de alerta",JOptionPane.WARNING_MESSAGE);
            System.out.println("O cpf � obrigat�rio!");
        }
        else
        {   if (dmPlano.consultar(this)!= null)
            { 
        		dmPlano.excluir(this); 
        		this.cliente = null;
            }
        
            else
            {   JOptionPane.showMessageDialog(null,"Exclus�o do cliente n�o realizada!\n Este cliente n�o existe!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Exclus�o de cliente n�o realizada!\n Este cliente n�o existe!");
            }
        }    
    }
	
	public void alterar(){   
		if (dmPlano.consultar(this)!= null)
        { dmPlano.alterar(this); }
        else
        {   JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            System.out.println("Impossivel alterar este cliente !"); }
    }

    
    public void shutDown(){   
    	dmPlano.shutDown();   
    }
}
