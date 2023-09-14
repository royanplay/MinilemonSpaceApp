import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minilemonspaceapp.Management.MissionAdapter
import com.example.minilemonspaceapp.Management.MissionManagement
import com.example.minilemonspaceapp.Management.UserAPI
import com.example.minilemonspaceapp.Management.UserProfile

class MainActivity : AppCompatActivity() {

    private lateinit var userAPI: UserAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi UserAPI
        userAPI = UserAPI(this)

        // Menyimpan data pengguna ke database
        val user = UserProfile(
            id = 0,
            jenisProfil = "Orang Tua",
            nama = "royan",
            email = "royan@example.com",
            usia = 24,
            jenisKelamin = "Laki-Laki"
        )

        val userId = userAPI.addUser(user)
        if (userId > 0) {
            Toast.makeText(this, "Profil pengguna berhasil disimpan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Gagal menyimpan profil pengguna", Toast.LENGTH_SHORT).show()
        }

        // Mengambil semua data pengguna dari database
        val usersCursor = userAPI.getAllUsers()
        if (usersCursor != null) {
            // Lakukan sesuatu dengan data pengguna (misalnya, tampilkan dalam daftar)
        }

        // Inisialisasi RecyclerView untuk menampilkan daftar misi
        val missionManagement = MissionManagement(this)
        val missions = missionManagement.getAllMissions()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = MissionAdapter(missions)
        recyclerView.adapter = adapter
    }
}
