package mvp.gjj.typedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * ----------------定义带类型参数的类-----------
     * 在紧跟类命之后的<>内,指定一个或多个类型参数的名字，同时也可以对类型参数的取
     值范围进行限定，多个类型参数之间用,号分隔
     */
    public class Type1<T>{}
    public class Type2<T,S extends View>{}//第二个人参数为继承view的任意参数

    /**
     * --------------------------------定义待类型参数方法---------------------------------------
     * 在定义带类型参数的方法时，在紧跟可见范围修饰（例如public）之后的<>内,指定一个或多个类型参数的名字，
     同时也可以对类型参数的取值范围进行限定，多个类型参数之间用,号分隔。
     */
    public <T,S extends T> void type3(T t,S s){}
    public <T> void type4(List<T> ts){}
    public <T>void type5(List<?>ss){}//?表示list内的item可以是任意的类型
    /**
     * 通配符
     * T  有类型
     ?  未知类型
     */
    public List<? extends View>list1;

    /**
     * --------------------------------------数组范型-------------------------------------
     * 可以使用带范型参数值的类声明数组，却不以有创建数组
     */
    List<Integer>[] iListArray;
    //    new ArrayList<Integer>[10];//编译时错误

}
