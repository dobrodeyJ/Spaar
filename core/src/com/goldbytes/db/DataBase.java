package com.goldbytes.db;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class DataBase {
	Database dbHandler;

	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";

	private static final String DATABASE_NAME = "comments.db";
	//"comments.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table if not exists " + TABLE_COMMENTS + "(" + COLUMN_ID
		+ " integer primary key autoincrement, " + COLUMN_COMMENT + " text not null);";
	
	public DataBase() {
		Gdx.app.log("DatabaseTest", "creation started");
		dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, DATABASE_CREATE, null);

		dbHandler.setupDatabase();
		try {
			dbHandler.openOrCreateDatabase();
			dbHandler.execSQL(DATABASE_CREATE);
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getComents() {
		String[] dataFromTable = null;
		
		try {
			dbHandler.execSQL("INSERT INTO comments ('comment') VALUES ('This is a test comment')");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		DatabaseCursor cursor = null;

		try {
			cursor = dbHandler.rawQuery("SELECT * FROM comments");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		String data = "";
		while (cursor.next()) {
			data += cursor.getInt(0) + " " + cursor.getString(1) + ",";
		}
		dataFromTable = data.split(",");

		try {
			cursor = dbHandler.rawQuery(cursor, "SELECT * FROM comments");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		return dataFromTable;
	}
	
	public void dispose() {
		try {
			dbHandler.closeDatabase();
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		dbHandler = null;
		Gdx.app.log("DatabaseTest", "dispose");
	}
	
}

