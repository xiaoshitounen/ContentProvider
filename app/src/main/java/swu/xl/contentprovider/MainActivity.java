package swu.xl.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver contentResolver = getContentResolver();

        //find(contentResolver);

        //insert(contentResolver);

        //update(contentResolver);

        //delete(contentResolver);
    }

    /**
     * 查询数据
     * @param contentResolver
     */
    public void find(ContentResolver contentResolver){
        Cursor cursor = contentResolver.query(Uri.parse(URIList.URI_USER), null, null, null, null);
    }

    /**
     * 插入数据
     * @param contentResolver
     */
    public void insert(ContentResolver contentResolver){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_COLUMN_NAME,"xl");
        contentValues.put(DatabaseHelper.USER_COLUMN_PASSWORD,"0000");
        contentResolver.insert(Uri.parse(URIList.URI_USER),contentValues);
    }

    /**
     * 更新数据
     * @param contentResolver
     */
    public void update(ContentResolver contentResolver){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_COLUMN_PASSWORD,"1234");

        contentResolver.update(Uri.parse(URIList.URI_USER),contentValues,DatabaseHelper.USER_COLUMN_PASSWORD+"= ?",new String[]{"0000"});
    }


    /**
     * 删除数据
     * @param contentResolver
     */
    public void delete(ContentResolver contentResolver){
        contentResolver.delete(Uri.parse(URIList.URI_USER),DatabaseHelper.USER_COLUMN_PASSWORD+"= ?", new String[]{"0000"});
    }
}
