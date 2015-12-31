package mvp.gjj.mvptestdemo.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.gjj.mvptestdemo.R;
import mvp.gjj.mvptestdemo.base.recyleBase.BaseAdapter;
import mvp.gjj.mvptestdemo.base.recyleBase.BaseViewHolder;
import mvp.gjj.mvptestdemo.model.Country;

/**
 * 作者：gjj on 2015/12/30 17:17
 * 邮箱：Gujj512@163.com
 */
public class CountriesAdapter extends BaseAdapter<Country> {
    Activity mContext;
    public CountriesAdapter(Activity mContext) {
        super( mContext);
        this.mContext = mContext;
    }
    public CountriesAdapter(List<Country> mItemDataList, Activity mContext) {
        super(mItemDataList, mContext);
        this.mItemDataList = mItemDataList;
        this.mContext = mContext;
    }

    @Override
    public void viewHolderShowData(BaseViewHolder viewHolde, int position, List<Country> mItemDataList) {
        CountryViewHolder viewHolder=(CountryViewHolder)viewHolde;
        viewHolder.textView.setText(mItemDataList.get(position).name);
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new CountryViewHolder(view);
    }

    @Override
    public int getLayout() {
        return R.layout.row_text;
    }

     class CountryViewHolder extends BaseViewHolder{
        @Bind(R.id.textView)
        TextView textView;
         CountryViewHolder(View view) {
             super(view);
             ButterKnife.bind(this, view);
        }
    }
}
