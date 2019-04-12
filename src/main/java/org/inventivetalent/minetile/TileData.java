package org.inventivetalent.minetile;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.gson.annotations.Expose;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TileData implements ByteArraySerializable<TileData> {

	@Expose public int x;
	@Expose public int z;
	@Expose public int y;

	public TileData() {
	}

	public TileData(int x, int z, int y) {
		this.x = x;
		this.z = z;
		this.y = y;
	}

	@Override
	public String toString() {
		return "TileData{" +
				"x=" + x +
				", z=" + z +
				", y=" + y +
				'}';
	}

	public TileData readFromByteArray(ByteArrayDataInput in) {
		this.x = in.readInt();
		this.z = in.readInt();
		this.y = in.readInt();

		return this;
	}

	public TileData writeToByteArray(ByteArrayDataOutput out) {
		out.writeInt(this.x);
		out.writeInt(this.z);
		out.writeInt(this.y);

		return this;
	}

	public String[] toDataArray() {
		return new String[] {
				"" + x,
				"" + z,
				"" + y
		};
	}

	public String toDataString() {
		return String.join(";", toDataArray());
	}

	public static TileData fromDataArray(String[] array) {
		return new TileData(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
	}

	public static TileData fromDataString(String data) {
		return fromDataArray(data.split(";"));
	}

	public static TileData fromSQL(ResultSet res) throws SQLException {
		return new TileData(
				res.getInt("x"),
				res.getInt("z"),
				0);
	}

}
