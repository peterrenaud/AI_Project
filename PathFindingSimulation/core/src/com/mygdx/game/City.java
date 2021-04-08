package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class City {
    float x;
    float y;
    String name;

    /** Index used by the A* algorithm. Keep track of it so we don't have to recalculate it later. */
    int index;

    public City(float x, float y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch, BitmapFont font, boolean inPath){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(inPath) {
            // green
            shapeRenderer.setColor(.57f, .76f, .48f, 1);
        }
        else{
            // blue
            shapeRenderer.setColor(0.501f, 0.737f, 1, 1);
        }
        shapeRenderer.circle(x, y, 35);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.circle(x, y, 35);
        shapeRenderer.end();

        batch.begin();
        font.setColor(0, 0, 0, 255);
//        font.getData().setScale(1);
        font.draw(batch, name, x-28, y+5);
        batch.end();
    }
}
