package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import modelo.FichaMedica;

public class DMFichaMedica extends DMGeral {
	String cpf = null;
    
    public void incluir(Object obj){  
    	FichaMedica ficha = (FichaMedica) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
            String incluirSQL = "INSERT INTO tb_ficha_medica (" +
                                "peso_atua, altura, imc, cliente) VALUES ('" +
                                ficha.getPeso() + "', '" +
                                ficha.getAltura() + "', '" +
                                ficha.getImc() + "', '" +
                                ficha.getCliente().getId()+ "')";
            
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            
            int result = statement.executeUpdate(incluirSQL);
            if (result == 1){   
            	JOptionPane.showMessageDialog(null,"Ficha medica cadastrada com sucesso cadastrada corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);}
            else{   
            	JOptionPane.showMessageDialog(null,"Erro ao cadastrar ficha!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
            	ficha.setPeso(0);
                ficha.setAltura(0);
                ficha.setImc(0);
                ficha.setCliente(null);
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de inclusão da ficha!"); }
    }

    public Object consultar(Object obj){   
    	
    	FichaMedica ficha = (FichaMedica) obj;
    	
        try
        {   
        	Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_ficha_medica "
            						+ " WHERE (cliente = '"+ ficha.getCliente().getId() +"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            
            if (result.next()){   
            	System.out.println("Cliente existente !" );
                System.out.println("Dados da ficah medica");
                System.out.println("peso.............: "+ result.getString("peso_atua"));
                System.out.println("altura...............: "+ result.getString("altura"));
                System.out.println("imc...............: "+ result.getString("imc"));
                System.out.println("id do Cliente: "+ result.getString("cliente"));
                
                ficha.setId(Integer.parseInt(result.getString("id")));
                ficha.setPeso(Float.parseFloat(result.getString("peso_atua")));
                ficha.setAltura(Float.parseFloat(result.getString("altura")));
                ficha.setImc(Float.parseFloat(result.getString("imc")));
                ficha.getCliente().setId(Integer.parseInt(result.getString("cliente")));
                result.close();
            }
            else{   
            	System.out.println( "Ficha medica não encontrada !\n" );
            	ficha = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de consulta da ficha medica !"); 
        }
        return ficha;
    }

    public Object consultar(String cpf){   
    	
        FichaMedica ficha = new FichaMedica();   
        
        System.out.println("Metodo Consultar de DMCliente - CPF: "+cpf);
        try
        {   Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_ficha_medica"
									+ " WHERE (cliente = '"+ ficha.getCliente().getId() +"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            
            ResultSet result = statement.executeQuery(consultarSQL);
            
            if (result.next())
            {   System.out.println("ficha medica existente !" );
            	System.out.println("Id.............: "+ result.getString("id"));
            	System.out.println("peso.............: "+ result.getString("peso_atua"));
                System.out.println("altura...............: "+ result.getString("altura"));
                System.out.println("imc...............: "+ result.getString("imc"));
                System.out.println("id do Cliente: "+ result.getString("cliente"));
                
                ficha.setId(Integer.parseInt(result.getString("id")));
                ficha.setPeso(Float.parseFloat(result.getString("peso_atua")));
                ficha.setAltura(Float.parseFloat(result.getString("altura")));
                ficha.setImc(Float.parseFloat(result.getString("imc")));
                result.close();
            }
            else
            {   System.out.println( "ficha medica não encontrada !\n" );
            	ficha = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de consulta da ficha medica !"); }
                
        return ficha;
    }
    

    public void excluir(Object obj){
    	
    	FichaMedica objCliente = (FichaMedica) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String excluirSQL = "DELETE FROM tb_ficha_medica WHERE (cliente = '"+objCliente.getCliente().getId()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(excluirSQL) + "\n");
            int result = statement.executeUpdate(excluirSQL);
            if (result == 1)
            {   JOptionPane.showMessageDialog(null,"ficha medica excluído corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }
            else
            {  
            	System.out.println( "\nErro ao excluir ficha medica !\n" );
            }
           statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de exclusão da ficha medica !"); }
        
    }

    public void alterar(Object obj){  
    	
    	FichaMedica objCliente = (FichaMedica) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String alterarSQL = "UPDATE tb_ficha_medica SET peso_atua = '"+objCliente.getPeso()+"' ,"+
                                "altura = '"+objCliente.getAltura()+"' " +
                                "imc = '"+objCliente.getImc()+"' " +
                                "WHERE (id = '"+objCliente.getCliente().getId()+"') ";
            
            int result = statement.executeUpdate(alterarSQL);
            if (result == 1)
            {	JOptionPane.showMessageDialog(null,"Dados da ficha medica alterado!");
            	System.out.println( "plano alterado corretamente !"); }
            else
            {   JOptionPane.showMessageDialog(null,"Impossivel alterar esta ficha medica !");
            	System.out.println( "\nErro ao alterar cliente !\n" );
            }
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de atualização da ficha medica !"); }
 
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
