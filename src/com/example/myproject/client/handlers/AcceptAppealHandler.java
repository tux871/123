package com.example.myproject.client.handlers;

import com.example.myproject.client.Postavshik;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class AcceptAppealHandler implements ClickHandler {
	long id;
	Postavshik postavshik;

	public AcceptAppealHandler(long id, Postavshik postavshik) {
		super();
		this.id = id;
		this.postavshik = postavshik;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//посылаем get запрос с id, в случае успеха удаляем строку
		int rowIndex = postavshik.table.getCellForEvent(event).getRowIndex();
		postavshik.table.removeRow(rowIndex);
	}

}
