package controladores;

import clases.Periodico;
import clases.Minizinc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import clases.Articulo;

public class ControlMainGUI {
	private Periodico periodico = new Periodico();
	private Minizinc mini = new Minizinc();
	
	@FXML 
	private TableView<Articulo> tbl;
	@FXML 
	private TableColumn<Articulo, String> topic;
	@FXML
	private TableColumn<Articulo, Integer> min;
	@FXML 
	private TableColumn<Articulo, Integer> max;
	@FXML 
	private TableColumn<Articulo, Integer> pot;
	@FXML 
	private Button btn_add;
	@FXML 
	private Button btn_del;
	@FXML 
	private Button btn_calc;
	@FXML 
	private TextField txt_lect;
	@FXML 
	private TextField txt_max;
	@FXML 
	private TextField txt_min;
	@FXML 
	private TextField txt_paginasp;
	@FXML 
	private TextField txt_tema;
	
	
	public void initialize() {
		txt_paginasp.setText(Integer.toString(periodico.getPaginas()));
		
    	topic.setCellValueFactory(new PropertyValueFactory<Articulo, String>("tema"));
    	min.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("paginasMin"));
    	max.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("paginasMax"));
    	pot.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("potencial"));
    	
    	tbl.setItems(getArticulos());
    	
    	tbl.setEditable(true);
    	topic.setCellFactory(TextFieldTableCell.forTableColumn());
    	min.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
    	max.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
    	pot.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
    }
	
	public void actualizar() {
		txt_paginasp.setText(Integer.toString(periodico.getPaginas()));
		
    	topic.setCellValueFactory(new PropertyValueFactory<Articulo, String>("tema"));
    	min.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("paginasMin"));
    	max.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("paginasMax"));
    	pot.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("potencial"));
    	
    	tbl.setItems(getArticulos());
	}

	private ObservableList<Articulo> getArticulos(){
		ObservableList<Articulo> articulos = FXCollections.observableArrayList();
		for (int i = 0; i < periodico.getArticulos().size(); i++) {
			  articulos.add(periodico.getArticulos().get(i));
			}
		return articulos;
	}
	
	@FXML
	private void changeTopic(CellEditEvent editedCell) {
		Articulo articuloSelect = tbl.getSelectionModel().getSelectedItem();
		articuloSelect.setTema(editedCell.getNewValue().toString());
	}
	
	@FXML
	private void changeMin(CellEditEvent editedCell) {
		Articulo articuloSelect = tbl.getSelectionModel().getSelectedItem();
		articuloSelect.setPaginasMin(Integer.parseInt(editedCell.getNewValue().toString()));
	}
	
	@FXML
	private void changeMax(CellEditEvent editedCell) {
		Articulo articuloSelect = tbl.getSelectionModel().getSelectedItem();
		articuloSelect.setPaginasMax(Integer.parseInt(editedCell.getNewValue().toString()));
	}
	
	@FXML
	private void changePot(CellEditEvent editedCell) {
		Articulo articuloSelect = tbl.getSelectionModel().getSelectedItem();
		articuloSelect.setPotencial(Integer.parseInt(editedCell.getNewValue().toString()));
	}
	
	@FXML
	private void changePages() {
		periodico.setPaginas(Integer.parseInt(txt_paginasp.getText()));
	}
	
	@FXML
	private void addTopic() {
		String tema = txt_tema.getText();
		int minimo = Integer.valueOf(txt_min.getText());
		int maximo = Integer.valueOf(txt_max.getText());
		int personas = Integer.valueOf(txt_lect.getText());
		Articulo nuevo = new Articulo(tema, minimo, maximo, personas);
		periodico.getArticulos().add(nuevo);
		
		actualizar();
	}
	
	@FXML
	private void delete() {
		Articulo articuloSelect = tbl.getSelectionModel().getSelectedItem();
		periodico.getArticulos().remove(articuloSelect);
		actualizar();
	}
	
	@FXML
	private void ejecutar() {
		mini.dnz(periodico);
		
		String result = mini.mzn();
		
		Alert alert = new Alert(AlertType.INFORMATION, result);
		alert.setHeaderText("Resultado Minizinc");
		alert.showAndWait();
	}
}
