package application;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane pane = new Pane();
		
		Label label=new Label("Compress/Decompress File");
		label.setFont(new Font(20));
		pane.getChildren().add(label);
		label.setTranslateX(160);
		label.setTranslateY(10);

		Button compress = new Button("Compress");
		compress.setPrefSize(180, 50);
		pane.getChildren().add(compress);
		compress.setTranslateX(70);
		compress.setTranslateY(50);
		compress.setFont(new Font(15));

		Button decompress = new Button("Decompress");
		decompress.setPrefSize(180, 50);
		pane.getChildren().add(decompress);
		decompress.setTranslateX(330);
		decompress.setTranslateY(50);
		decompress.setFont(new Font(15));
		
		Button Run = new Button("Show Run");
		Run.setPrefSize(180, 50);
		pane.getChildren().add(Run);
		Run.setTranslateX(250);
		Run.setTranslateY(430);
		Run.setFont(new Font(15));

		TextArea ta = new TextArea();
		ta.setPrefSize(440, 300);
		pane.getChildren().add(ta);
		ta.setTranslateX(70);
		ta.setTranslateY(120);
		ta.setFont(new Font(15));

		compress.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(primaryStage);
			try {
				Huffman.compress(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			Run.setOnAction(s -> {
				ta.appendText("File path: " + file.getPath() + "\nCompressed file path: " + Huffman.outFileName
					+ "\n"	 );
				for (int i = 0; i < Huffman.huffCodeArraySize; i++) {

					ta.appendText(Huffman.huffCodeArray[i].huffCode + " ");
			
				}
				ta.appendText("\n" + "Original File Size : " + Huffman.originalSize + " Byte. \n");
				ta.appendText("Compression File Size : " + Huffman.compressedSize + " Byte. \n");
				
				
				ta.appendText("\n\nASCII\tCharacter\t\tFrequency\tHuffCode\n" ) ;
				
				
				for (int k = 0; k < Huffman.huffCodeArray.length; k++) {
					if((int)Huffman.huffCodeArray[k].character==10 || (int)Huffman.huffCodeArray[k].character==9)
						continue;
					ta.appendText(String.valueOf((int) Huffman.huffCodeArray[k].character) + "\t\t  "
							+ Huffman.huffCodeArray[k].character + "\t\t\t"
							+ String.valueOf(Huffman.huffCodeArray[k].counter) + "\t\t\t"
							+ Huffman.huffCodeArray[k].huffCode+"\n");
				}
				
			});

			

		});
		
	
		decompress.setOnAction(e->{
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(primaryStage);
			ta.appendText("File path: " + file.getPath());
			Huffman.deCompress(file);
			
		});

		Scene scene = new Scene(pane, 600, 500);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Huffman Project");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}