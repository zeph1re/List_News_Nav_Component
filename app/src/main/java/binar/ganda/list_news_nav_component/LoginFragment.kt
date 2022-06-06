package binar.ganda.list_news_nav_component

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import binar.ganda.list_news_nav_component.model.ResponseUserItem
import binar.ganda.list_news_nav_component.network.ApiClient
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response


class LoginFragment : Fragment() {

    lateinit var sharedPreferences : SharedPreferences
    private var isRemembered = false
    lateinit var listUser : List<ResponseUserItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)

        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if (isRemembered){
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
        }

        btnLogin.setOnClickListener{
            if  (loginUsername.text.isNotEmpty() && loginPassword.text.isNotEmpty()){
                val username = loginUsername.text.toString()
                val password = loginPassword.text.toString()
                login(username, password)
            } else {
                Toast.makeText(requireContext(),"Field Email dan Password harus diisi", Toast.LENGTH_LONG).show()
            }
        }

        daftar.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment)
        }


    }

    fun login(username : String, password : String){
        ApiClient.INSTANCE.getAllUser(username)
            .enqueue(object : retrofit2.Callback<List<ResponseUserItem>>{
                override fun onResponse(
                    call: Call<List<ResponseUserItem>>,
                    response: Response<List<ResponseUserItem>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()?.isEmpty() == true){
                            Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
                        } else {
                            when {
                                response.body()?.size!! > 1 -> {
                                    Toast.makeText(requireContext(), "Mohon Masukkan Data", Toast.LENGTH_SHORT).show()
                                }
                                username!= response.body()!![0].username   -> {
                                    Toast.makeText(requireContext(), "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
                                }
                                password!= response.body()!![0].password   -> {
                                    Toast.makeText(requireContext(), "Password tidak terdaftar", Toast.LENGTH_SHORT).show()
                                }
                                else -> {
                                    response.body()?.let { it ->
                                        detailUser(it)
                                    }!!
                                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<ResponseUserItem>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun detailUser(listLogin : List<ResponseUserItem>){
        for (i in listLogin.indices){
            val checked = checkbox.isChecked
            val editor = sharedPreferences.edit()
            editor.putString("NAME", listLogin[i].name)
            editor.putString("ADDRESS", listLogin[i].address)
            editor.putString("UMUR", listLogin[i].umur.toString())
            editor.putString("IMAGE", listLogin[i].umur.toString())
            editor.putString("ID", listLogin[i].id)
            editor.putString("USERNAME", listLogin[i].username)
            editor.putString("PASSWORD", listLogin[i].password)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()
        }
    }
}