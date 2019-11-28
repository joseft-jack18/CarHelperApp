package com.example.carhelperapp

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var lon:Double=0.0
    var lat:Double=0.0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var manager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var listener=object:LocationListener{
            override fun onLocationChanged(p0: Location) {
                lon=p0.longitude
                lat=p0.latitude
                val my_loc = LatLng(p0.longitude,p0.latitude)
                mMap.addMarker(MarkerOptions().position(my_loc).title("aQUI VIVO PEEEEE"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_loc,16f))
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,listener)
        order_button.setOnClickListener {
            var url:String=UserInfo.url + "avl_emp.php?mobile=" + UserInfo.mobile +
                    "&service_id=" + UserInfo.service_id + "&lon=" + lon + "&lat=" + lat

            var rq= Volley.newRequestQueue(this)
            var sr=StringRequest(Request.Method.GET,url,
                Response.Listener { response ->
                    Toast.makeText(this,"Your order has been sent",Toast.LENGTH_LONG).show()
                    var mgr = SmsManager.getDefault()
                    mgr.sendTextMessage(response,null,"SDFSFSDF",null,null)
                },
                Response.ErrorListener {  })
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0,151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Aqui pe..."))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
