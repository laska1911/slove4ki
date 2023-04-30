class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        // Обработка нажатия на кнопки меню, например, кнопки "Играть"
        button_play.setOnClickListener {
            val intent = Intent(this, GameScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
