package mvp.gjj.mvptestdemo.present;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import mvp.gjj.mvptestdemo.model.Country;
import mvp.gjj.mvptestdemo.view.CountriesView;

/**
 * 作者：gjj on 2015/12/30 16:15
 * 邮箱：Gujj512@163.com
 */
public class SimpleCountriesPresenter extends MvpBasePresenter<CountriesView> implements CountriesPresenter{
    private static final String TAG = "CountriesPresenter";
    private List<Country>list=new ArrayList<>();
    public SimpleCountriesPresenter() {
        Log.d(TAG, "constructor " + toString());
    }
    @Override
    public void loadCountries(boolean pullToRefresh) {
        Log.d(TAG, "loadCountries(" + pullToRefresh + ")");

        Log.d(TAG, "showLoading(" + pullToRefresh + ")");
        getView().showLoading(pullToRefresh);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<30;i++){
                    list.add(new Country("第"+i+"个country"));
                }
                getView().setData(list);
                getView().showContent();
            }
        }, 5000);
    }
}
