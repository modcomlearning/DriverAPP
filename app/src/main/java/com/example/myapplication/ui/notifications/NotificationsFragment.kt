package com.example.myapplication.ui.notifications

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.example.myapplication.models.PasswordPost
import com.example.myapplication.models.UserInfo
import com.example.myapplication.services.RestApiService
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textNotifications
        val btnChange: MaterialButton = binding.btnChange
        val progress: ProgressBar = binding.progress
        val current: TextInputEditText = binding.current
        val newpassword: TextInputEditText = binding.newpassword
        val confirmpassword: TextInputEditText = binding.confirmpassword
        btnChange.setOnClickListener {
            progress.visibility = View.VISIBLE
            val prefs = requireActivity().getSharedPreferences("storage", AppCompatActivity.MODE_PRIVATE)
            val data = prefs.getString("userData", "")
            //Convert to Json
            val details = JSONObject(data.toString())
            //get driver_id by its key
            val driver_id = details.getString("driver_id")
            val apiService = RestApiService()
            val pass = PasswordPost(
                driverId = driver_id,
                currentPassword = current.text.toString(),
                newPassword = newpassword.text.toString(),
                confirmPassword = confirmpassword.text.toString())

            apiService.changepassword(pass) {
                progress.visibility = View.GONE
                Toast.makeText(requireActivity(), "${it!!.userMsg}", Toast.LENGTH_SHORT).show()
                if(it.userMsg == "password Changed"){
                    val editor = prefs.edit()
                    editor.remove("email")
                    editor.remove("password")
                    editor.apply()
                }
            } //https://github.com/modcomlearning/driverAPP

        }//end onclick
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}