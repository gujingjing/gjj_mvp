package mvp.gjj.mvpdemo2.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.gjj.mvpdemo2.R;
import mvp.gjj.mvpdemo2.model.User;

/**
 * 作者：gjj on 2015/12/31 12:01
 * 邮箱：Gujj512@163.com
 */
public class MembersAdapter extends BaseAdapter<User> {
    Activity mContext;
    private Picasso picasso;
    private Transformation roundedTransformation;
    public MembersAdapter(Activity mContext) {
        super(mContext);
        this.mContext = mContext;
        picasso=Picasso.with(mContext);
        roundedTransformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(1)
                .oval(false).build();
    }

    @Override
    public void viewHolderShowData(BaseViewHolder viewHolde, int position, List<User> mItemDataList) {
        ViewHolder viewHolder=(ViewHolder)viewHolde;
        viewHolder.username.setText(mItemDataList.get(position).login);
        picasso.load(mItemDataList.get(position).avatar_url).fit().transform(roundedTransformation).into(viewHolder.avatar);
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getLayout() {
        return R.layout.list_member;
    }

    static class ViewHolder extends BaseViewHolder{
        @Bind(R.id.avatar)
        RoundedImageView avatar;
        @Bind(R.id.username)
        TextView username;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
