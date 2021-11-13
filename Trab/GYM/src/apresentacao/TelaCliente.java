package apresentacao;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.*;

public class TelaCliente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lNome, lCpf, lDataNascimento;
	private JTextField tNome, tCpf, tDataNascimento;
	private JButton bSalvar, bLimpar,bBuscar, bExcluir, bEditar, bFechar;
	private JPanel pCliente;
	
	String cpf,nome,dataNascimento;
	
	Cliente cliente;
	
	public TelaCliente()
	{
		this.setSize(700,200);
		this.setTitle("Tela de Gerenciamento do Cliente");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../GYM/src/images/cliente.png");
		this.setIconImage(img);
		
		pCliente = new JPanel();
		pCliente.setSize(660,90);
		pCliente.setLocation(10,10);
		pCliente.setBorder(BorderFactory.createTitledBorder("DADOS DO CLIENTE:"));
		pCliente.setLayout(null);
		
		lCpf = new JLabel("Número do Cadastro de Pessoa Física - CPF: ");
		lCpf.setSize(280,30);
		lCpf.setLocation(20,20);
		
		tCpf = new JTextField();
		tCpf.setSize(120,20);
		tCpf.setLocation(280,25);

		lDataNascimento = new JLabel("Data de Nascimento:");
		lDataNascimento.setSize(120,30);
		lDataNascimento.setLocation(420,20);
		
		tDataNascimento = new JTextField();
		tDataNascimento.setSize(90,20);
		tDataNascimento.setLocation(550,25);
		tDataNascimento.setText("dd/mm/aaaa");
		
		lNome = new JLabel("Nome Completo: ");
		lNome.setSize(120,30);
		lNome.setLocation(20,50);
		
		tNome = new JTextField();
		tNome.setSize(510,20);
		tNome.setLocation(130,55);
		
		class BatSinal extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bSalvar) {
					
					nome = tNome.getText();
					cpf = tCpf.getText();
					dataNascimento = tDataNascimento.getText();
					cliente = new Cliente(nome,cpf,dataNascimento);			
				}
				
				if (e.getSource() == bLimpar) {
					tNome.setText("");
					tCpf.setText("");
					tDataNascimento.setText("");
				}
				
				if (e.getSource() == bBuscar)
				{
					cliente = new Cliente();
		        	cliente = (Cliente) cliente.pesquisar(tCpf.getText());
		        	cliente.imprimir();
				}
				
				if (e.getSource() == bExcluir) {
					cliente = new Cliente();
					cliente = (Cliente) cliente.pesquisar(tCpf.getText());
					cliente.excluir(cliente);
				}
				
				if (e.getSource() == bEditar) {
					cliente = new Cliente(tNome.getText(),tDataNascimento.getText());
					cliente = (Cliente) cliente.pesquisar(tCpf.getText());
					cliente.alterar();
				}
				
				if (e.getSource() == bFechar) {
					dispose();  
				}
			}	
		}
		
		BatSinal batman = new BatSinal();
				
		bSalvar = new JButton("Salvar");
		bSalvar.setSize(95,30);
		bSalvar.setLocation(35,120);
		bSalvar.setForeground(Color.white);
		bSalvar.setBackground(Color.black);
		bSalvar.addMouseListener(batman);
		
		bLimpar = new JButton("Limpar");
		bLimpar.setSize(95,30);
		bLimpar.setLocation(140,120);
		bLimpar.setForeground(Color.white);
		bLimpar.setBackground(Color.black);
		bLimpar.addMouseListener(batman);
				
		bBuscar = new JButton("Buscar");
		bBuscar.setSize(95,30);
		bBuscar.setLocation(245,120);
		bBuscar.setForeground(Color.white);
		bBuscar.setBackground(Color.black);
		bBuscar.addMouseListener(batman);

		bExcluir = new JButton("Excluir");
		bExcluir.setSize(95,30);
		bExcluir.setLocation(350,120);
		bExcluir.setForeground(Color.white);
		bExcluir.setBackground(Color.black);
		bExcluir.addMouseListener(batman);
		
		bEditar = new JButton("Editar");
		bEditar.setSize(95,30);
		bEditar.setLocation(455,120);
		bEditar.setForeground(Color.white);
		bEditar.setBackground(Color.black);
		bEditar.addMouseListener(batman);
		
		bFechar = new JButton("Fechar");
		bFechar.setSize(95,30);
		bFechar.setLocation(560,120);
		bFechar.setForeground(Color.white);
		bFechar.setBackground(Color.black);
		bFechar.addMouseListener(batman);

		this.add(pCliente);
		pCliente.add(lNome);
		pCliente.add(lCpf);
		pCliente.add(lDataNascimento);	
		pCliente.add(tNome);
		pCliente.add(tCpf);
		pCliente.add(tDataNascimento);
		
		this.add(bSalvar);
		this.add(bLimpar);
		this.add(bBuscar);
		this.add(bExcluir);
		this.add(bEditar);
		this.add(bFechar);
		this.repaint();					
	}	
}
