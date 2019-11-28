package com.example.carhelperapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var url:String=UserInfo.url + "services.php"
        var list=ArrayList<CarServices>()

        var rq=Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,
            null, Response.Listener { response ->
                for(x in 0..response.length()-1){
                    list.add(
                        CarServices(
                            response.getJSONObject(x).getInt("id"),
                            response.getJSONObject(x).getString("name"),
                            response.getJSONObject(x).getString("photo")
                    )
                    )
                }
                var adp=ServiceAdapter(this,list)
                services_rv.adapter=adp
                services_rv.layoutManager=GridLayoutManager(this,2)
            },
            Response.ErrorListener {  })
        rq.add(jar)
    }
}
