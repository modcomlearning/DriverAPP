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
import com.example.myapplication.models.TaskPost
import com.example.myapplication.models.UserInfo
import com.example.myapplication.services.RestApiService
import com.google.android.material.button.MaterialButton

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
        val status = holder.itemView.findViewById(R.id.status) as TextView
        val btnDoAction = holder.itemView.findViewById(R.id.btnDoAction) as MaterialButton

        //bind
        val item = productList[position]
        from.text = item.from
        to.text = item.to
        datetime.text = item.scheduled_date+ " : "+item.scheduled_time+"- "+item.reg_no
        status.text = item.trip_complete_status
        if(item.trip_complete_status == "Ongoing"){
            btnDoAction.text = "COMPLETE TRIP"
            btnDoAction.setBackgroundColor(Color.parseColor("#ffca28"))
        }
        if(item.trip_complete_status == "Completed"){
            btnDoAction.text = "TRIP COMPLETED"
            btnDoAction.setBackgroundColor(Color.parseColor("#2e7d32"))
        }
        btnDoAction.setOnClickListener{
            //We set a Interface, Retrofit===========
            if(item.trip_complete_status == "Pending") {
                val apiService = RestApiService()
                val task = TaskPost(taskId = item.task_id)
                apiService.Trip_Ongoing(task) {
                    Toast.makeText(context, "${it!!.userMsg}", Toast.LENGTH_SHORT).show()
                    val x = Intent(context, DriverMain::class.java)
                    x.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(x)
                }//end ongoing
            }//end if
            else if(item.trip_complete_status == "Ongoing") {
                val apiService = RestApiService()
                val task = TaskPost(taskId = item.task_id)
                apiService.Trip_Completed(task) {
                    Toast.makeText(context, "${it!!.userMsg}", Toast.LENGTH_SHORT).show()
                    val x = Intent(context, DriverMain::class.java)
                    x.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(x)
                }//end ongoing
            }//end if
            else {  btnDoAction.isEnabled = false  }









        }
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
