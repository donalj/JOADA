package com.joada.idioma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.View;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ProfileActivity extends AppCompatActivity {
    @View(R.id.profileImageView)
    ImageView profileImageView;

    @View(R.id.languageImageView)
    ImageView theirLanguage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Profile profile = getIntent().getExtras().getParcelable("profile");

        Glide.with(getApplicationContext()).load(profile.getImageUrl())
                .bitmapTransform(new RoundedCornersTransformation(getApplicationContext(), Utils.dpToPx(7), 0,
                        RoundedCornersTransformation.CornerType.TOP)).into(profileImageView);
        theirLanguage.setImageResource(R.drawable.united_kingdom);



    }

}
