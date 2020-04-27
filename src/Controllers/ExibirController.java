package Controllers;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.LivroDAO;
import Models.Livro;

public class ExibirController {

//	MOCK
//	public String column[]={"ID","NAME","SALARY"}; 
//	public String data[][] = { {"101","Amit","670000"},    
//            {"102","Jai","780000"},    
//            {"101","Sachin","700000"}};   

	
	private JTable table;
	public String column[]={"Título","Autor", "Genero", "Ano", "Já Leu?"};         

	public ExibirController(JTable table) {
		this.table = table;
		this.getLivros();
	}
	
	public String getLivros() {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	
		
		for (String columnName : this.column) {
			model.addColumn(columnName);
		}
		
		
		try {
			LivroDAO livroDao = new LivroDAO();
			List<Livro> list = livroDao.getAllLivros();
			
			for (Livro livro : list) {
				model.addRow(new Object[] {
						livro.getTitle(),
						livro.getAuthor(),
						livro.getGender(),
						livro.getAuthor(),
						livro.isRead()
				});
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
}
