import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var questions = mutableListOf<Question>()
    var currentQuestionIndex = 0
    var correctAnswers = 0
    var wrongAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Добавляем вопросы и ответы в список questions
        questions.add(Question("Как пишется слово 'кот'?", listOf("кот", "кат", "кет")))
        questions.add(Question("Как пишется слово 'банан'?", listOf("банан", "баннан", "бенан")))
        questions.add(Question("Как пишется слово 'стол'?", listOf("стол", "стул", "стулья")))
        questions.add(Question("Как пишется слово 'мама'?", listOf("мама", "мамма", "мамка")))
        questions.add(Question("Как пишется слово 'книга'?", listOf("книга", "кнега", "кнага")))
        
        showQuestion()
        
        // Обработчик нажатия на кнопку "Ответить"
        answerButton.setOnClickListener {
            val selectedAnswer = answerRadioGroup.checkedRadioButtonId
            val answerText = findViewById<RadioButton>(selectedAnswer).text
            
            checkAnswer(answerText.toString())
            showQuestion()
        }
    }
    
    // Показывает текущий вопрос и варианты ответов
    private fun showQuestion() {
        if (currentQuestionIndex >= questions.size) {
            // Все вопросы заданы, игра окончена
            showEndGame()
        } else {
            val currentQuestion = questions[currentQuestionIndex]
            questionTextView.text = currentQuestion.question
            
            // Перемешиваем варианты ответов
            val answers = currentQuestion.answers.shuffled()
            answerRadioButton1.text = answers[0]
            answerRadioButton2.text = answers[1]
            answerRadioButton3.text = answers[2]
            
            currentQuestionIndex++
        }
    }
    
    // Проверяет ответ на текущий вопрос
    private fun checkAnswer(answer: String) {
        val currentQuestion = questions[currentQuestionIndex - 1]
        if (currentQuestion.answers[0] == answer) {
            // Верный ответ
            correctAnswers++
        } else {
            // Неверный ответ
            wrongAnswers++
        }
    }
    
    // Показывает результаты игры
    private fun showEndGame() {
        val resultText = "Верных ответов: $correctAnswers\nНеверных ответов: $wrongAnswers"
        questionTextView.text = resultText
        answerRadioGroup.visibility = View.INVISIBLE
        answerButton.visibility = View.INVISIBLE
    }
}

class Question(val question: String, val answers: List<String>)
