package com.deco2800.redacted.map;

import com.deco2800.redacted.util.Point;

/**
* Class MapGenerator
* Class that runs SimplexNoise generator with given parameters
* Prepared by Arda Akgur
*/
public class MapGenerator {
    // output
    private double [][]totalNoise;
    // id of this instance
    private double id;
    
    /**
    * Constructor assigns id for instance
    */
    public MapGenerator() {
        this.id = Math.random() * 100;
    }
    
    /**
    * Runs simplex noise and returns totalNoise
    */
    public double[][] generate(int width, int height, int octaves, double roughness, double scale) {
        totalNoise = generateOctavedSimplexNoise(width, height, octaves, roughness, scale);
        return totalNoise;
    }
    
    
    
    /**
    * Runs simplexNoise with given amount of time 
    * adds riyghness each iteration and scales it accordingly
    * @param width, height - width and height of the city
    * @param octaves - amount of layers for map generator (how many times noise gen should run)
    * @param roughness - extra weight to be added each layer
    * @param scale - scale frequency for each layer
    * Default map 100, 100, 10, 0.4f, 0.008f
    */
    private double[][] generateOctavedSimplexNoise(int width, int height, int octaves, double roughness, double scale) {
		double[][] result = new double[width][height];
		double layerFrequency = scale;
		double layerWeight = 1;

		for (int octave = 0; octave < octaves; octave++) {
			// Calculate single layer/octave of simplex noise, then add it to total noise
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					result[x][y] += (double) SimplexNoise.noise(x * layerFrequency, y * layerFrequency)
							* layerWeight;
				}
			}

			// Increase variables with each incrementing octave
			layerFrequency *= 2;
			layerWeight *= roughness;

		}
		return result;
	}
}