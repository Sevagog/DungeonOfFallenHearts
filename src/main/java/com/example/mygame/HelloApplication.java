package com.example.mygame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Math.abs;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /* Для работы с любым разрешением

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double width = resolution.getWidth();
        double height = resolution.getHeight();
        double w = width/1920;
        double h = height/1080;
        Scale scale = new Scale(w, h, 0, 0);

        root.getTransforms().add(scale);
        */

        // Все возможные меню
        AnchorPane mainMenu = new AnchorPane();
        Scene maunMenuScene = new Scene(mainMenu, 1920, 1080);
        AnchorPane playMenu = new AnchorPane();
        Scene playMenuScene = new Scene(playMenu, 1920, 1080);

        AnchorPane shopMenu = new AnchorPane();
        Scene shopMenuScene = new Scene(shopMenu, 1920, 1080);

        AnchorPane settingsMenu = new AnchorPane();
        Scene settingsMenuScene = new Scene(settingsMenu, 1920, 1080);

        AnchorPane bestiaryMenu = new AnchorPane();
        Scene bestiaryMenuScene = new Scene(bestiaryMenu, 1920, 1080);

        // Для главного меню
        ImageView menuBackgroundImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/menu_background.png"))); menuBackgroundImage.setLayoutX(-40);
        ImageView menuNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/menu_navigator.png")));
        HBox mainMenuHBox = new HBox(); AnchorPane.setLeftAnchor(mainMenuHBox, 0.0); AnchorPane.setRightAnchor(mainMenuHBox, 0.0); mainMenuHBox.setAlignment(Pos.BASELINE_CENTER); mainMenuHBox.getChildren().add(menuNavigatorImage); mainMenuHBox.setLayoutY(146);
        Label playButton = new Label("Играть"); playButton.setFont(Font.font("Franklin Gothic Medium", 55));
        AnchorPane.setLeftAnchor(playButton, 0.0); AnchorPane.setRightAnchor(playButton, 0.0); playButton.setAlignment(Pos.BASELINE_CENTER); AnchorPane.setTopAnchor(playButton, 189.0);
        Label shopButton = new Label("Магазин"); shopButton.setFont(Font.font("Franklin Gothic Medium", 55));
        AnchorPane.setLeftAnchor(shopButton, 0.0); AnchorPane.setRightAnchor(shopButton, 0.0); shopButton.setAlignment(Pos.BASELINE_CENTER); AnchorPane.setTopAnchor(shopButton, 363.0);
        Label bestiaryButton = new Label("Бестиарий"); bestiaryButton.setFont(Font.font("Franklin Gothic Medium", 55));
        AnchorPane.setLeftAnchor(bestiaryButton, 0.0); AnchorPane.setRightAnchor(bestiaryButton, 0.0); bestiaryButton.setAlignment(Pos.BASELINE_CENTER); AnchorPane.setTopAnchor(bestiaryButton, 545.0);
        Label settingsButton = new Label("Настройки"); settingsButton.setFont(Font.font("Franklin Gothic Medium", 55));
        AnchorPane.setLeftAnchor(settingsButton, 0.0); AnchorPane.setRightAnchor(settingsButton, 0.0); settingsButton.setAlignment(Pos.BASELINE_CENTER); AnchorPane.setTopAnchor(settingsButton, 725.0);
        Label exitButton = new Label("Выход"); exitButton.setFont(Font.font("Franklin Gothic Medium", 55));
        AnchorPane.setLeftAnchor(exitButton, 0.0); AnchorPane.setRightAnchor(exitButton, 0.0); exitButton.setAlignment(Pos.BASELINE_CENTER); AnchorPane.setTopAnchor(exitButton, 895.0);
        mainMenu.getChildren().addAll(menuBackgroundImage, mainMenuHBox, playButton, shopButton, bestiaryButton, settingsButton, exitButton);

        // Для меню выбора персонажа
        ImageView playMenuBackgroundImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_background.png"))); playMenuBackgroundImage.setLayoutX(-40);
        ImageView playMenuNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_navigator.png"))); playMenuNavigatorImage.setLayoutX(41); playMenuNavigatorImage.setLayoutY(78);
        ImageView playMenuSliderNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_slider_navigator.png"))); playMenuSliderNavigatorImage.setLayoutX(14); playMenuSliderNavigatorImage.setLayoutY(870); playMenuSliderNavigatorImage.setOpacity(0.0);
        ImageView playMenuSliderImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_slider.png"))); playMenuSliderImage.setLayoutX(65); playMenuSliderImage.setLayoutY(926);
        playMenu.getChildren().addAll(playMenuBackgroundImage, playMenuNavigatorImage, playMenuSliderNavigatorImage, playMenuSliderImage);

        stage.setTitle("Dungeon Of Fallen Hearts");
        stage.setScene(maunMenuScene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(true);
        stage.show();

        // Переменные для логики
        int[] menuNavigator = new int[2];
        double[] menuNavigatorImageLocation = {145, 321, 501, 679, 853};
        double[] playMenuNavigatorImageLocationY = {78, 229, 373, 510};
        double[] playMenuNavigatorImageLocationX = {41, 187, 335, 482, 623};
        double[] playMenuSliderImageLocation = {65, 192, 317, 443, 581};
        boolean[][] unlockedHeroes = new boolean[4][5]; unlockedHeroes[0][0] = true; unlockedHeroes[0][1] = true; unlockedHeroes[0][2] = true;
        boolean[] isCharacterSelected = {false};
        int[] selectedHero = {0};

        maunMenuScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (key.getCode()){
                case W:
                    menuNavigator[0] = (menuNavigator[0] - 1) % 5;
                    if(menuNavigator[0] < 0)
                        menuNavigator[0] = 4;
                    mainMenuHBox.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
                    break;
                case S:
                    menuNavigator[0] = (menuNavigator[0] + 1) % 5;
                    mainMenuHBox.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
                    break;
                case SPACE:
                    switch (menuNavigator[0]){
                        case 0:
                            stage.setScene(playMenuScene);
                            break;
                        case 1:
                            stage.setScene(shopMenuScene); // TODO Магазин
                            break;
                        case 2:
                            stage.setScene(bestiaryMenuScene); // TODO Бестиарий
                            break;
                        case 3:
                            stage.setScene(settingsMenuScene); // TODO Настройки
                            break;
                        default:
                            Platform.exit();
                            break;
                    }
                    menuNavigator[0] = 0;
                    break;
            }
        });
        playMenuScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
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
        });
    }

    public static void main(String[] args) {
        launch();
    }
}