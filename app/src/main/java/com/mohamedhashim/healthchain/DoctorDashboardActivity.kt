package com.mohamedhashim.healthchain

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by Mohamed Hashim on 6/17/2018.
 */
class DoctorDashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val DoctorList = ArrayList<Doctor>()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: MoviesAdapter? = null
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_dashboard)
        fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            showChangeLangDialog()
        })
        recyclerView = findViewById<RecyclerView>(R.id.patients_recyclerview)

        mAdapter = MoviesAdapter(DoctorList)
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter

//        prepareDoctorData()
        postNewComment(this)
    }

//    private fun prepareDoctorData() {
//        var Doctor = Doctor("Mad Max: Fury Road", "Action & Adventure", "2015")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Inside Out", "Animation, Kids & Family", "2015")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Star Wars: Episode VII - The Force Awakens", "Action", "2015")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Shaun the Sheep", "Animation", "2015")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("The Martian", "Science Fiction & Fantasy", "2015")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Mission: Impossible Rogue Nation", "Action", "2015")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Up", "Animation", "2009")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Star Trek", "Science Fiction", "2009")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("The LEGO Doctor", "Animation", "2014")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Iron Man", "Action & Adventure", "2008")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Aliens", "Science Fiction", "1986")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Chicken Run", "Animation", "2000")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Back to the Future", "Science Fiction", "1985")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Raiders of the Lost Ark", "Action & Adventure", "1981")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Goldfinger", "Action & Adventure", "1965")
//        DoctorList.add(Doctor)
//
//        Doctor = Doctor("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014")
//        DoctorList.add(Doctor)
//
//        mAdapter!!.notifyDataSetChanged()
//
//    }


    fun postNewComment(context: Context) {
//        val mPostCommentResponse: PostCommentResponseListener
//        mPostCommentResponsese!!.requestStarted()
        val queue = Volley.newRequestQueue(context)
        val sr = object : StringRequest(Request.Method.GET, "http://ec2-18-216-204-179.us-east-2.compute.amazonaws.com:3000/api/patient", object : Response.Listener<String> {
            override fun onResponse(response: String) {
//                mPostCommentResponse.requestCompleted()
//                Log.d("logs",mPostCommentResponse.toString())
//                Toast.makeText(applicationContext, response, Toast.LENGTH_LONG).show()
                val json = JSONArray(response)
                for (i in 0 until json.length()) {
                    var jsonObject: JSONObject = json.getJSONObject(i)
                    val str_value = jsonObject.getString("fName")
                    val str_name = jsonObject.getString("lname")
//                    val str_age = jsonObject.getInt("Age")
                    var Doctor = Doctor(str_value + " " + str_name, str_value + " " + str_name, "22")
                    DoctorList.add(Doctor)
                    mAdapter!!.notifyDataSetChanged()

                }

            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError) {
//                mPostCommentResponse.requestEndedWithError(error)
                Toast.makeText(applicationContext, "ERORR " + error.toString(), Toast.LENGTH_LONG).show()
            }
        }) {
//            override fun getParams(): Map<String, String> {

//                val params = HashMap<String, String>()
//                params["patientID"] = "306"
//                params["fName"] = "Mohamed"
//                params["lname"] = "Hashim"
//                params["GenderType"] = "male"
//                params["Age"] = "100"
//                params["email"] = "sawmido2000@gmail.com"
//                params["PhonNumber"] = "100"
//                params["Age"] = "100"
//                params["department"] = "Dentist"
//                val jsonAddress = JSONObject()
//                jsonAddress.put("city", "Cairo")
//                jsonAddress.put("street", "Awad Elsisy")
//                params["address"] = jsonAddress.toString()
//                val par = HashMap<String, String>()
//                par["data"] = params.toString()
//                Toast.makeText(applicationContext, par.toString(), Toast.LENGTH_LONG).show()
//                return par
//            }

//            @Throws(AuthFailureError::class)
//            override fun getHeaders(): Map<String, String> {
//                val params = HashMap<String, String>()
//                params["Accept"] = "application/json"
//                params["Content-Type"] = "application/json"
//                return params
//            }
        }
        queue.add(sr)
    }

    interface PostCommentResponseListener {
        fun requestStarted()
        fun requestCompleted()
        fun requestEndedWithError(error: VolleyError)
    }

    fun showChangeLangDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)
        dialogBuilder.setView(dialogView)

        val edt1 = dialogView.findViewById<EditText>(R.id.edit1)
        val edt2 = dialogView.findViewById<EditText>(R.id.edit2)
        val edt3 = dialogView.findViewById<EditText>(R.id.edit3)

        dialogBuilder.setTitle("Patient Form")
//        dialogBuilder.setMessage("Enter text below")
        dialogBuilder.setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, whichButton ->
            //do something with edt.getText().toString();
            var Doctor = Doctor(edt1.text.toString() + " " + edt2.text.toString(), edt1.text.toString() + " " + edt2.text.toString(), edt3.text.toString())
            DoctorList.add(Doctor)
            mAdapter!!.notifyDataSetChanged()


            dialog.dismiss()
            Toast.makeText(applicationContext, "Patient is added successfully", Toast.LENGTH_LONG).show()
        })
//        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, whichButton ->
//            //pass
//        })
        val b = dialogBuilder.create()
        b.show()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> {
                this.finish()
            }
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}