package com.v2v.xpensa;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        background = findViewById(R.id.moving_background);

        // Animate background to move slowly for dynamic effect
        ObjectAnimator animator = ObjectAnimator.ofFloat(background, "translationX", 0f, -200f);
        animator.setDuration(6000); // 6 seconds per move
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();

        // Navigate to next activity after 3 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, SignUpActivity.class); // Change this if needed
            startActivity(intent);
            finish(); // So the user can’t return to splash screen
        }, 3000); // 3-second splash
    }
}
