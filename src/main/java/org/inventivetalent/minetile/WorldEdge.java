package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

public class WorldEdge {

	/// Might need to change to use long instead of int, if someone's insane enough to make a map that's bigger than 4 billion blocks

	@Expose public int north;
	@Expose public int east;
	@Expose public int south;
	@Expose public int west;

	public WorldEdge() {
	}

	public WorldEdge(int north, int east, int south, int west) {
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
}
