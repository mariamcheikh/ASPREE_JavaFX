/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_dev_aspree;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author Jean
 */
public class MediaFXMain  extends Application{
  MediaPlayer mediaplayer;
  @Override
	public void start(Stage stage) {
		Button btn_play, btn_pause, btn_stop;

		btn_play = new Button("Start");
		btn_pause = new Button("Pause");
		btn_stop = new Button("Stop");

		btn_play.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mediaplayer.play();
			}
		});
		btn_pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mediaplayer.pause();
			}
		});
		btn_stop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mediaplayer.stop();
			}
		});

		
		javafx.scene.media.Media videoFile = new javafx.scene.media.Media(
				"file:///C:/Users/Jean/Desktop/videoplayback.mp4");

		mediaplayer = new MediaPlayer(videoFile);
		// mediaplayer.setAutoPlay(true);
		mediaplayer.setVolume(0.1);
		
		MediaView mediaView = new MediaView(mediaplayer);

		VBox root = new VBox();
		root.getChildren().addAll(btn_play,btn_pause,btn_stop,mediaView);

		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);

		stage.show();
	}
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
