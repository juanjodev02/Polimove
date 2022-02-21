package com.example.polimove




import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.polimove.ui.login.LoginFragment
import com.example.polimove.ui.register.SignInFragment


class Login : AppCompatActivity() {
    private lateinit var buttonSignIn: Button
    var loginView = true

//    private val viewModel: ItemViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel.selectedItem.observe(this, Observer { item ->
//            // Perform an action with the latest item data
//        })
//    }

//    class ListFragment : Fragment() {
//        // Using the activityViewModels() Kotlin property delegate from the
//        // fragment-ktx artifact to retrieve the ViewModel in the activity scope
//        private val viewModel: ItemViewModel by activityViewModels()
//
//        // Called when the item is clicked
//        fun onItemClicked(item: Item) {
//            // Set a new item
//            viewModel.selectItem(item)
//        }


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Polimove)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        }


}