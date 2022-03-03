package com.example.polimove.ui.home



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.polimove.databinding.FragmentHomeBinding
import com.example.polimove.services.user.UserService
import com.example.polimove.sharedPreferences.LOGIN_KEY
import com.example.polimove.sharedPreferences.PASSWORD_KEY
import com.example.polimove.sharedPreferences.SHAREDINFO_FILENAME
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var nameTextView: TextView
    private lateinit var textViewTitulo: TextView
    private lateinit var imageViewQRCODE: ImageView
    private lateinit var layoutNoRoute: LinearLayout
    private lateinit var hasRouteLayout: LinearLayout
    private lateinit var buttonReservarRuta: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        nameTextView = binding.textHome
        textViewTitulo = binding.textViewNombreRuta
        imageViewQRCODE = binding.imageViewQRCODE
        layoutNoRoute = binding.noRoute
        hasRouteLayout = binding.hasRouteLayout
        buttonReservarRuta = binding.buttonReservarRuta




        val listadoLeido = this.readInformation()
        if(listadoLeido?.first != null) {
            this.cedula = listadoLeido.first
            UserService.getData(this.cedula) { nameUser ->
                nameTextView.text =
                    "¡Hola! " + String(Character.toChars(0x1F44B)) + " " + nameUser.name + " " + nameUser.lastName
                if (nameUser.routeId != null) {
                    this.hasRouteLayout.visibility = View.VISIBLE
                    UserService.getRouteName(nameUser.routeId.toString()) { routename ->
                        textViewTitulo.text = "Tu ruta es: " + routename
                    }
                    try {
                        val barcodeEncoder = BarcodeEncoder()
                        val bitmap = barcodeEncoder.encodeBitmap(cedula, BarcodeFormat.QR_CODE, 400, 400)
                        imageViewQRCODE.setImageBitmap(bitmap)
                    } catch (e: Exception) {
                        Log.d("TAG","Problems generating QRCODE")
                    }

                }else{
                    this.layoutNoRoute.visibility = View.VISIBLE
                    buttonReservarRuta.setOnClickListener(){
                        findNavController().navigate(com.example.polimove.R.id.action_home_to_Routes)
                    }
                }

            }


        }
        return root
    }

    fun readInformation():Pair<String,String>{
        val sharedPref = context?.getSharedPreferences(SHAREDINFO_FILENAME.toString(),0)
        val cedula = sharedPref?.getString(LOGIN_KEY,"").toString()
        val clave = sharedPref?.getString(PASSWORD_KEY,"").toString()
        return (cedula to clave)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datosAEnviar = Bundle()
        datosAEnviar.putString("cedula", this.cedula).toString().trim()
        parentFragmentManager.setFragmentResult("key",datosAEnviar)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}