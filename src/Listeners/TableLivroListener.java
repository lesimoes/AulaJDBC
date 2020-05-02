package Listeners;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Models.Livro;
import DAO.LivroDAO;

import javax.swing.event.TableModelEvent;


public class TableLivroListener implements TableModelListener {

	private JTable table;
	private DefaultTableModel model;
	private LivroDAO livroDao;
	
	public TableLivroListener(JTable table) throws SQLException {
		this.table = table;
		this.model = (DefaultTableModel) table.getModel();
		livroDao = new LivroDAO();
	}
	
	private void updateTable(TableModelEvent tableChange) throws SQLException {
		System.out.println("Update");
		System.out.println(tableChange.getFirstRow());
		int rowChanged = tableChange.getFirstRow();
		ArrayList columnsValues = new ArrayList();
		for (int columnIndex = 0 ; columnIndex < this.model.getColumnCount() ;  columnIndex ++) {
			System.out.println(this.model.getValueAt(rowChanged, columnIndex));
			columnsValues.add(this.model.getValueAt(rowChanged, columnIndex));
		}
		Livro livro = new Livro(
				columnsValues.get(0).toString(),
				columnsValues.get(1).toString(),
				columnsValues.get(2).toString(),
				Integer.parseInt(columnsValues.get(3).toString()),
				Boolean.parseBoolean(columnsValues.get(4).toString())
				);
		
		System.out.println(livro.toString());
		livroDao.updateLivroById(rowChanged + 1, livro);
	
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		switch(e.getType()) {
		case TableModelEvent.UPDATE: try {
				this.updateTable(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		}
		
	}

}
