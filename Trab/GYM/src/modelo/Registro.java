package modelo;

import javax.swing.JOptionPane;

import persistencia.DMRegistro;

public class Registro {
	private int id;
	private int numSemana;
	private int ano;
	private int diaSemana;
	
	private Cliente cliente;
	private DMRegistro dmRegistro;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumSemana() {
		return numSemana;
	}

	public void setNumSemana(int num_semana) {
		this.numSemana = num_semana;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int dia_semana) {
		this.diaSemana = dia_semana;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DMRegistro getDmRegistro() {
		return dmRegistro;
	}

	public void setDmRegistro(DMRegistro dmRegistro) {
		this.dmRegistro = dmRegistro;
	}

	public Registro(int num_semana, int ano, int dia_semana, Cliente cliente) {
		this.numSemana = num_semana;
		this.ano = ano;
		this.diaSemana = dia_semana;
		this.cliente = cliente;
		dmRegistro = new DMRegistro();
    	dmRegistro.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
	}

	public Registro() {
		dmRegistro = new DMRegistro();
    	dmRegistro.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conex�o feita � tabela Cliente com sucesso!");
	}
	
	public Object pesquisar(String cpf) {
		System.out.println("Metodo Pesquisa por CPF: "+cpf);
     	return (Pagamento) dmRegistro.consultar(cpf);
	}
	
	public Object pesquisar(Pagamento pagamento) {
		System.out.println("Metodo Pesquisa por CPF: "+pagamento);
     	return (Pagamento) dmRegistro.consultar(pagamento);
	}
	
	public void imprimir(){	
		System.out.println("Metodo imprimir - Pagamento");
		JOptionPane.showMessageDialog(null,"dia: "+ this.numSemana + "\n NOME: " + this.ano + 
				                       "\n  = "+ this.diaSemana + 
				                       "\n CPF do cliente: " + this.cliente.getCpf() );
	}
	
	public Object consultar(){  
		return dmRegistro.consultar(this);  
	}
	
	public void excluir(Pagamento pagamento){   
		if (cliente.getCpf().equals(" "))
        {   
			JOptionPane.showMessageDialog(null,"CPF do cliente � obrigat�rio!","Mensagem de alerta",JOptionPane.WARNING_MESSAGE);
            System.out.println("O cpf � obrigat�rio!");
        }
        else
        {   if (dmRegistro.consultar(this)!= null)
            { dmRegistro.excluir(this); }
            else
            {   
            	JOptionPane.showMessageDialog(null,"Exclus�o do pagamento n�o realizada!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Exclus�o de cliente n�o realizada!\n Este cliente n�o existe!");
            }
        }    
    }
	
	public void alterar(){   
		if (dmRegistro.consultar(this)!= null)
        { dmRegistro.alterar(this); }
        else
        {   
        	JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            System.out.println("Impossivel alterar este cliente !"); 
        }
    }

    
    public void shutDown(){   
    	dmRegistro.shutDown();   
    }
}
