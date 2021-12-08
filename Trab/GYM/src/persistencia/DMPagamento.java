package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.*;
import javax.swing.JOptionPane;

public class DMPagamento extends DMGeral {
	String cpf = null;
    
    public void incluir(Object obj){  
    	Pagamento pagamento = (Pagamento) obj;
    	Cliente cliente;
    	cliente = new Cliente();
    	cliente = (Cliente) cliente.pesquisar(pagamento.getCliente().getCpf());
        try
        {   
        	Statement statement = getConnection().createStatement();
            
            String incluirSQL = "INSERT INTO tb_pagamento (" +
                                "valor,data_realizada,cliente) VALUES ('" +
                                pagamento.getValor() + "', '" +
                                pagamento.getDataRealizada() + "', '" +
                                cliente.getId() + "')";
            
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
        	
            int result = statement.executeUpdate(incluirSQL);
 
    
            if (result == 1){   
            	JOptionPane.showMessageDialog(null,"Pagamento realizado com sucesso !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);}
            else{   
            	JOptionPane.showMessageDialog(null,"Erro ao cadastrar cliente!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
            	pagamento.setValor(0);   
            	pagamento.setDataRealizada("");
            	pagamento.setCliente(null);
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de inclusão do cliente!"); }
    }

    public Object consultar(Object obj){   
    	
    	Pagamento pagamento = (Pagamento) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_pagamento WHERE (cliente = '"+ pagamento.getCliente().getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next()){   
            	System.out.println("Pagamento existente !" );
                
                pagamento.setId(Integer.parseInt(result.getString("id")));
                pagamento.setValor(Float.parseFloat(result.getString("valor")));   
            	pagamento.setDataRealizada(result.getString("data_nascimento"));
            	pagamento.getCliente().setId(Integer.parseInt(result.getString("cliente")));
                result.close();
            }
            else{   
            	System.out.println( "Pagamento não encontrada !\n" );
            	pagamento = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de consulta do Pagamento !"); }
        return pagamento;
    }

    public Object consultar(String cpf){   
    	
    	Pagamento pagamento = new Pagamento();   
        System.out.println("Metodo Consultar de DMCliente - CPF: "+cpf);
        try
        {   
        	Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_pagamento WHERE (cliente = '"+ pagamento.getCliente().getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next())
            {   
            	System.out.println("Pagamento existente !" );
                
                pagamento.setId(Integer.parseInt(result.getString("id")));
                pagamento.setValor(Float.parseFloat(result.getString("valor")));   
            	pagamento.setDataRealizada(result.getString("data_nascimento"));
            	pagamento.getCliente().setId(Integer.parseInt(result.getString("cliente")));
                result.close();
            }
            else
            {   
            	JOptionPane.showMessageDialog(null,"OPS!!  Erro ao consultar pagamento!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
            	System.out.println( "Pagamento não encontrada !\n" );
            	pagamento = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	JOptionPane.showMessageDialog(null,"OPS!!  Erro no Banco de Dados, tente mais tarde!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
        	System.out.println("Problemas com o SQL de consulta do cliente !");
        }
                
        return pagamento;
    }
    

    public void excluir(Object obj){
    	
    	Pagamento objPagamento = (Pagamento) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String excluirSQL = "DELETE FROM tb_pagamneto WHERE (cliente = '"+objPagamento.getCliente().getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(excluirSQL) + "\n");
            int result = statement.executeUpdate(excluirSQL);
            if (result == 1)
            {   JOptionPane.showMessageDialog(null,"Pagamento excluído corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }
            else
            { System.out.println( "\nErro ao excluir cliente !\n" ); }
            
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de exclusão do cliente !"); }
        
    }

    public void alterar(Object obj){  
    	
    	Pagamento objPagamento = (Pagamento) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            String alterarSQL = "UPDATE tb_cliente SET valor = '"+ objPagamento.getValor() + "', '" +
			                    "data_realizada"+objPagamento.getDataRealizada() + "', '" +
			                    "cliente"+objPagamento.getCliente().getCpf() + "')";
            
            int result = statement.executeUpdate(alterarSQL);
            if (result == 1){	
            	JOptionPane.showMessageDialog(null,"Dados do cliente alterado!");
            	System.out.println( "Cliente alterado corretamente !"); 
            }else{   
            	JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            	System.out.println( "\nErro ao alterar cliente !\n" );
            }
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de atualização do cliente !"); }
 
    }

    public void shutDown()
    {   try
        { getConnection().close(); }
        catch (SQLException sqlex)
        {   System.err.println("Problemas ao desconectar !");
            sqlex.printStackTrace();
        }
    }
}

