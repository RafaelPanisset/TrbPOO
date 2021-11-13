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
import modelo.Plano;

public class TelaPlano extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lHoraSemanais, lMensalidade, lCpfCliente;
	private JTextField tHoraSemanais, tMensalidade, tCpfCliente;
	private JButton bSalvar, bLimpar, bBuscar, bExcluir, bEditar, bFechar;
	private JPanel pPlano;
	
	String horaSemanais, mensalidade; 
	Cliente cpfCliente;
	
	Plano plano;
	
	public TelaPlano()
	{
		this.setSize(690,200);
		this.setTitle("Tela de Gerenciamento do Plano");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../GYM/src/images/plano.png");
		this.setIconImage(img);
		
		pPlano = new JPanel();
		pPlano.setSize(660,90);
		pPlano.setLocation(10,10);
		pPlano.setBorder(BorderFactory.createTitledBorder("DADOS PARA O PLANO:"));
		pPlano.setLayout(null);
		
		lHoraSemanais = new JLabel("horas por dias: ");
		lHoraSemanais.setSize(200,30);
		lHoraSemanais.setLocation(20,20);
		
		tHoraSemanais = new JTextField();
		tHoraSemanais.setSize(120,20);
		tHoraSemanais.setLocation(200,25);

		lMensalidade = new JLabel("mensalidade do cliente: ");
		lMensalidade.setSize(100,30);
		lMensalidade.setLocation(360,20);
		
		tMensalidade = new JTextField();
		tMensalidade.setSize(120,20);
		tMensalidade.setLocation(450,25);
		
		lCpfCliente = new JLabel("CPF do cliente: ");
		lCpfCliente.setSize(120,30);
		lCpfCliente.setLocation(20,50);
		
		tCpfCliente = new JTextField();
		tCpfCliente.setSize(510,20);
		tCpfCliente.setLocation(130,55);
		
		class BatSinal extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bSalvar) {
					
					horaSemanais = tHoraSemanais.getText();
					mensalidade  = tMensalidade.getText().trim();
					cpfCliente = new Cliente(tCpfCliente.getText());	
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						plano = new Plano(Integer.parseInt(horaSemanais),Integer.parseInt(mensalidade),cpfCliente);
						plano.incluir(plano);
					} else {
						JOptionPane.showMessageDialog(null,"Cliente não encontrado!","AVISO",JOptionPane.WARNING_MESSAGE);
			        	System.out.println("Cliente não encontrado!");
					}
				}
				
				if (e.getSource() == bLimpar) {
					tHoraSemanais.setText("");
					tMensalidade.setText("");
					tCpfCliente.setText("");	
				}
				
				if (e.getSource() == bBuscar)
				{
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						plano = new Plano(Integer.parseInt(horaSemanais),Integer.parseInt(mensalidade),cpfCliente);
						plano.imprimir();
					}
				}
				
				if (e.getSource() == bExcluir) {
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						plano = new Plano(Integer.parseInt(horaSemanais),Integer.parseInt(mensalidade),cpfCliente);
						plano.excluir(plano);
					}
				}
				
				if (e.getSource() == bEditar) {
					
					if(cpfCliente != null) {
						plano = new Plano(Integer.parseInt(horaSemanais),Integer.parseInt(mensalidade),cpfCliente);
						plano.alterar();
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

		this.add(pPlano);
		pPlano.add(lHoraSemanais);
		pPlano.add(lMensalidade);
		pPlano.add(lCpfCliente);	
		pPlano.add(tHoraSemanais);
		pPlano.add(tMensalidade);
		pPlano.add(tCpfCliente);
		
		this.add(bSalvar);
		this.add(bLimpar);
		this.add(bBuscar);
		this.add(bExcluir);
		this.add(bEditar);
		this.add(bFechar);
		this.repaint();					
	}	
}
