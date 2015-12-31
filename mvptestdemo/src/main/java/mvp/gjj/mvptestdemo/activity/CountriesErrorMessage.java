package mvp.gjj.mvptestdemo.activity;

import android.content.Context;

import mvp.gjj.mvptestdemo.R;

/**
 * 作者：gjj on 2015/12/31 10:01
 * 邮箱：Gujj512@163.com
 */
public class CountriesErrorMessage {
    public static String get(Throwable e, boolean pullToRefresh, Context c) {
        // TODO distinguish type of exception and retrun different strings
        if (pullToRefresh) {
            return c.getString(R.string.error_countries);
        } else {
            return c.getString(R.string.error_countries_retry);
        }
    }
}
