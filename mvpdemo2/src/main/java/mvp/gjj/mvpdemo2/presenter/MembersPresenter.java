package mvp.gjj.mvpdemo2.presenter;


import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import mvp.gjj.mvpdemo2.model.User;
import mvp.gjj.mvpdemo2.services.GithubApi;
import mvp.gjj.mvpdemo2.services.NetworkException;
import mvp.gjj.mvpdemo2.view.MembersView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * 作者：gjj on 2015/12/31 14:22
 * 邮箱：Gujj512@163.com
 */
public class MembersPresenter extends MvpNullObjectBasePresenter<MembersView> {
    private GithubApi githubApi;
    Activity mComtent;
    public MembersPresenter(Activity mComtent){
        this.mComtent=mComtent;

        OkHttpClient client = new OkHttpClient();
        client.setCache(new Cache(mComtent.getCacheDir(), 10 * 1024 * 1024));
        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(client))
                .setEndpoint("https://api.github.com")
                .build();
        githubApi= restAdapter.create(GithubApi.class);
    }

    /**
     * 网络加载数据的方法
     */
    public void loadSquareMembers(final boolean pullToRefresh){
        //先显示loading
        getView().showLoading(pullToRefresh);
        //网络请求
        githubApi.getMembers("square", new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Log.e("users====",users.size()+"------");
                Toast.makeText(mComtent,users.size()+"",Toast.LENGTH_SHORT).show();
                getView().setData(users);
                getView().showContent();
            }

            @Override
            public void failure(RetrofitError error) {
                // needs no isViewAttached() check since it's a NullObjectBasePresenter --> see javadoc
                Throwable throwable;
                if (error.getKind() == RetrofitError.Kind.NETWORK) {
                    throwable = new NetworkException();
                } else {
                    throwable = error.getCause();
                }
                getView().showError(throwable, pullToRefresh);
            }
        });
    }
}
