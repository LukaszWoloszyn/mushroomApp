import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mushroomapp.LoginActivity
import com.example.mushroomapp.MainActivity
import com.example.mushroomapp.ProfileActivity
import com.example.mushroomapp.RegisterActivity
import com.example.mushroomapp.R
import com.example.mushroomapp.SessionManager
import com.google.android.material.navigation.NavigationView

open class BaseActivity : AppCompatActivity() {
    protected lateinit var drawerLayout: DrawerLayout
    protected lateinit var navigationView: NavigationView
    protected lateinit var toggle: ActionBarDrawerToggle
    protected lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        // Ustawienie paska narzędzi jako ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        sessionManager = SessionManager(this)

        // Inicjalizacja DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        // Inicjalizacja NavigationView - to kluczowy krok, który wcześniej był pominięty
        navigationView = findViewById(R.id.nav_view)

        // Ustawienie ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Ustawienie listenera dla elementów menu w NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_login -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_register -> {
                    startActivity(Intent(this, RegisterActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_logout -> {
                    logoutUser()
                    true
                }
                else -> false
            }
        }
    }

    protected fun updateNavMenu() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        if (navigationView != null) {
            val menu = navigationView.menu

            if (sessionManager.isLoggedIn()) {
                // Użytkownik zalogowany
                menu.findItem(R.id.nav_login)?.isVisible = false
                menu.findItem(R.id.nav_register)?.isVisible = false
                menu.findItem(R.id.nav_profile)?.isVisible = true
                menu.findItem(R.id.nav_trip)?.isVisible = true
                menu.findItem(R.id.nav_guide)?.isVisible = true
                menu.findItem(R.id.nav_community)?.isVisible = true
                menu.findItem(R.id.nav_logout)?.isVisible = true

                // Aktualizacja nagłówka
                updateNavHeader()
            } else {
                // Użytkownik niezalogowany
                menu.findItem(R.id.nav_login)?.isVisible = true
                menu.findItem(R.id.nav_register)?.isVisible = true
                menu.findItem(R.id.nav_profile)?.isVisible = false
                menu.findItem(R.id.nav_trip)?.isVisible = false
                menu.findItem(R.id.nav_guide)?.isVisible = false
                menu.findItem(R.id.nav_community)?.isVisible = false
                menu.findItem(R.id.nav_logout)?.isVisible = false

                // Reset nagłówka
                updateNavHeader()
            }
        }
    }

    private fun updateNavHeader() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view) ?: return
        val headerView = navigationView.getHeaderView(0)

        val usernameTextView = headerView.findViewById<TextView>(R.id.nav_header_username)
        val emailTextView = headerView.findViewById<TextView>(R.id.nav_header_email)

        if (sessionManager.isLoggedIn()) {
            val userDetails = sessionManager.getUserDetails()
            usernameTextView.text = userDetails["username"]
            emailTextView.text = userDetails["email"]
        } else {
            usernameTextView.text = getString(R.string.guest)
            emailTextView.text = ""
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    protected fun logoutUser() {
        sessionManager.logout()

        updateNavMenu()

        drawerLayout.closeDrawer(GravityCompat.START)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)

        Toast.makeText(this, "Zostałes wylogowany", Toast.LENGTH_SHORT).show()
    }
}
