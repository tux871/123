package com.example.myproject.client.handlers;

import com.example.myproject.client.Paginations;
import com.example.myproject.client.Postavshik;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class RasplombirovanieClickHandler implements ClickHandler {
	Postavshik postavshik;
	public RasplombirovanieClickHandler(Postavshik postavshik) {
		super();
		this.postavshik = postavshik;
	}
	public void onClick(ClickEvent event) {
		//сбросим offset в начальное значение
		postavshik.tableIndex = 0;
		//сделаем запрос и отобразим данные
		postavshik.refreshRasplombirovanieTable();
		//добавляем кнопки пагинации, очищаем панель управления
		postavshik.south11Panel.clear();
		postavshik.south12Panel.clear();
		Button previous = new Button("Предыдущие", new PaginationHandler(Paginations.PREVIOUS, postavshik));
		Button next = new Button("Следующие", new PaginationHandler(Paginations.NEXT, postavshik));
		Button refresh = new Button("Обновить", new PaginationHandler(Paginations.REFRESH, postavshik));
		postavshik.south11Panel.add(previous);
		postavshik.south11Panel.add(next);
		postavshik.south11Panel.add(refresh);
	}
}
