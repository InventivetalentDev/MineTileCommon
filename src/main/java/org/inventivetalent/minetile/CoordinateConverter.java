package org.inventivetalent.minetile;

public class CoordinateConverter {

	public static double localToGlobal(double d, int tileI, int tileSize, double centerD, boolean localIsGlobal) {
		if (localIsGlobal) { return d; }
		return d - centerD + (tileI * tileSize * 2 * 16);
	}

	public static double globalToLocal(double d, int tileI, int tileSize, double centerD, boolean localIsGlobal) {
		if (localIsGlobal) { return d; }
		return d - (tileI * tileSize * 2 * 16) + centerD;
	}

	public static int tile(double d, int tileSize, double offset) {
		return (int) Math.floor((d - offset) / 16 / tileSize / 2);
	}

	public static int tile16th(double d, int tileSize, double offset) {
		return (int) Math.floor((d - offset)  / tileSize / 2);
	}

}
