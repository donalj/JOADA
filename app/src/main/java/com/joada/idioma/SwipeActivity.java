package com.joada.idioma;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity implements TinderCard.Callback {

    private SwipeDirectionalView mSwipeView;
    private Context mContext;
    private int mAnimationDuration = 300;
    private boolean isToUndo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_swipe);

        mSwipeView = (SwipeDirectionalView) findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        mSwipeView.getBuilder().setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_HORIZONTAL);
        TabLayout tabs = (TabLayout) findViewById(R.id.mainTabLayout);
        tabs.setTabMode(1);

        Language<String, Integer, Integer> english = new Language<>("united_kingdom", 5, 0);
        Language<String, Integer, Integer> spanish = new Language<>("spain", 1, 1);
        Language<String, Integer, Integer> french = new Language<>("france", 2, 1);
        Language<String, Integer, Integer> japanese = new Language<>("japan", 4, 1);
        Language<String, Integer, Integer> mandarin = new Language<>("china", 3, 1);
        Language<String, Integer, Integer> welsh = new Language<>("wales", 1, 1);

        List<Language<String,Integer, Integer>> languages = new ArrayList<>();

//        languages.add(french);
//        languages.add(mandarin);
//        languages.add(spanish);
        languages.add(japanese);

        languages.add(english);
        Profile omar = new Profile("Omar Reid", 22, "Bath, UK", "https://scontent-lht6-1.xx.fbcdn.net/v/t31.0-8/12238497_10200992548211551_9041574692981909813_o.jpg?_nc_cat=109&_nc_ht=scontent-lht6-1.xx&oh=15053a7daf80f7587e566b7e1df943df&oe=5CF45DA7", languages);

        List<Language<String,Integer, Integer>> rossLang = new ArrayList<>();
        rossLang.add(welsh);
        rossLang.add(english);

        Profile ross = new Profile("Ross Challinor", 22, "Bath, UK", "https://scontent-lhr3-1.xx.fbcdn.net/v/t1.0-9/523081_3842299970870_597751835_n.jpg?_nc_cat=108&_nc_ht=scontent-lhr3-1.xx&oh=3eb38850d53932636cab88e198a0f566&oe=5CF07F0F", rossLang);
        int bottomMargin = Utils.dpToPx(160);
        Point windowSize = Utils.getDisplaySize(getWindowManager());
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setSwipeAnimTime(mAnimationDuration)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));


        Point cardViewHolderSize = new Point(windowSize.x, windowSize.y - bottomMargin);

        mSwipeView.addView(new NewTinderCard(mContext, omar, cardViewHolderSize, this));
        mSwipeView.addView(new NewTinderCard(mContext, ross, cardViewHolderSize, this));

        for (Profile profile : Utils.loadProfiles(this.getApplicationContext())) {
//            mSwipeView.addView(new NewTinderCard(mContext, profile, cardViewHolderSize, this));
        }


        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        findViewById(R.id.undoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.undoLastSwipe();
            }
        });



        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if (isToUndo) {
                    isToUndo = false;
                    mSwipeView.undoLastSwipe();

                }
            }
        });

        mSwipeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
                toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200); // 200 is duration in ms
            }
        });

    }

    @Override
    public void onSwipeUp() {
        Toast.makeText(this, "SUPER LIKE! Show any dialog here.", Toast.LENGTH_SHORT).show();
        isToUndo = true;
    }

}
