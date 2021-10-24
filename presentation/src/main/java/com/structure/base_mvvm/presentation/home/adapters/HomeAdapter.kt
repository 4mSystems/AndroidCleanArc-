package com.structure.base_mvvm.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.structure.base_mvvm.domain.home.models.HomeData
import com.structure.base_mvvm.presentation.R
import com.structure.base_mvvm.presentation.databinding.ItemHomeBinding
import com.structure.base_mvvm.presentation.home.viewModels.ItemHomeViewModel

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

  private val differCallback = object : DiffUtil.ItemCallback<HomeData>() {
    override fun areItemsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
      return oldItem == newItem
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    val itemViewModel = ItemHomeViewModel(data)
    holder.setViewModel(itemViewModel)

  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  override fun onViewAttachedToWindow(holder: ViewHolder) {
    super.onViewAttachedToWindow(holder)
    holder.bind()
  }

  override fun onViewDetachedFromWindow(holder: ViewHolder) {
    super.onViewDetachedFromWindow(holder)
    holder.unBind()
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private lateinit var itemLayoutBinding: ItemHomeBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setViewModel(itemViewModel: ItemHomeViewModel) {
      itemLayoutBinding.notifyItemViewModels = itemViewModel
    }
  }
}