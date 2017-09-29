package chandrayan.param.mastfooter.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chandrayan.param.mastfooter.R;
import chandrayan.param.mastfooter.adaptor.FacebookAdapter;
import chandrayan.param.mastfooter.enums.ViewType;
import chandrayan.param.mastfooter.model.FacebookPost;
import chandrayan.param.mastfooter.recycleScroll.RecyclerViewOnScrollListener;
import chandrayan.param.mastfooter.utils.AppUtils;
import chandrayan.param.mastfooter.utils.SpacesItemDecoration;

/**
 * Created by etiennelawlor on 6/23/14.
 */
public class FacebookFragment extends Fragment {

    // region Member Variables
    private String[] mAvatarUrls;
    private String[] mDisplayNames;
    private String[] mTimestamps;
    private String[] mMessages;
    private String[] mPostImageUrls;
    private int[] mCommentCounts;
    private int[] mLikeCounts;
    private RecyclerViewOnScrollListener mScrollListener;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.quick_return_footer_ll)
    LinearLayout mQuickReturnFooterLinearLayout;

    @Bind(R.id.statusll)
    LinearLayout statusll;
    @Bind(R.id.photoll)
    LinearLayout photoll;
    @Bind(R.id.chechinll)
    LinearLayout chechinll;

    // endregion

    // region Constructors
    public FacebookFragment() {
    }
    // endregion

    // region Factory Methods
    public static FacebookFragment newInstance() {
        FacebookFragment fragment = new FacebookFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAvatarUrls = getActivity().getResources().getStringArray(R.array.avatar_urls);
        mDisplayNames = getActivity().getResources().getStringArray(R.array.display_names);
        mTimestamps = getActivity().getResources().getStringArray(R.array.facebook_timestamps);
        mMessages = getActivity().getResources().getStringArray(R.array.facebook_messages);
        mPostImageUrls = getActivity().getResources().getStringArray(R.array.facebook_post_image_urls);
        mCommentCounts = getActivity().getResources().getIntArray(R.array.facebook_comment_counts);
        mLikeCounts = getActivity().getResources().getIntArray(R.array.facebook_like_counts);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_return_facebook, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<FacebookPost> posts = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            FacebookPost post = new FacebookPost();
            post.setAvatarUrl(mAvatarUrls[i]);
            post.setDisplayName(mDisplayNames[i]);
            post.setTimestamp(mTimestamps[i]);
            post.setCommentCount(mCommentCounts[i]);
            post.setLikeCount(mLikeCounts[i]);
            post.setPostImageUrl(mPostImageUrls[i]);
            post.setMessage(mMessages[i]);
            posts.add(post);
        }

        FacebookAdapter adapter = new FacebookAdapter(posts);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(AppUtils.dp2px(getActivity(), 8)));

        int headerHeight = getResources().getDimensionPixelSize(R.dimen.facebook_header_height);
        int footerHeight = getResources().getDimensionPixelSize(R.dimen.facebook_footer_height);

        int headerTranslation = -headerHeight;
        int footerTranslation = -footerHeight;

        mScrollListener = new RecyclerViewOnScrollListener.Builder(ViewType.FOOTER)
               // .header(mQuickReturnHeaderTextView)
               // .minHeaderTranslation(headerTranslation)
                .footer(mQuickReturnFooterLinearLayout)
                .minFooterTranslation(-footerTranslation)
                .isSnappable(true)
                .build();

        mRecyclerView.addOnScrollListener(mScrollListener);




    }

    @OnClick(R.id.statusll)
    public void statusClick() {

        Toast.makeText(getActivity(), "status", Toast.LENGTH_SHORT).show();
    } @OnClick(R.id.photoll)

    public void photoClick() {

        Toast.makeText(getActivity(), "photo", Toast.LENGTH_SHORT).show();
    } @OnClick(R.id.chechinll)

    public void checkinClick() {

        Toast.makeText(getActivity(), "check in", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        removeListeners();
        ButterKnife.unbind(this);
    }
    // endregion

    // region Helper Methods
    private void removeListeners() {
        mRecyclerView.removeOnScrollListener(mScrollListener);
    }
    // endregion
}
