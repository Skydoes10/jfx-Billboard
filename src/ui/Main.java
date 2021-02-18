package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InfrastructureDepartment;

public class Main extends Application {
	public final static int EXIT_OPTION = 5;
	private InfrastructureDepartment InfDepartment;
	private BillboardGUI billboardGUI;
	
	public Main() {
		InfDepartment = new InfrastructureDepartment();
		billboardGUI = new BillboardGUI(InfDepartment);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Billboard.fxml"));
		fxmlLoader.setController(billboardGUI);
		Parent root = fxmlLoader.load();		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Billboard");
		primaryStage.show();
	}

}
