package com.example.polimove.services.routes

import android.util.Log
import com.example.polimove.models.Route
import com.google.firebase.firestore.FirebaseFirestore

class RoutesService {
    companion object {
        fun getAllRoutes(cb: ((routes: ArrayList<Route>) -> Unit)): Unit {
            val firestore = FirebaseFirestore.getInstance()
            firestore
                .collection("routes")
                .get()
                .addOnSuccessListener { result ->
                    var routes = ArrayList<Route>();
                    for (document in result) {
                        var route = document.toObject(Route::class.java)
                        routes.add(route);
                    }
                    cb(routes)
                }
        }
    }
}