package mvp.gjj.mvptestdemo.present;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import mvp.gjj.mvptestdemo.view.CountriesView;

/**
 * 作者：gjj on 2015/12/30 16:06
 * 邮箱：Gujj512@163.com
 */
public interface CountriesPresenter extends MvpPresenter<CountriesView>{
    public void loadCountries(final boolean pullToRefresh);
}
