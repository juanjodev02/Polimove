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
                    var routes: ArrayList<Route> = arrayListOf()
                    for (document in result) {
                        var route = document.toObject(Route::class.java)
                        route.id = document.id
                        routes.add(route);
                    }
                    cb(routes)
                }
        }

        fun getRouteByName(name:String, cb: (route: Route) -> Unit): Unit {
            val firestore = FirebaseFirestore.getInstance()
            firestore
                .collection("routes")
                .whereEqualTo("name", name)
                .get()
                .addOnSuccessListener { result ->
                    var route = result.documents[0].toObject(Route::class.java)
                    if (route != null) {
                        route.id = result.documents[0].id
                        cb(route)
                    }
                }
        }
    }
}