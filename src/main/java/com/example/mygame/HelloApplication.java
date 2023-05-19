package com.example.mygame;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;


public class HelloApplication extends Application {
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
        Pane gamePlay = new Pane(); gamePlay.getTransforms().add(scale);
        Pane shopMenu = new Pane(); shopMenu.getTransforms().add(scale);
        Pane settingsMenu = new Pane(); settingsMenu.getTransforms().add(scale);
        Pane bestiaryMenu = new Pane(); bestiaryMenu.getTransforms().add(scale);
        Scene maunScene = new Scene(mainMenu, 1920, 1080);
        Font mainFont = Font.loadFont("file:src/main/java/com/example/mygame/main_font.ttf", 75);

        // Для главного меню
        ImageView menuBackgroundImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/menu_background.png"))); menuBackgroundImage.setLayoutX(-40);
        ImageView menuNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/menu_navigator.png")));
        menuNavigatorImage.setLayoutX(628); menuNavigatorImage.setLayoutY(145);

        Label playButton = new Label("Играть"); playButton.setFont(mainFont);playButton.setLayoutX(865); playButton.setLayoutY(170); playButton.setTextFill(Color.rgb(255, 255, 255));
        Label shopButton = new Label("Магазин"); shopButton.setFont(mainFont); shopButton.setLayoutX(851); shopButton.setLayoutY(345);
        Label bestiaryButton = new Label("Бестиарий"); bestiaryButton.setFont(mainFont); bestiaryButton.setLayoutX(830); bestiaryButton.setLayoutY(527);
        Label settingsButton = new Label("Настройки"); settingsButton.setFont(mainFont); settingsButton.setLayoutX(826); settingsButton.setLayoutY(704);
        Label exitButton = new Label("Выход"); exitButton.setFont(mainFont); exitButton.setLayoutX(876); exitButton.setLayoutY(879);
        Label[] mainMenuLabels = {playButton, shopButton, bestiaryButton, settingsButton, exitButton};
        for (int i = 1; i < 5; i++) mainMenuLabels[i].setTextFill(Color.rgb(98, 17, 17));
        mainMenu.getChildren().addAll(menuBackgroundImage, menuNavigatorImage, playButton, shopButton, bestiaryButton, settingsButton, exitButton);

        // Для меню выбора персонажа
        ImageView playMenuBackgroundImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_background.png"))); playMenuBackgroundImage.setLayoutX(-40);
        ImageView playMenuNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_navigator.png"))); playMenuNavigatorImage.setLayoutX(41); playMenuNavigatorImage.setLayoutY(78);
        ImageView playMenuSliderNavigatorImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_slider_navigator.png"))); playMenuSliderNavigatorImage.setLayoutX(14); playMenuSliderNavigatorImage.setLayoutY(868); playMenuSliderNavigatorImage.setOpacity(0.0);
        ImageView playMenuSliderImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/play_menu_slider.png"))); playMenuSliderImage.setLayoutX(65); playMenuSliderImage.setLayoutY(924);
        // Персонаж
        ImageView playerBody = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/body.png")));
        playerBody.setScaleX(0.65); playerBody.setScaleY(0.65); playerBody.setLayoutX(880); playerBody.setLayoutY(400);
        ImageView playerFoot = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/foot.png")));
        playerFoot.setScaleX(0.65); playerFoot.setScaleY(0.65); playerFoot.setLayoutX(1020); playerFoot.setLayoutY(530);
        ImageView playerHead = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/head_0.png")));
        playerHead.setScaleX(0.65); playerHead.setScaleY(0.65); playerHead.setLayoutX(895); playerHead.setLayoutY(282);
        ImageView playerWeapon = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/weapon_0.png")));
        playerWeapon.setScaleX(0.65); playerWeapon.setScaleY(0.65); playerWeapon.setLayoutX(905); playerWeapon.setLayoutY(350);
        playMenu.getChildren().addAll(playMenuBackgroundImage, playMenuNavigatorImage, playMenuSliderNavigatorImage, playMenuSliderImage, playerWeapon, playerFoot, playerBody, playerHead);

