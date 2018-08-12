package com.example.myproject.client.handlers;

import com.example.myproject.client.Category;
import com.example.myproject.client.Paginations;
import com.example.myproject.client.Postavshik;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class PaginationHandler implements ClickHandler {
	Paginations paginations;
	Postavshik postavshik;
	@Override
	public void onClick(ClickEvent event) {
		//сначала обнуляем checkBoxAll
		//при обновлении в какой то момент стала выскакивать ошибка, пришлось добавить проверку
		if (postavshik.selectAll != null)
		postavshik.selectAll.setValue(false);
		// TODO Auto-generated method stub
		//сначала проверяем предыдущие - следующие - обновить
		if(paginations == Paginations.PREVIOUS) {
			if(postavshik.tableIndex == 0) {
				return;
			}
			postavshik.tableIndex--;
		} else if (paginations == Paginations.REFRESH) {
			//ничего не делать
		} else {
			postavshik.tableIndex++;
		}
		//в зависимости от category, вызвать соответствующую функцию рефреш, к примеру,
		//refreshPuTable();
		switch (postavshik.category) {
		case PU:
			postavshik.refreshPuTable();	
			break;
		case POVERKA:
			postavshik.refreshPoverkaTable();	
			break;
		case OPLOMBIROVANIE:
			postavshik.refreshOplombirovanieTable();	
			break;
		case RASPLOMBIROVANIE:
			postavshik.refreshRasplombirovanieTable();
			break;
		case INSPECTION:
			postavshik.refreshInspectionTable();
			break;
		case APPEAL:
			postavshik.refreshAppealTable();
			break;
		case AVARIYA:
			//postavshik.refreshAvariyaTable();
			break;

		default:
			break;
		}
	}
	public PaginationHandler(Paginations paginations, Postavshik postavshik) {
		super();
		this.paginations = paginations;
		this.postavshik = postavshik;
	}

}
