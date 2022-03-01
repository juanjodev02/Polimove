package com.example.polimove.ui.home



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import com.example.polimove.databinding.FragmentHomeBinding
import com.example.polimove.services.user.UserService
import com.example.polimove.sharedPreferences.LOGIN_KEY
import com.example.polimove.sharedPreferences.PASSWORD_KEY
import com.example.polimove.sharedPreferences.SHAREDINFO_FILENAME
import com.example.polimove.ui.profile.ProfileFragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import io.grpc.InternalConfigSelector.KEY


private const val USER_CI_PARAM = "1722951165"
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var nameTextView: TextView
    private lateinit var textViewTitulo: TextView
    private lateinit var imageViewQRCODE: ImageView


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

        val listadoLeido = this.readInformation()
        if(listadoLeido?.first != null){
            this.cedula = listadoLeido.first
            UserService.getData(this.cedula) { nameUser ->
                nameTextView.text =
                    "¡Hola! " + String(Character.toChars(0x1F44B)) + " " + nameUser.name + " " + nameUser.lastName

                UserService.getRouteName(nameUser.routeId) { routename ->
                    textViewTitulo.text = "Tu ruta es: " + routename
                }

            }
        }

        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(cedula, BarcodeFormat.QR_CODE, 400, 400)
            imageViewQRCODE.setImageBitmap(bitmap)
        } catch (e: Exception) {

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