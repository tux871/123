package com.example.myproject.client.handlers;

import com.example.myproject.client.Postavshik;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;

public class SelectAllHandler implements ClickHandler {
	//чекбос, на который повешен слушатель
	CheckBox checkBoxAll;
	Postavshik postavshik;
	public SelectAllHandler(CheckBox checkBoxAll, Postavshik postavshik) {
		super();
		this.checkBoxAll = checkBoxAll;
		this.postavshik = postavshik;
	}
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		for (int i = 1; i < postavshik.table.getRowCount(); i++) {
			CheckBox checkBox = (CheckBox) postavshik.table.getWidget(i, 8);
			if (checkBoxAll.getValue()) {
				checkBox.setValue(true);
			} else {
				checkBox.setValue(false);				
			}
		}
	}

}
