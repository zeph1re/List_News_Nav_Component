package binar.ganda.list_news_nav_component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import binar.ganda.list_news_nav_component.model.ResponseUserItem
import binar.ganda.list_news_nav_component.network.ApiClient
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener{
            val name = register_name.text.toString()
            val username = register_username.text.toString()
            val password = register_password.text.toString()
            val confirmPassword = register_confirmPassword.text.toString()

            if  (name.isNotEmpty() && username.isNotEmpty() &&
                password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                    register(name,username,password)
                } else {
                    Toast.makeText(requireContext(), "Password dan Confirm Password harus sama", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Ada field yang kosong", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun register(
        name : String,
        username : String,
        password : String
    ) {
        ApiClient.INSTANCE.registerUser(name, username, password)
            .enqueue(object : Callback<ResponseUserItem> {
                override fun onResponse(
                    call: Call<ResponseUserItem>,
                    response: Response<ResponseUserItem>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                        Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUserItem>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                }
            })
    }


}