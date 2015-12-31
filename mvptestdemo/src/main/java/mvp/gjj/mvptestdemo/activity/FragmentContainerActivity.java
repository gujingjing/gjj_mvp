package mvp.gjj.mvptestdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import mvp.gjj.mvptestdemo.R;
import mvp.gjj.mvptestdemo.fragment.CountriesFragment;

/**
 * 作者：gjj on 2015/12/31 09:58
 * 邮箱：Gujj512@163.com
 */
public class FragmentContainerActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        if (savedInstanceState == null) {
            Fragment f = getFragment();
            if (f == null) {
                Toast.makeText(this, "Error: No fragment specified", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, f).commit();
            }
        }
    }
    public Fragment getFragment(){

        String fragmentName = getIntent().getStringExtra("fragment");
        if (fragmentName == null) {
            return null;
        }

        if ("CountriesFragment".equals(fragmentName)) {
            return new CountriesFragment();
        }
//
//        if ("RetainingCountriesFragment".equals(fragmentName)){
//            return new RetainingCountriesFragment();
//        }
//
//        if ("NotRetainingCountriesFragment".equals(fragmentName)){
//            return new NotRetainingCountriesFragment();
//        }
//
//        if ("CustomViewStateFragment".equals(fragmentName)){
//            return new MyCustomFragment();
//        }
//
//        if ("NestedNotRetainingFragment".equals(fragmentName)){
//            return new NestedFragment();
//        }
//
//        if ("NestedNotRetainingViewPagerFragment".equals(fragmentName)){
//            return new NestedViewPagerFragment();
//        }
//
//        getSupportActionBar().setTitle(fragmentName);
//
        return null;
    }
}
