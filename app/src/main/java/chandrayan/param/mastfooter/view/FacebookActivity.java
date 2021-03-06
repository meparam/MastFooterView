package chandrayan.param.mastfooter.view;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;
import chandrayan.param.mastfooter.R;
import chandrayan.param.mastfooter.interfaces.PageslideInterface;
import chandrayan.param.mastfooter.utils.AppUtils;


public class FacebookActivity extends AppCompatActivity implements ActionBar.TabListener, PageslideInterface {

    // region Member Variables
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private LinearLayout mTabsLinearLayout;

    @Bind(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @Bind(R.id.pager)
    ViewPager mViewPager;
    // endregion

    // region Listeners
    private ViewPager.OnPageChangeListener mTabsOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mTabsLinearLayout.getChildCount(); i++) {
                ImageButton ib = (ImageButton) mTabsLinearLayout.getChildAt(i);
                switch (i) {
                    case 0:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_news_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_news);
                        break;
                    case 1:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_users_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_users);
                        break;
                    case 2:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_messages_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_messages);
                        break;
                    case 3:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_notifications_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_notifications);
                        break;
                    case 4:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_more_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_more);
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    // endregion

    // region Lifecycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_return_facebook);
        ButterKnife.bind(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabs.setAllCaps(false);
        mTabs.setShouldExpand(true);
        mTabs.setIndicatorColorResource(R.color.dark_slate_blue);
        mTabs.setIndicatorHeight(AppUtils.dp2px(this, 4));
        mTabs.setDividerColor(getResources().getColor(android.R.color.transparent));
        mTabs.setUnderlineHeight(AppUtils.dp2px(this, 0));

        mTabs.setOnPageChangeListener(mTabsOnPageChangeListener);

        mTabs.setViewPager(mViewPager);


        // Set first tab selected
        mTabsLinearLayout = ((LinearLayout) mTabs.getChildAt(0));
        ImageButton ib = (ImageButton) mTabsLinearLayout.getChildAt(0);
        ib.setImageResource(R.drawable.ic_action_news_highlighted);

    }
    // endregion

    // region ActionBar.TabListener Methods
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
    // endregion

    // region PageslideInterface Methods
    @Override
    public PagerSlidingTabStrip getTabs() {
        return mTabs;
    }
    // endregion

    // region Inner Classes

    /**
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

        // region Constructors



        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // endregion

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FacebookFragment.newInstance();
                case 1:
                    return FacebookFragment.newInstance();
                case 2:
                    return FacebookFragment.newInstance();
                case 3:
                    return FacebookFragment.newInstance();
                case 4:
                    return FacebookFragment.newInstance();
                default:
                    return FacebookFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public int getPageIconResId(int position) {
            switch (position) {
                case 0:
                    return R.drawable.ic_action_news;
                case 1:
                    return R.drawable.ic_action_users;
                case 2:
                    return R.drawable.ic_action_messages;
                case 3:
                    return R.drawable.ic_action_notifications;
                case 4:
                    return R.drawable.ic_action_more;
            }

            return 0;
        }

    }

    // endregion

}
