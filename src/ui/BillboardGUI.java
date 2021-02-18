package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Billboard;
import model.InfrastructureDepartment;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BillboardGUI {

	@FXML
	private BorderPane mainPanel;
	
	@FXML
    private TextField txtWidth;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtBrand;

    @FXML
    private RadioButton rbYes;

    @FXML
    private RadioButton rbNo;
    
    @FXML
    private TableView<Billboard> tvBillboardsList;

    @FXML
    private TableColumn<Billboard, String> tcWidth;

    @FXML
    private TableColumn<Billboard, String> tcHeight;
    
    @FXML
    private TableColumn<Billboard, String> tcArea;

    @FXML
    private TableColumn<Billboard, String> tcInUse;

    @FXML
    private TableColumn<Billboard, String> tcBrand;
    
     private ToggleGroup group;
    
    private InfrastructureDepartment infDepartment;

  	public BillboardGUI(InfrastructureDepartment id) {
  		infDepartment = id;
  	}

  	public void initialize() {
  		
  	}

  	private void initializeTableView() {
  		ObservableList<Billboard> observableList;
    	observableList = FXCollections.observableArrayList(infDepartment.getBillboards());
    	
    	tvBillboardsList.setItems(observableList);
		tcWidth.setCellValueFactory(new PropertyValueFactory<Billboard,String>("width"));
		tcHeight.setCellValueFactory(new PropertyValueFactory<Billboard,String>("height"));
		tcArea.setCellValueFactory(new PropertyValueFactory<Billboard,String>("area"));
		tcInUse.setCellValueFactory(new PropertyValueFactory<Billboard,String>("in use"));
		tcBrand.setCellValueFactory(new PropertyValueFactory<Billboard,String>("brand"));
  	}
  	
  	@FXML
	public void loadAddBillboard(ActionEvent event) throws IOException {
  		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add-Billboard.fxml"));
    	fxmlLoader.setController(this);
    	Parent addBillboardPane = fxmlLoader.load();
    	
    	group = new ToggleGroup();
  		rbYes.setToggleGroup(group);
  		rbNo.setToggleGroup(group);
    	
    	mainPanel.getChildren().clear();
    	mainPanel.setCenter(addBillboardPane);
	}

	@FXML
	void loadShowBillboard(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Billboard-list.fxml"));
    	fxmlLoader.setController(this);
    	Parent billboardsPane = fxmlLoader.load();
    	
    	mainPanel.getChildren().clear();
    	mainPanel.setCenter(billboardsPane);
    	initializeTableView();
	}

	@FXML
	public void exportReport(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File f = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
        if (f != null) {
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Import Contacts");
            try {
            	infDepartment.exportDangerousBillboardReport(f.getAbsolutePath());
				alert.setContentText("The billboards was exported");

				alert.showAndWait();
			} catch (FileNotFoundException e) {
				alert.setContentText("The billboards was not exported");

				alert.showAndWait();
				e.printStackTrace();
			}
        }
	}

	@FXML
	public void importBillboards(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File f = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
    	if(f != null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Import Contacts");
    		try {
    			infDepartment.inportData(f.getAbsolutePath());
				alert.setContentText("The billboards was imported");

				alert.showAndWait();
			} catch (IOException e) {
				alert.setContentText("The billboards was not imported");

				alert.showAndWait();
				e.printStackTrace();
			}
    	}
	}

	@FXML
	public void About(ActionEvent event) {
		
	}

	@FXML
	public void Exit(ActionEvent event) {
		
	}
	
	@FXML
	public void addBillboard(ActionEvent event) {
		boolean option = false;
		if(rbYes.isSelected()) {
			option = true;
		}else if(rbNo.isSelected()) {
			option = false;
		}
		double newWidth = Double.parseDouble(txtWidth.getText());
    	double newheight = Double.parseDouble(txtHeight.getText());
    	
    	//String newOption = String.valueOf(option);
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Billboard added");
		try {
			infDepartment.addBillboard(newWidth,newheight, option, txtBrand.getText());
			txtWidth.setText("");
			txtHeight.setText("");
			txtBrand.setText("");
			alert.setContentText("The billboards was added");
			alert.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			alert.setContentText("The billboards was not added");
			alert.showAndWait();
			e.printStackTrace();
			
		}
    }
	
}
