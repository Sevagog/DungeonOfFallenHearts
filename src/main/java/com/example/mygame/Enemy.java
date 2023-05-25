package com.example.mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Enemy {
    int posX, posY; byte difficulty;
    private ImageView head;
    private ImageView body;
    private ImageView foot;
    private Rotate imFlip = new Rotate(180, Rotate.Y_AXIS);
    private boolean isFlipped = false;
    private Random random = new Random();


    public Enemy(int posX, int posY, byte difficulty) throws FileNotFoundException {
        this.posX = posX;
        this.posY = posY;
        body = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/enemy_body.png")));
        body.setScaleX(0.3); body.setScaleY(0.3); body.setLayoutX(posX); body.setLayoutY(posY);
        head = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/enemy_head.png")));
        head.setScaleX(0.3); head.setScaleY(0.3); head.setLayoutX(posX+10); head.setLayoutY(posY-20);
        foot = new ImageView(new Image(new FileInputStream("src/main/java/com/example/mygame/enemy_foot.png")));
        foot.setScaleX(0.3); foot.setScaleY(0.3); foot.setLayoutX(posX+10); foot.setLayoutY(posY+30);
    }

    public boolean intersect(){
        if(posX > 700 && posX < 800 && posY > 400 && posY < 600){
            return true;
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
        if(isFlipped){
            head.setLayoutX(posX+130);
            foot.setLayoutX(posX+140);
            body.setLayoutX(posX + 100);
        }else{
            head.setLayoutX(posX+70);
            foot.setLayoutX(posX+133);
            body.setLayoutX(posX);
        }

    }
    public void moveY(int moveY) {
        this.posY += moveY;
        body.setLayoutY(posY);
        head.setLayoutY(posY-220);
        foot.setLayoutY(posY+95);
    }

    public void pursuit(){
        if(posY <= 540){
            if(posX >= 750){
                //возврат в исходное его положение
                if(isFlipped){
                    head.getTransforms().add(imFlip);
                    body.getTransforms().add(imFlip);
                    foot.getTransforms().add(imFlip);
                    isFlipped=false;
                }
                moveX(-(random.nextInt(4) + 2 + 2*difficulty));
            }else if (posX <= 700){
                // отзеркаливание противника - направление движения вправо
                if(!isFlipped){
                    head.getTransforms().add(imFlip);
                    body.getTransforms().add(imFlip);
                    foot.getTransforms().add(imFlip);
                    isFlipped =true;
                }
                moveX(random.nextInt(4) + 2 + 2*difficulty);
            }
            moveY(random.nextInt(4) + 2 + 2*difficulty);
        } else {
            if(posX >= 760){
                //возрат в исходное положение
                if(isFlipped){
                    head.getTransforms().add(imFlip);
                    body.getTransforms().add(imFlip);
                    foot.getTransforms().add(imFlip);
                    isFlipped=false;
                }
                moveX(-(random.nextInt(4) + 2 + 2*difficulty));
            } else if (posX <= 710){
                //отзеркаливание противника
                if(!isFlipped){
                    head.getTransforms().add(imFlip);
                    body.getTransforms().add(imFlip);
                    foot.getTransforms().add(imFlip);
                    isFlipped =true;
                }
                moveX(random.nextInt(4) + 2 + 2*difficulty);
            }
            moveY(-(random.nextInt(4) + 2 + 2*difficulty));
        }
    }
}
