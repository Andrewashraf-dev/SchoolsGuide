package com.example.schoolsguide

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolsguide.schools.*
import com.example.schoolsguide.schools.intern.*


class InternationalSchoolsActivity : AppCompatActivity(), UsersAdapter.ClickListener {

    private lateinit var rvUsers: RecyclerView;
    private lateinit var usersAdapter: UsersAdapter;
    private lateinit var toolbar: Toolbar;
    private lateinit var spinner: Spinner;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_international_schools)
        initData();
    }


    private fun initData(){
        rvUsers = findViewById(R.id.rvUsers)
        toolbar = findViewById(R.id.tbToolbar)
        spinner = findViewById(R.id.spinner)
        this.setSupportActionBar(toolbar)
        this.supportActionBar!!.title = ""

        val categories= mutableListOf<String>()
        for (it in loc){
            categories.add(it)
        }
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = dataAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                fil = loc[position]
                initRecyclerView();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                fil = "All"
                initRecyclerView();
            }

        }
        initRecyclerView();
    }

    private fun initRecyclerView(){
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        usersAdapter = UsersAdapter(this);
        rvUsers.adapter = usersAdapter;
        showData();

    }

    var fil = "All"

    var loc  = arrayListOf("All","Shoubra", "Maadi")
    val dictionary = mapOf(
        "ISIS School" to "Shoubra",
        "St. George Language School" to "Shoubra",
        "Sweet Home Language School" to "Shoubra",
        "The British School - BSC" to "Maadi",
        "Sama International School" to "Maadi"
    )

    private fun populateUsers(): List<UserModel> {

        var userList = ArrayList<UserModel>()
        for (it in dictionary){
            if (fil == "All") userList.add(UserModel(it.key))
            else if(it.value == fil) userList.add(UserModel(it.key))
        }

        return userList;
    }

    private fun showData (){

        usersAdapter.setData(populateUsers())
    }

    override fun clickedItem(userModel: UserModel) {

        when(userModel.username){
            "ISIS School" -> {
                startActivity(Intent(this, ISIS::class.java).putExtra("name", userModel.username))
            }

            "St. George Language School" -> {
                startActivity(Intent(this, StGeorge::class.java))
            }
            "Sweet Home Language School" -> {
                startActivity(Intent(this, SweetHome::class.java))
            }
            "The British School - BSC" -> {
                startActivity(Intent(this, British::class.java))
            }
            "Sama International School" -> {
                startActivity(Intent(this, Sama::class.java))
            }

        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        var menuItem = menu!!.findItem(R.id.searchView)
        var searchView: SearchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                usersAdapter.filter.filter(newText)
                return true;
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun formatTimeStamp(toLong: Long) {
            return
        }
    }
}
