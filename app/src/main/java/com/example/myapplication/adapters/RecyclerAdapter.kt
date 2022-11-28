package com.example.myapplication.adapters
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Assignments
class RecyclerAdapter(var context: Context)://When you want to toast smthg without intent or activities
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    //View holder holds the views in single item.xml

    var productList : List<Assignments> = listOf() // empty product list

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    //Note below code returns above class and pass the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return ViewHolder(view)
    }
    //so far item view is same as single item
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val from = holder.itemView.findViewById(R.id.from) as TextView
        val to = holder.itemView.findViewById(R.id.to) as TextView
        val datetime = holder.itemView.findViewById(R.id.datetime) as TextView

        //bind
        val item = productList[position]
        from.text = item.from
        to.text = item.to
        datetime.text = item.scheduled_date+ " : "+item.scheduled_time

    }
    override fun getItemCount(): Int {
        return productList.size
    }

    //we will call this function on Loopj response
    fun setProductListItems(productList: List<Assignments>){
        this.productList = productList
        notifyDataSetChanged()
    }
}//end class
