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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.FichaMedica;
import modelo.Registro;

public class TelaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lNumSemana, lAno, lDiaSemana, lCpfCliente;
	private JTextField tNumSemana, tAno,tDiaSemana, tCpfCliente;
	private JButton bSalvar, bLimpar, bBuscar, bExcluir, bEditar, bFechar;
	private JPanel pRegistro;
	
	String numSemana, ano, diaSemana; 
	Cliente cpfCliente;
	
	FichaMedica ficha;
	Registro registro;
	public TelaRegistro()
	{
		this.setSize(690,200);
		this.setTitle("Tela de Gerenciamento do registro");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../GYM/src/images/registro.png");
		this.setIconImage(img);
		
		pRegistro = new JPanel();
		pRegistro.setSize(660,90);
		pRegistro.setLocation(10,10);
		pRegistro.setBorder(BorderFactory.createTitledBorder("DADOS PARA REGISTRO:"));
		pRegistro.setLayout(null);
		
		lNumSemana = new JLabel("Numero da Semana: ");
		lNumSemana.setSize(200,30);
		lNumSemana.setLocation(20,20);
		
		tNumSemana = new JTextField();
		tNumSemana.setSize(140,20);
		tNumSemana.setLocation(130,25);

		lAno = new JLabel("Ano: ");
		lAno.setSize(200,30);
		lAno.setLocation(360,20);
		
		tAno = new JTextField();
		tAno.setSize(140,20);
		tAno.setLocation(450,25);
		
		lDiaSemana = new JLabel("Dia da semana: ");
		lDiaSemana.setSize(200,20);
		lDiaSemana.setLocation(360,50);
		
		tDiaSemana = new JTextField();
		tDiaSemana.setSize(140,20);
		tDiaSemana.setLocation(450,55);
		
		lCpfCliente = new JLabel("CPF do cliente: ");
		lCpfCliente.setSize(120,20);
		lCpfCliente.setLocation(20,50);
		
		tCpfCliente = new JTextField();
		tCpfCliente.setSize(140,20);
		tCpfCliente.setLocation(130,55);
		
		class BatSinal extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bSalvar) {
					
					numSemana = tNumSemana.getText();
					ano = tAno.getText().trim();
					diaSemana = tDiaSemana.getText().trim();
					cpfCliente = new Cliente(tCpfCliente.getText());	
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						registro = new Registro(Integer.parseInt(numSemana),Integer.parseInt(ano),Integer.parseInt(diaSemana),cpfCliente);
						//ficha.incluir(ficha);
						registro.incluir(registro);
					} else {
						JOptionPane.showMessageDialog(null,"Cliente não encontrado!","AVISO",JOptionPane.WARNING_MESSAGE);
			        	System.out.println("Cliente não encontrado!");
					}
				}
				
				if (e.getSource() == bLimpar) {
					tNumSemana.setText("");
					tAno.setText("");
					tDiaSemana.setText("");
					tCpfCliente.setText("");	
				}
				
				if (e.getSource() == bBuscar)
				{
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						ficha = new FichaMedica(Integer.parseInt(numSemana),Integer.parseInt(ano),Integer.parseInt(diaSemana),cpfCliente);
						ficha.imprimir();
					}
				}
				
				if (e.getSource() == bExcluir) {
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						ficha = new FichaMedica(Integer.parseInt(numSemana),Integer.parseInt(ano),Integer.parseInt(diaSemana),cpfCliente);
						ficha.incluir(ficha);
					}
				}
				
				if (e.getSource() == bEditar) {
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						ficha = new FichaMedica(Integer.parseInt(numSemana),Integer.parseInt(ano),Integer.parseInt(diaSemana),cpfCliente);
						ficha.alterar();
					}
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

		this.add(pRegistro);
		pRegistro.add(lNumSemana);
		pRegistro.add(lAno);
		pRegistro.add(lDiaSemana);
		pRegistro.add(lCpfCliente);	
		pRegistro.add(tNumSemana);
		pRegistro.add(tAno);
		pRegistro.add(tDiaSemana);
		pRegistro.add(tCpfCliente);
		
		pRegistro.add(tAno);
		pRegistro.add(tCpfCliente);
		
		this.add(bSalvar);
		this.add(bLimpar);
		this.add(bBuscar);
		this.add(bExcluir);
		this.add(bEditar);
		this.add(bFechar);
		
		this.repaint();					
	}	
}