        // Для самой игры
        ImageView playerBodyInGame = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/body.png")));
        playerBodyInGame.setScaleX(0.3); playerBodyInGame.setScaleY(0.3); playerBodyInGame.setLayoutX(708); playerBodyInGame.setLayoutY(440);
        ImageView playerFootInGame = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/foot.png")));
        playerFootInGame.setScaleX(0.3); playerFootInGame.setScaleY(0.3); playerFootInGame.setLayoutX(848); playerFootInGame.setLayoutY(537);
        ImageView playerHeadInGame = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/head_0.png")));
        playerHeadInGame.setScaleX(0.3); playerHeadInGame.setScaleY(0.3); playerHeadInGame.setLayoutX(740); playerHeadInGame.setLayoutY(412);
        ImageView playerWeaponInGame = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/weapon_0.png")));
        playerWeaponInGame.setScaleX(0.3); playerWeaponInGame.setScaleY(0.3); playerWeaponInGame.setLayoutX(740); playerWeaponInGame.setLayoutY(410);
        ImageView[] floorImage = new ImageView[4];
        for (int i = 0; i < 4; i++){
            floorImage[i] = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/floor.png")));
            gamePlay.getChildren().add(floorImage[i]);
        }
        floorImage[0].setLayoutX(-1500); floorImage[0].setLayoutY(-1500);
        floorImage[1].setLayoutX(2764); floorImage[1].setLayoutY(-1500);
        floorImage[2].setLayoutX(-1500); floorImage[2].setLayoutY(1500);
        floorImage[3].setLayoutX(2764); floorImage[3].setLayoutY(1500);
        gamePlay.getChildren().addAll(playerWeaponInGame, playerFootInGame, playerBodyInGame, playerHeadInGame);

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
        double[] playMenuSliderImageLocation = {67, 194, 318, 443, 582};
        double[][] playMenuPlayerHead = {{895, 955, 955}, {282, 268, 268}}; // Первый столбик - Х, второй - Y.
        double[][] playMenuPlayerWeapon = {{905, 905, 905}, {350, 350, 350}}; // Первый столбик - Х, второй - Y.
        double[][] gamePlayPlayerHead = {{740, 790, 955}, {412, 402, 268}}; // Первый столбик - Х, второй - Y.
        double[][] gamePlayPlayerWeapon = {{740, 740, 740}, {410, 410, 410}}; // Первый столбик - Х, второй - Y.
        double[] gamePlayPlayerHeadReverted = {870, 880, 880};
        double[] gamePlayPlayerWeaponReverted = {860, 860, 860};
        boolean[][] unlockedHeroes = new boolean[4][5]; unlockedHeroes[0][0] = true; unlockedHeroes[0][1] = true; unlockedHeroes[0][2] = true;
        boolean[] isCharacterSelected = {false};
        int[] selectedHero = {0};
        boolean[] pressed = {false, false, false, false, false}; // W A S D Space
        boolean[] isReverted = {false}; boolean[] isRotated = {false};
        int[] footPos = {1}; // 0 - право; 1 - начальная; 2 - лево
        short[] timer = {0};
        Rotate imageFlip = new Rotate(180, Rotate.Y_AXIS);
        Rotate playerRotPlus = new Rotate(15, Rotate.Z_AXIS);
        Rotate playerRotMinus = new Rotate(-15, Rotate.Z_AXIS);

