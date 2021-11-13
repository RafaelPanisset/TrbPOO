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

public class TelaFichaMedica extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lPesoAtual, lAltura, lImc, lCpfCliente;
	private JTextField tPesoAtual, tAltura,tImc, tCpfCliente;
	private JButton bSalvar, bLimpar, bBuscar, bExcluir, bEditar, bFechar;
	private JPanel pFichaMedica;
	
	String pesoAtual, altura, imc; 
	Cliente cpfCliente;
	
	FichaMedica ficha;
	
	public TelaFichaMedica()
	{
		this.setSize(690,200);
		this.setTitle("Tela de Gerenciamento da ficha medica");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../GYM/src/images/fichaMedica.png");
		this.setIconImage(img);
		
		pFichaMedica = new JPanel();
		pFichaMedica.setSize(660,90);
		pFichaMedica.setLocation(10,10);
		pFichaMedica.setBorder(BorderFactory.createTitledBorder("DADOS PARA A FICHA MEDICA:"));
		pFichaMedica.setLayout(null);
		
		lPesoAtual = new JLabel("peso atual: ");
		lPesoAtual.setSize(200,30);
		lPesoAtual.setLocation(20,20);
		
		tPesoAtual = new JTextField();
		tPesoAtual.setSize(140,20);
		tPesoAtual.setLocation(130,25);

		lAltura = new JLabel("altura: ");
		lAltura.setSize(200,30);
		lAltura.setLocation(360,20);
		
		tAltura = new JTextField();
		tAltura.setSize(140,20);
		tAltura.setLocation(450,25);
		
		lImc = new JLabel("Imc: ");
		lImc.setSize(200,20);
		lImc.setLocation(360,50);
		
		tImc = new JTextField();
		tImc.setSize(140,20);
		tImc.setLocation(450,55);
		
		lCpfCliente = new JLabel("CPF do cliente: ");
		lCpfCliente.setSize(120,20);
		lCpfCliente.setLocation(20,50);
		
		tCpfCliente = new JTextField();
		tCpfCliente.setSize(140,20);
		tCpfCliente.setLocation(130,55);
		
		class BatSinal extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bSalvar) {
					
					pesoAtual = tPesoAtual.getText();
					altura = tAltura.getText().trim();
					imc = tImc.getText().trim();
					cpfCliente = new Cliente(tCpfCliente.getText());	
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						ficha = new FichaMedica(Float.parseFloat(pesoAtual),Float.parseFloat(altura),Float.parseFloat(imc),cpfCliente);
						ficha.incluir(ficha);
					} else {
						JOptionPane.showMessageDialog(null,"Cliente não encontrado!","AVISO",JOptionPane.WARNING_MESSAGE);
			        	System.out.println("Cliente não encontrado!");
					}
				}
				
				if (e.getSource() == bLimpar) {
					tPesoAtual.setText("");
					tAltura.setText("");
					tImc.setText("");
					tCpfCliente.setText("");	
				}
				
				if (e.getSource() == bBuscar)
				{
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						ficha = new FichaMedica(Float.parseFloat(pesoAtual),Float.parseFloat(altura),Float.parseFloat(imc),cpfCliente);
						ficha.imprimir();
					}
				}
				
				if (e.getSource() == bExcluir) {
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						ficha = new FichaMedica(Float.parseFloat(pesoAtual),Float.parseFloat(altura),Float.parseFloat(imc),cpfCliente);
						ficha.incluir(ficha);
					}
				}
				
				if (e.getSource() == bEditar) {
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						ficha = new FichaMedica(Float.parseFloat(pesoAtual),Float.parseFloat(altura),Float.parseFloat(imc),cpfCliente);
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

		this.add(pFichaMedica);
		pFichaMedica.add(lPesoAtual);
		pFichaMedica.add(lAltura);
		pFichaMedica.add(lImc);
		pFichaMedica.add(lCpfCliente);	
		pFichaMedica.add(tPesoAtual);
		pFichaMedica.add(tAltura);
		pFichaMedica.add(tImc);
		pFichaMedica.add(tCpfCliente);
		
		pFichaMedica.add(tAltura);
		pFichaMedica.add(tCpfCliente);
		
		this.add(bSalvar);
		this.add(bLimpar);
		this.add(bBuscar);
		this.add(bExcluir);
		this.add(bEditar);
		this.add(bFechar);
		
		this.repaint();					
	}	
}
