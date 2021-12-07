package com.dxshulya.catapi

data class Cat(
    //val breeds: List<Any>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
){
    constructor() : this(0, "0", "0", 0) // empty constructor to add footer
}
