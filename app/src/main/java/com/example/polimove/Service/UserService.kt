package com.example.polimove.Service

import android.content.ContentValues.TAG
import android.util.Log
import com.example.polimove.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserService {
    companion object {

        private lateinit var auth: FirebaseAuth

        fun getData(cedula: String?, cb: (user: User) -> Unit): Unit {
            val firestore = FirebaseFirestore.getInstance()
            firestore
                .collection("usuarios")
                .whereEqualTo("cedula", cedula)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        var nameUser = document.toObject(User::class.java)
                        nameUser.name = document["name"].toString()
                        nameUser.lastName = document["lastName"].toString()
                        nameUser.routeId = document["routeId"].toString()
                        nameUser.email = document["email"].toString()
                        cb(nameUser)
                    }
                }
        }

        fun getRouteName(rutaId: String?,cb: (nameRoute: String) -> Unit) : Unit{
            val firestore = FirebaseFirestore.getInstance()
            firestore
                .collection("routes")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        var route = document.toObject(User::class.java)
                        if (route != null){
                            route.routeId = document.id
                            if (route.routeId.equals(rutaId)){
                                var routename = document.data["name"].toString()
                                cb(routename)
                            }
                        }


                    }

                }
        }

        fun getUserId(cedula: String?): Unit{
            val firestore = FirebaseFirestore.getInstance()
            firestore
                .collection("usuarios")
                .whereEqualTo("cedula",cedula)
                .get()
                .addOnSuccessListener { result->
                    for(document in result){
                        var userid = document.id
                        deleteUser(userid)
                    }
                }
        }

        fun deleteUser(idUser:String){
            val db = Firebase.firestore
            db.collection("usuarios")
                .document(idUser)
                .delete()
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

        }


        fun getDriverData(cedula: String, cb: (user: User) -> Unit): Unit {
            val firestore = FirebaseFirestore.getInstance()
            firestore
                .collection("conductores")
                .whereEqualTo("cedula", cedula)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        var nameUser = document.toObject(User::class.java)
                        nameUser.name = document["name"].toString()
                        nameUser.lastName = document["lastName"].toString()
                        nameUser.routeId = document["routeId"].toString()
                        cb(nameUser)
                    }
                }
        }

        fun signOff(){
            Firebase.auth.signOut()
        }







    }
}