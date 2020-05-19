package com.rsupport.bucketlist.core.constants;

public final class ColumnEncryptionConstants {

    private static final String SECRET_KEY = "VN4A297LLXDHLN7G";
    private static final String DEC_COLUMN_PREFIX = "CAST(AES_DECRYPT(UNHEX(";
    private static final String DEC_COLUMN_SUFFIX = "), '" + SECRET_KEY + "') AS CHAR(1250))";
    public static final String ENC_COLUMN = "HEX(AES_ENCRYPT(?, '" + SECRET_KEY + "'))";

    public static final String DEC_BUCKETLIST_TITLE = DEC_COLUMN_PREFIX + "title" + DEC_COLUMN_SUFFIX;
    public static final String DEC_BUCKETLIST_MEMO = DEC_COLUMN_PREFIX + "memo" + DEC_COLUMN_SUFFIX;
    public static final String DEC_BUCKETLIST_IMG_URL_1 = DEC_COLUMN_PREFIX + "img_url_1" + DEC_COLUMN_SUFFIX;
    public static final String DEC_BUCKETLIST_IMG_URL_2 = DEC_COLUMN_PREFIX + "img_url_2" + DEC_COLUMN_SUFFIX;
    public static final String DEC_BUCKETLIST_IMG_URL_3 = DEC_COLUMN_PREFIX + "img_url_3" + DEC_COLUMN_SUFFIX;

    public static final String DEC_CATEGORY_NAME = DEC_COLUMN_PREFIX + "name" + DEC_COLUMN_SUFFIX;

    public static final String DEC_USER_EMAIL = DEC_COLUMN_PREFIX + "email" + DEC_COLUMN_SUFFIX;
    public static final String DEC_USER_NAME = DEC_COLUMN_PREFIX + "name" + DEC_COLUMN_SUFFIX;
    public static final String DEC_USER_IMG_URL = DEC_COLUMN_PREFIX + "img_url" + DEC_COLUMN_SUFFIX;

}
