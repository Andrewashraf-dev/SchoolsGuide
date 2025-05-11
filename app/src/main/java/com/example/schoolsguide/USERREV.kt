package com.example.schoolsguide

import android.app.AlertDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.schoolsguide.databinding.ActivityUserrevBinding
import com.example.schoolsguide.databinding.DialogCommentAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class USERREV : AppCompatActivity() {
    lateinit var viewBinding: ActivityUserrevBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    private lateinit var commentArrayList: ArrayList<ModelComment>
    private lateinit var adapterComment: AdapterComment

    private var schoolId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUserrevBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        showComments()

        //int progress bar
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setCanceledOnTouchOutside(false)

        //handle click, show add comment dialog
        viewBinding.addCommentBtn.setOnClickListener{
            /*to add a comment user must be logged in, if not show a messge youre not loggin in*/
            if (firebaseAuth.currentUser == null) {
                //user not logged in, dont allow adding comment
                Toast.makeText(this, "You're not logged in", Toast.LENGTH_SHORT).show()
            } else {
                //uswer loggd in allow addin commebt
                addCommentDialog()
            }
        }
    }

    private fun showComments() {
        commentArrayList = ArrayList()

        val ref = FirebaseDatabase.getInstance().getReference("schools")
        ref.child(schoolId).child("Comments")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    commentArrayList.clear()
                    for (ds in snapshot.children){
                        val model = ds.getValue(ModelComment::class.java)
                        commentArrayList.add(model!!)
                    }

                    adapterComment = AdapterComment(this@USERREV, commentArrayList)

                    viewBinding.CommentsRv.adapter = adapterComment

                }


                override fun onCancelled(error: DatabaseError) {
                }
            })
    }


    private var comment = ""
    private fun addCommentDialog() {
        //inflate/bind view for dialog dialog_comment_add.xml
        val commentAddBinding = DialogCommentAddBinding.inflate(LayoutInflater.from(this))

        // setup alert dialog
        val builder = AlertDialog.Builder(this, R.style.CustomDialog)
        builder.setView(commentAddBinding.root)

        //create and show alert dialog
        val alertDialog = builder.create()
        alertDialog.show()

        //handle click , dismsiss dialog
        commentAddBinding.backBtn.setOnClickListener{alertDialog.dismiss()}

        //handle click, add comment
        commentAddBinding.submitBtn.setOnClickListener{
            //get data
            comment = commentAddBinding.commentEt.text.toString().trim()
            // validate data
            if (comment.isEmpty()) {
                Toast.makeText(this, "Enter Comment.....", Toast.LENGTH_SHORT).show()
            }
            else{
                alertDialog.dismiss()
                addComment()
            }
        }

    }


    private fun addComment() {
        //show progess
        progressDialog.setMessage("Adding Comment")
        progressDialog.show()

        //timestamp for commebt id , commet time stap etc.
        val timestamp = "${System.currentTimeMillis()}"

        //setp data to add in db for commebt
        val hashMap = HashMap<String, Any> ()
        hashMap["id"] ="$timestamp"
        hashMap["schoolId"] ="$schoolId"
        hashMap["timestamp"] ="$timestamp"
        hashMap["comment"] ="$comment"
        hashMap["uid"] ="${firebaseAuth.uid}"

        //db path to add data into it
        // schoold > schooldId > comments > commentId > commentdata
        val ref = FirebaseDatabase.getInstance().getReference("Schools")
        ref.child(schoolId).child("Comments").child(timestamp)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Comment added,,,", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed to add comment due to ${e.message}", Toast.LENGTH_SHORT).show()

            }

    }
}
