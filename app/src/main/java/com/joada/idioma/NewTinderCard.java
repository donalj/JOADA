package com.joada.idioma;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeTouch;
import com.mindorks.placeholderview.annotations.swipe.SwipeView;
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by janisharali on 19/08/16.
 */
//@Layout(R.layout.language_card)
@Layout(R.layout.language_card)
public class NewTinderCard {


//    ImageView[] languageViews = new ImageView[4];

    @View(R.id.lang1Learn)
    android.view.View lang1Learn;
    @View(R.id.lang2Learn)
    android.view.View lang2Learn;
    @View(R.id.lang3Learn)
    android.view.View lang3Learn;
    @View(R.id.lang4Learn)
    android.view.View lang4Learn;

    @View(R.id.nativeLang)
    android.view.View nativeLang;


    @View(R.id.profileFlagImageView)
    ImageView theirLanguage;

    @View(R.id.nameAgeTxt)
    TextView nameAgeTxt;

    @View(R.id.profileLocation)
    TextView profileLocation;

    @View(R.id.languageInfoTxt)
    TextView languageInfoTxt;

    @View(R.id.languageTxt)
    TextView languageTxt;

    @View(R.id.languageFrame)
    FrameLayout languageFrame;

    //////////////////////////////////////////
    @View(R.id.profImageView)
    ImageView profileImageView;

    @View(R.id.profileName)
    TextView profileName;

    @View(R.id.profileFlagImageView)
    ImageView profileFlagImageView;

    @View(R.id.profileRatingBar)
    RatingBar profileRatingBar;

    @View(R.id.profileLearningLayout)
    LinearLayout profileLearningLayout;

    @SwipeView
    android.view.View mSwipeView;

    private Profile mProfile;
    private Context mContext;
    private Point mCardViewHolderSize;
    private TinderCard.Callback mCallback;

    public NewTinderCard(Context context, Profile profile, Point cardViewHolderSize, TinderCard.Callback callback) {
        mContext = context;
        mProfile = profile;
        mCardViewHolderSize = cardViewHolderSize;
        mCallback = callback;


    }

    @Resolve
    public void onResolved() {
        ArrayList<View> langArray;
        langArray = new ArrayList<View>();
        langArray.add(lang1Learn);
        langArray.add(lang2Learn);
        langArray.add(lang3Learn);
        langArray.add(lang4Learn);
        List<Language<String, Integer, Integer>> languages = mProfile.getLanguages();

        Glide.with(mContext).load(mProfile.getImageUrl())
                .bitmapTransform(new RoundedCornersTransformation(mContext, Utils.dpToPx(7), 0,
                        RoundedCornersTransformation.CornerType.TOP)).into(profileImageView);

//        profileFlagImageView.setImageResource(R.drawable.united_kingdom);

        ImageView nativeFlag = nativeLang.findViewById(R.id.langImage);
        TextView nativeLevel = nativeLang.findViewById(R.id.langText);

        Language<String, Integer, Integer> nat =  languages.get(languages.size()-1);
        String level = nat.getL().toString();


        int id  = mContext.getResources().getIdentifier(level, "drawable", mContext.getPackageName());
        Drawable pic = mContext.getResources().getDrawable(id);
        nativeFlag.setImageDrawable(pic);
        nativeLevel.setText(mProfile.getLevel(nat));


        profileName.setText(mProfile.getName() + ", " + mProfile.getAge());
        profileLocation.setText(mProfile.getLocation());
        profileRatingBar.setNumStars(5);
        profileRatingBar.isIndicator();


        int numLangs = languages.size()-1;
        if (numLangs>4) numLangs = 4;
        for (int i=0; i<numLangs; i++){
            Language<String, Integer, Integer> current = languages.get(i);
            if (current.getS().toString().equals("1")) {
                String n = current.getL().toString();
                id  = mContext.getResources().getIdentifier(n, "drawable", mContext.getPackageName());
                pic = mContext.getResources().getDrawable(id);
                android.view.View temp = langArray.get(i);
                TextView text = temp.findViewById(R.id.langText);
                ImageView image= temp.findViewById(R.id.langImage);

                text.setText(mProfile.getLevel(current));
                text.setTextSize(10);
                image.setImageDrawable(pic);
            }
        }


        mSwipeView.setAlpha(1);
    }



    @SwipeOutDirectional
    public void onSwipeOutDirectional(SwipeDirection direction) {
        Log.d("DEBUG", "SwipeOutDirectional " + direction.name());
        if (direction.getDirection() == SwipeDirection.TOP.getDirection()) {
            mCallback.onSwipeUp();
        }
    }

    @SwipeCancelState
    public void onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState");
        mSwipeView.setAlpha(1);
    }

    @SwipeInDirectional
    public void onSwipeInDirectional(SwipeDirection direction) {
        Log.d("DEBUG", "SwipeInDirectional " + direction.name());
    }

    @SwipingDirection
    public void onSwipingDirection(SwipeDirection direction) {
        Log.d("DEBUG", "SwipingDirection " + direction.name());
    }

    @SwipeTouch
    public void onSwipeTouch(float xStart, float yStart, float xCurrent, float yCurrent) {

        float cardHolderDiagonalLength =
                (float) Math.sqrt(Math.pow(mCardViewHolderSize.x, 2) + (Math.pow(mCardViewHolderSize.y, 2)));
        float distance = (float) Math.sqrt(Math.pow(xCurrent - xStart, 2) + (Math.pow(yCurrent - yStart, 2)));

        float alpha = 1 - distance / cardHolderDiagonalLength;

        Log.d("DEBUG", "onSwipeTouch "
                + " xStart : " + xStart
                + " yStart : " + yStart
                + " xCurrent : " + xCurrent
                + " yCurrent : " + yCurrent
                + " distance : " + distance
                + " TotalLength : " + cardHolderDiagonalLength
                + " alpha : " + alpha
        );

        ((FrameLayout)mSwipeView).setAlpha(alpha);
    }

    interface Callback {
        void onSwipeUp();
    }

}
