package com.vicnuel.shop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vicnuel.shop.Model.BrandModel
import com.vicnuel.shop.Model.ItemModel
import com.vicnuel.shop.Model.SlideModel

class MainViewModel():ViewModel() {

    private val firebaseDatabase= FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SlideModel>>()
    private val _brand = MutableLiveData<MutableList<BrandModel>>()
    private val _popular = MutableLiveData<MutableList<ItemModel>>()

    val banners: LiveData<List<SlideModel>> = _banner
    val brands: LiveData<MutableList<BrandModel>> = _brand
    val popular: LiveData<MutableList<ItemModel>> = _popular

    fun loadBanners() {
        val reference = firebaseDatabase.getReference("Banner")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SlideModel> ()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SlideModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadBrand() {
        val reference = firebaseDatabase.getReference("Category")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel> ()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadPopular() {
            val reference = firebaseDatabase.getReference("Items")
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = mutableListOf<ItemModel> ()
                    for (childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(ItemModel::class.java)
                        if (list != null) {
                            lists.add(list)
                        }
                    }
                    _popular.value = lists
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }


}