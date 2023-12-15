package com.example.basemodule.entity

/**
 * create by libo
 * create on 2021/7/2
 * description
 */

data class BannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)