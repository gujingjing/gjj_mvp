package mvp.gjj.mvptestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.gjj.mvptestdemo.activity.CountriesActivity;
import mvp.gjj.mvptestdemo.activity.FragmentContainerActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.listview)
    ListView listview;

    Demo[] demos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        demos = createDemos();
        listview.setAdapter(new ArrayAdapter<Demo>(this, android.R.layout.simple_list_item_1, demos));
        listview.setOnItemClickListener(this);
    }
    public Demo[] createDemos(){
        return new Demo[] {
                new Demo("Simple LceActivity", new Intent(this, CountriesActivity.class)),
                new Demo("Simple LceFragment",
                new Intent(this, FragmentContainerActivity.class).putExtra("fragment",
                        "CountriesFragment")),
//                new Demo("RetainingViewsState LceFragment",
//                new Intent(this, FragmentContainerActivity.class).putExtra("fragment",
//                        "RetainingCountriesFragment")),
//                new Demo("Retaining by using Parcelable ViewsState LceFragment",
//                        new Intent(this, FragmentContainerActivity.class).putExtra("fragment",
//                                "NotRetainingCountriesFragment")),
//
//                new Demo("Retaining ViewsState LceActivity",
//                        new Intent(this, RetainingCountriesActivity.class)),
//                new Demo("Retaining by using Parcelable ViewsState LceActivity",
//                        new Intent(this, NotRetainingCountriesActivity.class)),
//
//                new Demo("MVP FrameLayout", new Intent(this, CountriesLayoutActivity.class)),
//
//                new Demo("Custom ViewsState Fragment",
//                        new Intent(this, FragmentContainerActivity.class).putExtra("fragment",
//                                "CustomViewStateFragment")),
//
//                new Demo("Custom ViewState Activity", new Intent(this, MyCustomActivity.class)),
//                new Demo("Nested ViewState CountriesFragment",
//                        new Intent(this, FragmentContainerActivity.class).putExtra("fragment",
//                                "NestedNotRetainingFragment")),
//                new Demo("Nested ViewState CountriesFragment ViewPager",
//                        new Intent(this, FragmentContainerActivity.class).putExtra("fragment",
//                                "NestedNotRetainingViewPagerFragment")),
//                new Demo("Retaining ViewState Fragment embededed in activities xml layout ",
//                        new Intent(this, RetainingCountriesFragmentEmbededInXmlActivity.class))
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(demos[position].intent);
    }

    public class Demo{
        public String name;
        public Intent intent;

        private Demo(String name,Intent intent){
            this.name=name;
            this.intent=intent;
        }
    public String toString(){
        return name;
    }
    }
}
