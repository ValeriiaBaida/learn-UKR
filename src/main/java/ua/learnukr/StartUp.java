package ua.learnukr;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.learnukr.models.entities.*;
import ua.learnukr.models.enums.AnswerType;
import ua.learnukr.models.enums.FragmentType;
import ua.learnukr.repositories.*;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartUp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LessonSectionRepository lessonSectionRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskAnswerRepository taskAnswerRepository;
    @Autowired
    private FragmentSectionRepository fragmentSectionRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Метод, який виконується під час запуску додатка
    @Override
    public void run(String... args) throws Exception {
        // Створення користувача для демонстрації
        User user = new User("Valeria", "Baida", "user@user", passwordEncoder.encode("password"));

        // Створення списку матеріалів
        List<Material> materials = Arrays.asList(
                new Material("Конспект НМТ з української мови.pdf"),
                new Material("Наголос.pdf"),
                new Material("Словник фразеологізмів.pdf")
        );

        userRepository.save(user);
        materialRepository.saveAll(materials);


        // Додавання тем у базу даних
        addTopic1();
        addTopic2();

    }

    private void addTopic1() {
        // Створення нової теми з заданим ідентифікатором та назвою
        Topic topic = new Topic(1, "Фонетика. Графіка. Орфоепія");
        topicRepository.save(topic);

        // Створення списку уроків для теми
        List<Lesson> lessons = Arrays.asList(
                new Lesson(1, "Звуки", topic),

                new Lesson(2, "Алфавіт", topic),

                new Lesson(3, "Наголос", topic)
        );
        lessonRepository.saveAll(lessons);

        // Створення списку розділів уроків
        List<LessonSection> sections = Arrays.asList(
                // Урок 1
                new LessonSection(1, "Голосні звуки", lessons.get(0)),
                new LessonSection(2, "Приголосні звуки", lessons.get(0)),

                // Урок 2
                new LessonSection("Алфавіт", lessons.get(1)),

                // Урок 3
                new LessonSection(1, "Наголос", lessons.get(2))
        );
        lessonSectionRepository.saveAll(sections);

        // Створення списку фрагментів тексту для розділів
        List<FragmentSection> fragmentSections = Arrays.asList(
                // Урок 1 підсекція 1
                new FragmentSection(1, FragmentType.TEXT,
                        """
                                Звук – найменша одиниця мовлення. В українськiй мовi є 38 звукiв. Їх подiляють на голоснi та приголоснi.
                                                                
                                Голоснi звуки утворюються за участю лише голосу.
                                                                
                                Їх усього 6: а, o, у, i, и, е.
                                                                
                                На письмi голоснi звуки передаються за допомогою 10 лiтер: а, o, у, i, и, е, я, ю, є, ї. Лiтери я, ю, є, ї називають йотованими. Літера ї завжди позначає два звуки."""
                        , sections.get(0)),
                new FragmentSection(2, FragmentType.TEXT,
                        "Літери я, ю, є позначають по два звуки, якщо стоять:"
                        , sections.get(0)),
                new FragmentSection(3, FragmentType.LIST,
                        """
                                На початку слова: яблуко;  
                                                                
                                Пiсля голосного звуку: заява;     
                                 
                                Пiсля м’якого знака: Мольєр;     
                                                                
                                Пiсля апострофа: здоров’я."""
                        , sections.get(0)),
                new FragmentSection(4, FragmentType.TEXT,
                        """
                                В усiх iнших випадках я, ю, є позначають один звук. Наприклад: лялька.    
                                 
                                Голоснi звуки можуть були наголошеними та ненаголошеними. Ненаголошенi е, и у фонетичнiй транскрипцiї наближаються одне до одного: зима, сестра. Також ненаголошений о наближається до у лише перед складом з наголошеним i або у: зозуля, кожух."""
                        , sections.get(0)),

                // Урок 1 підсекція 2
                new FragmentSection(1, FragmentType.TEXT,
                        """
                                Приголоснi звуки утворюються за участю голосу i шуму. Усього в українськiй мовi є 32 приголосних.
                                                                
                                Приголоснi, у твореннi яких переважає голос, називають сонорними. До сонорних належать: м, в, н, л, р, й i м'якi [л’], [р’], [н’].
                                                                
                                Як запам’ятати «Ми не Валерiй» або «Ми винили рiй».
                                                                
                                Приголоснi, у твореннi яких переважає шум, називають шумними. Шумнi приголоснi подiляються на дзвiнкi та глухi. Дзвiнкi творяться за допомогою голосу та шуму, а глухi – лише шуму. Дзвiнкi та глухi приголоснi утворюють пари.
                                                                
                                Виняток: глухий.
                                                                
                                Як запам’ятати?!  «Хоч фокус – це шепiт».
                                                                
                                У завданнях, де необхiдно знайти дзвiнкi чи глухi приголоснi, сонорнi вважаються дзвiнкими. В деяких iнших завданнях з фонетики власне дзвiнкi i сонорнi слiд розрiзняти. Наприклад, сонорнi не можуть оглушуватися, а дзвiнкi можуть.
                                                                
                                Приголоснi звуки у певнiй позицiї в словi можуть бути твердими та м’якими або твердими та пом’якшеними.
                                                                
                                Усi м’якi приголоснi мають твердi вiдповiдники. Виняток: [й], який завжди м’який.
                                В фонетичнiй транскрипцiї м’якiсть приголосних позначається скiсною рискою, розташованою вгорi пiсля букви: [′].
                                                                
                                Приголоснi д, т, з, с, ц, л, н, р стають м’якими i позначаються у транскрипцiї знаком ′, якщо пiсля них стоять я, ю, є, i, ь. Наприклад: тiтка [т′íтка].
                                                                
                                Як запам’ятати ?! «Де ти з’їси цi лини, Дзар». М’який звук [й] тут «ховається» у лiтерi ї.
                                                                
                                Iншi приголоснi в деяких позицiях можуть бути пом’якшеними.
                                                                
                                Пом’якшеними можуть стати:"""
                        , sections.get(1)),
                new FragmentSection(2, FragmentType.LIST,
                        """
                                губнi: [б′], [п′], [в′], [м′], [ф′];
                                                                
                                гортанний [г′];
                                                                
                                задньоязиковi [х′], [ґ′], [к′];
                                                                
                                шиплячi [ж′], [ш′], [дж′],[ч′].""",
                        sections.get(1)),
                new FragmentSection(3, FragmentType.TEXT,
                        """
                                Якщо вони стоять перед є, ю, я, і. Перед м’яким знаком вони не стоять нiколи.
                                Наприклад: бiль [б′iль], мiсто [м′iсто]. 
                                """,
                        sections.get(1)),

                // Урок 2
                new FragmentSection(1,FragmentType.TEXT,
                        """
                                Для передачi звукiв мови на письмi використовуються букви.
                                                                
                                Визначення Алфавiт — це усталена сукупнiсть букв, розмiщених у певному порядку. Український алфавiт складається з 33 лiтер. """,
                        sections.get(2)),
                new FragmentSection(2,FragmentType.LIST,
                        """
                                
                                А а
                                Б б                                                                 
                                В в                                                                 
                                Г г                                                                 
                                Ґ ґ
                                Д д                                                                
                                Е е
                                Є є                                                                
                                Ж ж                                                                
                                З з                                                                
                                И и                                                                
                                I і
                                Ї ї                                                                
                                Й й
                                К к                                                                
                                Л л                                                                
                                М м                                                                
                                Н н                                                                
                                О о
                                П п                                                                
                                Р р
                                С с                                                                
                                Т т                                                                
                                У у                                                                
                                Ф ф                                                                
                                Х х                                                                
                                Ц ц                                                                
                                Ч ч                                                                
                                Ш ш                                                                
                                Щ щ                                                                
                                Ь ь                                                                
                                Ю ю                                                                
                                Я я """,
                        sections.get(2)),

                // Урок 3
                new FragmentSection(1, FragmentType.TEXT,
                        """
                                Визначення Наголос — це видiлення голосом певного складу в словi.
                                                                
                                Наголос в українськiй мовi є вiльним. Тобто, у рiзних словах вiн може припадати на рiзнi склади.
                                     
                                Наприклад: а́збука, абе́тка, алфаві́т.   
                                                         
                                Також наголос є рухомим. Тобто вiн може зi змiною слова перемiщуватися: кни́жка - книжки́.
                                
                                Деякi слова в українськiй мовi мають подвiйний наголос: """,
                        sections.get(3)),

                 new FragmentSection(2, FragmentType.LIST,
                """
                        
                        байду́же - ба́йдуже;
                        
                        весня́ний - весня́ний;
                        
                        допові́дач - доповіда́ч;
                        
                        завжди́ - завжди́;
                           
                        мабу́ть - мабу́ть.                           
                        """,
                sections.get(3))
        );
        fragmentSectionRepository.saveAll(fragmentSections);

        // Створення списку завдань
        List<Task> tasks = Arrays.asList(
                new Task(1, "На другий склад падає наголос у слові", topic),
                new Task(2, "На третій склад падає наголос у слові", topic),
                new Task(3, "На перший склад падає наголос у слові", topic),
                new Task(4, "На другий склад падає наголос у слові", topic),
                new Task(5, "Перший склад наголошений у слові", topic),
                new Task(6, "Другий склад наголошений у слові", topic),
                new Task(7, "Другий склад наголошений у слові", topic),
                new Task(8, "На перший склад падає наголос у слові", topic),
                new Task(9, "Перший склад наголошений у слові", topic),
                new Task(10, "На другий склад падає наголос у слові", topic)
        );

        taskRepository.saveAll(tasks);


        // Створення відповідей з текстом та посиланням на відповідне завдання
        List<TaskAnswer> taskAnswers = Arrays.asList(
                new TaskAnswer("черговий", tasks.get(0)),
                new TaskAnswer("псевдонім", tasks.get(0)),
                new TaskAnswer("осока", tasks.get(0)),
                new TaskAnswer("ознака", tasks.get(0), AnswerType.CORRECT),

                new TaskAnswer("периметр", tasks.get(1)),
                new TaskAnswer("підошва", tasks.get(1)),
                new TaskAnswer("однаковий", tasks.get(1)),
                new TaskAnswer("кропива", tasks.get(1), AnswerType.CORRECT),

                new TaskAnswer("експерт", tasks.get(2)),
                new TaskAnswer("перекис", tasks.get(2)),
                new TaskAnswer("деінде", tasks.get(2)),
                new TaskAnswer("ожеледь", tasks.get(2), AnswerType.CORRECT),

                new TaskAnswer("листопад", tasks.get(3)),
                new TaskAnswer("новина", tasks.get(3)),
                new TaskAnswer("столяр", tasks.get(3)),
                new TaskAnswer("літопис", tasks.get(3), AnswerType.CORRECT),

                new TaskAnswer("розвал", tasks.get(4)),
                new TaskAnswer("тонкий", tasks.get(4)),
                new TaskAnswer("люблю", tasks.get(4)),
                new TaskAnswer("разом", tasks.get(4), AnswerType.CORRECT),

                new TaskAnswer("приятель", tasks.get(5)),
                new TaskAnswer("недруг", tasks.get(5)),
                new TaskAnswer("відгомін", tasks.get(5)),
                new TaskAnswer("обранець", tasks.get(5), AnswerType.CORRECT),

                new TaskAnswer("запитання", tasks.get(6)),
                new TaskAnswer("сантиметр", tasks.get(6)),
                new TaskAnswer("одинадцять", tasks.get(6)),
                new TaskAnswer("завдання", tasks.get(6), AnswerType.CORRECT),

                new TaskAnswer("жалюзі", tasks.get(7)),
                new TaskAnswer("чіткий", tasks.get(7)),
                new TaskAnswer("перепис", tasks.get(7)),
                new TaskAnswer("випадок", tasks.get(7), AnswerType.CORRECT),

                new TaskAnswer("жалюзі", tasks.get(8)),
                new TaskAnswer("жаркий", tasks.get(8)),
                new TaskAnswer("дочка", tasks.get(8)),
                new TaskAnswer("дрова", tasks.get(8), AnswerType.CORRECT),

                new TaskAnswer("кілометр", tasks.get(9)),
                new TaskAnswer("одинадцять", tasks.get(9)),
                new TaskAnswer("привезти", tasks.get(9)),
                new TaskAnswer("котрий", tasks.get(9), AnswerType.CORRECT)
        );

        taskAnswerRepository.saveAll(taskAnswers);
    }


    private void addTopic2() {
        Topic topic = new Topic(2, "Орфографія");
        topicRepository.save(topic);

        List<Lesson> lessons = Arrays.asList(
                new Lesson(1, "Правопис лiтер, що позначають ненаголошенi голоснi [е], [и], [о]", topic),
                new Lesson(2, "Спрощення в групах приголосних", topic),
                new Lesson(3, "Змiни приголосних за творення слiв", topic)
        );
        lessonRepository.saveAll(lessons);

        List<LessonSection> lessonSections = Arrays.asList(
                new LessonSection(lessons.get(0)),
                new LessonSection(lessons.get(1)),
                new LessonSection(lessons.get(2))
        );
        lessonSectionRepository.saveAll(lessonSections);

        List<FragmentSection> fragmentSections = Arrays.asList(
                new FragmentSection(1, FragmentType.TEXT,
                        """
                                Звуки [е], [и] та [о] в українськiй мовi звучать невиразно у ненаголошенiй позицiї, як ми пам’ятаємо ще з фонетики. Тому часом складно визначити, яку лiтеру потрiбно писати.
                                                                
                                Написання слiв з цими ненаголошеними звуками перевiряється наголосом. Для визначення голосного треба:
                                                                
                                Дiбрати спiльнокореневе слово або змiнити форму слова так, щоб сумнiвний звук став наголошеним.
                                Наприклад: зазирнути – зиркати.
                                                                
                                Якщо за допомогою наголосу перевiрити написання слова не можна, то треба скористатися правилами:
                                
                                У буквосполученнях:
                                
                                """,
                        lessonSections.get(0)),
                new FragmentSection(2, FragmentType.LIST,
                        """
                                ере, еле: дерево, шелест;
                                
                                ри, ли: гриміти, глитати;
                                
                                оро, оло: дорога, волосся.
                                """,
                        lessonSections.get(0)),
                new FragmentSection(3, FragmentType.TEXT,
                        """
                               
                               У суфіксах:
                               
                                """,
                        lessonSections.get(0)),
                new FragmentSection(4, FragmentType.LIST,
                        """
                                ець, ень, тель, еро, елезн-: умілець, велетень, учитель, восьмеро, товстелезний;
                                
                                ен, енн: цуценя, оголошення;
                                
                                еньк, есеньк, ечок, ечк: тоненький, малесенький, мішечок, горлечко;
                                
                                ик, иц(я), ищ(е), иськ: щоденник, палиця, становище, дівчисько;
                                
                                ин: мамин, киянин;
                                
                                ичок, ичк (від ик, иц(я)): паличка, бо палиця;
                                
                                ив(о) в ім.: вариво.
                                """,
                        lessonSections.get(0)),
                new FragmentSection(5, FragmentType.TEXT,
                        """
                               
                               Винятки:
                               
                                """,
                        lessonSections.get(0)),
                new FragmentSection(6, FragmentType.LIST,
                        """
                                зарево, марево.
                                """,
                        lessonSections.get(0)),
                new FragmentSection(7, FragmentType.TEXT,
                        """
                               
                               Інші випадки: 
                               
                                """,
                        lessonSections.get(0)),
                new FragmentSection(8, FragmentType.LIST,
                        """
                                якщо е при зміні випадає або чергується з і: осені — осінь, вересень — вересня;
                                
                                у деяких дієслівних коренях, де вона чергується з е, якщо далі стоїть наголошений суфікс -а(-я): заберу – забирати;
                              
                                у словах: богатир, борсук, гончар, корявий, лопата, монастир, отаман, погано, товар.
                                """,
                        lessonSections.get(0)),
                new FragmentSection(1, FragmentType.TEXT,
                        """
                                Iнодi при вiдмiнюваннi слова або його твореннi виникає важкий для вимови збiг приголосних звукiв. Тому в процесi мовлення один з таких приголосних випадає, тобто вiдбувається спрощення.
                                                                
                                Спрощення вiдбувається:""",
                        lessonSections.get(1)),
                new FragmentSection(2, FragmentType.LIST,
                        """
                                У групах приголосних -ждн-, -здн- випадає д:
                                виїздити – виїзний, тиждень – тижня.
                                                                
                                У групах приголосних -стл-, -стн- випадає т:
                                щастя – щасливий, честь – чесний.
                                                                
                                У групах приголосних -зкн-, -скн- випадає к при твореннi дiєслiв iз суфiксом -ну-.
                                Наприклад: бризк - бризнути, трiск – трiснути.
                                                                
                                У групi приголосних -слн- випадає л:
                                масло - масний, ремесло - ремiсник.
                                                                
                                У групах приголосних -рдц-, -рнц- випадають д, н:
                                чернець – ченця, сердечний – серцевий.""",
                        lessonSections.get(1)),
                new FragmentSection(3, FragmentType.TEXT,
                        """
                                Спрощення НЕ вiдбувається: 1. У словах зап’ястний, кiстлявий, пестливий, хвастливий, хворостняк, шiстнадцять i похiдних вiд них. 2. У прикметниках, утворених вiд iменникiв iншомовного походження з кiнцевим СТ (вiдповiдний звук не вимовляється).
                                Наприклад: баласт – баластний, компост – компостний, контраст – контрастний, форпост – форпостний. 3. У групах приголосних –стц-, -стч-, -стськ-, -нстськ-, -нтств-.
                                Наприклад: артист – артистцi, студент – студентський, агент – агентство. 4. У словах вискнути, випускний, пропускний, тоскно, скнара, скнiти.""",
                        lessonSections.get(1)),

                new FragmentSection(1, FragmentType.TEXT,
                        """
                                Щоб утворити прикметник чи iменники з суфiксами -ськ-, -ств- треба подивитися на кiнцевий приголосний основи й скористатися такими «формулами»:""",
                        lessonSections.get(2)),
                new FragmentSection(2, FragmentType.LIST,
                        """
                                г, ж, з + -ськ- = -зьк-: Прага – празький;
                                                                
                                 к, ч, ц + -ськ- = -цьк-: Безрадичi – безрадицький;
                                                                
                                 х, ш, с + -ськ- = -ськ-: Чуваш – чуваський;
                                                                
                                 г, ж, з + -ств- = -зтв-: боягуз – боягузтво;
                                                                
                                 к, ч, ц + -ств- = -цтв-: представник – представництво;
                                                                
                                 х, ш, с + -ств- = -ств-: птах – птаство.""",
                        lessonSections.get(2))
        );
        fragmentSectionRepository.saveAll(fragmentSections);

        List<Task> tasks = Arrays.asList(
                new Task(1, "Літеру е треба писати на місці обох пропусків у рядку", topic),
                new Task(2, "Літеру е потрібно писати в усіх словах рядка", topic),
                new Task(3, "Правильно написано всі слова в рядку", topic),
                new Task(4, "Букву е на місці пропуску треба писати в усіх словах рядка", topic),
                new Task(5, "Букву и треба писати на місці пропуску в усіх словах рядка", topic),
                new Task(6, "Букву е треба писати на місці пропуску в усіх словах рядка", topic),
                new Task(7, "Букву и треба писати на місці пропуску в усіх словах рядка", topic),
                new Task(8, "Літеру е треба писати на місці пропуску в усіх словах рядка", topic)
        );

        taskRepository.saveAll(tasks);


        List<TaskAnswer> taskAnswers = Arrays.asList(
                new TaskAnswer("вир..нає сон..чко", tasks.get(0)),
                new TaskAnswer("в..личезний п..ріг", tasks.get(0)),
                new TaskAnswer("кр..шталеве дж..рело", tasks.get(0)),
                new TaskAnswer("в..сняне мар..во", tasks.get(0), AnswerType.CORRECT),

                new TaskAnswer("шел..стіти, мар..во, нож..чок", tasks.get(1)),
                new TaskAnswer("кр..мезний, л..цедій, сут..ніти", tasks.get(1)),
                new TaskAnswer("кр..шталик, в..селка, ож..ледь", tasks.get(1)),
                new TaskAnswer("об..ремок, віт..рець, зач..кати", tasks.get(1), AnswerType.CORRECT),

                new TaskAnswer("увеч..рі, увічл..вість, упередж..ний", tasks.get(2)),
                new TaskAnswer("оч..видячки, змал..чку, ч..мдуж", tasks.get(2)),
                new TaskAnswer("беззаст..режно, кр..шити, змуш..ний", tasks.get(2)),
                new TaskAnswer("б..нтежний, к..рівний, кл..котить", tasks.get(2), AnswerType.CORRECT),

                new TaskAnswer("власн.ця, кош..чок, пост..лити", tasks.get(3)),
                new TaskAnswer("заб..рати, ч..рговий, тр..вожний", tasks.get(3)),
                new TaskAnswer("печ..во, шел..стіння, др..жати", tasks.get(3)),
                new TaskAnswer("височ..на, відгр..міти, вогн..ще", tasks.get(3), AnswerType.CORRECT),

                new TaskAnswer("об…режно, тр…вожний, пол…тіти", tasks.get(4)),
                new TaskAnswer("мер..хтіння, вч..нити, ол..нятко", tasks.get(4)),
                new TaskAnswer("вел..тень, гр..мить, кр..мезний", tasks.get(4)),
                new TaskAnswer("підгр..бти, зач..кати, надв..чірок", tasks.get(4), AnswerType.CORRECT),

                new TaskAnswer("власн.ця, кош..чок, пост..лити", tasks.get(5)),
                new TaskAnswer("заб..рати, ч..рговий, тр..вожний", tasks.get(5)),
                new TaskAnswer("печ..во, шел..стіння, др..жати", tasks.get(5)),
                new TaskAnswer("височ..на, відгр..міти, вогн..ще", tasks.get(5), AnswerType.CORRECT),

                new TaskAnswer("мар..во, міл..на, кл..котати, м..тушня", tasks.get(6)),
                new TaskAnswer("мисл..ння, утвор..ний, прав..ло, гр..чаний", tasks.get(6)),
                new TaskAnswer("ш..ршавий, заскр..готав, бер..зень, заст..лати", tasks.get(6)),
                new TaskAnswer("крап..лька, віт..рець, пов..рнутися, зд..рати", tasks.get(6)),
                new TaskAnswer("л..вада, зв..личений, ск..лястий, зач..пити", tasks.get(6), AnswerType.CORRECT),

                new TaskAnswer("н..сти, вит..рати, гр..чаний, зел..ніти", tasks.get(7)),
                new TaskAnswer("реш..то, кол..со, вис..пати, кл..котати", tasks.get(7)),
                new TaskAnswer("зач..пити, розпоч..нати, п..кти, ч..брець", tasks.get(7)),
                new TaskAnswer("мар..во, прич..пити, ш..птати, гороб..ня", tasks.get(7), AnswerType.CORRECT)
        );

        taskAnswerRepository.saveAll(taskAnswers);
    }
}
