package com.example.myproject.client.handlers;

import com.example.myproject.client.Category;
import com.example.myproject.client.Paginations;
import com.example.myproject.client.Postavshik;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;

public class PuClickHandler implements ClickHandler {
	Postavshik postavshik;
	public PuClickHandler(Postavshik postavshik) {
		super();
		this.postavshik = postavshik;
	}
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//сбросим offset в начальное значение
		postavshik.tableIndex = 0;
		//сделаем запрос и отобразим данные
		postavshik.refreshPuTable();
		//добавляем кнопки пагинации, чекбокс отметить все и кнопку принять, очищаем панель управления
		postavshik.south11Panel.clear();
		postavshik.south12Panel.clear();
		Button previous = new Button("Предыдущие", new PaginationHandler(Paginations.PREVIOUS, postavshik));
		Button next = new Button("Следующие", new PaginationHandler(Paginations.NEXT, postavshik));
		Button refresh = new Button("Обновить", new PaginationHandler(Paginations.REFRESH, postavshik));

		postavshik.south11Panel.add(previous);
		postavshik.south11Panel.add(next);
		postavshik.south11Panel.add(refresh);
		if(!postavshik.isDone) {
			postavshik.selectAll = new CheckBox("Выделить все записи на странице");
			postavshik.selectAll.addClickHandler(new SelectAllHandler(postavshik.selectAll, postavshik));
			Button accept = new Button("Принять выделенные", new AcceptPuHandler(postavshik));
			postavshik.south12Panel.add(postavshik.selectAll);
			postavshik.south12Panel.add(accept);
		}
	}
}
