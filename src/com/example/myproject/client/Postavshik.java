package com.example.myproject.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.myproject.client.handlers.AcceptAppealHandler;
import com.example.myproject.client.handlers.AcceptInspectionActHandler;
import com.example.myproject.client.handlers.AcceptOplombirovanieActHandler;
import com.example.myproject.client.handlers.AcceptPoverkaActHandler;
import com.example.myproject.client.handlers.AcceptPuHandler;
import com.example.myproject.client.handlers.AcceptRasplombirovanieActHandler;
import com.example.myproject.client.handlers.AppealClickHandler;
import com.example.myproject.client.handlers.InspectionClickHandler;
import com.example.myproject.client.handlers.OplombirovanieClickHandler;
import com.example.myproject.client.handlers.PaginationHandler;
import com.example.myproject.client.handlers.PoverkaClickHandler;
import com.example.myproject.client.handlers.PuClickHandler;
import com.example.myproject.client.handlers.RasplombirovanieClickHandler;
import com.example.myproject.client.handlers.SelectAllHandler;
import com.example.myproject.client.objects.Appeal;
import com.example.myproject.client.objects.Avariya;
import com.example.myproject.client.objects.Inspection;
import com.example.myproject.client.objects.Oplombirovanie;
import com.example.myproject.client.objects.Poverka;
import com.example.myproject.client.objects.Pu;
import com.example.myproject.client.objects.Rasplombirovanie;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel.Direction;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class Postavshik implements EntryPoint {

	
	SplitLayoutPanel mainPanel = new SplitLayoutPanel();
	
	VerticalPanel leftPanel = new VerticalPanel();
	Button pu = new Button("Показания ПУ");
	Button poverka = new Button("Поверка");
	Button oplombirovanie = new Button("Опломбирование");
	Button rasplombirovanie = new Button("Распломбирование");
	Button inspection = new Button("Обследование");
	Button appeal = new Button("Жалоба");
	Button avariya = new Button("Авария");
	
	HorizontalPanel north1Panel = new HorizontalPanel();
	//две доп панели, чтобы разнести элементы слева и справа
	HorizontalPanel north11Panel = new HorizontalPanel();
	HorizontalPanel north12Panel = new HorizontalPanel();
	Image logo = new Image();
	Button novie = new Button("Новые");
	Button vipolneno = new Button("Выполнено");
	Button help = new Button("Помощь");
	Button logout = new Button("Выйти");
	
	HorizontalPanel north2Panel = new HorizontalPanel();
	Label obrasheniyaGrazhdan = new Label("Обращения граждан");
	Button search = new Button("Поиск");
	
	//объявляем модели
	ArrayList<Pu> pus;
	ArrayList<Poverka> poverkas;
	ArrayList<Oplombirovanie> oplombirovanies;
	ArrayList<Rasplombirovanie> rasplombirovanies;
	ArrayList<Inspection> inspections;
	ArrayList<Appeal> appeals;
	ArrayList<Avariya> avariyas;
	//таблица пу
	public FlexTable table;
	// панель панели управления
	// две панели для левого и правого расположения
	HorizontalPanel south1Panel = new HorizontalPanel();
	public HorizontalPanel south11Panel = new HorizontalPanel();
	public HorizontalPanel south12Panel = new HorizontalPanel();
	//оффсет для pu
	public int tableIndex = 0;
	//переключатель новые - выполнено, по умолчанию false (новые)
	public boolean isDone = false;
	// ссылка, чтобы передавать обработчикам
	static Postavshik postavshik;
	//скролл панель для центра
	ScrollPanel scrollCenterPanel = new ScrollPanel();
	//диалог бокс, что вышел
	DialogBox exitBox = new DialogBox();
	Button closeButton = new Button("ОК");
	//выбранная категория в данный момент, pu по умолчанию
	public Category category = Category.PU;
	//чекбокс, чтобы выделить все (вынес сюда, чтобы легче было обнулять его из обработчиков)
	public CheckBox selectAll;
	
	public void onModuleLoad() {
		// ссылка, чтобы передавать обработчикам
		postavshik = this;
		addElements();
		addHandlersAndConfigure();
	}
	
	void addElements() {
		RootLayoutPanel.get().add(mainPanel);
		
		mainPanel.addNorth(north1Panel, 60);
		mainPanel.addNorth(north2Panel, 50);
		mainPanel.addWest(leftPanel, 200);
		mainPanel.addSouth(south1Panel, 200);
		mainPanel.add(scrollCenterPanel);
		south1Panel.setSpacing(10);
		
		north1Panel.add(north11Panel);
		north1Panel.add(north12Panel);
		north1Panel.setWidth("100%");
		north12Panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		north12Panel.setWidth("100%");
		north1Panel.setSpacing(10);
		north11Panel.add(logo);
		logo.setUrl(GWT.getModuleBaseURL()+"images/fulllogo.jpg");
		north11Panel.add(novie);
		north11Panel.add(vipolneno);
		north12Panel.add(help);
		north12Panel.add(logout);
		north2Panel.add(obrasheniyaGrazhdan);
		north2Panel.add(search);
		
		south1Panel.add(south11Panel);
		south1Panel.add(south12Panel);
		south1Panel.setWidth("100%");
		south12Panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		south12Panel.setWidth("100%");
		
		leftPanel.add(pu);
		leftPanel.add(poverka);
		leftPanel.add(oplombirovanie);
		leftPanel.add(rasplombirovanie);
		leftPanel.add(inspection);
		leftPanel.add(appeal);
		leftPanel.add(avariya);
		
	    exitBox.add(closeButton);
		exitBox.setGlassEnabled(true);
		exitBox.setAnimationEnabled(true);

	}
	
	void addHandlersAndConfigure() {
		pu.addClickHandler(new PuClickHandler(postavshik));
		poverka.addClickHandler(new PoverkaClickHandler(postavshik));
		oplombirovanie.addClickHandler(new OplombirovanieClickHandler(postavshik));
		rasplombirovanie.addClickHandler(new RasplombirovanieClickHandler(postavshik));
		inspection.addClickHandler(new InspectionClickHandler(postavshik));
		appeal.addClickHandler(new AppealClickHandler(postavshik));
		//avariya.addClickHandler(new AvariyaClickHandler(postavshik));

		//////////////////////////////////////////////////////////////////////////////// Новые
		// устанавливает флаг выполнено или нет, обновляет содержимое таблицы в зависимости от того, какая категория была выбрана последней, функция обновления смотрит на флаг и обновляет соответственно
		novie.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				isDone = false;
				//проверяет категорию и запускает функцию соответствующего обработчика
				novieVipolneno();
			}
		});
		////////////////////////////////////////////////////////////////////////////////// Выполнено
		vipolneno.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				isDone = true;
				novieVipolneno();
			}
		});
		///////////////////////////////////////////////////////////////////////////////// помощь
		help.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				scrollCenterPanel.clear();
				scrollCenterPanel.add(new HTML("Содержание <br/>" + 
						"Типичное руководство пользователя содержит:<br/>" + 
						"<br/>" + 
						"Аннотацию, в которой приводится краткое изложение содержимого документа и его назначение<br/>" + 
						"Введение, содержащее ссылки на связанные документы и информацию о том, как лучше всего использовать данное руководство<br/>" + 
						"Страницу содержания<br/>" + 
						"Главы, описывающие, как использовать, по крайней мере, наиболее важные функции системы<br/>" + 
						"Глава, описывающая возможные проблемы и пути их решения<br/>" + 
						"Часто задаваемые вопросы и ответы на них<br/>" + 
						"Где ещё найти информацию по предмету, контактная информация<br/>" + 
						"Глоссарий и, в больших документах, предметный указатель<br/>" + 
						"Все главы и пункты, а также рисунки и таблицы, как правило, нумеруются, с тем, чтобы на них можно было сослаться внутри документа или из другого документа. Нумерация также облегчает ссылки на части руководства, например, при общении пользователя со службой поддержки."));
			}
		});
		////////////////////////////////////////////////////////////////////////////////// выйти
		logout.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				exitBox.setText("Вы разлогинились!");
				exitBox.center();
				exitBox.show();
				
			}
		});
		//////////////////////////////////////////////////////////////////////////////// ок в выйти
		closeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
	              exitBox.hide();
	              RootLayoutPanel.get().clear();
			}
		});

	}

	//сделать запрос, удалить старую и вставить новые данные. отдельная функция нужна, чтобы не обновлять заново кнопки панели управления
	public void refreshPuTable() {
		//отметка категории
		category = Category.PU;
		//здесь делаем запрос на сервер, получаем json, создать модель-объект, распарсить json в этот объект. в завимости от isDone, послать соответствующее поле на сервер
		//следующий код - статическое создание объекта
		pus = new ArrayList<Pu>();
		for (int i = 0; i < 10; i++) {
			pus.add(new Pu(i, "18.06.06", 5060404, "Тарана 19, 8", "Иванов", 4854395, "Кухня", 0556, 1113, "Иванова"));
		}
		table = new FlexTable();
		table.setWidth("100%");
		table.setBorderWidth(1);
		scrollCenterPanel.clear();
		scrollCenterPanel.add(table);
		// чтобы не редактировать номер столбца
		int columnIndex = 0;		
		table.setWidget(0, columnIndex++, new HTML("<b>Время</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>ЛКсчет</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адрес</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Абонент</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>ПУ</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Местонахождение</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Предыдущие показания</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Текущие показания</b>"));
		if(!isDone)
			table.setWidget(0, columnIndex++, new HTML("<b>Принять</b>"));
		else
			table.setWidget(0, columnIndex++, new HTML("<b>Кто принял</b>"));

			
		//добавить содержимое объекта в таблицу
		for (int i = 0; i < pus.size(); i++) {
			int rowCount = table.getRowCount();
			columnIndex = 0;		

			
			table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getDate()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(pus.get(i).getLkschet())));
			table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getAddress()));
			table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getAbonent()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(pus.get(i).getPu())));
			table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getPlace()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(pus.get(i).getPrevious())));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(pus.get(i).getCurrent())));
			if(!isDone)
			table.setWidget(rowCount, columnIndex++, new CheckBox());
			else
				table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getProviderDispatcher()));
			
			//присваиваем id стоке
			table.getRowFormatter().getElement(rowCount).setId(String.valueOf(pus.get(i).getId()));

		}
	}
	
	public void refreshPoverkaTable() {
		//отметка категории
		category = Category.POVERKA;
		//здесь делаем запрос на сервер, получаем json, создать модель-объект, распарсить json в этот объект. в завимости от isDone, послать соответствующее поле на сервер
		//следующий код - статическое создание объекта
		poverkas = new ArrayList<Poverka>();
		for (int i = 0; i < 10; i++) {
			//!что делать с -1?
			poverkas.add(new Poverka(i, "05.05.05", 535435, "Тарана 8, 2", "Иванов", 877744433388L, "15 августа", -1, "Иванова"));
		}
		//создать таблицу, удалить виджет, чтобы заменить
		table = new FlexTable();
		table.setWidth("100%");
		table.setBorderWidth(1);
		scrollCenterPanel.clear();
		scrollCenterPanel.add(table);			
		//создать заголовки
		int columnIndex = 0;		
		table.setWidget(0, columnIndex++, new HTML("<b>Время</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Лицевой счет</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адрес</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Абонент</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Телефон</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дата контролера</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Акт</b>"));
		//если выполнено, то отображать кнопки
		if(!isDone)
		table.setWidget(0, columnIndex++, new HTML("<b>Принять</b>"));
		else
			table.setWidget(0, columnIndex++, new HTML("<b>Кто принял</b>"));
		//добавить содержимое объекта в таблицу
		for (int i = 0; i < poverkas.size(); i++) {
			int rowCount = table.getRowCount();
			columnIndex = 0;
			table.setWidget(rowCount, columnIndex++, new HTML(poverkas.get(i).getDate()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(poverkas.get(i).getPersonalAcc())));
			table.setWidget(rowCount, columnIndex++, new HTML(poverkas.get(i).getAddress()));
			table.setWidget(rowCount, columnIndex++, new HTML(poverkas.get(i).getAbonent()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(poverkas.get(i).getPhone())));
			if(!isDone) {
				table.setWidget(rowCount, columnIndex++, new HTML(poverkas.get(i).getDateKont()));
				table.setWidget(rowCount, columnIndex++, new TextBox());
				table.setWidget(rowCount, columnIndex++, new Button("Принять", new AcceptPoverkaActHandler(poverkas.get(i).getId(), this)));
			}
			else {
				table.setWidget(rowCount, columnIndex++, new HTML(poverkas.get(i).getDateKont()));
				table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(poverkas.get(i).getAct())));
				table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getProviderDispatcher()));

			}
		}
	}

	public void refreshOplombirovanieTable() {
		//отметка категории
		category = Category.OPLOMBIROVANIE;
		//здесь делаем запрос на сервер, получаем json, создать модель-объект, распарсить json в этот объект. в завимости от isDone, послать соответствующее поле на сервер
		//следующий код - статическое создание объекта
		oplombirovanies = new ArrayList<Oplombirovanie>();
		for (int i = 0; i < 10; i++) {
			//!что делать с -1?
			oplombirovanies.add(new Oplombirovanie(i, "05.05.05", 535435, "Тарана 8, 2", "Иванов", 877744433388L, "После ремонта трубы", "15 августа", -1, "Иванова"));
		}
		//создать таблицу, удалить виджет, чтобы заменить
		table = new FlexTable();
		table.setWidth("100%");
		table.setBorderWidth(1);
		scrollCenterPanel.clear();
		scrollCenterPanel.add(table);			
		//создать заголовки
		int columnIndex = 0;		
		table.setWidget(0, columnIndex++, new HTML("<b>Время</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Лицевой счет</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адрес</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Абонент</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Телефон</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дополнительно</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дата контролера</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Акт</b>"));
		//если выполнено, то отображать кнопки
		if(!isDone)
		table.setWidget(0, columnIndex++, new HTML("<b>Принять</b>"));
		else
			table.setWidget(0, columnIndex++, new HTML("<b>Кто принял</b>"));
		//добавить содержимое объекта в таблицу
		for (int i = 0; i < oplombirovanies.size(); i++) {
			int rowCount = table.getRowCount();
			columnIndex = 0;
			table.setWidget(rowCount, columnIndex++, new HTML(oplombirovanies.get(i).getDate()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(oplombirovanies.get(i).getPersonalAcc())));
			table.setWidget(rowCount, columnIndex++, new HTML(oplombirovanies.get(i).getAddress()));
			table.setWidget(rowCount, columnIndex++, new HTML(oplombirovanies.get(i).getAbonent()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(oplombirovanies.get(i).getPhone())));
			table.setWidget(rowCount, columnIndex++, new HTML(oplombirovanies.get(i).getAdd()));
			if(!isDone) {
					table.setWidget(rowCount, columnIndex++, new HTML(oplombirovanies.get(i).getDateKont()));
					table.setWidget(rowCount, columnIndex++, new TextBox());
					table.setWidget(rowCount, columnIndex++, new Button("Принять", new AcceptOplombirovanieActHandler(oplombirovanies.get(i).getId(), this)));
			}
			else {
				table.setWidget(rowCount, columnIndex++, new HTML(oplombirovanies.get(i).getDateKont()));
				table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(oplombirovanies.get(i).getAct())));
				table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getProviderDispatcher()));

			}
		}
	}
	
	public void refreshRasplombirovanieTable() {
		//отметка категории
		category = Category.RASPLOMBIROVANIE;
		//здесь делаем запрос на сервер, получаем json, создать модель-объект, распарсить json в этот объект. в завимости от isDone, послать соответствующее поле на сервер
		//следующий код - статическое создание объекта
		rasplombirovanies = new ArrayList<Rasplombirovanie>();
		for (int i = 0; i < 10; i++) {
			//!что делать с -1?
			rasplombirovanies.add(new Rasplombirovanie(i, "05.05.05", 535435, "Тарана 8, 2", "Иванов", 877744433388L, "После ремонта трубы", "15 августа", -1, "Иванова"));
		}
		//создать таблицу, удалить виджет, чтобы заменить
		table = new FlexTable();
		table.setWidth("100%");
		table.setBorderWidth(1);
		scrollCenterPanel.clear();
		scrollCenterPanel.add(table);			
		//создать заголовки
		int columnIndex = 0;		
		table.setWidget(0, columnIndex++, new HTML("<b>Время</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Лицевой счет</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адрес</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Абонент</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Телефон</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дополнительно</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дата контролера</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Акт</b>"));
		//если выполнено, то отображать кнопки
		if(!isDone)
		table.setWidget(0, columnIndex++, new HTML("<b>Принять</b>"));
		else
			table.setWidget(0, columnIndex++, new HTML("<b>Кто принял</b>"));
		//добавить содержимое объекта в таблицу
		for (int i = 0; i < rasplombirovanies.size(); i++) {
			int rowCount = table.getRowCount();
			columnIndex = 0;
			table.setWidget(rowCount, columnIndex++, new HTML(rasplombirovanies.get(i).getDate()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(rasplombirovanies.get(i).getPersonalAcc())));
			table.setWidget(rowCount, columnIndex++, new HTML(rasplombirovanies.get(i).getAddress()));
			table.setWidget(rowCount, columnIndex++, new HTML(rasplombirovanies.get(i).getAbonent()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(rasplombirovanies.get(i).getPhone())));
			table.setWidget(rowCount, columnIndex++, new HTML(rasplombirovanies.get(i).getAdd()));
			if(!isDone) {
					table.setWidget(rowCount, columnIndex++, new HTML(rasplombirovanies.get(i).getDateKont()));
					table.setWidget(rowCount, columnIndex++, new TextBox());
					table.setWidget(rowCount, columnIndex++, new Button("Принять", new AcceptRasplombirovanieActHandler(rasplombirovanies.get(i).getId(), this)));
			}
			else {
				table.setWidget(rowCount, columnIndex++, new HTML(rasplombirovanies.get(i).getDateKont()));
				table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(rasplombirovanies.get(i).getAct())));
				table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getProviderDispatcher()));

			}
		}
	}
	
	public void refreshInspectionTable() {
		//отметка категории
		category = Category.INSPECTION;
		//здесь делаем запрос на сервер, получаем json, создать модель-объект, распарсить json в этот объект. в завимости от isDone, послать соответствующее поле на сервер
		//следующий код - статическое создание объекта
		inspections = new ArrayList<Inspection>();
		for (int i = 0; i < 10; i++) {
			//!что делать с -1?
			inspections.add(new Inspection(i, "05.05.05", 535435, "Тарана 8, 2", "Иванов", 877744433388L, "После ремонта трубы", "15 августа", -1, "Иванова"));
		}
		//создать таблицу, удалить виджет, чтобы заменить
		table = new FlexTable();
		table.setWidth("100%");
		table.setBorderWidth(1);
		scrollCenterPanel.clear();
		scrollCenterPanel.add(table);			
		//создать заголовки
		int columnIndex = 0;		
		table.setWidget(0, columnIndex++, new HTML("<b>Время</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Лицевой счет</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адрес</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Абонент</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Телефон</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дополнительно</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дата контролера</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Акт</b>"));
		//если выполнено, то отображать кнопки
		if(!isDone)
		table.setWidget(0, columnIndex++, new HTML("<b>Принять</b>"));
		else
			table.setWidget(0, columnIndex++, new HTML("<b>Кто принял</b>"));
		//добавить содержимое объекта в таблицу
		for (int i = 0; i < inspections.size(); i++) {
			int rowCount = table.getRowCount();
			columnIndex = 0;
			table.setWidget(rowCount, columnIndex++, new HTML(inspections.get(i).getDate()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(inspections.get(i).getPersonalAcc())));
			table.setWidget(rowCount, columnIndex++, new HTML(inspections.get(i).getAddress()));
			table.setWidget(rowCount, columnIndex++, new HTML(inspections.get(i).getAbonent()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(inspections.get(i).getPhone())));
			table.setWidget(rowCount, columnIndex++, new HTML(inspections.get(i).getAdd()));
			if(!isDone) {
					table.setWidget(rowCount, columnIndex++, new HTML(inspections.get(i).getDateKont()));
					table.setWidget(rowCount, columnIndex++, new TextBox());
					table.setWidget(rowCount, columnIndex++, new Button("Принять", new AcceptInspectionActHandler(inspections.get(i).getId(), this)));
			}
			else {
				table.setWidget(rowCount, columnIndex++, new HTML(inspections.get(i).getDateKont()));
				table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(inspections.get(i).getAct())));
				table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getProviderDispatcher()));

			}
		}
	}
	
	public void refreshAppealTable() {
		//отметка категории
		category = Category.APPEAL;
		//здесь делаем запрос на сервер, получаем json, создать модель-объект, распарсить json в этот объект. в завимости от isDone, послать соответствующее поле на сервер
		//следующий код - статическое создание объекта
		appeals = new ArrayList<Appeal>();
		for (int i = 0; i < 10; i++) {
			//!что делать с -1?
			appeals.add(new Appeal(i, "05.05.05", 535435, "Тарана 8, 2", "Иванов", 877744433388L, "КТЭК, Акимат", "Потолок течет", new Anchor("Скачать", "https://wav-library.net/sounds/0-0-1-21321-20"), "Иванова"));
		}
		//создать таблицу, удалить виджет, чтобы заменить
		table = new FlexTable();
		table.setWidth("100%");
		table.setBorderWidth(1);
		scrollCenterPanel.clear();
		scrollCenterPanel.add(table);			
		//создать заголовки
		int columnIndex = 0;		
		table.setWidget(0, columnIndex++, new HTML("<b>Время</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Лицевой счет</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адрес</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Абонент</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Телефон</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адресат</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Жалоба</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Запись звонка</b>"));
		//если выполнено, то отображать кнопки
		if(!isDone)
			table.setWidget(0, columnIndex++, new HTML("<b>Принять</b>"));
		else
			table.setWidget(0, columnIndex++, new HTML("<b>Кто принял</b>"));
		//добавить содержимое объекта в таблицу
		for (int i = 0; i < appeals.size(); i++) {
			int rowCount = table.getRowCount();
			columnIndex = 0;
			table.setWidget(rowCount, columnIndex++, new HTML(appeals.get(i).getDate()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(appeals.get(i).getPersonalAcc())));
			table.setWidget(rowCount, columnIndex++, new HTML(appeals.get(i).getAddress()));
			table.setWidget(rowCount, columnIndex++, new HTML(appeals.get(i).getAbonent()));
			table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(appeals.get(i).getPhone())));
			table.setWidget(rowCount, columnIndex++, new HTML(appeals.get(i).getDestination()));
			table.setWidget(rowCount, columnIndex++, new HTML(appeals.get(i).getAppealText()));
			table.setWidget(rowCount, columnIndex++, appeals.get(i).getAppealSound());
			
			if(!isDone) {
				table.setWidget(rowCount, columnIndex++, new Button("Принять", new AcceptAppealHandler(appeals.get(i).getId(), this)));

			} else
				table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getProviderDispatcher()));

		}
	}
	
	public void refreshAvariyaTable() {
		//отметка категории
		category = Category.AVARIYA;
		//здесь делаем запрос на сервер, получаем json, создать модель-объект, распарсить json в этот объект. в завимости от isDone, послать соответствующее поле на сервер
		//следующий код - статическое создание объекта
		avariyas = new ArrayList<Avariya>();
		for (int i = 0; i < 10; i++) {
			//!что делать с -1?
			//avariyas.add(new Avariya(i, "05.05.05", 535435, "Тарана 8, 2", "Иванов", 877744433388L, "После ремонта трубы", null, -1));
		}
		//создать таблицу, удалить виджет, чтобы заменить
		table = new FlexTable();
		table.setWidth("100%");
		table.setBorderWidth(1);
		scrollCenterPanel.clear();
		scrollCenterPanel.add(table);			
		//создать заголовки
		int columnIndex = 0;		
		table.setWidget(0, columnIndex++, new HTML("<b>Время</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Лицевой счет</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Адрес</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Абонент</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Телефон</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дополнительно</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Дата контролера</b>"));
		table.setWidget(0, columnIndex++, new HTML("<b>Акт</b>"));
		//если выполнено, то отображать кнопки
		if(!isDone)
		table.setWidget(0, columnIndex++, new HTML("<b>Принять</b>"));
		else
			table.setWidget(0, columnIndex++, new HTML("<b>Кто принял</b>"));
		//добавить содержимое объекта в таблицу
		//for (int i = 0; i < avariyas.size(); i++) {
			//int rowCount = table.getRowCount();
			//columnIndex = 0;
			//table.setWidget(rowCount, columnIndex++, new HTML(avariyas.get(i).getDate()));
			//table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(avariyas.get(i).getPersonalAcc())));
			//table.setWidget(rowCount, columnIndex++, new HTML(avariyas.get(i).getAddress()));
			//table.setWidget(rowCount, columnIndex++, new HTML(avariyas.get(i).getAbonent()));
			//table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(avariyas.get(i).getPhone())));
			//table.setWidget(rowCount, columnIndex++, new HTML(avariyas.get(i).getAdd()));
			//if(!isDone) {
					//table.setWidget(rowCount, columnIndex++, new HTML(avariyas.get(i).getDateKont()));
					//table.setWidget(rowCount, columnIndex++, new TextBox());
					//table.setWidget(rowCount, columnIndex++, new Button("Принять акт", new AcceptAvariyaActHandler(avariyas.get(i).getId(), this)));
			//}
			//else {
				//table.setWidget(rowCount, columnIndex++, new HTML(avariyas.get(i).getDateKont()));
				//table.setWidget(rowCount, columnIndex++, new HTML(String.valueOf(avariyas.get(i).getAct())));
				//table.setWidget(rowCount, columnIndex++, new HTML(pus.get(i).getProviderDispatcher()));

			}
		//}
	//}

	void novieVipolneno() {
		switch (category) {
		case PU:
			pu.click();
			break;
		case POVERKA:
			poverka.click();
			break; 
		case OPLOMBIROVANIE:
			oplombirovanie.click();
			break;
		case RASPLOMBIROVANIE:
			rasplombirovanie.click();
			break;
		case INSPECTION:
			inspection.click();
			break;
		case APPEAL:
			appeal.click();
			break;
		case AVARIYA:
			avariya.click();
			break;
		default:
			break;
		}
	}
}
