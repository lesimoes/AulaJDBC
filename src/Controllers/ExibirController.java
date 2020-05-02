package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import DAO.LivroDAO;
import Listeners.TableLivroListener;
import Models.Livro;

public class ExibirController implements ActionListener  {
	
	private JTable table;
	public String column[]={"Título","Autor", "Genero", "Ano", "Já Leu?"};         
	private DefaultTableModel model;
	
	public ExibirController(JTable table) throws SQLException {
		this.table = table;
		this.model = (DefaultTableModel) table.getModel();
//		table.getModel().addTableModelListener(new TableLivroListener(table));
		this.setTableColumns();
		this.getLivros();
		table.getModel().addTableModelListener(new TableLivroListener(table));

	}
	
	private void setTableColumns() {
		for (String columnName : this.column) {
			model.addColumn(columnName);
		}
	}
	
	public String getLivros() {
		
		this.model.setRowCount(0);
			
		
		try {
			LivroDAO livroDao = new LivroDAO();
			List<Livro> list = livroDao.getAllLivros();
			
			for (Livro livro : list) {
				model.addRow(new Object[] {
						livro.getTitle(),
						livro.getAuthor(),
						livro.getGender(),
						livro.getYear(),
						livro.isRead()
				});
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getLivros();
		
	}




	
}
