package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

/** Example class that moves around in a network of Cities. */
public class Agent {

    CityMap cityMap;

    float x;
    float y;

    float speed = 5f;
    float deltaX = 0;
    float deltaY = 0;

    City previousCity;
    Queue<City> pathQueue = new Queue<>();

    public Agent(CityMap cityMap, City start) {
        this.cityMap = cityMap;
        this.x = start.x;
        this.y = start.y;
        this.previousCity = start;
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f, 0f, 0f, 1);
        shapeRenderer.circle(x, y, 5);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.circle(x, y, 5);
        shapeRenderer.end();
    }

    public void step() {
        x += deltaX;
        y += deltaY;
        checkCollision();
    }

    /**
     * Set the goal City, calculate a path, and start moving.
     */
    public void setGoal(City goal) {
        GraphPath<City> graphPath = cityMap.findPathBetween(previousCity, goal);
        for (int i = 1; i < graphPath.getCount(); i++) {
            pathQueue.addLast(graphPath.get(i));
        }
        setSpeedToNextCity();
    }

    /**
     * Check whether Agent has reached the next City in its path.
     */
    private void checkCollision() {
        if (pathQueue.size > 0) {
            City targetCity = pathQueue.first();
            if (Vector2.dst(x, y, targetCity.x, targetCity.y) < 5) {
                reachNextCity();
            }
        }
    }

    /**
     * Agent has collided with the next City in its path.
     */
    private void reachNextCity() {

        City nextCity = pathQueue.first();

        // Set the position to keep the Agent in the middle of the path
        this.x = nextCity.x;
        this.y = nextCity.y;

        this.previousCity = nextCity;
        pathQueue.removeFirst();

        if (pathQueue.size == 0) {
            reachDestination();
        } else {
            setSpeedToNextCity();
        }
    }

    /**
     * Set xSpeed and ySpeed to move towards next City on path.
     */
    private void setSpeedToNextCity() {
        City nextCity = pathQueue.first();
        float angle = MathUtils.atan2(nextCity.y - previousCity.y, nextCity.x - previousCity.x);
        deltaX = MathUtils.cos(angle) * speed;
        deltaY = MathUtils.sin(angle) * speed;
    }

    /**
     * Agent has reached the goal City.
     */
    private void reachDestination() {
        deltaX = 0;
        deltaY = 0;

        // Find a new goal City
        City newGoal;
        do {
            newGoal = cityMap.cities.random();
        } while (newGoal == previousCity);

        setGoal(newGoal);
    }
}
