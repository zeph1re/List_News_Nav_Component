package binar.ganda.list_news_nav_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import binar.ganda.list_news_nav_component.adapter.NewsAdapter
import binar.ganda.list_news_nav_component.model.ResponsNewsItem
import binar.ganda.list_news_nav_component.network.ApiClient
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var newsAdapter : NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNews()
    }

        private fun getNews() {
        ApiClient.INSTANCE.getAllNews()
            .enqueue(object : Callback<List<ResponsNewsItem>>{
                override fun onResponse(
                    call: Call<List<ResponsNewsItem>>,
                    response: Response<List<ResponsNewsItem>>
                ) {
                    if (response.isSuccessful){
                        rv_news.layoutManager = GridLayoutManager(requireContext(), 2)
                        newsAdapter = response.body()?.let { it ->
                            NewsAdapter(it) {
                                val detailNews = bundleOf("data_news" to it)
                                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_detailFragment, detailNews)
                                Toast.makeText(requireContext(), "Menuju ke Detail", Toast.LENGTH_SHORT).show()
                            }
                        }!!
                        rv_news.adapter = newsAdapter
                        Toast.makeText(requireContext(),"Menampilkan Data News", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(),"Gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponsNewsItem>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
}

