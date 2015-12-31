package mvp.gjj.mvptestdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.gjj.mvptestdemo.R;
import mvp.gjj.mvptestdemo.adapter.CountriesAdapter;
import mvp.gjj.mvptestdemo.model.Country;
import mvp.gjj.mvptestdemo.present.CountriesPresenter;
import mvp.gjj.mvptestdemo.present.SimpleCountriesPresenter;
import mvp.gjj.mvptestdemo.view.CountriesView;

/**
 * 作者：gjj on 2015/12/30 14:38
 * 邮箱：Gujj512@163.com
 */
public class CountriesActivity extends MvpLceActivity<SwipeRefreshLayout,List<Country>,CountriesView,CountriesPresenter>
        implements SwipeRefreshLayout.OnRefreshListener,CountriesView {
    @Bind(R.id.loadingView)
    ProgressBar loadingView;
    @Bind(R.id.errorView)
    TextView errorView;
    @Bind(R.id.recyclerView)
    android.support.v7.widget.RecyclerView recyclerView;
    @Bind(R.id.contentView)
    SwipeRefreshLayout contentView;

    private CountriesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_list);
        ButterKnife.bind(this);

        contentView.setOnRefreshListener(this);
        adapter=new CountriesAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loadData(false);
    }

    @NonNull
    @Override
    public CountriesPresenter createPresenter() {
        return new SimpleCountriesPresenter();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }
    @Override
    public void setData(List<Country> data) {
        adapter.replace(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadCountries(pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);//设置刷寻状态还原
    }
}
