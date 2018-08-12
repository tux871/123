//одинаков для пломбирования и распломбирования, какие данные отправляются на сервер, зависит от категории
package com.example.myproject.client.handlers;

import com.example.myproject.client.Postavshik;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

//обработчик кнопки принять, удаляет запись в случае успеха
public class AcceptRasplombirovanieActHandler implements ClickHandler {
	long id;
	Postavshik postavshik;
	
	public AcceptRasplombirovanieActHandler(long id, Postavshik postavshik) {
		super();
		this.id = id;
		this.postavshik = postavshik;

	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//посылаем get запрос с id, в случае успеха удаляем строку
		int rowIndex = postavshik.table.getCellForEvent(event).getRowIndex();
		TextBox act = (TextBox) postavshik.table.getWidget(rowIndex, 7);
		if (act.getText() == "") return;
		postavshik.table.removeRow(rowIndex);
	}
	
}