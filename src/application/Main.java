package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	List<Card> player = new ArrayList<>();
	List<Card> dealer = new ArrayList<>();
	Deck deck = new Deck();
	int dealerSum = 0;
	int playerSum = 0;
	int playerDealtCard = 0;
	int dealerDealtCard = 0;
	boolean dealersTurn;
	boolean dealerBust = false;

	@Override
	public void start(Stage primaryStage) {

		try {
			primaryStage.setTitle("BlackJack");

			// GridPane is the main layout that is centered with the primary
			// stage
			GridPane mainGrid = new GridPane();
			mainGrid.setAlignment(Pos.CENTER);

			// Scene is set within the mainGrid at 700, 450
			Scene scene = new Scene(mainGrid, 700, 450);
			primaryStage.setScene(scene);

			primaryStage.show();

			// JavaFx objects initialized
			Text scenetitle = new Text("BlackJack");
			Text dealerTitle = new Text("Dealer");
			Text playerTitle = new Text("Player");
			Label dealerTotalLabel = new Label();
			Label playerTotalLabel = new Label();
			ListView<Card> playerLV = new ListView<>();
			ListView<Card> dealerLV = new ListView<>();
			Button shuffleBtn = new Button("Shuffle");
			Button dealBtn = new Button("Deal");
			Button hitBtn = new Button("Hit");
			Button stayBtn = new Button("Stay");
			Alert alert = new Alert(AlertType.INFORMATION);

			// Scene Title ("BlackJack") Font Style
			scenetitle.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 20));

			// Dealer and Player BlackJack hand summed and printed
			dealerTotalLabel.setText("" + dealerSum);
			playerTotalLabel.setText("" + playerSum);

			// HBox and VBox layouts initialized
			HBox dealerTitleHBox = new HBox();
			HBox playerTitleHBox = new HBox();
			HBox dealerTotalLabelHBox = new HBox();
			HBox playerTotalLabelHBox = new HBox();
			VBox buttonsVBox = new VBox();

			// Alignment centered
			dealerTitleHBox.setAlignment(Pos.CENTER);
			playerTitleHBox.setAlignment(Pos.CENTER);
			dealerTotalLabelHBox.setAlignment(Pos.CENTER);
			playerTotalLabelHBox.setAlignment(Pos.CENTER);
			buttonsVBox.setAlignment(Pos.CENTER);

			// VBox layout spacing. Contains shuffle, deal, hit, and stay
			// buttons
			buttonsVBox.setSpacing(10);

			// Title set
			alert.setTitle("BlackJack Results");

			// HBox and VBox layouts grabbing JavaFx objects created
			dealerTitleHBox.getChildren().add(dealerTitle);
			playerTitleHBox.getChildren().add(playerTitle);
			dealerTotalLabelHBox.getChildren().add(dealerTotalLabel);
			playerTotalLabelHBox.getChildren().add(playerTotalLabel);
			buttonsVBox.getChildren().addAll(shuffleBtn, dealBtn, hitBtn,
					stayBtn);

			// All JavaFx created objects within the mainGrid being placed
			mainGrid.add(scenetitle, 3, 0);
			mainGrid.add(dealerTitleHBox, 1, 1);
			mainGrid.add(playerTitleHBox, 4, 1);
			mainGrid.add(dealerTotalLabelHBox, 1, 3);
			mainGrid.add(playerTotalLabelHBox, 4, 3);
			mainGrid.add(dealerLV, 1, 2);
			mainGrid.add(playerLV, 4, 2);
			mainGrid.add(buttonsVBox, 3, 2);

			// Buttons disabled to prevent hitting buttons when the game hasn't
			// begun
			dealBtn.setDisable(true);
			hitBtn.setDisable(true);
			stayBtn.setDisable(true);

			// Shuffles the Deck object and resets the entire game
			shuffleBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					deck.shuffle();

					dealerLV.getItems().clear();
					playerLV.getItems().clear();

					dealerSum = 0;
					playerSum = 0;

					dealerTotalLabel.setText("" + dealerSum);
					playerTotalLabel.setText("" + playerSum);

					playerDealtCard = 0;
					dealerDealtCard = 0;

					dealerBust = false;
					dealersTurn = true;

					dealBtn.setDisable(false);
					hitBtn.setDisable(true);
					stayBtn.setDisable(true);

				}
			});

			// Deals two cards to the dealer and player then sums their hands as
			// the cards get dealt
			dealBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {

					ObservableList<Card> dealerOL = FXCollections
							.observableList(dealer);
					ObservableList<Card> playerOL = FXCollections
							.observableList(player);

					dealBtn.setDisable(true);
					hitBtn.setDisable(false);
					stayBtn.setDisable(false);

					for (int i = 0; i < 2; i++) {
						try {
							player.add(deck.deal());
							dealer.add(deck.deal());
						} catch (OutOfCardsException e) {
							System.out.println(e.getMessage());
						}

						dealerLV.setItems(dealerOL);
						playerLV.setItems(playerOL);

						playerSum += player.get(playerDealtCard).getRank()
								.getValue();
						dealerSum += dealer.get(dealerDealtCard).getRank()
								.getValue();

						playerTotalLabel.setText("" + playerSum);
						dealerTotalLabel.setText("" + dealerSum);

						playerDealtCard++;
						dealerDealtCard++;

					}

				}

			});

			// Deals a card to the player every time the button is pushed and
			// that card is added onto the player's sum
			hitBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {

					ObservableList<Card> playerOL = FXCollections
							.observableList(player);

					try {
						player.add(deck.deal());
					} catch (OutOfCardsException e) {
						System.out.println(e.getMessage());
					}

					playerLV.setItems(playerOL);

					playerSum += player.get(playerDealtCard).getRank()
							.getValue();

					playerTotalLabel.setText("" + playerSum);

					playerDealtCard++;

					if (playerSum > 21) {

						hitBtn.setDisable(true);
						stayBtn.setDisable(true);
						dealerLV.requestFocus();

						alert.setHeaderText("Dealer Total: " + dealerSum
								+ "\nPlayer Total: " + playerSum);
						alert.setContentText("Sorry, you busted with "
								+ playerSum + "\nYou Lose!"
								+ "\nShuffle to play again");
						alert.showAndWait();

					}

				}
			});

			// Ends player turn and begins dealer's turn as well as ends the
			// game with a winner and a loser
			stayBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {

					ObservableList<Card> dealerOL = FXCollections
							.observableList(dealer);

					hitBtn.setDisable(true);
					stayBtn.setDisable(true);

					while (dealersTurn) {

						if (dealerSum <= 17) {
							try {
								dealer.add(deck.deal());
							} catch (OutOfCardsException e) {
								System.out.println(e.getMessage());
							}

							dealerLV.setItems(dealerOL);

							dealerSum += dealer.get(dealerDealtCard).getRank()
									.getValue();

							dealerTotalLabel.setText("" + dealerSum);

							dealerDealtCard++;

							if (dealerSum > 21) {
								playerLV.requestFocus();
								dealerBust = true;
								alert.setHeaderText("Dealer Total: "
										+ dealerSum + "\nPlayer Total: "
										+ playerSum);
								alert.setContentText("Dealer busted with "
										+ dealerSum + "\nYou Win!"
										+ "\nShuffle to play again");
								alert.showAndWait();

							}
						} else {
							dealersTurn = false;
						}

					}

					// If the dealer has a better hand and hasn't busted then
					// the player loses
					// Else if the player has a better hand and hasn't busted
					// then the player wins
					if (dealerSum >= playerSum && !dealerBust) {
						dealerLV.requestFocus();

						alert.setHeaderText("Dealer Total: " + dealerSum
								+ "\nPlayer Total: " + playerSum);
						alert.setContentText("You Lose!"
								+ "\nShuffle to play again");
						alert.showAndWait();

					} else if (playerSum > dealerSum && !dealerBust) {
						playerLV.requestFocus();

						alert.setHeaderText("Dealer Total: " + dealerSum
								+ "\nPlayer Total: " + playerSum);
						alert.setContentText("You Win!"
								+ "\nShuffle to play again");
						alert.showAndWait();
					}

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
