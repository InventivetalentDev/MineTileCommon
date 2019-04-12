package org.inventivetalent.minetile;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MySQL {

	private Connection connection;
	public  String     prefix = "minetile_";

	private Executor executor;

	public MySQL() {
		this.executor = Executors.newCachedThreadPool();
	}

	public void execute(Runnable runnable) {
		this.executor.execute(runnable);
	}

	public void connect(String host, int port, String user, String pass, String db, String prefix) throws SQLException {
		this.prefix = prefix;
		String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true";
		this.connection = DriverManager.getConnection(url, user, pass);
	}

	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SneakyThrows
	public PreparedStatement stmt(String template)  {
		if (this.connection != null) {
			return this.connection.prepareStatement(template);
		} else {
			throw new IllegalStateException("connection is null");
		}
	}

	@SneakyThrows
	public PreparedStatement select(String fields, String table)  {
		return stmt("SELECT " + fields + " FROM " + prefix + table + ";");
	}

	@SneakyThrows
	public PreparedStatement select(String fields, String table, String condition)  {
		return stmt("SELECT " + fields + " FROM " + prefix + table + " WHERE " + condition + ";");
	}

	public boolean ensureTable(String table, String structure) throws SQLException {
		PreparedStatement stmt = stmt("CREATE TABLE IF NOT EXISTS `" + prefix + table + "` " + structure + ";");
		return stmt.execute();
	}

	public void initTables() throws SQLException{
		ensureTable("tiles", "("
				+ "`id` INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "`uuid` VARCHAR(36) NOT NULL UNIQUE KEY,"
				+ "`x` INT(64),"
				+ "`z` INT(64)"
				+ ") ENGINE = MYISAM");
		ensureTable("player_data", "("
				+ "`id` INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "`uuid` VARCHAR(36) NOT NULL UNIQUE KEY,"
				+ "`data` TEXT"
				+ ") ENGINE = MYISAM");
		ensureTable("settings", "("
				+ "`id` INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "`key` VARCHAR(255) NOT NULL UNIQUE KEY,"
				+ "`value` VARCHAR(255)"
				+ ") ENGINE = MYISAM");
		ensureTable("positions", "("
				+ "`id` INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "`uuid` VARCHAR(36) NOT NULL UNIQUE KEY,"
				+ "`x` DOUBLE,"
				+ "`y` DOUBLE,"
				+ "`z` DOUBLE,"
				+ "`yaw` FLOAT,"
				+ "`pitch` FLOAT"
				+ ") ENGINE = MYISAM");
	}

	@SneakyThrows
	public int updatePosition(UUID uuid, double x, double y, double z, float pitch, float yaw) {
		PreparedStatement stmt = stmt("INSERT INTO " + prefix + "positions (`uuid`,`x`,`y`,`z`,`pitch`,`yaw`) VALUES(?,?,?,?,?,?) "
				+ "ON DUPLICATE KEY UPDATE `x`=?, `y`=?, `z`=?, `pitch`=?, `yaw`=?;");
		stmt.setString(1, uuid.toString());
		stmt.setDouble(2, x);
		stmt.setDouble(3, y);
		stmt.setDouble(4, z);
		stmt.setFloat(5, pitch);
		stmt.setFloat(6, yaw);

		stmt.setDouble(7, x);
		stmt.setDouble(8, y);
		stmt.setDouble(9, z);
		stmt.setFloat(10, pitch);
		stmt.setFloat(11, yaw);

		return stmt.executeUpdate();
	}

	@SneakyThrows
	public int updatePosition(UUID uuid, PlayerLocation location){
		return updatePosition(uuid, location.x, location.y, location.z, location.pitch, location.yaw);
	}

	@SneakyThrows
	public boolean clearPosition(UUID uuid) {
		PreparedStatement stmt = stmt("DELETE FROM " + prefix + "positions WHERE `uuid`=?");
		stmt.setString(1, uuid.toString());
		return stmt.execute();
	}

	@SneakyThrows
	public int updatePlayerData(UUID uuid, PlayerData playerData)  {
		String playerDataString = new Gson().toJson(playerData);
		PreparedStatement stmt = stmt("INSERT INTO " + prefix + "player_data (`uuid`,`data`) VALUES(?,?) "
				+ "ON DUPLICATE KEY UPDATE `data`=?;");
		stmt.setString(1, uuid.toString());
		stmt.setString(2, playerDataString);
		stmt.setString(3, playerDataString);
		return stmt.executeUpdate();
	}

	@SneakyThrows
	public boolean clearPlayerData(UUID uuid) {
		PreparedStatement stmt = stmt("DELETE FROM " + prefix + "player_data WHERE `uuid`=?");
		stmt.setString(1, uuid.toString());
		return stmt.execute();
	}

	@SneakyThrows
	public String getSetting(String key) {
		PreparedStatement stmt = stmt("SELECT value FROM " + prefix + "settings WHERE `key`=?;");
		stmt.setString(1, key);
		ResultSet resultSet = stmt.executeQuery();
		if (resultSet.next()) {
			return resultSet.getString("value");
		}
		return null;
	}

	@SneakyThrows
	public int updateSetting(String key, String value)  {
		PreparedStatement stmt = stmt("INSERT INTO " + prefix + "settings (`key`,`value`) VALUES(?,?) "
				+ "ON DUPLICATE KEY UPDATE `value`=?;");
		stmt.setString(1, key);
		stmt.setString(2, value);
		stmt.setString(3, value);
		return stmt.executeUpdate();
	}

	@SneakyThrows
	public int updateTile(UUID uuid, int x, int z) {
		PreparedStatement stmt = stmt("INSERT INTO " + prefix + "tiles (`uuid`,`x`,`z`) VALUES(?,?,?) "
				+ "ON DUPLICATE KEY UPDATE `x`=?, `z`=?;");
		stmt.setString(1, uuid.toString());
		stmt.setInt(2, x);
		stmt.setInt(3, z);
		stmt.setInt(4, x);
		stmt.setInt(5, z);
		return stmt.executeUpdate();
	}

	@SneakyThrows
	public boolean removeTile(UUID uuid) {
		PreparedStatement stmt = stmt("DELETE FROM " + prefix + "tiles WHERE `uuid`=?");
		stmt.setString(1, uuid.toString());
		return stmt.execute();
	}
}
