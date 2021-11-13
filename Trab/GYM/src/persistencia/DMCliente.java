package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.*;
import javax.swing.JOptionPane;

public class DMCliente extends DMGeral {
	String cpf = null;
    
    public void incluir(Object obj){  
    	Cliente cliente = (Cliente) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
            String incluirSQL = "INSERT INTO tb_cliente (" +
                                "nome,cpf,dataNascimento) VALUES ('" +
                                cliente.getNome() + "', '" +
                                cliente.getCpf() + "', '" +
                                cliente.getDataNascimento() + "')";
            
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            
            int result = statement.executeUpdate(incluirSQL);
            if (result == 1){   
            	JOptionPane.showMessageDialog(null,"Cliente cadastrada corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);}
            else{   
            	JOptionPane.showMessageDialog(null,"Erro ao cadastrar cliente!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
            	cliente.setCpf("");    
            	cliente.setNome("");
            	cliente.setDataNascimento("");
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de inclusão do cliente!"); }
    }

    public Object consultar(Object obj){   
    	
    	Cliente cliente = (Cliente) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_cliente WHERE (cpf = '"+cliente.getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next()){   
            	System.out.println("Cliente existente !" );
                System.out.println("Dados do CLiente");
                System.out.println("Cpf.............: "+ result.getString("cpf"));
                System.out.println("Nome...............: "+ result.getString("nome"));
                System.out.println("Data de Nascimento.: "+ result.getString("dataNascimento"));
                
                cliente.setId(Integer.parseInt(result.getString("id")));
                cliente.setCpf(result.getString("cpf"));
                cliente.setNome(result.getString("nome"));
                cliente.setDataNascimento(result.getString("dataNascimento"));
                result.close();
            }
            else{   
            	System.out.println( "Cliente não encontrada !\n" );
            	cliente = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de consulta do cliente !"); }
        return cliente;
    }

    public Object consultar(String cpf){   
    	
    	Cliente cliente = new Cliente();   
        System.out.println("Metodo Consultar de DMCliente - CPF: "+cpf);
        try
        {   Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_cliente WHERE (cpf = '"+cpf+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next())
            {   System.out.println("Cliente existente !" );
            	System.out.println("Id.............: "+ result.getString("id"));
                System.out.println("Cpf.............: "+ result.getString("cpf"));
                System.out.println("Nome...............: "+ result.getString("nome"));
                
				cliente.setId(Integer.parseInt(result.getString("id")));
				System.out.println(cliente.getId());
                cliente.setCpf(result.getString("cpf"));
                cliente.setNome(result.getString("nome"));
                cliente.setDataNascimento(result.getString("dataNascimento"));
                result.close();
            }
            else
            {   System.out.println( "Cliente não encontrada !\n" );
            	cliente = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de consulta do cliente !"); }
                
        return cliente;
    }
    

    public void excluir(Object obj){
    	
    	Cliente objCliente = (Cliente) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String excluirSQL = "DELETE FROM tb_cliente WHERE (cpf = '"+objCliente.getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(excluirSQL) + "\n");
            int result = statement.executeUpdate(excluirSQL);
            if (result == 1)
            {   JOptionPane.showMessageDialog(null,"Cliente excluído corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }
            else
            {   System.out.println( "\nErro ao excluir cliente !\n" );
                objCliente.setNome("");
            }
           statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de exclusão do cliente !"); }
        
    }

    public void alterar(Object obj){  
    	
    	Cliente objCliente = (Cliente) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String alterarSQL = "UPDATE tb_cliente SET nome = '"+objCliente.getNome()+"' ,"+
                                "dataNascimento = '"+objCliente.getDataNascimento()+"' " +
                                "WHERE id = '"+objCliente.getId()+"' ";
            
            int result = statement.executeUpdate(alterarSQL);
            if (result == 1)
            {	JOptionPane.showMessageDialog(null,"Dados do cliente alterado!");
            	System.out.println( "Cliente alterado corretamente !"); }
            else
            {   JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            	System.out.println( "\nErro ao alterar cliente !\n" );
                objCliente.setNome("");
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
