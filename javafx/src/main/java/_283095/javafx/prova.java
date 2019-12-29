package _283095.javafx;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class prova extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		StackPane root = new StackPane();
		
		//Bottone 
		Button btn = new Button();
		btn.setText("Nome del bottone");
		btn.setOnAction(e -> System.out.println("Hello Wordl!"));
		btn.setMaxSize(100,100);
		//Bottone come immagine
		try {
			FileInputStream input = new FileInputStream("Facebook.png");
		    Image image = new Image(input);
		    ImageView imageView = new ImageView(image);
		
		    Button imageBtn = new Button("Facebook", imageView);
		    imageBtn.setMaxSize(100, 200);
		    root.getChildren().add(imageBtn);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root,800,800);		
		primaryStage.setTitle("Hello World!!!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
