package com.jjoh.firebasechatt.repository.remote.getlist.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.jjoh.firebasechatt.view.model.Friend
import com.jjoh.firebasechatt.widget.utils.Util.friend
import javax.inject.Inject

class GetListDataSourceImpl @Inject constructor(
    private var firebaseAuth: FirebaseAuth,
    private var firebaseDatabase: FirebaseDatabase
) : GetListDataSource {

    override suspend fun tryGetList() {
        firebaseAuth = Firebase.auth
        val myUID = firebaseAuth.currentUser?.uid.toString()

        firebaseDatabase.reference.child("users").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                friend.clear()
                for (data in snapshot.children) {
                    val item = data.getValue<Friend>()
                    if (item?.uid.equals(myUID)) { continue }
                    friend.add(item!!)
                }
            }
        })
    }
}
