import scala.io.Source

//noinspection TypeAnnotation
object Main {
  val path = "C:\\Users\\Neikila\\Documents\\coursera\\AnalyzeReport\\res\\data_v2.csv"

  val subjAnatomy = "Анатомия"
  val subjBiology = "Биология"
  val subjBioChem = "Биологическая химия"
  val subjGysto = "Гистология"
  val subjLatin = "Латинский язык"
  val subjMicro = "Микробиология"
  val subjPsycho = "Психология и педагогика"
  val subjPhyl = "Философия"
  val subjects = List(
    subjAnatomy,
    subjBiology,
    subjBioChem,
    subjGysto,
    subjLatin,
    subjMicro,
    subjPsycho,
    subjPhyl
  )

  val Q1 = new Question(4, List("Я закончил изучение дисциплины", "Я прошел дисциплину, но еще не сдал зачет/экзамен", "Я учу эту дисциплину"))
  val Q2 = new Question(3, List("На лекции", "На практическом занятии", "На стенде", "На сайте", "От друзей", "Ниоткуда"))
  val Q21 = new Question(2, "Рабочие тетради/методические пособия кафедры" :: "Дополнительные занятия по дисциплине на кафедре" :: "Дополнительные занятия по дисциплине за пределами университета" :: "«Помощь» в подготовке к зачету/экзамену" :: Nil)
  val Q3 = new Question(5, List("Мало времени на изучение дисциплины", "Недостаточное количество лекций", "Мало практических занятий", "Хотелось бы иметь лабораторные работы", "Самостоятельная работа не нужна"))
  val Q4_1 = new Question(6, List("Не знаю/Не помню", "Преподаватели менялись"))
  val Q4_2 = new Question(7, List("Не знаю/Не помню", "Преподаватели менялись"))
  val Q5 = new Question(8, List("Ясно излагает, отвечает на вопросы", "Умеет заинтересовать", "Использует интерактивные формы обучения", "Обладает ораторским мастерством"))
  val Q6 = new Question(9, List("Много отступлений от темы лекции/занятия", " Нет логической последовательности в объяснении тем", " Лекции не информативны", " Лекции – это чтение презентации преподавателем"))
  val Q7 = new Question(10, List("Лектора не слышно", "Лектор не пускает опоздавших", " Лектор прерывает лекцию, делая замечания ", "Содержание учебников противоречит утверждениям лектора", "Лекцию можно провести дистанционно", "Лекция не предшествует теме практического занятия"))
  val Q8 = new Question(11, List("Доступное лаконичное убедительное объяснение темы", "Не совсем понятная последовательность тем", "Отсутствие объяснения темы практического занятия ", "Только опрос обучающихся"))
  val Q9 = new Question(12, List("Необходимо подстраиваться под особенности преподавателя", "Настроение преподавателя влияет на оценку/балл", "Непонятна система оценивания знаний"))
  val Q10 = new Question(13, List("Доброжелательное", "Требовательное", "Формальное", "На «ты»", "Неуважительное", "Оскорбительное"))
  val Q11 = new Question(14, List("Развитие памяти", "Развитие логики", "Развитие межличностной коммуникации", "Развитие способности принятия решений", "Заучивание тестов", "Списывание текста из учебника/слайдов/учебного пособия", "Развитие авторской концепции преподавателя"))
  val Q12 = new Question(15, List("Материал лекций", "Учебники из библиотеки", "Электронную библиотеку", "Интернет-источники", "Учебные пособия кафедры", "Литературу, приобретенную самостоятельно", "Материал дополнительных занятий, предложенных кафедрой платно"))
  val Q13 = new Question(16, List("Чтение текста учебника и дополнительной литературы", "Конспектирование текста учебника", "Составление таблиц, выполнение расчетных работ, рисунков, схем", "Работу с конспектом лекций", "Составление плана и тезисов ответов на контрольные вопросы", "Выполнение обучающих и контролирующих заданий", "Написание рефератов, эссе", "Ничего"))
  val Q14 = new Question(17, List("Меньше 50 %", "Больше 50%", "Приближается к 100 %"))
  val Q15 = new Question(18, List("Дисциплина повторяет темы других дисциплин", "На сложные темы мало практических занятий", "Тема практического занятия далека от дисциплины"))
  val Q16 = new Question(19, List("Однотипные", "Большое количество по заданной теме", "Алгоритм решения не разбирался на практическом занятии", "Сложные и нет матрицы решения"))
  val Q17 = new Question(20, List("Устно", "Письменно", "Состоял из нескольких этапов", "Не проводился"))
  val Q18 = new Question(21, List("Контрольным вопросам/задачам, выданным заранее", "Заданиям, известным заранее, но их количество не позволяет проработать каждый вопрос", "Неизвестным заданиям", "Заранее оговоренным «купленным» вопросам", "Представленным Вами работам, выполненным по дисциплине в течение семестра"))
  val Q19 = new Question(22, List("Времени на подготовку недостаточно", "Задают дополнительные вопросы по другим темам", "Имеются вопросы, выходящие за рамки дисциплины", "Присутствуют проверяющие"))
  val Q20 = new Question(23, List("По критериям, вывешенным на стенде/сайте кафедры", "По неизвестным критериям", "По настроению преподавателя", "По предварительной договоренности на безвозмездной основе", "По предварительной договоренности платно", "В зависимости от величины оплаты"))
  val Q22 = new Question(24, List("Аудиториями со специализированным оборудованием и/или фантомами", "Компьютерным залом", "Аудиториями для самостоятельной работы", "Оборудованием и инструментами кафедры", "Расходным материалом кафедры"))
  val Q23 = new Question(25, List("Полезная", "Скучная", "Интересная", "Не нужная"))
  val Q24 = new Question(26, List("Какие знания данной дисциплины пригодятся в профессии", "Где применить полученные умения на практике"))
  val Q25 = new Question(27, List("Сопровождались инструкциями из методических пособий", "Раздавались с устными методическими указаниями", "Выполнялись без участия преподавателя и оставались без проверки", "Вызывающие затруднения в решении, не разбирались с преподавателем", "Не контролировались преподавателем"))
  val FUTURE = new Question(28, List())

