#Игра Бункер
__________
##Основные правила
Вы, как и другие хотите попасть в бункер который нашли с группой людей, минимум людей желающих попасть в бункер это 6
человек, максимальное количество людей это 15 человек. Но чтобы выжить в бункере все желающие попасть не могут.
Поэтому для этого решено было допустить в бункер только тех кто действительно сможет выжить, помочь с последствием и т. д.
Бункер рассчитан на людей в таком количестве:
    6-7 желающих - 3 могут войти в бункер
    8-9 желающих - 4 могут войти в бункер
    10-11 желающих - 5 могут войти в бункер
    12-13 желающих - 6 могут войти в бункер
    14-15 желающих - 7 могут войти в бункер
В процессе игры вы будете рассказывать о своих характеристиках про которые по вашему мнению люди должны знать, или по
желанию открывать те параметры о которых у вас попросят. В первом игровом круге все начинается с представления.
Каждый знакомится кто есть кто, и раскрывает свой основной параметр — это специальность, и 2 любых характеристики в
случае если 6-10 игроков, и 1 характеристику если больше 10 игроков.
На это дается каждому игроку по 1 минуте. (Свое время может устанавливать ведущий, но помните что катаклизм произошел и каждая минута на вес золота!)
После того как каждый игрок рассказал о своем параметре, в группе людей производится голосование кого будут исключать из временного лагеря.
После голосования начинается следующий игровой круг (раунд)
У всех последующих раундах раскрывается только один параметр не зависимо от количества игроков.
В конце игры каждый игрок раскрывает свои характеристики
##Как использовать мою реализацию этой игры?
* Моя версия этой игры требует наличия дискорда(discord.com) у всех игроков
* Для того чтобы начать играть, держателю игры необходимо создать бота discord
    * Для этого переходим по ссылке https://discord.com/developers/applications входим в свой аккаунт и нажимаем New application и вводим произвольное имя бота
    * Переходим в раздел Bot и добавляем бота 
    * В разделе Oauth2 выбираем scope /Bot/ и выдаем ему разрешение Send Message
    * После этих манипуляций появляется ссылка для добавления бота в беседу. Добавляем бота в чат
    * Так же нам понадобится CLIENT SECRET бота, который находится в `General information`
    * Скачиваем архив этого репозитория 
    * Вставляем токен бота(Client secret) в файл GamePresets/Configs
    * Двойным кликом по BUNKER.jar запускаем основное окно игры
* Для игры,  все участники пишут в ЛИЧНЫЕ СООБЩЕНИЯ боту `play`
* После того как все игроки написали боту, нажимаем `Начать игру`
* Всем игрокам раздаются характеристики в личные сообщения от бота
* Играем