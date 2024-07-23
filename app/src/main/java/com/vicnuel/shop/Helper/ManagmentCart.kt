package com.example.project1762.Helper

import android.content.Context
import android.widget.Toast
import com.vicnuel.shop.Helper.ChangeNumberItemsListener

import com.vicnuel.shop.Helper.TinyDB
import com.vicnuel.shop.Model.ItemModel


class ManagmentCart(val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertProduct(item: ItemModel) {
        var list = getListCart()
        val existAlready = list.any { it.title == item.title }
        val index = list.indexOfFirst { it.title == item.title }

        if (existAlready) {
            list[index].numberInCart = item.numberInCart
        } else {
            list.add(item)
        }
        tinyDB.putListObject("CartList", list)
        Toast.makeText(context, "Adicionado ao seu carrinho", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<ItemModel> {
        return tinyDB.getListObject("CartList") ?: arrayListOf()
    }

    fun minusItem(listProduct: ArrayList<ItemModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (listProduct[position].numberInCart == 1) {
            listProduct.removeAt(position)
        } else {
            listProduct[position].numberInCart--
        }
        tinyDB.putListObject("CartList", listProduct)
        listener.onChanged()
    }

    fun plusItem(listProduct: ArrayList<ItemModel>, position: Int, listener: ChangeNumberItemsListener) {
        listProduct[position].numberInCart++
        tinyDB.putListObject("CartList", listProduct)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val listProduct = getListCart()

        var fee = 0.0
        for (item in listProduct) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}