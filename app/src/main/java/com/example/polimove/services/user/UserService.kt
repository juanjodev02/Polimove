package com.example.polimove.services.user




import android.content.ContentValues.TAG
import android.util.Log
import com.example.polimove.models.User
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
                var nameUser = result.documents[0].toObject(User::class.java)

                if (nameUser != null) {
                    cb(nameUser)
                }
            }
    }

    fun getRouteName(rutaId: String, cb: (nameRoute: String) -> Unit) : Unit{
        val firestore = FirebaseFirestore.getInstance()
        firestore
            .collection("routes")
            .document(rutaId)
            .get()
            .addOnSuccessListener { result ->
                var route = result.toObject(User::class.java)
                if (route != null) {
                    route.name?.let { cb(it) }
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




    fun signOff(){
        Firebase.auth.signOut()
    }







}


}