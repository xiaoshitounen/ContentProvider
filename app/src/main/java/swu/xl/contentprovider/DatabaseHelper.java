package swu.xl.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DATABASE_NAME = "xl.db";
    //数据库版本
    public static final int DATABASE_VERSION = 1;
    //user表
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_NAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";
    //lesson表
    public static final String LESSON_TABLE_NAME = "lesson";
    public static final String LESSON_COLUMN_NAME = "lesson_name";
    public static final String LESSON_COLUMN_COUNT = "lesson_count";

    /**
     * 构造方法
     * @param context
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user表
        db.execSQL("create table " + USER_TABLE_NAME + "(" + USER_COLUMN_NAME + " varchar(20) not null, " + USER_COLUMN_PASSWORD + " varchar(20) not null);");
        //创建lesson表
        db.execSQL("create table " + LESSON_TABLE_NAME + "(" + LESSON_COLUMN_NAME + " varchar(20) not null, " + LESSON_COLUMN_COUNT + " varchar(20) not null);");
    }

    /**
     * 更新数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库更新操作 oldVersion > newVersion 的时候会调用这个方法
    }
}
