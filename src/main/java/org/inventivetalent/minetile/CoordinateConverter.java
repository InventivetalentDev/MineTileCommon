package org.inventivetalent.minetile;

public class CoordinateConverter {

	public static double localToGlobal(double d, int tileI, int tileSize, double centerD) {
		return d - centerD + (tileI * tileSize * 2 * 16);
	}

	public static double globalToLocal(double d, int tileI, int tileSize, double centerD) {
		return d - (tileI * tileSize * 2 * 16) + centerD;
	}




}
