package com.example.myapplication.adapters
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DriverMain
import com.example.myapplication.R
import com.example.myapplication.models.Assignments
import com.example.myapplication.models.Services
import com.example.myapplication.models.TaskPost
import com.example.myapplication.models.UserInfo
import com.example.myapplication.services.RestApiService
import com.google.android.material.button.MaterialButton

class RecyclerAdapter2(var context: Context)://When you want to toast smthg without intent or activities
    RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>(){
    //View holder holds the views in single item.xml

    var productList : List<Services> = listOf() // empty product list

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    //Note below code returns above class and pass the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter2.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item2, parent, false)
        return ViewHolder(view)
    }
    //so far item view is same as single item
    override fun onBindViewHolder(holder: RecyclerAdapter2.ViewHolder, position: Int) {
        val services = holder.itemView.findViewById(R.id.services) as TextView
        val datetime = holder.itemView.findViewById(R.id.datetime) as TextView
        val status = holder.itemView.findViewById(R.id.status) as TextView


        //bind
        val item = productList[position]
        services.text = item.services
        datetime.text = item.scheduled_date+ " : "+item.scheduled_time+"- "+item.reg_no
        status.text = item.status

    }
    override fun getItemCount(): Int {
        return productList.size
    }

    //we will call this function on Loopj response
    fun setProductListItems(productList: List<Services>){
        this.productList = productList
        notifyDataSetChanged()
    }
}//end class
