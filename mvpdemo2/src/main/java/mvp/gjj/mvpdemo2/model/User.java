package mvp.gjj.mvpdemo2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：gjj on 2015/12/31 11:59
 * 邮箱：Gujj512@163.com
 */
public class User implements Parcelable {
    public long id;
    public String login;
    public String avatar_url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.login);
        dest.writeString(this.avatar_url);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.login = in.readString();
        this.avatar_url = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