  val allQuestion = List(Q1, FUTURE)

  def main(args: Array[String]): Unit = {
    val data = Source.fromFile(path).getLines().toStream
    val tableHeaders = data.head.split(",").toList
    val result = data.tail.map(parse).map(strs => new Line(strs.toArray))
    subjects.foreach { it =>
      println("### Заголовок ###")
      println(it)
      analyze(tableHeaders, result, it)
    }
  }

  def analyze(tableHeaders: List[String], result: Stream[Line], subj: String): Unit = {
    allQuestion.foreach { it =>
      println(tableHeaders(it.numInTable))
      new BaseAnalyze(tableHeaders(it.numInTable), it, { l => new Answer(l.answers(it.numInTable), it) }, List(SubjFilter(subj))).analyze(result)
      println()
    }
    println()
  }

  type Filter = Line => Boolean

  case class SubjFilter(subj: String) extends Filter {
    override def apply(v1: Line): Boolean = v1.subject == subj
  }

  class BaseAnalyze(val qName: String, val question: Question, val extractor: Line => Answer, val filters: List[Filter]) {
    def analyze(result: Stream[Line]): Unit = {
      val filtered = result.filter(line => filters.forall(_(line)))
      val control = question.preparedAnswers.zipWithIndex.map { case (answer, num) =>
        val amount = filtered.count(extractor(_).answersNum.contains(num))
        println(s"Count: $amount, $answer")
          amount
      }.sum
      if (question.preparedAnswers.nonEmpty && control == 0) {
        throw new IllegalStateException(s"Fucked up with $qName")
      }
      println("Customs: ")
      val list = filtered
        .map(extractor(_).custom)
        .filter(_.nonEmpty)
        .groupBy(str => str)
        .map { case (a, b) => CalculatedAnswer(a, b.length) }
        .toList.sortBy(it => -it.amount * 10000 - it.response.length)
      println(list.mkString("\n"))
    }
  }

  case class CalculatedAnswer(response: String, amount: Int) {
    override def toString: String = s"$amount Ответ: $response"
  }

  class Line(val answers: Array[String]) {
    def date = answers(0)
    def subject = answers(1).trim
  }

  class Answer(string: String, question: Question) {
    val (selectedAnswers, answersNum) = question.preparedAnswers.zipWithIndex.filter { case (answer, _) => string.contains(answer) }.unzip
    val custom = question.preparedAnswers.foldLeft(string) { (str, answer) =>
      str
        .replace(answer + ", ", "")
        .replace(answer, "")
        .trim
    }
  }

  class Question(val numInTable: Int, val preparedAnswers: List[String])

  def parse(str: String): List[String] = {
    if (str.isEmpty) Nil
    else {
      if (str.head == '\"') {
        val closingDouble = str.tail.indexOf('"') + 1
        if (closingDouble == 0) {
          println(str)
        }
        val data = str.substring(1, closingDouble)
        data :: parse(removeComma(str.drop(closingDouble + 1)))
      } else {
        val comma = str.indexOf(',')
        if (comma == -1) {
          str :: Nil
        } else {
          extractTillComma(comma, str)
        }
      }
    }
  }

  def extractTillComma(comma: Int, str: String): List[String] = {
    val (data, tail) = str.splitAt(comma)
    data :: parse(removeComma(tail))
  }

  def removeComma(tail: String): String = {
    if (tail.isEmpty) {
      ""
    } else {
      tail.drop(1)
    }
  }
}
