package com.vicnuel.shop.activity

import android.content.Intent
import android.os.Bundle
import com.vicnuel.shop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var bindind: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(bindind.root)


        bindind.startBtn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }

    }
}