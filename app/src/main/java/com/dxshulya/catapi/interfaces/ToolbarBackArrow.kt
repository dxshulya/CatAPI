package com.dxshulya.catapi.interfaces

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

interface ToolbarBackArrow {
    fun getActivity(): Activity?
    fun setHasOptionsMenu(b: Boolean)

    fun enableToolBar() {
        (this.getActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.setHasOptionsMenu(true)
    }

    fun disableToolBar() {
        (this.getActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        this.setHasOptionsMenu(false)
    }
}