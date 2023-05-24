package com.example.mygame;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.CacheHint;
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
import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.random;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        Scale scale = new Scale(resolution.getWidth()/1920, resolution.getHeight()/1080, 0, 0);

        // Все возможные меню
        Pane mainMenu = new Pane(); mainMenu.getTransforms().add(scale);
        mainMenu.setCache(true);
        Pane playMenu = new Pane(); playMenu.getTransforms().add(scale);
        playMenu.setCache(true);
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
        ImageView blackBackground = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/black.png")));
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
            floorImage[i] = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/floor.png"), 800, 0, true, true));
        }
        int[] floorCoords = {-1000, -200}; // X и Y
        floorImage[0].setLayoutY(floorCoords[1]);
        floorImage[1].setLayoutY(floorCoords[1]);
        floorImage[2].setLayoutY(floorCoords[1] + 2990);
        floorImage[3].setLayoutY(floorCoords[1] + 2990);
        floorImage[0].setLayoutX(floorCoords[0]);
        floorImage[1].setLayoutX(floorCoords[0] + 2990);
        floorImage[2].setLayoutX(floorCoords[0]);
        floorImage[3].setLayoutX(floorCoords[0] + 2990);
        gamePlay.getChildren().addAll(blackBackground, floorImage[0], floorImage[1], floorImage[2], floorImage[3]);
        Enemy[] enemy = {new Enemy(0, 0), new Enemy(0, 0), new Enemy(0, 0), new Enemy(0, 0), new Enemy(0, 0), new Enemy(0, 0), new Enemy(0, 0), new Enemy(0, 0)};
        for (int i = 0; i < 8; i++){
            switch ((int)(random() * 8)){
                case 0 -> {
                    enemy[i].setPosX((int) (-600 + random() * 500));
                    enemy[i].setPosY((int) (-600 + random() * 500));}
                case 1 -> {
                    enemy[i].setPosX((int) (random() * 1900));
                    enemy[i].setPosY((int) (-600 + random() * 500));}
                case 2 -> {
                    enemy[i].setPosX((int) (1900 + random() * 500));
                    enemy[i].setPosY((int) (-600 + random() * 500));}
                case 3 -> {
                    enemy[i].setPosX((int) (1920 + random() * 500));
                    enemy[i].setPosY((int) (random() * 1080));}
                case 4 -> {
                    enemy[i].setPosX((int) (1920 + random() * 500));
                    enemy[i].setPosY((int) (1080 + random() * 500));}
                case 5 -> {
                    enemy[i].setPosX((int) (random() * 1900));
                    enemy[i].setPosY((int) (1080 + random() * 500));}
                case 6 -> {
                    enemy[i].setPosX((int) (-600 + (random() * 500)));
                    enemy[i].setPosY((int) (1080 + (random() * 500)));}
                case 7 -> {
                    enemy[i].setPosX((int) (-600 + (random() * 500)));
                    enemy[i].setPosY((int) (random() * 1080));}
            }
            gamePlay.getChildren().addAll(enemy[i].getFoot(), enemy[i].getBody(), enemy[i].getHead());
        }
        ImageView darkImage = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/dark.png")));
        ImageView healthBar = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/health_bar.png"))); healthBar.setLayoutX(20); healthBar.setLayoutY(20);
        ImageView killBar = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/kill_bar.png"))); killBar.setLayoutX(1530); killBar.setLayoutY(20);
        Label killerLabel = new Label("0"); killerLabel.setLayoutY(13); killerLabel.setLayoutX(1623); killerLabel.setFont(mainFont); killerLabel.setTextFill(Color.rgb(255, 255, 255));
        gamePlay.getChildren().addAll(playerWeaponInGame, playerFootInGame, playerBodyInGame, playerHeadInGame, darkImage, healthBar, killBar, killerLabel);
        gamePlay.setCache(true); gamePlay.setCacheHint(CacheHint.SPEED);

        stage.setTitle("Dungeon Of Fallen Hearts");
        stage.setScene(maunScene);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);
        stage.show();

        // Переменные для логики
        String[] pane = {"mainMenu"};
        byte[] menuNavigator = new byte[2];
        double[] menuNavigatorImageLocation = {145, 321, 501, 679, 853};
        double[] playMenuNavigatorImageLocationY = {78, 229, 373, 510};
        double[] playMenuNavigatorImageLocationX = {41, 187, 335, 482, 623};
        double[] playMenuSliderImageLocation = {67, 194, 318, 443, 582};
        double[][] playMenuPlayerHead = {{895, 955, 955}, {282, 268, 268}}; // Первый столбик - Х, второй - Y.
        double[][] playMenuPlayerWeapon = {{905, 905, 905}, {350, 350, 350}}; // Первый столбик - Х, второй - Y.
        Hero hero = new Hero();
        boolean[][] unlockedHeroes = new boolean[4][5]; unlockedHeroes[0][0] = true; unlockedHeroes[0][1] = true; unlockedHeroes[0][2] = true;
        boolean[] pressed = {false, false, false, false, false}; // W A S D Space
        boolean[] isReverted = {false}; boolean[] isRotated = {false};
        byte[] footPos = {1}; // 0 - право; 1 - начальная; 2 - лево
        short[] footPixelPos = {0};
        byte[] timer = {0}; //Первый таймер - нога, второй - атака
        short[] swordRotation = {360};
        byte[][] gamePlayWeaponAttack = {{0, 30, 40, 5, -15 ,-23, -15, -7, 25, 15, 0}, {0, -25, -50, -25, -20 ,0, 25, 50, 65, 30, 0}}; // 0 - X, 1 - Y
        Rotate imageFlip = new Rotate(180, Rotate.Y_AXIS);
        Rotate playerRotPlus = new Rotate(15, Rotate.Z_AXIS);
        Rotate playerRotMinus = new Rotate(-15, Rotate.Z_AXIS);
        int[] killerCount = {0};
        final Animation gameAnimation = new Transition() {
            {
                setCycleDuration(Duration.seconds(Integer.MAX_VALUE));
            }
            protected void interpolate(double frac) {
                // ЛОГИКА ИГРЫ ТУТ
                timer[0] += 1;
                if(timer[0] == Byte.MAX_VALUE) timer[0] = 0;
                // Перемещение всего при движении
                killerLabel.setText(Integer.toString(killerCount[0]));
                for(int i = 0; i < 8; i++){
                    enemy[i].pursuit(hero.getBodyY(), (short) playerBodyInGame.getLayoutX());
                }
                if(pressed[0]){
                    floorCoords[1] += 10;
                    for (int i = 0; i < 8; i++) {
                        enemy[i].moveY((short) 10);
                    }
                }
                if(pressed[1]){
                    floorCoords[0] += 10;
                    for (int i = 0; i < 8; i++) {
                        enemy[i].moveX((short) 10);
                    }
                }
                if(pressed[2]){
                    floorCoords[1] -= 10;
                    for (int i = 0; i < 8; i++) {
                        enemy[i].moveY((short) -10);
                    }
                }
                if(pressed[3]){
                    floorCoords[0] -= 10;
                    for (int i = 0; i < 8; i++) {
                        enemy[i].moveX((short) -10);
                    }
                }
                // Создает бесконечность поля
                if(floorCoords[1] >= -30){
                    floorCoords[1] -= 2990;
                }
                if(floorCoords[0] >= -30){
                    floorCoords[0] -= 2990;
                }
                if(floorCoords[1] <= -4800){
                    floorCoords[1] += 2990;
                }
                if(floorImage[0].getLayoutX() <= -4000){
                    floorCoords[0] += 2990;
                }
                if(pressed[0] || pressed[1] || pressed[2] || pressed[3]){
                    floorImage[0].setLayoutY(floorCoords[1]);
                    floorImage[1].setLayoutY(floorCoords[1]);
                    floorImage[2].setLayoutY(floorCoords[1] + 2990);
                    floorImage[3].setLayoutY(floorCoords[1] + 2990);
                    floorImage[0].setLayoutX(floorCoords[0]);
                    floorImage[1].setLayoutX(floorCoords[0] + 2990);
                    floorImage[2].setLayoutX(floorCoords[0]);
                    floorImage[3].setLayoutX(floorCoords[0] + 2990);
                }
                // Проверка положения тела персонажа на наклон и поворот
                if(pressed[1]){
                    if (isReverted[0] && !pressed[3]) {
                        isReverted[0] = false;
                        playerBodyInGame.getTransforms().add(imageFlip);
                        playerFootInGame.getTransforms().add(imageFlip);
                        playerHeadInGame.getTransforms().add(imageFlip);
                        playerWeaponInGame.getTransforms().add(imageFlip);
                        playerHeadInGame.setLayoutX(hero.getHeadX());
                        playerWeaponInGame.setLayoutX(hero.getWeaponX());
                        playerBodyInGame.setLayoutX(708);
                    }
                    if(!isRotated[0] && !pressed[3]){
                        playerBodyInGame.setLayoutY(hero.getBodyY()+17);
                        playerFootInGame.setLayoutY(hero.getFootY()+8);
                        playerBodyInGame.getTransforms().add(playerRotMinus);
                        playerFootInGame.getTransforms().add(playerRotMinus);
                        isRotated[0] = true;
                    }
                }
                if(pressed[3]){
                    if (!isReverted[0] && !pressed[1]) {
                        isReverted[0] = true;
                        playerBodyInGame.getTransforms().add(imageFlip);
                        playerFootInGame.getTransforms().add(imageFlip);
                        playerHeadInGame.getTransforms().add(imageFlip);
                        playerWeaponInGame.getTransforms().add(imageFlip);
                        playerHeadInGame.setLayoutX(hero.getHeadXReverted());
                        playerWeaponInGame.setLayoutX(hero.getWeaponXReverted());
                        playerBodyInGame.setLayoutX(841);
                    }
                    if(!isRotated[0] && !pressed[1]){
                        playerBodyInGame.setLayoutY(hero.getBodyY()+17);
                        playerFootInGame.setLayoutY(hero.getFootY()+8);
                        playerBodyInGame.getTransforms().add(playerRotMinus);
                        playerFootInGame.getTransforms().add(playerRotMinus);
                        isRotated[0] = true;
                    }
                }
                if(pressed[1] && pressed[3] && isRotated[0]){
                    isRotated[0] = false;
                    playerBodyInGame.setLayoutY(hero.getBodyY());
                    playerFootInGame.setLayoutY(hero.getFootY());
                    playerBodyInGame.getTransforms().add(playerRotPlus);
                    playerFootInGame.getTransforms().add(playerRotPlus);
                }

                // Контроль ноги
                if (timer[0] % 4 == 0){
                    if(pressed[0] || pressed[2] || isRotated[0]){
                        footPos[0] = (byte)((footPos[0] + 1) % 3);
                        if (isReverted[0]){
                            if(isRotated[0]){
                                footPixelPos[0] = 889;
                            } else {
                                footPixelPos[0] = 898;
                            }
                        } else {
                            if(isRotated[0]){
                                footPixelPos[0] = 857;
                            } else {
                                footPixelPos[0] = 848;
                            }
                        }
                        switch (footPos[0]) {
                            case 0 -> footPixelPos[0] -= 5;
                            case 2 -> footPixelPos[0] += 5;
                        }
                    } else {
                        if (isReverted[0]){
                            footPixelPos[0] = 898;
                        } else {
                            footPixelPos[0] = 848;
                        }
                    }
                    playerFootInGame.setLayoutX(footPixelPos[0]);
                }
                // Анимация меча
                if(pressed[4] || swordRotation[0] != 360){
                    if(swordRotation[0] == 360) swordRotation[0] = 0;
                    if(timer[0] % 2 == 0){
                        playerWeaponInGame.getTransforms().add(playerRotMinus);
                        if (isReverted[0]){
                            swordRotation[0] += 15;
                            playerWeaponInGame.setLayoutX(hero.getWeaponXReverted() - gamePlayWeaponAttack[0][abs(swordRotation[0]) / 36]);
                        } else {
                            swordRotation[0] -= 15;
                            playerWeaponInGame.setLayoutX(hero.getWeaponX() + gamePlayWeaponAttack[0][abs(swordRotation[0]) / 36]);
                        }
                        playerWeaponInGame.setLayoutY(hero.getWeaponY() + gamePlayWeaponAttack[1][abs(swordRotation[0]) / 36]);
                    }
                    if(swordRotation[0] == -360) swordRotation[0] = 360;
                }
            }
        };

        maunScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (pane[0]) {
                case "mainMenu" -> {
                    for (int i = 0; i < 5; i++) mainMenuLabels[i].setTextFill(Color.rgb(98, 17, 17));
                    switch (key.getCode()) {
                        case W -> {
                            menuNavigator[0] = (byte)((menuNavigator[0] - 1) % 5);
                            if (menuNavigator[0] < 0)
                                menuNavigator[0] = 4;
                            menuNavigatorImage.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
                        }
                        case S -> {
                            menuNavigator[0] = (byte)((menuNavigator[0] + 1) % 5);
                            menuNavigatorImage.setLayoutY(menuNavigatorImageLocation[menuNavigator[0]]);
                        }
                        case SPACE -> {
                            switch (menuNavigator[0]) {
                                case 0 -> {
                                    maunScene.setRoot(playMenu);
                                    mainMenu.getChildren().removeAll();
                                    pane[0] = "playMenu";
                                }
                                case 1 -> {
                                    maunScene.setRoot(shopMenu); // TODO Магазин
                                    pane[0] = "shopMenu";
                                }
                                case 2 -> {
                                    maunScene.setRoot(bestiaryMenu); // TODO Бестиарий
                                    pane[0] = "bestiaryMenu";
                                }
                                case 3 -> {
                                    maunScene.setRoot(settingsMenu); // TODO Настройки
                                    pane[0] = "settingsMenu";
                                }
                                default -> Platform.exit();
                            }
                            menuNavigator[0] = 0;
                        }
                    }
                    mainMenuLabels[menuNavigator[0]].setTextFill(Color.rgb(255, 255, 255));
                }
                case "playMenu" -> {
                    if (hero.getSelectedHero() != -1) {
                        switch (key.getCode()) {
                            case A -> {
                                menuNavigator[0] = (byte)((menuNavigator[0] - 1) % 5);
                                if (menuNavigator[0] < 0)
                                    menuNavigator[0] = 4;
                                playMenuSliderImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]]);
                                playMenuSliderNavigatorImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]] - 51);
                            }
                            case D -> {
                                menuNavigator[0] = (byte)((menuNavigator[0] + 1) % 5);
                                playMenuSliderImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]]);
                                playMenuSliderNavigatorImage.setLayoutX(playMenuSliderImageLocation[menuNavigator[0]] - 51);
                            }
                            case SPACE -> {
                                playerHeadInGame.setLayoutX(hero.getHeadX());
                                playerHeadInGame.setLayoutY(hero.getHeadY());
                                playerWeaponInGame.setLayoutX(hero.getWeaponX());
                                playerWeaponInGame.setLayoutY(hero.getWeaponY());
                                try {
                                    playerHeadInGame.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/head_%d.png", hero.getSelectedHero()))));
                                    playerWeaponInGame.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/weapon_%d.png", hero.getSelectedHero()))));
                                } catch (Exception ignored) {}
                                maunScene.setRoot(gamePlay);
                                playMenu.getChildren().removeAll();
                                pane[0] = "gamePlay";
                                gameAnimation.play();
                            }
                        }
                    } else {
                        switch (key.getCode()) {
                            case W -> {
                                menuNavigator[0] = (byte)((menuNavigator[0] - 1) % 4);
                                if (menuNavigator[0] < 0)
                                    menuNavigator[0] = 3;
                                playMenuNavigatorImage.setLayoutY(playMenuNavigatorImageLocationY[menuNavigator[0]]);
                            }
                            case S -> {
                                menuNavigator[0] = (byte)((menuNavigator[0] + 1) % 4);
                                playMenuNavigatorImage.setLayoutY(playMenuNavigatorImageLocationY[menuNavigator[0]]);
                            }
                            case A -> {
                                menuNavigator[1] = (byte)((menuNavigator[1] - 1) % 5);
                                if (menuNavigator[1] < 0)
                                    menuNavigator[1] = 4;
                                playMenuNavigatorImage.setLayoutX(playMenuNavigatorImageLocationX[menuNavigator[1]]);
                            }
                            case D -> {
                                menuNavigator[1] = (byte)((menuNavigator[1] + 1) % 5);
                                playMenuNavigatorImage.setLayoutX(playMenuNavigatorImageLocationX[menuNavigator[1]]);
                            }
                            case SPACE -> {
                                if (unlockedHeroes[menuNavigator[0]][menuNavigator[1]]) {
                                    hero.setSelectedHero((byte)(menuNavigator[0] * 5 + menuNavigator[1]));
                                    playMenuNavigatorImage.setOpacity(0.0);
                                    playMenuSliderNavigatorImage.setOpacity(1.0);
                                    // Todo Анимация слайдера
                                }
                            }
                        }
                        if (unlockedHeroes[menuNavigator[0]][menuNavigator[1]]) {
                            playerHead.setLayoutX(playMenuPlayerHead[0][(byte)(menuNavigator[0] * 5 + menuNavigator[1])]);
                            playerHead.setLayoutY(playMenuPlayerHead[1][(byte)(menuNavigator[0] * 5 + menuNavigator[1])]);
                            playerWeapon.setLayoutX(playMenuPlayerWeapon[0][(byte)(menuNavigator[0] * 5 + menuNavigator[1])]);
                            playerWeapon.setLayoutY(playMenuPlayerWeapon[1][(byte)(menuNavigator[0] * 5 + menuNavigator[1])]);
                            try {
                                playerHead.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/head_%d.png", (menuNavigator[0] * 5 + menuNavigator[1])))));
                                playerWeapon.setImage(new Image(new FileInputStream(String.format("src/main/java/com/example/mygame/weapon_%d.png", (menuNavigator[0] * 5 + menuNavigator[1])))));
                            } catch (Exception ignored) {}
                        }
                    }
                }
                case "gamePlay" -> {
                    switch (key.getCode()) {
                        case W -> pressed[0] = true;
                        case A -> pressed[1] = true;
                        case S -> pressed[2] = true;
                        case D -> pressed[3] = true;
                        case SPACE -> pressed[4] = true;
                    }
                }
            }
        });
        maunScene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if (Objects.equals(pane[0], "gamePlay")){
                switch (key.getCode()) {
                    case W -> pressed[0] = false;
                    case A -> {
                        pressed[1] = false;
                        if (!pressed[3]) {
                            playerHeadInGame.setLayoutX(hero.getHeadX());
                            playerWeaponInGame.setLayoutX(hero.getWeaponX());
                            playerFootInGame.setLayoutX(848);
                            playerBodyInGame.setLayoutX(708);
                        }
                        if (isRotated[0]) {
                            isRotated[0] = false;
                            playerBodyInGame.setLayoutY(hero.getBodyY());
                            playerFootInGame.setLayoutY(hero.getFootY());
                            playerBodyInGame.getTransforms().add(playerRotPlus);
                            playerFootInGame.getTransforms().add(playerRotPlus);
                        }
                    }
                    case S -> pressed[2] = false;
                    case D -> {
                        pressed[3] = false;
                        if (!pressed[1]) {
                            playerHeadInGame.setLayoutX(hero.getHeadXReverted());
                            playerWeaponInGame.setLayoutX(hero.getWeaponXReverted());
                            playerFootInGame.setLayoutX(898);
                            playerBodyInGame.setLayoutX(841);
                        }
                        if (isRotated[0]) {
                            isRotated[0] = false;
                            playerBodyInGame.setLayoutY(hero.getBodyY());
                            playerFootInGame.setLayoutY(hero.getFootY());
                            playerBodyInGame.getTransforms().add(playerRotPlus);
                            playerFootInGame.getTransforms().add(playerRotPlus);
                        }
                    }
                    case SPACE -> pressed[4] = false;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}