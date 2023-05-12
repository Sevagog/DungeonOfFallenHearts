package com.example.mygame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class HelloApplication extends Application {
    Label playButton = new Label("Играть");
    Label shopButton = new Label("Магазин");
    Label bestiaryButton = new Label("Бестиарий");
    Label settingsButton = new Label("Настройки");
    Label exitButton = new Label("Выход");
    Label[] mainMenuLabels = {playButton, shopButton, bestiaryButton, settingsButton, exitButton};

    @Override
    public void start(Stage stage) throws IOException {

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double width = resolution.getWidth();
        double height = resolution.getHeight();
        double w = width/1920;
        double h = height/1080;
        Scale scale = new Scale(w, h, 0, 0);

        // Все возможные меню
        Pane mainMenu = new Pane(); mainMenu.getTransforms().add(scale);
        Pane playMenu = new Pane(); playMenu.getTransforms().add(scale);
        Pane shopMenu = new Pane(); shopMenu.getTransforms().add(scale);
        Pane settingsMenu = new Pane(); settingsMenu.getTransforms().add(scale);
        Pane bestiaryMenu = new Pane(); bestiaryMenu.getTransforms().add(scale);

        Scene maunScene = new Scene(mainMenu, 1920, 1080);

        // Для главного меню
        ImageView menuBackgroundImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/menu_background.png"))); menuBackgroundImage.setLayoutX(-40);
        ImageView menuNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/menu_navigator.png")));
        menuNavigatorImage.setLayoutX(628); menuNavigatorImage.setLayoutY(145);

        // Baoli SC
        // Impact
        // Charter
        playButton.setFont(Font.font("Hoefler Text", 55)); playButton.setTextFill(Color.rgb(98, 17,17));
        playButton.setLayoutX(874); playButton.setLayoutY(183);
        shopButton.setFont(Font.font("Hoefler Text", 55)); shopButton.setTextFill(Color.rgb(98, 17,17));
        shopButton.setLayoutX(858); shopButton.setLayoutY(359);
        bestiaryButton.setFont(Font.font("Hoefler Text", 55)); bestiaryButton.setTextFill(Color.rgb(98, 17,17));
        bestiaryButton.setLayoutX(828); bestiaryButton.setLayoutY(539);
        settingsButton.setFont(Font.font("Hoefler Text", 54)); settingsButton.setTextFill(Color.rgb(98, 17,17));
        settingsButton.setLayoutX(824); settingsButton.setLayoutY(717);
        exitButton.setFont(Font.font("Hoefler Text", 55)); exitButton.setTextFill(Color.rgb(98, 17,17));
        exitButton.setLayoutX(874); exitButton.setLayoutY(891);
        mainMenu.getChildren().addAll(menuBackgroundImage, menuNavigatorImage, playButton, shopButton, bestiaryButton, settingsButton, exitButton);

        // Для меню выбора персонажа
        ImageView playMenuBackgroundImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_background.png"))); playMenuBackgroundImage.setLayoutX(-40);
        ImageView playMenuNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_navigator.png"))); playMenuNavigatorImage.setLayoutX(41); playMenuNavigatorImage.setLayoutY(78);
        ImageView playMenuSliderNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_slider_navigator.png"))); playMenuSliderNavigatorImage.setLayoutX(14); playMenuSliderNavigatorImage.setLayoutY(870); playMenuSliderNavigatorImage.setOpacity(0.0);
        ImageView playMenuSliderImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_slider.png"))); playMenuSliderImage.setLayoutX(65); playMenuSliderImage.setLayoutY(926);
        playMenu.getChildren().addAll(playMenuBackgroundImage, playMenuNavigatorImage, playMenuSliderNavigatorImage, playMenuSliderImage);

        stage.setTitle("Dungeon Of Fallen Hearts");
        stage.setScene(maunScene);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);
        stage.show();

        // Переменные для логики
        String[] pane = {"mainMenu"};
        int[] menuNavigator = new int[2];
        double[] menuNavigatorImageLocation = {145, 321, 501, 679, 853};
        double[] playMenuNavigatorImageLocationY = {78, 229, 373, 510};
        double[] playMenuNavigatorImageLocationX = {41, 187, 335, 482, 623};
        double[] playMenuSliderImageLocation = {65, 192, 317, 443, 581};
        boolean[][] unlockedHeroes = new boolean[4][5]; unlockedHeroes[0][0] = true; unlockedHeroes[0][1] = true; unlockedHeroes[0][2] = true;
        boolean[] isCharacterSelected = {false};
        int[] selectedHero = {0};

        maunScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (pane[0]){
                case "mainMenu":
                    switch (key.getCode()){
                        case W:
                            menuNavigator[0] = (menuNavigator[0] - 1) % 5;
                            if(menuNavigator[0] < 0)
                                menuNavigator[0] = 4;
                            menuNavigatorImage.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
                            mainMenuNavigatorIdicator(menuNavigator[0]);
                            break;
                        case S:
                            menuNavigator[0] = (menuNavigator[0] + 1) % 5;
                            menuNavigatorImage.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
                            mainMenuNavigatorIdicator(menuNavigator[0]);
                            break;
                        case SPACE:
                            switch (menuNavigator[0]){
                                case 0:
                                    maunScene.setRoot(playMenu);
                                    pane[0] = "playMenu";
                                    break;
                                case 1:
                                    maunScene.setRoot(shopMenu); // TODO Магазин
                                    pane[0] = "shopMenu";
                                    break;
                                case 2:
                                    maunScene.setRoot(bestiaryMenu); // TODO Бестиарий
                                    pane[0] = "bestiaryMenu";
                                    break;
                                case 3:
                                    maunScene.setRoot(settingsMenu); // TODO Настройки
                                    pane[0] = "settingsMenu";
                                    break;
                                default:
                                    Platform.exit();
                                    break;
                            }
                            menuNavigator[0] = 0;
                            break;
                    }
                    break;
                case "playMenu":
                    if(isCharacterSelected[0]) {
                        switch (key.getCode()){
                            case A:
                                menuNavigator[0] = (menuNavigator[0] - 1) % 5;
                                if (menuNavigator[0] < 0)
                                    menuNavigator[0] = 4;
                                playMenuSliderImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]]);
                                playMenuSliderNavigatorImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]]-51);
                                break;
                            case D:
                                menuNavigator[0] = (menuNavigator[0] + 1) % 5;
                                playMenuSliderImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]]);
                                playMenuSliderNavigatorImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]]-51);
                                break;
                            case SPACE:

                                break;
                        }
                    } else {
                        switch (key.getCode()) {
                            case W:
                                menuNavigator[0] = (menuNavigator[0] - 1) % 4;
                                if (menuNavigator[0] < 0)
                                    menuNavigator[0] = 3;
                                playMenuNavigatorImage.setLayoutY(playMenuNavigatorImageLocationY[menuNavigator[0]]);
                                break;
                            case S:
                                menuNavigator[0] = (menuNavigator[0] + 1) % 4;
                                playMenuNavigatorImage.setLayoutY(playMenuNavigatorImageLocationY[menuNavigator[0]]);
                                break;
                            case A:
                                menuNavigator[1] = (menuNavigator[1] - 1) % 5;
                                if (menuNavigator[1] < 0)
                                    menuNavigator[1] = 4;
                                playMenuNavigatorImage.setLayoutX(playMenuNavigatorImageLocationX[menuNavigator[1]]);
                                break;
                            case D:
                                menuNavigator[1] = (menuNavigator[1] + 1) % 5;
                                playMenuNavigatorImage.setLayoutX(playMenuNavigatorImageLocationX[menuNavigator[1]]);
                                break;
                            case SPACE:
                                if (unlockedHeroes[menuNavigator[0]][menuNavigator[1]]) {
                                    isCharacterSelected[0] = true;
                                    playMenuNavigatorImage.setOpacity(0.0);
                                    playMenuSliderNavigatorImage.setOpacity(1.0);
                                    selectedHero[0] = menuNavigator[0] * 5 + menuNavigator[1];
                                    menuNavigator[0] = 0; menuNavigator[1] = 0;
                                    // Todo Убрать обводку персонажа, добавить обводку слайдера
                                }
                                break;
                        }
                    }
                    break;
                case "shopMenu":

                    break;
                case "bestiaryMenu":

                    break;
                case "settingsMenu":

                    break;
            }
        });


    }

    private void mainMenuNavigatorIdicator(int i) {
        for (int j = 0; j < 5; j++) {
            mainMenuLabels[j].setTextFill(Color.rgb(98, 17,17));
        }
        switch (i){
            case 0:
                playButton.setTextFill(Color.rgb(255, 255,255));
                break;
            case 1:
                shopButton.setTextFill(Color.rgb(255, 255,255));
                break;
            case 2:
                bestiaryButton.setTextFill(Color.rgb(255, 255,255));
                break;
            case 3:
                settingsButton.setTextFill(Color.rgb(255, 255,255));
                break;
            case 4:
                exitButton.setTextFill(Color.rgb(255, 255,255));
        }
    }


    public static void main(String[] args) {
        launch();
    }
}