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

public class Tracker_GUI extends Application {
	///////////////////// profile////////////////////////////////
	Background black = new Background(new BackgroundFill(Paint.valueOf("#000000"), CornerRadii.EMPTY, Insets.EMPTY));// red
	Background white = new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY));// ye
	LargePic lp = new LargePic();
	private int n;
	Image[] Larp = new Image[15];
	Image[] smlp = new Image[15];
	///////////////////// total///////////////////////////////

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane buttom = new AnchorPane();
			buttom.setPrefSize(350, 700);
			buttom.setBackground(null);

			Pane p2=new Pane();
			p2.setPrefSize(280, 100);
			p2.setLayoutX(30);
			p2.setLayoutY(20);
			p2.setBackground(null);
			BackgroundImage BackImage2= new BackgroundImage(new Image("Image/hearthstone_logo.png",280,100,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
			p2.setBackground(new Background(BackImage2));  
			buttom.getChildren().add(p2);
			
			ScrollPane list = new ScrollPane();
			list.setPrefHeight(600);
			list.setPrefWidth(350);
			list.setLayoutX(0);
			list.setLayoutY(100);
			buttom.getChildren().add(list);

			VBox vb = new VBox();
			list.setContent(vb);
			Label[] cards = new Label[15];
			putImage();
			putsImage();
			for (int i = 0; i < 15; i++) {
				cards[i] = new Label();
				cards[i].setPrefSize(340, 50);
				BackgroundImage BackImage = new BackgroundImage((smlp[i]),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				cards[i].setBackground(new Background(BackImage));
				vb.getChildren().add(cards[i]);

			}
			for (int i = 0; i < 15; i++) {
				cards[i].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						// lp.setImage(i);
						Stage sta = new Stage();
						try {
							lp.getLoca(primaryStage.getX(), primaryStage.getY());
							if (e.getSource() == cards[0]) {
								lp.setImage(Larp[0]);
							} else if (e.getSource() == cards[1]) {
								lp.setImage(Larp[1]);
							} else if (e.getSource() == cards[2]) {
								lp.setImage(Larp[2]);
							} else if (e.getSource() == cards[3]) {
								lp.setImage(Larp[3]);
							} else if (e.getSource() == cards[4]) {
								lp.setImage(Larp[4]);
							} else if (e.getSource() == cards[5]) {
								lp.setImage(Larp[5]);
							} else if (e.getSource() == cards[6]) {
								lp.setImage(Larp[6]);
							} else if (e.getSource() == cards[7]) {
								lp.setImage(Larp[7]);
							} else if (e.getSource() == cards[8]) {
								lp.setImage(Larp[8]);
							} else if (e.getSource() == cards[9]) {
								lp.setImage(Larp[9]);
							} else if (e.getSource() == cards[10]) {
								lp.setImage(Larp[10]);
							} else if (e.getSource() == cards[11]) {
								lp.setImage(Larp[11]);
							} else if (e.getSource() == cards[12]) {
								lp.setImage(Larp[12]);
							} else if (e.getSource() == cards[13]) {
								lp.setImage(Larp[13]);
							} else if (e.getSource() == cards[14]) {
								lp.setImage(Larp[14]);
							}
							lp.start(sta);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				cards[i].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						lp.Close();

					}
				});

			}
			Scene scene = new Scene(buttom, 350, 700);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void putImage() {
		Larp[0] = new Image("Image/BoulderfistOgre.png");
		Larp[1] = new Image("Image/ArcaneExplosion.png");
		Larp[2] = new Image("Image/ArcaneIntellect.png");
		Larp[3] = new Image("Image/ArcaneMissiles.png");
		Larp[4] = new Image("Image/BloodfenRaptor.png");
		Larp[5] = new Image("Image/Fireball.png");
		Larp[6] = new Image("Image/MurlocRaider.png");
		Larp[7] = new Image("Image/Nightblade.png");
		Larp[8] = new Image("Image/NoviceEngineer.png");
		Larp[9] = new Image("Image/OasisSnapjaw.png");
		Larp[10] = new Image("Image/Polymorph.png");
		Larp[11] = new Image("Image/RaidLeader.png");
		Larp[12] = new Image("Image/RiverCrocolisk.png");
		Larp[13] = new Image("Image/Sen'jinShieldmasta.png");
		Larp[14] = new Image("Image/Wolfrider.png");
	
	}
	
	public void putsImage() {
		smlp[0] = new Image("Image/BoulderfistOgre(s).png");
		smlp[1] = new Image("Image/ArcaneExplosion(s).png");
		smlp[2] = new Image("Image/ArcaneIntellect(s).png");
		smlp[3] = new Image("Image/ArcaneMissiles(s).png");
		smlp[4] = new Image("Image/BloodfenRaptor(s).png");
		smlp[5] = new Image("Image/Fireball(s).png");
		smlp[6] = new Image("Image/MurlocRaider(s).png");
		smlp[7] = new Image("Image/Nightblade(s).png");
		smlp[8] = new Image("Image/NoviceEngineer(s).png");
		smlp[9] = new Image("Image/OasisSnapjaw(s).png");
		smlp[10] = new Image("Image/Polymorph(s).png");
		smlp[11] = new Image("Image/RaidLeader(s).png");
		smlp[12] = new Image("Image/RiverCrocolisk(s).png");
		smlp[13] = new Image("Image/Sen'jinShieldmasta(s).png");
		smlp[14] = new Image("Image/Wolfrider(s).png");
		
	}

}
