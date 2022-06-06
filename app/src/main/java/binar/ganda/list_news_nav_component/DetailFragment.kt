package binar.ganda.list_news_nav_component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import binar.ganda.list_news_nav_component.model.ResponsNewsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetail()
    }

    private fun getDetail() {
        val details = arguments?.getParcelable<ResponsNewsItem>("data_news")
        val judul = details?.title
        val releaseDate = details?.createdAt
        val penulis = details?.author
        val image = details?.image
        val description = details?.description


        title_news_detail.text = judul
        release_news_detail.text = releaseDate
        author_news_detail.text = penulis
        description_news_detail.text = description

        Glide.with(requireContext()).load(image).into(image_news_detail)
    }
}