package com.example.polimove.ui.home



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.polimove.databinding.FragmentHomeBinding
import com.example.polimove.services.user.UserService

private const val USER_CI_PARAM = "1722951165"
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var nameTextView: TextView
    private lateinit var textViewTitulo: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //cedula = arguments?.getString(USER_CI_PARAM)
            cedula="1722951165"
        val root: View = binding.root
        nameTextView = binding.textHome
        textViewTitulo = binding.textViewNombreRuta

        UserService.getData(cedula as String) { nameUser ->
            nameTextView.text = "¡Hola! "+String(Character.toChars(0x1F44B))+" "+nameUser.name +" "+nameUser.lastName

            UserService.getRouteName(nameUser.routeId){routename ->
                textViewTitulo.text = "Tu ruta es: "+routename
            }

        }





/*

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val textViewEmoji: TextView = binding.textViewEmoji
        homeViewModel.emoji.observe(viewLifecycleOwner){
            textViewEmoji.text = it
        }

        val textViewTitulo: TextView = binding.textViewNombreRuta
        homeViewModel.nombreRuta.observe(viewLifecycleOwner){
            textViewTitulo.text = it
        }

        val textViewCode: TextView = binding.textViewCode
        homeViewModel.codigo.observe(viewLifecycleOwner){
            textViewCode.text = it
        }

        val textViewQRCODE: TextView = binding.textViewQRCODE
        homeViewModel.QRCODE.observe(viewLifecycleOwner){
            textViewQRCODE.text = it
        }*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}