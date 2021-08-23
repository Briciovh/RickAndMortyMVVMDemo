package com.example.rickandmortydemo.ui

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.rickandmortydemo.R
import com.example.rickandmortydemo.model.rickmorty.Results

@BindingAdapter("setSpeciesAndStatus")
fun TextView.setSpeciesAndStatus(item: Results?) {
    item?.let {
        text = context.getString(R.string.joined_properties, item.species, item.status)
    }
}

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url:String?){
    url?.let {
        Glide.with(context).load(url).into(this)
    }
}

@BindingAdapter("genderAndSpecies")
fun TextView.genderAndSpecies(item: Results?){
    item?.let {
        text = context.getString(R.string.gender_species, item.gender, item.species)
    }
}

@BindingAdapter("origin")
fun TextView.origin(item: Results?){
    item?.let {
        text = context.getString(R.string.origin, item.origin.name)
    }
}

@BindingAdapter("statusAndLocation")
fun TextView.statusAndLocation(item: Results?){
    item?.let {
        text = context.getString(R.string.status_location, item.status, item.location.name)
    }
}

@BindingAdapter("toggleVisibility")
fun View.toggleVisibility(show: Boolean){
    visibility = if (show) VISIBLE else GONE
}