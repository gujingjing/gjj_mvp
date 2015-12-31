package mvp.gjj.mvpdemo2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.CastedArrayListLceViewState;

import java.util.List;

import mvp.gjj.mvpdemo2.adapter.MembersAdapter;
import mvp.gjj.mvpdemo2.model.User;
import mvp.gjj.mvpdemo2.presenter.MembersPresenter;
import mvp.gjj.mvpdemo2.view.MembersView;

public class MainActivity extends MvpLceViewStateActivity<SwipeRefreshLayout,List<User>,MembersView,MembersPresenter>
        implements MembersView,SwipeRefreshLayout.OnRefreshListener {

    @butterknife.Bind(R.id.loadingView)
    ProgressBar loadingView;
    @butterknife.Bind(R.id.errorView)
    TextView errorView;
    @butterknife.Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @butterknife.Bind(R.id.contentView)
    SwipeRefreshLayout contentView;

    private MembersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        butterknife.ButterKnife.bind(this);

        adapter=new MembersAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contentView.setOnRefreshListener(this);
    }

    @NonNull
    @Override
    public MembersPresenter createPresenter() {
        return new MembersPresenter(this);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public LceViewState<List<User>, MembersView> createViewState() {
        return new CastedArrayListLceViewState<>();
    }

    @Override
    public List<User> getData() {
        return adapter.getData();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        Toast.makeText(this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public void setData(List<User> data) {
        adapter.replace(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadSquareMembers(pullToRefresh);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
            contentView.post(new Runnable() {
                @Override public void run() {
                    contentView.setRefreshing(true);
                }
            });
        }
    }
}
