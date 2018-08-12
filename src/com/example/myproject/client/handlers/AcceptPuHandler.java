package com.example.myproject.client.handlers;

import java.util.ArrayList;
import java.util.Collections;

import com.example.myproject.client.Postavshik;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;

//обработчик кнопки принять, удаляет запись в случае успеха
public class AcceptPuHandler implements ClickHandler {
	Postavshik postavshik;
	
	
	public AcceptPuHandler(Postavshik postavshik) {
		super();
		this.postavshik = postavshik;
	}

	@Override
	public void onClick(ClickEvent event) {
		//обнуляем checkBoxAll
		postavshik.selectAll.setValue(false);
		// TODO Auto-generated method stub
		//формируем массив id: проходимся по таблице, смотрим, какие чекбоксы выделены, с этих строк снимаем id
		ArrayList<Integer> ids = new ArrayList<>();
		//помещать сюда номера отмеченных строк, чтобы потом их удалить в случае успеха
		ArrayList<Integer> rows = new ArrayList<>();
		for (int i = 1; i < postavshik.table.getRowCount(); i++) {
			CheckBox checkBox = (CheckBox) postavshik.table.getWidget(i, 8);
			if (checkBox.getValue()) {
				ids.add(Integer.parseInt(postavshik.table.getRowFormatter().getElement(i).getId()));
				rows.add(i);
			}
		}
		//String id = PUS.get(ii+1).getId();
		//в случае успеха удаляем строку, удаляем строки с конца, потому что если удалять с начала, сломается
		Collections.reverse(rows);
		for (int i = 0; i < rows.size(); i++) {
			postavshik.table.removeRow(rows.get(i));
		}
	}
	
}