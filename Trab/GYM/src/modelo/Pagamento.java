package modelo;

import javax.swing.JOptionPane;

import persistencia.DMPagamento;

public class Pagamento {
	private int id;
	private float valor;
	private String dataRealizada;
	
	private Cliente cliente;
	private DMPagamento dmPagamento;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(String dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DMPagamento getDmPagamento() {
		return dmPagamento;
	}

	public void setDmPagamento(DMPagamento dmPagamento) {
		this.dmPagamento = dmPagamento;
	}

	public Object pesquisar(String cpf) {
		System.out.println("Metodo Pesquisa por CPF: "+cpf);
     	return (Pagamento) dmPagamento.consultar(cpf);
	}
	
	public Object pesquisar(Pagamento pagamento) {
		System.out.println("Metodo Pesquisa por CPF: "+pagamento);
     	return (Pagamento) dmPagamento.consultar(pagamento);
	}

	public Pagamento(float valor, String dataRealizada, Cliente cliente) {
		super();
		this.valor = valor;
		this.dataRealizada = dataRealizada;
		this.cliente = cliente;
		dmPagamento = new DMPagamento();
    	dmPagamento.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
	}
	
	public Pagamento () {
		dmPagamento = new DMPagamento();
    	dmPagamento.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
	}
	public void incluir(Pagamento pagamento) {
		if (pagamento.getValor() == 0 && pagamento.getDataRealizada().equals("") && pagamento.getCliente().getCpf() == null)
        {   JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!","AVISO",JOptionPane.WARNING_MESSAGE);
            System.out.println("Os campos s�o obrigat�rios!");
        }
        else
        {  
           dmPagamento.incluir(this);
            
        }
	}
	public void imprimir(){	
		System.out.println("Metodo imprimir - Pagamento");
		JOptionPane.showMessageDialog(null,"CPF: "+ this.valor + "\n NOME: " + this.dataRealizada +
				                       "\n CPF do cliente: " + this.cliente.getCpf() );
	}
	
	public Object consultar(){  
		return dmPagamento.consultar(this);  
	}
	
	public void excluir(Pagamento pagamento){   
		if (cliente.getCpf().equals(" "))
        {   
			JOptionPane.showMessageDialog(null,"CPF do cliente � obrigat�rio!","Mensagem de alerta",JOptionPane.WARNING_MESSAGE);
            System.out.println("O cpf � obrigat�rio!");
        }
        else
        {   if (dmPagamento.consultar(this)!= null)
            { dmPagamento.excluir(this); }
            else
            {   
            	JOptionPane.showMessageDialog(null,"Exclus�o do pagamento n�o realizada!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Exclus�o de cliente n�o realizada!\n Este cliente n�o existe!");
            }
        }    
    }
	
	public void alterar(){   
		if (dmPagamento.consultar(this)!= null)
        { dmPagamento.alterar(this); }
        else
        {   
        	JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            System.out.println("Impossivel alterar este cliente !"); 
        }
    }

    
    public void shutDown(){   
    	dmPagamento.shutDown();   
    }

}
