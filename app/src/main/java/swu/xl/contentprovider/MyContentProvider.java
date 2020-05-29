package swu.xl.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher;
    public static final int URI_MATCH_USER = 1;
    public static final int URI_MATCH_LESSON = 2;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //content://swu.xl.contentprovider/user    --- 1
        //content://swu.xl.contentprovider/lesson  --- 2
        uriMatcher.addURI(URIList.AUTHORITY,DatabaseHelper.USER_TABLE_NAME,URI_MATCH_USER);
        uriMatcher.addURI(URIList.AUTHORITY,DatabaseHelper.LESSON_TABLE_NAME,URI_MATCH_LESSON);
    }

    private SQLiteDatabase database;


    @Override
    public boolean onCreate() {

        //创建数据库
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        database = databaseHelper.getWritableDatabase();

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return null;
        }

        Cursor cursor = database.query(tableName,projection,selection,selectionArgs,null,null,sortOrder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return null;
        }

        long id = database.insert(tableName, null, values);

        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return 0;
        }

        int count = database.delete(tableName, selection, selectionArgs);

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return 0;
        }

        int count = database.update(tableName, values, selection, selectionArgs);

        return count;
    }

    private String getTableName(Uri uri){
        System.out.println(uri.toString());

        //解析uri对应的code
        int type = uriMatcher.match(uri);

        //存储表名
        String table_name;

        //根据不同的code返回不同的表名
        switch (type){
            case URI_MATCH_USER:
                table_name = DatabaseHelper.USER_TABLE_NAME;
                break;

            case URI_MATCH_LESSON:
                table_name = DatabaseHelper.LESSON_TABLE_NAME;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        return table_name;
    }

}
