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
import modelo.Pagamento;

public class TelaPagamento extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lValor, lDataRealizada, lCpfCliente;
	private JTextField tValor, tdataRealizada, tCpfCliente;
	private JButton bSalvar, bLimpar, bBuscar, bExcluir, bEditar, bFechar;
	private JPanel pPagamento;
	
	String valor, dataRealizada; 
	Cliente cpfCliente;
	
	Pagamento pagamento;
	
	public TelaPagamento()
	{
		this.setSize(690,200);
		this.setTitle("Tela de Gerenciamento do pagamento");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../GYM/src/images/pagamento.png");
		this.setIconImage(img);
		
		pPagamento = new JPanel();
		pPagamento.setSize(660,90);
		pPagamento.setLocation(10,10);
		pPagamento.setBorder(BorderFactory.createTitledBorder("DADOS PARA O PAGAMENTO:"));
		pPagamento.setLayout(null);
		
		lValor = new JLabel("Valor: ");
		lValor.setSize(200,30);
		lValor.setLocation(20,20);
		
		tValor = new JTextField();
		tValor.setSize(120,20);
		tValor.setLocation(200,25);

		lDataRealizada = new JLabel("Data realizada: ");
		lDataRealizada.setSize(100,30);
		lDataRealizada.setLocation(360,20);
		
		tdataRealizada = new JTextField();
		tdataRealizada.setSize(120,20);
		tdataRealizada.setLocation(450,25);
		
		lCpfCliente = new JLabel("CPF do cliente: ");
		lCpfCliente.setSize(120,30);
		lCpfCliente.setLocation(20,50);
		
		tCpfCliente = new JTextField();
		tCpfCliente.setSize(510,20);
		tCpfCliente.setLocation(130,55);
		
		class BatSinal extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bSalvar) {
					
					valor = tValor.getText();
					dataRealizada  = tdataRealizada.getText().trim();
					cpfCliente = new Cliente(tCpfCliente.getText());	
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						pagamento = new Pagamento(Float.parseFloat(valor),dataRealizada,cpfCliente);
						pagamento.incluir(pagamento);
					} else {
						JOptionPane.showMessageDialog(null,"Cliente não encontrado!","AVISO",JOptionPane.WARNING_MESSAGE);
			        	System.out.println("Cliente não encontrado!");
					}
				}
				
				if (e.getSource() == bLimpar) {
					tValor.setText("");
					tdataRealizada.setText("");
					tCpfCliente.setText("");	
				}
				
				if (e.getSource() == bBuscar)
				{
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						pagamento = new Pagamento(Float.parseFloat(valor),dataRealizada,cpfCliente);
						pagamento.imprimir();
					}
				}
				
				if (e.getSource() == bExcluir) {
					cpfCliente = (Cliente) cpfCliente.pesquisar(tCpfCliente.getText());
					
					if(cpfCliente != null) {
						pagamento = new Pagamento(Float.parseFloat(valor),dataRealizada,cpfCliente);
						pagamento.excluir(pagamento);
					}
				}
				
				if (e.getSource() == bEditar) {
					
					if(cpfCliente != null) {
						pagamento = new Pagamento(Float.parseFloat(valor),dataRealizada,cpfCliente);
						pagamento.alterar();
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

		this.add(pPagamento);
		pPagamento.add(lValor);
		pPagamento.add(lDataRealizada);
		pPagamento.add(lCpfCliente);	
		pPagamento.add(tValor);
		pPagamento.add(tdataRealizada);
		pPagamento.add(tCpfCliente);
		
		this.add(bSalvar);
		this.add(bLimpar);
		this.add(bBuscar);
		this.add(bExcluir);
		this.add(bEditar);
		this.add(bFechar);
		this.repaint();					
	}	
}
