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
import com.example.polimove.services.user.UserService
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import androidx.navigation.fragment.findNavController
import com.example.polimove.databinding.FragmentHomeBinding


private const val USER_CI_PARAM = "1722951165"
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var nameTextView: TextView
    private lateinit var textViewTitulo: TextView
    private lateinit var imageViewQRCODE: ImageView
    private lateinit var layoutNoRoute: LinearLayout
    private lateinit var hasRouteLayout: LinearLayout
    private lateinit var imageViewPolibus: ImageView
    private lateinit var textViewHM: TextView
    private lateinit var textViewHN: TextView
    private lateinit var textViewMensaje: TextView
    private lateinit var buttonReservarRuta: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        cedula = "1721791661"
        val root: View = binding.root

        nameTextView = binding.textHome
        textViewTitulo = binding.textViewNombreRuta
        imageViewQRCODE = binding.imageViewQRCODE
        imageViewPolibus = binding.imageView
        textViewHM = binding.textViewHorarioMatutino
        textViewHN = binding.textViewHorarioNoche
        textViewMensaje = binding.textViewMensaje
        layoutNoRoute = binding.noRoute
        buttonReservarRuta = binding.buttonReservarRuta
        hasRouteLayout = binding.hasRouteLayout


        UserService.getData(cedula as String) { nameUser ->

          if(nameUser.routeId != null){
                this.hasRouteLayout.visibility = View.VISIBLE
                nameTextView.text =
                    "¡Hola! " + String(Character.toChars(0x1F44B)) + " " + nameUser.name + " " + nameUser.lastName

                UserService.getRouteName(nameUser.routeId.toString()) { routename ->
                    textViewTitulo.text = "Tu ruta es: " + routename
                }

                try {
                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap = barcodeEncoder.encodeBitmap(cedula, BarcodeFormat.QR_CODE, 400, 400)
                    imageViewQRCODE.setImageBitmap(bitmap)
                } catch (e: Exception) {

                }
            }else{
              this.layoutNoRoute.visibility = View.VISIBLE
              buttonReservarRuta.setOnClickListener(){
                  findNavController().navigate(com.example.polimove.R.id.action_home_to_Routes)
              }
          }
        }




        return root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datosAEnviar = Bundle()
        datosAEnviar.putString("cedula", "1722951165").toString().trim()
        parentFragmentManager.setFragmentResult("key",datosAEnviar)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}