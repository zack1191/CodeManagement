package com.hha.codemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hha.codemanagement.R
import com.hha.codemanagement.data.entities.PopularMovie
import com.hha.codemanagement.data.vo.CastX
import com.hha.codemanagement.databinding.ItemCastBinding
import com.hha.codemanagement.databinding.ItemMoviesBinding
import com.hha.codemanagement.utils.DiffCallBack
import com.hha.codemanagement.utils.Utils
import kotlin.properties.Delegates

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>(), DiffCallBack
{
    var data : MutableList<CastX> by Delegates.observable(ArrayList()) { _, old, new ->
        compareItem(old, new) { o, n -> o.id.toString() === n.id.toString() }
    }

    inner class CastViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        private lateinit var binding : ItemCastBinding
        fun bindData(cast : CastX)
        {
            binding = ItemCastBinding.bind(itemView)
            binding.apply {

                tvCastName.text = cast.name
                Glide.with(itemView.context)
                        .load(Utils.IMG_BASE_URL + cast.profile_path)
                        .into(ivCast)

            }
        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : CastViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)

        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder : CastViewHolder, position : Int)
    {

        holder.bindData(data[position])
    }

    override fun getItemCount() : Int
    {
        return data.size
    }

}