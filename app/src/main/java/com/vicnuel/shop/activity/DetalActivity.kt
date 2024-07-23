package com.vicnuel.shop.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.vicnuel.shop.Adapter.ColorAdapter
import com.vicnuel.shop.Adapter.SizeAdapter
import com.vicnuel.shop.Adapter.SliderAdapter
import com.vicnuel.shop.Model.ItemModel
import com.vicnuel.shop.Model.SlideModel
import com.vicnuel.shop.databinding.ActivityDetalBinding

class DetalActivity : BaseActivity() {
    private lateinit var binding: ActivityDetalBinding
    private lateinit var item: ItemModel
    private val numberOder = 1
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()
        banners()
        initLists()
    }

    private fun initLists() {
        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }


        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun banners() {
        val sliderItems = ArrayList<SlideModel>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(SlideModel(imageUrl))
        }

        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)

        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 1

        if (sliderItems.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "R$ ${item.price}"
        binding.ratingTxt.text = "${item.rating} Estrelas"

        binding.cardBtn.setOnClickListener {
            startActivity(Intent(this@DetalActivity, CartActivity::class.java))
        }

        binding.backBtn.setOnClickListener { finish() }
        binding.buyNowBtn.setOnClickListener {
            item.numberInCart = numberOder
            managmentCart.insertProduct(item)
        }
    }
}