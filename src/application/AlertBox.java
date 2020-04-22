package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import javafx.geometry.*;
/**
 * This class create different type of pop up windows for alerts in JavaFX.
 * @author OBL Team13
 */

public class AlertBox implements Runnable{
	
	private int mode;
	private String title;
	private String message;
	private String buttonText;
	private Image icon;
	private String imgSrc;
	
	/**
	 * Simple AlertBox with a title, a message and s button to close.
	 * @param mode			decides which type of AlertBox will load.
	 * @param title			for the title of the pop up.
	 * @param message		the content of the pop up.
	 * @param buttonText	the name of the button.
	 */
	//FOR MODE 1
	public AlertBox(int mode, String title, String message, String buttonText) {		
		this.mode = mode;
		this.title = title;
		this.message = message;
		this.buttonText = buttonText;		
	}
	
	/**
	 * Improved version of AlertBox.
	 * @param mode			decides which type of AlertBox will load.
	 * @param title			for the title of the pop up.
	 * @param message		the content of the pop up.
	 * @param buttonText	the name of the button.
	 * @param icon			the icon to add to title line.
	 * @param imgSrc		the image that appear inside the pop up.
	 */
	//FOR MODE 2
	public AlertBox(int mode, String title, String message, String buttonText, Image icon, String imgSrc) {		
		this.mode = mode;
		this.title = title;
		this.message = message;
		this.buttonText = buttonText;		
		this.icon = icon;
		this.imgSrc = imgSrc;
	}
	
	/**
	 * Run one of the AlertBox, based on mode.
	 */
	@Override
	public void run() {
		
		switch(mode) {
		case 1:
			display(title, message, buttonText);
			break;
		case 2:
			display(title, message, buttonText, icon, imgSrc);
		}
		
	}
	
	/**
	 * Sets the location of components in AlertBox with mode 2.
	 * @param title			for the title of the pop up.
	 * @param message		the content of the pop up.
	 * @param buttonText	the name of the button.
	 * @param icon			the icon to add to title line.
	 * @param imgSrc		the image that appear inside the pop up.
	 */
	public static void display(String title, String message, String buttonText,  Image icon, String imgSrc) {
		Stage window = new Stage();																				//Creates a new stage called window
		
		window.initModality(Modality.APPLICATION_MODAL);														//Block everything until this window has been taken care of
		window.setTitle(title);																					//sets the title of the window
		window.setMinWidth(250);																				//sets the minimum width for the window
		window.getIcons().add(icon);																			//sets the icon		
		
		Region image = new Region();																			//Creating a new region for the image
		image.setMinSize(64, 64);																				//Setting the size of the image	
		image.setBackground(new Background(new BackgroundImage(new Image(AlertBox.class.getResourceAsStream(imgSrc), 64, 64, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))); //We'll place the image as the background of the region
		
		Label label = new Label();																				//Creates a new label
		label.setText(message);																					//sets the label's text
		label.setFont(new Font("Arial", 16));																	//sets the label's font
		
		HBox hBox = new HBox(5);																				//creates a new HBox layout
		hBox.getChildren().addAll(image,label);																	//add the image and the label to the hBox layout
		hBox.setAlignment(Pos.CENTER);																			//centers everything
		hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));	//Gives the layout a nice white color
		
		Button closeButton = new Button(buttonText);															//Creates a new button
		closeButton.setOnAction(e -> window.close());															//Event handler for when clicking on the button
		
		VBox vBox = new VBox(5);																				//Creating a new layout with a 5 spacing between Nods
		vBox.getChildren().addAll(hBox, closeButton);															//Adds all the Nods to the layout
		vBox.setAlignment(Pos.CENTER);																			//This will center everything
		vBox.setPadding(new Insets(10));																		//Gives the layout some padding
		vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));	//Set a nice white background
		
		Scene scene = new Scene(vBox);		
		
		window.setScene(scene);																					//sets the scene
		window.setResizable(false);																				//disables the ability to resize the window
		window.showAndWait();																					//shows the window and disables any other action until this window is taken care of
	}	
	
	/**
	 * Sets the location of components in AlertBox with mode 2.
	 * @param title			for the title of the pop up.
	 * @param message		the content of the pop up.
	 * @param buttonText	the name of the button.
	 */
	public static void display(String title, String message, String buttonText) {
		Stage window = new Stage();																				//Creates a new stage called window
		
		window.initModality(Modality.APPLICATION_MODAL);														//Block everything until this window has been taken care of
		window.setTitle(title);																					//sets the title of the window
		window.setMinWidth(250);																				//sets the minimum width for the window
		
		Label label = new Label();																				//Creates a new label
		label.setText(message);																					//sets the label's text
		label.setFont(new Font("Arial", 16));																	//sets the label's font
		
		HBox hBox = new HBox(5);																				//creates a new HBox layout
		hBox.getChildren().add(label);																			//add the image and the label to the hBox layout
		hBox.setAlignment(Pos.CENTER);																			//centers everything
		hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));	//Gives the layout a nice white color
		
		Button closeButton = new Button(buttonText);															//Creates a new button
		closeButton.setOnAction(e -> window.close());															//Event handler for when clicking on the button
		
		VBox vBox = new VBox(5);																				//Creating a new layout with a 5 spacing between Nods
		vBox.getChildren().addAll(hBox, closeButton);															//Adds all the Nods to the layout
		vBox.setAlignment(Pos.CENTER);																			//This will center everything
		vBox.setPadding(new Insets(10));																		//Gives the layout some padding
		vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));	//Set a nice white background
		
		Scene scene = new Scene(vBox);		
		
		window.setScene(scene);																					//sets the scene
		window.setResizable(false);																				//disables the ability to resize the window
		window.showAndWait();																					//shows the window and disables any other action until this window is taken care of
	}

	
	
}
