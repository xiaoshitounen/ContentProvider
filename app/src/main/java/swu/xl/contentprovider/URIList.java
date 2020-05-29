package swu.xl.contentprovider;

public class URIList {
    public static final String CONTENT = "content://";
    public static final String AUTHORITY = "swu.xl.contentprovider";

    //调用者使用
    public static final String URI_USER = CONTENT + AUTHORITY + "/" + DatabaseHelper.USER_TABLE_NAME;
    public static final String URI_LESSON = CONTENT + AUTHORITY + "/" + DatabaseHelper.LESSON_TABLE_NAME;
}
