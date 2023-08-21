package com.example.movidle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller  implements Initializable {
    @FXML
    public VBox filmListVBox;
    @FXML
    public Label filmYear;
    @FXML
    public Label filmGenre;
    @FXML
    public Label filmOrigin;
    @FXML
    public Label filmDirector;
    @FXML
    public Label filmStar;
    @FXML
    public TextField userGuess;

    public FilmList filmListesi;
    public Film currentFilm;

    public Controller()  {
        filmListesi = new FilmList();
        currentFilm = filmListesi.randomFilm();

        //if (currentFilm != null) {
        //  updateFilmDetails();
        //}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        if (currentFilm != null) {
            updateFilmDetails();
        }
    }

    private void updateFilmDetails() {
        filmYear.setText(String.valueOf(currentFilm.getYear()));
        filmGenre.setText(currentFilm.getGenre());
        filmOrigin.setText(currentFilm.getOrigin());
        filmDirector.setText(currentFilm.getDirector());
        filmStar.setText(currentFilm.getStar());
    }

    @FXML
    protected void onGuessButtonClick() {
        // Check if currentFilm is null
        if (currentFilm == null) {
            // Handle the case where currentFilm is null,
            // for example show an error message, return from the function, etc.
            System.out.println("currentFilm is null!");
            return;
        }
        String guess = userGuess.getText().trim();

        checkAndSet(guess, currentFilm.getTitle().trim());
    }
    Image image = new Image("file:res/imdb250image.jpg");
    ImageView imageView = new ImageView(image);

    private void checkAndSet(String guess, String actual) {
        List<Film> allFilm = filmListesi.getAllFilm();
        Film guessFilm = allFilm.stream().filter(x -> x.getTitle().equalsIgnoreCase(guess)).findFirst().orElse(null);

        if (guessFilm != null) {
            if (guessFilm.getYear() == currentFilm.getYear())
                filmYear.setStyle("-fx-text-fill: green;");
            else
                filmYear.setStyle("-fx-text-fill: red;");

            if (guessFilm.getGenre().trim().equals(currentFilm.getGenre().trim()))
                filmGenre.setStyle("-fx-text-fill: green;");
            else
                filmGenre.setStyle("-fx-text-fill: red;");

            if (guessFilm.getOrigin().trim().equals(currentFilm.getOrigin().trim()))
                filmOrigin.setStyle("-fx-text-fill: green;");
            else
                filmOrigin.setStyle("-fx-text-fill: red;");

            if (guessFilm.getDirector().trim().equals(currentFilm.getDirector().trim()))
                filmDirector.setStyle("-fx-text-fill: green;");
            else
                filmDirector.setStyle("-fx-text-fill: red;");

            if (guessFilm.getStar().trim().equals(currentFilm.getStar().trim()))
                filmStar.setStyle("-fx-text-fill: green;");
            else
                filmStar.setStyle("-fx-text-fill: red;");
        } else {
            filmYear.setStyle("-fx-text-fill: red;");
            filmGenre.setStyle("-fx-text-fill: red;");
            filmOrigin.setStyle("-fx-text-fill: red;");
            filmDirector.setStyle("-fx-text-fill: red;");
            filmStar.setStyle("-fx-text-fill: red;");
        }


        if (guessFilm != null && guessFilm.equals(currentFilm)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tebrikler!");
            alert.setHeaderText(null);
            alert.setContentText("Film Tahmininiz Doğru");

            alert.showAndWait();
        }
    }

    @FXML
    protected void onNewFilmButtonClick() {
        currentFilm = filmListesi.randomFilm();
        updateFilmDetails();
        filmYear.setStyle("-fx-text-fill: black;");
        filmGenre.setStyle("-fx-text-fill: black;");
        filmOrigin.setStyle("-fx-text-fill: black;");
        filmDirector.setStyle("-fx-text-fill: black;");
        filmStar.setStyle("-fx-text-fill: black;");
    }
}
