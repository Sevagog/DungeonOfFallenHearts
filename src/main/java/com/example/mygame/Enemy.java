package com.example.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Enemy {
    int posX, posY;
    private ImageView head;
    private ImageView body;
    private ImageView foot;
    private Rotate imflip = new Rotate(180, Rotate.Y_AXIS);
    private boolean isflipped = false;
    private Random random = new Random();

    private int length = 35;
    private int height = 60;


    public Enemy(int posX, int posY) throws FileNotFoundException {
        this.posX = posX;
        this.posY = posY;
        body = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/enemy_body.png")));
        body.setScaleX(0.3); body.setScaleY(0.3); body.setLayoutX(posX); body.setLayoutY(posY);
        head = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/enemy_head.png")));
        head.setScaleX(0.3); head.setScaleY(0.3); head.setLayoutX(posX+10); head.setLayoutY(posY-20);
        foot = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/enemy_foot.png")));
        foot.setScaleX(0.3); foot.setScaleY(0.3); foot.setLayoutX(posX+10); foot.setLayoutY(posY+30);
    }

    public boolean intersect(short hHeadY, short hHeadX, short hFootY, short hFootX){
        if(((foot.getLayoutY() + 35) > hHeadY) && ((foot.getLayoutY() + 35) <= hHeadY + height)){
            if(isflipped){
                if(((body.getLayoutX() + 35) >= hHeadX) && ((body.getLayoutX() + 35) <= (hHeadX + length))){
                    return true;
                }
            }else{
                if(((body.getLayoutX() - 35) >= (hHeadX + length)) && ((body.getLayoutX() - 35) <= (hHeadX))){
                    return true;
                }
            }

        } else if ((head.getLayoutY() <= (hHeadY + height)) && (head.getLayoutY() >= hHeadY) ) {
            if(isflipped){
                if(((body.getLayoutX() + 35) >= hHeadX) && ((body.getLayoutX() + 35) <= (hHeadX + length))){
                    return true;
                }
            }else{
                if(((body.getLayoutX() - 35) >= (hHeadX + length)) && ((body.getLayoutX() - 35) <= (hHeadX))){
                    return true;
                }
            }
        }
        return false;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        body.setLayoutX(posX);
        head.setLayoutX(posX+70);
        foot.setLayoutX(posX+133);
    }

    public void setPosY(int posY) {
        this.posY = posY;
        body.setLayoutY(posY);
        head.setLayoutY(posY-220);
        foot.setLayoutY(posY+95);
    }

    public ImageView getHead() {
        return head;
    }

    public ImageView getBody() {
        return body;
    }

    public ImageView getFoot() {
        return foot;
    }

    public void moveX(int moveX) {
        this.posX += moveX;
        if(isflipped){
            head.setLayoutX(posX+30);
            foot.setLayoutX(posX+40);
        }else{
            head.setLayoutX(posX+70);
            foot.setLayoutX(posX+133);
        }
        body.setLayoutX(posX);
    }
    public void moveY(int moveY) {
        this.posY += moveY;
        body.setLayoutY(posY);
        head.setLayoutY(posY-220);
        foot.setLayoutY(posY+95);
    }

    public void pursuit(short hBodyY, short hBodyX){
        if(body.getLayoutY() <= hBodyY){
            if(body.getLayoutX() >= hBodyX + 10){
                //возврат в исходное его положение
                if(isflipped){
                    head.getTransforms().add(imflip);
                    body.getTransforms().add(imflip);
                    foot.getTransforms().add(imflip);
                    isflipped=false;
                }
                moveX(-random.nextInt(4));
            }else{
                // отзеркаливание противника - направление движения вправо
                if(!isflipped){
                    head.getTransforms().add(imflip);
                    body.getTransforms().add(imflip);
                    foot.getTransforms().add(imflip);
                    isflipped =true;
                }
                moveX(random.nextInt(4));
            }
            moveY(random.nextInt(4));

        } else if(body.getLayoutY() >= hBodyY){
            if(body.getLayoutX() >= hBodyX + 10){
                //возрат в исходное положение
                if(isflipped){
                    head.getTransforms().add(imflip);
                    body.getTransforms().add(imflip);
                    foot.getTransforms().add(imflip);
                    isflipped=false;
                }
                moveX(-random.nextInt(4));
            }else{
                //отзеркаливание противника
                if(!isflipped){
                    head.getTransforms().add(imflip);
                    body.getTransforms().add(imflip);
                    foot.getTransforms().add(imflip);
                    isflipped =true;
                }
                moveX(random.nextInt(4));
            }
            moveY(-random.nextInt(4));
        }
    }
}
