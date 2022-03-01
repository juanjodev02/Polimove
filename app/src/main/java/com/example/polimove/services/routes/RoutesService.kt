package com.example.polimove.services.routes

import android.util.Log
import com.example.polimove.models.Route
import com.example.polimove.models.User
import com.google.firebase.firestore.FirebaseFirestore

class RoutesService {
    companion object {
        fun getAllRoutes(firestore: FirebaseFirestore, cb: ((routes: ArrayList<Route>) -> Unit)): Unit {
            try {
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
            } catch (e: NullPointerException) {
                Log.d("routes:service", e.message.toString())
            }
        }

        fun getRouteByName(firestore: FirebaseFirestore, name:String, cb: (route: Route) -> Unit): Unit {
            firestore
                .collection("routes")
                .whereEqualTo("name", name)
                .get()
                .addOnSuccessListener { result ->
                    val route = result.documents[0].toObject(Route::class.java)
                    if (route != null) {
                        route.id = result.documents[0].id
                        cb(route)
                    }
                }
        }

        fun attachUserToRoute(firestore: FirebaseFirestore, routeId: String, identityNumberId: String, cb: (user: User)-> Unit): Unit {
            try {
                val userRef =  firestore
                    .collection("usuarios")
                    .whereEqualTo("cedula", identityNumberId)

                userRef
                    .get()
                    .addOnSuccessListener { result ->
                        if (result.isEmpty) {
                            return@addOnSuccessListener
                        }
                        val user = result.documents[0].toObject(User::class.java)
                        firestore
                            .collection("usuarios")
                            .document("/" + result.documents[0].id)
                            .update("routeId", routeId)
                        if (user != null) {
                            cb(user)
                        }
                    }
            } catch (e: NullPointerException) {
                Log.e("routes:service", e.message.toString())
            }
        }

        fun checkIfUserHasARouteAttached(firestore: FirebaseFirestore,identityNumberId: String, cb: (hasRouteAttached: Boolean) -> Unit) {
            try {
                firestore
                    .collection("usuarios")
                    .whereEqualTo("cedula", identityNumberId)
                    .get()
                    .addOnSuccessListener { result ->
                        if (result.isEmpty) {
                            return@addOnSuccessListener
                        }
                        val user = result.documents[0].toObject(User::class.java)
                        if (user != null) {
                            if (user.routeId != null) {
                                cb(true)
                            } else {
                                cb(false)
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("error", "Error getting documents.", exception)
                    }
            } catch (e: java.lang.NullPointerException) {
                Log.e("routes:service", e.message.toString())
            }
        }
    }
}