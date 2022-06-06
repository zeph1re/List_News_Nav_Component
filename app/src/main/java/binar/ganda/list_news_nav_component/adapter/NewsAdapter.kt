package binar.ganda.list_news_nav_component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.ganda.list_news_nav_component.R
import binar.ganda.list_news_nav_component.model.ResponsNewsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class NewsAdapter(private var dataNews : List<ResponsNewsItem>, private val onClick: (ResponsNewsItem) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_judul.text = dataNews[position].title
        holder.itemView.tv_tanggal_rilis.text = dataNews[position].createdAt
        holder.itemView.tv_penulis.text = dataNews[position].author

        Glide.with(holder.itemView.context).load(dataNews[position].image).into(holder.itemView.image_news)

        holder.itemView.cardNews.setOnClickListener {
            onClick(dataNews[position])
        }
    }

    override fun getItemCount(): Int {
        return dataNews.size
    }
}