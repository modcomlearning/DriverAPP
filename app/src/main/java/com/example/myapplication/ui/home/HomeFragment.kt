package com.example.myapplication.ui.home

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.RecyclerAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.models.Assignments
import com.example.myapplication.models.DriverPost
import com.example.myapplication.services.RestApiService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var productList:ArrayList<Assignments>
    lateinit var recyclerAdapter: RecyclerAdapter //call the adapter
    lateinit var progressbar: ProgressBar
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //val textView: TextView = binding.textHome
        progressbar = binding.progressbar
        recyclerView = binding.recycler
        recyclerAdapter = RecyclerAdapter(requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)

        //we convert json array to a list of a given model
        val prefs = requireActivity().getSharedPreferences("storage", AppCompatActivity.MODE_PRIVATE)
        val data = prefs.getString("userData", "")
        //Convert to Json
        val details = JSONObject(data.toString())
        //get driver_id by its key
        val driver_id = details.getString("driver_id")
        val apiService = RestApiService()
        //pass the driver id below
        val driver = DriverPost(driverId = driver_id)

        apiService.getAssignments(driver){
            if(it!!.userData==null){
                Toast.makeText(requireActivity(), it.userMsg, Toast.LENGTH_SHORT).show()
                progressbar.visibility = View.GONE
                noassign.visibility = View.VISIBLE
            }
            else {
                noassign.visibility = View.GONE
                val gson = GsonBuilder().create()
                val list = gson.fromJson(
                    it.userData,
                    Array<Assignments>::class.java
                ).toList()
                //now pass the converted list to adapter
                recyclerAdapter.setProductListItems(list)
                progressbar.visibility = View.GONE
                println("resppp " + it.userData)
                recyclerView.adapter = recyclerAdapter
            }

        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}