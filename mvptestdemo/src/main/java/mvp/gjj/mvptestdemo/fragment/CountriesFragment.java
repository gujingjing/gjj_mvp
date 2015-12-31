package mvp.gjj.mvptestdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

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
 * 作者：gjj on 2015/12/31 10:12
 * 邮箱：Gujj512@163.com
 */
public class CountriesFragment extends MvpLceFragment<SwipeRefreshLayout, List<Country>, CountriesView, CountriesPresenter> implements CountriesView, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.loadingView)
    ProgressBar loadingView;
    @Bind(R.id.errorView)
    TextView errorView;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.contentView)
    SwipeRefreshLayout contentView;

    private CountriesAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countries_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);

        // Setup recycler view
        adapter = new CountriesAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        loadData(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public CountriesPresenter createPresenter() {
        return new SimpleCountriesPresenter();
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }
}