        final Animation gameAnimation = new Transition() {
            {
                setCycleDuration(Duration.seconds(Integer.MAX_VALUE));
            }
            protected void interpolate(double frac) {
                // ЛОГИКА ИГРЫ ТУТ
                timer[0] += 1;
                if(timer[0] == Short.MAX_VALUE) timer[0] = 0;
                for (int i = 0; i < 4; i++){
                    if(pressed[0]){
                        floorImage[i].setLayoutY(floorImage[i].getLayoutY() + 10);
                    }
                    if(pressed[1]){
                        floorImage[i].setLayoutX(floorImage[i].getLayoutX() + 10);
                        if (isReverted[0] && !pressed[3]) {
                            isReverted[0] = false;
                            playerBodyInGame.getTransforms().add(imageFlip);
                            playerFootInGame.getTransforms().add(imageFlip);
                            playerHeadInGame.getTransforms().add(imageFlip);
                            playerWeaponInGame.getTransforms().add(imageFlip);
                            playerHeadInGame.setLayoutX(gamePlayPlayerHead[0][selectedHero[0]]);
                            playerWeaponInGame.setLayoutX(gamePlayPlayerWeapon[0][selectedHero[0]]);
                            //playerFootInGame.setLayoutX(848);
                            playerBodyInGame.setLayoutX(708);
                        }
                        if(!isRotated[0] && !pressed[3]){
                            playerBodyInGame.setLayoutY(playerBodyInGame.getLayoutY()+17);
                            playerFootInGame.setLayoutY(playerFootInGame.getLayoutY()+8);
                            playerBodyInGame.getTransforms().add(playerRotMinus);
                            playerFootInGame.getTransforms().add(playerRotMinus);
                            isRotated[0] = true;
                        }
                    }
                    if(pressed[2]){
                        floorImage[i].setLayoutY(floorImage[i].getLayoutY() - 10);
                    }
                    if(pressed[3]){
                        floorImage[i].setLayoutX(floorImage[i].getLayoutX() - 10);
                        if (!isReverted[0] && !pressed[1]) {
                            isReverted[0] = true;
                            playerBodyInGame.getTransforms().add(imageFlip);
                            playerFootInGame.getTransforms().add(imageFlip);
                            playerHeadInGame.getTransforms().add(imageFlip);
                            playerWeaponInGame.getTransforms().add(imageFlip);
                            playerHeadInGame.setLayoutX(gamePlayPlayerHeadReverted[selectedHero[0]]);
                            playerWeaponInGame.setLayoutX(gamePlayPlayerWeaponReverted[selectedHero[0]]);
                            //playerFootInGame.setLayoutX(898);
                            playerBodyInGame.setLayoutX(841);
                        }
                        if(!isRotated[0] && !pressed[1]){
                            playerBodyInGame.setLayoutY(playerBodyInGame.getLayoutY()+17);
                            playerFootInGame.setLayoutY(playerFootInGame.getLayoutY()+8);
                            playerBodyInGame.getTransforms().add(playerRotMinus);
                            playerFootInGame.getTransforms().add(playerRotMinus);
                            isRotated[0] = true;
                        }
                    }
                }
                // Контроль ноги
                if(timer[0] % 4 == 0 && (pressed[0] || pressed[1] || pressed[2] || pressed[3])){
                    footPos[0] = (footPos[0] + 1) % 3;
                    if (isReverted[0]){
                        if(isRotated[0]){
                            playerFootInGame.setLayoutX(889);
                        } else {
                            playerFootInGame.setLayoutX(898);
                        }
                    } else {
                        if(isRotated[0]){
                            playerFootInGame.setLayoutX(857);
                        } else {
                            playerFootInGame.setLayoutX(848);
                        }
                    }
                    switch (footPos[0]){
                        case 0:
                            playerFootInGame.setLayoutX(playerFootInGame.getLayoutX()-5);
                            break;
                        case 2:
                            playerFootInGame.setLayoutX(playerFootInGame.getLayoutX()+5);
                            break;
                    }
                }
                // Создает бесконечность поля
                if(floorImage[0].getLayoutY() <= -4870){
                    for (int i = 0; i < 4; i++) {
                        floorImage[i].setLayoutY(floorImage[i].getLayoutY() + 3000);
                    }
                }
                if(floorImage[0].getLayoutX() <= -6430){
                    for (int i = 0; i < 4; i++) {
                        floorImage[i].setLayoutX(floorImage[i].getLayoutX() + 4264);
                    }
                }
                if(floorImage[0].getLayoutY() >= -10){
                    for (int i = 0; i < 4; i++) {
                        floorImage[i].setLayoutY(floorImage[i].getLayoutY() - 3000);
                    }
                }
                if(floorImage[0].getLayoutX() >= -1775){
                    for (int i = 0; i < 4; i++) {
                        floorImage[i].setLayoutX(floorImage[i].getLayoutX() - 4264);
                    }
                }


            }
        };

        maunScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (pane[0]){
                case "mainMenu":
                    for (int i = 0; i < 5; i++) mainMenuLabels[i].setTextFill(Color.rgb(98, 17, 17));
                    switch (key.getCode()){
                        case W:
                            menuNavigator[0] = (menuNavigator[0] - 1) % 5;
                            if(menuNavigator[0] < 0)
                                menuNavigator[0] = 4;
                            menuNavigatorImage.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
                            break;
                        case S:
                            menuNavigator[0] = (menuNavigator[0] + 1) % 5;
                            menuNavigatorImage.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
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
                    mainMenuLabels[menuNavigator[0]].setTextFill(Color.rgb(255, 255, 255));
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
                                playerHeadInGame.setLayoutX(gamePlayPlayerHead[0][selectedHero[0]]); playerHeadInGame.setLayoutY(gamePlayPlayerHead[1][selectedHero[0]]);
                                playerWeaponInGame.setLayoutX(gamePlayPlayerWeapon[0][selectedHero[0]]); playerWeaponInGame.setLayoutY(gamePlayPlayerWeapon[1][selectedHero[0]]);
                                try {
                                    playerHeadInGame.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/head_%d.png", selectedHero[0]))));
                                    playerWeaponInGame.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/weapon_%d.png", selectedHero[0]))));
                                } catch (Exception e) {}
                                maunScene.setRoot(gamePlay);
                                pane[0] = "gamePlay";
                                gameAnimation.play();
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
                                    // Todo Анимация слайдера
                                }
                                break;
                        }
                        if (unlockedHeroes[menuNavigator[0]][menuNavigator[1]]) {
                            selectedHero[0] = menuNavigator[0] * 5 + menuNavigator[1];
                            playerHead.setLayoutX(playMenuPlayerHead[0][selectedHero[0]]); playerHead.setLayoutY(playMenuPlayerHead[1][selectedHero[0]]);
                            playerWeapon.setLayoutX(playMenuPlayerWeapon[0][selectedHero[0]]); playerWeapon.setLayoutY(playMenuPlayerWeapon[1][selectedHero[0]]);
                            try {
                                playerHead.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/head_%d.png", selectedHero[0]))));
                                playerWeapon.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/weapon_%d.png", selectedHero[0]))));
                            } catch (Exception e) {}
                        }
                    }
                    break;

                case "gamePlay":
                    switch (key.getCode()) {
                        case W:
                            pressed[0] = true;
                            break;
                        case A:
                            pressed[1] = true;
                            break;
                        case S:
                            pressed[2] = true;
                            break;
                        case D:
                            pressed[3] = true;
                            break;
                        case SPACE:

                            break;
                    }
                    break;
            }
        });
        maunScene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if (pane[0] == "gamePlay"){
                switch (key.getCode()) {
                    case W:
                        pressed[0] = false;
                        break;
                    case A:
                        pressed[1] = false;
                        if(!pressed[3]){
                            playerHeadInGame.setLayoutX(gamePlayPlayerHead[0][selectedHero[0]]);
                            playerWeaponInGame.setLayoutX(gamePlayPlayerWeapon[0][selectedHero[0]]);
                            playerFootInGame.setLayoutX(848);
                            playerBodyInGame.setLayoutX(708);
                        }
                        if(isRotated[0]){
                            isRotated[0] = false;
                            playerBodyInGame.setLayoutY(playerBodyInGame.getLayoutY()-17);
                            playerFootInGame.setLayoutY(playerFootInGame.getLayoutY()-8);
                            playerBodyInGame.getTransforms().add(playerRotPlus);
                            playerFootInGame.getTransforms().add(playerRotPlus);
                        }
                        break;
                    case S:
                        pressed[2] = false;
                        break;
                    case D:
                        pressed[3] = false;
                        if(!pressed[1]){
                            playerHeadInGame.setLayoutX(gamePlayPlayerHeadReverted[selectedHero[0]]);
                            playerWeaponInGame.setLayoutX(gamePlayPlayerWeaponReverted[selectedHero[0]]);
                            playerFootInGame.setLayoutX(898);
                            playerBodyInGame.setLayoutX(841);
                        }
                        if(isRotated[0]){
                            isRotated[0] = false;
                            playerBodyInGame.setLayoutY(playerBodyInGame.getLayoutY()-17);
                            playerFootInGame.setLayoutY(playerFootInGame.getLayoutY()-8);
                            playerBodyInGame.getTransforms().add(playerRotPlus);
                            playerFootInGame.getTransforms().add(playerRotPlus);
                        }
                        break;
                    case SPACE:

                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}