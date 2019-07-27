# webProject
 To Do List
 
Учебный проект 1

Приложение – это ToDo список с возможностью прикреплять к каждой задаче списка файл. Список должен содержать три раздела: Today, Tomorrow и Someday. Каждый из разделов может содержать неограниченное количество задач. 
Если задача добавляется в раздел Today или Tomorrow, то ей автоматически присваивается соответственно сегодняшняя или завтрашняя дата выполнения. При добавлении задачи в раздел Someday, необходимо запросить дату выполнения. 
В разделе Today отображаются как сегодняшние задачи, так и просроченные. Все задачи, добавленные в раздел Tomorrow, на следующий день после добавления отображаются в разделе Today. Аналогично, некоторые задачи из раздела Someday образуют новое содержимое раздела Tomorrow.
К каждой задаче можно прикрепить один файл. Файлы из задач можно удалять, а также загружать на локальный компьютер. 
Задачи можно отмечать как выполненные, после чего они исчезают из списка.  При этом они не удаляются физически, а показываются в разделе Fixed. 
Из любого раздела можно удалять задачи. В результате выполнения данной операции они попадают в корзину (еще один раздел). Задачу можно восстановить из корзины. Далее задача отображается в разделе, соответствующем дате выполнения. 
Корзину можно очистить полностью либо удалить каждую задачу по отдельности. В таком случае задача и прикрепленный файл, если такой есть, физически удаляются. 
Для того, чтобы зайти в приложение, пользователь должен зарегистрироваться. Таким образом, начальная страница приложения предлагает пользователю зарегистрироваться либо аутентифицироваться. При нажатии на соответствующую ссылку (или кнопку) пользователю показывается либо форма для регистрации, либо форма для ввода имени пользователя и пароля. После успешной регистрации или аутентификации пользователь переходит на страницу со своим ToDo списком. 
Для упрощения разработки будем считать, что время сервера и пользователя совпадает. 

