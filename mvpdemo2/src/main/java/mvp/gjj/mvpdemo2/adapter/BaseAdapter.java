package mvp.gjj.mvpdemo2.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：lg on 2015-10-31 17:03
 * 邮箱：support@1yd.me
 */
public abstract class BaseAdapter<ItemDataType> extends
        RecyclerView.Adapter<BaseViewHolder> {
    protected List<ItemDataType> mItemDataList = new ArrayList<ItemDataType>();
    public Activity mContext;
    protected int layoutId;
    protected  long lastClickTime=(long)0;
    protected  int clickTime=1000;

    public BaseAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public BaseAdapter(List<ItemDataType> mItemDataList, Activity mContext) {
        this.mContext = mContext;
        this.mItemDataList = mItemDataList;
    }
    //设置两次点击事件响应间隔时间
    public void setClickTime(int clickTime) {
        this.clickTime = clickTime;
    }
    /**
     * 动态增加一条数据
     *
     * @param itemDataType 数据实体类对象
     */
    public void append(ItemDataType itemDataType) {
        if (itemDataType != null) {
            mItemDataList.add(itemDataType);
            notifyDataSetChanged();
        }
    }

    /**
     * 动态增加一组数据集合
     *
     * @param itemDataTypes 数据实体类集合
     */
    public void append(List<ItemDataType> itemDataTypes) {
        if (itemDataTypes.size() > 0) {
            for (ItemDataType itemDataType : itemDataTypes) {
                mItemDataList.add(itemDataType);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 获取所有的数据
     */
    public List<ItemDataType> getData(){
        return mItemDataList;
    }
    /**
     * 替换全部数据
     *
     * @param itemDataTypes 数据实体类集合
     */
    public void replace(List<ItemDataType> itemDataTypes) {
        mItemDataList.clear();
        if (itemDataTypes.size() > 0) {
            mItemDataList.addAll(itemDataTypes);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除一条数据集合
     *
     * @param position
     */
    public void remove(int position) {
        mItemDataList.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 移除所有数据
     */
    public void removeAll() {
        mItemDataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItemDataList.size();
    }
    @Override
    public void onBindViewHolder( BaseViewHolder viewHolder,final  int position) {
        viewHolderShowData(viewHolder, position, mItemDataList);
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClcikListener) {
                    long cl=System.currentTimeMillis();
                    if(cl-lastClickTime>clickTime){
                        onItemClcikListener.onClick(v, position);
                        lastClickTime=clickTime;
                    }
                }
            }
        });
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        layoutId = getLayout();
        View v = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        //View v = View.inflate(mContext, layoutId, null);
        return createViewHolder (v);
    }


    /**
     * 显示数据抽象函数
     *
     * @param viewHolder    基类ViewHolder,需要向下转型为对应的ViewHolder（example:MainRecyclerViewHolder mainRecyclerViewHolder=(MainRecyclerViewHolder) viewHolder;）
     * @param position             位置
     * @param mItemDataList 数据集合
     */
    public abstract void viewHolderShowData(BaseViewHolder viewHolder, int position, List<ItemDataType> mItemDataList);

    /**
     * 加载一个ViewHolder,为RecyclerViewHolderBase子类,直接返回子类的对象即可
     *
     * @param view item 的view
     * @return RecyclerViewHolderBase 基类ViewHolder
     */
    public abstract BaseViewHolder createViewHolder(View view);

    /**
     * 设置布局文件
     *
     * @return
     */
    public abstract int getLayout();

    OnItemClickListener onItemClcikListener;

    public void setOnItemClickListener(OnItemClickListener onItemClcikListener) {
        this.onItemClcikListener = onItemClcikListener;
    }
}
