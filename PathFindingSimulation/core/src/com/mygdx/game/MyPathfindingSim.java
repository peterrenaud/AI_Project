package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyPathfindingSim extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	BitmapFont font;

	CityMap ontarioCityMap;
	GraphPath<City> cityPath;

	Agent car;

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();

		ontarioCityMap = new CityMap();

		// Windsor will act as the start city
		City windsor = new City(70, 50, "Windsor");
		City chatham = new City(150, 130, "Chatham");
		City london = new City(230, 210, "London");
		City brantford = new City(330, 210, "Brantford");
		City dundas = new City(420, 180, "Dundas");
		City sarnia = new City(50, 210, "Sarnia");
		City markdale = new City(80, 430, "Markdale");
		City waterloo = new City(330, 290, "Waterloo");
		City kitchener = new City(380, 350, "Kitchener");
		City guelph = new City(510, 360, "Guelph");
		City milton = new City(500, 450, "Milton");
		City toronto = new City(600, 160, "Toronto");
		City brampton = new City(340, 450, "Brampton");
		City vaughn = new City(550, 560, "Vaughn");
		City oakville = new City(500, 220, "Oakville");
		City hamilton = new City(320, 90, "Hamilton");
		// here markham is the goal city
		City markham = new City(650, 500, "Markham");

		// Create object which simulates car travelling through the city map
		car = new Agent(ontarioCityMap, windsor);

		ontarioCityMap.addCity(windsor);
		ontarioCityMap.addCity(chatham);
		ontarioCityMap.addCity(sarnia);
		ontarioCityMap.addCity(london);
		ontarioCityMap.addCity(milton);
		ontarioCityMap.addCity(waterloo);
		ontarioCityMap.addCity(kitchener);
		ontarioCityMap.addCity(toronto);
		ontarioCityMap.addCity(guelph);
		ontarioCityMap.addCity(brampton);
		ontarioCityMap.addCity(vaughn);
		ontarioCityMap.addCity(oakville);
		ontarioCityMap.addCity(markdale);
		ontarioCityMap.addCity(markham);
		ontarioCityMap.addCity(hamilton);
		ontarioCityMap.addCity(brantford);
		ontarioCityMap.addCity(dundas);

		ontarioCityMap.connectCities(windsor, chatham);
		ontarioCityMap.connectCities(chatham, london);
		ontarioCityMap.connectCities(london, waterloo);
		ontarioCityMap.connectCities(london, sarnia);
		ontarioCityMap.connectCities(waterloo, kitchener);
		ontarioCityMap.connectCities(kitchener, guelph);
		ontarioCityMap.connectCities(guelph, milton);
		ontarioCityMap.connectCities(london, brantford);
		ontarioCityMap.connectCities(brantford, dundas);
		ontarioCityMap.connectCities(dundas, oakville);
		ontarioCityMap.connectCities(oakville, toronto);
		ontarioCityMap.connectCities(toronto, markham);
		ontarioCityMap.connectCities(sarnia, markdale);
		ontarioCityMap.connectCities(markdale, vaughn);
		ontarioCityMap.connectCities(toronto, oakville);
		ontarioCityMap.connectCities(oakville, guelph);
		ontarioCityMap.connectCities(kitchener, milton);
		ontarioCityMap.connectCities(milton, brampton);
		ontarioCityMap.connectCities(kitchener, brampton);
		ontarioCityMap.connectCities(waterloo, markdale);
		ontarioCityMap.connectCities(chatham, hamilton);
		ontarioCityMap.connectCities(toronto, hamilton);
		ontarioCityMap.connectCities(markham, vaughn);

		cityPath = ontarioCityMap.findPathBetween(windsor, vaughn);

		car.setGoal(markdale);
	}

	@Override
	public void render() {
//		Gdx.graphics.setWindowedMode(1000, 600);

		Gdx.gl.glClearColor(0.309f, 0.329f, 0.349f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for (Street street : ontarioCityMap.streets) {
			street.render(shapeRenderer);
		}

		// Draw all cities blue
		for (City city : ontarioCityMap.cities) {
			city.render(shapeRenderer, batch, font, false);
		}

		// Draw cities in path green
		for (City city : cityPath) {
			city.render(shapeRenderer, batch, font, true);
		}

//		car.render(shapeRenderer, batch);
	}

	public void update() {
		car.step();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
		batch.dispose();
		font.dispose();
	}
}
