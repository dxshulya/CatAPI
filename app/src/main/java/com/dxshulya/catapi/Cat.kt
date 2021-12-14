package com.dxshulya.catapi

data class Cat(
    val breeds: List<Any>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)
data class RecyclerCat(
    val items: List<Cat>
)
