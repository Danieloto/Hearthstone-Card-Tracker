package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings("restriction")
public class LargePic extends Application {
	///////////////////// profile////////////////////////////////
	BackgroundImage BackImage= new BackgroundImage(new Image("Image/123.png",290,45,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
	  ImageView ima=new ImageView();
	  int num;
	  double x,y;
	  Image temp;
	///////////////////// total///////////////////////////////

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane buttom = new AnchorPane();
			buttom.setPrefSize(280, 400);
			buttom.setBackground(null);
			
			
			buttom.getChildren().add(ima);
			ima.setFitHeight(400);
			ima.setFitWidth(280);
			ima.setImage(temp);
			
			Scene scene = new Scene(buttom,280,400);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.setX(x+425);
			primaryStage.setY(y+150);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	   public void setImage(Image i) {
		   temp=i;
	   }
	   public void Close() {
		   ima.getScene().getWindow().hide();
	   }
	   public void getLoca(double x,double y) {
		   this.x=x;
		   this.y=y;
	   }
	
	
}

