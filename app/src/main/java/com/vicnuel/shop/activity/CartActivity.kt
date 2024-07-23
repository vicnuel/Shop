package com.vicnuel.shop.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.vicnuel.shop.Adapter.CartAdapter
import com.vicnuel.shop.Helper.ChangeNumberItemsListener
import com.vicnuel.shop.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        managmentCart= ManagmentCart(this)
        
        setVariable()
        initCartList()
        calculateCart()
    }

    private fun initCartList() {
        binding.viewCard.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewCard.adapter= CartAdapter(managmentCart.getListCart(), this,
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }

            })

        with(binding) {
            emptytxt.visibility= if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView.visibility= if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateCart () {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round(managmentCart.getTotalFee()*percentTax*100)/100.0

        val total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100) / 100.0
        val itemTotal  = Math.round(managmentCart.getTotalFee()*100) /100.0

        with(binding) {
            totalFeeTxt.text="R$ $itemTotal"
            taxTxt.text="R$ $tax"
            deliveryTxt.text = "R$ $delivery"
            totalTxt.text="R$ $total"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener{finish()}
    }
}