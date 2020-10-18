package com.example.mysubshop

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property name of sandwich
 * @property totalCost of sandwich with all additional toppings
 * @property sandwichCost just the cost of the sandwich
 * @property sandwichCostText the sandwich cost converted to a string
 * @property extraCost the cost of the additional toppings added to the sandwich like lettuce or tomatoes
 * @property breadType the type of bread they chose like wheat or gluten-free
 * @property toppings a list of toppings they chose for their sandwich
 */
@Parcelize
data class Sandwich(
    var name: String,
    var totalCost: String = "",
    var sandwichCost: Float = 0.0f,
    var sandwichCostText: String = "",
    var extraCost: Float = 0.0f,
    var breadType: String = "",
    var toppings: ArrayList<String>
): Parcelable
