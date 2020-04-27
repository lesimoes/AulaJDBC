package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Models.Livro;

public class CadastroLivroController implements ActionListener {

	private JTextField fieldTitle;
	private JTextField fieldAuthor;
	private JComboBox comboGender;
	private JComboBox comboYear;
	private JCheckBox isRead;
	private List<Livro> listLivros;
	
	
	public CadastroLivroController(
			JTextField fieldTitle, 
			JTextField fieldAuthor,
			JComboBox comboGender,
			JComboBox comboYear,
			JCheckBox isRead) {
		
		this.fieldTitle = fieldTitle;
		this.fieldAuthor = fieldAuthor;
		this.isRead = isRead;
		
		listLivros = new ArrayList<>();
		
		this.populateComboYear(comboYear);
		this.populateComboGener(comboGender);
	}
	
	private void populateComboYear(JComboBox comboYear) {
		this.comboYear = comboYear;
		
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR); 
		for (int yearLoop = anoAtual ; yearLoop >= 1980 ; yearLoop --) {
			this.comboYear.addItem(yearLoop);
		}
	}
	
	private void populateComboGener(JComboBox comboGender) {
		this.comboGender = comboGender;
		String[] genders = new String[] { "Terror", "Ficção", "Drama", "Aventura" };
		for (String genderLoop : genders) {
			this.comboGender.addItem(genderLoop);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getActionCommand());
		this.cadastrarLivro();
	}
	
	public void cadastrarLivro() {
		
		Livro livro = new Livro(
				this.fieldTitle.getText(), 
				this.fieldAuthor.getText(),
				this.comboGender.getSelectedItem().toString(),
				Integer.parseInt(this.comboYear.getSelectedItem().toString()),
				this.isRead.isSelected()
				);
		
		listLivros.add(livro);
		
		for (int i = 0 ; i < listLivros.size() ; i ++ ) {
			System.out.println(listLivros.get(i).toString());
		}
		
	}

}
